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


public class Quadro implements Initializable{

	@FXML MenuBar menu_formas;
	@FXML RadioMenuItem rmiPonto;
	@FXML RadioMenuItem rmiLinha;
	@FXML RadioMenuItem rmiCirculo;
	@FXML Canvas cv_quadro;
	
	PontoGr p1 = null, p2 = null;
	
	ToggleGroup tgFormas = new ToggleGroup();
	GraphicsContext gcCanvas;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
				
		rmiPonto.setToggleGroup(tgFormas);
		rmiLinha.setToggleGroup(tgFormas);
		rmiCirculo.setToggleGroup(tgFormas);
		
		rmiPonto.setSelected(true);
		
		gcCanvas = cv_quadro.getGraphicsContext2D();
		
		cv_quadro.setOnMouseClicked(
			(ev)->{
				RadioMenuItem rmiSelecionado = (RadioMenuItem) tgFormas.getSelectedToggle();
				
				switch (rmiSelecionado.getText()) {
					case "Ponto":{
						//Elimina resquícios dos outros desenhos
						p1 = null;
						
						new PontoGr((int)ev.getX(), (int)ev.getY()).desenharPonto(gcCanvas);
						break;
					}
					
					case "Linha":{
						if (p1 == null) {
							p1 = new PontoGr((int)ev.getX(), (int)ev.getY());
							p1.desenharPonto(gcCanvas);
						}
						
						else {
							p2 = new PontoGr((int)ev.getX(), (int)ev.getY());
							p2.desenharPonto(gcCanvas);
							
							new LinhaGr(p1, p2).desenharLinha(gcCanvas);
							
							p1 = null;
							p2 = null;
						}
						
						break;
					}
					
					case "Circulo":{
						if (p1 == null) {
							p1 = new PontoGr((int)ev.getX(), (int)ev.getY());
							p1.desenharPonto(gcCanvas);
						}
						
						else {
							p2 = new PontoGr((int)ev.getX(), (int)ev.getY());
							p2.desenharPonto(gcCanvas);
							
							new CirculoGr(p1, p2).desenharCirculo(gcCanvas);
							
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
