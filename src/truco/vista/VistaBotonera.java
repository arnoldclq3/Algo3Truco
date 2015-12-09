package truco.vista;

import java.util.HashMap;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import truco.controlador.*;
import truco.modelo.*;

public class VistaBotonera implements Observer{

	private HBox botoneraTruco;
	private HBox botoneraEnvido;
	private HBox botoneraFlor;
	private HBox botoneraAcciones;
	private HBox botoneraTantos;
	
	private HashMap<Canto, Button> mapaBotonesCantos;
	private VBox botonera;
	private Jugador jugador;
	
	
	/*************************************************
	 ** 				Constructor					**
	 *************************************************/

	public VistaBotonera(Jugador jugador){
		this.jugador = jugador;
		this.inciarBotoneras();
		this.iniciarCajaVertical();
		this.iniciarBotoneraTruco();
		this.iniciarBotoneraEnvido();
		this.iniciarBotoneraFlor();
		this.iniciarBotoneraAcciones();
		this.iniciarBotoneraCantosDelTanto();
		this.iniciarVinculacionesDeBotones();
		this.estadoInicialDeBotonera();
	}

	private Button iniciarBotonDeBotonera(String nombre, EventHandler<ActionEvent> eventoPorMouse){
		Button botonARetornar = new Button(nombre);
		botonARetornar.setOnAction(eventoPorMouse);
		botonARetornar.setMinSize(150, 20);
		botonARetornar.setMaxSize(150, 40);
		return botonARetornar;
	}
	
	private HBox iniciarCajaHorizontal(){
		HBox cajaARetornar = new HBox();
		cajaARetornar.setAlignment(Pos.CENTER);
		cajaARetornar.setMaxSize(480, 40);
		cajaARetornar.setSpacing(2);
		return cajaARetornar;
	}
	
	private void inciarBotoneras() {
		this.botoneraTruco = this.iniciarCajaHorizontal();
		this.botoneraEnvido = this.iniciarCajaHorizontal();
		this.botoneraFlor = this.iniciarCajaHorizontal();
		this.botoneraAcciones = this.iniciarCajaHorizontal();
		this.botoneraTantos = this.iniciarCajaHorizontal();
	}
	
	private void iniciarCajaVertical(){
		this.botonera = new VBox();
		this.botonera.getChildren().add(this.botoneraTruco);
		this.botonera.getChildren().add(this.botoneraEnvido);
		this.botonera.getChildren().add(this.botoneraFlor);
		this.botonera.getChildren().add(this.botoneraAcciones);
		this.botonera.getChildren().add(this.botoneraTantos);
	}
	
	private void iniciarBotoneraTruco() {
		this.botoneraTruco.getChildren().add( this.iniciarBotonDeBotonera("Truco" , new ControladorTruco(jugador) ) );
		this.botoneraTruco.getChildren().add( this.iniciarBotonDeBotonera("Retruco" , new ControladorRetruco(jugador) ) );
		this.botoneraTruco.getChildren().add( this.iniciarBotonDeBotonera("Vale Cuatro" , new ControladorValeCuatro(jugador) ) );
	}
	
	private void iniciarBotoneraEnvido() {
		this.botoneraEnvido.getChildren().add( this.iniciarBotonDeBotonera("Envido" , new ControladorEnvido(jugador) ) );
		this.botoneraEnvido.getChildren().add( this.iniciarBotonDeBotonera("Real Envido" , new ControladorRealEnvido(jugador) ) );
		this.botoneraEnvido.getChildren().add( this.iniciarBotonDeBotonera("Falta Envido" , new ControladorFaltaEnvido(jugador) ) );
	}
	
	private void iniciarBotoneraFlor() {
		this.botoneraFlor.getChildren().add( this.iniciarBotonDeBotonera("Flor" , new ControladorFlor(jugador) ) );
		this.botoneraFlor.getChildren().add( this.iniciarBotonDeBotonera("Contra Flor" , new ControladorContraFlor(jugador) ) );
		this.botoneraFlor.getChildren().add( this.iniciarBotonDeBotonera("Contra Flor a Resto" , new ControladorContraFlorAResto(jugador) ) );
	}
	
	private void iniciarBotoneraAcciones() {
		this.botoneraAcciones.getChildren().add( this.iniciarBotonDeBotonera("Quiero" , new ControladorQuiero(jugador) ) );
		this.botoneraAcciones.getChildren().add( this.iniciarBotonDeBotonera("No Quiero" , new ControladorNoQuiero(jugador) ) );
		this.botoneraAcciones.getChildren().add( this.iniciarBotonDeBotonera("Me voy al mazo" , new ControladorMeVoyAlMazo(jugador) ) );
	}
	
	private void iniciarBotoneraCantosDelTanto() {
		this.botoneraTantos.getChildren().add( this.iniciarBotonDeBotonera("Cantar Tanto Envido" , new ControladorCantarTantoDelEnvido(jugador) ) );
		this.botoneraTantos.getChildren().add( this.iniciarBotonDeBotonera("Cantar Tanto Flor" , new ControladorCantarTantoDeLaFlor(jugador) ) );
		this.botoneraTantos.getChildren().add( this.iniciarBotonDeBotonera("Son Buenas" , new ControladorSonBuenas(jugador) ) );	
	}
	
	private void iniciarVinculacionesDeBotones(){
		this.mapaBotonesCantos = new HashMap<Canto,Button>();
		this.mapaBotonesCantos.put( new Envido(null) , (Button) this.botoneraEnvido.getChildren().get(0) );
		this.mapaBotonesCantos.put( new RealEnvido(null) , (Button) this.botoneraEnvido.getChildren().get(1) );
		this.mapaBotonesCantos.put( new FaltaEnvido(null) , (Button) this.botoneraEnvido.getChildren().get(2) );
		this.mapaBotonesCantos.put( new Truco(null) , (Button) this.botoneraTruco.getChildren().get(0) );
		this.mapaBotonesCantos.put( new Retruco(null) , (Button) this.botoneraTruco.getChildren().get(1) );
		this.mapaBotonesCantos.put( new ValeCuatro(null) , (Button) this.botoneraTruco.getChildren().get(2) );
		this.mapaBotonesCantos.put( new Flor(null) , (Button) this.botoneraFlor.getChildren().get(0) );
		this.mapaBotonesCantos.put( new ContraFlor(null) , (Button) this.botoneraFlor.getChildren().get(1) );
		this.mapaBotonesCantos.put( new ContraFlorAResto(null) , (Button) this.botoneraFlor.getChildren().get(2) );
	}
	
	private void estadoInicialDeBotonera(){
		this.botoneraTruco.getChildren().get(1).setDisable(true);
		this.botoneraTruco.getChildren().get(2).setDisable(true);
		
		
		this.estadoIncialBotonerasDelTanto();
	}
	
	private void estadoIncialBotonerasDelTanto(){
		this.botoneraEnvido.setDisable(false);
		this.botoneraFlor.getChildren().get(1).setDisable(true);
		this.botoneraFlor.getChildren().get(2).setDisable(true);
		
		if ( this.jugador.tieneFlor() )
			this.botoneraFlor.getChildren().get(0).setDisable(false);
		
		this.botoneraTantos.setDisable(true);
		
		this.botoneraAcciones.getChildren().get(0).setDisable(true);
		this.botoneraAcciones.getChildren().get(1).setDisable(true);
	}
	

	
	/*************************************************
	 ** 				Observer					**
	 *************************************************/
	@Override
	public void update(Observable objetoObservable, Object argumento) {
		this.botoneraFlor.setVisible( this.jugador.getMesa().seJuegaConFlor() );
		Ronda rondaActual = this.jugador.getMesa().getRondaActual();
		// En caso de que el jugador no sea el jugador que debe jugar no actualizo la botonera
		if (rondaActual == null || !this.jugador.equals( rondaActual.getJugadorQueDebeJugar() )  )
			return;
		
		this.deshabilitarBotones();
		if ( rondaActual.getManos().size() > 1 )
			this.actualizarBotoneraDeTruco(rondaActual);
		this.procesoActualizacionBotoneraParaPrimeraRonda(rondaActual);
	}

	private void deshabilitarBotones() {
		this.botonera.setDisable(true);
	}
	
	private void actualizarBotoneraDeTruco(Ronda rondaActual) {
		CantosEnProcesoParaElTruco cantoTruco = rondaActual.getCantoEnProcesoDelTruco();
		if ( cantoTruco.getCantosRealizados().isEmpty() )
			this.botoneraTruco.getChildren().get(0).setDisable(false);
		else{
			Canto ultimoCanto = cantoTruco.getCantosRealizados().get( cantoTruco.getCantosRealizados().size() - 1 );
			this.habilitarCantosDeRespuestaParaElCanto(ultimoCanto);
		}
		
	}
	
	private void habilitarCantosDeRespuestaParaElCanto(Canto ultimoCanto) {
		List<Canto> cantosRespuestas = ultimoCanto.cantosValidosDeRespuesta();
		for (Canto unCanto : cantosRespuestas)
			this.mapaBotonesCantos.get(unCanto).setDisable(false);
	}
	
	
	private void procesoActualizacionBotoneraParaPrimeraRonda(Ronda rondaActual) {
		// Casos de la primera ronda
		CantoEnProcesoParaElTanto cantoTanto = rondaActual.getCantoEnProcesoDelTanto();
		// Caso donde no se canto el tanto
		if ( cantoTanto == null ){
			this.estadoIncialBotonerasDelTanto();
			this.actualizarBotoneraDeTruco(rondaActual);
			return;
		}
		// Caso donde el canto ya se termino de cantar
		if ( rondaActual.terminoElProcesoDeCantoDelTanto() ){
			this.actualizarBotoneraDeTruco(rondaActual);
			return;
		}
		// Caso donde se este cantando el tanto
		if ( ! cantoTanto.sePuedenRealizarOtrosCantos() ){
			this.actualizarBotoneraDeTantos(cantoTanto);
			return;
		}
		// El ultimo caso seria la posibilidad de un canto de envido en espera de respuesta
		Canto ultimoCanto = cantoTanto.getCantosRealizados().get( cantoTanto.getCantosRealizados().size() - 1 );
		this.habilitarCantosDeRespuestaParaElCanto(ultimoCanto);			
	}

	private void actualizarBotoneraDeTantos(CantoEnProcesoParaElTanto cantoTanto) {
		if ( cantoTanto.seCantoFlor() )
			this.botoneraTantos.getChildren().get(1).setDisable(false);
		else
			this.botoneraTantos.getChildren().get(0).setDisable(false);
		this.botoneraTantos.getChildren().get(2).setDisable(false);
	}

	

	/*************************************************
	 ** 		    Metodos Publicos				**
	 *************************************************/
	
	public Node obtenerVista(){
		return botonera;
	}
	
	
}
