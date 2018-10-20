package formas;

import java.util.ArrayList;

public class Poligono implements Formas{
	
	ArrayList<Ponto> pontos = new ArrayList<>();
	
	public Poligono (Ponto p1) {
		pontos.add(p1);
	}
	
	
	public Linha calcularNovaLinha(Ponto novoPonto) {
		Linha novaLinha = new Linha();
		
		novaLinha.setP1(pontos.get(pontos.size() - 1));
		novaLinha.setP2(novoPonto);
		
		pontos.add(novoPonto);
		return novaLinha;
	}
	
	//Pega primeiro ponto
	public Ponto getP1() {
		return pontos.get(0);
	}
	
	//retorna último ponto do polígono
	public Ponto getPN() {
		return pontos.get(pontos.size() - 1);
	}
	
	//Retorna lista de pontos
	public ArrayList<Ponto> getPontos(){
		return this.pontos;
	}
	
}
