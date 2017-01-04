#ifndef lint
static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";
#endif
#define YYBYACC 1
#line 2 "hoc.y"
#include   "hoc.h"
#define  code2(c1,c2)	    code(c1);   code(c2)
#define  code3(c1 ,c2,c3)   code(c1);   code(c2);  code(c3)

void yyerror (char *s);
int yylex ();
void warning(char *s, char *t);
void execerror(char *s, char *t);
void fpecatch(int e);
#line 12 "hoc.y"
typedef union   {
Symbol  *sym;   /* apuntador de la tabla de símbolos */ 
Inst    *inst;  /* instrucción de máquina */
} YYSTYPE;
#line 21 "y.tab.c"
#define NUMBER 257
#define PRINT 258
#define VAR 259
#define BLTIN 260
#define UNDEF 261
#define WHILE 262
#define IF 263
#define ELSE 264
#define OR 265
#define AND 266
#define GT 267
#define GE 268
#define LT 269
#define LE 270
#define EQ 271
#define NE 272
#define UNARYMINUS 273
#define NOT 274
#define YYERRCODE 256
short yylhs[] = {                                        -1,
    0,    0,    0,    0,    0,    0,    2,    1,    1,    1,
    1,    1,    1,    5,    6,    7,    8,    4,    4,    4,
    3,    3,    3,    3,    3,    3,    3,    3,    3,    3,
    3,    3,    3,    3,    3,    3,    3,    3,    3,    3,
};
short yylen[] = {                                         2,
    0,    2,    3,    3,    3,    3,    3,    1,    2,    4,
    4,    7,    3,    3,    1,    1,    0,    0,    2,    2,
    1,    1,    1,    4,    3,    3,    3,    3,    3,    3,
    2,    3,    3,    3,    3,    3,    3,    3,    3,    2,
};
short yydefred[] = {                                      1,
    0,    0,   21,    0,    0,    0,   15,   16,    0,    2,
   18,    0,    0,    0,    0,    0,    0,    0,    6,   23,
    0,    0,    0,    0,    0,    0,    0,    4,    3,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    5,    0,    0,    0,    0,    0,    0,    0,   19,   13,
   20,    0,   25,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   17,   17,   24,
   14,   10,    0,    0,   17,   12,
};
short yydgoto[] = {                                       1,
   14,   20,   52,   25,   45,   17,   18,   72,
};
short yysindex[] = {                                      0,
  513,    5,    0,  -39,  -51,  -23,    0,    0,  -39,    0,
    0,  -39,  -39,   10,   11,  488,  -18,  -18,    0,    0,
  130,  -39,  -39,  -37,  531,  564,  -37,    0,    0,  -39,
  -39,  -39,  -39,  -39,  -39,  -39,  -39,  -39,  -39,  -39,
    0,  -39,  -39,  -39,  -29,  -29,  130,  721,    0,    0,
    0,  130,    0,  572,  138,  -40,  -40,  -40,  -40,  -40,
  -40,  -33,  -37,  -37,  130,  130,  766,    0,    0,    0,
    0,    0, -241,  -29,    0,    0,
};
short yyrindex[] = {                                      0,
    0,    0,    0,    0,  -10,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  148,    0,    0,    0,    0,    0,
  538,    0,    0,    8,    0,    0,   26,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  461,    0,    0,    0,
    0,  563,    0,  452,  439,  100,  118,  176,  363,  381,
  413,   82,   46,   64,  474,  487,    0,    0,    0,    0,
    0,    0,  655,    0,    0,    0,
};
short yygindex[] = {                                      0,
  -21,   25,  844,    0,    9,    0,    0,  -56,
};
#define YYTABLESIZE 1038
short yytable[] = {                                      22,
   12,   39,   38,   51,   42,   13,   40,   42,   39,   22,
   12,   42,   73,   40,   19,   13,   23,   40,   76,   28,
   29,   44,   74,   68,   69,   15,   46,    0,    0,   22,
   22,   22,   22,    0,   22,   31,   22,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   40,   40,   40,
   40,    0,   75,   43,   40,   28,   43,    0,    0,    0,
   43,    0,    0,    0,    0,   31,   31,   31,   31,    0,
    0,    0,   31,   29,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   22,    0,   28,   28,   28,   28,    0,
    0,   26,   28,   11,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   29,   29,   29,   29,    0,    0,   32,
   29,    0,   22,    0,   22,    0,    0,    0,    0,    0,
    0,   26,   26,    0,   26,    0,    0,   33,    0,    0,
   40,    0,   40,    0,    0,    0,    0,    0,    0,   32,
   32,    0,    0,    0,    0,    0,    0,    0,   31,    0,
   31,    0,    0,    0,    0,    0,    0,   33,   33,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   28,    0,
   28,   39,   38,    0,   42,    0,   40,    0,    0,   39,
   38,    0,   42,    0,   40,   34,   29,    0,   29,   23,
   23,    0,   23,    0,   23,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   26,    0,   26,    0,    0,    0,
    0,    0,    0,    0,    0,   34,   34,    3,    0,    5,
    6,    0,   32,   43,   32,    0,    0,    3,    4,    5,
    6,   43,    7,    8,    9,    0,    0,    0,    0,    0,
   33,   23,   33,    0,    9,    0,   22,   22,   22,   22,
    0,   22,   22,   22,   22,   22,   22,   22,   22,   22,
   22,   22,    0,   22,   40,   40,   40,   40,    0,   40,
   40,   40,   40,   40,   40,   40,   40,   40,   40,   40,
    0,   40,   31,   31,   31,   31,    0,   31,   31,   31,
   31,   31,   31,   31,   31,   31,   31,   31,   34,   31,
   34,    0,   28,   28,   28,   28,    0,   28,   28,   28,
   28,   28,   28,   28,   28,   28,   28,   28,    0,   28,
   29,   29,   29,   29,    0,   29,   29,   29,   29,   29,
   29,   29,   29,   29,   29,   29,    0,   29,   26,   26,
   26,   26,    0,   26,   26,   26,   26,   26,   26,   26,
   26,   26,   26,   26,    0,   26,   32,   32,   32,   32,
    0,   32,   32,   32,   32,   32,   32,   32,   32,   32,
   32,   32,   35,   32,   33,   33,   33,   33,    0,   33,
   33,   33,   33,   33,   33,   33,   33,   33,   33,   33,
   36,   33,    0,    0,   30,   31,   32,   33,   34,   35,
   36,   37,   35,   35,   32,   33,   34,   35,   36,   37,
    0,    0,   23,   23,   23,   23,   23,   23,   23,   23,
   36,   36,   37,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   34,   34,   34,   34,    0,   34,   34,   34,
   34,   34,   34,   34,   34,   34,   34,   34,   38,   34,
    0,    0,   37,   37,    0,    0,    0,    0,    0,    0,
    0,   39,    0,    0,    0,    0,    0,    0,    0,    0,
    7,    0,    0,    0,    0,    0,    0,    0,   38,   38,
    0,    0,    0,   27,    0,   35,    0,   35,    0,    0,
    0,   39,   39,    0,    0,    0,   30,   41,    0,    0,
    7,    7,    0,   36,    0,   36,    0,    0,    0,    0,
    0,    0,    0,   27,   27,    0,    0,    0,    0,    0,
    0,    0,   10,    0,    0,    0,   30,   30,    0,   39,
   38,    0,   42,    0,   40,   37,    0,   37,    0,    0,
   49,    0,    0,    0,    0,    0,    0,    9,    0,    0,
    0,    0,   12,    0,    0,    0,    0,   13,    0,    0,
    0,   38,    0,   38,    0,    0,    0,    0,    0,    0,
   12,    0,    8,    0,   39,   13,   39,    9,    0,    0,
    0,   43,    0,    7,    0,    7,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   27,    0,   27,    0,
    0,    0,    8,    0,   53,   39,   38,    0,   42,   30,
   40,   30,    0,   39,   38,    0,   42,    0,   40,   35,
   35,   35,   35,    0,   35,   35,   35,   35,   35,   35,
   35,   35,   35,   35,   35,   11,   35,   36,   36,   36,
   36,    0,   36,   36,   36,   36,   36,   36,   36,   36,
   36,   36,   36,   11,   36,   50,    0,   43,    0,    0,
    9,    0,    9,    0,   11,   43,    0,    0,    0,   37,
   37,   37,   37,    0,   37,   37,   37,   37,   37,   37,
   37,   37,   37,   37,   37,    8,   37,    8,    0,    0,
    0,    0,    0,    0,   11,   38,   38,   38,   38,   11,
   38,   38,   38,   38,   38,    0,    0,    0,   39,   39,
   39,   39,   38,   39,   39,   39,   39,    7,    7,    7,
    7,    0,    7,    7,    7,   39,    0,    0,    0,    0,
   27,   27,   27,   27,    7,   27,   27,   27,    0,    0,
    0,    0,    0,   30,   30,   30,   30,   27,   30,   30,
   30,    0,   30,   31,   32,   33,   34,   35,   36,   37,
   30,   70,   39,   38,    0,   42,    0,   40,    2,    3,
    4,    5,    6,    0,    7,    8,    0,   11,    0,   11,
    0,    0,    0,    0,    0,    0,    9,    3,    4,    5,
    6,    0,    7,    8,    9,    9,    9,    9,    0,    9,
    9,    9,    0,    0,    9,    0,   71,   39,   38,    0,
   42,    9,   40,    0,   43,    0,    0,    0,    0,    8,
    8,    8,    8,    0,    8,    8,    8,    0,   30,   31,
   32,   33,   34,   35,   36,   37,    8,   31,   32,   33,
   34,   35,   36,   37,   16,    0,    0,   21,    0,    0,
    0,    0,   24,    0,    0,   26,   27,    0,    0,   43,
    0,    0,    0,    0,    0,   47,   48,    0,    0,    0,
    0,    0,    0,   54,   55,   56,   57,   58,   59,   60,
   61,   62,   63,   64,    0,   65,   66,   67,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   11,   11,   11,   11,    0,   11,   11,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   11,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   30,   31,   32,   33,   34,
   35,   36,   37,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   30,   31,   32,   33,   34,   35,   36,   37,
};
short yycheck[] = {                                      10,
   40,   42,   43,   25,   45,   45,   47,   45,   42,   61,
   40,   45,   69,   47,   10,   45,   40,   10,   75,   10,
   10,   40,  264,   45,   46,    1,   18,   -1,   -1,   40,
   41,   42,   43,   -1,   45,   10,   47,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   40,   41,   42,
   43,   -1,   74,   94,   47,   10,   94,   -1,   -1,   -1,
   94,   -1,   -1,   -1,   -1,   40,   41,   42,   43,   -1,
   -1,   -1,   47,   10,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   94,   -1,   40,   41,   42,   43,   -1,
   -1,   10,   47,  123,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   40,   41,   42,   43,   -1,   -1,   10,
   47,   -1,  123,   -1,  125,   -1,   -1,   -1,   -1,   -1,
   -1,   40,   41,   -1,   43,   -1,   -1,   10,   -1,   -1,
  123,   -1,  125,   -1,   -1,   -1,   -1,   -1,   -1,   40,
   41,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  123,   -1,
  125,   -1,   -1,   -1,   -1,   -1,   -1,   40,   41,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  123,   -1,
  125,   42,   43,   -1,   45,   -1,   47,   -1,   -1,   42,
   43,   -1,   45,   -1,   47,   10,  123,   -1,  125,   42,
   43,   -1,   45,   -1,   47,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  123,   -1,  125,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   40,   41,  257,   -1,  259,
  260,   -1,  123,   94,  125,   -1,   -1,  257,  258,  259,
  260,   94,  262,  263,  274,   -1,   -1,   -1,   -1,   -1,
  123,   94,  125,   -1,  274,   -1,  257,  258,  259,  260,
   -1,  262,  263,  264,  265,  266,  267,  268,  269,  270,
  271,  272,   -1,  274,  257,  258,  259,  260,   -1,  262,
  263,  264,  265,  266,  267,  268,  269,  270,  271,  272,
   -1,  274,  257,  258,  259,  260,   -1,  262,  263,  264,
  265,  266,  267,  268,  269,  270,  271,  272,  123,  274,
  125,   -1,  257,  258,  259,  260,   -1,  262,  263,  264,
  265,  266,  267,  268,  269,  270,  271,  272,   -1,  274,
  257,  258,  259,  260,   -1,  262,  263,  264,  265,  266,
  267,  268,  269,  270,  271,  272,   -1,  274,  257,  258,
  259,  260,   -1,  262,  263,  264,  265,  266,  267,  268,
  269,  270,  271,  272,   -1,  274,  257,  258,  259,  260,
   -1,  262,  263,  264,  265,  266,  267,  268,  269,  270,
  271,  272,   10,  274,  257,  258,  259,  260,   -1,  262,
  263,  264,  265,  266,  267,  268,  269,  270,  271,  272,
   10,  274,   -1,   -1,  265,  266,  267,  268,  269,  270,
  271,  272,   40,   41,  267,  268,  269,  270,  271,  272,
   -1,   -1,  265,  266,  267,  268,  269,  270,  271,  272,
   40,   41,   10,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  257,  258,  259,  260,   -1,  262,  263,  264,
  265,  266,  267,  268,  269,  270,  271,  272,   10,  274,
   -1,   -1,   40,   41,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   10,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   10,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   40,   41,
   -1,   -1,   -1,   10,   -1,  123,   -1,  125,   -1,   -1,
   -1,   40,   41,   -1,   -1,   -1,   10,   10,   -1,   -1,
   40,   41,   -1,  123,   -1,  125,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   40,   41,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   10,   -1,   -1,   -1,   40,   41,   -1,   42,
   43,   -1,   45,   -1,   47,  123,   -1,  125,   -1,   -1,
   10,   -1,   -1,   -1,   -1,   -1,   -1,   10,   -1,   -1,
   -1,   -1,   40,   -1,   -1,   -1,   -1,   45,   -1,   -1,
   -1,  123,   -1,  125,   -1,   -1,   -1,   -1,   -1,   -1,
   40,   -1,   10,   -1,  123,   45,  125,   40,   -1,   -1,
   -1,   94,   -1,  123,   -1,  125,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  123,   -1,  125,   -1,
   -1,   -1,   40,   -1,   41,   42,   43,   -1,   45,  123,
   47,  125,   -1,   42,   43,   -1,   45,   -1,   47,  257,
  258,  259,  260,   -1,  262,  263,  264,  265,  266,  267,
  268,  269,  270,  271,  272,  123,  274,  257,  258,  259,
  260,   -1,  262,  263,  264,  265,  266,  267,  268,  269,
  270,  271,  272,  123,  274,  125,   -1,   94,   -1,   -1,
  123,   -1,  125,   -1,   10,   94,   -1,   -1,   -1,  257,
  258,  259,  260,   -1,  262,  263,  264,  265,  266,  267,
  268,  269,  270,  271,  272,  123,  274,  125,   -1,   -1,
   -1,   -1,   -1,   -1,   40,  257,  258,  259,  260,   45,
  262,  263,  264,  265,  266,   -1,   -1,   -1,  257,  258,
  259,  260,  274,  262,  263,  264,  265,  257,  258,  259,
  260,   -1,  262,  263,  264,  274,   -1,   -1,   -1,   -1,
  257,  258,  259,  260,  274,  262,  263,  264,   -1,   -1,
   -1,   -1,   -1,  257,  258,  259,  260,  274,  262,  263,
  264,   -1,  265,  266,  267,  268,  269,  270,  271,  272,
  274,   41,   42,   43,   -1,   45,   -1,   47,  256,  257,
  258,  259,  260,   -1,  262,  263,   -1,  123,   -1,  125,
   -1,   -1,   -1,   -1,   -1,   -1,  274,  257,  258,  259,
  260,   -1,  262,  263,  257,  258,  259,  260,   -1,  262,
  263,  264,   -1,   -1,  274,   -1,   41,   42,   43,   -1,
   45,  274,   47,   -1,   94,   -1,   -1,   -1,   -1,  257,
  258,  259,  260,   -1,  262,  263,  264,   -1,  265,  266,
  267,  268,  269,  270,  271,  272,  274,  266,  267,  268,
  269,  270,  271,  272,    1,   -1,   -1,    4,   -1,   -1,
   -1,   -1,    9,   -1,   -1,   12,   13,   -1,   -1,   94,
   -1,   -1,   -1,   -1,   -1,   22,   23,   -1,   -1,   -1,
   -1,   -1,   -1,   30,   31,   32,   33,   34,   35,   36,
   37,   38,   39,   40,   -1,   42,   43,   44,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  257,  258,  259,  260,   -1,  262,  263,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  274,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  265,  266,  267,  268,  269,
  270,  271,  272,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  265,  266,  267,  268,  269,  270,  271,  272,
};
#define YYFINAL 1
#ifndef YYDEBUG
#define YYDEBUG 0
#endif
#define YYMAXTOKEN 274
#if YYDEBUG
char *yyname[] = {
"end-of-file",0,0,0,0,0,0,0,0,0,"'\\n'",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,"'('","')'","'*'","'+'",0,"'-'",0,"'/'",0,0,0,0,0,0,0,0,0,0,0,
0,0,"'='",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,"'^'",
"'_'",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,"'{'",0,"'}'",0,0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,"NUMBER","PRINT","VAR","BLTIN","UNDEF","WHILE","IF","ELSE","OR",
"AND","GT","GE","LT","LE","EQ","NE","UNARYMINUS","NOT",
};
char *yyrule[] = {
"$accept : list",
"list :",
"list : list '\\n'",
"list : list asgn '\\n'",
"list : list stmt '\\n'",
"list : list expr '\\n'",
"list : list error '\\n'",
"asgn : VAR '=' expr",
"stmt : expr",
"stmt : PRINT expr",
"stmt : while cond stmt end",
"stmt : if cond stmt end",
"stmt : if cond stmt end ELSE stmt end",
"stmt : '{' stmtlist '}'",
"cond : '(' expr ')'",
"while : WHILE",
"if : IF",
"end :",
"stmtlist :",
"stmtlist : stmtlist '\\n'",
"stmtlist : stmtlist stmt",
"expr : NUMBER",
"expr : VAR",
"expr : asgn",
"expr : BLTIN '(' expr ')'",
"expr : '(' expr ')'",
"expr : expr '+' expr",
"expr : expr '-' expr",
"expr : expr '*' expr",
"expr : expr '/' expr",
"expr : expr '^' expr",
"expr : '-' expr",
"expr : expr GT expr",
"expr : expr GE expr",
"expr : expr LT expr",
"expr : expr LE expr",
"expr : expr EQ expr",
"expr : expr NE expr",
"expr : expr AND expr",
"expr : expr OR expr",
"expr : NOT expr",
};
#endif
#define yyclearin (yychar=(-1))
#define yyerrok (yyerrflag=0)
#ifdef YYSTACKSIZE
#ifndef YYMAXDEPTH
#define YYMAXDEPTH YYSTACKSIZE
#endif
#else
#ifdef YYMAXDEPTH
#define YYSTACKSIZE YYMAXDEPTH
#else
#define YYSTACKSIZE 500
#define YYMAXDEPTH 500
#endif
#endif
int yydebug;
int yynerrs;
int yyerrflag;
int yychar;
short *yyssp;
YYSTYPE *yyvsp;
YYSTYPE yyval;
YYSTYPE yylval;
short yyss[YYSTACKSIZE];
YYSTYPE yyvs[YYSTACKSIZE];
#define yystacksize YYSTACKSIZE
#line 87 "hoc.y"
#include <stdio.h>
#include <ctype.h>
#include <signal.h>
#include <setjmp.h>

jmp_buf begin;

char *progname;
int lineno = 1;

main(int argc, char **argv)       /* hoc5 */ 
{
/*int  fpecatch();*/
progname  =  argv[0];
init( );
setjmp(begin);
signal(SIGFPE, fpecatch);
for (initcode(); yyparse(); initcode() )
execute(prog); 
return 0; 
}

yylex()
{
int c;
while  ((c=getchar()) ==  ' ' ||  c ==   '\t')
          ;
if (c == EOF)
	return 0; 
if (c == '.' || isdigit(c)) {   /* número */
	double d;
	ungetc(c, stdin); 
	scanf("%lf", &d);
	yylval.sym = install("", NUMBER, d);
	return NUMBER; 
}
if (isalpha(c)) { 
	Symbol *s;
	char sbuf[100], *p = sbuf; 
	do {
		if (p >= sbuf + sizeof(sbuf) - 1) { 
			*p = '\0'; 
			execerror("name too long", sbuf);
		}
	*p++ = c;
	} while ((c=getc(stdin)) != EOF && isalnum(c)); 
	ungetc(c, stdin); 
	*p = '\0'; 
	if ((s=lookup(sbuf)) == 0)
       		s=install(sbuf, UNDEF, 0.0); 
	yylval.sym = s;
	return s->type == UNDEF ? VAR : s->type;
}
	switch (c) {
	case '>':                return follow('=', GE, GT);
	case '<':                return follow('=', LE, LT);
	case '=':                return follow('=', EQ, '=');
	case '!':                return follow('=', NE, NOT);
	case '|':                return follow('|', OR, '|');
	case '&':                return follow('&', AND, '&');
	case '\n':              lineno++; return '\n';
	default:                  return c; 
        }
}

follow(expect,   ifyes,   ifno)  /*   buscar  >=, etc.   */ 
{
int c  = getchar();
if  (c  ==  expect)
return ifyes; 
ungetc(c,   stdin); 
return  ifno;
}

void yyerror(char *s)      /* comunicar errores de tiempo de compilación */
{
warning(s, (char *)0); 
} 

void execerror(char *s, char *t)
{
	warning(s, t);
	longjmp(begin, 0);
}

void fpecatch(int e)      /* detectar errores por punto flotante */ 
{
execerror("floating point exception", (char *) 0); 
}	

void warning(char *s, char *t)
{
	fprintf (stderr, "%s: %s", progname, s);
	if(t)
		fprintf (stderr, " %s", t);
	fprintf (stderr, "cerca de la linea %d\n", lineno);
}



	
#line 490 "y.tab.c"
#define YYABORT goto yyabort
#define YYACCEPT goto yyaccept
#define YYERROR goto yyerrlab
int
yyparse()
{
    register int yym, yyn, yystate;
#if YYDEBUG
    register char *yys;
    extern char *getenv();

    if (yys = getenv("YYDEBUG"))
    {
        yyn = *yys;
        if (yyn >= '0' && yyn <= '9')
            yydebug = yyn - '0';
    }
#endif

    yynerrs = 0;
    yyerrflag = 0;
    yychar = (-1);

    yyssp = yyss;
    yyvsp = yyvs;
    *yyssp = yystate = 0;

yyloop:
    if (yyn = yydefred[yystate]) goto yyreduce;
    if (yychar < 0)
    {
        if ((yychar = yylex()) < 0) yychar = 0;
#if YYDEBUG
        if (yydebug)
        {
            yys = 0;
            if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
            if (!yys) yys = "illegal-symbol";
            printf("yydebug: state %d, reading %d (%s)\n", yystate,
                    yychar, yys);
        }
#endif
    }
    if ((yyn = yysindex[yystate]) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
    {
#if YYDEBUG
        if (yydebug)
            printf("yydebug: state %d, shifting to state %d (%s)\n",
                    yystate, yytable[yyn],yyrule[yyn]);
#endif
        if (yyssp >= yyss + yystacksize - 1)
        {
            goto yyoverflow;
        }
        *++yyssp = yystate = yytable[yyn];
        *++yyvsp = yylval;
        yychar = (-1);
        if (yyerrflag > 0)  --yyerrflag;
        goto yyloop;
    }
    if ((yyn = yyrindex[yystate]) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
    {
        yyn = yytable[yyn];
        goto yyreduce;
    }
    if (yyerrflag) goto yyinrecovery;
#ifdef lint
    goto yynewerror;
#endif
yynewerror:
    yyerror("syntax error");
#ifdef lint
    goto yyerrlab;
#endif
yyerrlab:
    ++yynerrs;
yyinrecovery:
    if (yyerrflag < 3)
    {
        yyerrflag = 3;
        for (;;)
        {
            if ((yyn = yysindex[*yyssp]) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
#if YYDEBUG
                if (yydebug)
                    printf("yydebug: state %d, error recovery shifting\
 to state %d\n", *yyssp, yytable[yyn]);
#endif
                if (yyssp >= yyss + yystacksize - 1)
                {
                    goto yyoverflow;
                }
                *++yyssp = yystate = yytable[yyn];
                *++yyvsp = yylval;
                goto yyloop;
            }
            else
            {
#if YYDEBUG
                if (yydebug)
                    printf("yydebug: error recovery discarding state %d\n",
                            *yyssp);
#endif
                if (yyssp <= yyss) goto yyabort;
                --yyssp;
                --yyvsp;
            }
        }
    }
    else
    {
        if (yychar == 0) goto yyabort;
#if YYDEBUG
        if (yydebug)
        {
            yys = 0;
            if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
            if (!yys) yys = "illegal-symbol";
            printf("yydebug: state %d, error recovery discards token %d (%s)\n",
                    yystate, yychar, yys);
        }
#endif
        yychar = (-1);
        goto yyloop;
    }
yyreduce:
#if YYDEBUG
    if (yydebug)
        printf("yydebug: state %d, reducing by rule %d (%s)\n",
                yystate, yyn, yyrule[yyn]);
#endif
    yym = yylen[yyn];
    yyval = yyvsp[1-yym];
    switch (yyn)
    {
case 3:
#line 31 "hoc.y"
{ code2(pop, STOP); return 1; }
break;
case 4:
#line 32 "hoc.y"
{ code(STOP); return 1; }
break;
case 5:
#line 33 "hoc.y"
{ code2(print, STOP); return 1; }
break;
case 6:
#line 34 "hoc.y"
{ yyerrok; }
break;
case 7:
#line 36 "hoc.y"
{ yyval.inst=yyvsp[0].inst; code3(varpush,(Inst)yyvsp[-2].sym,assign); }
break;
case 8:
#line 38 "hoc.y"
{ code(pop); }
break;
case 9:
#line 39 "hoc.y"
{ code(prexpr); yyval.inst = yyvsp[0].inst;}
break;
case 10:
#line 40 "hoc.y"
{
		(yyvsp[-3].inst)[1] = (Inst)yyvsp[-1].inst;     /* cuerpo de la iteración */ 
		(yyvsp[-3].inst)[2] = (Inst)yyvsp[0].inst; }
break;
case 11:
#line 43 "hoc.y"
{    /* proposición if que no emplea else */ 
		(yyvsp[-3].inst)[1] = (Inst)yyvsp[-1].inst;     /* parte then */ 
		(yyvsp[-3].inst)[3] = (Inst)yyvsp[0].inst; }
break;
case 12:
#line 46 "hoc.y"
{  /* proposición if con parte else */
		(yyvsp[-6].inst)[1]   =   (Inst)yyvsp[-4].inst;	/*  parte then  */
		(yyvsp[-6].inst)[2]   =   (Inst)yyvsp[-1].inst;	/* parte else   */
		(yyvsp[-6].inst)[3]   =   (Inst)yyvsp[0].inst;   }
break;
case 13:
#line 50 "hoc.y"
{   yyval.inst  =  yyvsp[-1].inst;   }
break;
case 14:
#line 52 "hoc.y"
{   code(STOP);  yyval.inst=  yyvsp[-1].inst;   }
break;
case 15:
#line 54 "hoc.y"
{   yyval.inst   =   code3(whilecode,STOP,STOP); }
break;
case 16:
#line 56 "hoc.y"
{ yyval.inst=code(ifcode); code3(STOP, STOP, STOP); }
break;
case 17:
#line 58 "hoc.y"
{ code(STOP); yyval.inst = progp; }
break;
case 18:
#line 60 "hoc.y"
{ yyval.inst = progp; }
break;
case 21:
#line 64 "hoc.y"
{ yyval.inst = code2(constpush, (Inst)yyvsp[0].sym); }
break;
case 22:
#line 65 "hoc.y"
{  yyval.inst = code3(varpush, (Inst)yyvsp[0].sym, eval);}
break;
case 24:
#line 68 "hoc.y"
{ yyval.inst = yyvsp[-1].inst; code2(bltin,(Inst)yyvsp[-3].sym->u.ptr); }
break;
case 25:
#line 69 "hoc.y"
{ yyval.inst = yyvsp[-1].inst; }
break;
case 26:
#line 70 "hoc.y"
{ code(add); }
break;
case 27:
#line 71 "hoc.y"
{ code(sub); }
break;
case 28:
#line 72 "hoc.y"
{ code(mul); }
break;
case 29:
#line 73 "hoc.y"
{ code(div); }
break;
case 30:
#line 74 "hoc.y"
{ code (power); }
break;
case 31:
#line 75 "hoc.y"
{ yyval.inst = yyvsp[0].inst; code(negate); }
break;
case 32:
#line 76 "hoc.y"
{ code(gt); }
break;
case 33:
#line 77 "hoc.y"
{ code(ge); }
break;
case 34:
#line 78 "hoc.y"
{ code(lt); }
break;
case 35:
#line 79 "hoc.y"
{ code(le); }
break;
case 36:
#line 80 "hoc.y"
{ code (eq); }
break;
case 37:
#line 81 "hoc.y"
{ code(ne); }
break;
case 38:
#line 82 "hoc.y"
{ code(and); }
break;
case 39:
#line 83 "hoc.y"
{ code(or); }
break;
case 40:
#line 84 "hoc.y"
{ yyval.inst = yyvsp[0].inst; code(not); }
break;
#line 777 "y.tab.c"
    }
    yyssp -= yym;
    yystate = *yyssp;
    yyvsp -= yym;
    yym = yylhs[yyn];
    if (yystate == 0 && yym == 0)
    {
#if YYDEBUG
        if (yydebug)
            printf("yydebug: after reduction, shifting from state 0 to\
 state %d\n", YYFINAL);
#endif
        yystate = YYFINAL;
        *++yyssp = YYFINAL;
        *++yyvsp = yyval;
        if (yychar < 0)
        {
            if ((yychar = yylex()) < 0) yychar = 0;
#if YYDEBUG
            if (yydebug)
            {
                yys = 0;
                if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
                if (!yys) yys = "illegal-symbol";
                printf("yydebug: state %d, reading %d (%s)\n",
                        YYFINAL, yychar, yys);
            }
#endif
        }
        if (yychar == 0) goto yyaccept;
        goto yyloop;
    }
    if ((yyn = yygindex[yym]) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn];
    else
        yystate = yydgoto[yym];
#if YYDEBUG
    if (yydebug)
        printf("yydebug: after reduction, shifting from state %d \
to state %d\n", *yyssp, yystate);
#endif
    if (yyssp >= yyss + yystacksize - 1)
    {
        goto yyoverflow;
    }
    *++yyssp = yystate;
    *++yyvsp = yyval;
    goto yyloop;
yyoverflow:
    yyerror("yacc stack overflow");
yyabort:
    return (1);
yyaccept:
    return (0);
}
