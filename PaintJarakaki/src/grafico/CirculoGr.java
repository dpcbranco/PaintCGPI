package grafico;

import java.util.ArrayList;

import formas.Circulo;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

public class CirculoGr extends Circulo implements FormaGr {
	
	Color cCor;
	int iBorda;
	ArrayList<PontoGr> pontosCirculo = new ArrayList<>();

	public CirculoGr(Color cCor, int iBorda) {
		this.cCor = cCor;
		this.iBorda = iBorda;
	}
	
	public void desenhar(Pane pane) {
		ArrayList<int[]> alCoordenadas = this.calcularCirculo();
		
		for (int[] iCoordenadas : alCoordenadas) {
			PontoGr p = new PontoGr(iCoordenadas[0], iCoordenadas[1], cCor, iBorda);
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
		return this.cCor;
	}

	
}
