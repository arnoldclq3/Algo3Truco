package truco.modelo;

import java.util.Collections;
/**
 * La Mesa pica a pica se va a utlizar para el truco de 6.
 * Faltan agregar un par de verificaciones y realizar los test correspondientes
 * En este momento, se deja mesa como clase normal, y para poder utilizar Mesa pica a pica se
 * tiene que hacer el siguiente listado de puntos:
 * 1º) Poner en la clase Mesa como clase Abstracta
 * 2º) Terminar el proceso abajo para la nuevas rondas
 * @author sebas
 *
 */
public class MesaPicaPica extends Mesa {

	private int numeroDeRondaPicaAPica;
	private boolean seEstaJugandoPicaAPica = false;
	private boolean rondaSinPicaPica;
	private Equipo equipoNosotrosTemporal;
	private Equipo equipoEllosTemporal;
	private MesaNormal mesaTemporal;

	public MesaPicaPica(Equipo nosotros, Equipo ellos, boolean seJuegaConFlor) {
		super(nosotros, ellos, seJuegaConFlor);
	}

	@Override
	protected void generadorDeNuevaRonda() {
		if (this.seEstaJugandoPicaAPica)
			this.generarNuevaRondaPicaAPica();
		
		super.retirarCartasDeLaRonda();	
		Collections.rotate(this.ordenJugadores, -1);
		
		if (!this.seTieneQueJugarPicaAPica() && !this.rondaSinPicaPica )
			this.generarNuevaRondaNormal();
		else{
			this.generarNuevaRondaPicaAPica();
			this.numeroDeRondaPicaAPica = 1;
			this.seEstaJugandoPicaAPica = true;	
		}
	}

	private boolean seTieneQueJugarPicaAPica() {
		if ( nosotros.obtenerCantidadDePuntos() >= 5 || ellos.obtenerCantidadDePuntos() >= 5 )
			if ( nosotros.obtenerPuntosFaltantesParaGanar() > 5 && ellos.obtenerPuntosFaltantesParaGanar() > 5)
				return true;
		return false;
	}
	
	private void generarNuevaRondaNormal() {
		super.ronda = new Ronda(nosotros, ellos,ordenJugadores);
		this.rondaSinPicaPica = false;
	}
	
	private void generarNuevaRondaPicaAPica() {
		if (this.numeroDeRondaPicaAPica > 1)
			this.sumarPuntosDeEquiposTemporalesAEquiposDefinitivos();
		while (this.numeroDeRondaPicaAPica <= 3){
			this.asignarEquiposYJugadoresNuevaMesaTemporal();
			this.numeroDeRondaPicaAPica ++;	
		}
		if (this.numeroDeRondaPicaAPica == 4){
			this.seEstaJugandoPicaAPica = false;
			this.rondaSinPicaPica = true;
			// Falta sumar los puntos 
		}
	}
	
	private void asignarEquiposYJugadoresNuevaMesaTemporal(){
		this.equipoNosotrosTemporal = new Equipo();
		this.equipoEllosTemporal = new Equipo();
		this.mesaTemporal = new MesaNormal(equipoNosotrosTemporal,equipoEllosTemporal,super.seJuegaConFlor);
		Jugador jugadorUno = this.ordenJugadores.get(this.numeroDeRondaPicaAPica - 1);
		jugadorUno.setMesa(this.mesaTemporal);
		Jugador jugadorDos = this.ordenJugadores.get(this.numeroDeRondaPicaAPica - 1 + 3);
		jugadorDos.setMesa(this.mesaTemporal);
		if (this.nosotros.estaJugador(jugadorUno) ){
			this.equipoNosotrosTemporal.agregarJugador(jugadorUno);
			this.equipoEllosTemporal.agregarJugador(jugadorDos);
		}
		else{
			this.equipoNosotrosTemporal.agregarJugador(jugadorDos);
			this.equipoEllosTemporal.agregarJugador(jugadorUno);
		}
	}
	
	private void sumarPuntosDeEquiposTemporalesAEquiposDefinitivos() {
		super.nosotros.sumarPuntos(this.equipoNosotrosTemporal.obtenerCantidadDePuntos() );
		super.ellos.sumarPuntos(this.equipoEllosTemporal.obtenerCantidadDePuntos() );
		super.verificarSiExisteUnEquipoGanador();
	}


	

	

}
