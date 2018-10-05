package app;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import figuras.Sierpinski;
import grafico.CirculoGr;
import grafico.LinhaGr;
import grafico.LinhaPoligonalGr;
import grafico.PoligonoGr;
import grafico.PontoGr;
import grafico.RetanguloGr;
import grafico.TrianguloGr;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;


public class Quadro implements Initializable{

	@FXML MenuBar menuOpcoes;
	@FXML Pane paneCanvas;
	
	@FXML RadioMenuItem rmiPonto;
	@FXML RadioMenuItem rmiLinha;
	@FXML RadioMenuItem rmiCirculo;
	@FXML RadioMenuItem rmiTriangulo;
	@FXML RadioMenuItem rmiSierpinski;
	@FXML RadioMenuItem rmiRetangulo;
	@FXML RadioMenuItem rmiPoligono;
	@FXML RadioMenuItem rmiLinhaPoligonal;
	
	@FXML RadioMenuItem rmiPreto;
	@FXML RadioMenuItem rmiAmarelo;
	@FXML RadioMenuItem rmiVerde;
	@FXML RadioMenuItem rmiAzul;
	@FXML RadioMenuItem rmiVermelho;
	
	@FXML MenuItem miLimpar;
	@FXML MenuItem miSelecionar;	
	@FXML MenuItem miDeletar;
	
	@FXML Slider slBorda;
	@FXML MenuItem miBorda;
	
	@FXML Canvas cv_quadro;
	
	WritableImage imgSnapshot;
	
	PontoGr novoPonto;
	PontoGr p1 = null, p2 = null, p3 = null;
	
	//Objeto usado para desenho e redesenho de linha elástica
	LinhaGr novaLinha;  
	CirculoGr novoCirculo;
	TrianguloGr novoTriangulo;
	RetanguloGr novoRetangulo;
	PoligonoGr novoPoligono;
	LinhaPoligonalGr novaLinhaPoligonal;
	
	ToggleGroup tgFormas = new ToggleGroup();
	ToggleGroup tgCores = new ToggleGroup();
	GraphicsContext gcCanvas;
	Sierpinski sierpDesenho;
	
	//Armazena desenhos de iteração anterior
	ArrayList<Node> listaFormas = new ArrayList<>();
	
	boolean selecionar = false;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
				
		//Define grupo de formas a serem escolhidas
		rmiPonto.setToggleGroup(tgFormas);
		rmiLinha.setToggleGroup(tgFormas);
		rmiCirculo.setToggleGroup(tgFormas);
		rmiTriangulo.setToggleGroup(tgFormas);
		rmiSierpinski.setToggleGroup(tgFormas);
		rmiRetangulo.setToggleGroup(tgFormas);
		rmiPoligono.setToggleGroup(tgFormas);
		rmiLinhaPoligonal.setToggleGroup(tgFormas);
		
		//Define grupo de cores a serem escolhidas
		rmiPreto.setToggleGroup(tgCores);
		rmiAmarelo.setToggleGroup(tgCores);
		rmiVerde.setToggleGroup(tgCores);
		rmiAzul.setToggleGroup(tgCores);
		rmiVermelho.setToggleGroup(tgCores);
		
		//Define a cor que cada botão representa
		rmiPreto.setUserData(Color.BLACK);
		rmiAmarelo.setUserData(Color.YELLOW);
		rmiVerde.setUserData(Color.GREEN);
		rmiAzul.setUserData(Color.BLUE);
		rmiVermelho.setUserData(Color.RED);
		
		//Define opções "DEFAULT"
		rmiPonto.setSelected(true);
		rmiPreto.setSelected(true);
		
		miBorda.setText(new Double(slBorda.getMajorTickUnit()).intValue() + "px");
		
		gcCanvas = cv_quadro.getGraphicsContext2D();
		gcCanvas.setFill(Color.WHITE);
		gcCanvas.fill();
		
		cv_quadro.toFront();
		
		slBorda.valueProperty().addListener(
			(ev)->{
				miBorda.setText(new Double(slBorda.getValue()).intValue() + "px");
			}
		);
		
		miLimpar.setOnAction(
		
			(ev)->{
				paneCanvas.getChildren().clear();
				paneCanvas.getChildren().add(cv_quadro);
				sierpDesenho = null;
			}
		);
		
		miSelecionar.setOnAction(
			(ev)->{
				if (selecionar == false) {
					cv_quadro.toBack();
					selecionar = true;
					miSelecionar.setText("✓ Selecionar");
				}
				
				else{
					cv_quadro.toFront();
					selecionar = false;
					miSelecionar.setText("Selecionar");
				}
					
			}
		);
		
		miDeletar.setOnAction( 
			(ev)->{
				ArrayList<Node> formas = new ArrayList<>();
				formas.addAll(paneCanvas.getChildren());
				for (int i = 1; i < formas.size(); i++) {
					Ellipse e = (Ellipse) formas.get(i);
					if (e.getFill() == Color.FUCHSIA) {
						paneCanvas.getChildren().remove(e);
					}
				}
			}
		);
		
		//Trata movimento do mouse durante desenho das formas
		cv_quadro.setOnMouseMoved(
			(ev)->{
				
				String opcaoForma = ((RadioMenuItem)tgFormas.getSelectedToggle()).getText();
				Color opcaoCor = (Color)((RadioMenuItem)tgCores.getSelectedToggle()).getUserData();
				int opcaoBorda = new Double(slBorda.getValue()).intValue();
				
				PontoGr pontoEv = new PontoGr((int)ev.getX(), (int)ev.getY(), opcaoCor, opcaoBorda);
				

				switch (opcaoForma) {
					case "Linha":{
						if (novaLinha != null) {
							elasticoLinha(pontoEv, opcaoCor, opcaoBorda);
						}
						break;
					}
						
					case "Circulo":{
						if (novoCirculo != null) {
							elasticoCirculo(pontoEv, opcaoCor, opcaoBorda);
						}	
						break;
					}
						
					case "Triangulo":{
						//para dois primeiros pontos, será utilizado elástico de linha
						if (novoTriangulo != null) {
							if (novoTriangulo.getP2() == null) {
								elasticoLinha(pontoEv, opcaoCor, opcaoBorda);
							}
							else {
								elasticoTriangulo(pontoEv, opcaoCor, opcaoBorda);
							}
						}
						break;
					}
						
					case "Retangulo":{
						elasticoRetangulo(pontoEv, opcaoCor, opcaoBorda);
						break;
					}
						
					case "Poligono":{
						elasticoPoligono(pontoEv, opcaoCor, opcaoBorda);
						break;
					}
					
					case "Linha Poligonal":{
						elasticoLinhaPoligonal(pontoEv, opcaoCor, opcaoBorda);
						break;
					}
				}
				
			}
		);
		
		cv_quadro.setOnMouseClicked(
			(ev)->{
				RadioMenuItem rmiOpcaoForma = (RadioMenuItem) tgFormas.getSelectedToggle();
				RadioMenuItem rmiOpcaoCor = (RadioMenuItem) tgCores.getSelectedToggle();
				
				Color cor = (Color) rmiOpcaoCor.getUserData();
				int borda = new Double(slBorda.getValue()).intValue();
				
				novoPonto = new PontoGr((int)ev.getX(), (int)ev.getY(), cor, borda);				
				
				switch (rmiOpcaoForma.getText()) {
					case "Ponto":{
						desenharPonto();
						break;
					}
					
					case "Linha":{
						desenharLinha(novoPonto, cor, borda);						
						break;
					}
					
					case "Circulo":{
						desenharCirculo(novoPonto, cor, borda);						
						break;
					}
					
					case "Triangulo":{
						desenharTriangulo(novoPonto, cor, borda);						
						break;
					}
					
					case "Retangulo":{
						desenharRetangulo(novoPonto, cor, borda);
						break;
					}
					
					case "Poligono":{
						desenharPoligono(ev, cor, borda);
						break;
						
					}
					
					case "Linha Poligonal":{
						desenharLinhaPoligonal(ev, cor, borda);
						break;
					}
					
					
					case "Sierpinski":{
						if (sierpDesenho == null) {
							sierpDesenho = new Sierpinski();
						}

						sierpDesenho.setBorda(borda);
						sierpDesenho.setCor(cor);
						sierpDesenho.desenharIteracao(paneCanvas);
						
						break;
					}
				}

			}
		);		
		
	}
	
	private void elasticoLinhaPoligonal(PontoGr p, Color opcaoCor, int opcaoBorda) {
		LinhaGr linhaPoligono;
		
		if (novaLinhaPoligonal != null) {
			paneCanvas.getChildren().clear();
			paneCanvas.getChildren().addAll(listaFormas);
			linhaPoligono = new LinhaGr((PontoGr) novaLinhaPoligonal.getPN(), p, opcaoCor, opcaoBorda);
			linhaPoligono.desenhar(paneCanvas);
			
			cv_quadro.toFront();
		}
	}

	private void desenharLinhaPoligonal(MouseEvent ev, Color cor, int borda) {
		
		PontoGr p = new PontoGr((int)ev.getX(),(int)ev.getY(), cor, borda);
		
		if (ev.getButton() == MouseButton.PRIMARY) {
			
			if (novaLinhaPoligonal == null) {
				novaLinhaPoligonal = new LinhaPoligonalGr(p, cor, borda);
				listaFormas.addAll(paneCanvas.getChildren());
			}
		
			else {
				paneCanvas.getChildren().clear();
				paneCanvas.getChildren().addAll(listaFormas);
				novaLinhaPoligonal.desenhar(p, paneCanvas);
				
				listaFormas.clear();
				listaFormas.addAll(paneCanvas.getChildren());
			}
		}
		else if (ev.getButton() == MouseButton.SECONDARY) {
			paneCanvas.getChildren().clear();
			paneCanvas.getChildren().addAll(listaFormas);
			novaLinhaPoligonal.desenhar(p, paneCanvas);
			
			listaFormas.clear();
			novaLinhaPoligonal = null;
		}
	}

	private void elasticoPoligono(PontoGr p, Color opcaoCor, int opcaoBorda) {
		LinhaGr linhaPoligono;
		
		if (novoPoligono != null) {
			paneCanvas.getChildren().clear();
			paneCanvas.getChildren().addAll(listaFormas);
			linhaPoligono = new LinhaGr((PontoGr) novoPoligono.getPN(), p, opcaoCor, opcaoBorda);
			linhaPoligono.desenhar(paneCanvas);
			
			cv_quadro.toFront();
		}
	}

	private void desenharPoligono(MouseEvent ev, Color cor, int borda) {
		
		if (ev.getButton() == MouseButton.PRIMARY) {
			
			p1 = new PontoGr((int) ev.getX(), (int) ev.getY(), cor, borda);
			
			if (novoPoligono == null) {
				novoPoligono = new PoligonoGr(p1, cor, borda);
				listaFormas.addAll(paneCanvas.getChildren());
			}
		
			else {
				paneCanvas.getChildren().clear();
				paneCanvas.getChildren().addAll(listaFormas);
				novoPoligono.desenhar(p1, paneCanvas);
				listaFormas.clear();
				listaFormas.addAll(paneCanvas.getChildren());
				
			}
		}
		else if (ev.getButton() == MouseButton.SECONDARY) {
			paneCanvas.getChildren().clear();
			paneCanvas.getChildren().addAll(listaFormas);
			novoPoligono.finalizarPoligono(paneCanvas);
			
			listaFormas.clear();
			novoPoligono = null;
		}
		
	}

	//Desenha linhas que reproduzem efeito do elástico movimento do mouse
	private void elasticoRetangulo(PontoGr p, Color opcaoCor, int opcaoBorda) {
		RetanguloGr retanguloLinha;
		if (novoRetangulo != null) {
			paneCanvas.getChildren().clear();
			paneCanvas.getChildren().addAll(listaFormas);
			cv_quadro.getGraphicsContext2D().drawImage(imgSnapshot, 0, 0);
			retanguloLinha = new RetanguloGr(novoRetangulo.getP1(), p, opcaoCor, opcaoBorda);
			retanguloLinha.desenhar(paneCanvas);
			cv_quadro.toFront();
		}
	}
	
	private void elasticoTriangulo(PontoGr p, Color opcaoCor, int opcaoBorda) {
		TrianguloGr trianguloElastico;
		
		if (novoTriangulo != null) {
			paneCanvas.getChildren().clear();
			paneCanvas.getChildren().addAll(listaFormas);
			trianguloElastico = new TrianguloGr(novoTriangulo.getP1(), novoTriangulo.getP2(), p, opcaoCor, opcaoBorda);
			trianguloElastico.desenhar(paneCanvas);
			
			cv_quadro.toFront();
		}
		
	}

	//Desenha linhas que reproduzem efeito do elástico movimento do mouse
	private void elasticoLinha(PontoGr p, Color opcaoCor, int opcaoBorda) {
		LinhaGr linhaElastico;
		
		if (novaLinha != null) {
	//		cv_quadro.getGraphicsContext2D().drawImage(imgSnapshot, 0, 0);
			paneCanvas.getChildren().clear();
			paneCanvas.getChildren().addAll(listaFormas);
			linhaElastico = new LinhaGr(opcaoCor, opcaoBorda);
			linhaElastico.setP1(novaLinha.getP1());
			linhaElastico.setP2(p);
			linhaElastico.desenhar(paneCanvas);
			
			cv_quadro.toFront();
		}
	}
	
	
	private void elasticoCirculo(PontoGr p, Color opcaoCor, int opcaoBorda) {
		CirculoGr circuloElastico;
		
		if (novoCirculo != null) {
			paneCanvas.getChildren().clear();
			paneCanvas.getChildren().addAll(listaFormas);
			circuloElastico = new CirculoGr(opcaoCor, opcaoBorda);
			circuloElastico.setCentro(novoCirculo.getCentro());
			circuloElastico.setRaio(p);
			circuloElastico.desenhar(paneCanvas);
			
			cv_quadro.toFront();
		}
	}
	
	private void desenharRetangulo(PontoGr p, Color cor, int borda) {
		if (novoRetangulo == null) {
			novoRetangulo = new RetanguloGr(p, null, cor, borda);			
			listaFormas.addAll(paneCanvas.getChildren());	
		}
		
		else {
			//remove resquicios do snapshot anterior
			paneCanvas.getChildren().clear();	
			paneCanvas.getChildren().addAll(listaFormas);
			
			novoRetangulo.setP2(p);
			novoRetangulo.desenhar(paneCanvas);
			listaFormas.clear();
			novoRetangulo = null;
		}
		
	}

	//Desenha triângulo à partir de pontos já desenhados e do clique no quadro
	private void desenharTriangulo(PontoGr p, Color cor, int borda) {
		if (novoTriangulo == null) {
			listaFormas.addAll(paneCanvas.getChildren());
			novoTriangulo = new TrianguloGr(p, null, null, cor, borda);
			novaLinha = new LinhaGr(cor, borda);
			novaLinha.setP1(p);
	
		}
		
		else if (novoTriangulo.getP2() == null) {
			//remove resquicios do snapshot anterior
			paneCanvas.getChildren().clear();	
			paneCanvas.getChildren().addAll(listaFormas);
				
			novoTriangulo.setP2(p);
			
			//desenha a linha, limpa objeto novaLinha utilizado para elastico de linhas e tira novo snapshot para desenho do triângulo
			novaLinha.setP2(p);
			novaLinha.desenhar(paneCanvas);
			novaLinha = null;
			
			listaFormas.clear();
			listaFormas.addAll(paneCanvas.getChildren());
		}
		
		else {
			//remove resquicios do snapshot anterior
			paneCanvas.getChildren().clear();	
			paneCanvas.getChildren().addAll(listaFormas);
			
			novoTriangulo.setP3(p);
			novoTriangulo.desenhar(paneCanvas);
			listaFormas.clear();
			novoTriangulo = null;
		}
	}
	
	//Desenha circulo de acordo com o clique no quadro
	private void desenharCirculo(PontoGr p, Color cor, int borda) {
		
		//Se centro do círculo ainda não foi fixado, é criado ponto no local do clique
		if (novoCirculo == null) {
			listaFormas.addAll(paneCanvas.getChildren());
			novoCirculo = new CirculoGr(cor, borda);
			novoCirculo.setCentro(p);
			imgSnapshot = cv_quadro.snapshot(new SnapshotParameters(), null); //Usado para efeito de elástico
		}
		
		//Desenha circulo de acordo com o ponto clicado em evento anterior e ponto atual
		else {
			paneCanvas.getChildren().clear();
			paneCanvas.getChildren().addAll(listaFormas);
			novoCirculo.setRaio(p);
			novoCirculo.desenhar(paneCanvas);
			
			//Limpa dados do circulo para desenho de novas formas
			listaFormas.clear();
			novoCirculo = null;
		}
	}

	//Desenha linha de acordo cliques no quadro
	private void desenharLinha(PontoGr p, Color cor, int borda) {
		
		if (novaLinha == null) {
			novaLinha = new LinhaGr(cor, borda);			
			novaLinha.setP1(p);
			listaFormas.addAll(paneCanvas.getChildren());
			imgSnapshot = cv_quadro.snapshot(new SnapshotParameters(), null);	//Usado para efeito de elástico	
		}
		
		else {
			//remove resquicios do snapshot anterior
			paneCanvas.getChildren().clear();	
			paneCanvas.getChildren().addAll(listaFormas);
			
			p = new PontoGr((int)p.getX(), (int)p.getY(), cor, borda);
			p.desenhar(paneCanvas);
			
			novaLinha.setP2(p);
			novaLinha.desenhar(paneCanvas);
			
			novaLinha = null;
			listaFormas.clear();
		}
		
	}

	//Desenha ponto conforme clique no quadro
	private void desenharPonto() {
		novoPonto.desenhar(paneCanvas);
		cv_quadro.toFront();
	}

}
