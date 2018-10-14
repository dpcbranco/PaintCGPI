package app;

import java.util.ArrayList;

import grafico.CirculoGr;
import grafico.LinhaGr;
import grafico.LinhaPoligonalGr;
import grafico.PoligonoGr;
import grafico.PontoGr;
import grafico.RetanguloGr;
import grafico.TrianguloGr;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Elastico {
	
	//Objeto usado para desenho e redesenho de linha elástica
	PontoGr novoPonto;
	LinhaGr novaLinha;  
	CirculoGr novoCirculo;
	TrianguloGr novoTriangulo;
	RetanguloGr novoRetangulo;
	PoligonoGr novoPoligono;
	LinhaPoligonalGr novaLinhaPoligonal;
	
	//Pane onde formas são desenhadas
	Pane pc;
	
	//Lista de formas da pane - Usado para implementação do elástico
	ArrayList<Node> listaFormas = new ArrayList<>();
	
	public Elastico(Pane pc) {
		this.pc = pc;
	}
	
	//Desenha linhas que reproduzem efeito do elástico movimento do mouse
	public void elasticoLinha(PontoGr p, Color opcaoCor, int opcaoBorda) {
		LinhaGr linhaElastico;
		
		if (novaLinha != null) {
	//		cv_quadro.getGraphicsContext2D().drawImage(imgSnapshot, 0, 0);
			pc.getChildren().clear();
			pc.getChildren().addAll(listaFormas);
			linhaElastico = new LinhaGr(opcaoCor, opcaoBorda);
			linhaElastico.setP1(novaLinha.getP1());
			linhaElastico.setP2(p);
			linhaElastico.desenhar(pc);
		}
	}
	
	
	public void elasticoTriangulo(PontoGr p, Color opcaoCor, int opcaoBorda) {
		TrianguloGr trianguloElastico;
		
		if (novoTriangulo != null) {
			if (novoTriangulo.getP2() != null) {
				pc.getChildren().clear();
				pc.getChildren().addAll(listaFormas);
				trianguloElastico = new TrianguloGr(novoTriangulo.getP1(), novoTriangulo.getP2(), p, opcaoCor, opcaoBorda);
				trianguloElastico.desenhar(pc);
			}
			
			else {
				elasticoLinha(p, opcaoCor, opcaoBorda);
			}
		}
		
	}
	
	//Desenha linhas que reproduzem efeito do elástico movimento do mouse
	public void elasticoRetangulo(PontoGr p, Color opcaoCor, int opcaoBorda) {
		RetanguloGr retanguloLinha;
		if (novoRetangulo != null) {
			pc.getChildren().clear();
			pc.getChildren().addAll(listaFormas);
			retanguloLinha = new RetanguloGr(novoRetangulo.getP1(), p, opcaoCor, opcaoBorda);
			retanguloLinha.desenhar(pc);
		}
	}
	
	public void elasticoCirculo(PontoGr p, Color opcaoCor, int opcaoBorda) {
		CirculoGr circuloElastico;
		
		if (novoCirculo != null) {
			pc.getChildren().clear();
			pc.getChildren().addAll(listaFormas);
			circuloElastico = new CirculoGr(opcaoCor, opcaoBorda);
			circuloElastico.setCentro(novoCirculo.getCentro());
			circuloElastico.setRaio(p);
			circuloElastico.desenhar(pc);
		}
	}
	
	public void elasticoPoligono(PontoGr p, Color opcaoCor, int opcaoBorda) {
		LinhaGr linhaPoligono;
		
		if (novoPoligono != null) {
			pc.getChildren().clear();
			pc.getChildren().addAll(listaFormas);
			linhaPoligono = new LinhaGr((PontoGr) novoPoligono.getPN(), p, opcaoCor, opcaoBorda);
			linhaPoligono.desenhar(pc);
			
		}
	}
	
	public void elasticoLinhaPoligonal(PontoGr p, Color opcaoCor, int opcaoBorda) {
		LinhaGr linhaPoligono;
		
		if (novaLinhaPoligonal != null) {
			pc.getChildren().clear();
			pc.getChildren().addAll(listaFormas);
			linhaPoligono = new LinhaGr((PontoGr) novaLinhaPoligonal.getPN(), p, opcaoCor, opcaoBorda);
			linhaPoligono.desenhar(pc);
		}
	}

	
	
	public void setNovoPonto(PontoGr novoPonto) {
		this.novoPonto = novoPonto; 
	}

	public PontoGr getNovoPonto() {
		return novoPonto;
	}
	
	public LinhaGr getNovaLinha() {
		return novaLinha;
	}

	public void setNovaLinha(LinhaGr novaLinha) {
		this.novaLinha = novaLinha;
	}

	public CirculoGr getNovoCirculo() {
		return novoCirculo;
	}

	public void setNovoCirculo(CirculoGr novoCirculo) {
		this.novoCirculo = novoCirculo;
	}

	public TrianguloGr getNovoTriangulo() {
		return novoTriangulo;
	}

	public void setNovoTriangulo(TrianguloGr novoTriangulo) {
		this.novoTriangulo = novoTriangulo;
	}

	public RetanguloGr getNovoRetangulo() {
		return novoRetangulo;
	}

	public void setNovoRetangulo(RetanguloGr novoRetangulo) {
		this.novoRetangulo = novoRetangulo;
	}

	public PoligonoGr getNovoPoligono() {
		return novoPoligono;
	}

	public void setNovoPoligono(PoligonoGr novoPoligono) {
		this.novoPoligono = novoPoligono;
	}

	public LinhaPoligonalGr getNovaLinhaPoligonal() {
		return novaLinhaPoligonal;
	}

	public void setNovaLinhaPoligonal(LinhaPoligonalGr novaLinhaPoligonal) {
		this.novaLinhaPoligonal = novaLinhaPoligonal;
	}

}
