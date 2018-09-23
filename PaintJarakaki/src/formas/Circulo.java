package formas;

import java.util.ArrayList;

public class Circulo {
	
	Ponto p1, p2;
	double dRaio;
	
	public Circulo(Ponto p1, Ponto p2) {
		this.p1 = p1;
		this.p2 = p2;
		
		//Raio --> Distância entre pontos --> Raiz Quadrada((x2 - x1) + (y2 - y1))
		dRaio = Math.sqrt( Math.pow( (p2.getX() - p1.getX()), 2 ) + Math.pow( (p2.getY() - p1.getY()), 2 ) );
	}
	
	protected ArrayList<int[]> calcularCirculo() {
		ArrayList<int[]> alCoordenadas = new ArrayList<>();
		int iCentroX, iCentroY;
		double dSeno, dCosseno, iAngulo;
		int dX, dY;
		
		//Valores de X e Y do ponto central transformados em int
		iCentroX = new Double(p1.getX()).intValue();
		iCentroY = new Double(p1.getY()).intValue();
		
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
}
