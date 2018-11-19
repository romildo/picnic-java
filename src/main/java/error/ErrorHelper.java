package error;

import absyn.Loc;

public interface ErrorHelper {

   static CompilerError error(String message) {
      return new CompilerError(message);
   }

   static CompilerError error(String format, Object... args) {
      return error(String.format(format, args));
   }

   static CompilerError error(Loc loc, String format, Object... args) {
      return new CompilerError(loc, format, args);
   }

   static FatalError fatal(String format, Object... args) {
      return new FatalError(format, args);
   }

}
