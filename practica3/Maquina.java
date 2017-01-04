import java.awt.*;
import java.util.*;
import java.lang.reflect.*;
import javax.swing.*;

class  Maquina {

Stack pila;
Vector prog;
Simbolo tabSim;

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
	
	public Maquina(Graphics g, Simbolo t){ 
		this.g=g;
		tabSim = t;
		System.out.println("Maquina Simbolo inicial: " + tabSim);
	}

	public Vector getProg(){ 
		return prog; 
	}
	
	public void initcode(){
        	pila=new Stack();
		    prog=new Vector();            
	}


	public Object pop(){ 
		return pila.pop(); 
	}
	

	public int code(Object f){
		System.out.println("Gen ("+f+") size="+prog.size());
   		prog.addElement(f);
		return prog.size()-1;
	}

    public void execute(int p){
                
		String inst;
        System.out.println("progsize="+prog.size());
            for(pc=0;pc < prog.size(); pc=pc+1){
			    System.out.println("pc="+pc+" inst "+prog.elementAt(pc));
		    }
		for(pc=p; !(inst=(String)prog.elementAt(pc)).equals("STOP") && !returning;){		
			try {
			   inst=(String)prog.elementAt(pc);
			   pc=pc+1;
			   System.out.println("222 pc= "+pc+" instr "+inst);
               c=this.getClass();			
          	   metodo=c.getDeclaredMethod(inst, null);
			   metodo.invoke(this, null);
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

      
     
	public void constpush(){
	  Simbolo s;
	  Double d;
	  s=(Simbolo)prog.elementAt(pc);
	  pc=pc+1;
	  pila.push(new Double(s.val));
	}
        
    public void color(){
                Color colors[]={Color.red,Color.green,Color.blue};
		        double d1;
		        d1=((Double)pila.pop()).doubleValue();
                if(g!=null){
			    g.setColor(colors[(int)d1]);
		        }
    }

	public void line(){
		        double d1,d2,d3,d4;
                d4=((Double)pila.pop()).doubleValue();
                d3=((Double)pila.pop()).doubleValue();
                d2=((Double)pila.pop()).doubleValue();
		        d1=((Double)pila.pop()).doubleValue();
                if(g!=null){
                        (new Linea((int)d1, (int)d2 ,(int) d3 ,(int) d4)).dibuja(g);
                }
                x=(int)(x+d1*Math.cos(angulo));
                y=(int)(y+d1*Math.sin(angulo));
                System.out.println("x="+x+" y="+y+" d1="+d1);
	}

    public void circulo(){
		        double d1,d2,d3;                
                d3=((Double)pila.pop()).doubleValue();
                d2=((Double)pila.pop()).doubleValue();
		        d1=((Double)pila.pop()).doubleValue();
                if(g!=null){
			        (new Circulo((int)d1, (int)d2, (int)d3)).dibuja(g);
                }
    }

    public void rectangulo(){
                double d1,d2,d3,d4;
                d4=((Double)pila.pop()).doubleValue();
                d3=((Double)pila.pop()).doubleValue();
                d2=((Double)pila.pop()).doubleValue();
		        d1=((Double)pila.pop()).doubleValue();                
                if(g!=null){
                       (new Rectangulo((int)d1, (int)d2, (int)d3, (int)d4)).dibuja(g);
                }
    }

	public void print(){
	           Double d;
	           d=(Double)pila.pop();
	           System.out.println(""+d.doubleValue());
	}


	public void prexpr(){
	    Double d;
	    d=(Double)pila.pop();
	    System.out.print("["+d.doubleValue()+"]");
	}

	public void meterSimbolo(){
		String nombre = (String)prog.elementAt(pc);
		pc = pc + 1 ;
		String tipo = (String)prog.elementAt(pc);
		pc = pc + 1 ;
		double d1,d2,d3,d4,d5;
            d5=((Double)pila.pop()).doubleValue();
            d4=((Double)pila.pop()).doubleValue();
            d3=((Double)pila.pop()).doubleValue();
            d2=((Double)pila.pop()).doubleValue();
		    d1=((Double)pila.pop()).doubleValue();
		agregarSimbolo(nombre, tipo, d1,d2,d3,d4,d5);
	}
        
        public void agregarSimbolo(String nombre, String tipo, double d1,double d2,double d3,double d4,double d5){                          
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

        public void dibujarFigura(){
            String nombre = (String)prog.elementAt(pc);
		    pc = pc + 1 ;
		    dibujarF(nombre);
        }

        public void dibujarF(String nombre){           
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

        public void moverFigura(){
        	String nombre = (String)prog.elementAt(pc);
		    pc = pc + 1 ;
		    double d1,d2,d3,d4;            
            d4=((Double)pila.pop()).doubleValue();
            d3=((Double)pila.pop()).doubleValue();
            d2=((Double)pila.pop()).doubleValue();
		    d1=((Double)pila.pop()).doubleValue();
		    moverF(nombre, d1,d2,d3,d4);
        }

        public void moverF(String nombre, double x1, double y1, double x2, double y2){           
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
}
