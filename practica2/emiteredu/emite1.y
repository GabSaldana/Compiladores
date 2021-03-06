%{
#include <stdio.h>
#include <string.h>
#include <ctype.h>
#include <malloc.h>
#include "emite.h"

#define YYSTYPE char * 

#include "y.tab.h"
int yylex(void);
void yyerror(char *s);
void warning(char *s, char *t);
void execerror(char *s, char *t);

int lineno, tmp=0;
FILE *fo;
%}
%token NUMBER

%right '='
%left '+' '-'
%left '*'
%%
list:
	| list '\n'
	| list 
	{
	   emite("org 100\n\n"); emite("section .text\n\n");
        } expr '\n' { 
	   genDataSec();
	}
	;
expr: NUMBER { $$=genNumero($1); }
	| expr '+' expr { $$=genSuma($1, $3); }
	| '(' expr ')' { $$ = $2; }
	;
%%
char *createmporal(){
      char *tmpcad=malloc(54);    
      tmp=tmp+1;
      sprintf(tmpcad,"temp%d",tmp);
      return tmpcad;     
}
void genDataSec(){
   int i;
   char s[100];

   emite("\nsection .data\n");
   for(i=0;i< tmp; i++){
      sprintf(s,"temp%d dw 0\n", i+1);
      emite(s);
   }
}
char *genNumero(char *numero){
   char s[100];
   char *temp = createmporal(); 
   strcpy(s,"mov word [");
   strcat(s, temp);
   strcat(s, "] , ");
   strcat(s, numero);
   strcat(s, "\n");
   emite(s);
   free(numero);
   return temp;
}
char *genSuma(void *op1, void *op2){
   char s[100], s1[100], s2[100];
   char *temp=NULL;              
   strcpy(s,"mov ax, word [");
   strcat(s, op1);
   strcat(s, "]\n");
   emite(s);
   strcpy(s1,"add ax, word [");
   strcat(s1, op2);
   strcat(s1, "]\n");
   emite(s1);
   temp=createmporal();
   strcpy(s2,"mov word [");
   strcat(s2, temp);
   strcat(s2, "] , ax \n");
   emite(s2);
   return temp;
}
int yylex() { 
  int c; 
   while((c=getchar()) == ' ' || c == '\t') 
   	; 
  if(c == EOF) 
  	return 0; 
  if(isdigit(c)) { 
    int val;
    ungetc(c, stdin); 
    scanf("%d",&val);
    yylval=(char *)malloc(500);
    sprintf(yylval,"%d",val);
    return NUMBER; 
   } 
   if(c == '\n')
		lineno++;
  return c; 
 } 
void execerror(char *s, char *t){ warning(s, t); }
void yyerror (char *s)  { warning(s, (char *) 0); }
void warning(char *s, char *t){
	fprintf(stderr, "%s", /*progname,*/ s);
	if (t && *t)
		fprintf(stderr, " %s", t);
	fprintf(stderr, " near line \n"/*, lineno*/);
}
void emite(char *s){
     puts(s);
     fputs(s,fo);
}
void main() { 
   fo=fopen("compi.asm","w"); 
   yyparse(); 
   fclose(fo);
}


