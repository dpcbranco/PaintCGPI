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
	
	//Objeto usado para desenho e redesenho de linha elástica
	LinhaGr novaLinha;  
	CirculoGr novoCirculo;
	TrianguloGr novoTriangulo;
	
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
		
		//Define a cor que cada botï¿½o representa
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
		
		//Trata movimento do mouse durante desenho das formas
		cv_quadro.setOnMouseMoved(
			(ev)->{
				
				String opcaoForma = ((RadioMenuItem)tgFormas.getSelectedToggle()).getText();
				Color opcaoCor = (Color)((RadioMenuItem)tgCores.getSelectedToggle()).getUserData();
				int opcaoBorda = new Double(slBorda.getValue()).intValue();
				
				//Caso desenho não tenha sido iniciado (p1 do desenho é nulo) ignora o evento
				if (p1 != null) {
					switch (opcaoForma) {
						case "Linha":{
							elasticoLinha(ev, opcaoCor, opcaoBorda);
							break;
						}
						
						case "Circulo":{
							elasticoCirculo(ev, opcaoCor, opcaoBorda);
							break;
						}
						
						case "Triangulo":{
							//para dois primeiros pontos, será utilizado elástico de linha
							if (p2 == null) {
								elasticoLinha(ev, opcaoCor, opcaoBorda);
							}
							else {
								elasticoTriangulo(ev, opcaoCor, opcaoBorda);
							}
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
	
	private void elasticoTriangulo(MouseEvent ev, Color opcaoCor, int opcaoBorda) {
		PontoGr p3 = new PontoGr((int) ev.getX(), (int) ev.getY(), opcaoCor, opcaoBorda);
		if (novoTriangulo == null) {
			novoTriangulo = new TrianguloGr(p1, p2, p3, opcaoCor, opcaoBorda);
			novoTriangulo.desenhar(gcCanvas);
		}
		else {
			cv_quadro.getGraphicsContext2D().drawImage(imgSnapshot, 0, 0);
			novoTriangulo = new TrianguloGr(p1, p2, p3, opcaoCor, opcaoBorda);
			novoTriangulo.desenhar(gcCanvas);
		}
	}

	//Desenha linhas que reproduzem efeito do elástico movimento do mouse
	private void elasticoLinha(MouseEvent ev, Color opcaoCor, int opcaoBorda) {
		PontoGr p2 = new PontoGr((int) ev.getX(), (int) ev.getY(), opcaoCor, opcaoBorda);
		if (novaLinha == null) {
			novaLinha = new LinhaGr(p1, p2, opcaoCor, opcaoBorda);
			novaLinha.desenhar(gcCanvas);
		}
		
		else {
			cv_quadro.getGraphicsContext2D().drawImage(imgSnapshot, 0, 0);
			novaLinha = new LinhaGr(p1, p2, opcaoCor, new Double(slBorda.getValue()).intValue());
			novaLinha.desenhar(gcCanvas);
		}
	}
	
	
	private void elasticoCirculo(MouseEvent ev, Color opcaoCor, int opcaoBorda) {
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
	}

	//Desenha triângulo à partir de pontos já desenhados e do clique no quadro
	private void desenharTriangulo(MouseEvent ev, Color cor, int borda) {
		if (p1 == null) {
			p1 = new PontoGr((int)ev.getX(), (int)ev.getY(), cor, borda);
			p1.desenhar(gcCanvas);
			imgSnapshot = cv_quadro.snapshot(new SnapshotParameters(), null);	//Usado para efeito de elástico	
		}
		
		else if (p2 == null) {
			imgSnapshot = cv_quadro.snapshot(new SnapshotParameters(), null);	//remove resquicios do desenho anterior	
			p2 = new PontoGr((int)ev.getX(), (int)ev.getY(), cor, borda);
			
			//desenha a linha, limpa objeto novaLinha utilizado para elastico de linhas e tira novo snapshot para desenho do triângulo
			new LinhaGr(p1, p2, cor, borda);
			novaLinha = null;
			imgSnapshot = cv_quadro.snapshot(new SnapshotParameters(), null);	//Usado para efeito de elástico	
		}
		
		else {
			p3 = new PontoGr((int)ev.getX(), (int)ev.getY(), cor, borda);
			p3.desenhar(gcCanvas);
			
			new TrianguloGr(p1, p2, p3, cor, borda).desenhar(gcCanvas);
			
			//Limpa dados do triangulo atual para desenho de nova forma
			p1 = null;
			p2 = null;
			p3 = null;
			novoTriangulo = null;
		}
	}
	
	//Desenha circulo de acordo com o clique no quadro
	private void desenharCirculo(MouseEvent ev, Color cor, int borda) {
		
		//Se centro do círculo ainda não foi fixado, é criado ponto no local do clique
		if (p1 == null) {
			p1 = new PontoGr((int)ev.getX(), (int)ev.getY(), Color.WHITE, 0);
			imgSnapshot = cv_quadro.snapshot(new SnapshotParameters(), null); //Usado para efeito de elástico
		}
		
		//Desenha circulo de acordo com o ponto clicado em evento anterior e ponto atual
		else {
			cv_quadro.getGraphicsContext2D().drawImage(imgSnapshot, 0, 0);	//remove resquicios do snapshot anterior
			p2 = new PontoGr((int)ev.getX(), (int)ev.getY(), cor, borda);
			
			new CirculoGr(p1, p2, cor, borda).desenhar(gcCanvas);
			
			//Limpa dados do circulo para desenho de novas formas
			p1 = null;
			p2 = null;
			novoCirculo = null;
		}
	}

	//Desenha linha de acordo cliques no quadro
	private void desenharLinha(MouseEvent ev, Color cor, int borda) {
		if (p1 == null) {

			p1 = new PontoGr((int)ev.getX(), (int)ev.getY(), cor, borda);
			p1.desenhar(gcCanvas);
			
			imgSnapshot = cv_quadro.snapshot(new SnapshotParameters(), null);	//Usado para efeito de elástico	
		}
		
		else {
			cv_quadro.getGraphicsContext2D().drawImage(imgSnapshot, 0, 0);	//remove resquicios do snapshot anterior
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
		//Elimina resquícios de outras formas não terminadas
		p1 = null;
		p2 = null;
		p3 = null;
		
		//Desenha novo ponto na tela, à partir do X e Y do canvas
		new PontoGr((int)ev.getX(), (int)ev.getY(), cor, borda).desenhar(gcCanvas);
	}

}
