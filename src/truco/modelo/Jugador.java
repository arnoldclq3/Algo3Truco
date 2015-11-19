package truco.modelo;

import java.util.ArrayList;
import java.util.List;

import truco.excepciones.jugador.CartaEnManoInexistenteException;
import truco.excepciones.jugador.ElJugadorNoTieneFlorException;
import truco.excepciones.jugador.JugadorNoPuedeTenerMasDeTresCartasEnManoException;

public class Jugador {
	
	/*************************************************
	 ** 				Atributos					**
	 *************************************************/
	
	private ArrayList<Carta> manoDelJugador;
	private String nombre;
	private Mesa mesaEnLaQueEstoyJugando;
	private static int numeroJugador = 0;
	
	
	/*************************************************
	 ** 			   Constructores				
	 * @param nombrEJugador **
	 *************************************************/
	
	public Jugador(){
		this.manoDelJugador = new ArrayList<Carta>();
		this.nombre = "Jugador "+ Jugador.numeroJugador;
		Jugador.numeroJugador++;
	}
	
	public Jugador(String nombreJugador){
		this.nombre = nombreJugador;
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
		
		ArrayList<Carta> misCartas = new ArrayList<Carta>();
		this.agregarCartasJugadas(misCartas);
		misCartas.addAll(this.manoDelJugador);
		
		if (misCartas.size() != 3)
			return 0;
		
		int puntajeARetornar = this.calcularEnvido( misCartas.get(0) , misCartas.get(2) );
		
		for (int i = 0 ; i <= 1 ; i++){
			int puntaje = this.calcularEnvido( misCartas.get(i) , misCartas.get(i+1) );
			if (puntaje > puntajeARetornar)
				puntajeARetornar = puntaje;
		}
		
		return puntajeARetornar;
	}

	public int puntajeFlor() {
		
		if (! this.tieneFlor() )
			throw new ElJugadorNoTieneFlorException();
		
		ArrayList<Carta> misCartas = new ArrayList<Carta>();
		this.agregarCartasJugadas(misCartas);
		misCartas.addAll(this.manoDelJugador);
		
		if (misCartas.size() != 3)
			return 0;
		
		int valorRetorno = 20;
		for (Carta unaCarta : misCartas){
			valorRetorno = valorRetorno + this.valorCartaParaElTanto(unaCarta);
		}
		return valorRetorno;
	}
	
	private void agregarCartasJugadas(ArrayList<Carta> listadoEnDondeAgregarLasCartas){
		if (this.mesaEnLaQueEstoyJugando == null)
			return;
		ArrayList<Carta> cartasJugadas = this.mesaEnLaQueEstoyJugando.mostrarCartasDelJugador(this);
		listadoEnDondeAgregarLasCartas.addAll( cartasJugadas );
	}

	public boolean tieneFlor() {
		Carta primeraCarta = this.manoDelJugador.get(0);
		for (Carta otraCarta : this.manoDelJugador){
			if ( otraCarta != primeraCarta && ! otraCarta.mismoPalo(primeraCarta) )
				return false;		
		}
		return true;
	}

	public Carta tirarCarta(Carta carta) { 
		for (Carta otraCarta : this.manoDelJugador){
			if(otraCarta.equals(carta)){
				this.manoDelJugador.remove(otraCarta);
				return otraCarta;
			}
		}
		throw new CartaEnManoInexistenteException();
	}
	
	public void tomarCarta(Carta unaCarta) {
		if(this.manoDelJugador.size() >= 3){
			throw new JugadorNoPuedeTenerMasDeTresCartasEnManoException();
		}
		this.manoDelJugador.add(unaCarta);
	}

	public String getNombre() {
		return this.nombre;
	}
	
	@Override
	public boolean equals(Object unObjeto){
		
		if ( ! ( unObjeto  instanceof Jugador ) )
			return false;
			
		Jugador unJugador = (Jugador)unObjeto;
		return ( this.nombre == unJugador.nombre);
		
	}

	public Mesa getMesa() {
		return mesaEnLaQueEstoyJugando;
	}

	public void setMesa(Mesa laMesaEnLaQueEstoyJugando) {
		this.mesaEnLaQueEstoyJugando = laMesaEnLaQueEstoyJugando;
	}

	/*************************************************
	 ** 		 	  Fin de la Clase				**
	 *************************************************/
}
