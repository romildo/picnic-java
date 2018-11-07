package absyn;

import io.vavr.collection.List;
import io.vavr.collection.Tree;
import io.vavr.render.ToTree;

public class ExpCall extends Exp {

   public final String name;
   public final List<Exp> args;

   public ExpCall(String name, List<Exp> args) {
      this.name = name;
      this.args = args;
   }

   @Override
   public Tree.Node<String> toTree() {
      return Tree.of("ExpCall: " + name,
                     args.map(ToTree::toTree));
   }
}
