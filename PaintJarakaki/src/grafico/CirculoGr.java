package grafico;

import java.util.ArrayList;

import formas.Circulo;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class CirculoGr extends Circulo implements FormaGr {
	
	Color cCor;
	int iBorda;

	public CirculoGr(Color cCor, int iBorda) {
		this.cCor = cCor;
		this.iBorda = iBorda;
	}
	
	public void desenhar(Pane pane) {
		ArrayList<int[]> alCoordenadas = this.calcularCirculo();
		
		for (int[] iCoordenadas : alCoordenadas) {
			new PontoGr(iCoordenadas[0], iCoordenadas[1], cCor, iBorda).desenhar(pane);;
		}
	}

	
}
