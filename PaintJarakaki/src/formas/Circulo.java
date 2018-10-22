package formas;

import java.util.ArrayList;

public class Circulo implements Formas{
	
	protected Ponto centro, perimetro;
	double raio = 0;
	
	public Circulo (Ponto centro) {
		this.centro = centro;
	}
	
	public Circulo (Ponto centro, double raio) {
		this.centro = centro;
		this.raio = raio;
	}

	protected ArrayList<int[]> calcularCirculo() {
		ArrayList<int[]> alCoordenadas = new ArrayList<>();
		int xCentro, yCentro;
		double seno, cosseno, angulo;
		int dX, dY;
		
		if (raio == 0) {
			//Raio --> Distância entre pontos --> Raiz Quadrada((x2 - x1) + (y2 - y1))
			raio = Math.sqrt( Math.pow( (perimetro.getX() - centro.getX()), 2 ) + Math.pow( (perimetro.getY() - centro.getY()), 2 ) );
		}
		
		//Valores de X e Y do ponto central transformados em int
		xCentro = new Double(centro.getX()).intValue();
		yCentro = new Double(centro.getY()).intValue();
		
		for (angulo = 0; angulo <= 45; angulo = angulo + 0.125) {
			
			seno = Math.sin( Math.toRadians(angulo));
			cosseno = Math.cos( Math.toRadians(angulo));
			
			dX = new Double( raio *  cosseno).intValue();
			dY = new Double( raio * seno).intValue();
			
			//Cálculo por octante dos pontos:
			//Ponto(x,y)
			alCoordenadas.add( new int [] {xCentro + dX, yCentro + dY} );
			
			//Ponto(-x, y)
			alCoordenadas.add( new int [] {xCentro - dX, yCentro + dY} );
			
			//Ponto(x, -y)
			alCoordenadas.add( new int [] {xCentro + dX, yCentro - dY} );
			
			//Ponto(-x, -y)
			alCoordenadas.add( new int [] {xCentro - dX, yCentro - dY} );
			
			//Ponto (y, x)
			alCoordenadas.add( new int [] {xCentro + dY, yCentro + dX} );
			
			//Ponto (-y, x)
			alCoordenadas.add( new int [] {xCentro - dY, yCentro + dX} );
			
			//Ponto (y, -x)
			alCoordenadas.add( new int [] {xCentro + dY, yCentro - dX} );
			
			//Ponto(-y, -x)
			alCoordenadas.add( new int [] {xCentro - dY, yCentro - dX} );
			
		}
		
		return alCoordenadas;
	}
	
	public double getRaio() {
		return raio;
	}

	public void setRaio(double raio) {
		this.raio = raio;
	}

	public void setCentro(Ponto p) {
		this.centro = p;
	}
	
	public void setPerimetro(Ponto p) {
		this.perimetro = p;
	}

	public Ponto getCentro() {
		return this.centro;
	}
	
	public Ponto getPerimetro() {
		return this.perimetro;
	}
}
