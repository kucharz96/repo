package application;
import javafx.collections.ObservableList; 

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.geometry.Insets; 
import javafx.scene.control.Label;
import javafx.scene.text.Font;
public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        Button btn2 = new Button();
        Button btn3 = new Button();
        Label label1 = new Label("Przechodnia");
        Pane root = new Pane();
        label1.setFont(new Font("Arial", 38));
        ObservableList list = root.getChildren();
        btn.setText("Pacjenci");
        btn2.setText("Lekarze");
        
        btn3.setText("Exit");
        btn.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
         list.clear();

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
        Scene scene = new Scene(root, 600, 400);

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
 public static void main(String[] args) {
        launch(args);
    }
}