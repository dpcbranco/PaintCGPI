package grafico;

import java.util.ArrayList;

import app.Quadro;
import formas.Linha;
import formas.Poligono;
import formas.Ponto;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

public class PoligonoGr extends Poligono implements FormaGr{

	Color cor;
	int borda;
	ArrayList<PontoGr> pontosPoligono = new ArrayList<>();
	boolean selecionado = false;
	
	public PoligonoGr(Ponto p1, Color cor, int borda) {
		super(p1);
		this.cor = cor;
		this.borda = borda;
	}
	
	public PoligonoGr(ArrayList<Ponto> pontos, Color cor, int borda) {
		super(pontos);
		this.cor = cor;
		this.borda = borda;
	}

	public void desenharPonto(Ponto p, Pane pane) {
		LinhaGr novaLinha = new LinhaGr(this.calcularNovaLinha(p), cor, borda);
		novaLinha.desenhar(pane);
		
		pontosPoligono.addAll(novaLinha.getPontos());
	}

	public void selecionar() {
		if (selecionado) {
			for (PontoGr p : pontosPoligono) {
				Ellipse e = p.getEllipse();
				e.setOpacity(1);
			}
			
			selecionado = false;
		}
		
		else {
			for (PontoGr p : pontosPoligono) {
				Ellipse e = p.getEllipse();
				e.setOpacity(0.1);
			}
			
			selecionado = true;
		}
	}

	public void finalizarPoligono(Pane pane) {
		LinhaGr novaLinha = new LinhaGr(this.calcularNovaLinha(this.getP1()), cor, borda);
		novaLinha.desenhar(pane);
		
		for (PontoGr ponto : novaLinha.getPontos()) {
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
		
		pontosPoligono.addAll(novaLinha.getPontos());
		
	}
	
	//Usado na leitura do XML - Quando todos os pontos do poligono estão definidos
	public void desenhar (Pane pane) {
		ArrayList<Linha> linhasPoligono = this.calcularPoligono();
		
		for (Linha l : linhasPoligono) {
			LinhaGr novaLinha = new LinhaGr(l, cor, borda);
			novaLinha.desenhar(pane);
			
			for (PontoGr ponto : novaLinha.getPontos()) {
				ponto.getEllipse().setOnMouseClicked( 
					(ev)->{
						selecionar();
					}
				);
				
				ponto.getEllipse().setOnMouseDragged(
						(ev)->{
							mover(ev.getX() - ponto.getX(), ev.getY() - ponto.getY());
						}
				);
			}
			
			pontosPoligono.addAll(novaLinha.getPontos());
		}
	}

	public Color getCor() {
		return this.cor;
	}
	
	public void setPontosPoligono(ArrayList<PontoGr> pontosPoligono) {
		this.pontosPoligono = pontosPoligono;
	}

	@Override
	public void mover(double x, double y) {
		for (PontoGr p : pontosPoligono) {
			p.setX(p.getX() + x);
			p.setY(p.getY() + y);
		}
	}

	@Override
	public void rotacao(double x, double y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean selecionado() {
		return selecionado;
	}
}
