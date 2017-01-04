//### This file created by BYACC 1.8(/Java extension  1.15)
//### Java capabilities added 7 Jan 97, Bob Jamison
//### Updated : 27 Nov 97  -- Bob Jamison, Joe Nieten
//###           01 Jan 98  -- Bob Jamison -- fixed generic semantic constructor
//###           01 Jun 99  -- Bob Jamison -- added Runnable support
//###           06 Aug 00  -- Bob Jamison -- made state variables class-global
//###           03 Jan 01  -- Bob Jamison -- improved flags, tracing
//###           16 May 01  -- Bob Jamison -- added custom stack sizing
//###           04 Mar 02  -- Yuval Oren  -- improved java performance, added options
//###           14 Mar 02  -- Tomas Hurka -- -d support, static initializer workaround
//### Please send bug reports to tom@hukatronic.cz
//### static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";






//#line 1 "forma.y"

import java.lang.Math;
import java.io.*;
import java.util.StringTokenizer;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
//#line 25 "Parser.java"




public class Parser
{

boolean yydebug;        //do I want debug output?
int yynerrs;            //number of errors so far
int yyerrflag;          //was there an error?
int yychar;             //the current working character

//########## MESSAGES ##########
//###############################################################
// method: debug
//###############################################################
void debug(String msg)
{
  if (yydebug)
    System.out.println(msg);
}

//########## STATE STACK ##########
final static int YYSTACKSIZE = 500;  //maximum stack size
int statestk[] = new int[YYSTACKSIZE]; //state stack
int stateptr;
int stateptrmax;                     //highest index of stackptr
int statemax;                        //state when highest index reached
//###############################################################
// methods: state stack push,pop,drop,peek
//###############################################################
final void state_push(int state)
{
  try {
		stateptr++;
		statestk[stateptr]=state;
	 }
	 catch (ArrayIndexOutOfBoundsException e) {
     int oldsize = statestk.length;
     int newsize = oldsize * 2;
     int[] newstack = new int[newsize];
     System.arraycopy(statestk,0,newstack,0,oldsize);
     statestk = newstack;
     statestk[stateptr]=state;
  }
}
final int state_pop()
{
  return statestk[stateptr--];
}
final void state_drop(int cnt)
{
  stateptr -= cnt; 
}
final int state_peek(int relative)
{
  return statestk[stateptr-relative];
}
//###############################################################
// method: init_stacks : allocate and prepare stacks
//###############################################################
final boolean init_stacks()
{
  stateptr = -1;
  val_init();
  return true;
}
//###############################################################
// method: dump_stacks : show n levels of the stacks
//###############################################################
void dump_stacks(int count)
{
int i;
  System.out.println("=index==state====value=     s:"+stateptr+"  v:"+valptr);
  for (i=0;i<count;i++)
    System.out.println(" "+i+"    "+statestk[i]+"      "+valstk[i]);
  System.out.println("======================");
}


//########## SEMANTIC VALUES ##########
//public class ParserVal is defined in ParserVal.java


String   yytext;//user variable to return contextual strings
ParserVal yyval; //used to return semantic vals from action routines
ParserVal yylval;//the 'lval' (result) I got from yylex()
ParserVal valstk[];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
void val_init()
{
  valstk=new ParserVal[YYSTACKSIZE];
  yyval=new ParserVal();
  yylval=new ParserVal();
  valptr=-1;
}
void val_push(ParserVal val)
{
  if (valptr>=YYSTACKSIZE)
    return;
  valstk[++valptr]=val;
}
ParserVal val_pop()
{
  if (valptr<0)
    return new ParserVal();
  return valstk[valptr--];
}
void val_drop(int cnt)
{
int ptr;
  ptr=valptr-cnt;
  if (ptr<0)
    return;
  valptr = ptr;
}
ParserVal val_peek(int relative)
{
int ptr;
  ptr=valptr-relative;
  if (ptr<0)
    return new ParserVal();
  return valstk[ptr];
}
final ParserVal dup_yyval(ParserVal val)
{
  ParserVal dup = new ParserVal();
  dup.ival = val.ival;
  dup.dval = val.dval;
  dup.sval = val.sval;
  dup.obj = val.obj;
  return dup;
}
//#### end semantic value section ####
public final static short NUMBER=257;
public final static short LINE=258;
public final static short RIGHT=259;
public final static short CIRCULO=260;
public final static short COLOR=261;
public final static short PRINT=262;
public final static short RECTANGULO=263;
public final static short VARIABLE=264;
public final static short MOVERF=265;
public final static short DIBUJARF=266;
public final static short IF=267;
public final static short IGUAL=268;
public final static short END=269;
public final static short ENTERO=270;
public final static short PRINTF=271;
public final static short LOOP=272;
public final static short MENOR=273;
public final static short MAYOR=274;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    0,    0,    0,    0,    3,    4,    2,    5,    5,
    5,    5,    7,    6,    8,    9,    9,    9,    9,    9,
    1,    1,    1,    1,    1,    1,    1,    1,    1,    1,
    1,    1,    1,
};
final static short yylen[] = {                            2,
    0,    2,    3,    2,    2,    4,    1,    4,    0,    3,
    2,    2,    1,    1,    3,    1,    1,    3,    3,    3,
    1,    6,    5,    6,    7,    6,    7,    3,    3,    2,
    2,    2,    6,
};
final static short yydefred[] = {                         1,
    0,   21,    0,    0,    0,    0,    0,    0,   13,    0,
    7,    2,    0,    4,    5,    0,    0,   16,   17,    0,
    0,    0,    0,    0,    0,    0,    0,   30,    0,   31,
   32,    3,    9,    0,    9,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   28,   29,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   14,    0,   11,   12,    6,   15,    8,    0,   23,    0,
    0,    0,    0,    0,   10,   22,   24,    0,   26,    0,
   33,   25,   27,
};
final static short yydgoto[] = {                          1,
   62,   63,   64,   16,   48,   65,   17,   35,   20,
};
final static short yysindex[] = {                         0,
  -57,    0, -253, -253, -247,  -42, -249, -246,    0, -240,
    0,    0,  -32,    0,    0, -227,  -23,    0,    0, -248,
 -248, -225, -224, -223, -221, -214, -213,    0, -212,    0,
    0,    0,    0, -253,    0, -253, -253, -253, -248, -248,
 -211, -208, -205, -203,    0,    0, -196, -207,  -41, -207,
 -260, -260, -260, -248, -245, -194, -191, -190, -189, -188,
    0,   11,    0,    0,    0,    0,    0, -226,    0, -186,
 -185, -184, -183, -182,    0,    0,    0, -181,    0, -180,
    0,    0,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  -38,  -35,  -34,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,
};
final static short yygindex[] = {                         0,
   77,   78,   79,    0,   46,   32,    0,    0,    1,
};
final static int YYTABLESIZE=233;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         66,
   28,   12,   18,   18,   21,   19,   20,   36,   18,   22,
   19,   69,   37,   38,   29,   19,   34,   30,   27,   36,
   39,   40,   36,   31,   37,   38,   32,   37,   38,   33,
   76,   41,   42,   43,   49,   44,   51,   52,   53,   54,
   55,   36,   45,   46,   47,   56,   37,   38,   57,    2,
    3,   58,    4,   59,   68,    5,    6,    7,    8,    9,
   60,   61,   70,   10,   11,   71,   72,   73,   74,   75,
   77,   78,   79,   80,   81,   82,   83,   13,   14,   15,
   50,   67,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    2,
    3,    0,    4,    0,    0,    5,    6,    7,    8,    9,
    0,    0,    0,   10,   11,   23,    0,   24,   18,    0,
   25,   19,   20,    0,    0,   18,   36,   26,   19,   20,
    0,   37,   38,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         41,
   43,   59,   41,  257,    4,   41,   41,  268,  257,  257,
  264,  257,  273,  274,  264,  264,   40,  264,   61,  268,
   20,   21,  268,  264,  273,  274,   59,  273,  274,  257,
  257,  257,  257,  257,   34,  257,   36,   37,   38,   39,
   40,  268,  257,  257,  257,  257,  273,  274,  257,  257,
  258,  257,  260,  257,   54,  263,  264,  265,  266,  267,
  257,  269,  257,  271,  272,  257,  257,  257,  257,   59,
  257,  257,  257,  257,  257,  257,  257,    1,    1,    1,
   35,   50,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  257,
  258,   -1,  260,   -1,   -1,  263,  264,  265,  266,  267,
   -1,   -1,   -1,  271,  272,  258,   -1,  260,  257,   -1,
  263,  257,  257,   -1,   -1,  264,  268,  270,  264,  264,
   -1,  273,  274,
};
}
final static short YYFINAL=1;
final static short YYMAXTOKEN=274;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,"'('","')'",null,"'+'",null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,"';'",
null,"'='",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,"NUMBER","LINE","RIGHT","CIRCULO","COLOR","PRINT",
"RECTANGULO","VARIABLE","MOVERF","DIBUJARF","IF","IGUAL","END","ENTERO",
"PRINTF","LOOP","MENOR","MAYOR",
};
final static String yyrule[] = {
"$accept : list",
"list :",
"list : list ';'",
"list : list inst ';'",
"list : list desicion",
"list : list ciclo",
"ciclo : loop NUMBER stm end",
"loop : LOOP",
"desicion : if condicion stm end",
"stm :",
"stm : stm inst ';'",
"stm : stm desicion",
"stm : stm ciclo",
"if : IF",
"end : END",
"condicion : '(' expresion ')'",
"expresion : NUMBER",
"expresion : VARIABLE",
"expresion : expresion IGUAL expresion",
"expresion : expresion MENOR expresion",
"expresion : expresion MAYOR expresion",
"inst : NUMBER",
"inst : LINE expresion expresion expresion expresion NUMBER",
"inst : CIRCULO expresion expresion expresion NUMBER",
"inst : RECTANGULO NUMBER NUMBER NUMBER NUMBER NUMBER",
"inst : VARIABLE LINE NUMBER NUMBER NUMBER NUMBER NUMBER",
"inst : VARIABLE CIRCULO NUMBER NUMBER NUMBER NUMBER",
"inst : VARIABLE RECTANGULO NUMBER NUMBER NUMBER NUMBER NUMBER",
"inst : VARIABLE ENTERO NUMBER",
"inst : VARIABLE '=' NUMBER",
"inst : VARIABLE '+'",
"inst : DIBUJARF VARIABLE",
"inst : PRINTF VARIABLE",
"inst : MOVERF VARIABLE NUMBER NUMBER NUMBER NUMBER",
};

//#line 141 "forma.y"

class Algo {
	Simbolo simb;
	int inst;
	public Algo(int i){ inst=i; }
	public Algo(Simbolo s, int i){
		simb=s;
                inst=i;
	}
}
Tabla tabla;
static Maquina maq;

StringTokenizer st;
boolean newline;
int yylex(){
String s;
int tok;
Double d;
Simbolo simbo;
   if (!st.hasMoreTokens())
      if (!newline) {
         newline=true; 
	 return ';';  
      }
   else
      return 0;
   s = st.nextToken();
   try {
      d = Double.valueOf(s);
      yylval = new ParserVal(
             new Algo(tabla.install("", NUMBER, d.doubleValue()),0) );
      tok = NUMBER;
   } catch (Exception e){
   if(Character.isLetter(s.charAt(0))){
      System.out.println("Ando aqui con: " + s);
       if((simbo=tabla.lookup(s))==null){
         simbo = new Simbolo(s, VARIABLE, 0.0);
         yylval = new ParserVal(new Algo(simbo, 0));
         System.out.println("Ya instale a: " + simbo.obtenNombre());
       }
       tok = simbo.tipo;		
      } else {
    	tok = s.charAt(0);
      }
   }
   return tok;
}
void yyerror(String s){
   System.out.println("parser error: "+s);
}
static Parser par = new Parser(0);
static JFrame f;
static JTextArea t=new JTextArea();
static JButton bcalc;
static JLabel lmuestra=new JLabel("                                 ");
static Canvas canv;
static Graphics g;
static Simbolo ts;
Parser(int foo){
   f=new JFrame("Calcula");
   bcalc=new JButton("Ejecuta");
   bcalc.addActionListener(new ManejaBoton());
   canv=new Canvas();
   canv.setSize(300,300);
   f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);  
}
public static void main(String args[]){
  f.add("North", t);
  f.add("Center", canv);
  f.add("South", bcalc);
  f.setSize( 900, 800);
  f.setVisible(true);
  g=canv.getGraphics();
  maq=new Maquina(g);
  System.out.println(""+ts);
}
class ManejaBoton implements ActionListener {
   public void actionPerformed(ActionEvent e){
      JButton jb=(JButton)e.getSource();
      if(jb == bcalc){
	 tabla=new Tabla();
	 tabla.install("line", LINE, 0.0);
         tabla.install("circulo", CIRCULO, 0.0);
         tabla.install("color", COLOR, 0.0);
	 tabla.install("print", PRINT, 0.0);
         tabla.install("rectangulo", RECTANGULO, 0.0);
         tabla.install("dibujarF", DIBUJARF, 0.0);
         tabla.install("moverF", MOVERF, 0.0);
         tabla.install("menor", MENOR, 0.0);
         tabla.install("mayor", MAYOR, 0.0);
         tabla.install("if", IF, 0.0);
         tabla.install("igual", IGUAL, 0.0);
         tabla.install("end", END, 0.0);
         tabla.install("entero", ENTERO, 0.0);
         tabla.install("printf", PRINTF, 0.0);
         tabla.install("loop", LOOP, 0.0);
	 st = new StringTokenizer(t.getText());
    	 newline=false;
	 for(maq.initcode(); par.yyparse ()!=0; maq.initcode()){
	     System.out.println("Maquina va en: " + maq.progbase);               
               maq.execute(maq.progbase);
               //maq.imprimePrograma();
         }
      }
   }
}
//#line 393 "Parser.java"
//###############################################################
// method: yylexdebug : check lexer state
//###############################################################
void yylexdebug(int state,int ch)
{
String s=null;
  if (ch < 0) ch=0;
  if (ch <= YYMAXTOKEN) //check index bounds
     s = yyname[ch];    //now get it
  if (s==null)
    s = "illegal-symbol";
  debug("state "+state+", reading "+ch+" ("+s+")");
}





//The following are now global, to aid in error reporting
int yyn;       //next next thing to do
int yym;       //
int yystate;   //current parsing state from state table
String yys;    //current token string


//###############################################################
// method: yyparse : parse input and execute indicated items
//###############################################################
int yyparse()
{
boolean doaction;
  init_stacks();
  yynerrs = 0;
  yyerrflag = 0;
  yychar = -1;          //impossible char forces a read
  yystate=0;            //initial state
  state_push(yystate);  //save it
  val_push(yylval);     //save empty value
  while (true) //until parsing is done, either correctly, or w/error
    {
    doaction=true;
    if (yydebug) debug("loop"); 
    //#### NEXT ACTION (from reduction table)
    for (yyn=yydefred[yystate];yyn==0;yyn=yydefred[yystate])
      {
      if (yydebug) debug("yyn:"+yyn+"  state:"+yystate+"  yychar:"+yychar);
      if (yychar < 0)      //we want a char?
        {
        yychar = yylex();  //get next token
        if (yydebug) debug(" next yychar:"+yychar);
        //#### ERROR CHECK ####
        if (yychar < 0)    //it it didn't work/error
          {
          yychar = 0;      //change it to default string (no -1!)
          if (yydebug)
            yylexdebug(yystate,yychar);
          }
        }//yychar<0
      yyn = yysindex[yystate];  //get amount to shift by (shift index)
      if ((yyn != 0) && (yyn += yychar) >= 0 &&
          yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
        {
        if (yydebug)
          debug("state "+yystate+", shifting to state "+yytable[yyn]);
        //#### NEXT STATE ####
        yystate = yytable[yyn];//we are in a new state
        state_push(yystate);   //save it
        val_push(yylval);      //push our lval as the input for next rule
        yychar = -1;           //since we have 'eaten' a token, say we need another
        if (yyerrflag > 0)     //have we recovered an error?
           --yyerrflag;        //give ourselves credit
        doaction=false;        //but don't process yet
        break;   //quit the yyn=0 loop
        }

    yyn = yyrindex[yystate];  //reduce
    if ((yyn !=0 ) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
      {   //we reduced!
      if (yydebug) debug("reduce");
      yyn = yytable[yyn];
      doaction=true; //get ready to execute
      break;         //drop down to actions
      }
    else //ERROR RECOVERY
      {
      if (yyerrflag==0)
        {
        yyerror("syntax error");
        yynerrs++;
        }
      if (yyerrflag < 3) //low error count?
        {
        yyerrflag = 3;
        while (true)   //do until break
          {
          if (stateptr<0)   //check for under & overflow here
            {
            yyerror("stack underflow. aborting...");  //note lower case 's'
            return 1;
            }
          yyn = yysindex[state_peek(0)];
          if ((yyn != 0) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
            if (yydebug)
              debug("state "+state_peek(0)+", error recovery shifting to state "+yytable[yyn]+" ");
            yystate = yytable[yyn];
            state_push(yystate);
            val_push(yylval);
            doaction=false;
            break;
            }
          else
            {
            if (yydebug)
              debug("error recovery discarding state "+state_peek(0)+" ");
            if (stateptr<0)   //check for under & overflow here
              {
              yyerror("Stack underflow. aborting...");  //capital 'S'
              return 1;
              }
            state_pop();
            val_pop();
            }
          }
        }
      else            //discard this token
        {
        if (yychar == 0)
          return 1; //yyabort
        if (yydebug)
          {
          yys = null;
          if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
          if (yys == null) yys = "illegal-symbol";
          debug("state "+yystate+", error recovery discards token "+yychar+" ("+yys+")");
          }
        yychar = -1;  //read another
        }
      }//end error recovery
    }//yyn=0 loop
    if (!doaction)   //any reason not to proceed?
      continue;      //skip action
    yym = yylen[yyn];          //get count of terminals on rhs
    if (yydebug)
      debug("state "+yystate+", reducing "+yym+" by rule "+yyn+" ("+yyrule[yyn]+")");
    if (yym>0)                 //if count of rhs not 'nil'
      yyval = val_peek(yym-1); //get current semantic value
    yyval = dup_yyval(yyval); //duplicate yyval if ParserVal is used as semantic value
    switch(yyn)
      {
//########## USER-SUPPLIED ACTIONS ##########
case 3:
//#line 14 "forma.y"
{maq.code("STOP"); return 1 ;}
//break;
case 4:
//#line 15 "forma.y"
{maq.code("STOP"); return 1 ;}
//break;
case 5:
//#line 16 "forma.y"
{maq.code("STOP"); return 1 ;}
//break;
case 6:
//#line 19 "forma.y"
{
      yyval = new ParserVal(new Algo(new Integer(((Algo)val_peek(3).obj).inst)));
      maq.getProg().setElementAt( new Double((((Algo)val_peek(2).obj).simb).val) , ((Algo)val_peek(3).obj).inst+1 ); 
      maq.getProg().setElementAt( new Integer(((Algo)val_peek(0).obj).inst), ((Algo)val_peek(3).obj).inst+2);
      }
break;
case 7:
//#line 26 "forma.y"
{ yyval = new ParserVal(new Algo(maq.code("loopcode"))); 
             maq.code("STOP");maq.code("STOP");}
break;
case 8:
//#line 30 "forma.y"
{
                   yyval = new ParserVal(new Algo(new Integer(((Algo)val_peek(3).obj).inst)));    
                   maq.getProg().setElementAt( new Integer(((Algo)val_peek(1).obj).inst), ((Algo)val_peek(3).obj).inst+1 ); 
                   maq.getProg().setElementAt( new Integer(((Algo)val_peek(0).obj).inst), ((Algo)val_peek(3).obj).inst+3); 
         }
break;
case 9:
//#line 37 "forma.y"
{yyval=new ParserVal(new Algo(0));}
break;
case 10:
//#line 38 "forma.y"
{maq.code("STOP"); 
        if( (new Integer(((Algo)val_peek(2).obj).inst)) != 0 ) yyval = new ParserVal(new Algo(new Integer(((Algo)val_peek(2).obj).inst))); else
        yyval = new ParserVal(new Algo(new Integer(((Algo)val_peek(1).obj).inst)));}
break;
case 11:
//#line 41 "forma.y"
{maq.code("STOP"); 
        if( (new Integer(((Algo)val_peek(1).obj).inst)) != 0 ) yyval = new ParserVal(new Algo(new Integer(((Algo)val_peek(1).obj).inst))); else
        yyval = new ParserVal(new Algo(new Integer(((Algo)val_peek(0).obj).inst)));}
break;
case 12:
//#line 44 "forma.y"
{maq.code("STOP"); 
        if( (new Integer(((Algo)val_peek(1).obj).inst)) != 0 ) yyval = new ParserVal(new Algo(new Integer(((Algo)val_peek(1).obj).inst))); else
        yyval = new ParserVal(new Algo(new Integer(((Algo)val_peek(0).obj).inst)));}
break;
case 13:
//#line 50 "forma.y"
{ yyval = new ParserVal(new Algo(maq.code("ifcode"))); 
        maq.code("STOP");maq.code("STOP");maq.code("STOP");}
break;
case 14:
//#line 54 "forma.y"
{ maq.code("end"); 
          yyval=new ParserVal(new Algo(maq.getProg().size()));        
        }
break;
case 15:
//#line 59 "forma.y"
{   maq.code("STOP");  
          /*((Algo)$$.obj).inst = ((Algo)$2.obj).inst;
*/
          }
break;
case 16:
//#line 65 "forma.y"
{ ((Algo)yyval.obj).inst=maq.code("constpush");maq.code(((Algo)val_peek(0).obj).simb); }
break;
case 17:
//#line 66 "forma.y"
{ ((Algo)yyval.obj).inst=maq.code("constpush");maq.code(maq.obtenerValor( ((Algo)val_peek(0).obj).simb.obtenNombre()  )); }
break;
case 18:
//#line 67 "forma.y"
{yyval = new ParserVal(new Algo(maq.code("igual")));}
break;
case 19:
//#line 68 "forma.y"
{yyval = new ParserVal(new Algo(maq.code("menor")));}
break;
case 20:
//#line 69 "forma.y"
{yyval = new ParserVal(new Algo(maq.code("mayor")));}
break;
case 21:
//#line 72 "forma.y"
{ yyval = new ParserVal(new Algo(maq.code("constpush")));
                maq.code(((Algo)val_peek(0).obj).simb); }
break;
case 22:
//#line 75 "forma.y"
{ 
                yyval = new ParserVal(new Algo(((Algo)val_peek(4).obj).inst));
                maq.code("constpush"); maq.code(((Algo)val_peek(0).obj).simb); 
                maq.code("color");maq.code("line");}
break;
case 23:
//#line 80 "forma.y"
{
      yyval = new ParserVal(new Algo(((Algo)val_peek(3).obj).inst));  
      maq.code("constpush"); maq.code(((Algo)val_peek(0).obj).simb); maq.code("color"); maq.code("circulo");}
break;
case 24:
//#line 84 "forma.y"
{ 
      yyval = new ParserVal(new Algo(maq.code("constpush"))); maq.code(((Algo)val_peek(4).obj).simb);
      maq.code("constpush"); maq.code(((Algo)val_peek(3).obj).simb);   maq.code("constpush"); maq.code(((Algo)val_peek(2).obj).simb);
      maq.code("constpush"); maq.code(((Algo)val_peek(1).obj).simb);   maq.code("constpush"); maq.code(((Algo)val_peek(0).obj).simb);
      maq.code("color"); maq.code("rectangulo");}
break;
case 25:
//#line 90 "forma.y"
{
         yyval = new ParserVal(new Algo(maq.code("constpush")));maq.code(((Algo)val_peek(4).obj).simb); 
         maq.code("constpush");maq.code(((Algo)val_peek(3).obj).simb);
         maq.code("constpush");maq.code(((Algo)val_peek(2).obj).simb); maq.code("constpush");maq.code(((Algo)val_peek(1).obj).simb);
         maq.code("constpush");maq.code(((Algo)val_peek(0).obj).simb); maq.code("meterSimbolo"); maq.code(((Algo)val_peek(6).obj).simb.obtenNombre());
         maq.code("line");}
break;
case 26:
//#line 97 "forma.y"
{
         yyval = new ParserVal(new Algo(maq.code("constpush")));maq.code(((Algo)val_peek(3).obj).simb); 
         maq.code("constpush");maq.code(((Algo)val_peek(2).obj).simb);
         maq.code("constpush");maq.code(((Algo)val_peek(1).obj).simb); maq.code("constpush");maq.code(((Algo)val_peek(0).obj).simb);
         maq.code("constpush");maq.code(((Algo)val_peek(0).obj).simb);
         maq.code("meterSimbolo"); maq.code(((Algo)val_peek(5).obj).simb.obtenNombre());
         maq.code("circulo");}
break;
case 27:
//#line 105 "forma.y"
{
         yyval = new ParserVal(new Algo(maq.code("constpush")));maq.code(((Algo)val_peek(4).obj).simb); 
         maq.code("constpush");maq.code(((Algo)val_peek(3).obj).simb);
         maq.code("constpush");maq.code(((Algo)val_peek(2).obj).simb); maq.code("constpush");maq.code(((Algo)val_peek(1).obj).simb);
         maq.code("constpush");maq.code(((Algo)val_peek(0).obj).simb); maq.code("meterSimbolo"); maq.code(((Algo)val_peek(6).obj).simb.obtenNombre());
         maq.code("rectangulo");}
break;
case 28:
//#line 112 "forma.y"
{
         yyval = new ParserVal(new Algo(maq.code("constpush")));maq.code(((Algo)val_peek(0).obj).simb); 
         maq.code("constpush");maq.code(((Algo)val_peek(0).obj).simb);
         maq.code("constpush");maq.code(((Algo)val_peek(0).obj).simb); maq.code("constpush");maq.code(((Algo)val_peek(0).obj).simb);
         maq.code("constpush");maq.code(((Algo)val_peek(0).obj).simb); maq.code("meterSimbolo"); maq.code(((Algo)val_peek(2).obj).simb.obtenNombre());
         maq.code("entero");}
break;
case 29:
//#line 119 "forma.y"
{
         yyval = new ParserVal(new Algo(maq.code("constpush")));maq.code(((Algo)val_peek(0).obj).simb); 
         maq.code("asignarValor"); maq.code(((Algo)val_peek(2).obj).simb.obtenNombre());}
break;
case 30:
//#line 123 "forma.y"
{
         yyval = new ParserVal(new Algo(maq.code("incrementarV"))); maq.code(((Algo)val_peek(1).obj).simb.obtenNombre());}
break;
case 31:
//#line 126 "forma.y"
{ yyval = new ParserVal(new Algo(maq.code("dibujarFigura")));
       maq.code(((Algo)val_peek(0).obj).simb.obtenNombre());
       }
break;
case 32:
//#line 130 "forma.y"
{yyval = new ParserVal(new Algo(maq.code("printfV")));
       maq.code(((Algo)val_peek(0).obj).simb.obtenNombre());
       }
break;
case 33:
//#line 134 "forma.y"
{ 
          yyval = new ParserVal(new Algo(maq.code("constpush")));
          maq.code(((Algo)val_peek(3).obj).simb); maq.code("constpush");maq.code(((Algo)val_peek(2).obj).simb);
          maq.code("constpush");maq.code(((Algo)val_peek(1).obj).simb); maq.code("constpush");maq.code(((Algo)val_peek(0).obj).simb);
          maq.code("moverFigura"); maq.code(((Algo)val_peek(4).obj).simb.obtenNombre());
        }
break;
//#line 729 "Parser.java"
//########## END OF USER-SUPPLIED ACTIONS ##########
    }//switch
    //#### Now let's reduce... ####
    if (yydebug) debug("reduce");
    state_drop(yym);             //we just reduced yylen states
    yystate = state_peek(0);     //get new state
    val_drop(yym);               //corresponding value drop
    yym = yylhs[yyn];            //select next TERMINAL(on lhs)
    if (yystate == 0 && yym == 0)//done? 'rest' state and at first TERMINAL
      {
      if (yydebug) debug("After reduction, shifting from state 0 to state "+YYFINAL+"");
      yystate = YYFINAL;         //explicitly say we're done
      state_push(YYFINAL);       //and save it
      val_push(yyval);           //also save the semantic value of parsing
      if (yychar < 0)            //we want another character?
        {
        yychar = yylex();        //get next character
        if (yychar<0) yychar=0;  //clean, if necessary
        if (yydebug)
          yylexdebug(yystate,yychar);
        }
      if (yychar == 0)          //Good exit (if lex returns 0 ;-)
         break;                 //quit the loop--all DONE
      }//if yystate
    else                        //else not done yet
      {                         //get next state and push, for next yydefred[]
      yyn = yygindex[yym];      //find out where to go
      if ((yyn != 0) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn]; //get new state
      else
        yystate = yydgoto[yym]; //else go to new defred
      if (yydebug) debug("after reduction, shifting from state "+state_peek(0)+" to state "+yystate+"");
      state_push(yystate);     //going again, so push state & val...
      val_push(yyval);         //for next action
      }
    }//main loop
  return 0;//yyaccept!!
}
//## end of method parse() ######################################



//## run() --- for Thread #######################################
/**
 * A default run method, used for operating this parser
 * object in the background.  It is intended for extending Thread
 * or implementing Runnable.  Turn off with -Jnorun .
 */
public void run()
{
  yyparse();
}
//## end of method run() ########################################



//## Constructors ###############################################
/**
 * Default constructor.  Turn off with -Jnoconstruct .

 */
public Parser()
{
  //nothing to do
}


/**
 * Create a parser, setting the debug to true or false.
 * @param debugMe true for debugging, false for no debug.
 */
public Parser(boolean debugMe)
{
  yydebug=debugMe;
}
//###############################################################



}
//################### END OF CLASS ##############################
