package grafico;

import java.util.ArrayList;

import formas.Circulo;
import formas.Ponto;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

public class CirculoGr extends Circulo implements FormaGr {
	
	Color cor;
	int borda;
	ArrayList<PontoGr> pontosCirculo = new ArrayList<>();

	public CirculoGr(Ponto centro, Color cor, int borda) {
		super(centro);
		this.cor = cor;
		this.borda = borda;
	}
	
	public CirculoGr(Ponto centro, double raio, Color cor, int borda) {
		super(centro, raio);
		this.cor = cor;
		this.borda = borda;
	}
	
	public void desenhar(Pane pane) {
		ArrayList<int[]> alCoordenadas = this.calcularCirculo();
		
		for (int[] iCoordenadas : alCoordenadas) {
			PontoGr p = new PontoGr(iCoordenadas[0], iCoordenadas[1], cor, borda);
			p.desenhar(pane);
			pontosCirculo.add(p);
			
			p.obterElipse().setOnMouseClicked( 
				(ev)->{
					selecionar();
				}
			);
		}
	}

	private void selecionar() {
		for (PontoGr p : pontosCirculo) {
			Ellipse e = p.obterElipse();
			e.setStroke(Color.FUCHSIA);
		}
	}

	public Color getCor() {
		return this.cor;
	}

	
}
