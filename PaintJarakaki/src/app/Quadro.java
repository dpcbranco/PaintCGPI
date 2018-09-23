package app;

import java.net.URL;
import java.util.ResourceBundle;

import figuras.Sierpinski;
import grafico.CirculoGr;
import grafico.LinhaGr;
import grafico.PontoGr;
import grafico.TrianguloGr;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;


public class Quadro implements Initializable{

	@FXML MenuBar menuOpcoes;
	
	@FXML RadioMenuItem rmiPonto;
	@FXML RadioMenuItem rmiLinha;
	@FXML RadioMenuItem rmiCirculo;
	@FXML RadioMenuItem rmiTriangulo;
	@FXML RadioMenuItem rmiSierpinski;
	@FXML MenuItem miLimpar;
	
	@FXML RadioMenuItem rmiPreto;
	@FXML RadioMenuItem rmiAmarelo;
	@FXML RadioMenuItem rmiVerde;
	@FXML RadioMenuItem rmiAzul;
	@FXML RadioMenuItem rmiVermelho;
	@FXML Slider slBorda;
	@FXML MenuItem miBorda;
	
	@FXML Canvas cv_quadro;
	
	WritableImage imgSnapshot;
	
	PontoGr p1 = null, p2 = null, p3 = null;
	LinhaGr novaLinha;  //Objeto usado para desenho e redesenho de linha el�stica
	CirculoGr novoCirculo;
	
	ToggleGroup tgFormas = new ToggleGroup();
	ToggleGroup tgCores = new ToggleGroup();
	GraphicsContext gcCanvas;
	Sierpinski sierpDesenho;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
				
		//Define grupo de formas a serem escolhidas
		rmiPonto.setToggleGroup(tgFormas);
		rmiLinha.setToggleGroup(tgFormas);
		rmiCirculo.setToggleGroup(tgFormas);
		rmiTriangulo.setToggleGroup(tgFormas);
		rmiSierpinski.setToggleGroup(tgFormas);
		
		//Define grupo de cores a serem escolhidas
		rmiPreto.setToggleGroup(tgCores);
		rmiAmarelo.setToggleGroup(tgCores);
		rmiVerde.setToggleGroup(tgCores);
		rmiAzul.setToggleGroup(tgCores);
		rmiVermelho.setToggleGroup(tgCores);
		
		//Define a cor que cada bot�o representa
		rmiPreto.setUserData(Color.BLACK);
		rmiAmarelo.setUserData(Color.YELLOW);
		rmiVerde.setUserData(Color.GREEN);
		rmiAzul.setUserData(Color.BLUE);
		rmiVermelho.setUserData(Color.RED);
		
		//Define op��es "DEFAULT"
		rmiPonto.setSelected(true);
		rmiPreto.setSelected(true);
		
		
		miBorda.setText(new Double(slBorda.getMajorTickUnit()).intValue() + "px");
		
		gcCanvas = cv_quadro.getGraphicsContext2D();
		gcCanvas.setFill(Color.WHITE);
		gcCanvas.fill();
		
		slBorda.valueProperty().addListener(
			(ev)->{
				miBorda.setText(new Double(slBorda.getValue()).intValue() + "px");
			}
		);
		
		miLimpar.setOnAction(
		
			(ev)->{
				gcCanvas.clearRect(0, 0, 1280, 770);
				gcCanvas.fill();
				sierpDesenho = null;
			}
		);
		
		
		cv_quadro.setOnMouseMoved(
			(ev)->{
				
				String opcaoForma = ((RadioMenuItem)tgFormas.getSelectedToggle()).getText();
				Color opcaoCor = (Color)((RadioMenuItem)tgCores.getSelectedToggle()).getUserData();
				
				if (p1 != null) {
					switch (opcaoForma) {
						case "Linha":{
							PontoGr p2 = new PontoGr((int) ev.getX(), (int) ev.getY(), opcaoCor, new Double(slBorda.getValue()).intValue());
							if (novaLinha == null) {
								novaLinha = new LinhaGr(p1, p2, opcaoCor, new Double(slBorda.getValue()).intValue());
								novaLinha.desenhar(gcCanvas);
							}
							
							else {
								cv_quadro.getGraphicsContext2D().drawImage(imgSnapshot, 0, 0);
								novaLinha = new LinhaGr(p1, p2, opcaoCor, new Double(slBorda.getValue()).intValue());
								novaLinha.desenhar(gcCanvas);
							}
							break;
						}
						
						case "Circulo":{
							PontoGr p2 = new PontoGr((int) ev.getX(), (int) ev.getY(), opcaoCor, new Double(slBorda.getValue()).intValue());
							if (novoCirculo == null) {
								novoCirculo = new CirculoGr(p1, p2, opcaoCor, new Double(slBorda.getValue()).intValue());
								novoCirculo.desenhar(gcCanvas);
							}
							
							else {
								cv_quadro.getGraphicsContext2D().drawImage(imgSnapshot, 0, 0);
								novoCirculo = new CirculoGr(p1, p2, opcaoCor, new Double(slBorda.getValue()).intValue());
								novoCirculo.desenhar(gcCanvas);
							}
							break;
						}
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
				
				
				switch (rmiOpcaoForma.getText()) {
					case "Ponto":{
						desenharPonto(ev, cor, borda);
						break;
					}
					
					case "Linha":{
						desenharLinha(ev, cor, borda);						
						break;
					}
					
					case "Circulo":{
						desenharCirculo(ev, cor, borda);						
						break;
					}
					
					case "Triangulo":{
						desenharTriangulo(ev, cor, borda);						
						break;
					}
					
					case "Sierpinski":{
						if (sierpDesenho == null) {
							sierpDesenho = new Sierpinski();
						}

						sierpDesenho.setBorda(borda);
						sierpDesenho.setCor(cor);
						sierpDesenho.desenharIteracao(gcCanvas);
						
						break;
					}
				}

			}
		);		
		
	}
	
	//Desenha tri�ngulo � partir de pontos j� desenhados e do clique no quadro
	private void desenharTriangulo(MouseEvent ev, Color cor, int borda) {
		if (p1 == null) {
			p1 = new PontoGr((int)ev.getX(), (int)ev.getY(), cor, borda);
			p1.desenhar(gcCanvas);
		}
		
		else if (p2 == null) {
			p2 = new PontoGr((int)ev.getX(), (int)ev.getY(), cor, borda);
			p2.desenhar(gcCanvas);
		}
		
		else {
			p3 = new PontoGr((int)ev.getX(), (int)ev.getY(), cor, borda);
			p3.desenhar(gcCanvas);
			
			new TrianguloGr(p1, p2, p3, cor, borda).desenhar(gcCanvas);
			
			//Limpa dados do triangulo atual para desenho de nova forma
			p1 = null;
			p2 = null;
			p3 = null;
		}
	}
	
	//Desenha circulo de acordo com o clique no quadro
	private void desenharCirculo(MouseEvent ev, Color cor, int borda) {
		
		//Se centro do c�rculo ainda n�o foi fixado, � criado ponto no local do clique
		if (p1 == null) {
			p1 = new PontoGr((int)ev.getX(), (int)ev.getY(), Color.WHITE, 0);
			imgSnapshot = cv_quadro.snapshot(new SnapshotParameters(), null);
		}
		
		//Desenha circulo de acordo com o ponto clicado em evento anterior e ponto atual
		else {
			//remove resquicios do desenho anterior
			cv_quadro.getGraphicsContext2D().drawImage(imgSnapshot, 0, 0);
			p2 = new PontoGr((int)ev.getX(), (int)ev.getY(), cor, borda);
			
			new CirculoGr(p1, p2, cor, borda).desenhar(gcCanvas);
			
			//Limpa dados do circulo para desenho de novas formas
			p1 = null;
			p2 = null;
		}
	}

	//Desenha linha de acordo cliques no quadro
	private void desenharLinha(MouseEvent ev, Color cor, int borda) {
		if (p1 == null) {

			p1 = new PontoGr((int)ev.getX(), (int)ev.getY(), cor, borda);
			p1.desenhar(gcCanvas);
			
			imgSnapshot = cv_quadro.snapshot(new SnapshotParameters(), null);			
		}
		
		else {
			//remove resquicios do desenho anterior
			cv_quadro.getGraphicsContext2D().drawImage(imgSnapshot, 0, 0);
			p2 = new PontoGr((int)ev.getX(), (int)ev.getY(), cor, borda);
			p2.desenhar(gcCanvas);
			
			new LinhaGr(p1, p2, cor, borda).desenhar(gcCanvas);
			
			p1 = null;
			p2 = null;
			novaLinha = null;
		}
		
	}

	//Desenha ponto conforme clique no quadro
	private void desenharPonto(MouseEvent ev, Color cor, int borda) {
		//Elimina resqu�cios dos outros desenhos
		p1 = null;
		p2 = null;
		p3 = null;
		
		//Desenha novo ponto na tela, � partir do X e Y do canvas
		new PontoGr((int)ev.getX(), (int)ev.getY(), cor, borda).desenhar(gcCanvas);
	}

}
