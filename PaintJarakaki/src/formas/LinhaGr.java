package formas;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import math.Linha;
import math.Ponto;

public class LinhaGr extends Linha {

	Color cCor;
	int iBorda;
	
	public LinhaGr(Ponto p1, Ponto p2, Color cCor, int iBorda) {
		super(p1, p2);
		this.cCor = cCor;
		this.iBorda = iBorda;
	}
	
	public void desenharLinha(GraphicsContext g) {
		ArrayList<int[]> alCoordenadas = this.calcularLinha();
		
		for (int[] iCoordenadas : alCoordenadas) {
			new PontoGr(iCoordenadas[0], iCoordenadas[1], cCor, iBorda).desenharPonto(g);;
		}
	}
	
}
