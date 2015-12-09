package truco.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import truco.excepciones.jugador.CartaEnManoInexistenteException;
import truco.excepciones.jugador.ElJugadorNoEstaEnNingunaMesaException;
import truco.excepciones.jugador.ElJugadorNoTieneFlorException;
import truco.excepciones.jugador.JugadorNoPuedeTenerMasDeTresCartasEnManoException;

public class Jugador extends Observable {
	
	/*************************************************
	 ** 				Atributos					**
	 *************************************************/
	
	private ArrayList<Carta> manoDelJugador;
	private String nombre;
	private Mesa mesaEnLaQueEstoyJugando;
	private static int numeroJugador = 1;
	
	
	/*************************************************
	 ** 			   Constructores				
	 * @param nombrEJugador **
	 *************************************************/
	
	public Jugador(){
		this.crearJugadorAutomatico();
	}
	
	public Jugador(String nombreJugador){
		
		if ( nombreJugador == null || nombreJugador.isEmpty() ) 
			this.crearJugadorAutomatico();
		else
			this.nombre = nombreJugador;
			this.manoDelJugador = new ArrayList<Carta>();
	}
	
	private void crearJugadorAutomatico() {
		
		this.manoDelJugador = new ArrayList<Carta>();
		this.nombre = "Jugador "+ Jugador.numeroJugador;
		Jugador.numeroJugador++;
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
	
	private void verificarQueEstaEnUnaMesa() {
		
		if ( this.mesaEnLaQueEstoyJugando == null ) throw new ElJugadorNoEstaEnNingunaMesaException();
	}
	
	private void notificarObservadores(){
		this.setChanged();
		this.notifyObservers();
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
		
		List<Carta> misCartas = new ArrayList<Carta>();
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
		
		List<Carta> misCartas = new ArrayList<Carta>();
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
	
	private void agregarCartasJugadas(List<Carta> listadoEnDondeAgregarLasCartas){
		if (this.mesaEnLaQueEstoyJugando == null)
			return;
		List<Carta> cartasJugadas = this.mesaEnLaQueEstoyJugando.mostrarCartasDelJugador(this);
		listadoEnDondeAgregarLasCartas.addAll( cartasJugadas );
	}

	public boolean tieneFlor() {
		if (this.manoDelJugador.isEmpty() )
			return false;
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
		
		if (this.manoDelJugador.size() == 3) this.notificarObservadores();
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
	
	@Override
	public int hashCode(){
		return this.nombre.hashCode();
	}

	public Mesa getMesa() {
		return mesaEnLaQueEstoyJugando;
	}

	public void setMesa(Mesa laMesaEnLaQueEstoyJugando) {
		this.mesaEnLaQueEstoyJugando = laMesaEnLaQueEstoyJugando;
	}
	
	public void jugarCarta(Carta unaCarta) {
		
		this.verificarQueEstaEnUnaMesa();
		this.mesaEnLaQueEstoyJugando.jugarCarta(this, this.tirarCarta(unaCarta));
		this.notificarObservadores();
	}
	
	public void irseAlMazo() {
		
		this.verificarQueEstaEnUnaMesa();
		this.mesaEnLaQueEstoyJugando.irseAlMazo(this);
		this.notificarObservadores();
	}
	
	public void quiero() {
	
		this.verificarQueEstaEnUnaMesa();
		this.mesaEnLaQueEstoyJugando.quiero(this);
		this.notificarObservadores();
	}
	
	public void noQuiero() {
		
		this.verificarQueEstaEnUnaMesa();
		this.mesaEnLaQueEstoyJugando.noQuiero(this);
		this.notificarObservadores();
	}
	
	public void envido() {
		
		this.verificarQueEstaEnUnaMesa();
		this.mesaEnLaQueEstoyJugando.envido(this);
		this.notificarObservadores();
	}
	
	public void realEnvido() {
		
		this.verificarQueEstaEnUnaMesa();
		this.mesaEnLaQueEstoyJugando.realEnvido(this);
		this.notificarObservadores();
	}
	
	public void faltaEnvido() {
		
		this.verificarQueEstaEnUnaMesa();
		this.mesaEnLaQueEstoyJugando.faltaEnvido(this);
		this.notificarObservadores();
	}
	
	public void cantarTantoDelEnvido() {
		
		this.verificarQueEstaEnUnaMesa();
		this.mesaEnLaQueEstoyJugando.cantarTantoDelEnvido(this);
		this.notificarObservadores();
	}
	
	public void sonBuenas() {
		
		this.verificarQueEstaEnUnaMesa();
		this.mesaEnLaQueEstoyJugando.sonBuenas(this);
		this.notificarObservadores();
	}
	
	public void flor() {
		
		this.verificarQueEstaEnUnaMesa();
		this.mesaEnLaQueEstoyJugando.flor(this);
		this.notificarObservadores();
	}
	
	public void contraFlor() {
		
		this.verificarQueEstaEnUnaMesa();
		this.mesaEnLaQueEstoyJugando.contraFlor(this);
		this.notificarObservadores();
	}
	
	public void contraFlorAResto() {
		
		this.verificarQueEstaEnUnaMesa();
		this.mesaEnLaQueEstoyJugando.contraFlorAResto(this);
		this.notificarObservadores();
	}
	
	public void cantarTantoDeLaFlor() {
		
		this.verificarQueEstaEnUnaMesa();
		this.mesaEnLaQueEstoyJugando.cantarTantoDeLaFlor(this);
		this.notificarObservadores();
	}
	
	public void truco() {
		
		this.verificarQueEstaEnUnaMesa();
		this.mesaEnLaQueEstoyJugando.truco(this);
		this.notificarObservadores();
	}
	
	public void retruco() {
		
		this.verificarQueEstaEnUnaMesa();
		this.mesaEnLaQueEstoyJugando.retruco(this);
		this.notificarObservadores();
	}
	
	public void valeCuatro() {
		
		this.verificarQueEstaEnUnaMesa();
		this.mesaEnLaQueEstoyJugando.valeCuatro(this);
		this.notificarObservadores();
	}
	
	
	/*************************************************
	 ** 		 		  GETTERS					**
	 *************************************************/

	public List<Carta> obtenerMano() {
		// TODO Auto-generated method stub
		return this.manoDelJugador;
	}

	/*************************************************
	 ** 		 	  Fin de la Clase				**
	 *************************************************/
}
