package programs;

import java.util.List;

import worms.model.expressions.*;
import worms.model.programs.ProgramFactory;
import worms.model.statements.*;
import worms.model.types.*;

public class ProgramFactoryImpl implements ProgramFactory<Expression,Statement,Type>{

	@Override
	public Expression createDoubleLiteral(int line, int column, double d) {
		return (Expression) new DoubleLiteral(d);
	}

	@Override
	public Expression createBooleanLiteral(int line, int column, boolean b) {
		return (Expression) new BooleanLiteral(b);
	}

	@Override
	public Expression createAnd(int line, int column, Expression e1,
			Expression e2) {
		return (Expression) new AndExpression(e1, e2);
	}

	@Override
	public Expression createOr(int line, int column, Expression e1,
			Expression e2) {
		return (Expression) new OrExpression(e1, e2);
	}

	@Override
	public Expression createNot(int line, int column, Expression e) {
		return new NotExpression(e);
	}

	@Override
	public Expression createNull(int line, int column) {
		return (Expression) new NullLiteral();
	}

	@Override
	public Expression createSelf(int line, int column) {
		return new SelfWormExpression();
	}

	@Override
	public Expression createGetX(int line, int column, Expression e) {
		return (Expression) new GetXExpression(e);
	}

	@Override
	public Expression createGetY(int line, int column, Expression e) {
		return new GetYExpression(e);
	}

	@Override
	public Expression createGetRadius(int line, int column, Expression e) {
		return new GetRadiusExpression(e);
	}

	@Override
	public Expression createGetDir(int line, int column, Expression e) {
		return new GetDirectionExpression(e);
	}

	@Override
	public Expression createGetAP(int line, int column, Expression e) {
		return new GetAPExpression(e);
	}

	@Override
	public Expression createGetMaxAP(int line, int column, Expression e) {
		return new GetMaxAPExpression(e);
	}

	@Override
	public Expression createGetHP(int line, int column, Expression e) {
		return new GetHPExpression(e);
	}

	@Override
	public Expression createGetMaxHP(int line, int column, Expression e) {
		return new GetMaxHPExpression(e);
	}

	@Override
	public Expression createSameTeam(int line, int column, Expression e) {
		return new SameTeamExpression(e);
	}

	@Override
	public Expression createSearchObj(int line, int column, Expression e) {
		return new SearchObjectExpression(e);
	}

	@Override
	public Expression createIsWorm(int line, int column, Expression e) {
		return new IsWormExpression(e);
	}

	@Override
	public Expression createIsFood(int line, int column, Expression e) {
		return new IsFoodExpression(e);
	}

	@Override
	public Expression createVariableAccess(int line, int column, String name) {
		return new VariableAccesExpression(name);
	}

	@Override
	public Expression createLessThan(int line, int column, Expression e1,
			Expression e2) {
		return new LessThan(e1,e2);
	}

	@Override
	public Expression createGreaterThan(int line, int column, Expression e1,
			Expression e2) {
		return new GreaterThan(e1,e2);
	}

	@Override
	public Expression createLessThanOrEqualTo(int line, int column,
			Expression e1, Expression e2) {
		return new LessThanOrEqual(e1, e2);
	}

	@Override
	public Expression createGreaterThanOrEqualTo(int line, int column,
			Expression e1, Expression e2) {
		return new GreaterThanOrEqual(e1, e2);
	}

	@Override
	public Expression createEquality(int line, int column, Expression e1,
			Expression e2) {
		return new Equality(e1,e2);
	}

	@Override
	public Expression createInequality(int line, int column, Expression e1,
			Expression e2) {
		return new Inequality(e1,e2);
	}

	@Override
	public Expression createAdd(int line, int column, Expression e1,
			Expression e2) {
		return new Addition( e1, e2);
	}

	@Override
	public Expression createSubtraction(int line, int column, Expression e1,
			Expression e2) {
		return new Subtraction(e1,e2);
	}

	@Override
	public Expression createMul(int line, int column, Expression e1,
			Expression e2) {
		return new Multiplication(e1,e2);
	}

	@Override
	public Expression createDivision(int line, int column, Expression e1,
			Expression e2) {
		return new Division(e1,e2);
	}

	@Override
	public Expression createSqrt(int line, int column, Expression e) {
		return new SqrtExpression(e);
	}

	@Override
	public Expression createSin(int line, int column, Expression e) {
		return new SinExpression(e);
	}

	@Override
	public Expression createCos(int line, int column, Expression e) {
		return new CosExpression(e);
	}

	@Override
	public Statement createTurn(int line, int column, Expression angle) {
		return new Turn(angle);
	}

	@Override
	public Statement createMove(int line, int column) {
		return new Move();
	}

	@Override
	public Statement createJump(int line, int column) {
		return new Jump();
	}

	@Override
	public Statement createToggleWeap(int line, int column) {
		return new ToggleWeapon();
	}

	@Override
	public Statement createFire(int line, int column, Expression yield) {
		return new Fire(yield);
	}

	@Override
	public Statement createSkip(int line, int column) {
		return new Skip();
	}

	@Override
	public Statement createAssignment(int line, int column,
			String variableName, Expression rhs) {
		return new Assignment(variableName, rhs);
	}

	@Override
	public Statement createIf(int line, int column, Expression condition,
			Statement then, Statement otherwise) {
		return new If(condition, then, otherwise);
	}

	@Override
	public Statement createWhile(int line, int column, Expression condition,
			Statement body) {
		return new While(condition, body);
	}

	@Override
	public Statement createForeach(int line, int column,
			worms.model.programs.ProgramFactory.ForeachType type,
			String variableName, Statement body) {
		return new ForEach(type, variableName, body);
	}

	@Override
	public Statement createSequence(int line, int column,
			List<Statement> statements) {
		return new Sequence(statements);
	}

	@Override
	public Statement createPrint(int line, int column, Expression e) {
		return new Print(e);
	}

	@Override
	public Type createDoubleType() {
		return new DoubleType();
	}

	@Override
	public Type createBooleanType() {
		return new BooleanType();
	}

	@Override
	public Type createEntityType() {
		return new Entity<>();
	}

	@Override
	public Expression createVariableAccess(int line, int column, String name,
			Type type) {
		return null;
	}

}
