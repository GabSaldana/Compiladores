#define NUMBER 257
#define VAR 258
#define BLTIN 259
#define INDEF 260
#define UNARYMINUS 261
typedef union {
	Symbol *sym;
	double val;
} YYSTYPE;
extern YYSTYPE yylval;
