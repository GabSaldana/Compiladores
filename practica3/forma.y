%{
import java.lang.Math;
import java.io.*;
import java.util.StringTokenizer;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
%}
%token NUMBER LINE RIGHT CIRCULO COLOR PRINT RECTANGULO VARIABLE DRAW MOV
%start list
%%
list :
     | list ';'
     | list inst ';'   { 
             //maq.code("print"); maq.code("STOP");
              return 1 ;
     }   
     ;
inst:  NUMBER  { ((Algo)$$.obj).inst=maq.code("constpush");
                maq.code(((Algo)$1.obj).simb); }

      | VARIABLE LINE NUMBER  NUMBER  NUMBER  NUMBER  NUMBER {agregarSimbolo( ((Algo)$1.obj).simb.obtenNombre(), "line",
               new Double(((Algo)$3.obj).simb.val), new Double(((Algo)$4.obj).simb.val), new Double(((Algo)$5.obj).simb.val),
               new Double(((Algo)$6.obj).simb.val), new Double(((Algo)$7.obj).simb.val) );}

      | VARIABLE CIRCULO NUMBER  NUMBER  NUMBER  NUMBER {agregarSimbolo( ((Algo)$1.obj).simb.obtenNombre(), "circulo",
               new Double(((Algo)$3.obj).simb.val), new Double(((Algo)$4.obj).simb.val), new Double(((Algo)$5.obj).simb.val),
               new Double(((Algo)$6.obj).simb.val), 0.0 );}

      | VARIABLE RECTANGULO NUMBER  NUMBER  NUMBER  NUMBER  NUMBER {agregarSimbolo( ((Algo)$1.obj).simb.obtenNombre(), "rectangulo",
               new Double(((Algo)$3.obj).simb.val), new Double(((Algo)$4.obj).simb.val), new Double(((Algo)$5.obj).simb.val),
               new Double(((Algo)$6.obj).simb.val), new Double(((Algo)$7.obj).simb.val) );}
      
      | DRAW VARIABLE { draw( ((Algo)$2.obj).simb.obtenNombre() );}
 
      | MOV VARIABLE NUMBER NUMBER NUMBER NUMBER { mov( ((Algo)$2.obj).simb.obtenNombre(), new Double(((Algo)$3.obj).simb.val),
          new Double(((Algo)$4.obj).simb.val), new Double(((Algo)$5.obj).simb.val), new Double(((Algo)$6.obj).simb.val) );}
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
static Tabla tabla;
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
static JTextField t=new JTextField(20);
static JButton bcalc;
static JLabel lmuestra=new JLabel("                                 ");
static Canvas canv;
static Graphics g;
static Simbolo tabSim;

Parser(int foo){
   f=new JFrame("Calcula");
   bcalc=new JButton("Ejecuta");
   bcalc.addActionListener(new ManejaBoton());
   canv=new Canvas();
   canv.setSize(300,300);
   f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);  
}

void agregarSimbolo(String nombre, String tipo, double d1,double d2,double d3,double d4,double d5){                          
            System.out.println("Agregando variable"); 
            Simbolo iterador = tabSim, aux = tabSim;
            System.out.println("Inicio: " + iterador);
             if(iterador != null ){
                 while(iterador.obtenSig() != null){
                      if((iterador.obtenNombre()).equals(nombre))
                        break;
                      aux = iterador; 
                      iterador = aux.obtenSig();
                 }    
                if(iterador.obtenNombre().equals(nombre)){
                 JOptionPane.showMessageDialog(null, "Variable ya existente");
                 aux = null;
                }else{
                    System.out.println("Creando nodo->"+aux);
                    int tipoD = 0;
                      if(tipo.equals("circulo"))
                          tipoD = 1;
                      if(tipo.equals("rectangulo"))
                          tipoD = 2;     
                    aux.ponSig(new Simbolo(nombre,(short) tipoD, 0));
                    aux = aux.obtenSig();
                    System.out.println("Nodo creado->"+aux);
                }
             }else{
                int tipoD = 0;
                      if(tipo.equals("circulo"))
                          tipoD = 1;
                      if(tipo.equals("rectangulo"))
                          tipoD = 2;
               tabSim = new Simbolo(nombre, (short)tipoD, 0);
               aux = tabSim;   
             }
             if(aux != null){
             Dibujable dib = null;
             double colorF = d5;
               if(tipo.equals("circulo")){   
                   dib = (Dibujable) (new Circulo((int)d1, (int)d2, (int)d3));
                   colorF = d4;
                   System.out.println("Creando circulo");
               }else if(tipo.equals("line")){
                   dib = (Dibujable) (new Linea((int)d1, (int)d2 ,(int) d3 ,(int) d4)); 
                   System.out.println("Creando linea");                 
               }else {
                   dib = (Dibujable) (new Rectangulo((int)d1, (int)d2, (int)d3, (int)d4));                  
                   System.out.println("Creando rectangulo");
               }
             aux.setFigura(dib);
             aux.setValor(colorF);
             System.out.println("Variable: " + aux.obtenNombre() + " se creo con exito");                
           }           
              System.out.println("Tabsim: " + tabSim);
        }
   
        void draw(String nombre){           
           System.out.println("Estoy dibujando a: " + nombre);             
           Simbolo iterador = tabSim, aux = tabSim;
             if(iterador != null){
                while(iterador.obtenSig() != null){
                    if(iterador.obtenNombre().equals(nombre))
                       break;    
                    iterador = iterador.obtenSig();
                }
               if(iterador.obtenNombre().equals(nombre)){
                    Color colors[]={Color.red,Color.green,Color.blue};
                    g.setColor(colors[(int)iterador.getValor()]);
                    iterador.getFigura().dibuja(g);
               }   
             }else{
               JOptionPane.showMessageDialog(null,"Variable inexistente");
             } 
            System.out.println("Tabsim: " + tabSim);               
        }

          void mov(String nombre, double x1, double y1, double x2, double y2){           
           System.out.println("Estoy moviendo a: " + nombre);             
           Simbolo iterador = tabSim, aux = tabSim;
             if(iterador != null){
                while(iterador.obtenSig() != null){
                    if(iterador.obtenNombre().equals(nombre))
                       break;    
                    iterador = iterador.obtenSig();
                }
               if(iterador.obtenNombre().equals(nombre)){
                    Color colors[]={Color.red,Color.green,Color.blue};
                    g.setColor(colors[(int)iterador.getValor()]);
                    iterador.getFigura().moverFigura(g,x1,y1,x2,y2);
               }   
             }else{
               JOptionPane.showMessageDialog(null,"Variable inexistente");
             } 
            System.out.println("Tabsim: " + tabSim);               
        }

public static void main(String args[]){
  f.add("North", t);
  f.add("Center", canv);
  f.add("South", bcalc);
  f.setSize( 300, 400);
  f.setVisible(true);
  g=canv.getGraphics();
  tabSim = null;
  tabla=new Tabla();
  tabla.install("line", LINE, 0.0);
         tabla.install("circulo", CIRCULO, 0.0);
         tabla.install("color", COLOR, 0.0);
	 tabla.install("print", PRINT, 0.0);
         tabla.install("rectangulo", RECTANGULO, 0.0);
         tabla.install("draw", DRAW, 0.0);
         tabla.install("mov", MOV, 0.0);  
}
class ManejaBoton implements ActionListener {
   public void actionPerformed(ActionEvent e){
      JButton jb=(JButton)e.getSource();
      if(jb == bcalc){
         //maq=new Maquina(g);            
	 st = new StringTokenizer(t.getText());
    	 newline=false;
	 for(/*maq.initcode()*/; par.yyparse ()!=0; /*maq.initcode()*/){}
	    //maq.execute(maq.progbase);
      }
   }
}
