package app;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import figuras.Sierpinski;
import formas.Formas;
import formas.Ponto;
import grafico.PontoGr;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import xml.Xml;
import javafx.stage.Stage;


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
	@FXML MenuItem miAbrir;
	
	@FXML Slider slBorda;
	@FXML MenuItem miBorda;
	
	@FXML Canvas cv_quadro;
	
	
	Desenho desenhador; //Objeto responsável pelo desenho das formas conforme evento recebido	
	
	ToggleGroup tgFormas = new ToggleGroup();
	ToggleGroup tgCores = new ToggleGroup();
	Sierpinski sierpDesenho;
	
	String txtXml;
	Xml arqXml;
	
	ArrayList<Formas> listaFormas = new ArrayList<>();
	
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
		
		//Move as formas para frente, permitindo sua seleção
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
					if (e.getOpacity() == 0.1) {
						paneCanvas.getChildren().remove(e);
					}
				}
			}
		);
		
		miSalvar.setOnAction( 
			(ev)->{
				FileChooser fc = new FileChooser();
				fc.setTitle("Salvar imagem...");
				fc.getExtensionFilters().add(new ExtensionFilter("XML File (.xml)", "*.xml"));
				
				try {
					arqXml = new Xml(fc.showSaveDialog(new Stage()));	
					arqXml.escreverXml(listaFormas);
				}catch (IOException e) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("ERRO AO SALVAR !");
					alert.setContentText("Erro ao salvar arquivo XML. Favor verifique a permissão da pasta e tente novamente!");
					alert.showAndWait();
				}
			}
		);
		
		
		//Abrir arquivo XML
		miAbrir.setOnAction(
			(ev)->{
				try {
					//limpa desenho anterior
					paneCanvas.getChildren().clear();
					paneCanvas.getChildren().add(cv_quadro);
					sierpDesenho = null;
					
					//Carrega desenho a partir de XML escolhido
					FileChooser fc = new FileChooser();
					arqXml = new Xml(fc.showOpenDialog(new Stage()));
					listaFormas = arqXml.lerXml(paneCanvas);
				} catch(Exception e) {
					e.printStackTrace();
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
				
				if (!selecionar) {

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
							listaFormas.add((Ponto) novoPonto);
							break;
						}
					
						case "Linha":{
							desenhador.desenharLinha(novoPonto, listaFormas, cor, borda);						
							break;
						}
					
						case "Circulo":{
							desenhador.desenharCirculo(novoPonto, listaFormas, cor, borda);						
							break;
						}
					
						case "Triangulo":{
							desenhador.desenharTriangulo(novoPonto, listaFormas, cor, borda);						
							break;
						}
					
						case "Retangulo":{
							desenhador.desenharRetangulo(novoPonto, listaFormas, cor, borda);
							break;
						}
					
						case "Poligono":{
							desenhador.desenharPoligono(ev, listaFormas, cor, borda);
							break;
						
						}
					
						case "Linha Poligonal":{
							desenhador.desenharLinhaPoligonal(ev, listaFormas, cor, borda);
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
