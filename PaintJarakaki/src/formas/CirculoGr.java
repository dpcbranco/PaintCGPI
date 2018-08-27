package formas;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import math.Circulo;
import math.Ponto;

public class CirculoGr extends Circulo {
	
	Color cCor;

	public CirculoGr(Ponto p1, Ponto p2, Color cCor) {
		super(p1, p2);
		this.cCor = cCor;
	}
	
	public void desenharCirculo(GraphicsContext g) {
		ArrayList<int[]> alCoordenadas = this.calcularCirculo();
		
		for (int[] iCoordenadas : alCoordenadas) {
			new PontoGr(iCoordenadas[0], iCoordenadas[1], cCor).desenharPonto(g);;
		}
	}
	
}
