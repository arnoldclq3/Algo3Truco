package truco.modelo;

public class Truco extends Canto {

	public Truco(){
		this.cantosValidosDeRespuesta.add( new Retruco() );
	}
	
	public int puntosPorGanar() {
		return 2;
	}
}
