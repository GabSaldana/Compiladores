

%{
#define YYSTYPE double
#include <stdio.h>
%}
  %token NUMBER
  %left '+' '-'
  %left '*' '/'
%%
list:
      | '\n'
      | list expr '\n'  { printf("%.8g\n",$2);}
      ;

  expr:
       NUMBER { $$ = $1;}
      | expr '+' expr {$$ = $1 + $3;}
      | expr '-' expr {$$ = $1 - $3;}
      | expr '*' expr {$$ = $1 * $3;}
      | expr '/' expr {$$ = $1 / $3;}
     ;


%%
#include <stdio.h>
#include <ctype.h>
void main(){
yyparse();
}

int yylex(){

 int c;
 while((c=getchar())==" " || c== '\t');
  
   if(c== EOF)
       return 0;
   if(c== '.' || isdigit(c)){

        ungetc(c,stdin);
        scanf("%lf",&yylval);
        return NUMBER;
   }
   return c;

}
void yyerror(char *s){
puts(s);
}