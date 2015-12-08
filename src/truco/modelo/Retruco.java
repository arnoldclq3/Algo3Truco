package truco.modelo;

public class Retruco extends Canto {

	public Retruco(Jugador jugadorQueCanta){
		super(jugadorQueCanta);
		this.cantosValidosDeRespuesta.add( new ValeCuatro(null) );
	}
	
	
	public int puntosPorGanar(Jugador jugadorGanador) {	
		return 3;
	}
}
