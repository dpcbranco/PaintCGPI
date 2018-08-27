package grafico;

import java.net.URL;
import java.util.ResourceBundle;

import formas.CirculoGr;
import formas.LinhaGr;
import formas.PontoGr;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.MenuBar;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.Color;


public class Quadro implements Initializable{

	@FXML MenuBar menuOpcoes;
	
	@FXML RadioMenuItem rmiPonto;
	@FXML RadioMenuItem rmiLinha;
	@FXML RadioMenuItem rmiCirculo;
	
	@FXML RadioMenuItem rmiPreto;
	@FXML RadioMenuItem rmiAmarelo;
	@FXML RadioMenuItem rmiVerde;
	@FXML RadioMenuItem rmiAzul;
	@FXML RadioMenuItem rmiVermelho;
	
	@FXML Canvas cv_quadro;
	
	
	PontoGr p1 = null, p2 = null;
	
	ToggleGroup tgFormas = new ToggleGroup();
	ToggleGroup tgCores = new ToggleGroup();
	
	GraphicsContext gcCanvas;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
				
		//Define grupo de formas a serem escolhidas
		rmiPonto.setToggleGroup(tgFormas);
		rmiLinha.setToggleGroup(tgFormas);
		rmiCirculo.setToggleGroup(tgFormas);
		
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
		
		gcCanvas = cv_quadro.getGraphicsContext2D();
		
		cv_quadro.setOnMouseClicked(
			(ev)->{
				RadioMenuItem rmiOpcaoForma = (RadioMenuItem) tgFormas.getSelectedToggle();
				RadioMenuItem rmiOpcaoCor = (RadioMenuItem) tgCores.getSelectedToggle();
				
				
				switch (rmiOpcaoForma.getText()) {
					case "Ponto":{
						//Elimina resquícios dos outros desenhos
						p1 = null;
						
						new PontoGr((int)ev.getX(), (int)ev.getY(), (Color) rmiOpcaoCor.getUserData()).desenharPonto(gcCanvas);
						break;
					}
					
					case "Linha":{
						if (p1 == null) {
							p1 = new PontoGr((int)ev.getX(), (int)ev.getY(), (Color) rmiOpcaoCor.getUserData());
							p1.desenharPonto(gcCanvas);
						}
						
						else {
							p2 = new PontoGr((int)ev.getX(), (int)ev.getY(), (Color) rmiOpcaoCor.getUserData());
							p2.desenharPonto(gcCanvas);
							
							new LinhaGr(p1, p2, (Color) rmiOpcaoCor.getUserData()).desenharLinha(gcCanvas);
							
							p1 = null;
							p2 = null;
						}
						
						break;
					}
					
					case "Circulo":{
						if (p1 == null) {
							p1 = new PontoGr((int)ev.getX(), (int)ev.getY(), (Color) rmiOpcaoCor.getUserData());
							p1.desenharPonto(gcCanvas);
						}
						
						else {
							p2 = new PontoGr((int)ev.getX(), (int)ev.getY(), (Color) rmiOpcaoCor.getUserData());
							p2.desenharPonto(gcCanvas);
							
							new CirculoGr(p1, p2, (Color) rmiOpcaoCor.getUserData()).desenharCirculo(gcCanvas);
							
							p1 = null;
							p2 = null;
						}
						
						break;
					}
				}

			}
		);		
		
	}
    
	
	public void buttonsEvents(String event) {
		
		switch(event) {
			case "Ponto":
				System.out.println("clicou no ponto");
			break;
		}
	}

}
