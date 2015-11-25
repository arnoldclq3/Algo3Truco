package truco.modelo;

public class ContraFlor extends Canto {

	public ContraFlor(Jugador jugadorQueCanta) {
		super(null);
	}

	@Override
	public int puntosPorGanar(Jugador jugadorGanador) {
		return 6;
	}

}
