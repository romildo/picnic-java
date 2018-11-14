package absyn;

import io.vavr.collection.Tree;
import parse.Loc;

public class ExpNum extends Exp {

   public final long value;

   public ExpNum(Loc loc, String value) {
      super(loc);
      this.value = new Long(value);
   }

   @Override
   public Tree.Node<String> toTree() {
      return Tree.of("ExpNum: " + value);
   }
}
