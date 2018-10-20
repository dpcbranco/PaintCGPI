package formas;

import javafx.geometry.Point2D;

public class Ponto extends Point2D implements Formas{
	public Ponto () {
		super(0, 0);
	}

	public Ponto (double x, double y) {
		super(x, y);
	}

	public Ponto (Ponto p) {
		super(p.getX(), p.getY());
	}

    public double calcularDistancia(Ponto p) {
		return distance(p);
	}
    
    public double calcularDistancia(double x, double y) {
		return distance(x, y);
	}
}
