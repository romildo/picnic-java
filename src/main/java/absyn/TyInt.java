package absyn;

import io.vavr.collection.Tree;

public class TyInt extends Ty {

   public TyInt(Loc loc) {
      super(loc);
   }

   @java.lang.Override
   public Tree.Node<String> toTree() {
      return Tree.of("TyInt");
   }
}
