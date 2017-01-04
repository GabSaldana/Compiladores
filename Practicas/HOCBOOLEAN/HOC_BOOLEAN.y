%{
#define YYSTYPE double
#include <stdio.h>
%}
  %token NUMBER
  %left '-'
  %left '^' 'v'
  
%%
list:
      | '\n'
      | list expr '\n'  { printf("%.8g\n",$2);}
      ;

  expr:
       NUMBER { $$ = $1;}
      |  expr  '^'  expr  {$$ = $1 && $3;}
      |  expr  'v'  expr  {$$ = $1 || $3;}
      |  '-' expr {$$ = !$2;}
     ;


%%
#include <stdio.h>
#include <ctype.h>
void main(){
yyparse();
}

int yylex(){

 int c;
 while((c=getchar())==" " || c== '\t');//espacios en blanco
  
   if(c== EOF)
       return 0;
   if(c== '.' || isdigit(c)){//si es un digito

        ungetc(c,stdin);
        scanf("%lf",&yylval);
        return NUMBER;//regresamos el numero(0...9)
   }
   return c;//regresamos el caracter(^,v,-)

}
void yyerror(char *s){
	puts(s);
}
