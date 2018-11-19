package absyn;

import env.Env;
import io.vavr.collection.Tree;
import types.BOOL;
import types.INT;
import types.Type;

import static error.ErrorHelper.fatal;
import static error.ErrorHelper.typeMismatch;

public class ExpBin extends Exp {

   public final BinOp op;
   public final Exp left;
   public final Exp right;

   public ExpBin(Loc loc, BinOp op, Exp left, Exp right) {
      super(loc);
      this.op = op;
      this.left = left;
      this.right = right;
   }

   @Override
   public Tree.Node<String> toTree() {
      return Tree.of(annotateType("ExpBin: " + op),
                     left.toTree(),
                     right.toTree());
   }

   @Override
   protected Type semantic_(Env env) {
      left.semantic(env);
      right.semantic(env);
      switch (op) {
         // arithmetic operations
         case PLUS:
         case MINUS:
         case TIMES:
         case DIV:
         case POWER:
            if (! left.type.is(INT.T))
               throw typeMismatch(left.loc, left.type, INT.T);
            if (! right.type.is(INT.T))
               throw typeMismatch(right.loc, right.type, INT.T);
            return INT.T;

         // relational operations
         case LT:
         case LE:
         case GT:
         case GE:
            if (! left.type.is(INT.T))
               throw typeMismatch(left.loc, left.type, INT.T);
            if (! right.type.is(INT.T))
               throw typeMismatch(right.loc, right.type, INT.T);
            return BOOL.T;

         case EQ:
         case NE:
            if (left.type.is(right.type))
               return BOOL.T;
            if (right.type.is(left.type))
               return BOOL.T;
            throw typeMismatch(right.loc, right.type, left.type);

         // logical operations
         case AND:
         case OR:
            if (! left.type.is(BOOL.T))
               throw typeMismatch(left.loc, left.type, BOOL.T);
            if (! right.type.is(BOOL.T))
               throw typeMismatch(right.loc, right.type, BOOL.T);
            return BOOL.T;

         default:
            throw fatal("incomplete semantic analisys of binary operation");
      }
   }

}
