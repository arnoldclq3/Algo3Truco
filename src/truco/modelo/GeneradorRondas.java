package truco.modelo;

import java.util.List;

public interface GeneradorRondas {
	List<Ronda> generar(Equipo equipo1, Equipo equipo2, List<Jugador> jugadoresEnJuego);
}
