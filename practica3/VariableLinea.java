class VariableLinea extends Variable{
   int x1, x2, y1, y2, color;
   

   public VariableLinea(String tipo, String nombre, int xx1, int xx2,int yy1, int yy2, int col){
      super(tipo, nombre);     
      x1 = xx1;
      x2 = xx2;
      y1 = yy1;
      y2 = yy2;
      color = col;
   }
   public int getX1(){
    return x1;
   }
    public int getX2(){
    return x2;
   }
   public int getY1(){
    return y1;
    }
  public int getY2(){
    return y2;
   }
   public int getColor(){
    return color;
   }

   public void setX1(int v){
    x1 = v;
   }
 public void setX2(int v){
    x2 = v;
   }
 public void setY1(int v){
    y1 = v;
   }
 public void setY2(int v){
    y2 = v;
   }
 public void setColor(int v){
    color = v;
   }
  
}
