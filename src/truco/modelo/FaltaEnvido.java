package truco.modelo;

import truco.excepciones.cantos.PuntosQueLeFaltanAlOtroEquipoParaGanarException;

public class FaltaEnvido extends Canto {

	public FaltaEnvido(Jugador jugadorQueCanta){
		super(jugadorQueCanta);
	}

	@Override
	public int puntosPorGanar() {
		throw new PuntosQueLeFaltanAlOtroEquipoParaGanarException();
	}

}
