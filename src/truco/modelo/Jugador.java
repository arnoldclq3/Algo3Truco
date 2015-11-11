package truco.modelo;

import java.util.ArrayList;
import java.util.List;

import truco.excepciones.jugador.ElJugadorNoTieneFlor;
import truco.excepciones.jugador.CartaEnManoInexistenteException;
import truco.excepciones.jugador.JugadorNoPuedeTenerMasDeTresCartasEnManoException;;

public class Jugador {
	

	private ArrayList<Carta> manoDelJugador;
	
	public Jugador(){
		this.manoDelJugador = new ArrayList<Carta>();
	}

	public void nuevaCarta(Carta unaCarta) {
		if(this.manoDelJugador.size() >= 3){
			throw new JugadorNoPuedeTenerMasDeTresCartasEnManoException();
		}
		this.manoDelJugador.add(unaCarta);
	}

	
	public int puntajeEnvido() {
		int puntajeARetornar = 0;
		for (Carta unaCarta : this.manoDelJugador )
			for (Carta otraCarta : this.manoDelJugador){
				if (unaCarta != otraCarta){
					int valorEnvido = this.calcularEnvido(unaCarta,otraCarta);	
					if (valorEnvido > puntajeARetornar)
						puntajeARetornar = valorEnvido;	
				}
			}
		return puntajeARetornar;
	}
	
	private int valorCartaParaElTanto(Carta unaCarta){
		if (unaCarta.getValor()>= 10 && unaCarta.getValor() <= 12)
			return 0;
		return unaCarta.getValor();
	}


	private int calcularEnvido(Carta unaCarta, Carta otraCarta) {
		if (! unaCarta.mismoPalo(otraCarta) )
			return 0;
		return 20 + this.valorCartaParaElTanto(unaCarta) + this.valorCartaParaElTanto(otraCarta);
	}

	public int puntajeFlor() {
		if (! this.tieneFlor() )
			throw new ElJugadorNoTieneFlor();
		int valorRetorno = 20;
		for (Carta unaCarta : this.manoDelJugador){
			valorRetorno = valorRetorno + this.valorCartaParaElTanto(unaCarta);
		}
		return valorRetorno;
	}

	public boolean tieneFlor() {
		Carta primeraCarta = this.manoDelJugador.get(0);
		for (Carta otraCarta : this.manoDelJugador){
			if ( otraCarta != primeraCarta && ! otraCarta.mismoPalo(primeraCarta) )
				return false;		
		}
		return true;
	}
	
	public List<Carta> obtenerCartasEnMano(){
		return this.manoDelJugador;
	}
	
	public Carta tirarCarta(Carta carta){
		for (Carta otraCarta : this.manoDelJugador){
			if(otraCarta.equals(carta)){
				this.manoDelJugador.remove(otraCarta);
				return otraCarta;
			}
		}
		throw new CartaEnManoInexistenteException();
	}
	
	

}
