package grafico;

import java.util.ArrayList;
import java.util.Optional;

import app.Quadro;
import formas.Linha;
import formas.Ponto;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

public class LinhaGr extends Linha implements FormaGr{

	Color cor;
	int borda;
	boolean selecionado = false;
	
	ArrayList<PontoGr> pontosLinha = new ArrayList<>();
	
	public LinhaGr(Color cor, int borda) {
		this.cor = cor;
		this.borda = borda;
	}
	
	public LinhaGr (PontoGr p1, PontoGr p2, Color cor, int borda) {
		super(new Ponto(p1), new Ponto(p2));
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
			
			if (p.equals(p1)) {
				novoPonto.getEllipse().setOnMouseDragged(   
					(ev)->{
						if (Quadro.getRotacionar()) {
							double anguloAtual = Math.atan2(p1.getY() - p2.getY(), p1.getX() - p2.getX());
							double novoAngulo = Math.atan2(ev.getY() - p2.getY(), ev.getX() - p2.getX());
							rotacao(p2, novoAngulo - anguloAtual);
						}
						
						else if (Quadro.getMover()) {
							mover(ev.getX() - novoPonto.getX(), ev.getY() - novoPonto.getY());
						}
						
						else if (Quadro.getEscalar()) {
							escala(pane, novoPonto);
						}
					}
				);
			}
			
			else if (p.equals(p2)) {
				novoPonto.getEllipse().setOnMouseDragged(   
						(ev)->{
							if (Quadro.getRotacionar()) {
								double anguloAtual = Math.atan2(p1.getY() - p2.getY(), p1.getX() - p2.getX());
								double novoAngulo = Math.atan2(p1.getY() - ev.getY(), p1.getX() - ev.getX());
								rotacao(p1, novoAngulo - anguloAtual);
							}
							
							else if (Quadro.getMover()) {
								mover(ev.getX() - novoPonto.getX(), ev.getY() - novoPonto.getY());
							}
							
							else if (Quadro.getEscalar()) {
								escala(pane, novoPonto);
							}
						}
					);
			}
			
			else {
				novoPonto.getEllipse().setOnMouseDragged(
					(ev)->{
						if (Quadro.getMover()) {
							mover(ev.getX() - novoPonto.getX(), ev.getY() - novoPonto.getY());
						}
						
						else if (Quadro.getEscalar()) {
							escala(pane, novoPonto);
						}
					}
				);
			}
			
			pontosLinha.add(novoPonto);
		}
	}

	@Override
	public void rotacao(Ponto pBase, double angulo) {
		for (PontoGr p : pontosLinha) {
			double x = pBase.getX() + (p.getX() - pBase.getX()) * Math.cos(angulo) - (p.getY() - pBase.getY()) * Math.sin(angulo);
			double y = pBase.getY() + (p.getX() - pBase.getX()) * Math.sin(angulo) + (p.getY() - pBase.getY()) * Math.cos(angulo);
			
			p.setX(x);
			p.setY(y);
		}
		
		if (pBase.getX() == p1.getX() && pBase.getY() == p1.getY()) {
			double x = pBase.getX() + (p2.getX() - pBase.getX()) * Math.cos(angulo) - (p2.getY() - pBase.getY()) * Math.sin(angulo);
			double y = pBase.getY() + (p2.getX() - pBase.getX()) * Math.sin(angulo) + (p2.getY() - pBase.getY()) * Math.cos(angulo);
			
			p2.setX(x);
			p2.setY(y);
		}
		
		else {
			double x = pBase.getX() + (p1.getX() - pBase.getX()) * Math.cos(angulo) - (p1.getY() - pBase.getY()) * Math.sin(angulo);
			double y = pBase.getY() + (p1.getX() - pBase.getX()) * Math.sin(angulo) + (p1.getY() - pBase.getY()) * Math.cos(angulo);
			
			p1.setX(x);
			p1.setY(y);
		}
	}

	public void selecionar() {
		if (selecionado) {
			for (PontoGr pgr : pontosLinha) {
				Ellipse e = pgr.getEllipse();
				
				e.setOpacity(1);
					
			}
			
			selecionado = false;
		}
		
		else {
			for (PontoGr pgr : pontosLinha) {
				Ellipse e = pgr.getEllipse();
				
				e.setOpacity(0.1);				
				
			}
			
			selecionado = true;
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

	@Override
	public boolean selecionado() {
		return selecionado;
	}

	@Override
	public void marcarRotacao() {
		for (PontoGr p : pontosLinha) {
			if ((p1.getX() == p.getX() && p1.getY() == p.getY()) || (p2.getX() == p.getX() && p2.getY() == p.getY()) ) {
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
	public void escala(Pane pane, Ponto pBase) {
		TextInputDialog inputEscala = new TextInputDialog("2");
		double escala;
		
		
		inputEscala.setTitle("Escala");
		inputEscala.setContentText("Entre com uma escala entre 0.1 e 10:");
		
		Optional<String> entrada = inputEscala.showAndWait();
		
		if(entrada.isPresent()) {
			try {
				escala = Double.parseDouble(entrada.get());
				
				if (escala < 0.1 || escala > 10) {
					throw new NumberFormatException();
				}
				
				p1.setX(p1.getX() * escala + pBase.getX() * (1 - escala));
				p2.setX(p2.getX() * escala + pBase.getX() * (1 - escala));
				
				p1.setY(p1.getY() * escala + pBase.getY() * (1 - escala));
				p2.setY(p2.getY() * escala + pBase.getY() * (1 - escala));
				
				this.deletar(pane);
				this.desenhar(pane);
				
			}catch (NumberFormatException e){
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("ERRO!");
				alert.setContentText("Entrada inv�lida");

				alert.showAndWait();
			}
		}
		
	}

	private void deletar(Pane pane) {
		for (PontoGr p :  pontosLinha) {
			pane.getChildren().remove(p.getEllipse());
		}
		
		pontosLinha.clear();
	}
}
