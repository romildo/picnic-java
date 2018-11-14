package absyn;

import io.vavr.collection.List;
import io.vavr.collection.Tree;
import io.vavr.render.ToTree;

public class Program extends AST {

   public final List<Fun> functions;

   public Program(List<Fun> functions) {
      this.functions = functions;
   }

   @java.lang.Override
   public Tree.Node<String> toTree() {
      return Tree.of("Program",
                     functions.map(ToTree::toTree));
   }
}
