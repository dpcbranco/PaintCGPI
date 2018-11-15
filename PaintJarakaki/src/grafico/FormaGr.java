package grafico;

import javafx.scene.layout.Pane;

public interface FormaGr {
	public void desenhar(Pane p);
	public void selecionar();
	public void mover(double x, double y); //Move de acordo com a diferença de x e y do ponto movido
	public void rotacao(double x, double y);	
	public boolean selecionado();
}
