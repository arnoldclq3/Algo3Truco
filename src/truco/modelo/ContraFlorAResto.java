package truco.modelo;

import truco.excepciones.canto.PuntosQueLeFaltanAlOtroEquipoParaGanarException;

public class ContraFlorAResto extends Canto {

	@Override
	public int puntosPorGanar() {
		throw new PuntosQueLeFaltanAlOtroEquipoParaGanarException();
	}

}
