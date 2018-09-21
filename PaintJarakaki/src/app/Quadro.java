package app;

import java.net.URL;
import java.util.ResourceBundle;

import figuras.Sierpinski;
import formas.CirculoGr;
import formas.LinhaGr;
import formas.PontoGr;
import formas.TrianguloGr;
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
	LinhaGr novaLinha;  //Objeto usado para desenho e redesenho de linha elástica
	
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
		
		slBorda.valueProperty().addListener(
			(ev)->{
				miBorda.setText(new Double(slBorda.getValue()).intValue() + "px");
			}
		);
		
		miLimpar.setOnAction(
		
			(ev)->{
				gcCanvas.clearRect(0, 0, 1280, 770);
				gcCanvas.setFill(Color.WHITE);
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
								novaLinha.desenharLinha(gcCanvas);
							}
							
							else {
								cv_quadro.getGraphicsContext2D().drawImage(imgSnapshot, 0, 0);
								//novaLinha.apagarLinha(gcCanvas);
								novaLinha = new LinhaGr(p1, p2, opcaoCor, new Double(slBorda.getValue()).intValue());
								novaLinha.desenharLinha(gcCanvas);
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
				
				
				switch (rmiOpcaoForma.getText()) {
					case "Ponto":{
						//Elimina resquícios dos outros desenhos
						p1 = null;
						p2 = null;
						p3 = null;
						
						//Desenha novo ponto na tela, à partir do X e Y do canvas
						new PontoGr((int)ev.getX(), (int)ev.getY(), (Color) rmiOpcaoCor.getUserData(), new Double(slBorda.getValue()).intValue()).desenharPonto(gcCanvas);
						break;
					}
					
					case "Linha":{
						if (p1 == null) {
							p1 = new PontoGr((int)ev.getX(), (int)ev.getY(), (Color) rmiOpcaoCor.getUserData(), new Double(slBorda.getValue()).intValue());
							p1.desenharPonto(gcCanvas);
							
							imgSnapshot = cv_quadro.snapshot(new SnapshotParameters(), null);
							
						}
						
						else {
							
							p2 = new PontoGr((int)ev.getX(), (int)ev.getY(), (Color) rmiOpcaoCor.getUserData(), new Double(slBorda.getValue()).intValue());
							p2.desenharPonto(gcCanvas);
							
							new LinhaGr(p1, p2, (Color) rmiOpcaoCor.getUserData(), new Double(slBorda.getValue()).intValue()).desenharLinha(gcCanvas);
							
							p1 = null;
							p2 = null;
							novaLinha = null;
						}
						
						break;
					}
					
					case "Circulo":{
						if (p1 == null) {
							p1 = new PontoGr((int)ev.getX(), (int)ev.getY(), (Color) rmiOpcaoCor.getUserData());
						}
						
						else {
							p2 = new PontoGr((int)ev.getX(), (int)ev.getY(), (Color) rmiOpcaoCor.getUserData());
							
							new CirculoGr(p1, p2, (Color) rmiOpcaoCor.getUserData(), new Double(slBorda.getValue()).intValue()).desenharCirculo(gcCanvas);
							
							p1 = null;
							p2 = null;
						}
						
						break;
					}
					
					case "Triangulo":{
						if (p1 == null) {
							p1 = new PontoGr((int)ev.getX(), (int)ev.getY(), (Color) rmiOpcaoCor.getUserData());
							p1.desenharPonto(gcCanvas);
						}
						
						else if (p2 == null) {
							p2 = new PontoGr((int)ev.getX(), (int)ev.getY(), (Color) rmiOpcaoCor.getUserData());
							p2.desenharPonto(gcCanvas);
						}
						
						else {
							p3 = new PontoGr((int)ev.getX(), (int)ev.getY(), (Color) rmiOpcaoCor.getUserData());
							p3.desenharPonto(gcCanvas);
							
							new TrianguloGr(p1, p2, p3, (Color) rmiOpcaoCor.getUserData(), new Double(slBorda.getValue()).intValue()).desenharTriangulo(gcCanvas);
							
							p1 = null;
							p2 = null;
							p3 = null;
						}
						
						break;
					}
					
					case "Sierpinski":{
						if (sierpDesenho == null) {
							sierpDesenho = new Sierpinski();
						}

						sierpDesenho.setBorda(new Double (slBorda.getValue()).intValue());
						sierpDesenho.setCor((Color) rmiOpcaoCor.getUserData());
						sierpDesenho.desenharIteracao(gcCanvas);
						
						break;
					}
				}

			}
		);		
		
	}

}
