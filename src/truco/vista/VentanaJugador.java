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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class VentanaJugador extends Ventana implements Observer {
	
	private List<Button> botonesCarta;
	
	private Button botonTruco;
	private Button botonReTruco;
	private Button botonValeCuatro;
	
	private Button botonEnvido;
	private Button botonRealEnvido;
	private Button botonFaltaEnvido;
	
	private Button botonQuiero;
	private Button botonNoQuiero;
	private Button botonIrseAlMazo;
	
	private static Carta carta1;
	private static Carta carta2;
	private static Carta carta3;
	
	private Jugador jugador;
	
	public VentanaJugador(Jugador jugador) {
		
		this.jugador = jugador;
		
		this.botonesCarta = new ArrayList<Button>();
		for(int i=1;i<=3;i++){
			this.botonesCarta.add(new Button("",this.conseguirImagen(null)));
		}
		
		this.botonTruco = new Button("Truco");
		this.botonReTruco = new Button("Re Truco");
		this.botonValeCuatro = new Button("Vale Cuatro");
		
		this.botonEnvido = new Button("Envido");
		this.botonRealEnvido = new Button("Real Envdio");
		this.botonFaltaEnvido = new Button("Falta Envido");
		
		this.botonQuiero = new Button("Quiero");
		this.botonNoQuiero = new Button("No Quiero");
		this.botonIrseAlMazo = new Button("Irse al Mazo");
	}
	
	public void crearVentana(Stage principal){
		
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(20);
        grid.setVgap(20);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        grid.setGridLinesVisible(true);

        for(int i=0;i<3;i++){
        	HBox boxBoton1 = new HBox(10);
            boxBoton1.setAlignment(Pos.CENTER);
            boxBoton1.getChildren().add(this.botonesCarta.get(i));
            grid.add(boxBoton1, i, 2);
        }
        
        //botones truco
        HBox boxBoton4 = new HBox(10);
        boxBoton4.setAlignment(Pos.CENTER);
        boxBoton4.getChildren().add(this.botonTruco);
        
        HBox boxBoton5 = new HBox(10);
        boxBoton5.setAlignment(Pos.CENTER);
        boxBoton5.getChildren().add(this.botonReTruco);
        
        HBox boxBoton6 = new HBox(10);
        boxBoton6.setAlignment(Pos.CENTER);
        boxBoton6.getChildren().add(this.botonValeCuatro);
        
        VBox botoneraTruco = new VBox(10);
        botoneraTruco.setAlignment(Pos.CENTER);
        botoneraTruco.getChildren().add(boxBoton4);
        botoneraTruco.getChildren().add(boxBoton5);
        botoneraTruco.getChildren().add(boxBoton6);
        grid.add(botoneraTruco, 3, 2);
        
        //botones envido
        HBox boxBoton7 = new HBox(10);
        boxBoton7.setAlignment(Pos.CENTER);
        boxBoton7.getChildren().add(this.botonEnvido);
        
        HBox boxBoton8 = new HBox(10);
        boxBoton8.setAlignment(Pos.CENTER);
        boxBoton8.getChildren().add(this.botonRealEnvido);
        
        HBox boxBoton9 = new HBox(10);
        boxBoton9.setAlignment(Pos.CENTER);
        boxBoton9.getChildren().add(this.botonFaltaEnvido);
        
        VBox botoneraEnvido = new VBox(10);
        botoneraEnvido.setAlignment(Pos.CENTER);
        botoneraEnvido.getChildren().add(boxBoton7);
        botoneraEnvido.getChildren().add(boxBoton8);
        botoneraEnvido.getChildren().add(boxBoton9);
        grid.add(botoneraEnvido, 4, 2);
        
        //botones acciones
        VBox boxBoton10 = new VBox(10);
        boxBoton10.setAlignment(Pos.CENTER);
        boxBoton10.getChildren().add(this.botonQuiero);
        
        VBox boxBoton11 = new VBox(10);
        boxBoton11.setAlignment(Pos.CENTER);
        boxBoton11.getChildren().add(this.botonNoQuiero);
        
        VBox boxBoton12 = new VBox(10);
        boxBoton12.setAlignment(Pos.CENTER);
        boxBoton12.getChildren().add(this.botonIrseAlMazo);
        
        grid.add(boxBoton10, 0, 3);
        grid.add(boxBoton11, 1, 3);
        grid.add(boxBoton12, 2, 3);
        
        Scene ventanaPrincipal = new Scene(grid, 800, 400);
        principal.setScene(ventanaPrincipal);
        
	}

	@Override
	public void update(Observable o, Object arg) {
		
		@SuppressWarnings("unchecked")
		List<Carta> cartas = (List<Carta>)arg;
		
		int i = 0;
		for(Carta carta: cartas){
			Button botonCarta = this.botonesCarta.get(i);
			botonCarta.setGraphic(this.conseguirImagen(carta));
			this.botonesCarta.set(i, botonCarta);
			i++;
		}
	}
	
}
