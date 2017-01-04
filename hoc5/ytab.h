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
typedef union   {
Symbol  *sym;   /* apuntador de la tabla de símbolos */ 
Inst    *inst;  /* instrucción de máquina */
} YYSTYPE;
extern YYSTYPE yylval;
