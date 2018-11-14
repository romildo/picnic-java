package absyn;

import io.vavr.collection.Tree;
import parse.Loc;

public class ExpLet extends Exp {

   public final String var;
   public final Exp init;
   public final Exp body;

   public ExpLet(Loc loc, String var, Exp init, Exp body) {
      super(loc);
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
