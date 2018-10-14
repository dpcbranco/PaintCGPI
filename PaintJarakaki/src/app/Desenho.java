package app;

import grafico.CirculoGr;
import grafico.LinhaGr;
import grafico.LinhaPoligonalGr;
import grafico.PoligonoGr;
import grafico.PontoGr;
import grafico.RetanguloGr;
import grafico.TrianguloGr;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Desenho extends Elastico{
	
	public Desenho(Pane pc) {
		super(pc);
	}
	
	//Desenha ponto conforme clique no quadro
	public void desenharPonto() {
		novoPonto.desenhar(pc);
	}
	
	public void desenharLinha(PontoGr p, Color cor, int borda) {
		
		if (novaLinha == null) {
			novaLinha = new LinhaGr(cor, borda);			
			novaLinha.setP1(p);
			listaFormas.addAll(pc.getChildren());
		}
		
		else {
			//remove resquicios do snapshot anterior
			pc.getChildren().clear();	
			pc.getChildren().addAll(listaFormas);
			
			p = new PontoGr((int)p.getX(), (int)p.getY(), cor, borda);
			
			novaLinha.setP2(p);
			novaLinha.desenhar(pc);
			
			novaLinha = null;
			listaFormas.clear();
		}
		
	}
	
	//Desenha triângulo à partir de pontos já desenhados e do clique no quadro
	public void desenharTriangulo(PontoGr p, Color cor, int borda) {
		if (novoTriangulo == null) {
			listaFormas.addAll(pc.getChildren());
			novoTriangulo = new TrianguloGr(p, null, null, cor, borda);
			novaLinha = new LinhaGr(cor, borda);
			novaLinha.setP1(p);
	
		}
		
		else if (novoTriangulo.getP2() == null) {
			//remove resquicios do snapshot anterior
			pc.getChildren().clear();	
			pc.getChildren().addAll(listaFormas);
				
			novoTriangulo.setP2(p);
			
			//desenha a linha, limpa objeto novaLinha utilizado para elastico de linhas e tira novo snapshot para desenho do triângulo
			novaLinha.setP2(p);
			novaLinha.desenhar(pc);
			novaLinha = null;
			
			listaFormas.clear();
			listaFormas.addAll(pc.getChildren());
		}
		
		else {
			//remove resquicios do snapshot anterior
			pc.getChildren().clear();	
			pc.getChildren().addAll(listaFormas);
			
			novoTriangulo.setP3(p);
			novoTriangulo.desenhar(pc);
			listaFormas.clear();
			novoTriangulo = null;
		}
	}
	
	
	//Desenha circulo de acordo com o clique no quadro
	public void desenharCirculo(PontoGr p, Color cor, int borda) {
		
		//Se centro do círculo ainda não foi fixado, é criado ponto no local do clique
		if (novoCirculo == null) {
			listaFormas.addAll(pc.getChildren());
			novoCirculo = new CirculoGr(cor, borda);
			novoCirculo.setCentro(p);
		}
		
		//Desenha circulo de acordo com o ponto clicado em evento anterior e ponto atual
		else {
			pc.getChildren().clear();
			pc.getChildren().addAll(listaFormas);
			novoCirculo.setRaio(p);
			novoCirculo.desenhar(pc);
			
			//Limpa dados do circulo para desenho de novas formas
			listaFormas.clear();
			novoCirculo = null;
		}
	}
	
	public void desenharRetangulo(PontoGr p, Color cor, int borda) {
		if (novoRetangulo == null) {
			novoRetangulo = new RetanguloGr(p, null, cor, borda);			
			listaFormas.addAll(pc.getChildren());	
		}
		
		else {
			//remove resquicios do snapshot anterior
			pc.getChildren().clear();	
			pc.getChildren().addAll(listaFormas);
			
			novoRetangulo.setP2(p);
			novoRetangulo.desenhar(pc);
			listaFormas.clear();
			novoRetangulo = null;
		}
		
	}
	
	public void desenharLinhaPoligonal(MouseEvent ev, Color cor, int borda) {
		
		PontoGr p = new PontoGr((int)ev.getX(),(int)ev.getY(), cor, borda);
		
		if (ev.getButton() == MouseButton.PRIMARY) {
			
			if (novaLinhaPoligonal == null) {
				novaLinhaPoligonal = new LinhaPoligonalGr(p, cor, borda);
				listaFormas.addAll(pc.getChildren());
			}
		
			else {
				pc.getChildren().clear();
				pc.getChildren().addAll(listaFormas);
				novaLinhaPoligonal.desenhar(p, pc);
				
				listaFormas.clear();
				listaFormas.addAll(pc.getChildren());
			}
		}
		else if (ev.getButton() == MouseButton.SECONDARY) {
			pc.getChildren().clear();
			pc.getChildren().addAll(listaFormas);
			novaLinhaPoligonal.desenhar(p, pc);
			
			listaFormas.clear();
			novaLinhaPoligonal = null;
		}
	}


	public void desenharPoligono(MouseEvent ev, Color cor, int borda) {
		
		if (ev.getButton() == MouseButton.PRIMARY) {
			
			this.setNovoPonto(new PontoGr((int) ev.getX(), (int) ev.getY(), cor, borda));
			
			if (novoPoligono == null) {
				novoPoligono = new PoligonoGr(this.getNovoPonto(), cor, borda);
				listaFormas.addAll(pc.getChildren());
			}
		
			else {
				pc.getChildren().clear();
				pc.getChildren().addAll(listaFormas);
				novoPoligono.desenhar(this.getNovoPonto(), pc);
				listaFormas.clear();
				listaFormas.addAll(pc.getChildren());
				
			}
		}
		else if (ev.getButton() == MouseButton.SECONDARY) {
			pc.getChildren().clear();
			pc.getChildren().addAll(listaFormas);
			novoPoligono.finalizarPoligono(pc);
			
			listaFormas.clear();
			novoPoligono = null;
		}
		
	}
}
