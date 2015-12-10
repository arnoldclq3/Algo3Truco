package truco.vista;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import truco.controlador.Integrador;
import truco.modelo.JuegoTruco;
import truco.modelo.Jugador;

public class MenuPrincipal {

	private Stage stage;
	private JuegoTruco juego;
	private Integrador integrador;
	
	public MenuPrincipal(Stage stage) {
		
		this.stage = stage;
		this.juego = new JuegoTruco();
		this.integrador = new Integrador(stage);
	}
	
	public void crearMenuBienvenida() {
		
		VBox botones = new VBox();
		botones.setAlignment(Pos.TOP_CENTER);
		botones.setSpacing(10);
		botones.setPadding(new Insets(50, 10, 10, 10));
		
		Text tituloPrincipa2 = new Text("Bienvenido \n a");
        tituloPrincipa2.setFont(Font.font("Tahoma", FontWeight.BOLD, 25));
        tituloPrincipa2.setTextAlignment(TextAlignment.CENTER);
        tituloPrincipa2.setFill(Color.WHITE);
        
		Text tituloPrincipal = new Text("ALGO3TRUCO");
        tituloPrincipal.setFont(Font.font("Tahoma", FontWeight.BOLD, 40));
        tituloPrincipal.setTextAlignment(TextAlignment.CENTER);
        tituloPrincipal.setFill(Color.WHITE);
		
        Button comenzar = new Button("Comenzar");
        comenzar.setOnAction(e->{
        	this.crearMenuCantidadJugadores();
        });
        
        botones.getChildren().add(tituloPrincipa2);
		botones.getChildren().add(tituloPrincipal);
		botones.getChildren().add(new Text(""));
		botones.getChildren().add(new Text(""));
		botones.getChildren().add(comenzar);
		
		BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(new Image("file:Imagenes/FONDO3.jpg"), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);
        botones.setBackground(background);
        
		Scene scene = new Scene(botones,344,600);
		this.stage.setScene(scene);
		this.stage.setTitle("Algo3Truco");
		this.stage.getIcons().add(new Image("file:Imagenes/ICON.png"));
		this.stage.show();
	}
	
	public void crearMenuCantidadJugadores() {
		
		VBox botones = new VBox();
		botones.setAlignment(Pos.TOP_CENTER);
		botones.setSpacing(10);
		botones.setPadding(new Insets(20, 10, 10, 10));
		
		Text tituloPrincipal = new Text("Seleccionar cantidad \n jugadores");
		tituloPrincipal.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
	    tituloPrincipal.setTextAlignment(TextAlignment.CENTER);
	    tituloPrincipal.setFill(Color.WHITE);
        
        Button boton2Jug = new Button("2 jugadores");
        boton2Jug.setOnAction(e->{
        	this.juego.juegoDeDosJugadores();
        	this.crearMenuNombresJugadores();
        });
        Button boton4Jug = new Button("4 jugadores");
        boton4Jug.setDisable(true);
        Button boton6Jug = new Button("6 jugadores");
        boton6Jug.setDisable(true);
		
		botones.getChildren().add(tituloPrincipal);
		botones.getChildren().add(new Text(""));
		botones.getChildren().add(boton2Jug);
		botones.getChildren().add(boton4Jug);
		botones.getChildren().add(boton6Jug);
		
		BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(new Image("file:Imagenes/FONDO3.jpg"), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);
        botones.setBackground(background);
        
		Scene scene = new Scene(botones,344,600);
		this.stage.setScene(scene);
	}
	public void crearMenuNombresJugadores() {
		
		VBox botones2 = new VBox();
		botones2.setAlignment(Pos.TOP_CENTER);
		botones2.setSpacing(10);
		botones2.setPadding(new Insets(20, 10, 10, 10));
		
		GridPane botones = new GridPane();
		botones.setAlignment(Pos.CENTER);
		botones.setHgap(5);
		botones.setVgap(5);
		
		Text tituloPrincipal = new Text("Ingrese los nombres \n de los jugadores");
        tituloPrincipal.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
        tituloPrincipal.setTextAlignment(TextAlignment.CENTER);
        tituloPrincipal.setFill(Color.WHITE);
        
        Label jugador1Label = new Label("Nombre Jugador 1:");
        jugador1Label.setTextFill(Color.WHITE);
        Label jugador2Label = new Label("Nombre Jugador 2:");
        jugador2Label.setTextFill(Color.WHITE);
        
        TextField nombreJugador1 = new TextField();
        nombreJugador1.setPromptText("Jugador 1");
        TextField nombreJugador2 = new TextField();
        nombreJugador2.setPromptText("Jugador 2");
		
        Button siguiente = new Button("Siguiente");
        siguiente.setOnAction(e->{
        	
        	Jugador jugador1 = new Jugador(nombreJugador1.getText());
        	Jugador jugador2 = new Jugador(nombreJugador2.getText());
        	
        	this.juego.agregarJugadorEnEquipoNosotros(jugador1);
        	this.juego.agregarJugadorEnEquipoEllos(jugador2);
        	
        	/*
        	this.integrador.agregarJugadorObservado(jugador1);
        	this.integrador.agregarJugadorObservado(jugador2);
        	*/
        	this.crearMenuFlor();
        });
        
		botones.add(jugador1Label, 0, 10);
		botones.add(nombreJugador1, 1, 10);
		botones.add(jugador2Label, 0, 11);
		botones.add(nombreJugador2, 1, 11);
		botones.add(siguiente, 1, 15);
		
		botones2.getChildren().add(tituloPrincipal);
		botones2.getChildren().add(botones);
		
		BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(new Image("file:Imagenes/FONDO3.jpg"), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);
        botones2.setBackground(background);
        
		Scene scene = new Scene(botones2,344,600);
		this.stage.setScene(scene);
	}
	
	public void crearMenuFlor() {
		
		VBox botones = new VBox();
		botones.setAlignment(Pos.TOP_CENTER);
		botones.setSpacing(10);
		botones.setPadding(new Insets(20, 10, 10, 10));
		
		Text tituloPrincipal = new Text("Seleccionar si se juega \n con Flor");
        tituloPrincipal.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
        tituloPrincipal.setTextAlignment(TextAlignment.CENTER);
        tituloPrincipal.setFill(Color.WHITE);
        
        Button botonConFlor = new Button("Con Flor");
        botonConFlor.setOnAction(e->{
        	this.juego.jugarConFlor();
        	this.crearMenuJugar();
        });
        
        Button botonSinFlor = new Button("Sin Flor");
        botonSinFlor.setOnAction(e->{
        	this.juego.jugarSinFlor();
        	this.crearMenuJugar();
        });
		
		botones.getChildren().add(tituloPrincipal);
		botones.getChildren().add(new Text(""));
		botones.getChildren().add(botonConFlor);
		botones.getChildren().add(botonSinFlor);
		
		BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(new Image("file:Imagenes/FONDO3.jpg"), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);
        botones.setBackground(background);
        
		Scene scene = new Scene(botones,344,600);
		this.stage.setScene(scene);
	}
	
	public void crearMenuJugar() {
		
		VBox botones = new VBox();
		botones.setAlignment(Pos.CENTER);
		botones.setSpacing(10);
		botones.setPadding(new Insets(10, 10, 10, 10));
        
		Image play = new Image("file:Imagenes/PLAY.png");
		ImageView imagenPlay = new ImageView(play);
		imagenPlay.setFitWidth(120);
		imagenPlay.setPreserveRatio(true);
		
        Button botonJugar = new Button("",imagenPlay);
        botonJugar.setOnAction(e->{
        
        
        	//this.integrador.agregarMesaObservada(this.juego.iniciarJuego());
        	this.integrador.iniciar( this.juego.iniciarJuego() );
        });
		
		botones.getChildren().add(botonJugar);
		
		BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(new Image("file:Imagenes/FONDO3.jpg"), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);
        botones.setBackground(background);
        
		Scene scene = new Scene(botones,344,600);
		this.stage.setScene(scene);
	}
}
