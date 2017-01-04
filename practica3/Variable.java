public class Variable {
   String tipo, nombre;
   Variable siguiente;

public Variable(String t, String n){
        tipo = t;
        nombre = n;
        siguiente = null;
}

public String getTipo(){
  return tipo;
}

public void setTipo(String tipo){
 this.tipo = tipo;
}

public void setSiguiente (Variable sig){
   siguiente = sig;
}
public Variable getSiguiente(){
  return siguiente;
}
public String getNombre(){
  return nombre;
}

}
