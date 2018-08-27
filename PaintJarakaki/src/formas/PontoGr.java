package formas;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import math.Ponto;

/**
 * Representa um ponto gráfico
 * 
 * @author Julio Arakaki
 * @version v1.0 Data: 2018/05/06
 * 
 */
public class PontoGr extends Ponto {
	Color cor; // cor do ponto 
	int diametro = 5; // diametro do ponto, default = 5

	/**
	 * Constroi um ponto na posicao x, y e com os atributos
	 * 
	 * @param x coordenada x
	 * @param y coordenada y
	 * @param cor cor do ponto a ser construido
	 * @param diametro diametro do ponto
	 */
	public PontoGr(int x, int y, Color cor){
		super((double)x, (double)y);
		setCor(cor);	 	 	 
	}

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
	public PontoGr(PontoGr pg, Color cor, int diametro){
		super(pg.getX(), pg.getY());	 
		setCor(cor);
		setDiametro(diametro);
	}

	/**
	 * Constroi um ponto na posicao 0, 0 com diametro e cor Black
	 * 
	 * @param diametro
	 */
	public PontoGr(int x, int y){
		super((double)x, (double)y);
		setCor(Color.BLACK);	 	  
	}

	/**
	 * Constroi um ponto na posicao 0, 0 com diametro e cor Black
	 * 
	 * @param diametro
	 */
	public PontoGr(int diametro){
		super(0, 0);	 
		setCor(Color.BLACK);	  
		setDiametro(diametro);

	}

	private Color getCor() {
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
	 * @param g contexto grafico
	 */
	public void desenharPonto(GraphicsContext g) {
		// desenha ponto como um oval
		g.setFill(getCor());
		g.fillOval((int)getX() -(getDiametro()/2), (int)getY() - (getDiametro()/2), getDiametro(), getDiametro());
	}
}