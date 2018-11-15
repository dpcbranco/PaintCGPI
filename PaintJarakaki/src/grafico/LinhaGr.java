package grafico;

import java.util.ArrayList;

import app.Quadro;
import formas.Linha;
import formas.Ponto;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

public class LinhaGr extends Linha implements FormaGr{

	Color cor;
	int borda;
	
	ArrayList<PontoGr> pontosLinha = new ArrayList<>();
	
	public LinhaGr(Color cor, int borda) {
		this.cor = cor;
		this.borda = borda;
	}
	
	public LinhaGr (PontoGr p1, PontoGr p2, Color cor, int borda) {
		super((Ponto)p1, (Ponto)p2);
		this.cor = cor;
		this.borda = borda;
	}
	
	public LinhaGr(Linha l, Color cor, int borda){
		setP1(l.getP1());
		setP2(l.getP2());
		
		this.cor = cor;
		this.borda = borda;
	}
	
	public LinhaGr(Ponto p1, Ponto p2, Color cor, int borda) {
		super(p1, p2);
		this.cor = cor;
		this.borda = borda;
	}

	public void desenhar(Pane pane) {
		ArrayList<Ponto> pontosCalculo;
		pontosCalculo = this.calcularLinha();
		
		for (Ponto p : pontosCalculo) {
			PontoGr novoPonto = new PontoGr(p, cor, borda);
			novoPonto.desenhar(pane);
			novoPonto.getEllipse().setOnMouseClicked(
				(ev) ->{
					if (Quadro.getSelecionar()) {
						selecionar();
					}
				}
			);
			
			if (p.equals(p1) || p.equals(p2)) {
				novoPonto.getEllipse().setOnMouseDragged(
					(ev)->{
						rotacao(ev.getX(), ev.getY());
					}
				);
			}
			
			else {
				novoPonto.getEllipse().setOnMouseDragged(
					(ev)->{
						if (Quadro.getMover()) {
							mover(ev.getX() - novoPonto.getX(), ev.getY() - novoPonto.getY());
						}
					}
				);
			}
			
			pontosLinha.add(novoPonto);
		}
	}

	@Override
	public void rotacao(double x, double y) {
		
	}

	public void selecionar() {
		for (PontoGr pgr : pontosLinha) {
			Ponto p = (Ponto) pgr;
			Ellipse e = pgr.getEllipse();
			
			if (!p.equals(p1) && !p.equals(p2)) {
				e.setOpacity(0.1);
			}
		}
	}

	public ArrayList<PontoGr> getPontos() {
		return this.pontosLinha;
	}

	public Color getCor() {
		return this.cor;
	}

	@Override
	public void mover(double x, double y) {
		for (PontoGr p : pontosLinha) {
			p.setX(p.getX() + x);
			p.setY(p.getY() + y);
		}
		
		p1.setX(p1.getX() + x);
		p1.setY(p1.getY() + y);
		
		p2.setX(p2.getX() + x);
		p2.setY(p2.getY() + y);
	}

}
