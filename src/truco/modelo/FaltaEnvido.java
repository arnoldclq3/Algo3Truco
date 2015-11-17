package truco.modelo;

import truco.excepciones.canto.PuntosQueLeFaltanAlOtroEquipoParaGanarException;

public class FaltaEnvido extends Canto {

	@Override
	public int puntosPorGanar() {
		throw new PuntosQueLeFaltanAlOtroEquipoParaGanarException();
	}

}
