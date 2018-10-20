package formas;

import java.util.ArrayList;

public class Circulo implements Formas{
	
	protected Ponto centro, perimetro;
	double raio;

	protected ArrayList<int[]> calcularCirculo() {
		ArrayList<int[]> alCoordenadas = new ArrayList<>();
		int iCentroX, iCentroY;
		double dSeno, dCosseno, iAngulo;
		int dX, dY;
		
		//Raio --> Distância entre pontos --> Raiz Quadrada((x2 - x1) + (y2 - y1))
		raio = Math.sqrt( Math.pow( (perimetro.getX() - centro.getX()), 2 ) + Math.pow( (perimetro.getY() - centro.getY()), 2 ) );
		
		//Valores de X e Y do ponto central transformados em int
		iCentroX = new Double(centro.getX()).intValue();
		iCentroY = new Double(centro.getY()).intValue();
		
		for (iAngulo = 0; iAngulo <= 45; iAngulo = iAngulo + 0.125) {
			
			dSeno = Math.sin( Math.toRadians(iAngulo));
			dCosseno = Math.cos( Math.toRadians(iAngulo));
			
			dX = new Double( raio *  dCosseno).intValue();
			dY = new Double( raio * dSeno).intValue();
			
			//Cálculo por octante dos pontos:
			//Ponto(x,y)
			alCoordenadas.add( new int [] {iCentroX + dX, iCentroY + dY} );
			
			//Ponto(-x, y)
			alCoordenadas.add( new int [] {iCentroX - dX, iCentroY + dY} );
			
			//Ponto(x, -y)
			alCoordenadas.add( new int [] {iCentroX + dX, iCentroY - dY} );
			
			//Ponto(-x, -y)
			alCoordenadas.add( new int [] {iCentroX - dX, iCentroY - dY} );
			
			//Ponto (y, x)
			alCoordenadas.add( new int [] {iCentroX + dY, iCentroY + dX} );
			
			//Ponto (-y, x)
			alCoordenadas.add( new int [] {iCentroX - dY, iCentroY + dX} );
			
			//Ponto (y, -x)
			alCoordenadas.add( new int [] {iCentroX + dY, iCentroY - dX} );
			
			//Ponto(-y, -x)
			alCoordenadas.add( new int [] {iCentroX - dY, iCentroY - dX} );
			
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
