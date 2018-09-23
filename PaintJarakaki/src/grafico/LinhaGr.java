package grafico;

import java.util.ArrayList;

import formas.Linha;
import formas.Ponto;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class LinhaGr extends Linha {

	Color cCor;
	int iBorda;
	
	ArrayList<int[]> alCoordenadas;
	
	public LinhaGr(Ponto p1, Ponto p2, Color cCor, int iBorda) {
		super(p1, p2);
		this.cCor = cCor;
		this.iBorda = iBorda;
	}
	
	public void desenharLinha(GraphicsContext g) {
		alCoordenadas = this.calcularLinha();
		
		for (int[] iCoordenadas : alCoordenadas) {
			new PontoGr(iCoordenadas[0], iCoordenadas[1], cCor, iBorda).desenharPonto(g);;
		}
	}

	public void apagarLinha(GraphicsContext g) {
		for (int[] iCoordenadas : alCoordenadas) {
			new PontoGr(iCoordenadas[0], iCoordenadas[1], Color.WHITE, iBorda).desenharPonto(g);;
		}
	}
	
}
