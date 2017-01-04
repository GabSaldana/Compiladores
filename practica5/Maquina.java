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
	
	public Maquina(Graphics g){ 
		this.g=g;
		tabSim = new Simbolo("null",(short) 0, 0.0);
		System.out.println("Maquina Simbolo inicial: " + tabSim);
	}

  public void imprimirVariables(){
    System.out.println("Voy a imprimir variables");             
           Simbolo iterador = tabSim, aux = tabSim;
             if(iterador != null){
                while(iterador != null){
                    System.out.println(iterador.obtenNombre());
                    iterador.obtenSig();
                  }              
             }else{
               JOptionPane.showMessageDialog(null,"Variable inexistente");
             } 
            System.out.println("Tabsim: " + tabSim);               
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
  public void imprimePrograma(){
         for(int x = 0; x < prog.size();x++){
             try{
             System.out.println("Inst["+x+"]: " + (String)prog.elementAt(x));
             }catch(Exception e){
                try{
                System.out.println("Inst["+x+"]: " + (new Double(((Simbolo)prog.elementAt(x)).val)) );                  
                }catch(Exception ie){
                 System.out.println("Inst["+x+"]: " + (Double)prog.elementAt(x) );                  
                }
             }
            
         }
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
  

  public void executeInterno(int p){
                
        String inst;
        System.out.println("progsize="+prog.size());        
    for(pc=p; !(inst=(String)prog.elementAt(pc)).equals("end") && !returning;){    
      try {
         inst=(String)prog.elementAt(pc);
         if(!inst.equals("STOP")){
           pc=pc+1;
           System.out.println("222 pc= "+pc+" instr "+inst);
           c=this.getClass();     
           metodo=c.getDeclaredMethod(inst, null);
           metodo.invoke(this, null);   
         }else{
           pc = pc + 1;
         }
         
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
    pc = pc+1;
  }
  
  public void ifcode(){
      boolean d;      
      int savepc=pc;
      System.out.println("en if code pc= "+pc);
      execute(savepc+3);
      System.out.println("en ant pop ifcode  pc= "+pc);      
      d=((Boolean)pila.pop()).booleanValue();
      System.out.println("en ifcode  pc= "+pc+" val ="+d);
      if(d){
         System.out.println("en el if");
         executeInterno(((Integer)prog.elementAt(savepc)).intValue());
      }
      else if(!prog.elementAt(savepc+1).toString().equals("STOP"))
         execute(((Integer)prog.elementAt(savepc+1)).intValue());
      pc=((Integer)prog.elementAt(savepc+2)).intValue();
      System.out.println("SALGO DEL IFCODE PC = " + pc);
   }

  public void loopcode(){
      double s;
      int repe;
      s=(Double)prog.elementAt(pc);
      pc=pc+1;
      int savepc = pc;
      repe = (int) s;
      while (repe > 0){
          executeInterno(pc+1);
          pc = savepc;
          repe--;
      }
      pc=((Integer)prog.elementAt(savepc)).intValue();
      System.out.println("SALGO DEL LOOPCODE PC = " + pc);
   }

  public void igual(){
    double d1, d2;
    boolean res = false;  
    d2=((Double)pila.pop()).doubleValue();
    d1=((Double)pila.pop()).doubleValue();
    if(d2 == d1)
      res = true;
    pila.push(new Boolean(res));
  }

  public void menor(){
    double d1, d2;
    boolean res = false;  
    d2=((Double)pila.pop()).doubleValue();
    d1=((Double)pila.pop()).doubleValue();
    if(d1 < d2)
      res = true;
    pila.push(new Boolean(res));
  }    
  public void mayor(){
    double d1, d2;
    boolean res = false;  
    d2=((Double)pila.pop()).doubleValue();
    d1=((Double)pila.pop()).doubleValue();
    if(d1 > d2)
      res = true;
    pila.push(new Boolean(res));
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
	           //d=(Double)pila.pop();
	           //System.out.println(""+d.doubleValue());
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
                      //JOptionPane.showMessageDialog(null,"iterador: " + iterador);                  
                          if((iterador.obtenNombre()).equals(nombre))
                               break;                          
                          iterador = iterador.obtenSig();                          
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
                         if(tipo.equals("entero"))
                          tipoD = 3;
                         aux = iterador;     
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
                      }else if(tipo.equals("rectangulo")){
                                 dib = (Dibujable) (new Rectangulo((int)d1, (int)d2, (int)d3, (int)d4));                  
                                 System.out.println("Creando rectangulo");
                      }else{
                                 dib = null;
                                 System.out.println("Creando entero");    
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
  
  public void asignarValor(){
        String nombre = (String)prog.elementAt(pc);
        pc = pc + 1 ;
        double d1=((Double)pila.pop()).doubleValue();
        asignarV(nombre, d1);

  }

  public void asignarV(String nombre, double valor){                      
           Simbolo iterador = tabSim, aux = tabSim;
             if(iterador != null){
                while(iterador.obtenSig() != null){
                    if(iterador.obtenNombre().equals(nombre))
                       break;    
                    iterador = iterador.obtenSig();
                }
               if(iterador.obtenNombre().equals(nombre)){
                    iterador.setValor(valor);
               }   
             }else{
               JOptionPane.showMessageDialog(null,"Variable inexistente");
             } 
            System.out.println("Tabsim: " + tabSim);               
  }

  public Simbolo obtenerValor(String nombre){                      
           Simbolo iterador = tabSim, aux = tabSim;
             if(iterador != null){
                while(iterador.obtenSig() != null){
                    if(iterador.obtenNombre().equals(nombre))
                       break;    
                    iterador = iterador.obtenSig();
                }
               if(iterador.obtenNombre().equals(nombre)){
                    return iterador;
               }else
                    return null;   
             }else{
               JOptionPane.showMessageDialog(null,"Variable inexistente");                    
             }             
             return null;
  }  
  
  public void printfV(){
        String nombre = (String)prog.elementAt(pc);
        pc = pc + 1 ;
        printfVariable(nombre);
  }

  public void printfVariable(String nombre){                      
           Simbolo iterador = tabSim, aux = tabSim;
             if(iterador != null){
                while(iterador.obtenSig() != null){
                    if(iterador.obtenNombre().equals(nombre))
                       break;    
                    iterador = iterador.obtenSig();
                }
               if(iterador.obtenNombre().equals(nombre)){
                JOptionPane.showMessageDialog(null,"Variable: " + nombre + "  Valor: " + iterador.getValor());
               }else
                 JOptionPane.showMessageDialog(null,"Variable: " + nombre + "   NO encontrada");
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

  public void incrementarV(){
        String nombre = (String)prog.elementAt(pc);
        pc = pc + 1 ;
        incrementarVariable(nombre);
  }

  public void incrementarVariable(String nombre){                      
           Simbolo iterador = tabSim, aux = tabSim;
             if(iterador != null){
                while(iterador.obtenSig() != null){
                    if(iterador.obtenNombre().equals(nombre))
                       break;    
                    iterador = iterador.obtenSig();
                }
               if(iterador.obtenNombre().equals(nombre)){
                   iterador.setValor(iterador.getValor() + 1);
               }else 
                 JOptionPane.showMessageDialog(null,"Variable: " + nombre + "   NO encontrada");
             }else{
               JOptionPane.showMessageDialog(null,"Variable inexistente");                    
             } 
            System.out.println("Tabsim: " + tabSim);               
  }


}
