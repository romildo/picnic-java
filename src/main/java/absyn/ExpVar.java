package absyn;

import io.vavr.collection.Tree;
import parse.Loc;

public class ExpVar extends Exp {

   public final String name;

   public ExpVar(Loc loc, String name) {
      super(loc);
      this.name = name;
   }

   @Override
   public Tree.Node<String> toTree() {
      return Tree.of("ExpVar: " + name);
   }
}
