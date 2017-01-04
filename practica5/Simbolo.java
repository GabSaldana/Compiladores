class Simbolo {
	String nombre;
	public int tipo;
	double val;
        Dibujable figura;
	public String metodo;
	int defn;
	Simbolo sig = null;

	Simbolo(String s, short t, double d)
	{
		nombre=s;
		tipo=t;
		val=d;
	}
        
        public void setFigura(Dibujable d){
             figura = d;
        }
        public Simbolo obtenSig()
        {
		return sig;
	}
        public void ponSig(Simbolo s)
	{
	      sig=s;
              System.out.println("" + sig);
	}
        public String obtenNombre()
	{
		return nombre;
	}
        public Dibujable getFigura(){
                return figura;
        }
        public void setValor(double v){ 
                val =v; 
        }
        public double getValor(){
                return val;
        }
}
