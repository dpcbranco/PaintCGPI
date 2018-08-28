package formas;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import math.Circulo;
import math.Ponto;

public class CirculoGr extends Circulo {
	
	Color cCor;
	int iBorda;

	public CirculoGr(Ponto p1, Ponto p2, Color cCor, int iBorda) {
		super(p1, p2);
		this.cCor = cCor;
		this.iBorda = iBorda;
	}
	
	public void desenharCirculo(GraphicsContext g) {
		ArrayList<int[]> alCoordenadas = this.calcularCirculo();
		
		for (int[] iCoordenadas : alCoordenadas) {
			new PontoGr(iCoordenadas[0], iCoordenadas[1], cCor, iBorda).desenharPonto(g);;
		}
	}
	
}
