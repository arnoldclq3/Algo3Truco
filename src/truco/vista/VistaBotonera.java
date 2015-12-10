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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import truco.controlador.*;
import truco.modelo.*;

public class VistaBotonera implements Observer{

	
	private HashMap<Canto, Button> mapaBotonesCantos;
	private VBox botonera;
	private Jugador jugador;
	private HashMap<String, Button> mapaDeBotonesPorNombre;
	private Ronda rondaActual;
	private HBox cajaPuntosTanto;
	
	
	/*************************************************
	 ** 				Constructor					**
	 *************************************************/

	public VistaBotonera(Jugador jugador){
		this.jugador = jugador;
		this.rondaActual = this.jugador.getMesa().getRondaActual();
		this.botonera = new VBox();
		this.mapaDeBotonesPorNombre = new HashMap<String,Button>();
		
		this.iniciarBotoneraTruco();
		this.iniciarBotoneraEnvido();
		this.iniciarBotoneraFlor();
		this.iniciarBotoneraAcciones();
		this.iniciarBotoneraCantosDelTanto();
		this.iniciarCajaDeResultadoDePuntosDeTanto();
		
		this.vincularBotonesDeCantosConObjetosCanto();
		this.estadoInicialDeBotonera();
		
		this.volverInvisibleLosBotonesDeLaFlorEnCasoDeQueNoSeJuegueConFlor();
	}

	// Text tituloPrincipa2 = new Text("Bienvenido \n a");
	private void volverInvisibleLosBotonesDeLaFlorEnCasoDeQueNoSeJuegueConFlor() {
		if ( !this.jugador.getMesa().seJuegaConFlor() ){
			this.mapaDeBotonesPorNombre.get("Flor").setVisible(false);
			this.mapaDeBotonesPorNombre.get("Contra Flor").setVisible(false);
			this.mapaDeBotonesPorNombre.get("Contra Flor a Resto").setVisible(false);
		}
		
	}

	private Button iniciarBotonDeBotonera(String nombre, EventHandler<ActionEvent> eventoPorMouse){
		Button botonARetornar = new Button(nombre);
		botonARetornar.setOnAction(eventoPorMouse);
		botonARetornar.setMinSize(180, 20);
		botonARetornar.setMaxSize(180, 40);
		this.mapaDeBotonesPorNombre.put(nombre, botonARetornar);
		return botonARetornar;
	}
	
	private HBox iniciarCajaHorizontal(){
		HBox cajaARetornar = new HBox();
		cajaARetornar.setAlignment(Pos.CENTER);
		cajaARetornar.setMaxSize(550, 40);
		cajaARetornar.setSpacing(2);
		return cajaARetornar;
	}
	
	
	private void iniciarBotoneraTruco() {
		HBox botoneraTruco = this.iniciarCajaHorizontal();
		botoneraTruco.getChildren().add( this.iniciarBotonDeBotonera("Truco" , new ControladorTruco(jugador) ) );
		botoneraTruco.getChildren().add( this.iniciarBotonDeBotonera("Retruco" , new ControladorRetruco(jugador) ) );
		botoneraTruco.getChildren().add( this.iniciarBotonDeBotonera("Vale Cuatro" , new ControladorValeCuatro(jugador) ) );
		botonera.getChildren().add(botoneraTruco);
	}
	
	private void iniciarBotoneraEnvido() {
		HBox botoneraEnvido = this.iniciarCajaHorizontal();
		botoneraEnvido.getChildren().add( this.iniciarBotonDeBotonera("Envido" , new ControladorEnvido(jugador) ) );
		botoneraEnvido.getChildren().add( this.iniciarBotonDeBotonera("Real Envido" , new ControladorRealEnvido(jugador) ) );
		botoneraEnvido.getChildren().add( this.iniciarBotonDeBotonera("Falta Envido" , new ControladorFaltaEnvido(jugador) ) );
		botonera.getChildren().add(botoneraEnvido);
	}
	
	private void iniciarBotoneraFlor() {
		HBox botoneraFlor = this.iniciarCajaHorizontal();
		botoneraFlor.getChildren().add( this.iniciarBotonDeBotonera("Flor" , new ControladorFlor(jugador) ) );
		botoneraFlor.getChildren().add( this.iniciarBotonDeBotonera("Contra Flor" , new ControladorContraFlor(jugador) ) );
		botoneraFlor.getChildren().add( this.iniciarBotonDeBotonera("Contra Flor a Resto" , new ControladorContraFlorAResto(jugador) ) );
		botonera.getChildren().add(botoneraFlor);
	}
	
	private void iniciarBotoneraAcciones() {
		HBox botoneraAcciones = this.iniciarCajaHorizontal();
		botoneraAcciones.getChildren().add( this.iniciarBotonDeBotonera("Quiero" , new ControladorQuiero(jugador) ) );
		botoneraAcciones.getChildren().add( this.iniciarBotonDeBotonera("No Quiero" , new ControladorNoQuiero(jugador) ) );
		botoneraAcciones.getChildren().add( this.iniciarBotonDeBotonera("Me voy al mazo" , new ControladorMeVoyAlMazo(jugador) ) );
		botonera.getChildren().add(botoneraAcciones);
	}
	
	private void iniciarBotoneraCantosDelTanto() {
		HBox botoneraTantos = this.iniciarCajaHorizontal();
		botoneraTantos.getChildren().add( this.iniciarBotonDeBotonera("Cantar Tanto Envido" , new ControladorCantarTantoDelEnvido(jugador) ) );
		botoneraTantos.getChildren().add( this.iniciarBotonDeBotonera("Cantar Tanto Flor" , new ControladorCantarTantoDeLaFlor(jugador) ) );
		botoneraTantos.getChildren().add( this.iniciarBotonDeBotonera("Son Buenas" , new ControladorSonBuenas(jugador) ) );
		botonera.getChildren().add(botoneraTantos);
	}
	

	private void iniciarCajaDeResultadoDePuntosDeTanto() {
		this.cajaPuntosTanto = this.iniciarCajaHorizontal();
		Text descripcion = this.crearCajaDeTexto("Puntos de Tanto: ");
		Text valorPuntos = this.crearCajaDeTexto("");
		cajaPuntosTanto.getChildren().add(descripcion);
		cajaPuntosTanto.getChildren().add(valorPuntos);
		this.botonera.getChildren().add( this.cajaPuntosTanto );
	}
	
	private Text crearCajaDeTexto(String texto){
		Text caja = new Text("Puntos de Tanto: ");
		caja.setFont(Font.font("Tahoma", FontWeight.BOLD, 25));
		caja.setTextAlignment(TextAlignment.CENTER);
		caja.setFill(Color.WHITE);
		return caja;
	}
	
	private void vincularBotonesDeCantosConObjetosCanto(){
		this.mapaBotonesCantos = new HashMap<Canto,Button>();
		this.mapaBotonesCantos.put( new Envido(null) , this.mapaDeBotonesPorNombre.get("Envido") );
		this.mapaBotonesCantos.put( new RealEnvido(null) , this.mapaDeBotonesPorNombre.get("Real Envido") );
		this.mapaBotonesCantos.put( new FaltaEnvido(null) , this.mapaDeBotonesPorNombre.get("Falta Envido") );
		this.mapaBotonesCantos.put( new Truco(null) , this.mapaDeBotonesPorNombre.get("Truco") );
		this.mapaBotonesCantos.put( new Retruco(null) , this.mapaDeBotonesPorNombre.get("Retruco") );
		this.mapaBotonesCantos.put( new ValeCuatro(null) , this.mapaDeBotonesPorNombre.get("Vale Cuatro") );
		this.mapaBotonesCantos.put( new Flor(null) , this.mapaDeBotonesPorNombre.get("Flor") );
		this.mapaBotonesCantos.put( new ContraFlor(null) , this.mapaDeBotonesPorNombre.get("Contra Flor") );
		this.mapaBotonesCantos.put( new ContraFlorAResto(null) , this.mapaDeBotonesPorNombre.get("Contra Flor a Resto") );
	}
	
	private void estadoInicialDeBotonera(){
		this.apagarTodosLosBotonoes();
		this.prenderBotonesInicialesParaElTruco();
		this.prenderBotonesInicialesParaElTanto();
		this.prenderBotonDeMeVoyAlMazo();
	}
	

	
	/*************************************************
	 ** 			Prender o Apagar Botones		**
	 *************************************************/
	
	private void apagarTodosLosBotonoes() {
		for (Button unBoton : this.mapaDeBotonesPorNombre.values() )
			unBoton.setDisable(true);
		this.apagarLaCajaDePuntosDelTanto();
	}
	
	private void prenderBotonesInicialesParaElTruco() {
		this.mapaDeBotonesPorNombre.get("Truco").setDisable(false);
	}

	private void prenderBotonesInicialesParaElTanto(){
		if ( this.jugador.tieneFlor() )
			this.mapaDeBotonesPorNombre.get("Flor").setDisable(false);
		this.mapaDeBotonesPorNombre.get("Envido").setDisable(false);
		this.mapaDeBotonesPorNombre.get("Real Envido").setDisable(false);
		this.mapaDeBotonesPorNombre.get("Falta Envido").setDisable(false);
	}
	
	private void prenderBotonDeMeVoyAlMazo() {
		this.mapaDeBotonesPorNombre.get("Me voy al mazo").setDisable(false);
	}
	
	private void apagarBotonDeMeVoyAlMazo() {
		this.mapaDeBotonesPorNombre.get("Me voy al mazo").setDisable(true);
	}
	
	private void prenderBotonesDeAceptacion(){
		this.mapaDeBotonesPorNombre.get("Quiero").setDisable(false);
		this.mapaDeBotonesPorNombre.get("No Quiero").setDisable(false);
	}
	
	private void apagarBotonesDeAceptacion(){
		this.mapaDeBotonesPorNombre.get("Quiero").setDisable(true);
		this.mapaDeBotonesPorNombre.get("No Quiero").setDisable(true);
	}
	
	private void apagarBotonesDeTruco(){
		this.mapaDeBotonesPorNombre.get("Truco").setDisable(true);
		this.mapaDeBotonesPorNombre.get("Retruco").setDisable(true);
		this.mapaDeBotonesPorNombre.get("Vale Cuatro").setDisable(true);
	}
	
	private void apagarBotonesDelTanto(){
		this.apagarBotonesDeEnvido();
		this.apagarBotonesDeFlor();
	}
	
	private void apagarBotonesDeEnvido(){
		this.mapaDeBotonesPorNombre.get("Envido").setDisable(true);
		this.mapaDeBotonesPorNombre.get("Real Envido").setDisable(true);
		this.mapaDeBotonesPorNombre.get("Falta Envido").setDisable(true);
	}
	
	private void apagarBotonesDeFlor(){
		this.mapaDeBotonesPorNombre.get("Flor").setDisable(true);
		this.mapaDeBotonesPorNombre.get("Contra Flor").setDisable(true);
		this.mapaDeBotonesPorNombre.get("Contra Flor a Resto").setDisable(true);	
	}
	
	private void prenderBotonesParaCantarElTantoDelEnvido() {
		this.mapaDeBotonesPorNombre.get("Cantar Tanto Envido").setDisable(false);
		this.mapaDeBotonesPorNombre.get("Son Buenas").setDisable(false);
		this.prenderLaCajaDePuntosDelTanto(false);
	}

	private void prenderBotonesParaCantarElTantoDeLaFlor() {
		this.mapaDeBotonesPorNombre.get("Cantar Tanto Flor").setDisable(false);
		this.mapaDeBotonesPorNombre.get("Son Buenas").setDisable(false);
		this.prenderLaCajaDePuntosDelTanto(true);
	}
	
	private void prenderBotonesRespuestasParaElUltimoCanto(CantosEnProceso procesoDeCantos){
		if ( procesoDeCantos.getCantosRealizados().isEmpty() )
			return;
		Canto ultimoCanto = procesoDeCantos.getCantosRealizados().get(procesoDeCantos.getCantosRealizados().size() - 1 );
		this.habilitarCantosDeRespuestaParaElCanto(ultimoCanto);
	}

	private void habilitarCantosDeRespuestaParaElCanto(Canto ultimoCanto) {
		List<Canto> cantosRespuestas = ultimoCanto.cantosValidosDeRespuesta();
		for (Canto unCanto : cantosRespuestas)
			this.mapaBotonesCantos.get(unCanto).setDisable(false);
	}
	
	
	private void apagarLaCajaDePuntosDelTanto(){
		this.cajaPuntosTanto.setVisible(false);
	}
	
	private void prenderLaCajaDePuntosDelTanto(boolean paraLaFlor){
		this.cajaPuntosTanto.setVisible(true);
		if (paraLaFlor)
			this.setearLosPuntosDeTanto(this.jugador.puntajeFlor() );
		else
			this.setearLosPuntosDeTanto(this.jugador.puntajeEnvido() );
	}

	private void setearLosPuntosDeTanto(int puntos){
		Text valorPuntos = (Text) this.cajaPuntosTanto.getChildren().get(1);
		valorPuntos.setText( String.valueOf(puntos) );
	}
	
	private void apagarBotonesDeTantoDeCasosParticulares(CantoEnProcesoParaElTanto cantoEnProcesoDeTanto) {
		if ( !this.jugador.tieneFlor() ){
			this.apagarBotonesDeFlor();
			if ( this.rondaActual.getCantoEnProcesoDelTanto().seCantoFlor() )
				this.mapaDeBotonesPorNombre.get("Quiero").setDisable(true);
		}
		else
			this.mapaDeBotonesPorNombre.get("Flor").setDisable(false);
		
		if (! cantoEnProcesoDeTanto.sePuedeCantarOtroEnvido() )
			this.mapaDeBotonesPorNombre.get("Envido").setDisable(true);
		if (! cantoEnProcesoDeTanto.sePuedeCantarOtroRealEnvido() )
			this.mapaDeBotonesPorNombre.get("Real Envido").setDisable(true);
		if (! cantoEnProcesoDeTanto.sePuedeCantarOtraFlor() && cantoEnProcesoDeTanto.getCantosRealizados().size() == 1 )
			this.mapaDeBotonesPorNombre.get("Flor").setDisable(true);
		if ( cantoEnProcesoDeTanto.seCantoFlor() && cantoEnProcesoDeTanto.getCantosRealizados().size() >= 2 )
			this.apagarBotonesDeFlor();
	}

	
	/*************************************************
	 ** 				Observer					**
	 *************************************************/
	@Override
	public void update(Observable objetoObservable, Object argumento) {
	
		if (this.rondaActual.estaTerminada() ){
			// Se inicia la botonera
			this.rondaActual = this.jugador.getMesa().getRondaActual();
			this.estadoInicialDeBotonera();
		}
		else{
			// Se analiza el caso particular de la botonera
			this.apagarTodosLosBotonoes();
			this.prenderBotonDeMeVoyAlMazo();
			this.analizarQueBotonesDelTrucoHayQuePrender();
			this.analizarQueBotonesDelTantoHayQuePrender();
		}
		
	}

	private void analizarQueBotonesDelTrucoHayQuePrender() {
		CantosEnProcesoParaElTruco cantoEnProcesoDeTruco = rondaActual.getCantoEnProcesoDelTruco();
		// En el caso de que no se halla cantado ningun Truco, se deja en el estado inicial
		if ( ! cantoEnProcesoDeTruco.seRealizoAlgunCanto() ){
			this.prenderBotonesInicialesParaElTruco();
			return;
		}
		if ( cantoEnProcesoDeTruco.seAceptaronLosCantos() ){
			// Si se acepto un truco, solo hay que habilitar los botones de respuestas posibles para el ultimo canto si puede cantar
			if ( this.rondaActual.puedeElJugadorRealizarUnCantoDeTruco(this.jugador) )
				this.prenderBotonesRespuestasParaElUltimoCanto(cantoEnProcesoDeTruco);
		}
		else{
			// Si hay un canto en proceso, se tienen que habilitar los de aceptacion y el canto siguiente para el utlimo Truco cantado.
			this.prenderBotonesDeAceptacion();
			this.prenderBotonesRespuestasParaElUltimoCanto(cantoEnProcesoDeTruco);
			this.apagarBotonDeMeVoyAlMazo();
		}		
	}
	
	private void analizarQueBotonesDelTantoHayQuePrender() {
		/*	En los siguientes casos hay que apagar los botones del tanto: 
		 * 	1º) No se este jugando la primera mano
		 * 	2º) Se halla terminado el canto del tanto. (Puede ser por un No Quiero)
		 * 	3º) Halla un canto del truco aceptado */
		CantoEnProcesoParaElTanto cantoEnProcesoDeTanto = rondaActual.getCantoEnProcesoDelTanto();
		if ( ! this.rondaActual.seEstaJugandoLaPrimeraMano() || 
				rondaActual.terminoElProcesoDeCantoDelTanto() ||
				rondaActual.getCantoEnProcesoDelTruco().seAceptaronLosCantos() ){
			this.apagarBotonesDelTanto();
			return;
		}
		
		// En el caso de que se este jugando la primera ronda y no se halla cantad nada de tanto
		if ( this.rondaActual.seEstaJugandoLaPrimeraMano() && ! rondaActual.seRealizoAlgunCantoDelTanto() ){
			this.prenderBotonesInicialesParaElTanto();
			return;
		}
		// De ahora en mas, hay un tanto cantado, y por ende, debo apagar los botones de truco
		this.apagarBotonesDeTruco();
		this.apagarBotonDeMeVoyAlMazo();
		// En el caso que se pueda seguir respondiendo a los envidos, habilito las respuestas y los de aceptacion.
		if ( cantoEnProcesoDeTanto.sePuedenRealizarOtrosCantos() ){
			this.prenderBotonesRespuestasParaElUltimoCanto(cantoEnProcesoDeTanto);
			this.prenderBotonesDeAceptacion();
			this.apagarBotonesDeTantoDeCasosParticulares(cantoEnProcesoDeTanto);
			return;
		}
			
		// Los ultimos casos a analizar es cuando hay un canto de los puntos del tanto en progreso.
		this.apagarBotonesDeAceptacion();
		if ( this.jugador.getMesa().seJuegaConFlor() && cantoEnProcesoDeTanto.seCantoFlor()){
			this.prenderBotonesParaCantarElTantoDeLaFlor();
		}
		else
			this.prenderBotonesParaCantarElTantoDelEnvido();
		// Surge el caso particular de analizar que el jugador no puede cantar Son Buenas
		if ( !this.rondaActual.puedeElJugadorCantarSonBuenas(this.jugador) )
			this.mapaDeBotonesPorNombre.get("Son Buenas").setDisable(true);
			
	}
	

	/*************************************************
	 ** 		    Metodos Publicos				**
	 *************************************************/
	
	public Node obtenerVista(){
		return botonera;
	}
	
	
}
