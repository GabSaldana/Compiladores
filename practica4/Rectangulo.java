import java.awt.*;


public class Rectangulo implements Dibujable {
	private int x1=0;
	private int y1=0;
	private int x2=0;
	private int y2=0;

	public Rectangulo(int x1, int y1, int x2, int y2)
	{
		this.x1=x1;
    this.y1=y1;
		this.x2=x2;
		this.y2=y2;
	}
	public void dibuja(Graphics g){
		g.drawRect(x1,y1,x2,y2);
    System.out.println("Rectangle Center: " + ( x2 - x1)/2 + "," + (y2 - y1)/2);
	}

  public void moverFigura(Graphics g, double x1n, double y1n, double x2n, double y2n){
      int coorx1 = (int)x1n;
      int coory1 = (int)y1n;
      int coorx2 = (int)x2n;
      int coory2 = (int)y2n;
      Color ori = g.getColor();
      g.setColor(Color.white);
      g.drawRect(x1,y1,x2,y2);
      this.x1 = coorx1;
      this.y1 = coory1;
      this.x2 = coorx2;
      this.y2 = coory2;
      g.setColor(ori);
      g.drawRect(x1,y1,x2,y2);
  }

  public void escalarFigura(Graphics g,double escala){

    int esc= (int)escala;
    Color color = g.getColor();
    g.setColor(Color.white);
    g.drawRect(x1,y1,x2,y2);
    this.x2 /=esc;
    this.y2 /=esc;
    g.setColor(color);
    g.drawRect(x1,y1,x2,y2);

  }

}
