package app;

import java.util.ArrayList;

import formas.Ponto;
import grafico.RetanguloGr;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Ellipse;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Clipping {
	
	RetanguloGr retSelecao;
	Desenho desenhoSelecao;
	Ponto pInicial; 
	WritableImage clip;
	
	public Clipping(Pane pc) {
		desenhoSelecao = new Desenho(pc);
	}

	public Ponto getPInicial() {
		return pInicial;
	}

	public void setPInicial(Ponto pInicial) {
		this.pInicial = pInicial;
	}

	public void iniciarCorte(Ponto novoPonto) {
		pInicial = novoPonto;
		desenhoSelecao.desenharCorte(novoPonto);
	}

	public void encerrarCorte(Ponto novoPonto) {
		Stage janelaCorte = new Stage();
		Rectangle2D areaClip = new Rectangle2D(pInicial.getX(), pInicial.getY(), larguraCorte(novoPonto), alturaCorte(novoPonto));
		ArrayList<Ellipse> listaRecorte = new ArrayList<>();
		Pane clip = new Pane();
		
		desenhoSelecao.desenharCorte(novoPonto);
		
		for (Node n : desenhoSelecao.getPaneCanvas().getChildren()) {
			if (n.getClass().getSimpleName().equals("Ellipse")) {
				Ellipse e = (Ellipse) n;
				if (areaClip.contains(e.getCenterX(), e.getCenterY())) {
					listaRecorte.add(copiarEllipse(e));
				}
			}
		}
		
		clip.getChildren().addAll(listaRecorte);
		
		janelaCorte.initModality(Modality.APPLICATION_MODAL);
		janelaCorte.setScene(new Scene(clip));
		janelaCorte.show();
		
		pInicial = null;
	}
	
	//Copia propriedades de um Ellipse
	private Ellipse copiarEllipse(Ellipse e) {
		Ellipse copia = new Ellipse();
		
		copia.setCenterX(e.getCenterX());
		copia.setCenterY(e.getCenterY());
		copia.setRadiusX(e.getRadiusX());
		copia.setRadiusY(e.getRadiusY());
		copia.setFill(e.getFill());
		
		return copia;
	}

	public void elasticoCorte (Ponto pontoElastico) {
		desenhoSelecao.elasticoCorte(pontoElastico);
	}
	
	private double larguraCorte (Ponto novoPonto) {
		return Math.abs(novoPonto.getX() - pInicial.getX());
	}
	
	private double alturaCorte (Ponto novoPonto) {
		return Math.abs(novoPonto.getY() - pInicial.getY());
	}
}
