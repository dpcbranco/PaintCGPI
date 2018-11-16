package grafico;

import formas.Ponto;
import javafx.scene.layout.Pane;

public interface FormaGr {
	public void desenhar(Pane p);
	public void selecionar();
	public void mover(double x, double y); 		//Move de acordo com a diferen�a de x e y do ponto movido
	public void rotacao(Ponto pBase, double angulo);	//Rotaciona de acordo com os v�rtices da forma	
	public boolean selecionado();				//Retorna se forma est� selecionada
	public void marcarRotacao();				//Marca pontos dispon�veis para rotacao
	public void escala(Pane pane, Ponto pBase);
}
