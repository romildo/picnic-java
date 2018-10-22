package error;

public interface ErrorHelper {

   static CompilerError error(String message) {
      return new CompilerError(message);
   }

   static CompilerError error(String format, Object... args) {
      return error(String.format(format, args));
   }

   static CompilerError error(int line, int col, String format, Object... args) {
      return new CompilerError(line, col, format, args);
   }

   static FatalError fatal(String format, Object... args) {
      return new FatalError(format, args);
   }

}
