package parse;

import java_cup.runtime.Symbol;

public interface Terminals extends SymbolConstants {

   static String dumpSimpleTerminal(Symbol tok) {
      if (tok.value == null)
         return terminalNames[tok.sym];
      else
         return String.format("%s(%s)", terminalNames[tok.sym], tok.value);
   }

}
