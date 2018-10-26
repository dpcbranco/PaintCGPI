package grafico;

import java.util.ArrayList;

import formas.Linha;
import formas.Poligono;
import formas.Ponto;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

public class PoligonoGr extends Poligono implements FormaGr{

	Color cor;
	int borda;
	ArrayList<PontoGr> pontosPoligono = new ArrayList<>();
	
	public PoligonoGr(Ponto p1, Color cor, int borda) {
		super(p1);
		this.cor = cor;
		this.borda = borda;
	}
	
	public PoligonoGr(ArrayList<Ponto> pontos, Color cor, int borda) {
		super(pontos);
		this.cor = cor;
		this.borda = borda;
	}

	public void desenharPonto(Ponto p, Pane pane) {
		LinhaGr novaLinha = new LinhaGr(this.calcularNovaLinha(p), cor, borda);
		novaLinha.desenhar(pane);
		
		pontosPoligono.addAll(novaLinha.getPontos());
	}

	public void selecionar() {
		for (PontoGr p : pontosPoligono) {
			Ellipse e = p.getEllipse();
			e.setOpacity(0.1);
		}
	}

	public void finalizarPoligono(Pane pane) {
		LinhaGr novaLinha = new LinhaGr(this.calcularNovaLinha(this.getP1()), cor, borda);
		novaLinha.desenhar(pane);
		
		for (PontoGr ponto : novaLinha.getPontos()) {
			ponto.getEllipse().setOnMouseClicked( 
				(ev)->{
					selecionar();
				}
			);
		}
		
	}
	
	//Usado na leitura do XML - Quando todos os pontos do poligono estão definidos
	public void desenhar (Pane pane) {
		ArrayList<Linha> linhasPoligono = this.calcularPoligono();
		
		for (Linha l : linhasPoligono) {
			LinhaGr novaLinha = new LinhaGr(l, cor, borda);
			novaLinha.desenhar(pane);
			
			for (PontoGr ponto : novaLinha.getPontos()) {
				ponto.getEllipse().setOnMouseClicked( 
					(ev)->{
						selecionar();
					}
				);
			}
			
			pontosPoligono.addAll(novaLinha.getPontos());
		}
	}

	public Color getCor() {
		return this.cor;
	}
	
	public void setPontosPoligono(ArrayList<PontoGr> pontosPoligono) {
		this.pontosPoligono = pontosPoligono;
	}
}
