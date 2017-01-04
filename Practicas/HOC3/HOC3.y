%{

#include "hoc.h"
extern double Pow(double,double);

%}
%union {
	Symbol *sym;
	double val;
}
%token <val> NUMBER
%token <sym> VAR BLTIN INDEF
%type <val> expr asgn
%right '='
%left '+' '-'
%left '*' '/'
%left UNARYMINUS
%right '^'

%%

list:
	/*empty*/
	|list asgn
	;
asgn:
	VAR '=' expr { $$=$1->u.val=$3; $1->type=VAR; }
	;

expr:	NUMBER
	|VAR { if($1->type == INDEF)
		puts("variable inefinida");
		$$=$1->u.val; }
	|asgn
	|BLTIN '(' expr ')' { $$=($1->u.ptr)($3); }
	|expr '^' expr { $$=Pow($1,$3); }
	;
%%
#include <stdio.h>
#include <ctype.h>
void main(){
yyparse();
}

int yylex ()
{
  	int c;

  	while ((c = getchar ()) == ' ' || c == '\t')  
  		;
 	if (c == EOF)                            
    		return 0;
  	if (c == '.' || isdigit (c))                
    	{
		double d;
      		ungetc (c, stdin);
      		scanf ("%lf", &d);
                yylval.sym=install("", NUMBER, d);
	      	return NUMBER;
    	}
	if(isalpha(c)){
		Symbol *s;
		char sbuf[200], *p=sbuf;
		do {
			*p++=c;
		} while ((c=getchar())!=EOF && isalnum(c));
		ungetc(c, stdin);
		*p='\0';
		if((s=lookup(sbuf))==(Symbol *)NULL)
			s=install(sbuf, INDEF, 0.0);
		yylval.sym=s;
		return s->type == INDEF ? VAR : s->type;
	}
  
  	return c;                                
}

void yyerror(char *s){
puts(s);
}






















