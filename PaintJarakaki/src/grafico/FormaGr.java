package grafico;

import javafx.scene.layout.Pane;

public interface FormaGr {
	public void desenhar(Pane p);
	public void selecionar();
	public void mover(double x, double y);
	public void rotacao(double x, double y);
}
