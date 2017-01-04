%{
import java.lang.Math;
import java.io.*;
import java.util.StringTokenizer;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
%}
%token NUMBER LINE RIGHT CIRCULO COLOR PRINT RECTANGULO VARIABLE MOVERF DIBUJARF IF IGUAL END ENTERO PRINTF LOOP MENOR MAYOR
%start list
%%
list :
     | list ';'
     | list inst ';' {maq.code("STOP"); return 1 ;}
     | list desicion {maq.code("STOP"); return 1 ;}
     | list ciclo {maq.code("STOP"); return 1 ;}   
     ;

ciclo: loop NUMBER stm end {
      $$ = new ParserVal(new Algo(new Integer(((Algo)$1.obj).inst)));
      maq.getProg().setElementAt( new Double((((Algo)$2.obj).simb).val) , ((Algo)$1.obj).inst+1 ); 
      maq.getProg().setElementAt( new Integer(((Algo)$4.obj).inst), ((Algo)$1.obj).inst+2);
      }
     ;

loop: LOOP { $$ = new ParserVal(new Algo(maq.code("loopcode"))); 
             maq.code("STOP");maq.code("STOP");}
    ; 

desicion: if condicion stm end {
                   $$ = new ParserVal(new Algo(new Integer(((Algo)$1.obj).inst)));    
                   maq.getProg().setElementAt( new Integer(((Algo)$3.obj).inst), ((Algo)$1.obj).inst+1 ); 
                   maq.getProg().setElementAt( new Integer(((Algo)$4.obj).inst), ((Algo)$1.obj).inst+3); 
         }
         ;

stm: {$$=new ParserVal(new Algo(0));}
    |stm inst ';' {maq.code("STOP"); 
        if( (new Integer(((Algo)$1.obj).inst)) != 0 ) $$ = new ParserVal(new Algo(new Integer(((Algo)$1.obj).inst))); else
        $$ = new ParserVal(new Algo(new Integer(((Algo)$2.obj).inst)));}
    |stm desicion {maq.code("STOP"); 
        if( (new Integer(((Algo)$1.obj).inst)) != 0 ) $$ = new ParserVal(new Algo(new Integer(((Algo)$1.obj).inst))); else
        $$ = new ParserVal(new Algo(new Integer(((Algo)$2.obj).inst)));}
    |stm ciclo{maq.code("STOP"); 
        if( (new Integer(((Algo)$1.obj).inst)) != 0 ) $$ = new ParserVal(new Algo(new Integer(((Algo)$1.obj).inst))); else
        $$ = new ParserVal(new Algo(new Integer(((Algo)$2.obj).inst)));}
    ;


if: IF { $$ = new ParserVal(new Algo(maq.code("ifcode"))); 
        maq.code("STOP");maq.code("STOP");maq.code("STOP");}
    ;

end:END { maq.code("end"); 
          $$=new ParserVal(new Algo(maq.getProg().size()));        
        }
	; 

condicion: '(' expresion ')'   {   maq.code("STOP");  
          //((Algo)$$.obj).inst = ((Algo)$2.obj).inst;
          } 
         ;


expresion: NUMBER   { ((Algo)$$.obj).inst=maq.code("constpush");maq.code(((Algo)$1.obj).simb); }
          |VARIABLE { ((Algo)$$.obj).inst=maq.code("constpush");maq.code(maq.obtenerValor( ((Algo)$1.obj).simb.obtenNombre()  )); }
          |expresion IGUAL expresion {$$ = new ParserVal(new Algo(maq.code("igual")));}
          |expresion MENOR expresion {$$ = new ParserVal(new Algo(maq.code("menor")));}
          |expresion MAYOR expresion {$$ = new ParserVal(new Algo(maq.code("mayor")));}
          ;

inst:  NUMBER  { $$ = new ParserVal(new Algo(maq.code("constpush")));
                maq.code(((Algo)$1.obj).simb); }

      | LINE expresion expresion expresion expresion NUMBER { 
                $$ = new ParserVal(new Algo(((Algo)$2.obj).inst));
                maq.code("constpush"); maq.code(((Algo)$6.obj).simb); 
                maq.code("color");maq.code("line");}

      | CIRCULO expresion expresion expresion NUMBER {
      $$ = new ParserVal(new Algo(((Algo)$2.obj).inst));  
      maq.code("constpush"); maq.code(((Algo)$5.obj).simb); maq.code("color"); maq.code("circulo");}

      | RECTANGULO NUMBER NUMBER NUMBER NUMBER NUMBER { 
      $$ = new ParserVal(new Algo(maq.code("constpush"))); maq.code(((Algo)$2.obj).simb);
      maq.code("constpush"); maq.code(((Algo)$3.obj).simb);   maq.code("constpush"); maq.code(((Algo)$4.obj).simb);
      maq.code("constpush"); maq.code(((Algo)$5.obj).simb);   maq.code("constpush"); maq.code(((Algo)$6.obj).simb);
      maq.code("color"); maq.code("rectangulo");}

      | VARIABLE LINE NUMBER  NUMBER  NUMBER  NUMBER  NUMBER {
         $$ = new ParserVal(new Algo(maq.code("constpush")));maq.code(((Algo)$3.obj).simb); 
         maq.code("constpush");maq.code(((Algo)$4.obj).simb);
         maq.code("constpush");maq.code(((Algo)$5.obj).simb); maq.code("constpush");maq.code(((Algo)$6.obj).simb);
         maq.code("constpush");maq.code(((Algo)$7.obj).simb); maq.code("meterSimbolo"); maq.code(((Algo)$1.obj).simb.obtenNombre());
         maq.code("line");}

      | VARIABLE CIRCULO NUMBER  NUMBER  NUMBER  NUMBER {
         $$ = new ParserVal(new Algo(maq.code("constpush")));maq.code(((Algo)$3.obj).simb); 
         maq.code("constpush");maq.code(((Algo)$4.obj).simb);
         maq.code("constpush");maq.code(((Algo)$5.obj).simb); maq.code("constpush");maq.code(((Algo)$6.obj).simb);
         maq.code("constpush");maq.code(((Algo)$6.obj).simb);
         maq.code("meterSimbolo"); maq.code(((Algo)$1.obj).simb.obtenNombre());
         maq.code("circulo");}

      | VARIABLE RECTANGULO NUMBER  NUMBER  NUMBER  NUMBER  NUMBER {
         $$ = new ParserVal(new Algo(maq.code("constpush")));maq.code(((Algo)$3.obj).simb); 
         maq.code("constpush");maq.code(((Algo)$4.obj).simb);
         maq.code("constpush");maq.code(((Algo)$5.obj).simb); maq.code("constpush");maq.code(((Algo)$6.obj).simb);
         maq.code("constpush");maq.code(((Algo)$7.obj).simb); maq.code("meterSimbolo"); maq.code(((Algo)$1.obj).simb.obtenNombre());
         maq.code("rectangulo");}

      |VARIABLE ENTERO NUMBER {
         $$ = new ParserVal(new Algo(maq.code("constpush")));maq.code(((Algo)$3.obj).simb); 
         maq.code("constpush");maq.code(((Algo)$3.obj).simb);
         maq.code("constpush");maq.code(((Algo)$3.obj).simb); maq.code("constpush");maq.code(((Algo)$3.obj).simb);
         maq.code("constpush");maq.code(((Algo)$3.obj).simb); maq.code("meterSimbolo"); maq.code(((Algo)$1.obj).simb.obtenNombre());
         maq.code("entero");}
      
      |VARIABLE '=' NUMBER {
         $$ = new ParserVal(new Algo(maq.code("constpush")));maq.code(((Algo)$3.obj).simb); 
         maq.code("asignarValor"); maq.code(((Algo)$1.obj).simb.obtenNombre());}
      
      |VARIABLE '+' {
         $$ = new ParserVal(new Algo(maq.code("incrementarV"))); maq.code(((Algo)$1.obj).simb.obtenNombre());}

      | DIBUJARF VARIABLE { $$ = new ParserVal(new Algo(maq.code("dibujarFigura")));
       maq.code(((Algo)$2.obj).simb.obtenNombre());
       }
      
      |PRINTF VARIABLE {$$ = new ParserVal(new Algo(maq.code("printfV")));
       maq.code(((Algo)$2.obj).simb.obtenNombre());
       }
 
      | MOVERF VARIABLE NUMBER NUMBER NUMBER NUMBER { 
          $$ = new ParserVal(new Algo(maq.code("constpush")));
          maq.code(((Algo)$3.obj).simb); maq.code("constpush");maq.code(((Algo)$4.obj).simb);
          maq.code("constpush");maq.code(((Algo)$5.obj).simb); maq.code("constpush");maq.code(((Algo)$6.obj).simb);
          maq.code("moverFigura"); maq.code(((Algo)$2.obj).simb.obtenNombre());
        }
      ;
%%
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
