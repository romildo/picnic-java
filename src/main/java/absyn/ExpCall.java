package absyn;

import env.Entry;
import env.Env;
import env.FunEntry;
import env.Table;
import interpret.Value;
import interpret.ValueInt;
import io.vavr.collection.List;
import io.vavr.collection.Tree;
import io.vavr.control.Option;
import io.vavr.render.ToTree;
import types.Type;

import static error.ErrorHelper.*;


public class ExpCall extends Exp {

   public final String name;
   public final List<Exp> arguments;

   public ExpCall(Loc loc, String name, List<Exp> arguments) {
      super(loc);
      this.name = name;
      this.arguments = arguments;
   }

   @Override
   public Tree.Node<String> toTree() {
      return Tree.of(annotateType("ExpCall: " + name),
                     arguments.map(ToTree::toTree));
   }

   @Override
   protected Type semantic_(Env env) {
      Entry entry = env.venv.get(name);
      if (entry == null)
         throw undefined(loc, "function", name);
      if (!(entry instanceof FunEntry))
         throw notAFunction(loc, name);
      FunEntry fentry = (FunEntry) entry;
      if (arguments.size() < fentry.formals.size())
         throw tooFewArguments(loc, name);
      if (arguments.size() > fentry.formals.size())
         throw tooMuchArguments(loc, name);
      fentry.formals.zipWith(arguments,
                             (f, a) -> {
                                if (!a.semantic(env).is(f))
                                   throw typeMismatch(a.loc, a.type, f);
                                return 0;
                             });
      return fentry.result;
   }

   @Override
   public Value eval(Table<Value> memory, List<Fun> functions) {
      List<Value> args = arguments.map(a -> a.eval(memory, functions));
      Option<Fun> option = functions.find(fun -> fun.name.id == name);
      if (option.isEmpty())
         return applyPrimitive(name, args);
      Fun f = option.get();
      memory.beginScope();
      f.parameters.zipWith(args, (p, v) -> {
         memory.put(p.id, v);
         return null; });
      Value x = f.body.eval(memory, functions);
      memory.endScope();
      return x;
   }

   private Value applyPrimitive(String name, List<Value> arguments) {
      switch (name) {
         case "printint":
            System.out.println(((ValueInt)arguments.get(0)).value);
            return new ValueInt(0L);
         default:
            fatal("unknown primitive function");
            return new ValueInt(0L);
      }
   }
}
