package truco.modelo;

public class Truco extends Canto {

	public Truco(Jugador jugadorQueCanta){
		super(jugadorQueCanta);
		this.cantosValidosDeRespuesta.add( new Retruco(null) );
	}
	
	public int puntosPorGanar(Jugador jugadorGanador) {
		return 2;
	}
}
