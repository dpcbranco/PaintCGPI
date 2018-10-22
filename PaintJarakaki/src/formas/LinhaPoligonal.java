package formas;

import java.util.ArrayList;

public class LinhaPoligonal implements Formas{
	
	ArrayList<Ponto> pontos = new ArrayList<>();
	
	public LinhaPoligonal (Ponto p1) {
		pontos.add(p1);
	}
	
	public LinhaPoligonal (ArrayList<Ponto> pontos) {
		this.pontos = pontos;
	}
	
	
	public Linha calcularNovaLinha(Ponto novoPonto) {
		Linha novaLinha = new Linha();
		
		novaLinha.setP1(pontos.get(pontos.size() - 1));
		novaLinha.setP2(novoPonto);
		
		pontos.add(novoPonto);
		return novaLinha;
	}
	
	//Usado apenas caso poligono já esteja com todos os pontos definidos (Leitura XML)
	protected ArrayList<Linha> calcularLPoligonal() {
		Ponto pAnterior = null; //Usado para traçar as linhas
		ArrayList<Linha> linhas = new ArrayList<>();
		
		for(Ponto p : pontos) {
			if (pAnterior == null) {
				pAnterior = p;
			}
			
			else {
				Linha l = new Linha(pAnterior, p);
				linhas.add(l);
				pAnterior = p;
			}
		}
		
		return linhas;
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
