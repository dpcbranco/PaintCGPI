package formas;

import java.util.ArrayList;

public class LinhaPoligonal implements Formas{
	
	ArrayList<Ponto> pontos = new ArrayList<>();
	
	public LinhaPoligonal (Ponto p1) {
		pontos.add(p1);
	}
	
	
	public Linha calcularNovaLinha(Ponto novoPonto) {
		Linha novaLinha = new Linha();
		
		novaLinha.setP1(pontos.get(pontos.size() - 1));
		novaLinha.setP2(novoPonto);
		
		pontos.add(novoPonto);
		return novaLinha;
	}
	
	
	public Ponto getP1() {
		return pontos.get(0);
	}
	
	//retorna último ponto do polígono
	public Ponto getPN() {
		return pontos.get(pontos.size() - 1);
	}
	
	//retorna lista de pontos da L. Poligonal
	public ArrayList<Ponto> getPontos(){
		return pontos;
	}
}
