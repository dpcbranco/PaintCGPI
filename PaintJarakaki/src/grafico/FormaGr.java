package grafico;

import formas.Ponto;
import javafx.scene.layout.Pane;

public interface FormaGr {
	public void desenhar(Pane p);
	public void selecionar();
	public void mover(double x, double y); 		//Move de acordo com a diferença de x e y do ponto movido
	public void rotacao(Ponto pBase, double angulo);	//Rotaciona de acordo com os vértices da forma	
	public boolean selecionado();				//Retorna se forma está selecionada
	public void marcarRotacao();				//Marca pontos disponíveis para rotacao
	public void escala(Pane pane, Ponto pBase);
}
