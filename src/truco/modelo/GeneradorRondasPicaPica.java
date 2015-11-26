package truco.modelo;

import java.util.LinkedList;

public class GeneradorRondasPicaPica implements GeneradorRondas {

	private boolean seTieneQueJugarPicaAPica;

	public GeneradorRondasPicaPica(){
		/* Se inicia el valor en true para forzar la verificacion
		 * de los puntajes del equipo	*/
		this.seTieneQueJugarPicaAPica = true;
	}
	
	@Override
	public LinkedList<Ronda> generar(Equipo equipo1, Equipo equipo2,
			LinkedList<Jugador> jugadoresEnJuego) {
		
		if (this.seTieneQueJugarPicaAPica && 
				this.seCumplenLasCondicionesParaPoderJugarUnaRondaPicaAPica(equipo1, equipo2) )
			return this.generardorPicaAPica(equipo1, equipo2, jugadoresEnJuego);
		return this.generardorNormal(equipo1, equipo2, jugadoresEnJuego);
	}
	
	private LinkedList<Ronda> generardorPicaAPica(Equipo equipo1, Equipo equipo2,
			LinkedList<Jugador> jugadoresEnJuego){
		
		LinkedList<Ronda> listadoRetorno = new LinkedList<Ronda>();
		/*
		Jugador 1 -> Es la posicion 0 en el listado de Jugadores
		Jugador 2 -> Es la posicion 1 en el listado de Jugadores
		Jugador 3 -> Es la posicion 2 en el listado de Jugadores
		Jugador 4 -> Es la posicion 3 en el listado de Jugadores
		Jugador 5 -> Es la posicion 4 en el listado de Jugadores
		Jugador 6 -> Es la posicion 5 en el listado de Jugadores
		*/
		
		for (int numeroRonda = 1 ; numeroRonda <= 3 ; numeroRonda ++){
			LinkedList<Jugador> jugadoresDeLaRonda = new LinkedList<Jugador>();
			jugadoresDeLaRonda.add( jugadoresEnJuego.get(numeroRonda - 1) );
			jugadoresDeLaRonda.add( jugadoresEnJuego.get(numeroRonda + 2) );
			
			listadoRetorno.add( new Ronda(equipo1,equipo2,jugadoresDeLaRonda) );
		}
		
		return listadoRetorno;
	}
	
	private LinkedList<Ronda> generardorNormal(Equipo equipo1, Equipo equipo2,
			LinkedList<Jugador> jugadoresEnJuego){
		
		GeneradorRondasNormales generadorAuxiliar = new GeneradorRondasNormales();
		return generadorAuxiliar.generar(equipo1, equipo2, jugadoresEnJuego);
	}
	
	private boolean seCumplenLasCondicionesParaPoderJugarUnaRondaPicaAPica(Equipo equipo1, Equipo equipo2){
		if (equipo1.obtenerCantidadDePuntos() >= 5 || equipo2.obtenerCantidadDePuntos() >= 5)
			if (equipo1.obtenerCantidadDePuntos() < 25 && equipo2.obtenerCantidadDePuntos() < 25 )
				return true;
		return false;
	}

}
