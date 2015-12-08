package truco.modelo;

import java.util.List;
import java.util.LinkedList;

public class GeneradorRondasNormales implements GeneradorRondas{

	@Override
	public List<Ronda> generar(Equipo equipo1, Equipo equipo2,
			List<Jugador> jugadoresEnJuego) {
		
		List<Ronda> listadoRetorno = new LinkedList<Ronda>();

		listadoRetorno.add(  new Ronda(equipo1,equipo2,jugadoresEnJuego) );
		
		return listadoRetorno;
	}

}
