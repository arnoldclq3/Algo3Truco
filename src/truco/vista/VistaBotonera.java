package truco.vista;

import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import truco.modelo.*;

public class VistaBotonera implements Observer{

	private HBox botoneraTruco;
	private HBox botoneraEnvido;
	private HBox botoneraFlor;
	private HBox botoneraAcciones;
	private HBox botoneraTantos;
	
	private HashMap<Canto, Button> mapaBotonesCantos;
	private VBox botonera;
	
	
	/*************************************************
	 ** 				Constructor					**
	 *************************************************/

	public VistaBotonera(){
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

	private Button iniciarBotonDeBotonera(String nombre){
		Button botonARetornar = new Button(nombre);
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
		this.botoneraTruco.getChildren().add( this.iniciarBotonDeBotonera("Truco") );
		this.botoneraTruco.getChildren().add( this.iniciarBotonDeBotonera("Retruco") );
		this.botoneraTruco.getChildren().add( this.iniciarBotonDeBotonera("Vale Cuatro") );
	}
	
	private void iniciarBotoneraEnvido() {
		this.botoneraEnvido.getChildren().add( this.iniciarBotonDeBotonera("Envido") );
		this.botoneraEnvido.getChildren().add( this.iniciarBotonDeBotonera("Real Envido") );
		this.botoneraEnvido.getChildren().add( this.iniciarBotonDeBotonera("Falta Envido") );
	}
	
	private void iniciarBotoneraFlor() {
		this.botoneraFlor.getChildren().add( this.iniciarBotonDeBotonera("Flor") );
		this.botoneraFlor.getChildren().add( this.iniciarBotonDeBotonera("Contra Flor") );
		this.botoneraFlor.getChildren().add( this.iniciarBotonDeBotonera("Contra Flor a Resto") );
	}
	
	private void iniciarBotoneraAcciones() {
		this.botoneraAcciones.getChildren().add( this.iniciarBotonDeBotonera("Quiero") );
		this.botoneraAcciones.getChildren().add( this.iniciarBotonDeBotonera("No Quiero") );
		this.botoneraAcciones.getChildren().add( this.iniciarBotonDeBotonera("Me voy al mazo") );
	}
	
	private void iniciarBotoneraCantosDelTanto() {
		this.botoneraTantos.getChildren().add( this.iniciarBotonDeBotonera("Cantar Tanto") );
		this.botoneraTantos.getChildren().add( this.iniciarBotonDeBotonera("Son Buenas") );	
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
		
		this.botoneraFlor.setDisable(true);

		this.botoneraAcciones.getChildren().get(0).setDisable(true);
		this.botoneraAcciones.getChildren().get(1).setDisable(true);
		this.botoneraTantos.setVisible(false);
	}
	

	
	/*************************************************
	 ** 				Observer					**
	 *************************************************/
	@Override
	public void update(Observable objetoObservable, Object argumento) {
		// TODO Auto-generated method stub
		
	}
	
	
	/*************************************************
	 ** 		    Metodos Publicos				**
	 *************************************************/
	
	public Node obtenerVista(){
		return botonera;
	}
	
	
}
