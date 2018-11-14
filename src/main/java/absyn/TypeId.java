package absyn;

import io.vavr.collection.Tree;

public class TypeId extends AST {

   public final Ty type;
   public final String id;

   public TypeId(Ty type, String id) {
      this.type = type;
      this.id = id;
   }

   @java.lang.Override
   public Tree.Node<String> toTree() {
      return Tree.of("TypeId",
                     type.toTree(),
                     Tree.of(id));
   }
}
