package truco.modelo;

public class Retruco extends Canto {

	public Retruco(){
		this.cantosValidosDeRespuesta.add( new ValeCuatro() );
	}
	
	
	public int puntosPorGanar() {	
		return 3;
	}
}
