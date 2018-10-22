package formas;

import java.util.ArrayList;

public class Poligono implements Formas{
	
	protected ArrayList<Ponto> pontos = new ArrayList<>();
	
	public Poligono (Ponto p1) {
		pontos.add(p1);
	}
	
	public Poligono (ArrayList<Ponto> pontos) {
		this.pontos = pontos;
	}
	
	
	public Linha calcularNovaLinha(Ponto novoPonto) {
		Linha novaLinha = new Linha();
		
		novaLinha.setP1(pontos.get(pontos.size() - 1));
		novaLinha.setP2(novoPonto);
		
		//N�o insere o P1 duplicado
		if (!pontos.contains(novoPonto)) {
			pontos.add(novoPonto);
		}
		
		return novaLinha;
	}
	
	//Usado apenas caso poligono j� esteja com todos os pontos definidos (Leitura XML)
	protected ArrayList<Linha> calcularPoligono() {
		Ponto pAnterior = null; //Usado para tra�ar as linhas
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
		
		//Adiciona linha ligando �tlimo e primeiro pontos
		linhas.add(new Linha(pontos.get(0), pontos.get(pontos.size() - 1)));
		
		return linhas;
	}
	
	//Pega primeiro ponto
	public Ponto getP1() {
		return pontos.get(0);
	}
	
	//retorna �ltimo ponto do pol�gono
	public Ponto getPN() {
		return pontos.get(pontos.size() - 1);
	}
	
	//Retorna lista de pontos
	public ArrayList<Ponto> getPontos(){
		return this.pontos;
	}
	
	
}
