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
	boolean selecionado = false;
	
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
		LinhaGr novaLinha = new LinhaGr(this.calcularNovaLinha(new Ponto(p)), cor, borda);
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
		
		if (selecionado) {
			for (PontoGr p : pontosLPoligonal) {
				Ellipse e = p.getEllipse();
				e.setOpacity(1);
			}
			
			selecionado = false;
		}
		
		else {
			for (PontoGr p : pontosLPoligonal) {
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
		for (PontoGr pgr : pontosLPoligonal) {
			pgr.setX(pgr.getX() + x);
			pgr.setY(pgr.getY() + y);
		}
		
		for (Ponto p : this.getPontos()) {
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
		for (PontoGr p : pontosLPoligonal) {
			if (p.getX() == this.getP1().getX() && p.getY() == this.getP1().getY()) {
				
				p.getEllipse().setOnMouseDragged(   
						(ev)->{
							if (Quadro.getRotacionar()) {
								double anguloAtual = Math.atan2(this.getPN().getY() - this.getP1().getY(), this.getPN().getX() - this.getP1().getX());
								double novoAngulo = Math.atan2(this.getPN().getY() - ev.getY(), this.getPN().getX() - ev.getX());
								rotacao(this.getPN(), novoAngulo - anguloAtual);
							}
							
							else if (Quadro.getMover()) {
								mover(ev.getX() - p.getX(), ev.getY() - p.getY());
							}
						}
					);
				
				if (cor.equals(Color.RED)) {
					p.setCor(Color.BLACK);
				}
				
				else {
					p.setCor(Color.RED);
				}
			}
			
			else if (p.getX() == this.getPN().getX() && p.getY() == this.getPN().getY()) {
				
				p.getEllipse().setOnMouseDragged(   
						(ev)->{
							if (Quadro.getRotacionar()) {
								double anguloAtual = Math.atan2(this.getPN().getY() - this.getP1().getY(), this.getPN().getX() - this.getP1().getX());
								double novoAngulo = Math.atan2(ev.getY() - this.getP1().getY(), ev.getX() - this.getP1().getX());
								rotacao(this.getP1(), novoAngulo - anguloAtual);
							}
							
							else if (Quadro.getMover()) {
								mover(ev.getX() - p.getX(), ev.getY() - p.getY());
							}
						}
					);
				
				if (cor.equals(Color.RED)) {
					p.setCor(Color.BLACK);
				}
				
				else {
					p.setCor(Color.RED);
				}
			}
		}
	}

	@Override
	public void rotacao(Ponto pBase, double angulo) {
		for (PontoGr p : pontosLPoligonal) {
			double x = pBase.getX() + (p.getX() - pBase.getX()) * Math.cos(angulo) - (p.getY() - pBase.getY()) * Math.sin(angulo);
			double y = pBase.getY() + (p.getX() - pBase.getX()) * Math.sin(angulo) + (p.getY() - pBase.getY()) * Math.cos(angulo);
			
			p.setX(x);
			p.setY(y);
		}
		
		if (pBase.getX() == this.getP1().getX() && pBase.getY() == this.getP1().getY()) {
			double x = pBase.getX() + (this.getPN().getX() - pBase.getX()) * Math.cos(angulo) - (this.getPN().getY() - pBase.getY()) * Math.sin(angulo);
			double y = pBase.getY() + (this.getPN().getX() - pBase.getX()) * Math.sin(angulo) + (this.getPN().getY() - pBase.getY()) * Math.cos(angulo);
			
			this.getPN().setX(x);
			this.getPN().setY(y);
		}
		
		else {
			double x = pBase.getX() + (this.getP1().getX() - pBase.getX()) * Math.cos(angulo) - (this.getP1().getY() - pBase.getY()) * Math.sin(angulo);
			double y = pBase.getY() + (this.getP1().getX() - pBase.getX()) * Math.sin(angulo) + (this.getP1().getY() - pBase.getY()) * Math.cos(angulo);
			
			this.getP1().setX(x);
			this.getP1().setY(y);
		}
	}

	@Override
	public void escala(Pane pane, Ponto pBase) {
		// TODO Auto-generated method stub
		
	}
	
}
