package absyn;

import io.vavr.collection.Tree;

public class TyBool extends Ty {

   public TyBool(Loc loc) {
      super(loc);
   }

   @java.lang.Override
   public Tree.Node<String> toTree() {
      return Tree.of("TyBool");
   }
}
