package grafico;

import java.util.ArrayList;

import formas.Linha;
import formas.Ponto;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

public class LinhaGr extends Linha implements FormaGr{

	Color cor;
	int borda;
	
	ArrayList<PontoGr> pontosLinha = new ArrayList<>();
	
	public LinhaGr(Color cor, int borda) {
		this.cor = cor;
		this.borda = borda;
	}
	
	public LinhaGr (PontoGr p1, PontoGr p2, Color cor, int borda) {
		super(p1, p2);
		this.cor = cor;
		this.borda = borda;
	}
	
	public LinhaGr(Linha l, Color cor, int borda){
		setP1(l.getP1());
		setP2(l.getP2());
		
		this.cor = cor;
		this.borda = borda;
	}
	
	public LinhaGr(Ponto p1, Ponto p2, Color cor, int borda) {
		super(p1, p2);
		this.cor = cor;
		this.borda = borda;
	}

	public void desenhar(Pane pane) {
		ArrayList<Ponto> pontosCalculo;
		pontosCalculo = this.calcularLinha();
		
		for (Ponto p : pontosCalculo) {
			PontoGr novoPonto = new PontoGr(p, cor, borda);
			novoPonto.desenhar(pane);
			novoPonto.getEllipse().setOnMouseClicked(
				(ev) ->{
					selecionar();
				}
			);
			pontosLinha.add(novoPonto);
		}
	}

	public void selecionar() {
		for (PontoGr p : pontosLinha) {
			Ellipse e = p.getEllipse();
			e.setOpacity(0.1);
		}
	}

	public ArrayList<PontoGr> getPontos() {
		return this.pontosLinha;
	}

	public Color getCor() {
		return this.cor;
	}

}
