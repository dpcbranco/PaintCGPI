package formas;

import java.util.ArrayList;

public class Retangulo implements Formas{

	Ponto p1, p2, p3, p4;
	
	//Pontos da diagonal do retângulo
	public Retangulo(Ponto p1, Ponto p2) {
		this.p1 = p1;
		this.p2 = p2;
	}
	
	public ArrayList<Linha> calcularRetangulo(){
		ArrayList<Linha> alRetas = new ArrayList<>();		
		p3 = new Ponto((int)p2.getX(), (int)p1.getY());
		p4 = new Ponto((int)p1.getX(), (int)p2.getY());
		
		alRetas.add(new Linha(p1, p3));		
		alRetas.add(new Linha(p1, p4));
		alRetas.add(new Linha(p2, p3));
		alRetas.add(new Linha(p2, p4));
		
		return alRetas;
	}
	
	public Ponto getP1() {
		return p1;
	}
	
	public Ponto getP2() {
		return p2;
	}
	
	public void setP1(Ponto p) {
		this.p1 = p;
	}
	
	public void setP2(Ponto p) {
		this.p2 = p;
	}
	
	public Ponto getP3() {
		return p3;
	}

	public void setP3(Ponto p3) {
		this.p3 = p3;
	}

	public Ponto getP4() {
		return p4;
	}

	public void setP4(Ponto p4) {
		this.p4 = p4;
	}

}
