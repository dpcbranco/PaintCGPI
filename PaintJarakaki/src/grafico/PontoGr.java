package grafico;

import formas.Ponto;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

/**
 * Representa um ponto gr�fico
 * 
 * @author Julio Arakaki
 * @version v1.0 Data: 2018/05/06
 * 
 */
public class PontoGr extends Ponto implements FormaGr {
	Color cor; // cor do ponto 
	int diametro; // diametro do ponto, default = 5
	Ellipse e;
	
	/**
	 * Constroi um ponto na posicao x, y e com os atributos
	 * 
	 * @param x coordenada x
	 * @param y coordenada y
	 * @param cor cor do ponto a ser construido
	 * @param diametro diametro do ponto
	 */
	public PontoGr(int x, int y, Color cor, int diametro){
		super((double)x, (double)y);
		setCor(cor);	 	 
		setDiametro(diametro);
	}

	/**
	 * Constroi um ponto baseado em outro ponto grafico
	 * 
	 * @param pg outro ponto
	 * @param cor cor do ponto a ser construido
	 * @param diametro diametro do ponto
	 */
	public PontoGr(Ponto pg, Color cor, int diametro){
		super(pg.getX(), pg.getY());	 
		setCor(cor);
		setDiametro(diametro);
	}


	public Color getCor() {
		return cor;
	}


	private int getDiametro() {
		return diametro;
	}

	private void setDiametro(int diametro) {
		this.diametro = diametro;
	}

	private void setCor(Color cor){
		this.cor = cor;
	}

	/**
	 * desenha um ponto utilizando o oval 
	 * 
	 * @param pane contexto grafico
	 */
	public void desenhar(Pane pane) {
		e = new Ellipse();
		
		// desenha ponto como um oval
		e.setCenterX(getX());
		e.setCenterY(getY());
		e.setRadiusX(getDiametro());
		e.setRadiusY(getDiametro());
		
		e.setFill(getCor());
		
		e.setOnMouseClicked(
			(ev)->{
				this.selecionar();
			}
		);
		
		pane.getChildren().add(e);
		/*g.setFill(getCor());
		sc.fillOval((int)getX() -(getDiametro()/2), (int)getY() - (getDiametro()/2), getDiametro(), getDiametro());*/
	}

	//@Override
	public void selecionar() {
		e.setOpacity(0.1);
	}
	
	public Ellipse getEllipse() {
		return this.e;
	}

}