import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.lang.reflect.*;

class  Maquina {

	Stack pila;
	Vector prog;

	static int pc=0;
	int progbase=0;
	boolean returning=false;

	int numArchi=0;
	Method metodo;
	Method metodos[];
	Class c;
	Graphics g;
	double angulo;
	int x=0, y=0;
	Class parames[];

	Maquina(Graphics g){ this.g=g; }//guardamos el objeto

	public Vector getProg(){ return prog; }

	void initcode(){
        pila=new Stack();
		prog=new Vector();
	}

	Object pop(){ return pila.pop(); }

	int code(Object f){//vetor de instrucciones
		System.out.println("Gen ("+f+") size="+prog.size());
   		prog.addElement(f);
		return prog.size()-1;
	}

    void execute(int p){
		
		String inst;
        System.out.println("progsize="+prog.size());
        
        for(pc=0;pc < prog.size(); pc=pc+1){

			System.out.println("pc="+pc+" inst "+prog.elementAt(pc));
		}

		for(pc=p; !(inst=(String)prog.elementAt(pc)).equals("STOP") && !returning;){
			//for(pc=p;pc < prog.size();){
			try {
				//System.out.println("111 pc= "+pc);
				inst=(String)prog.elementAt(pc);
				pc=pc+1;
				System.out.println("222 pc= "+pc+" instr "+inst);
                c=this.getClass();
				//System.out.println("clase "+c.getName());
          		metodo=c.getDeclaredMethod(inst, null);
				metodo.invoke(this, null);//ejecuta metodo
			}
			catch(NoSuchMethodException e){
				System.out.println("No metodo "+e);
            }

			catch(InvocationTargetException e){
				System.out.println(e);
            }
			catch(IllegalAccessException e){
				System.out.println(e);
            }
		}
	}

	void constpush(){

		Simbolo s;
		Double d;
		s=(Simbolo)prog.elementAt(pc);
		pc=pc+1;
		pila.push(new Double(s.val));
	}


    void color(){

    	
        Color colors[]={Color.red,Color.green,Color.blue};
		double d1;
		d1=((Double)pila.pop()).doubleValue();
        
        if(g!=null){
			g.setColor(colors[(int)d1]);
			//g.setColor(Color.blue);
			System.out.println("ENTRO A COLOR");
		}
    }


	void line(){

		double d1,d2,d3,d4;
		d1=((Double)pila.pop()).doubleValue();//valor NUMB%{
import java.lang.Math;
import java.io.*;
import java.util.StringTokenizer;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
%}

%token NUMBER LINE RIGHT CIRCULO COLOR PRINT RECTANGULO
%start list
%%

list :
     | list ';'
     | list inst ';'   { 
        maq.code("print"); maq.code("STOP"); return 1 ;
     }   
     ;
inst:  NUMBER  { 
          ((Algo)$$.obj).inst=maq.code("constpush");
           maq.code(((Algo)$1.obj).simb); 
        }

      | LINE NUMBER NUMBER NUMBER NUMBER  COLOR{ 
          
          maq.code("constpush");
          maq.code(((Algo)$2.obj).simb);
          maq.code("constpush");
          maq.code(((Algo)$3.obj).simb);
          maq.code("constpush");
          maq.code(((Algo)$4.obj).simb);
          maq.code("constpush");
          maq.code(((Algo)$5.obj).simb);
          maq.code("line");

        }

      | CIRCULO NUMBER NUMBER NUMBER COLOR{
                maq.code("constpush");
                maq.code(((Algo)$2.obj).simb);
                maq.code("constpush");
                maq.code(((Algo)$3.obj).simb);
                maq.code("constpush");
                maq.code(((Algo)$4.obj).simb);
                maq.code("circulo");
        }

      | RECTANGULO NUMBER NUMBER NUMBER NUMBER COLOR{

                maq.code("constpush");
                maq.code(((Algo)$2.obj).simb);
                maq.code("constpush");
                maq.code(((Algo)$3.obj).simb);
                maq.code("constpush");
                maq.code(((Algo)$4.obj).simb);
                maq.code("constpush");
                maq.code(((Algo)$5.obj).simb);
                maq.code("rectangulo");

        }
        
      | COLOR NUMBER { maq.code("constpush");
                maq.code(((Algo)$2.obj).simb); maq.code("color");}
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
Maquina maq;

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
      if((simbo=tabla.lookup(s))==null)
         yylval = new ParserVal(new Algo(simbo, 0));
	 tok= simbo.tipo;	
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
static JTextField t=new JTextField(20);
static JButton bcalc;
static JLabel lmuestra=new JLabel("                                 ");
static Canvas canv;
static Graphics g;

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
  f.setSize( 300, 400);
  f.setVisible(true);
  g=canv.getGraphics();

}

class ManejaBoton implements ActionListener {

   public void actionPerformed(ActionEvent e){
      JButton jb=(JButton)e.getSource();
      if(jb == bcalc){

         maq=new Maquina(g);
	       tabla=new Tabla();
	       tabla.install("line", LINE, 0.0);
         tabla.install("circulo", CIRCULO, 0.0);
         tabla.install("color", COLOR, 0.0);
	       tabla.install("print", PRINT, 0.0);
         tabla.install("rectangulo", RECTANGULO, 0.0);
	       st = new StringTokenizer(t.getText());
    	   newline=false;
	       
         for(maq.initcode(); par.yyparse ()!=0; maq.initcode())
         maq.execute(maq.progbase);
      }
   }
}

ER
		d2=((Double)pila.pop()).doubleValue();//valor NUMBER
		d3=((Double)pila.pop()).doubleValue();//valor NUMBER
		d4=((Double)pila.pop()).doubleValue();//valor NUMBER

        if(g!=null){
            
            System.out.println("ENTRO A LINEA");
            (new Linea((int)(d1+150),(int)(150-d2),
			(int)(x+d3*Math.cos(angulo))+150, 
			150-(int)(y+d4*Math.sin(angulo))) ).dibuja(g);
        }
        
        x=(int)(x+d1*Math.cos(angulo));
        y=(int)(y+d2*Math.sin(angulo));
        System.out.println("x="+x+" y="+y+" d1="+d1);

	}

    void circulo(){

		
		double d1,d2,d3;
		d1=((Double)pila.pop()).doubleValue();//valor NUMBER
		d2=((Double)pila.pop()).doubleValue();//valor NUMBER
		d3=((Double)pila.pop()).doubleValue();//valor NUMBER
        
        if(g!=null){
			
			System.out.println("ENTRO A CIRCULO");
			(new Circulo((int)(d1+150), (int)(150-d2), (int)d3)).dibuja(g);
        }
    }

    void rectangulo(){

		double d1,d2,d3,d4;
		d1=((Double)pila.pop()).doubleValue();//valor NUMBER
		d2=((Double)pila.pop()).doubleValue();//valor NUMBER
		d3=((Double)pila.pop()).doubleValue();//valor NUMBER
		d4=((Double)pila.pop()).doubleValue();//valor NUMBER
        
        if(g!=null){
			
			System.out.println("ENTRO A RECTANGULO");
			(new Rectangulo((int)(d1+150), (int)(150-d2), (int)d1,(int)d2)).dibuja(g);
        }
    }

	void print(){

		Double d;
		d=(Double)pila.pop();
		System.out.println(""+d.doubleValue());
	}

	void prexpr(){
		Double d;
		d=(Double)pila.pop();
		System.out.print("["+d.doubleValue()+"]");
	}
}
