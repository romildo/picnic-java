package main;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import error.CompilerError;
import java_cup.runtime.Symbol;
import parse.Lexer;
import parse.Parser;
import parse.Terminals;

import static error.ErrorHelper.fatal;

// command line options
class DriverOptions {
   @Parameter(description = "<source file>")
   public List<String> parameters = new ArrayList<>();

   @Parameter(names = {"--help", "-h"}, description = "Usage help", help = true)
   public boolean help = false;

   @Parameter(names = {"--lexer", "-l"}, description = "Lexical analysis")
   public boolean lexer = false;

   @Parameter(names = {"--parser", "-p"}, description = "Parser")
   public boolean parser = false;
}

// main
public class Driver {

   public static void main(String[] args) {
      // parse command line options
      final DriverOptions options = new DriverOptions();
      final JCommander jCommander = new JCommander(options);
      jCommander.setProgramName("Driver");

      try {
         jCommander.parse(args);
      }
      catch (ParameterException e) {
         System.out.println(e.getMessage());
         jCommander.usage();
         System.exit(1);
      }

      if (options.help) {
         jCommander.usage();
         return;
      }

      Reader input = null;
      final String name;
      try {
         // set the input (source code) to compile
         if (options.parameters.isEmpty()) {
            name = "unknown";
            input = new InputStreamReader(System.in);
         }
         else {
            name = options.parameters.get(0);
            input = new FileReader(name);
         }

         // do only lexical analyses
         if (options.lexer)
            lexicalAnalysis(name, input);
         else if (options.parser)
            syntaxAnalysis(options, name, input);

      }
      catch (CompilerError e) {
         System.out.println(e.getMessage());
         System.exit(3);
      }
      catch (IOException e) {
         System.out.println(e.getMessage());
         System.exit(2);
      }
      catch (Exception e) {
         System.out.println(e.getMessage());
         e.printStackTrace();
         System.exit(3);
      }
      finally {
         // closes the input file
         if (input instanceof FileReader)
            try {
               input.close();
            }
            catch (IOException e) {
               System.out.println(e.getMessage());
               System.exit(4);
            }
      }
   }

   private static void lexicalAnalysis(String name, Reader input) throws IOException {
      final Lexer lexer = new Lexer(input);
      Symbol tok;
      do {
         tok = (Symbol) lexer.next_token();
         System.out.println(Terminals.dumpSimpleTerminal(tok));
      } while (tok.sym != Terminals.EOF);
   }

   public static void syntaxAnalysis(DriverOptions options, String name, Reader input) throws Exception {
      final Lexer lexer = new Lexer(input);
      final Parser parser = new Parser(lexer);
      final Symbol result = parser.parse();
      System.out.println(result);
   }

}
