package absyn;

import io.vavr.collection.Tree;

public class ExpNum extends Exp {

   public final long value;

   public ExpNum(String value) {
      this.value = new Long(value);
   }

   @Override
   public Tree.Node<String> toTree() {
      return Tree.of("ExpNum: " + value);
   }
}
