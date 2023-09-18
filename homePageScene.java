import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.FileInputStream; 
import java.io.FileNotFoundException; 
import javafx.geometry.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;

public class homePageScene{
   static Button startBtn;
   static StackPane mainPane;
   static BorderPane startMenuPane;
   static Label welcomeLabel;
   static Label detailsBarLabel;
   static Label titleLabel;
   static ComboBox themeComboBox;
   static ComboBox difficultyComboBox;
   
   public homePageScene(){
   
   startBtn = new Button("Start");
   mainPane = new StackPane();
   startMenuPane = new BorderPane();
   welcomeLabel = new Label("\tWelcome to \nThe Ultimate Maze");
   
   themeComboBox = new ComboBox();
   themeComboBox.getItems().addAll("Lights Out","Galaxy","Lava","B&W");
   themeComboBox.setValue("Select Theme");
   themeComboBox.setOnAction(new EventHandler<ActionEvent>(){
      @Override
      public void handle(ActionEvent e){
         if(themeComboBox.getValue().equals("Galaxy")){
            GameDetails.WallColor = Color.BLACK;
            GameDetails.NonWallColor = Color.PINK;
            System.out.println("\nUpdated GameDetails"+ GameDetails.WallColor);
         }else if(themeComboBox.getValue().equals("Lava")){
            GameDetails.WallColor = Color.BLACK;
            GameDetails.NonWallColor = Color.RED;
            System.out.println("\nUpdated GameDetails"+ GameDetails.WallColor);
         }else if(themeComboBox.getValue().equals("B&W")){
            GameDetails.WallColor = Color.BLACK;
            GameDetails.NonWallColor = Color.WHITE;
            System.out.println("\nUpdated GameDetails 123"+ GameDetails.WallColor);
         }
         else if(themeComboBox.getValue().equals("Lights Out")){
            GameDetails.WallColor = Color.BLACK;
            GameDetails.NonWallColor = Color.BLACK;
            GameDetails.backgroundRoot = "black";
            System.out.println("\nUpdated GameDetails"+ GameDetails.WallColor);
         }
         else{ 
            System.out.println("Error 909");
         }
      }
   });
   //detailsBarLabel = new Label("Details Bar");
   //titleLabel = new Label("The Ultimate Maze");
   
   welcomeLabel.setStyle("-fx-text-fill: White; -fx-font : 50px Georgia; -fx-position:center;");
   //titleLabel.setStyle("-fx-text-fill: blue; -fx-font : 50px Georgia");
   //welcomeLabel.setAlignment(Pos.CENTER);
   //titleLabel.setAlignment(Pos.CENTER);

   //detailsBarLabel.setStyle("-fx-text-fill: blue; -fx-font : 100px Georgia");
   
   //pink and black: galaxy
   //red and black: lava
   //
   }
   
   public static Scene getScene(){
      
      
      //Creating an image 
      try{
      Image image = new Image(new FileInputStream("images/bg.png"));  
      
      //Setting the image view 
      ImageView imageView = new ImageView(image); 
      
      //Setting the position of the image 
      imageView.setX(0); 
      imageView.setY(0);
      
      imageView.setFitHeight(800); 
      imageView.setFitWidth(1100);
      
      BorderPane borderPane = new BorderPane();
      startBtn.setStyle( "-fx-background-color: black; -fx-border-color: red; -fx-border-radius: 5; -fx-text-fill: white; -fx-font-size : 60px;" );
      themeComboBox.setStyle( "-fx-background-color: black; -fx-border-color: red; -fx-border-radius: 5; -fx-text-fill: white; -fx-font-size : 40px;");
      startBtn.setMinWidth(100);
      startBtn.setMinHeight(100);
      VBox vbox = new VBox(welcomeLabel);
      vbox.setAlignment(Pos.CENTER);
      vbox.setPadding(new Insets(200,0,0,0));
      startMenuPane.setCenter(vbox);
      VBox options = new VBox(startBtn,themeComboBox);
      options.setSpacing(30);
      options.setAlignment(Pos.CENTER);
      borderPane.setTop(startMenuPane);
      borderPane.setCenter(options);
      mainPane.getChildren().addAll(imageView,borderPane);
      Scene scene = new Scene(mainPane, 1100, 800);
      return scene;
      }catch(Exception e){
         e.printStackTrace();
      }
      return new Scene(new StackPane(new Label("null")),1100,800);
   }
   
   public Button getStartButton(){
      return this.startBtn;
   }
   

}
