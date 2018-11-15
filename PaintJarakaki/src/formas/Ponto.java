package formas;

import javafx.geometry.Point2D;

public class Ponto implements Forma{
	
	protected double x;
	protected double y;
	
	public Ponto () {
		this.x = this.y = 0;
	}

	public Ponto (double x, double y) {
		this.x = x;
		this.y = y;
	}

	public Ponto (Ponto p) {
		this.x = p.getX();
		this.y = p.getY();
	}

    public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double calcularDistancia(Ponto p) {
		Point2D p1 = new Point2D(getX(), getY());
		Point2D p2 = new Point2D(p.getX(), p.getY());
		return p1.distance(p2);
	}
    
    public double calcularDistancia(double x, double y) {
    	Point2D p1 = new Point2D(getX(), getY());
		Point2D p2 = new Point2D(x, y);
		return p1.distance(p2);
	}
}
