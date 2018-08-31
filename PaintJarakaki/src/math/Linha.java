package math;

import java.util.ArrayList;

public class Linha {
	
	Ponto p1, p2, pAux;
	
	public Linha (Ponto p1, Ponto p2) {
		this.p1 = p1;
		this.p2 = p2;
	}
	
	protected ArrayList<int[]> calcularLinha(){
		ArrayList<int[]> alCoordenadas = new ArrayList<>();
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
	private ArrayList<int[]> calcularCoordX(){
		double dTg = (p2.getY() - p1.getY())/(p2.getX() - p1.getX());
		double dAltura = p1.getY() - dTg * p1.getX();
		ArrayList<int[]> aAux = new ArrayList<>();
		int dY;
		
		for (int dX = (int)p1.getX(); dX < (int)p2.getX(); dX++) {
			dY = new Double((dTg*dX) + dAltura).intValue();
			aAux.add(new int[] {dX, dY});
		}
		
		return aAux;
	}
	
	//Calcular linha pela coordenada Y
	private ArrayList<int[]> calcularCoordY(){
		double dTg = (p2.getY() - p1.getY())/(p2.getX() - p1.getX());
		double dAltura = p1.getY() - dTg * p1.getX();
		ArrayList<int[]> aAux = new ArrayList<>();
		int dX;
		
		for (int dY = (int)p1.getY(); dY < (int)p2.getY(); dY++) {
			dX = new Double((dY - dAltura)/dTg).intValue();
			aAux.add(new int[] {dX, dY});
		}
		
		return aAux;
	}
	
	private ArrayList<int[]> calcularLinhaVertical(){
		
		ArrayList<int[]> aAux = new ArrayList<>();
		
		if (p1.getY() > p2.getY()) {
			pAux = p1;
			p1 = p2;
			p2 = pAux;
		}
		
		for (int i = (int) p1.getY(); i < (int) p2.getY(); i++) {
			aAux.add(new int[]{(int) p1.getX(), i});
		}
		
		return aAux;
	
	}
	
	private ArrayList<int[]> calcularLinhaHorizontal(){
		
		ArrayList<int[]> aAux = new ArrayList<>();
		
		if (p1.getX() > p2.getX()) {
			pAux = p1;
			p1 = p2;
			p2 = pAux;
		}
		
		for (int i = (int) p1.getX(); i < (int) p2.getX(); i++) {
			aAux.add(new int[]{i, (int) p1.getY()});
		}
		
		return aAux;
	
	}
	
	public Ponto pontoMedio() {
		int x, y;
		
		//xm = (x1 + x2)/2
		x = new Double( (p1.getX() + p2.getX())/2 ).intValue();
		y = new Double( (p1.getY() + p2.getY())/2 ).intValue();
		
		return new Ponto(x, y);
		
	}
}
