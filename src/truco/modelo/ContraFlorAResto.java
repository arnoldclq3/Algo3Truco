package truco.modelo;

import truco.excepciones.cantos.PuntosQueLeFaltanAlOtroEquipoParaGanarException;

public class ContraFlorAResto extends Canto {

	public ContraFlorAResto() {
		super(null);
	}

	@Override
	public int puntosPorGanar() {
		throw new PuntosQueLeFaltanAlOtroEquipoParaGanarException();
	}

}
