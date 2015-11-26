package truco.modelo;

import java.util.LinkedList;

public interface GeneradorRondas {
	LinkedList<Ronda> generar(Equipo equipo1, Equipo equipo2, LinkedList<Jugador> jugadoresEnJuego);
}
