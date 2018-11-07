package formas;

import java.util.ArrayList;

public class Linha implements Formas{
	
	public Linha() {
		
	}
	
	public Linha (Ponto p1, Ponto p2) {
		this.p1 = p1;
		this.p2 = p2;
	}
	
	protected Ponto p1, p2, pAux;
	
	protected ArrayList<Ponto> calcularLinha(){
		ArrayList<Ponto> alCoordenadas = new ArrayList<>();
		int iDifx = new Double(Math.abs(p2.getX() - p1.getX())).intValue();
		int iDify = new Double(Math.abs(p2.getY() - p1.getY())).intValue();
		
		if (iDifx == 0) {
			alCoordenadas = calcularLinhaVertical();
		}
		
		else if (iDify == 0) {
			alCoordenadas = calcularLinhaHorizontal();
		}
		
		else if (iDifx > iDify) {
			if (p1.getX() > p2.getX()) {
				pAux = p1;
				p1 = p2;
				p2 = pAux;
			}
			
			alCoordenadas = calcularCoordX();
		}
		
		else {
			if (p1.getY() > p2.getY()) {
				pAux = p1;
				p1 = p2;
				p2 = pAux;
			}
			
			alCoordenadas = calcularCoordY();
		}
		
		return alCoordenadas;
	}
	
	//Calcular linha pela coordenada X
	private ArrayList<Ponto> calcularCoordX(){
		double dTg = (p2.getY() - p1.getY())/(p2.getX() - p1.getX());
		double dAltura = p1.getY() - dTg * p1.getX();
		ArrayList<Ponto> aAux = new ArrayList<>();
		int dY;
		
		for (int dX = (int)p1.getX(); dX < (int)p2.getX(); dX++) {
			dY = new Double((dTg*dX) + dAltura).intValue();
			aAux.add(new Ponto(dX, dY));
		}
		
		return aAux;
	}
	
	//Calcular linha pela coordenada Y
	private ArrayList<Ponto> calcularCoordY(){
		double dTg = (p2.getY() - p1.getY())/(p2.getX() - p1.getX());
		double dAltura = p1.getY() - dTg * p1.getX();
		ArrayList<Ponto> aAux = new ArrayList<>();
		int dX;
		
		for (int dY = (int)p1.getY(); dY < (int)p2.getY(); dY++) {
			dX = new Double((dY - dAltura)/dTg).intValue();
			aAux.add(new Ponto (dX, dY));
		}
		
		return aAux;
	}
	
	private ArrayList<Ponto> calcularLinhaVertical(){
		
		ArrayList<Ponto> aAux = new ArrayList<>();
		
		if (p1.getY() > p2.getY()) {
			pAux = p1;
			p1 = p2;
			p2 = pAux;
		}
		
		for (int i = (int) p1.getY(); i < (int) p2.getY(); i++) {
			aAux.add(new Ponto((int) p1.getX(), i));
		}
		
		return aAux;
	
	}
	
	private ArrayList<Ponto> calcularLinhaHorizontal(){
		
		ArrayList<Ponto> aAux = new ArrayList<>();
		
		if (p1.getX() > p2.getX()) {
			pAux = p1;
			p1 = p2;
			p2 = pAux;
		}
		
		for (int i = (int) p1.getX(); i < (int) p2.getX(); i++) {
			aAux.add(new Ponto(i, (int) p1.getY()));
		}
		
		return aAux;
	
	}
	
	public Ponto getPontoMedio() {
		int x, y;
		
		//xm = (x1 + x2)/2
		x = new Double( (p1.getX() + p2.getX())/2 ).intValue();
		y = new Double( (p1.getY() + p2.getY())/2 ).intValue();
		
		return new Ponto(x, y);
		
	}
	

	public int getComprimento() {
		double x = Math.pow((p2.getX() - p1.getX()), 2);
		double y = Math.pow((p2.getY() - p1.getY()), 2);
		
		double comprimento = Math.sqrt(x + y);
		
		return new Double(comprimento).intValue();
	}

	public Ponto getP1() {
		return this.p1;
	}
	
	public Ponto getP2() {
		return this.p2;
	}
	

	public void setP1(Ponto ponto) {
		this.p1 = ponto;
	}
	
	public void setP2(Ponto ponto) {
		this.p2 = ponto;
	}
	
	
}
