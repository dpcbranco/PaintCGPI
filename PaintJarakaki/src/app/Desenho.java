package app;

import java.util.ArrayList;

import formas.Circulo;
import formas.Formas;
import formas.Linha;
import formas.LinhaPoligonal;
import formas.Poligono;
import formas.Ponto;
import formas.Retangulo;
import formas.Triangulo;
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
	
	public void desenharLinha(PontoGr p, ArrayList<Formas> listaXML, Color cor, int borda) {
		
		if (novaLinha == null) {
			novaLinha = new LinhaGr(cor, borda);			
			novaLinha.setP1(p);
			formasPane.addAll(pc.getChildren());
		}
		
		else {
			//remove resquicios do elastico
			restaurar();
			
			p = new PontoGr((int)p.getX(), (int)p.getY(), cor, borda);
			
			novaLinha.setP2(p);
			novaLinha.desenhar(pc);
			
			listaXML.add((Linha) novaLinha);
			
			novaLinha = null;
			formasPane.clear();
		}
		
	}
	
	//Desenha triângulo à partir de pontos já desenhados e do clique no quadro
	public void desenharTriangulo(PontoGr p, ArrayList<Formas> listaXML, Color cor, int borda) {
		if (novoTriangulo == null) {
			formasPane.addAll(pc.getChildren());
			novoTriangulo = new TrianguloGr(p, null, null, cor, borda);
			novaLinha = new LinhaGr(cor, borda);
			novaLinha.setP1(p);
	
		}
		
		else if (novoTriangulo.getP2() == null) {
			//remove resquicios do elastico
			restaurar();
				
			novoTriangulo.setP2(p);
			
			//desenha a linha, limpa objeto novaLinha utilizado para elastico de linhas e tira novo snapshot para desenho do triângulo
			novaLinha.setP2(p);
			novaLinha.desenhar(pc);
			novaLinha = null;
			
			formasPane.clear();
			formasPane.addAll(pc.getChildren());
		}
		
		else {
			//remove resquicios do elastico
			restaurar();
			
			novoTriangulo.setP3(p);
			novoTriangulo.desenhar(pc);
			
			listaXML.add((Triangulo) novoTriangulo);
			
			formasPane.clear();
			novoTriangulo = null;
		}
		
	}
	
	
	//Desenha circulo de acordo com o clique no quadro
	public void desenharCirculo(PontoGr p, ArrayList<Formas> listaXML, Color cor, int borda) {
		
		//Se centro do círculo ainda não foi fixado, é criado ponto no local do clique
		if (novoCirculo == null) {
			formasPane.addAll(pc.getChildren());
			novoCirculo = new CirculoGr((Ponto)p, cor, borda);
		}
		
		//Desenha circulo de acordo com o ponto clicado em evento anterior e ponto atual
		else {
			//remove resquicios do elastico
			restaurar();
			
			novoCirculo.setPerimetro(p);
			novoCirculo.desenhar(pc);
			
			listaXML.add((Circulo) novoCirculo);
			
			//Limpa dados do circulo para desenho de novas formas
			formasPane.clear();
			novoCirculo = null;
		}
		
	}
	
	public void desenharRetangulo(PontoGr p, ArrayList<Formas> listaXML, Color cor, int borda) {
		if (novoRetangulo == null) {
			novoRetangulo = new RetanguloGr(p, null, cor, borda);			
			formasPane.addAll(pc.getChildren());	
		}
		
		else {
			//remove resquicios do elastico
			restaurar();
			
			novoRetangulo.setP2(p);
			novoRetangulo.desenhar(pc);
			
			listaXML.add((Retangulo) novoRetangulo);
			
			formasPane.clear();
			novoRetangulo = null;
		}
		
	}
	
	public void desenharLinhaPoligonal(MouseEvent ev, ArrayList<Formas> listaXML, Color cor, int borda) {
		
		PontoGr p = new PontoGr((int)ev.getX(),(int)ev.getY(), cor, borda);
		
		if (ev.getButton() == MouseButton.PRIMARY) {
			
			if (novaLinhaPoligonal == null) {
				novaLinhaPoligonal = new LinhaPoligonalGr(p, cor, borda);
				formasPane.addAll(pc.getChildren());
			}
		
			else {
				//remove resquicios do elastico
				restaurar();
				
				novaLinhaPoligonal.desenharPonto(p, pc);
				
				formasPane.clear();
				formasPane.addAll(pc.getChildren());
			}
		}
		else if (ev.getButton() == MouseButton.SECONDARY) {
			//remove resquicios do elastico
			restaurar();
			
			novaLinhaPoligonal.desenharPonto(p, pc);
			listaXML.add((LinhaPoligonal) novaLinhaPoligonal);
			
			formasPane.clear();
			novaLinhaPoligonal = null;
		}
	}


	public void desenharPoligono(MouseEvent ev, ArrayList<Formas> listaXML, Color cor, int borda) {
		
		if (ev.getButton() == MouseButton.PRIMARY) {
			
			this.setNovoPonto(new PontoGr((int) ev.getX(), (int) ev.getY(), cor, borda));
			
			if (novoPoligono == null) {
				novoPoligono = new PoligonoGr(this.getNovoPonto(), cor, borda);
				formasPane.addAll(pc.getChildren());
			}
		
			else {
				//remove resquicios do elastico
				restaurar();
				
				novoPoligono.desenharPonto(this.getNovoPonto(), pc);
				
				formasPane.clear();
				formasPane.addAll(pc.getChildren());
				
			}
		}
		else if (ev.getButton() == MouseButton.SECONDARY) {
			//remove resquicios do elastico
			restaurar();
			
			novoPoligono.finalizarPoligono(pc);
			listaXML.add((Poligono) novoPoligono);
			
			formasPane.clear();
			novoPoligono = null;
		}
		
	}
}
