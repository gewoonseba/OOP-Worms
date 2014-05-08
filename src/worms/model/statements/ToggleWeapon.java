package worms.model.statements;

import worms.gui.game.IActionHandler;
import worms.model.Worm;

public class ToggleWeapon extends ActionStatement {
	
	public ToggleWeapon(){
		
	}

	@Override
	public String toString() {
		return "toggleweap";
	}

	@Override
	public void executeStatement() {
		Worm self = SelfWormExpression.getWorm();
		IActionHandler handler = self.getProgram().getHandler();
		handler.toggleWeapon(self);
	}

}
