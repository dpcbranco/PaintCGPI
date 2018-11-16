package grafico;

import java.util.ArrayList;

import app.Quadro;
import formas.Linha;
import formas.Ponto;
import formas.Triangulo;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

public class TrianguloGr extends Triangulo implements FormaGr {
	
	Color cCor;
	int iBorda;
	ArrayList<PontoGr> pontosTriangulo = new ArrayList<>();
	boolean selecionado = false;


	public TrianguloGr(Ponto p1, Ponto p2, Ponto p3, Color cor, int borda) {
		super(p1, p2, p3);
		cCor = cor;
		iBorda = borda;
	}
	
	public void desenhar(Pane pane) {
		
		LinhaGr lgrReta1, lgrReta2, lgrReta3;
		
		lgrReta1 = new LinhaGr(cCor, iBorda);
		lgrReta2 = new LinhaGr(cCor, iBorda);
		lgrReta3 = new LinhaGr(cCor, iBorda);
				
		lgrReta1.setP1(this.p1);
		lgrReta1.setP2(this.p2);
		lgrReta1.desenhar(pane);

		lgrReta2.setP1(this.p1);
		lgrReta2.setP2(this.p3);
		lgrReta2.desenhar(pane);
		
		lgrReta3.setP1(this.p2);
		lgrReta3.setP2(this.p3);
		lgrReta3.desenhar(pane);
		
		pontosTriangulo.addAll(lgrReta1.getPontos());
		pontosTriangulo.addAll(lgrReta2.getPontos());
		pontosTriangulo.addAll(lgrReta3.getPontos());
		
		for (PontoGr ponto : pontosTriangulo) {
			Ellipse e = ponto.getEllipse();
			
			e.setOnMouseClicked(  
				(ev)-> {
					if (Quadro.getSelecionar()) {
						selecionar();
					}
				}
			);
			
			if (p1.getX() == ponto.getX() && p1.getY() == ponto.getY()) {
				ponto.getEllipse().setOnMouseDragged(   
					(ev)->{
						if (Quadro.getRotacionar()) {
							double anguloAtual = Math.atan2(p1.getY() - p2.getY(), p1.getX() - p2.getX());
							double novoAngulo = Math.atan2(ev.getY() - p2.getY(), ev.getX() - p2.getX());
							rotacao(p2, novoAngulo - anguloAtual);
						}
						
						else if (Quadro.getMover()) {
							mover(ev.getX() - ponto.getX(), ev.getY() - ponto.getY());
						}
					}
				);
			}
			
			else if (p2.getX() == ponto.getX() && p2.getY() == ponto.getY()) {
				ponto.getEllipse().setOnMouseDragged(   
						(ev)->{
							if (Quadro.getRotacionar()) {
								double anguloAtual = Math.atan2(p1.getY() - p2.getY(), p1.getX() - p2.getX());
								double novoAngulo = Math.atan2(p1.getY() - ev.getY(), p1.getX() - ev.getX());
								rotacao(p1, novoAngulo - anguloAtual);
							}
							
							else if (Quadro.getMover()) {
								mover(ev.getX() - ponto.getX(), ev.getY() - ponto.getY());
							}
						}
					);
			}
			
			else if (p3.getX() == ponto.getX() && p3.getY() == ponto.getY()) {
				ponto.getEllipse().setOnMouseDragged(   
						(ev)->{
							if (Quadro.getRotacionar()) {
								double anguloAtual = Math.atan2(p3.getY() - p1.getY(), p3.getX() - p1.getX());
								double novoAngulo = Math.atan2(ev.getY() - p1.getY(), ev.getX() - p1.getX());
								rotacao(p1, novoAngulo - anguloAtual);
							}
							
							else if (Quadro.getMover()) {
								mover(ev.getX() - ponto.getX(), ev.getY() - ponto.getY());
							}
						}
					);
			}
			
			else {
				e.setOnMouseDragged(
					(ev)->{
						if (Quadro.getMover()) {
							mover(ev.getX() - ponto.getX(), ev.getY() - ponto.getY());
						}
					}
				);
			}
		}
		
	}
	
	
	public void selecionar() {
		if (selecionado) {
			for (PontoGr p : pontosTriangulo) {
				Ellipse e = p.getEllipse();
				e.setOpacity(1);
			}
			
			selecionado = false;
		}
		
		else {
			for (PontoGr p : pontosTriangulo) {
				Ellipse e = p.getEllipse();
				e.setOpacity(0.1);
			}
			
			selecionado = true;
		}
	}

	public Ponto getPontoMedioP1P2() {
		Linha lreta = new Linha(this.p1, this.p2);		
		return lreta.getPontoMedio();
	}

	public Ponto getPontoMedioP1P3() {
		Linha lreta = new Linha(this.p1, this.p3);		
		return lreta.getPontoMedio();
	}
	
	public Ponto getPontoMedioP2P3() {
		Linha lreta = new Linha(this.p2, this.p3);		
		return lreta.getPontoMedio();
	}

	public Color getCor() {
		return this.cCor;
	}

	@Override
	public void mover(double x, double y) {
		for (PontoGr p : pontosTriangulo) {
			p.setX(p.getX() + x);
			p.setY(p.getY() + y);
		}
		
		p1.setX(p1.getX() + x);
		p2.setX(p2.getX() + x);
		p3.setX(p3.getX() + x);
		
		p1.setY(p1.getY() + y);
		p2.setY(p2.getY() + y);
		p3.setY(p3.getY() + y);
	}

	@Override
	public boolean selecionado() {
		return selecionado;
	}

	@Override
	public void marcarRotacao() {
		for (PontoGr p : pontosTriangulo) {
			if ((p1.getX() == p.getX() && p1.getY() == p.getY()) || (p2.getX() == p.getX() && p2.getY() == p.getY()) || 
				(p3.getX() == p.getX() && p3.getY() == p.getY())) {
				
				if (cCor.equals(Color.RED)) {
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
		for (PontoGr p : pontosTriangulo) {
			double x = pBase.getX() + (p.getX() - pBase.getX()) * Math.cos(angulo) - (p.getY() - pBase.getY()) * Math.sin(angulo);
			double y = pBase.getY() + (p.getX() - pBase.getX()) * Math.sin(angulo) + (p.getY() - pBase.getY()) * Math.cos(angulo);
			
			p.setX(x);
			p.setY(y);
		}
		
		if (!(pBase.getX() == p1.getX() && pBase.getY() == p1.getY())) {
			double x = pBase.getX() + (p1.getX() - pBase.getX()) * Math.cos(angulo) - (p1.getY() - pBase.getY()) * Math.sin(angulo);
			double y = pBase.getY() + (p1.getX() - pBase.getX()) * Math.sin(angulo) + (p1.getY() - pBase.getY()) * Math.cos(angulo);
			
			p1.setX(x);
			p1.setY(y);
		}
		
		if (!(pBase.getX() == p2.getX() && pBase.getY() == p2.getY())) {
			double x = pBase.getX() + (p2.getX() - pBase.getX()) * Math.cos(angulo) - (p2.getY() - pBase.getY()) * Math.sin(angulo);
			double y = pBase.getY() + (p2.getX() - pBase.getX()) * Math.sin(angulo) + (p2.getY() - pBase.getY()) * Math.cos(angulo);
			
			p2.setX(x);
			p2.setY(y);
		}
		
		if (!(pBase.getX() == p3.getX() && pBase.getY() == p3.getY())) {
			double x = pBase.getX() + (p3.getX() - pBase.getX()) * Math.cos(angulo) - (p3.getY() - pBase.getY()) * Math.sin(angulo);
			double y = pBase.getY() + (p3.getX() - pBase.getX()) * Math.sin(angulo) + (p3.getY() - pBase.getY()) * Math.cos(angulo);
			
			p3.setX(x);
			p3.setY(y);
		}

	}

	@Override
	public void escala(Pane pane, Ponto pBase) {
		// TODO Auto-generated method stub
		
	}	

}
