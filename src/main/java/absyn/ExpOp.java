package absyn;

import io.vavr.collection.Tree;
import parse.Loc;

public class ExpOp extends Exp {

   public final Operator op;
   public final Exp left;
   public final Exp right;

   public ExpOp(Loc loc, Operator op, Exp left, Exp right) {
      super(loc);
      this.op = op;
      this.left = left;
      this.right = right;
   }

   @Override
   public Tree.Node<String> toTree() {
      return Tree.of("ExpOp: " + op,
                     left.toTree(),
                     right.toTree());
   }
}
