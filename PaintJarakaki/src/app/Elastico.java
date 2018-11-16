package app;

import java.util.ArrayList;

import formas.Ponto;
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
	RetanguloGr retanguloCorte;
	
	//Pane onde formas são desenhadas
	Pane pc;
	
	//Lista de formas da pane - Usado para implementação do elástico
	ArrayList<Node> formasPane = new ArrayList<>();
	
	public Elastico(Pane pc) {
		this.pc = pc;
	}
	
	//Desenha linhas que reproduzem efeito do elástico movimento do mouse
	public void elasticoLinha(Ponto pontoEv, Color opcaoCor, int opcaoBorda) {
		LinhaGr linhaElastico;
		
		if (novaLinha != null) {
	//		cv_quadro.getGraphicsContext2D().drawImage(imgSnapshot, 0, 0);
			pc.getChildren().clear();
			pc.getChildren().addAll(formasPane);
			linhaElastico = new LinhaGr(opcaoCor, opcaoBorda);
			linhaElastico.setP1(novaLinha.getP1());
			linhaElastico.setP2(pontoEv);
			linhaElastico.desenhar(pc);
		}
	}
	
	
	public void elasticoTriangulo(Ponto pontoEv, Color opcaoCor, int opcaoBorda) {
		TrianguloGr trianguloElastico;
		
		if (novoTriangulo != null) {
			if (novoTriangulo.getP2() != null) {
				pc.getChildren().clear();
				pc.getChildren().addAll(formasPane);
				trianguloElastico = new TrianguloGr(novoTriangulo.getP1(), novoTriangulo.getP2(), pontoEv, opcaoCor, opcaoBorda);
				trianguloElastico.desenhar(pc);
			}
			
			else {
				elasticoLinha(pontoEv, opcaoCor, opcaoBorda);
			}
		}
		
	}
	
	//Desenha linhas que reproduzem efeito do elástico movimento do mouse
	public void elasticoRetangulo(Ponto pontoEv, Color opcaoCor, int opcaoBorda) {
		RetanguloGr retanguloLinha;
		if (novoRetangulo != null) {
			pc.getChildren().clear();
			pc.getChildren().addAll(formasPane);
			retanguloLinha = new RetanguloGr(novoRetangulo.getP1(), pontoEv, opcaoCor, opcaoBorda);
			retanguloLinha.desenhar(pc);
		}
	}
	
	public void elasticoCirculo(Ponto pontoEv, Color opcaoCor, int opcaoBorda) {
		CirculoGr circuloElastico;
		
		if (novoCirculo != null) {
			pc.getChildren().clear();
			pc.getChildren().addAll(formasPane);
			circuloElastico = new CirculoGr(novoCirculo.getCentro(), opcaoCor, opcaoBorda);
			circuloElastico.setPerimetro(pontoEv);
			circuloElastico.desenhar(pc);
		}
	}
	
	public void elasticoPoligono(Ponto pontoEv, Color opcaoCor, int opcaoBorda) {
		LinhaGr linhaPoligono;
		
		if (novoPoligono != null) {
			pc.getChildren().clear();
			pc.getChildren().addAll(formasPane);
			linhaPoligono = new LinhaGr(novoPoligono.getPN(), pontoEv, opcaoCor, opcaoBorda);
			linhaPoligono.desenhar(pc);
			
		}
	}
	
	public void elasticoLinhaPoligonal(Ponto pontoEv, Color opcaoCor, int opcaoBorda) {
		LinhaGr linhaPoligono;
		
		if (novaLinhaPoligonal != null) {
			pc.getChildren().clear();
			pc.getChildren().addAll(formasPane);
			linhaPoligono = new LinhaGr(novaLinhaPoligonal.getPN(), pontoEv, opcaoCor, opcaoBorda);
			linhaPoligono.desenhar(pc);
		}
	}

	public void elasticoCorte(Ponto pontoElastico) {
		RetanguloGr corteElastico;
		
		if(retanguloCorte != null) {
			pc.getChildren().clear();
			pc.getChildren().addAll(formasPane);
			corteElastico = new RetanguloGr(retanguloCorte.getP1(), pontoElastico, Color.GRAY, 1);
			corteElastico.desenhar(pc);
			
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

	//Restaura formas existentes no Pane antes da criação da nova forma
	public void restaurar() {
		pc.getChildren().clear();
		pc.getChildren().addAll(formasPane);
	}
	
	public Pane getPaneCanvas() {
		return pc;
	}
}
