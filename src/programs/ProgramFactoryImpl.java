package programs;

import java.util.List;

import worms.model.expressions.*;
import worms.model.programs.ProgramFactory;
import worms.model.statements.*;
import worms.model.types.*;

public class ProgramFactoryImpl<E> implements ProgramFactory<Expression<E>,Statement,Type>{

	@Override
	public Expression<E> createDoubleLiteral(int line, int column, double d) {
		return (Expression<E>) new DoubleLiteral(d);
	}

	@Override
	public Expression<E> createBooleanLiteral(int line, int column, boolean b) {
		return (Expression<E>) new BooleanLiteral(b);
	}

	@Override
	public Expression<E> createAnd(int line, int column, Expression<E> e1,
			Expression<E> e2) {
		return (Expression<E>) new AndExpression<E>(e1, e2);
	}

	@Override
	public Expression<E> createOr(int line, int column, Expression<E> e1,
			Expression<E> e2) {
		return (Expression<E>) new OrExpression<>(e1, e2);
	}

	@Override
	public Expression<E> createNot(int line, int column, Expression<E> e) {
		return null;
	}

	@Override
	public Expression<E> createNull(int line, int column) {
		return (Expression<E>) new NullLiteral();
	}

	@Override
	public Expression<E> createSelf(int line, int column) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression<E> createGetX(int line, int column, Expression<E> e) {
		return (Expression<E>) new GetXExpression(e);
	}

	@Override
	public Expression<E> createGetY(int line, int column, Expression<E> e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression<E> createGetRadius(int line, int column, Expression<E> e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression<E> createGetDir(int line, int column, Expression<E> e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression<E> createGetAP(int line, int column, Expression<E> e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression<E> createGetMaxAP(int line, int column, Expression<E> e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression<E> createGetHP(int line, int column, Expression<E> e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression<E> createGetMaxHP(int line, int column, Expression<E> e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression<E> createSameTeam(int line, int column, Expression<E> e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression<E> createSearchObj(int line, int column, Expression<E> e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression<E> createIsWorm(int line, int column, Expression<E> e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression<E> createIsFood(int line, int column, Expression<E> e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression<E> createVariableAccess(int line, int column, String name) {
		return new VariableAccesExpression<E>(name);
	}

//	@Override
//	public Expression<E> createVariableAccess(int line, int column, String name,
//			Type type) {
//		return null;
//	}

	@Override
	public Expression<E> createLessThan(int line, int column, Expression<E> e1,
			Expression<E> e2) {
		return (Expression<E>) new LessThan(e1,e2);
	}

	@Override
	public Expression<E> createGreaterThan(int line, int column, Expression<E> e1,
			Expression<E> e2) {
		return (Expression<E>) new GreaterThan(e1,e2);
	}

	@Override
	public Expression<E> createLessThanOrEqualTo(int line, int column,
			Expression<E> e1, Expression<E> e2) {
		return (Expression<E>) new LessThanOrEqual<>(e1, e2);
	}

	@Override
	public Expression<E> createGreaterThanOrEqualTo(int line, int column,
			Expression<E> e1, Expression<E> e2) {
		return (Expression<E>) new GreaterThanOrEqual<>(e1, e2);
	}

	@Override
	public Expression<E> createEquality(int line, int column, Expression<E> e1,
			Expression<E> e2) {
		return (Expression<E>) new Equality(e1,e2);
	}

	@Override
	public Expression<E> createInequality(int line, int column, Expression<E> e1,
			Expression<E> e2) {
		return (Expression<E>) new Inequality(e1,e2);
	}

	@Override
	public Expression<E> createAdd(int line, int column, Expression<E> e1,
			Expression<E> e2) {
		return (Expression<E>) new Addition((DoubleExpression) e1,(DoubleExpression) e2);
	}

	@Override
	public Expression<E> createSubtraction(int line, int column, Expression<E> e1,
			Expression<E> e2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression<E> createMul(int line, int column, Expression<E> e1,
			Expression<E> e2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression<E> createDivision(int line, int column, Expression<E> e1,
			Expression<E> e2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression<E> createSqrt(int line, int column, Expression<E> e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression<E> createSin(int line, int column, Expression<E> e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression<E> createCos(int line, int column, Expression<E> e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createTurn(int line, int column, Expression<E> angle) {
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
	public Statement createFire(int line, int column, Expression<E> yield) {
		return new Fire(yield);
	}

	@Override
	public Statement createSkip(int line, int column) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createAssignment(int line, int column,
			String variableName, Expression<E> rhs) {
		return new Assignment<E>(variableName, rhs);
	}

	@Override
	public Statement createIf(int line, int column, Expression<E> condition,
			Statement then, Statement otherwise) {
		return new If(condition, then, otherwise);
	}

	@Override
	public Statement createWhile(int line, int column, Expression<E> condition,
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
	public Statement createPrint(int line, int column, Expression<E> e) {
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
