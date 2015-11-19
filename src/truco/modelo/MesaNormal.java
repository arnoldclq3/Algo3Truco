package truco.modelo;

import java.util.Collections;

public class MesaNormal extends Mesa {
	/**
	 * La mesa normal cumplira el rol que hace la mesa en este momento.
	 * Cuando se configure todo lo de mesa pica a pica y se ponga mesa como 
	 * clase abstracta, se debera instanciar objetos de mesa normal para poder utilizar
	 * mesa como se usaba antes de la mesa pica a pica
	 * @author sebas
	 *
	 */	

	public MesaNormal(Equipo nosotros, Equipo ellos, boolean seJuegaConFlor) {
		super(nosotros, ellos, seJuegaConFlor);
	}

	@Override 
	protected void generadorDeNuevaRonda() {
		super.retirarCartasDeLaRonda();
		Collections.rotate(this.ordenJugadores, -1);
		super.ronda = new Ronda(nosotros, ellos,ordenJugadores);
	}
}
