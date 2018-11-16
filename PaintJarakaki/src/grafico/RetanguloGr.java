package grafico;

import java.util.ArrayList;

import app.Quadro;
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
	boolean selecionado = false;
	
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
								double anguloAtual = Math.atan2(p3.getY() - p4.getY(), p3.getX() - p4.getX());
								double novoAngulo = Math.atan2(ev.getY() - p4.getY(), ev.getX() - p4.getX());
								rotacao(p4, novoAngulo - anguloAtual);
							}
							
							else if (Quadro.getMover()) {
								mover(ev.getX() - ponto.getX(), ev.getY() - ponto.getY());
							}
						}
					);
			}
			
			else if (p4.getX() == ponto.getX() && p4.getY() == ponto.getY()) {
				ponto.getEllipse().setOnMouseDragged(   
						(ev)->{
							if (Quadro.getRotacionar()) {
								double anguloAtual = Math.atan2(p3.getY() - p4.getY(), p3.getX() - p4.getX());
								double novoAngulo = Math.atan2(p3.getY() - ev.getY(), p3.getX() - ev.getX());
								rotacao(p3, novoAngulo - anguloAtual);
							}
							
							else if (Quadro.getMover()) {
								mover(ev.getX() - ponto.getX(), ev.getY() - ponto.getY());
							}
						}
					);
			}
			
			else {
				ponto.getEllipse().setOnMouseDragged(
					(ev)->{
						if (Quadro.getMover()) {
							mover(ev.getX() - ponto.getX(), ev.getY() - ponto.getY());
						}
					}
				);
			}
			
			ponto.getEllipse().setOnMouseClicked(  
				(ev)-> {
					if (Quadro.getSelecionar()) {
						selecionar();
					}
				}
			);
			
		}
	}

	public void selecionar() {
		if (selecionado) {
			for (PontoGr p : pontosRetangulo) {
				Ellipse e = p.getEllipse();
				e.setOpacity(1);
			}
			
			selecionado = false;
		}
		
		else {
			for (PontoGr p : pontosRetangulo) {
				Ellipse e = p.getEllipse();
				e.setOpacity(0.1);
			}
			
			selecionado = true;
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
		
		p1.setX(p1.getX() + x);
		p2.setX(p2.getX() + x);
		p3.setX(p3.getX() + x);
		p4.setX(p4.getX() + x);
		
		p1.setY(p1.getY() + y);
		p2.setY(p2.getY() + y);
		p3.setY(p3.getY() + y);
		p4.setY(p4.getY() + y);
		
	}

	@Override
	public boolean selecionado() {
		return selecionado;
	}

	@Override
	public void marcarRotacao() {
		for (PontoGr p : pontosRetangulo) {
			if ((p1.getX() == p.getX() && p1.getY() == p.getY()) || (p2.getX() == p.getX() && p2.getY() == p.getY()) || 
				(p3.getX() == p.getX() && p3.getY() == p.getY()) || (p4.getX() == p.getX() && p4.getY() == p.getY())) {
				
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
		for (PontoGr p : pontosRetangulo) {
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
		
		if (!(pBase.getX() == p4.getX() && pBase.getY() == p4.getY())) {
			double x = pBase.getX() + (p4.getX() - pBase.getX()) * Math.cos(angulo) - (p4.getY() - pBase.getY()) * Math.sin(angulo);
			double y = pBase.getY() + (p4.getX() - pBase.getX()) * Math.sin(angulo) + (p4.getY() - pBase.getY()) * Math.cos(angulo);
			
			p4.setX(x);
			p4.setY(y);
		}

	}

	@Override
	public void escala(Pane pane, Ponto pBase) {
		// TODO Auto-generated method stub
		
	}

}
