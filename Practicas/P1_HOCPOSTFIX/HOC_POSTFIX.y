%{
#define YYSTYPE double
#include <stdio.h>
%}

%token NUMBER
%left '+' '-'
%left '*' '/'

%% /* Grammar rules and actions follow */
input:    /* empty */
        | input line
;

line:     '\n'
        | exp '\n'  { printf("%.8g\n",$1);}
;

exp:      NUMBER             { $$ = $1; }
        | exp exp '+'     { $$ = $1 + $2;}
        | exp exp '-'     { $$ = $1 - $2;}
        | exp exp '*'     { $$ = $1 * $2;}
        | exp exp '/'     { $$ = $1 / $2;}
        ;
%%
/* Lexical analyzer returns a double floating point 
   number on the stack and the token NUM, or the ASCII
   character read if not a number.  Skips all blanks
   and tabs, returns 0 for EOF. */

#include <ctype.h>
#include <stdio.h>

void main(){
yyparse();
}

yylex ()
{
  int c;

  /* skip white space  */
  while ((c = getchar ()) == ' ' || c == '\t')  
    ;
  /* process numbers   */
  if (c == '.' || isdigit (c))                
    {
      ungetc (c, stdin);
      scanf ("%lf", &yylval);
      return NUMBER;
    }
  /* return end-of-file  */
  if (c == EOF)                            
    return 0;
  /* return single chars */
  return c;                                
}
void yyerror(char *s){
puts(s);
}