package app;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import figuras.Sierpinski;
import grafico.PontoGr;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleGroup;
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
	@FXML MenuItem miSalvar;
	
	@FXML Slider slBorda;
	@FXML MenuItem miBorda;
	
	@FXML Canvas cv_quadro;
	
	
	Desenho desenhador; //Objeto responsável pelo desenho das formas conforme evento recebido	
	
	ToggleGroup tgFormas = new ToggleGroup();
	ToggleGroup tgCores = new ToggleGroup();
	Sierpinski sierpDesenho;
	
	
	boolean selecionar = false;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		desenhador = new Desenho(paneCanvas);
				
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
		
		//Traz quadro para frente para que eventos do mouse sejam capturados
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
						desenhador.elasticoLinha(pontoEv, opcaoCor, opcaoBorda);
						break;
					}
						
					case "Circulo":{
						desenhador.elasticoCirculo(pontoEv, opcaoCor, opcaoBorda);	
						break;
					}
						
					case "Triangulo":{
						desenhador.elasticoTriangulo(pontoEv, opcaoCor, opcaoBorda);	
						break;
					}
						
					case "Retangulo":{
						desenhador.elasticoRetangulo(pontoEv, opcaoCor, opcaoBorda);
						break;
					}
						
					case "Poligono":{
						desenhador.elasticoPoligono(pontoEv, opcaoCor, opcaoBorda);
						break;
					}
					
					case "Linha Poligonal":{
						desenhador.elasticoLinhaPoligonal(pontoEv, opcaoCor, opcaoBorda);
						break;
					}
				}
				
				//Traz canvas para frente, para que próximos eventos possam ser capturados
				cv_quadro.toFront();
				
			}
		);
		
		cv_quadro.setOnMouseClicked(
			(ev)->{
				if (!selecionar) {
					//Obtém opções selecionadas de forma, cor e borda
					RadioMenuItem rmiOpcaoForma = (RadioMenuItem) tgFormas.getSelectedToggle();
					RadioMenuItem rmiOpcaoCor = (RadioMenuItem) tgCores.getSelectedToggle();
				
					Color cor = (Color) rmiOpcaoCor.getUserData();
					int borda = new Double(slBorda.getValue()).intValue();
					
					PontoGr novoPonto = new PontoGr((int)ev.getX(), (int)ev.getY(), cor, borda);	
					desenhador.setNovoPonto(novoPonto);
				
					switch (rmiOpcaoForma.getText()) {
						case "Ponto":{
							desenhador.desenharPonto();
							break;
						}
					
						case "Linha":{
							desenhador.desenharLinha(novoPonto, cor, borda);						
							break;
						}
					
						case "Circulo":{
							desenhador.desenharCirculo(novoPonto, cor, borda);						
							break;
						}
					
						case "Triangulo":{
							desenhador.desenharTriangulo(novoPonto, cor, borda);						
							break;
						}
					
						case "Retangulo":{
							desenhador.desenharRetangulo(novoPonto, cor, borda);
							break;
						}
					
						case "Poligono":{
							desenhador.desenharPoligono(ev, cor, borda);
							break;
						
						}
					
						case "Linha Poligonal":{
							desenhador.desenharLinhaPoligonal(ev, cor, borda);
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
					
					cv_quadro.toFront();
				}
			}
		);		
	}
	
}
