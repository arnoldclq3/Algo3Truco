package truco.modelo;

public class FaltaEnvido extends Canto {

	@Override
	public int puntosPorGanar() {
		// Se devuelve Cero. La realidad es que deberia devolverse una excepcion.
		// Falta envido no sabe que puntaje devolver si se gana.
		return 0;
	}

}
