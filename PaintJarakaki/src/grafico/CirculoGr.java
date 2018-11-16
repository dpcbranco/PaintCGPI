package grafico;

import java.util.ArrayList;

import app.Quadro;
import formas.Circulo;
import formas.Ponto;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

public class CirculoGr extends Circulo implements FormaGr {
	
	Color cor;
	int borda;
	ArrayList<PontoGr> pontosCirculo = new ArrayList<>();
	boolean selecionado = false;

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
			
			p.getEllipse().setOnMouseClicked( 
				(ev)->{
					if (Quadro.getSelecionar()) {
						selecionar();
					}
				}
			);
			
			p.getEllipse().setOnMouseDragged(
				(ev)->{
					if (Quadro.getMover()) {
						mover(ev.getX() - p.getX(), ev.getY() - p.getY());
					}
				}
			);
		}
	}

	public void selecionar() {
		if (selecionado) {
			for (PontoGr p : pontosCirculo) {
				Ellipse e = p.getEllipse();
				e.setOpacity(1);
			}
			
			selecionado = false;
		}
		
		else {
			for (PontoGr p : pontosCirculo) {
				Ellipse e = p.getEllipse();
				e.setOpacity(0.1);
			}
			
			selecionado = true;
		}
	}

	public Color getCor() {
		return this.cor;
	}

	@Override
	public void mover(double x, double y) {
		for (PontoGr p : pontosCirculo) {
			p.setX(p.getX() + x);
			p.setY(p.getY() + y);
		}
	}


	@Override
	public boolean selecionado() {
		return selecionado;
	}

	@Override
	public void marcarRotacao() {
		
	}

	@Override
	public void rotacao(Ponto pBase, double angulo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void escala(Pane pane, Ponto pBase) {
		// TODO Auto-generated method stub
		
	}

	
}
