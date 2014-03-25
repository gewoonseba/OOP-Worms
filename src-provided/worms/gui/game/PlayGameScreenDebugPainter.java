package worms.gui.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Ellipse2D.Double;
import java.awt.image.BufferedImage;
import java.util.List;

import worms.gui.GUIConstants;
import worms.gui.GUIUtils;
import worms.gui.Level;
import worms.gui.game.commands.Command;
import worms.gui.game.sprites.FoodSprite;
import worms.gui.game.sprites.WormSprite;
import worms.model.ModelException;
import worms.model.World;

public class PlayGameScreenDebugPainter extends PlayGameScreenPainter {

	private static final int LOCATION_MARKER_SIZE = 4;

	private static final double MAX_DIVERSION = 0.7875;

	private static final boolean PAINT_PASSABLE = true;

	private Image passableImage;

	public PlayGameScreenDebugPainter(PlayGameScreen screen) {
		super(screen);
	}

	@Override
	public void paint(Graphics2D g) {
		super.paint(g);

		paintCommandStack(g);
	}

	protected void paintCommandStack(Graphics2D g) {
		List<Command> commands = getScreen().getGameState()
				.getEnqueuedCommands();
		g.setColor(Color.WHITE);
		for (int i = 0; i < commands.size(); i++) {
			g.drawString(commands.get(i).toString(), 15, 30 + i * 15);
		}
	}

	@Override
	protected void paintLevel() {
		super.paintLevel();

		Level level = getState().getLevel();
		World world = getState().getWorld();
		if (passableImage == null) {
			BufferedImage image = new BufferedImage(getScreen()
					.getScreenWidth(), getScreen().getScreenHeight(),
					BufferedImage.TYPE_4BYTE_ABGR);
			BufferedImage adjacencyImage = new BufferedImage(getScreen()
					.getScreenWidth(), getScreen().getScreenHeight(),
					BufferedImage.TYPE_4BYTE_ABGR);
			Graphics2D imGfx = image.createGraphics();
			imGfx.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
			Graphics2D imAdjacencyGfx = adjacencyImage.createGraphics();
			imAdjacencyGfx.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);

			double testRadius = getScreen().screenToWorldDistance(10); // 10
																		// screen
																		// pixels
			double stepSize = getScreen().screenToWorldDistance(3); // 3 screen
																	// pixels
			for (double x = testRadius; x <= level.getWorldWidth() - testRadius; x += stepSize) {
				for (double y = testRadius; y <= level.getWorldHeight()
						- testRadius; y += stepSize) {
					double randomizedX = x + (-0.5 + Math.random()) * stepSize
							* 2;
					double randomizedY = y + (-0.5 + Math.random()) * stepSize
							* 2;
					Graphics2D targetGraphics = imGfx;
					boolean isPassable = false;
					if (getFacade().isImpassable(world, randomizedX,
							randomizedY, testRadius)) {
						targetGraphics.setColor(new Color(255, 0, 0, 4));
					} else if (getFacade().isAdjacent(world, randomizedX,
							randomizedY, testRadius)) {
						targetGraphics = imAdjacencyGfx;
						targetGraphics.setColor(new Color(0, 255, 0, 64));
					} else {
						isPassable = true;
						targetGraphics.setColor(new Color(0, 0, 255, 4));
					}
					if (!isPassable || PAINT_PASSABLE) {
						Double circle = GUIUtils.circleAt(
								getScreenX(randomizedX),
								getScreenY(randomizedY), getScreen()
										.worldToScreenDistance(testRadius));
						targetGraphics.fill(circle);
					}
				}
			}
			imGfx.drawImage(adjacencyImage, 0, 0, null);
			imAdjacencyGfx.dispose();
			imGfx.dispose();
			this.passableImage = image;
		}

		currentGraphics.drawImage(passableImage, 0, 0, null);

		drawCrossMarker(getScreenX(0), getScreenY(0), 10, Color.BLUE);
		drawCrossMarker(getScreenX(0), getScreenY(getLevel().getWorldHeight()),
				10, Color.BLUE);
		drawCrossMarker(getScreenX(getLevel().getWorldWidth()), getScreenY(0),
				10, Color.BLUE);
		drawCrossMarker(getScreenX(getLevel().getWorldWidth()),
				getScreenY(getLevel().getWorldHeight()), 10, Color.BLUE);
	}

	@Override
	protected void paintWorm(WormSprite sprite) {

		drawName(sprite);

		drawActionBar(sprite);
		drawHitpointsBar(sprite);

		drawOutline(sprite);
		drawJumpMarkers(sprite); // also draw for other worms

		drawDirectionLine(sprite);

		drawLocationMarker(sprite);

	}

	@Override
	protected void drawJumpMarkers(WormSprite sprite) {
		try {
			double currentWormX = getFacade().getX(sprite.getWorm());
			double currentWormY = getFacade().getY(sprite.getWorm());

			double[] prevXY = new double[] { currentWormX, currentWormY };

			double time = getFacade().getJumpTime(sprite.getWorm(),
					GUIConstants.JUMP_TIME_STEP);
			int n = 1 + (int) (time / JUMP_MARKER_TIME_DISTANCE);
			for (int i = 1; i <= n; i++) {
				double dt = i * time / n;
				double[] xy = getFacade().getJumpStep(sprite.getWorm(), dt);
				if (xy != null && prevXY != null) {
					double jumpX = getScreenX(xy[0]);
					double jumpY = getScreenY(xy[1]);
					currentGraphics.setColor(JUMP_MARKER_COLOR);
					currentGraphics.drawLine((int) getScreenX(prevXY[0]),
							(int) getScreenY(prevXY[1]), (int) jumpX,
							(int) jumpY);
					prevXY = xy;
					drawCrossMarker(jumpX, jumpY, JUMP_MARKER_SIZE,
							JUMP_MARKER_COLOR);
				}
			}
		} catch (ModelException e) {
			// cannot jump; draw nothing
		}
	}

	/**
	 * Draw a marker at the current location of the worm (which is not
	 * necessarily equal to the sprite's location)
	 */
	protected void drawLocationMarker(WormSprite worm) {
		double x = getFacade().getX(worm.getWorm());
		double y = getFacade().getY(worm.getWorm());

		drawCrossMarker(getScreenX(x), getScreenY(y), LOCATION_MARKER_SIZE,
				Color.YELLOW);
	}

	@Override
	protected void paintFood(FoodSprite sprite) {
		super.paintFood(sprite);
		double r = getFacade().getRadius(sprite.getFood());
		double x = sprite.getCenterX();
		double y = sprite.getCenterY();

		currentGraphics.setColor(Color.CYAN);
		Shape circle = GUIUtils.circleAt(x, y, getScreen()
				.worldToScreenDistance(r));
		currentGraphics.fill(circle);
		currentGraphics.setColor(Color.DARK_GRAY);
		currentGraphics.draw(circle);
	}

	protected void drawOutline(WormSprite sprite) {
		double r = getFacade().getRadius(sprite.getWorm());
		double x = sprite.getCenterX();
		double y = sprite.getCenterY();

		currentGraphics.setColor(Color.YELLOW);
		Shape circle = GUIUtils.circleAt(x, y, getScreen()
				.worldToScreenDistance(r));
		currentGraphics.draw(circle);

	}

	protected void drawDirectionLine(WormSprite sprite) {
		double x = sprite.getCenterX();
		double y = sprite.getCenterY();
		double dist = sprite.getHeight(currentGraphics) / 2.0;
		double direction = getFacade().getOrientation(sprite.getWorm());

		currentGraphics.setColor(Color.YELLOW);
		currentGraphics.drawLine((int) x, (int) y,
				(int) (x + dist * Math.cos(direction)),
				(int) (y - dist * Math.sin(direction)));

		// draw move tolerance

		direction = direction - MAX_DIVERSION;
		currentGraphics.drawLine((int) x, (int) y,
				(int) (x + dist * Math.cos(direction)),
				(int) (y - dist * Math.sin(direction)));

		direction = direction + 2 * MAX_DIVERSION;
		currentGraphics.drawLine((int) x, (int) y,
				(int) (x + dist * Math.cos(direction)),
				(int) (y - dist * Math.sin(direction)));
	}

}
