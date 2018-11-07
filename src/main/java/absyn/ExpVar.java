package absyn;

import io.vavr.collection.Tree;

public class ExpVar extends Exp {

   public final String name;

   public ExpVar(String name) {
      this.name = name;
   }

   @Override
   public Tree.Node<String> toTree() {
      return Tree.of("ExpVar: " + name);
   }
}
