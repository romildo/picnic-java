package absyn;

import io.vavr.collection.Tree;

public class ExpIf extends Exp {

   public final Exp test;
   public final Exp alt1;
   public final Exp alt2;

   public ExpIf(Loc loc, Exp test, Exp alt1, Exp alt2) {
      super(loc);
      this.test = test;
      this.alt1 = alt1;
      this.alt2 = alt2;
   }

   @Override
   public Tree.Node<String> toTree() {
      return Tree.of("ExpIf",
                     test.toTree(),
                     alt1.toTree(),
                     alt2.toTree());
   }
}
