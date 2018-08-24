package formas;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import math.Linha;
import math.Ponto;

public class LinhaGr extends Linha {

	public LinhaGr(Ponto p1, Ponto p2) {
		super(p1, p2);
	}
	
	public void desenharLinha(GraphicsContext g) {
		ArrayList<int[]> alCoordenadas = this.calcularLinha();
		
		for (int[] iCoordenadas : alCoordenadas) {
			new PontoGr(iCoordenadas[0], iCoordenadas[1]).desenharPonto(g);;
		}
	}
	
}
