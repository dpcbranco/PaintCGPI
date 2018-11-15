package grafico;

import java.util.ArrayList;

import formas.Linha;
import formas.Ponto;
import formas.Retangulo;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

public class RetanguloGr extends Retangulo implements FormaGr {

	Color cor;
	int borda;
	ArrayList<PontoGr> pontosRetangulo = new ArrayList<>();
	
	public RetanguloGr(Ponto p1, Ponto p2, Color cor, int borda) {
		super(p1, p2);
		this.cor = cor;
		this.borda = borda;
	}

	public void desenhar(Pane pane) {
		ArrayList<Linha> retasRetangulo = this.calcularRetangulo();
		LinhaGr retaGrafica;
		
		for (Linha reta : retasRetangulo) {
			retaGrafica = new LinhaGr(reta, cor, borda);	
			retaGrafica.desenhar(pane);
			pontosRetangulo.addAll(retaGrafica.getPontos());
		}
		
		for (PontoGr ponto : pontosRetangulo) {
			Ellipse e = ponto.getEllipse();
			
			e.setOnMouseClicked(  
				(ev)-> {
					this.selecionar();
				}
			);
			
			e.setOnMouseDragged(
				(ev)->{
					mover(ev.getX() - ponto.getX(), ev.getY() - ponto.getY());
				}
			);
		}
	}

	public void selecionar() {
		for (PontoGr p : pontosRetangulo) {
			Ellipse e = p.getEllipse();
			e.setOpacity(0.1);
		}
	}


	public Color getCor() {
		return this.cor;
	}
	
	public ArrayList<PontoGr> getPontosRetangulo(){
		return pontosRetangulo;
	}

	@Override
	public void mover(double x, double y) {
		for (PontoGr p : pontosRetangulo) {
			p.setX(p.getX() + x);
			p.setY(p.getY() + y);
		}
	}

	@Override
	public void rotacao(double x, double y) {
		// TODO Auto-generated method stub
		
	}

}
