package absyn;

import env.Env;
import io.vavr.collection.Tree;
import types.BOOL;
import types.Type;

public class ExpBool extends Exp {

   public final boolean value;

   public ExpBool(Loc loc, String value) {
      super(loc);
      this.value = new Boolean(value);
   }

   @Override
   public Tree.Node<String> toTree() {
      return Tree.of(annotateType("ExpBool: " + value));
   }

   @Override
   protected Type semantic_(Env env) {
      return BOOL.T;
   }

}
