package grafico;

import java.util.ArrayList;

import formas.Linha;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class LinhaGr extends Linha implements FormaGr{

	Color cor;
	int borda;
	
	ArrayList<int[]> alCoordenadas;
	
	public LinhaGr(Color cor, int borda) {
		this.cor = cor;
		this.borda = borda;
	}
	
	public LinhaGr(Linha l, Color cor, int borda){
		setP1(l.getP1());
		setP2(l.getP2());
		
		this.cor = cor;
		this.borda = borda;
	}
	
	public void desenhar(GraphicsContext g) {
		alCoordenadas = this.calcularLinha();
		
		for (int[] iCoordenadas : alCoordenadas) {
			new PontoGr(iCoordenadas[0], iCoordenadas[1], cor, borda).desenhar(g);;
		}
	}

	public void apagarLinha(GraphicsContext g) {
		for (int[] iCoordenadas : alCoordenadas) {
			new PontoGr(iCoordenadas[0], iCoordenadas[1], Color.WHITE, borda).desenhar(g);;
		}
	}

}
