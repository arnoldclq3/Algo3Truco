package truco.modelo;

public class ValeCuatro extends Canto {

	public ValeCuatro(Jugador jugadorQueCanta){
		super(jugadorQueCanta);
	}

	public int puntosPorGanar(Jugador jugadorGanador) {
		
		return 4;
	}
}
