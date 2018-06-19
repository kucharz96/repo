package application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.geometry.Insets; 
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.control.TextField;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.Group;
import javafx.scene.layout.VBox;
/////////////Dla MongoDB////////////
import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;


public class Main extends Application {
	 //private TableView table = new TableView();
	 private Stage stages = new Stage();
 	 MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
 	 DB database = mongoClient.getDB("Kamil");
 	 	//Pobiera kolekcjê, tworzy zapytanie wraz z kursorem (petla)
 	 DBCollection kolekcja_pacjenci = database.getCollection("Pacjenci");
 	 BasicDBObject searchQuery = new BasicDBObject();
 	 DBCursor cursor = kolekcja_pacjenci.find();
 	 

 	 private TableView<Pacjent> table = new TableView<Pacjent>();
     private ObservableList<Pacjent>  dane_pacjentow = FXCollections.observableArrayList();
     final HBox hb = new HBox(); //Odpowiada wyswietlenie dodawania
 	 private int a = 0;
 	 //////////////////
 	 ////////////////////Funkcja pierwsza////////////////////////
	public void pacjenci(Stage stage) {
		 final VBox vbox = new VBox();
		 if (a==0){
        Scene scene = new Scene(new Group());
        
        stage.setTitle("Pacjenci");
        stage.setWidth(1500);
        stage.setHeight(1000);
 
        final Label label = new Label("Lista pacjentów");
        label.setFont(new Font("Arial", 20));
 
        table.setEditable(true);
 ////////////////////////Kolumny i mo¿liwe zmiany///////////////
        TableColumn kolumna_imie = new TableColumn("Imie");
        kolumna_imie.setMinWidth(100);
        kolumna_imie.setCellValueFactory(
                new PropertyValueFactory<Pacjent, String>("imie"));
        //Edycja pola Imie
        kolumna_imie.setCellFactory(TextFieldTableCell.forTableColumn());
        kolumna_imie.setOnEditCommit(
            new EventHandler<CellEditEvent<Pacjent, String>>() {
                @Override
                public void handle(CellEditEvent<Pacjent, String> t) {
                	
                	BasicDBObject newDocument = new BasicDBObject();
                	newDocument.append("$set", new BasicDBObject().append("imie", t.getNewValue()));

                	BasicDBObject searchQuery = new BasicDBObject().append("_id",((Pacjent) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                            ).getId_pacjenta());

                	kolekcja_pacjenci.update(searchQuery, newDocument);
                	
                	((Pacjent) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        ).setImie(t.getNewValue());
                }
            });
        TableColumn kolumna_nazwisko = new TableColumn("Nazwisko");
        kolumna_nazwisko.setMinWidth(100);
        kolumna_nazwisko.setCellValueFactory(
                new PropertyValueFactory<Pacjent, String>("nazwisko"));
        //Edycja pole nazwisko
        kolumna_nazwisko.setCellFactory(TextFieldTableCell.forTableColumn());
        kolumna_nazwisko.setOnEditCommit(
            new EventHandler<CellEditEvent<Pacjent, String>>() {
                @Override
                public void handle(CellEditEvent<Pacjent, String> t) {
                    
                	BasicDBObject newDocument = new BasicDBObject();
                	newDocument.append("$set", new BasicDBObject().append("nazwisko", t.getNewValue()));

                	BasicDBObject searchQuery = new BasicDBObject().append("_id",((Pacjent) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                            ).getId_pacjenta());

                	kolekcja_pacjenci.update(searchQuery, newDocument);
                	
                	
                	
                	
                	((Pacjent) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        ).setNazwisko(t.getNewValue());
                }
            }
        );
        TableColumn kolumna_miasto = new TableColumn("Miasto");
        kolumna_miasto.setMinWidth(200);
        kolumna_miasto.setCellValueFactory(
                new PropertyValueFactory<Pacjent, String>("miasto"));
        //Edycja pole miasto
        kolumna_miasto.setCellFactory(TextFieldTableCell.forTableColumn());
        kolumna_miasto.setOnEditCommit(
            new EventHandler<CellEditEvent<Pacjent, String>>() {
                @Override
                public void handle(CellEditEvent<Pacjent, String> t) {
                	BasicDBObject newDocument = new BasicDBObject();
                	newDocument.append("$set", new BasicDBObject().append("miasto", t.getNewValue()));

                	BasicDBObject searchQuery = new BasicDBObject().append("_id",((Pacjent) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                            ).getId_pacjenta());

                	kolekcja_pacjenci.update(searchQuery, newDocument);
                	
                	
                	
                	
                	((Pacjent) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        ).setMiasto(t.getNewValue());
                }
            }
        );
        //Kolumna pesel
        TableColumn kolumna_pesel = new TableColumn("Pesel");
        kolumna_pesel.setMinWidth(200);
        kolumna_pesel.setCellValueFactory(
                new PropertyValueFactory<Pacjent, String>("pesel"));
        //Edycja pole pesel
        kolumna_pesel.setCellFactory(TextFieldTableCell.forTableColumn());
        kolumna_pesel.setOnEditCommit(
            new EventHandler<CellEditEvent<Pacjent, String>>() {
                @Override
                public void handle(CellEditEvent<Pacjent, String> t) {
                    
                	BasicDBObject newDocument = new BasicDBObject();
                	newDocument.append("$set", new BasicDBObject().append("pesel", t.getNewValue()));

                	BasicDBObject searchQuery = new BasicDBObject().append("_id",((Pacjent) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                            ).getId_pacjenta());

                	kolekcja_pacjenci.update(searchQuery, newDocument);
                	
                	
                	
                	
                	((Pacjent) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        ).setMiasto(t.getNewValue());
                }
            }
        );
        //Kolumna ulica//
        TableColumn kolumna_ulica = new TableColumn("Ulica");
        kolumna_ulica.setMinWidth(200);
        kolumna_ulica.setCellValueFactory(
                new PropertyValueFactory<Pacjent, String>("ulica"));
        //Edycja pole ulica
        kolumna_ulica.setCellFactory(TextFieldTableCell.forTableColumn());
        kolumna_ulica.setOnEditCommit(
            new EventHandler<CellEditEvent<Pacjent, String>>() {
                @Override
                public void handle(CellEditEvent<Pacjent, String> t) {
                    
                	BasicDBObject newDocument = new BasicDBObject();
                	newDocument.append("$set", new BasicDBObject().append("ulica", t.getNewValue()));

                	BasicDBObject searchQuery = new BasicDBObject().append("_id",((Pacjent) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                            ).getId_pacjenta());

                	kolekcja_pacjenci.update(searchQuery, newDocument);
                	
                	
                	
                	((Pacjent) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        ).setMiasto(t.getNewValue());
                }
            }
        );
        //Kolumna telefon
        TableColumn kolumna_telefon = new TableColumn("Telefon");
        kolumna_telefon.setMinWidth(200);
        kolumna_telefon.setCellValueFactory(
                new PropertyValueFactory<Pacjent, String>("telefon"));
        //Edycja pole telefon
        kolumna_telefon.setCellFactory(TextFieldTableCell.forTableColumn());
        kolumna_telefon.setOnEditCommit(
            new EventHandler<CellEditEvent<Pacjent, String>>() {
                @Override
                public void handle(CellEditEvent<Pacjent, String> t) {
                    
                	BasicDBObject newDocument = new BasicDBObject();
                	newDocument.append("$set", new BasicDBObject().append("telefon", t.getNewValue()));

                	BasicDBObject searchQuery = new BasicDBObject().append("_id",((Pacjent) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                            ).getId_pacjenta());

                	kolekcja_pacjenci.update(searchQuery, newDocument);
                	
                	
                	((Pacjent) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        ).setMiasto(t.getNewValue());
                }
            }
        );
        //Kolumna _id pacjenta
        TableColumn kolumna_id_pacjenta = new TableColumn("ID pacjenta");
        kolumna_id_pacjenta.setMinWidth(200);
        kolumna_id_pacjenta.setCellValueFactory(
                new PropertyValueFactory<Pacjent, String>("id_pacjenta"));
        //Edycja pole id
        kolumna_id_pacjenta.setCellFactory(TextFieldTableCell.forTableColumn());
        kolumna_id_pacjenta.setOnEditCommit(
            new EventHandler<CellEditEvent<Pacjent, String>>() {
                @Override
                public void handle(CellEditEvent<Pacjent, String> t) {
                    
                	
                	BasicDBObject newDocument = new BasicDBObject();
                	newDocument.append("$set", new BasicDBObject().append("id_pacjenta", t.getNewValue()));

                	BasicDBObject searchQuery = new BasicDBObject().append("_id",((Pacjent) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                            ).getId_pacjenta());

                	kolekcja_pacjenci.update(searchQuery, newDocument);
                	
                	
                	((Pacjent) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        ).setMiasto(t.getNewValue());
                }
            }
        );
        //ID lekarza
        TableColumn kolumna_id_lekarza = new TableColumn("ID lekarza");
        kolumna_id_lekarza.setMinWidth(200);
        kolumna_id_lekarza.setCellValueFactory(
                new PropertyValueFactory<Pacjent, String>("id_lekarza"));
        //Edycja pole id_lekarza
        kolumna_id_lekarza.setCellFactory(TextFieldTableCell.forTableColumn());
        kolumna_id_lekarza.setOnEditCommit(
            new EventHandler<CellEditEvent<Pacjent, String>>() {
                @Override
                public void handle(CellEditEvent<Pacjent, String> t) {
                	BasicDBObject newDocument = new BasicDBObject();
                	newDocument.append("$set", new BasicDBObject().append("id_lekarza", t.getNewValue()));

                	BasicDBObject searchQuery = new BasicDBObject().append("_id",((Pacjent) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                            ).getId_pacjenta());

                	kolekcja_pacjenci.update(searchQuery, newDocument);
                	
                	
                	
                	
                	((Pacjent) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        ).setMiasto(t.getNewValue());
                }
            }
        );

        //System.out.println(cursor.next());
        ////Odczyt z bazy do obiektow person
        try{
		 	while(cursor.hasNext())
		 	{
		 		DBObject obecny_dokument = cursor.next();
		 		
		 		dane_pacjentow.add(new Pacjent((String)obecny_dokument.get("imie"), (String)obecny_dokument.get("nazwisko"), (String)obecny_dokument.get("pesel"),
		 				(String)obecny_dokument.get("miasto"), (String)obecny_dokument.get("ulica"), (String)obecny_dokument.get("telefon"),(String)obecny_dokument.get("_id"),
		 				(String)obecny_dokument.get("_id_lekarza")));
		 		

		 	}
        }finally{
		 		
		 		cursor.close();
		 	}
        table.setItems(dane_pacjentow);
        //System.out.println(dane_pacjentow);
        //table.setItems(dane_pacjentow);

        table.getColumns().addAll(kolumna_id_pacjenta , kolumna_imie, kolumna_nazwisko, kolumna_pesel, kolumna_miasto, kolumna_ulica, kolumna_telefon, kolumna_id_lekarza);
 
        ////////Operacja dodawania////////
        final TextField addImie = new TextField();
        addImie.setPromptText("Imie");
        addImie.setMaxWidth(kolumna_imie.getPrefWidth());
        final TextField addNazwisko = new TextField();
        addNazwisko.setMaxWidth(kolumna_nazwisko.getPrefWidth());
        addNazwisko.setPromptText("Nazwisko");
        final TextField addPesel = new TextField();
        addPesel.setMaxWidth(kolumna_pesel.getPrefWidth());
        addPesel.setPromptText("Pesel");
        final TextField addMiasto = new TextField();
        addMiasto.setMaxWidth(kolumna_miasto.getPrefWidth());
        addMiasto.setPromptText("Miasto");
        final TextField addUlica = new TextField();
        addUlica.setMaxWidth(kolumna_ulica.getPrefWidth());
        addUlica.setPromptText("Ulica");
        final TextField addTelefon = new TextField();
        addTelefon.setMaxWidth(kolumna_telefon.getPrefWidth());
        addTelefon.setPromptText("Telefon");
        final TextField addId_pacjenta = new TextField();
        addId_pacjenta.setMaxWidth(kolumna_id_pacjenta.getPrefWidth());
        addId_pacjenta.setPromptText("ID pacjenta");
        final TextField addId_lekarza = new TextField();
        addId_lekarza.setMaxWidth(kolumna_id_lekarza.getPrefWidth());
        addId_lekarza.setPromptText("ID lekarza");
        
        final Button addButton = new Button("Add");
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                dane_pacjentow.add(new Pacjent(
                        addImie.getText(),
                        addNazwisko.getText(),
                        addPesel.getText(),
                        addMiasto.getText(),
                        addUlica.getText(),
                        addTelefon.getText(),
                        addId_pacjenta.getText(),
                        addId_lekarza.getText()
                        ));
                BasicDBObject dodaj = new BasicDBObject("imie", addImie.getText()).append("nazwisko", addNazwisko.getText())
                		.append("pesel",  addPesel.getText()).append("miasto", addMiasto.getText()).append("ulica", addUlica.getText())
                		.append("telefon", addTelefon.getText()).append("_id", addId_pacjenta.getText())
                		.append("_id_lekarza", addId_lekarza.getText());
                kolekcja_pacjenci.insert(dodaj);
                
                addImie.clear();
                addNazwisko.clear();
                addPesel.clear();
                addMiasto.clear();
                addUlica.clear();
                addTelefon.clear();
                addId_pacjenta.clear();
                addId_lekarza.clear();
                
            }
        });
        
        //Pobieramy dzieci, czyli elementy
        hb.getChildren().addAll(addId_pacjenta, addImie, addNazwisko, addPesel, addMiasto, addUlica, addTelefon, addId_lekarza, addButton);
        hb.setSpacing(5);
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        //Hb jest dzieciaczkiem (uruchomiony w ramach rodzica, zatem hb podaæ jako argument)
        vbox.getChildren().addAll(label, table, hb);
 
        ((Group) scene.getRoot()).getChildren().addAll(vbox);
 
        stage.setScene(scene);
        stage.show();
        a=1;
		 }
		 else
		 stage.show();
    }
	//////////////////Funkcja start//////////////////////
    @Override
    public void start(Stage primaryStage) {

    	Button btn = new Button();
        Button btn2 = new Button();
        Button btn3 = new Button();
        Label label1 = new Label("Przychodnia");
        Pane root = new Pane();
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setTitle("Aplikacja");
        primaryStage.setScene(scene);
        label1.setFont(new Font("Arial", 38));
        ObservableList list = root.getChildren();
        btn.setText("Pacjenci");
        btn2.setText("Lekarze");
        
        btn3.setText("Exit");
        btn.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
            	//mongoClient.getDatabaseNames().forEach(System.out::println);

            	//list.clear();
            	pacjenci(stages);

            }
            
        });
        
        
        
        label1.setLayoutX(190);
        label1.setLayoutY(40);
        btn.setLayoutX(150);
        btn.setLayoutY(120);
        btn2.setLayoutX(150);
        btn2.setLayoutY(220);
        btn3.setLayoutX(430);
        btn3.setLayoutY(350);
        btn.setMinWidth(300);
        btn.setMinHeight(75);
        btn2.setMinWidth(300);
        btn2.setMinHeight(75);
        btn3.setMinWidth(100);
        
        list.addAll(btn,btn2,btn3,label1);

        primaryStage.show();
    }
    public static class Pacjent {
    	 
        private final SimpleStringProperty imie;
        private final SimpleStringProperty nazwisko;
        private final SimpleStringProperty pesel;
        private final SimpleStringProperty miasto;
        private final SimpleStringProperty ulica;
        private final SimpleStringProperty telefon;
        private final SimpleStringProperty id_pacjenta;
        private final SimpleStringProperty id_lekarza;
        //Konstruktor
        private Pacjent(String imie, String nazwisko, String pesel, String miasto, String ulica, String telefon, String id_pacjenta, String id_lekarza) {
            this.imie = new SimpleStringProperty(imie);
            this.nazwisko = new SimpleStringProperty(nazwisko);
            this.pesel = new SimpleStringProperty(pesel);
            this.miasto = new SimpleStringProperty(miasto);
            this.ulica = new SimpleStringProperty(ulica);
            this.telefon = new SimpleStringProperty(telefon);
            this.id_pacjenta = new SimpleStringProperty(id_pacjenta);
            this.id_lekarza = new SimpleStringProperty(id_lekarza);
        }
        //GETTERY I SETTERY
        public String getImie() {
            return imie.get();
        }
        
        public void setImie(String fName) {
            imie.set(fName);
        }
 
        public String getNazwisko() {
            return nazwisko.get();
        }
 
        public void setNazwisko(String fName) {
            nazwisko.set(fName);
        }
 
        public String getPesel() {
            return pesel.get();
        }
 
        public void setPesel(String fName) {
            pesel.set(fName);
        }
        public String getMiasto() {
            return miasto.get();
        }
 
        public void setMiasto(String fName) {
            miasto.set(fName);
        }
        public String getUlica() {
            return ulica.get();
        }
        
        public void setUlica(String fName) {
            ulica.set(fName);
        }
        public String getTelefon() {
            return telefon.get();
        }
 
        public void setTelefon(String fName) {
            telefon.set(fName);
        }
        public String getId_pacjenta() {
            return id_pacjenta.get();
        }
 
        public void setId_pacjenta(String fName) {
            id_pacjenta.set(fName);
        }
        public String getId_lekarza() {
            return id_lekarza.get();
        }
 
        public void setId_lekarza(String fName) {
            id_lekarza.set(fName);
        }
    }
 public static void main(String[] args) {
        launch(args);
    }
}