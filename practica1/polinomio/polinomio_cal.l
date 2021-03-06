%option noyywrap
%{
#include <stdio.h>
#include <stdlib.h>
#include "polinomio_cal.h"
#include "y.tab.h"
void RmWs(char* str);
extern YYSTYPE yylval;
//NodoL *cab=NULL;
%}
/* Add your Flex definitions here              */
/* Some definitions are already provided to you*/
op [-+*/()]
ws [ \t]+
digits [0-9]
number (0|[1-9]+{digits}*)
im [i]
fr [+]
termino  {ws}*[+|-]*{ws}*{number}{ws}*x[+|-]*{ws}*{number}{ws}* 
termino1 {ws}*{number}{ws}*
polinomio {im}{termino1}*{im} { /*return POLINOMIO ;*/}
%%
{termino} {
   int coefi, expo;
   RmWs(yytext); 
   //printf("LEX (%s)", yytext);
   sscanf(yytext,"%dx%d", &coefi, &expo); 
   //printf("lex (%d, %d)", coefi, expo); 
   yylval.term=creaTermino(coefi,expo);
   return TERMINO;
}
{op} |
\n {return *yytext;}
{ws} { /* Do nothing */ } 
. { /* Do nothing */ }
%%
/* function provided to student to remove */
/* all the whitespaces from a string.     */
/* input :       a string of chars        */
/* output:       nothing                  */
/* side effect:  whitespace in the        */
/*               original string removed  */
/* return value: none                     */
void RmWs(char* str) {
  int i = 0, j = 0;
  char temp[strlen(str) + 1];
  strcpy(temp, str);
  while (temp[i] != '\0') {
    while (temp[i] == ' ')
      i++;
    str[j] = temp[i];
    i++;
    j++;
  }
 str[j] = '\0';
}

