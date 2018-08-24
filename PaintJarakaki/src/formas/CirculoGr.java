package formas;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import math.Circulo;
import math.Ponto;

public class CirculoGr extends Circulo {

	public CirculoGr(Ponto p1, Ponto p2) {
		super(p1, p2);
	}
	
	public void desenharCirculo(GraphicsContext g) {
		ArrayList<int[]> alCoordenadas = this.calcularCirculo();
		
		for (int[] iCoordenadas : alCoordenadas) {
			new PontoGr(iCoordenadas[0], iCoordenadas[1]).desenharPonto(g);;
		}
	}
	
}
