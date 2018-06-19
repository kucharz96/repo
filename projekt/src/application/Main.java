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
import java.lang.String;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.Group;
import javafx.scene.layout.VBox;
import javafx.beans.value.ChangeListener;

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
 	 DBCollection kolekcja_lekarze = database.getCollection("Lekarze");
 	 BasicDBObject searchQuery = new BasicDBObject();
 	 DBCursor cursor = kolekcja_pacjenci.find();
 	 

 	 private TableView<Pacjent> table = new TableView<Pacjent>();
 TableView<Lekarz> table1 = new TableView<Lekarz>();
 	 private ObservableList<Lekarz>  dane_lekarzy = FXCollections.observableArrayList();
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
                        ).setPesel(t.getNewValue());
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
                        ).setUlica(t.getNewValue());
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
                        ).setTelefon(t.getNewValue());
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
                        ).setId_pacjenta(t.getNewValue());
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
                        ).setId_lekarza(t.getNewValue());
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
        final Button delButton = new Button("Delete");
        addButton.setOnAction(new EventHandler<ActionEvent>() {
        	 BasicDBObject dodaj;
            @Override  
            public void handle(ActionEvent e) throws  DuplicateKeyException  {
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
                dodaj = new BasicDBObject("imie", addImie.getText()).append("nazwisko", addNazwisko.getText())
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
        
        delButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
            	
            	
            	kolekcja_pacjenci.remove(new BasicDBObject().append("_id",table.getSelectionModel().getSelectedItem().getId_pacjenta()));

            }
        });
        
        
        
        //Pobieramy dzieci, czyli elementy
        hb.getChildren().addAll(addId_pacjenta, addImie, addNazwisko, addPesel, addMiasto, addUlica, addTelefon, addId_lekarza, addButton,delButton);
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
	//////////////////////Funkcja lekarze//////////////////
	public void lekarze(Stage stage) {
		final VBox vbox = new VBox();
		 if (a==0){
       Scene scene = new Scene(new Group());
       
       stage.setTitle("Lekarze");
       stage.setWidth(1500);
       stage.setHeight(1000);

       final Label label = new Label("Lista lekarzy");
       label.setFont(new Font("Arial", 20));

       table1.setEditable(true);
////////////////////////Kolumny i mo¿liwe zmiany///////////////
       TableColumn kolumna_imie = new TableColumn("Imie");
       kolumna_imie.setMinWidth(50);
       kolumna_imie.setCellValueFactory(
               new PropertyValueFactory<Lekarz, String>("imie"));
       //Edycja pola Imie
       kolumna_imie.setCellFactory(TextFieldTableCell.forTableColumn());
       kolumna_imie.setOnEditCommit(
           new EventHandler<CellEditEvent<Lekarz, String>>() {
               @Override
               public void handle(CellEditEvent<Lekarz, String> t) {
               	
               	BasicDBObject newDocument = new BasicDBObject();
               	newDocument.append("$set", new BasicDBObject().append("imie", t.getNewValue()));

               	BasicDBObject searchQuery = new BasicDBObject().append("_id",((Lekarz) t.getTableView().getItems().get(
                           t.getTablePosition().getRow())
                           ).getId_lekarza());

               	kolekcja_lekarze.update(searchQuery, newDocument);
               	
               	((Lekarz) t.getTableView().getItems().get(
                       t.getTablePosition().getRow())
                       ).setImie(t.getNewValue());
               }
           });
       ///Nazwisko lekarza
       TableColumn kolumna_nazwisko = new TableColumn("Nazwisko");
       kolumna_nazwisko.setMinWidth(50);
       kolumna_nazwisko.setCellValueFactory(
               new PropertyValueFactory<Lekarz, String>("nazwisko"));
       //Edycja pole nazwisko
       kolumna_nazwisko.setCellFactory(TextFieldTableCell.forTableColumn());
       kolumna_nazwisko.setOnEditCommit(
           new EventHandler<CellEditEvent<Lekarz, String>>() {
               @Override
               public void handle(CellEditEvent<Lekarz, String> t) {
                   
               	BasicDBObject newDocument = new BasicDBObject();
               	newDocument.append("$set", new BasicDBObject().append("nazwisko", t.getNewValue()));

               	BasicDBObject searchQuery = new BasicDBObject().append("_id",((Lekarz) t.getTableView().getItems().get(
                           t.getTablePosition().getRow())
                           ).getId_lekarza());

               	kolekcja_lekarze.update(searchQuery, newDocument);
               	
               	
               	
               	
               	((Lekarz) t.getTableView().getItems().get(
                       t.getTablePosition().getRow())
                       ).setNazwisko(t.getNewValue());
               }
           }
       );
       TableColumn kolumna_miasto = new TableColumn("Miasto");
       kolumna_miasto.setMinWidth(50);
       kolumna_miasto.setCellValueFactory(
               new PropertyValueFactory<Lekarz, String>("miasto"));
       //Edycja pole miasto
       kolumna_miasto.setCellFactory(TextFieldTableCell.forTableColumn());
       kolumna_miasto.setOnEditCommit(
           new EventHandler<CellEditEvent<Lekarz, String>>() {
               @Override
               public void handle(CellEditEvent<Lekarz, String> t) {
               	BasicDBObject newDocument = new BasicDBObject();
               	newDocument.append("$set", new BasicDBObject().append("miasto", t.getNewValue()));

               	BasicDBObject searchQuery = new BasicDBObject().append("_id",((Lekarz) t.getTableView().getItems().get(
                           t.getTablePosition().getRow())
                           ).getId_lekarza());

               	kolekcja_lekarze.update(searchQuery, newDocument);
               	
               	
               	
               	
               	((Lekarz) t.getTableView().getItems().get(
                       t.getTablePosition().getRow())
                       ).setMiasto(t.getNewValue());
               }
           }
       );
       //Kolumna pesel
       TableColumn kolumna_pesel = new TableColumn("Pesel");
       kolumna_pesel.setMinWidth(50);
       kolumna_pesel.setCellValueFactory(
               new PropertyValueFactory<Lekarz, String>("pesel"));
       //Edycja pole pesel
       kolumna_pesel.setCellFactory(TextFieldTableCell.forTableColumn());
       kolumna_pesel.setOnEditCommit(
           new EventHandler<CellEditEvent<Lekarz, String>>() {
               @Override
               public void handle(CellEditEvent<Lekarz, String> t) {
                   
               	BasicDBObject newDocument = new BasicDBObject();
               	newDocument.append("$set", new BasicDBObject().append("pesel", t.getNewValue()));

               	BasicDBObject searchQuery = new BasicDBObject().append("_id",((Lekarz) t.getTableView().getItems().get(
                           t.getTablePosition().getRow())
                           ).getId_lekarza());

               	kolekcja_lekarze.update(searchQuery, newDocument);
               	
               	
               	
               	
               	((Lekarz) t.getTableView().getItems().get(
                       t.getTablePosition().getRow())
                       ).setPesel(t.getNewValue());
               }
           }
       );
       //Kolumna ulica//
       TableColumn kolumna_ulica = new TableColumn("Ulica");
       kolumna_ulica.setMinWidth(200);
       kolumna_ulica.setCellValueFactory(
               new PropertyValueFactory<Lekarz, String>("ulica"));
       //Edycja pole ulica
       kolumna_ulica.setCellFactory(TextFieldTableCell.forTableColumn());
       kolumna_ulica.setOnEditCommit(
           new EventHandler<CellEditEvent<Lekarz, String>>() {
               @Override
               public void handle(CellEditEvent<Lekarz, String> t) {
                   
               	BasicDBObject newDocument = new BasicDBObject();
               	newDocument.append("$set", new BasicDBObject().append("ulica", t.getNewValue()));

               	BasicDBObject searchQuery = new BasicDBObject().append("_id",((Lekarz) t.getTableView().getItems().get(
                           t.getTablePosition().getRow())
                           ).getId_lekarza());

               	kolekcja_lekarze.update(searchQuery, newDocument);
               	
               	
               	
               	((Lekarz) t.getTableView().getItems().get(
                       t.getTablePosition().getRow())
                       ).setUlica(t.getNewValue());
               }
           }
       );
       //ID lekarza
       TableColumn kolumna_id_lekarza = new TableColumn("ID lekarza");
       kolumna_id_lekarza.setMinWidth(200);
       kolumna_id_lekarza.setCellValueFactory(
               new PropertyValueFactory<Lekarz, String>("id_lekarza"));
       //Edycja pole id_lekarza
       kolumna_id_lekarza.setCellFactory(TextFieldTableCell.forTableColumn());
       kolumna_id_lekarza.setOnEditCommit(
           new EventHandler<CellEditEvent<Lekarz, String>>() {
               @Override
               public void handle(CellEditEvent<Lekarz, String> t) {
               	BasicDBObject newDocument = new BasicDBObject();
               	newDocument.append("$set", new BasicDBObject().append("id_lekarza", t.getNewValue()));

               	BasicDBObject searchQuery = new BasicDBObject().append("_id",((Lekarz) t.getTableView().getItems().get(
                           t.getTablePosition().getRow())
                           ).getId_lekarza());

               	kolekcja_lekarze.update(searchQuery, newDocument);
               	
               	
               	
               	
               	((Lekarz) t.getTableView().getItems().get(
                       t.getTablePosition().getRow())
                       ).setId_lekarza(t.getNewValue());
               }
           }
       );
       //Numer domu lekarza
       TableColumn kolumna_numer_domu = new TableColumn("Numer domu");
       kolumna_numer_domu.setMinWidth(200);
       kolumna_numer_domu.setCellValueFactory(
               new PropertyValueFactory<Lekarz, String>("numer_domu"));
       //Edycja pole numer_domu
       kolumna_numer_domu.setCellFactory(TextFieldTableCell.forTableColumn());
       kolumna_numer_domu.setOnEditCommit(
           new EventHandler<CellEditEvent<Lekarz, String>>() {
               @Override
               public void handle(CellEditEvent<Lekarz, String> t) {
               	BasicDBObject newDocument = new BasicDBObject();
               	newDocument.append("$set", new BasicDBObject().append("numer_domu", t.getNewValue()));

               	BasicDBObject searchQuery = new BasicDBObject().append("_id",((Lekarz) t.getTableView().getItems().get(
                           t.getTablePosition().getRow())
                           ).getNumer_domu());

               	kolekcja_lekarze.update(searchQuery, newDocument);
               	
               	
               	
               	
               	((Lekarz) t.getTableView().getItems().get(
                       t.getTablePosition().getRow())
                       ).setNumer_domu(t.getNewValue());
               }
           }
       );
       //Sala lekarza
       TableColumn kolumna_sala = new TableColumn("Sala");
       kolumna_sala.setMinWidth(200);
       kolumna_sala.setCellValueFactory(
               new PropertyValueFactory<Lekarz, String>("sala"));
       //Edycja pole numer_domu
       kolumna_sala.setCellFactory(TextFieldTableCell.forTableColumn());
       kolumna_sala.setOnEditCommit(
           new EventHandler<CellEditEvent<Lekarz, String>>() {
               @Override
               public void handle(CellEditEvent<Lekarz, String> t) {
               	BasicDBObject newDocument = new BasicDBObject();
               	newDocument.append("$set", new BasicDBObject().append("sala", t.getNewValue()));

               	BasicDBObject searchQuery = new BasicDBObject().append("_id",((Lekarz) t.getTableView().getItems().get(
                           t.getTablePosition().getRow())
                           ).getSala());

               	kolekcja_lekarze.update(searchQuery, newDocument);
               	
               	
               	
               	
               	((Lekarz) t.getTableView().getItems().get(
                       t.getTablePosition().getRow())
                       ).setSala(t.getNewValue());
               }
           }
       );
       //Sala lekarza
       TableColumn kolumna_specjalizacja = new TableColumn("Specjalizacja");
       kolumna_specjalizacja.setMinWidth(200);
       kolumna_specjalizacja.setCellValueFactory(
               new PropertyValueFactory<Lekarz, String>("specjalizacja"));
       //Edycja pole numer_domu
       kolumna_specjalizacja.setCellFactory(TextFieldTableCell.forTableColumn());
       kolumna_specjalizacja.setOnEditCommit(
           new EventHandler<CellEditEvent<Lekarz, String>>() {
               @Override
               public void handle(CellEditEvent<Lekarz, String> t) {
               	BasicDBObject newDocument = new BasicDBObject();
               	newDocument.append("$set", new BasicDBObject().append("specjalizacja", t.getNewValue()));

               	BasicDBObject searchQuery = new BasicDBObject().append("_id",((Lekarz) t.getTableView().getItems().get(
                           t.getTablePosition().getRow())
                           ).getSpecjalizacja());

               	kolekcja_lekarze.update(searchQuery, newDocument);
               	
               	
               	
               	
               	((Lekarz) t.getTableView().getItems().get(
                       t.getTablePosition().getRow())
                       ).setSpecjalizacja(t.getNewValue());
               }
           }
       );
       
       ////Odczyt z bazy do obiektow person
       try{
    	   cursor = kolekcja_lekarze.find();
		 	while(cursor.hasNext())
		 	{
		 		DBObject obecny_dokument = cursor.next();
		 		
		 		dane_lekarzy.add(new Lekarz((String)obecny_dokument.get("imie"), (String)obecny_dokument.get("nazwisko"), (String)obecny_dokument.get("pesel"),
		 				(String)obecny_dokument.get("miasto"), (String)obecny_dokument.get("ulica"), (String)obecny_dokument.get("numer_domu"),(String)obecny_dokument.get("sala"),
		 				(String)obecny_dokument.get("specjalizacja"), (String)obecny_dokument.get("_id")));
		 		

		 	}
       }finally{
		 		
		 		cursor.close();
		 	}
       table1.setItems(dane_lekarzy);
       //System.out.println(dane_pacjentow);
       //table.setItems(dane_pacjentow);

       table1.getColumns().addAll(kolumna_imie, kolumna_nazwisko, kolumna_pesel, kolumna_miasto, kolumna_ulica, kolumna_numer_domu, kolumna_sala, kolumna_specjalizacja, kolumna_id_lekarza);

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
       final TextField addNumer_domu = new TextField();
       addNumer_domu.setMaxWidth(kolumna_numer_domu.getPrefWidth());
       addNumer_domu.setPromptText("Numer domu");
       final TextField addSala = new TextField();
       addSala.setMaxWidth(kolumna_numer_domu.getPrefWidth());
       addSala.setPromptText("Sala");
       
       final TextField addSpecjalizacja = new TextField();
       addSpecjalizacja.setMaxWidth(kolumna_specjalizacja.getPrefWidth());
       addSpecjalizacja.setPromptText("Specjalizacja");
       final TextField addId_lekarza = new TextField();
       addId_lekarza.setMaxWidth(kolumna_id_lekarza.getPrefWidth());
       addId_lekarza.setPromptText("ID lekarza");
       
       final Button addButton = new Button("Add");
       addButton.setOnAction(new EventHandler<ActionEvent>() {
       	 BasicDBObject dodaj;
           @Override  
           public void handle(ActionEvent e) throws  DuplicateKeyException  {
           	
           
           		System.out.println("XD");
           		dane_lekarzy.add(new Lekarz(
                       addImie.getText(),
                       addNazwisko.getText(),
                       addPesel.getText(),
                       addMiasto.getText(),
                       addUlica.getText(),
                       addNumer_domu.getText(),
                       addSala.getText(),
                       addSpecjalizacja.getText(),
                       addId_lekarza.getText()
                       ));
               dodaj = new BasicDBObject("imie", addImie.getText()).append("nazwisko", addNazwisko.getText())
               		.append("pesel",  addPesel.getText()).append("miasto", addMiasto.getText()).append("ulica", addUlica.getText())
               		.append("numer_domu", addNumer_domu.getText()).append("sala", addSala.getText())
               		.append("specjalizacja", addSpecjalizacja.getText()).append("_id", addId_lekarza.getText());
              kolekcja_lekarze.insert(dodaj);
               addImie.clear();
               addNazwisko.clear();
               addPesel.clear();
               addMiasto.clear();
               addUlica.clear();
               addNumer_domu.clear();
               addSala.clear();
               addSpecjalizacja.clear();
               addId_lekarza.clear();
               
           }
       });
       
       //Pobieramy dzieci, czyli elementy
       hb.getChildren().addAll(addId_lekarza, addImie, addNazwisko, addPesel, addMiasto, addUlica, addNumer_domu, addSala, addSpecjalizacja, addButton);
       hb.setSpacing(5);
       vbox.setSpacing(5);
       vbox.setPadding(new Insets(10, 0, 0, 10));
       //Hb jest dzieciaczkiem (uruchomiony w ramach rodzica, zatem hb podaæ jako argument)
       vbox.getChildren().addAll(label, table1, hb);

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
        btn2.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
            	//mongoClient.getDatabaseNames().forEach(System.out::println);

            	//list.clear();
            	lekarze(stages);

            }
            
        });
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
    public static class Lekarz{
    
    	private final SimpleStringProperty imie;
        private final SimpleStringProperty nazwisko;
        private final SimpleStringProperty pesel;
        private final SimpleStringProperty miasto;
        private final SimpleStringProperty ulica;
        private final SimpleStringProperty numer_domu;
        private final SimpleStringProperty sala;
        private final SimpleStringProperty id_lekarza;
        private final SimpleStringProperty specjalizacja;
    	
        private Lekarz(String imie, String nazwisko, String pesel, String miasto, String ulica, String numer_domu ,
        		String sala , String specjalizacja,  String id_lekarza ) {
            this.imie = new SimpleStringProperty(imie);
            this.nazwisko = new SimpleStringProperty(nazwisko);
            this.pesel = new SimpleStringProperty(pesel);
            this.miasto = new SimpleStringProperty(miasto);
            this.ulica = new SimpleStringProperty(ulica);
            this.numer_domu = new SimpleStringProperty(numer_domu);
            this.id_lekarza = new SimpleStringProperty(id_lekarza);
            this.sala = new SimpleStringProperty(sala);
            this.specjalizacja = new SimpleStringProperty(specjalizacja);
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
        public String getNumer_domu() {
            return numer_domu.get();
        }
 
        public void setNumer_domu(String fName) {
            numer_domu.set(fName);
        }
        public String getSala() {
            return sala.get();
        }
 
        public void setSala(String fName) {
            sala.set(fName);
        }
        public String getId_lekarza() {
            return id_lekarza.get();
        }
 
        public void setId_lekarza(String fName) {
            id_lekarza.set(fName);
        }
        public String getSpecjalizacja() {
            return specjalizacja.get();
        }
 
        public void setSpecjalizacja(String fName) {
            specjalizacja.set(fName);
        }
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
        private Pacjent(String imie, String nazwisko, String pesel, String miasto, String ulica,
        		String telefon, String id_pacjenta, String id_lekarza) {
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