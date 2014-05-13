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
		return (Expression) new OrExpression<>(e1, e2);
	}

	@Override
	public Expression createNot(int line, int column, Expression e) {
		return null;
	}

	@Override
	public Expression createNull(int line, int column) {
		return (Expression) new NullLiteral();
	}

	@Override
	public Expression createSelf(int line, int column) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createGetX(int line, int column, Expression e) {
		return (Expression) new GetXExpression(e);
	}

	@Override
	public Expression createGetY(int line, int column, Expression e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createGetRadius(int line, int column, Expression e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createGetDir(int line, int column, Expression e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createGetAP(int line, int column, Expression e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createGetMaxAP(int line, int column, Expression e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createGetHP(int line, int column, Expression e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createGetMaxHP(int line, int column, Expression e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createSameTeam(int line, int column, Expression e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createSearchObj(int line, int column, Expression e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createIsWorm(int line, int column, Expression e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createIsFood(int line, int column, Expression e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createVariableAccess(int line, int column, String name) {
		return new VariableAccesExpression(name);
	}

//	@Override
//	public Expression createVariableAccess(int line, int column, String name,
//			Type type) {
//		return null;
//	}

	@Override
	public Expression createLessThan(int line, int column, Expression e1,
			Expression e2) {
		return (Expression) new LessThan(e1,e2);
	}

	@Override
	public Expression createGreaterThan(int line, int column, Expression e1,
			Expression e2) {
		return (Expression) new GreaterThan(e1,e2);
	}

	@Override
	public Expression createLessThanOrEqualTo(int line, int column,
			Expression e1, Expression e2) {
		return (Expression) new LessThanOrEqual<>(e1, e2);
	}

	@Override
	public Expression createGreaterThanOrEqualTo(int line, int column,
			Expression e1, Expression e2) {
		return (Expression) new GreaterThanOrEqual<>(e1, e2);
	}

	@Override
	public Expression createEquality(int line, int column, Expression e1,
			Expression e2) {
		return (Expression) new Equality(e1,e2);
	}

	@Override
	public Expression createInequality(int line, int column, Expression e1,
			Expression e2) {
		return (Expression) new Inequality(e1,e2);
	}

	@Override
	public Expression createAdd(int line, int column, Expression e1,
			Expression e2) {
		return (Expression) new Addition((DoubleExpression) e1,(DoubleExpression) e2);
	}

	@Override
	public Expression createSubtraction(int line, int column, Expression e1,
			Expression e2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createMul(int line, int column, Expression e1,
			Expression e2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createDivision(int line, int column, Expression e1,
			Expression e2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createSqrt(int line, int column, Expression e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createSin(int line, int column, Expression e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createCos(int line, int column, Expression e) {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createAssignment(int line, int column,
			String variableName, Expression rhs) {
		return new Assignment<E>(variableName, rhs);
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Type createBooleanType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Type createEntityType() {
		return null;
	}

}
