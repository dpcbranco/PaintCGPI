package app;

import formas.Ponto;
import grafico.RetanguloGr;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
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
		Rectangle2D viewport = new Rectangle2D(pInicial.getX(), pInicial.getY(), this.larguraCorte(novoPonto), this.alturaCorte(novoPonto));
		SnapshotParameters paramClip = new SnapshotParameters();
		WritableImage clip;
		Canvas telaClip = new Canvas();
		Pane parent = new Pane();
		
		janelaCorte.setHeight(this.alturaCorte(novoPonto));
		janelaCorte.setWidth(larguraCorte(novoPonto));
		
		parent.getChildren().add(telaClip);
		
		paramClip.setViewport(viewport);
		clip = desenhoSelecao.getPaneCanvas().snapshot(paramClip, null);
		telaClip.getGraphicsContext2D().drawImage(clip, 0, 0);
		
		desenhoSelecao.desenharCorte(novoPonto);
		janelaCorte.initModality(Modality.APPLICATION_MODAL);
		janelaCorte.setScene(new Scene(parent));
		janelaCorte.show();
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
