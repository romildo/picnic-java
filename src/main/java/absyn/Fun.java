package absyn;

import io.vavr.collection.List;
import io.vavr.collection.Tree;
import io.vavr.render.ToTree;

public class Fun extends AST {

   public final TypeId name;
   public final List<TypeId> parameters;
   public final Exp body;

   public Fun(TypeId name, List<TypeId> parameters, Exp body) {
      this.name = name;
      this.parameters = parameters;
      this.body = body;
   }

   @java.lang.Override
   public Tree.Node<String> toTree() {
      return Tree.of("Fun",
                     name.toTree(),
                     Tree.of("Parameters",
                             parameters.map(ToTree::toTree)),
                     body.toTree());
   }
}
