import java.awt.*;

public class Circulo implements Dibujable {
	private int x=0;
	private int y=0;
	private int r=0;

	public Circulo(int x, int y, int r)
	{
		this.x=x;
		this.y=y;
		this.r=r;
	}

	public void dibuja(Graphics g){
		g.drawOval(x,y,r,r);
	}
        
        public void moverFigura(Graphics g, double x1n, double y1n, double x2n, double y2n){
              int coorx = (int)x1n;
              int coory = (int)y1n;
              Color ori = g.getColor();
              g.setColor(Color.white);
              g.drawOval(x,y,r,r);
              this.x = coorx;
              this.y = coory;
              g.setColor(ori);
              g.drawOval(x,y,r,r);
        }
}


