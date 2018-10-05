package grafico;

import java.util.ArrayList;

import formas.Linha;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

public class LinhaGr extends Linha implements FormaGr{

	Color cor;
	int borda;
	
	ArrayList<int[]> alCoordenadas;
	ArrayList<PontoGr> pontosLinha = new ArrayList<>();
	
	public LinhaGr(Color cor, int borda) {
		this.cor = cor;
		this.borda = borda;
	}
	
	public LinhaGr (PontoGr p1, PontoGr p2, Color cor, int borda) {
		this.p1 = p1;
		this.p2 = p2;
		this.cor = cor;
		this.borda = borda;
	}
	
	public LinhaGr(Linha l, Color cor, int borda){
		setP1(l.getP1());
		setP2(l.getP2());
		
		this.cor = cor;
		this.borda = borda;
	}
	
	public void desenhar(Pane pane) {
		alCoordenadas = this.calcularLinha();
		
		for (int[] iCoordenadas : alCoordenadas) {
			PontoGr novoPonto = new PontoGr(iCoordenadas[0], iCoordenadas[1], cor, borda);
			novoPonto.desenhar(pane);
			novoPonto.obterElipse().setOnMouseClicked(
				(ev) ->{
					selecionar();
				}
			);
			pontosLinha.add(novoPonto);
		}
	}

	private void selecionar() {
		for (PontoGr p : pontosLinha) {
			Ellipse e = p.obterElipse();
			e.setFill(Color.FUCHSIA);
		}
	}

	public ArrayList<PontoGr> getPontos() {
		return this.pontosLinha;
	}

}
