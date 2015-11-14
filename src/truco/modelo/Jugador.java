package truco.modelo;

import java.util.ArrayList;
import java.util.List;

import truco.excepciones.jugador.ElJugadorNoTieneFlor;
import truco.excepciones.jugador.CartaEnManoInexistente;
import truco.excepciones.jugador.JugadorNoPuedeTenerMasDeTresCartasEnMano;;

public class Jugador {
	
	/*************************************************
	 ** 				Atributos					**
	 *************************************************/
	private ArrayList<Carta> manoDelJugador;
	
	/*************************************************
	 ** 			   Constructores				**
	 *************************************************/
	public Jugador(){
		this.manoDelJugador = new ArrayList<Carta>();
	}
	
	/*************************************************
	 ** 			  Metodos Privados				**
	 *************************************************/
	
	private int calcularEnvido(Carta unaCarta, Carta otraCarta) {
		if (! unaCarta.mismoPalo(otraCarta) )
			return 0;
		return 20 + this.valorCartaParaElTanto(unaCarta) + this.valorCartaParaElTanto(otraCarta);
	}
	
	private int valorCartaParaElTanto(Carta unaCarta){
		if (unaCarta.getValor()>= 10 && unaCarta.getValor() <= 12)
			return 0;
		return unaCarta.getValor();
	}
	
	/*************************************************
	 ** 			  Metodos Publicos				**
	 *************************************************/
	
	public int cantidadDeCartas() {
		return this.manoDelJugador.size();
	}
	
	public List<Carta> devolverCartas() {
		List<Carta> listadoRetorno = new ArrayList<Carta>();
		listadoRetorno.addAll(this.manoDelJugador);
		this.manoDelJugador.removeAll(this.manoDelJugador);
		return listadoRetorno;
	}
	
	public int puntajeEnvido() {
		/*	Este metodo debera ser refactorizado, en especial para tener en cuenta
		 * 	el caso donde el jugador halla jugado una de sus cartas en la mesa y 
		 * 	para calcular el envido tenga que usar dicha carta.	*/
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

	public int puntajeFlor() {
		/*	Posiblemente necesite refactoring cuando jugador juegue cartas a la mesa */
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
	
	public Carta tirarCarta(Carta carta){
		for (Carta otraCarta : this.manoDelJugador){
			if(otraCarta.equals(carta)){
				this.manoDelJugador.remove(otraCarta);
				return otraCarta;
			}
		}
		throw new CartaEnManoInexistente();
	}
	
	public void tomarCarta(Carta unaCarta) {
		if(this.manoDelJugador.size() >= 3){
			throw new JugadorNoPuedeTenerMasDeTresCartasEnMano();
		}
		this.manoDelJugador.add(unaCarta);
	}

	/*************************************************
	 ** 		 	  Fin de la Clase				**
	 *************************************************/
}
