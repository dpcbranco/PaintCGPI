package app;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import figuras.Sierpinski;
import formas.Forma;
import formas.Ponto;
import grafico.FormaGr;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import xml.Xml;


public class Quadro implements Initializable{

	@FXML Pane paneCanvas;
	
	@FXML RadioMenuItem rmiPonto;
	@FXML RadioMenuItem rmiLinha;
	@FXML RadioMenuItem rmiCirculo;
	@FXML RadioMenuItem rmiTriangulo;
	@FXML RadioMenuItem rmiSierpinski;
	@FXML RadioMenuItem rmiRetangulo;
	@FXML RadioMenuItem rmiPoligono;
	@FXML RadioMenuItem rmiLinhaPoligonal;
	
	@FXML Menu mCor;	
	@FXML MenuItem miLimpar;
	@FXML MenuItem miSelecionar;	
	@FXML MenuItem miDeletar;
	@FXML MenuItem miSalvar;
	@FXML MenuItem miAbrir;
	@FXML MenuItem miClipping;
	@FXML MenuItem miMover;
	
	@FXML Slider slBorda;
	@FXML MenuItem miBorda;
	
	@FXML Canvas cv_quadro;
	
	
	Desenho desenhador; //Objeto responsável pelo desenho das formas conforme evento recebido	
	Clipping ferramentaCorte; 
	ToggleGroup tgFormas = new ToggleGroup();
	ColorPicker corDesenho = new ColorPicker(Color.BLACK);
	Sierpinski sierpDesenho;
	
	String txtXml;
	Xml arqXml;	
	ArrayList<FormaGr> listaFormas = new ArrayList<>();
	
	private static boolean selecionar = false, recortar = false, mover = false;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		desenhador = new Desenho(paneCanvas);
		ferramentaCorte = new Clipping (paneCanvas);
				
		//Define grupo de formas a serem escolhidas
		rmiPonto.setToggleGroup(tgFormas);
		rmiLinha.setToggleGroup(tgFormas);
		rmiCirculo.setToggleGroup(tgFormas);
		rmiTriangulo.setToggleGroup(tgFormas);
		rmiSierpinski.setToggleGroup(tgFormas);
		rmiRetangulo.setToggleGroup(tgFormas);
		rmiPoligono.setToggleGroup(tgFormas);
		rmiLinhaPoligonal.setToggleGroup(tgFormas);
				
		//Define opções "DEFAULT"
		rmiPonto.setSelected(true);
		
		miBorda.setText(new Double(slBorda.getMajorTickUnit()).intValue() + "px");
		
		corDesenho.setStyle("-fx-color-label-visible: false ;");
		mCor.getItems().add(new MenuItem(null, corDesenho));
		
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
		
		miClipping.setOnAction(
			(ev)->{
				if (recortar) {
					recortar = false;
					miClipping.setText("Recortar");
				}
				
				else {
					recortar = true;
					miClipping.setText("✓ Recortar");
				}
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
					
					for (FormaGr f : listaFormas) {
						if (f.selecionado()) {
							f.selecionar();
						}
					}
				}
					
			}
		);
		
		miDeletar.setOnAction( 
			(ev)->{
				ArrayList<Node> formas = new ArrayList<>();
				formas.addAll(paneCanvas.getChildren());
				for (int i = 1; i < formas.size(); i++) {
					if (formas.get(i).getClass().getSimpleName().equals("Ellipse")) {
						Ellipse e = (Ellipse) formas.get(i);
						if (e.getOpacity() == 0.1) {
							paneCanvas.getChildren().remove(e);
						}
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
					if (arqXml.getArqXml() != null) {
						arqXml.escreverXml(listaFormas);
					}
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
					fc.setTitle("Abrir imagem...");
					fc.getExtensionFilters().add(new ExtensionFilter("XML File (.xml)", "*.xml"));
					
					arqXml = new Xml(fc.showOpenDialog(new Stage()));
					if (arqXml.getArqXml() != null) {
						listaFormas = arqXml.lerXml(paneCanvas);
					}
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		);
		
		miMover.setOnAction(
			(ev)->{
				if (mover) {
					cv_quadro.toFront();
					mover = false;
					miMover.setText("Mover");
				}
				
				else {
					cv_quadro.toBack();					
					mover = true;
					miMover.setText("✓ Mover");
				}
			}
		);
		
		//Trata movimento do mouse durante desenho das formas
		cv_quadro.setOnMouseMoved(
			(ev)->{
				
				Ponto pontoEv = new Ponto((int)ev.getX(), (int)ev.getY());
				
				if (recortar) {
					ferramentaCorte.elasticoCorte(pontoEv);
					cv_quadro.toFront();
				}
				
				else if (!selecionar && !mover) {
					
					String opcaoForma = ((RadioMenuItem)tgFormas.getSelectedToggle()).getText();
					Color opcaoCor = corDesenho.getValue();
					int opcaoBorda = new Double(slBorda.getValue()).intValue();

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
		
		//Inicio de desenho de formas
		cv_quadro.setOnMouseClicked(
			(ev)->{
				
				Ponto novoPonto = new Ponto((int)ev.getX(), (int)ev.getY());	
				
				if (recortar) {
					if (ferramentaCorte.getPInicial() == null) {
						ferramentaCorte.iniciarCorte(novoPonto);
					}
					
					else {
						ferramentaCorte.encerrarCorte(novoPonto);
					}
					
					cv_quadro.toFront();
				}
				
				
				else if (!selecionar && !mover) {
					//Obtém opções selecionadas de forma, cor e borda
					RadioMenuItem rmiOpcaoForma = (RadioMenuItem) tgFormas.getSelectedToggle();				
					Color cor = corDesenho.getValue();
					int borda = new Double(slBorda.getValue()).intValue();
				
					switch (rmiOpcaoForma.getText()) {
						case "Ponto":{
							listaFormas.add(desenhador.desenharPonto(novoPonto, cor, borda));
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
	
	public static boolean getSelecionar() {
		return selecionar;
	}
	
	public static boolean getRecortar() {
		return recortar;
	}
	
	public static boolean getMover() {
		return mover;
	}

	public static boolean getRotacionar() {
		return true;
	}
	
}
