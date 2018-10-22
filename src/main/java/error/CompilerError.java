package error;

public class CompilerError extends RuntimeException {

   public CompilerError(String message) {
      super(message);
   }

   public CompilerError(String format, Object... args) {
      this(String.format(format, args));
   }

   public CompilerError(int line, int col, String format, Object... args) {
      this(String.format("%d:%d", line, col) + " " + String.format(format, args));
   }
}
