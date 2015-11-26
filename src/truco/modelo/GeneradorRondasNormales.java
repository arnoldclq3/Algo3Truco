package truco.modelo;

import java.util.LinkedList;

public class GeneradorRondasNormales implements GeneradorRondas{

	@Override
	public LinkedList<Ronda> generar(Equipo equipo1, Equipo equipo2,
			LinkedList<Jugador> jugadoresEnJuego) {
		
		LinkedList<Ronda> listadoRetorno = new LinkedList<Ronda>();

		listadoRetorno.add(  new Ronda(equipo1,equipo2,jugadoresEnJuego) );
		
		return listadoRetorno;
	}

}
