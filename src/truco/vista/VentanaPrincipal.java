package truco.vista;

import truco.modelo.*;

import java.util.ArrayList;
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
	
	private List<Button> botonesCartaJugador1;
	private List<Button> botonesCartaJugador2;
	
	private Button botonDevolverCartas;
	private Button botonRepartirCartas;
	
	public VentanaPrincipal() {
		
		this.botonesCartaJugador1 = new ArrayList<Button>();
		this.botonesCartaJugador2 = new ArrayList<Button>();
		
		for(int i=0;i<3;i++){
			this.botonesCartaJugador1.add(new Button("",this.conseguirImagen(null)));
			this.botonesCartaJugador2.add(new Button("",this.conseguirImagen(null)));
		}
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

        for(int i=0;i<3;i++){
        	
        	HBox boxBoton1 = new HBox(10);
            boxBoton1.setAlignment(Pos.CENTER);
            boxBoton1.getChildren().add(this.botonesCartaJugador1.get(i));
            grid.add(boxBoton1, i, 3);
            
            HBox boxBoton4 = new HBox(10);
            boxBoton4.setAlignment(Pos.CENTER);
            boxBoton4.getChildren().add(this.botonesCartaJugador2.get(i));
            grid.add(boxBoton4, i, 5);
            
            
		}
        
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
		Jugador jugador = (Jugador)o;
		List<Carta> cartasJugadas = (List<Carta>)arg;
		
		if(jugador.getNombre() == "Jugador 1"){
			
			int i=0;
			for(Carta cartaJugada: cartasJugadas){
				Button boton = this.botonesCartaJugador1.get(i);
				boton.setGraphic(this.conseguirImagen(cartaJugada));
				this.botonesCartaJugador1.set(i,boton);
				i++;
			}
			
		} else{
			
			int i=0;
			for(Carta cartaJugada: cartasJugadas){
				Button boton = this.botonesCartaJugador2.get(i);
				boton.setGraphic(this.conseguirImagen(cartaJugada));
				this.botonesCartaJugador2.set(i,boton);
				i++;
			}
			
		}
	}
	
}
