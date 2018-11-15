package grafico;

import java.util.ArrayList;

import app.Quadro;
import formas.Linha;
import formas.LinhaPoligonal;
import formas.Ponto;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

public class LinhaPoligonalGr extends LinhaPoligonal implements FormaGr{
	Color cor;
	int borda;
	ArrayList<PontoGr> pontosLPoligonal = new ArrayList<>();
	
	public LinhaPoligonalGr(Ponto p1, Color cor, int borda) {
		super(p1);
		this.cor = cor;
		this.borda = borda;
	}
	
	public LinhaPoligonalGr(ArrayList<Ponto> pontos, Color cor, int borda) {
		super(pontos);
		this.cor = cor;
		this.borda = borda;
	}

	public void desenharPonto(Ponto p, Pane pane) {
		LinhaGr novaLinha = new LinhaGr(this.calcularNovaLinha(p), cor, borda);
		novaLinha.desenhar(pane);
		
		pontosLPoligonal.addAll(novaLinha.getPontos());
		
		for (PontoGr ponto : pontosLPoligonal) {
			ponto.getEllipse().setOnMouseClicked( 
				(ev)->{
					if (Quadro.getSelecionar()) {
						selecionar();
					}
				}
			);
			
			ponto.getEllipse().setOnMouseDragged(
				(ev)->{
					if (Quadro.getMover()) {
						mover(ev.getX() - ponto.getX(), ev.getY() - ponto.getY());
					}
				}
			);
		}
	}
	
	//Usado na leitura do XML - Quando todos os pontos do poligono estão definidos
	public void desenhar (Pane pane) {
		ArrayList<Linha> linhasPoligono = this.calcularLPoligonal();
			
		for (Linha l : linhasPoligono) {
			LinhaGr novaLinha = new LinhaGr(l, cor, borda);
			novaLinha.desenhar(pane);
			
			for (PontoGr ponto : novaLinha.getPontos()) {
				ponto.getEllipse().setOnMouseClicked( 
					(ev)->{
						selecionar();
					}
				);
			}
				
			pontosLPoligonal.addAll(novaLinha.getPontos());
		}
	}

	public void selecionar() {
		for (PontoGr p : pontosLPoligonal) {
			Ellipse e = p.getEllipse();
			e.setOpacity(0.1);
		}
	}

	public Color getCor() {
		return this.cor;
	}

	@Override
	public void mover(double x, double y) {
		for (PontoGr p : pontosLPoligonal) {
			p.setX(p.getX() + x);
			p.setY(p.getY() + y);
		}
	}

	@Override
	public void rotacao(double x, double y) {
		// TODO Auto-generated method stub
		
	}
	
}
