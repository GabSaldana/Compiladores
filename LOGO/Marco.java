import java.util.ArrayList;

public class Marco {

    private ArrayList<Object> parametros;
    private int retorno;
    private String nombre;

    public Marco(){
        parametros = new ArrayList<Object>();
        retorno = 0;
        nombre = null;
    }
    
    public void agregarParametro(Object parametro){
        parametros.add(parametro);
    }
    
    public Object getParametro(int i){
        return parametros.get(i);
    }
    
    public void setParametros(ArrayList<Object> parametros) {
        this.parametros = parametros;
    }

    public int getRetorno() {
        return retorno;
    }

    public void setRetorno(int retorno) {
        this.retorno = retorno;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
 
}
