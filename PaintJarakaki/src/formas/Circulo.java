package formas;

import java.util.ArrayList;

public class Circulo {
	
	protected Ponto centro, raio;
	double dRaio;

	protected ArrayList<int[]> calcularCirculo() {
		ArrayList<int[]> alCoordenadas = new ArrayList<>();
		int iCentroX, iCentroY;
		double dSeno, dCosseno, iAngulo;
		int dX, dY;
		
		//Raio --> Distância entre pontos --> Raiz Quadrada((x2 - x1) + (y2 - y1))
		dRaio = Math.sqrt( Math.pow( (raio.getX() - centro.getX()), 2 ) + Math.pow( (raio.getY() - centro.getY()), 2 ) );
		
		//Valores de X e Y do ponto central transformados em int
		iCentroX = new Double(centro.getX()).intValue();
		iCentroY = new Double(centro.getY()).intValue();
		
		for (iAngulo = 0; iAngulo <= 45; iAngulo = iAngulo + 0.125) {
			
			dSeno = Math.sin( Math.toRadians(iAngulo));
			dCosseno = Math.cos( Math.toRadians(iAngulo));
			
			dX = new Double( dRaio *  dCosseno).intValue();
			dY = new Double( dRaio * dSeno).intValue();
			
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
	
	public void setCentro(Ponto p) {
		this.centro = p;
	}
	
	public void setRaio(Ponto p) {
		this.raio = p;
	}

	public Ponto getCentro() {
		return this.centro;
	}
	
	public Ponto getRaio() {
		return this.raio;
	}
}
