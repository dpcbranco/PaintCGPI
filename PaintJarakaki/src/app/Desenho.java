package app;

import java.util.ArrayList;

import formas.Circulo;
import formas.Forma;
import formas.Linha;
import formas.LinhaPoligonal;
import formas.Poligono;
import formas.Ponto;
import formas.Retangulo;
import formas.Triangulo;
import grafico.CirculoGr;
import grafico.FormaGr;
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
	public PontoGr desenharPonto(Ponto p, Color cor, int borda) {
		novoPonto = new PontoGr(p, cor, borda);
		novoPonto.desenhar(pc);
		return novoPonto;
	}
	
	public void desenharLinha(Ponto novoPonto, ArrayList<FormaGr> listaXML, Color cor, int borda) {
		
		if (novaLinha == null) {
			novaLinha = new LinhaGr(cor, borda);			
			novaLinha.setP1(novoPonto);
			formasPane.addAll(pc.getChildren());
		}
		
		else {
			//remove resquicios do elastico
			restaurar();
			
			novoPonto = new PontoGr((int)novoPonto.getX(), (int)novoPonto.getY(), cor, borda);
			
			novaLinha.setP2(novoPonto);
			novaLinha.desenhar(pc);
			
			listaXML.add(novaLinha);
			
			novaLinha = null;
			formasPane.clear();
		}
		
	}
	
	//Desenha triângulo à partir de pontos já desenhados e do clique no quadro
	public void desenharTriangulo(Ponto novoPonto, ArrayList<FormaGr> listaXML, Color cor, int borda) {
		if (novoTriangulo == null) {
			formasPane.addAll(pc.getChildren());
			novoTriangulo = new TrianguloGr(novoPonto, null, null, cor, borda);
			novaLinha = new LinhaGr(cor, borda);
			novaLinha.setP1(novoPonto);
	
		}
		
		else if (novoTriangulo.getP2() == null) {
			//remove resquicios do elastico
			restaurar();
				
			novoTriangulo.setP2(novoPonto);
			
			//desenha a linha, limpa objeto novaLinha utilizado para elastico de linhas e tira novo snapshot para desenho do triângulo
			novaLinha.setP2(novoPonto);
			novaLinha.desenhar(pc);
			
			formasPane.clear();
			formasPane.addAll(pc.getChildren());
		}
		
		else {
			//remove resquicios do elastico
			restaurar();
			deletar(novaLinha.getPontos());
			
			novoTriangulo.setP3(novoPonto);
			novoTriangulo.desenhar(pc);
			
			listaXML.add(novoTriangulo);
			
			formasPane.clear();
			novoTriangulo = null;
			novaLinha = null;
		}
		
	}
	
	
	//Desenha circulo de acordo com o clique no quadro
	public void desenharCirculo(Ponto novoPonto, ArrayList<FormaGr> listaXML, Color cor, int borda) {
		
		//Se centro do círculo ainda não foi fixado, é criado ponto no local do clique
		if (novoCirculo == null) {
			formasPane.addAll(pc.getChildren());
			novoCirculo = new CirculoGr((Ponto)novoPonto, cor, borda);
		}
		
		//Desenha circulo de acordo com o ponto clicado em evento anterior e ponto atual
		else {
			//remove resquicios do elastico
			restaurar();
			
			novoCirculo.setPerimetro(novoPonto);
			novoCirculo.desenhar(pc);
			
			listaXML.add(novoCirculo);
			
			//Limpa dados do circulo para desenho de novas formas
			formasPane.clear();
			novoCirculo = null;
		}
		
	}
	
	public void desenharRetangulo(Ponto novoPonto, ArrayList<FormaGr> listaXML, Color cor, int borda) {
		if (novoRetangulo == null) {
			novoRetangulo = new RetanguloGr(novoPonto, null, cor, borda);			
			formasPane.addAll(pc.getChildren());	
		}
		
		else {
			//remove resquicios do elastico
			restaurar();
			
			novoRetangulo.setP2(novoPonto);
			novoRetangulo.desenhar(pc);
			
			listaXML.add(novoRetangulo);
			
			formasPane.clear();
			novoRetangulo = null;
		}
		
	}
	
	public void desenharLinhaPoligonal(MouseEvent ev, ArrayList<FormaGr> listaXML, Color cor, int borda) {
		
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
			listaXML.add(novaLinhaPoligonal);
			
			formasPane.clear();
			novaLinhaPoligonal = null;
		}
	}


	public void desenharPoligono(MouseEvent ev, ArrayList<FormaGr> listaXML, Color cor, int borda) {
		
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
			listaXML.add(novoPoligono);
			
			formasPane.clear();
			novoPoligono = null;
		}
		
	}
	
	public void desenharCorte(Ponto p) {
		if (retanguloCorte == null) {
			retanguloCorte = new RetanguloGr(p, null, Color.GRAY, 2);
			formasPane.addAll(pc.getChildren());
		}
		
		else {
			restaurar();
			retanguloCorte.setP2(p);
			retanguloCorte.desenhar(pc);
			
			for (PontoGr pgr : retanguloCorte.getPontosRetangulo()) {
				pc.getChildren().remove(pgr.getEllipse());
			}
			retanguloCorte = null;
			formasPane.clear();
		}
	}
	
	private void deletar(ArrayList<PontoGr> pontos) {
		for (PontoGr p : pontos) {
			pc.getChildren().remove(p.getEllipse());
		}
	}

}
