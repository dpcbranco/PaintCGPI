package formas;

import java.util.ArrayList;

public class Poligono {
	
	ArrayList<Ponto> pontos = new ArrayList<>();
	
	public Poligono (Ponto p1) {
		pontos.add(p1);
	}
	
	
	public Linha calcularNovaLinha(Ponto novoPonto) {
		Linha novaLinha = new Linha(pontos.get(pontos.size() - 1), novoPonto);
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
	
}
