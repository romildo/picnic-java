package absyn;

import io.vavr.collection.Tree;
import parse.Loc;

public class TypeId extends AST {

   public final Ty type;
   public final String id;

   public TypeId(Loc loc, Ty type, String id) {
      super(loc);
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
