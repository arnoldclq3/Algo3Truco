package truco.vista;

import truco.modelo.*;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class VentanaPrincipal extends Ventana implements Observer {
	
	private Button botonCarta1Jugador1;
	private Button botonCarta2Jugador1;
	private Button botonCarta3Jugador1;
	
	private Button botonCarta1Jugador2;
	private Button botonCarta2Jugador2;
	private Button botonCarta3Jugador2;
	
	private Button botonDevolverCartas;
	private Button botonRepartirCartas;
	
	public VentanaPrincipal() {
		
		this.botonCarta1Jugador1 = new Button("",this.conseguirImagen(null));
		this.botonCarta2Jugador1 = new Button("",this.conseguirImagen(null));
		this.botonCarta3Jugador1 = new Button("",this.conseguirImagen(null));
		
		this.botonCarta1Jugador2 = new Button("",this.conseguirImagen(null));
		this.botonCarta2Jugador2 = new Button("",this.conseguirImagen(null));
		this.botonCarta3Jugador2 = new Button("",this.conseguirImagen(null));
		
		this.botonDevolverCartas = new Button("Devolver \n Cartas");
		this.botonRepartirCartas = new Button("Repartir \n Cartas");
	}
	
	public void crearVentana(Stage principal){
		
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(20);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        grid.setGridLinesVisible(true);

        HBox boxBoton1 = new HBox(10);
        boxBoton1.setAlignment(Pos.CENTER);
        boxBoton1.getChildren().add(this.botonCarta1Jugador1);
        grid.add(boxBoton1, 0, 3);
        
        HBox boxBoton2 = new HBox(10);
        boxBoton2.setAlignment(Pos.CENTER);
        boxBoton2.getChildren().add(this.botonCarta2Jugador1);
        grid.add(boxBoton2, 1, 3);
        
        HBox boxBoton3 = new HBox(10);
        boxBoton3.setAlignment(Pos.CENTER);
        boxBoton3.getChildren().add(this.botonCarta3Jugador1);
        grid.add(boxBoton3, 2, 3);
        
        HBox boxBoton4 = new HBox(10);
        boxBoton4.setAlignment(Pos.CENTER);
        boxBoton4.getChildren().add(this.botonCarta1Jugador2);
        grid.add(boxBoton4, 0, 5);
        
        HBox boxBoton5 = new HBox(10);
        boxBoton5.setAlignment(Pos.CENTER);
        boxBoton5.getChildren().add(this.botonCarta2Jugador2);
        grid.add(boxBoton5, 1, 5);
        
        HBox boxBoton6 = new HBox(10);
        boxBoton6.setAlignment(Pos.CENTER);
        boxBoton6.getChildren().add(this.botonCarta3Jugador2);
        grid.add(boxBoton6, 2, 5);
        
        VBox boxPuntaje = new VBox(10);
        Label lblEquipo1 = new Label("Equipo 1: 0");
        Label lblEquipo2 = new Label("Equipo 2: 0");
        
        HBox boxPuntaje1 = new HBox(10);
        boxPuntaje1.getChildren().add(lblEquipo1);
        
        HBox boxPuntaje2 = new HBox(10);
        boxPuntaje2.getChildren().add(lblEquipo2);
        
        boxPuntaje.getChildren().add(boxPuntaje1);
        boxPuntaje.getChildren().add(boxPuntaje2);
        grid.add(boxPuntaje, 0, 1);
        
        
        HBox boxBoton7 = new HBox(10);
        boxBoton7.setAlignment(Pos.CENTER);
        boxBoton7.getChildren().add(this.botonDevolverCartas);
        grid.add(boxBoton7, 0, 7);
        
        HBox boxBoton8 = new HBox(10);
        boxBoton8.setAlignment(Pos.CENTER);
        boxBoton8.getChildren().add(this.botonRepartirCartas);
        grid.add(boxBoton8, 2, 7);
        
        Scene ventanaPrincipal = new Scene(grid, 800, 400);
        principal.setScene(ventanaPrincipal);
	}
	
	public void setBotonDevolverCartas(EventHandler<ActionEvent> controlador) {
		
		this.botonDevolverCartas.setOnAction(controlador);
	}
	
	public void setBotonRepartirCartas(EventHandler<ActionEvent> controlador) {
		
		this.botonRepartirCartas.setOnAction(controlador);
	}

	@Override
	public void update(Observable o, Object arg) {
		
		@SuppressWarnings("unchecked")
		List<Carta> cartas = (List<Carta>)arg;
		
		/*if ( cartas.size() == 3 ) {
			
			Carta carta1 = cartas.get(0);
			Carta carta2 = cartas.get(1);
			Carta carta3 = cartas.get(2);
			
			this.botonCarta1.setGraphic(this.conseguirImagen(carta1));
			this.botonCarta2.setGraphic(this.conseguirImagen(carta2));
			this.botonCarta3.setGraphic(this.conseguirImagen(carta3));
		
		} else if ( cartas.size() == 0 ) {
			
			this.botonCarta1.setGraphic(this.conseguirImagen(null));
			this.botonCarta2.setGraphic(this.conseguirImagen(null));
			this.botonCarta3.setGraphic(this.conseguirImagen(null));
		}*/
	}
	
}
