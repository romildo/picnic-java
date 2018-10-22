package parse;

import error.ErrorHelper;

import java_cup.runtime.Symbol;

%%

%public
%final
%class Lexer
%implements Terminals
%cupsym Terminals
%cup
%line
%column
%char

%eofval{
    return tok(EOF);
%eofval}

%{
   // Lexical symbol construction
   private Symbol tok(int type, Object value) {
      return new Symbol(type, yyline+1, yycolumn+1, value);
   }

   private Symbol tok(int type) {
      return tok(type, null);
   }

   // Error handling
   private void error(String format, Object... args) {
      throw ErrorHelper.error(yyline+1, yycolumn+1,
                              "lexical error: " + format,
                              args);
   }

   // Auxiliary variables
   private int commentLevel;
%}

%state COMMENT

litint    = [0-9]+
id        = [a-zA-Z][a-zA-Z0-9_]*

%%

<YYINITIAL>{
[ \t\f\n\r]+ { /* skip */ }
"#" .*       { /* skip */ }
"{#"         { yybegin(COMMENT); commentLevel = 1; }

true         { return tok(LITBOOL, true); }
false        { return tok(LITBOOL, false); }

{litint}     { return tok(LITINT, yytext()); }

bool         { return tok(BOOL); }
int          { return tok(INT); }
if           { return tok(IF); }
then         { return tok(THEN); }
else         { return tok(ELSE); }
let          { return tok(LET); }
in           { return tok(IN); }

{id}         { return tok(ID, yytext().intern()); }

"+"          { return tok(PLUS); }
"="          { return tok(EQ); }
"("          { return tok(LPAREN); }
")"          { return tok(RPAREN); }
","          { return tok(COMMA); }
}

<COMMENT>{
"{#"         { ++commentLevel; }
"#}"         { if (--commentLevel == 0) yybegin(YYINITIAL); }
[^]          { }
<<EOF>>      { yybegin(YYINITIAL); error("unclosed comment"); }
}

.            { error("invalid character '%s'", yytext()); }
