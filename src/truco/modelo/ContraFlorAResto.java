package truco.modelo;

import truco.excepciones.cantos.UnCantoARestoNoPuedeSaberElPuntajePorGanarSiNoConoceLosEquiposException;
import truco.excepciones.equipo.JugadorInexistenteException;

	public class ContraFlorAResto extends Canto {

		private Equipo equipo1;
		private Equipo equipo2;

		public ContraFlorAResto(Jugador jugadorQueCanta){
			super(jugadorQueCanta);
		}
		
		public ContraFlorAResto(Jugador jugadorQueCanta,Equipo equipo1,Equipo equipo2){
			this(jugadorQueCanta);
			this.equipo1 = equipo1;
			this.equipo2 = equipo2;
		}

		@Override
		public int puntosPorGanar(Jugador jugadorGanador) {
			return this.obtenerEquipoPerdedor(jugadorGanador).obtenerPuntosFaltantesParaGanar();
		}
		
		private Equipo obtenerEquipoPerdedor(Jugador jugadorGanador){
			if ( equipo1 == null || equipo2 == null)
				throw new UnCantoARestoNoPuedeSaberElPuntajePorGanarSiNoConoceLosEquiposException();
			if ( equipo1.estaJugador(jugadorGanador) ) return equipo2;
			if ( equipo2.estaJugador(jugadorGanador) ) return equipo1;	
			throw new JugadorInexistenteException();
		}
}
