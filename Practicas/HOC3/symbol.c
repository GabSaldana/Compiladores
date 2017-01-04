#include  "hoc.h" 
#include  "ytab.h"

static Symbol *symlist=0;    /* tabla de simbolos: lista ligada */

Symbol *lookup(char *s)    /* encontrar s en la tabla de símbolos */
{
Symbol  *sp;
	for (sp = symlist; sp != (Symbol *)0; sp = sp->next) 
		if (!strcmp(sp->name, s)) 
			return sp;
	return 0;      /* 0 ==> no se encontró */ 
}

Symbol *install(char *s,int t, double d) /* instalar s en la tabla de símbolos */
{
	Symbol *sp;
	sp = (Symbol *) malloc(sizeof(Symbol));
	sp->name = strdup(s); /* +1 para '\0' */
	sp->type = t;
	sp->u.val = d;
	sp->next  =  symlist;   /*  poner al frente de la lista   */
	symlist =  sp; 
        return sp; 
}

