package absyn;

import io.vavr.collection.Tree;

public class ExpLet extends Exp {

   public final String var;
   public final Exp init;
   public final Exp body;

   public ExpLet(String var, Exp init, Exp body) {
      this.var = var;
      this.init = init;
      this.body = body;
   }

   @Override
   public Tree.Node<String> toTree() {
      return Tree.of("ExpLet",
                     Tree.of(var),
                     init.toTree(),
                     body.toTree());
   }
}
