import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.layout.*;
import javafx.scene.input.KeyEvent;
import javafx.geometry.*;
import java.util.*;
import javafx.scene.control.*;
import javafx.application.Platform;
import java.util.concurrent.TimeUnit;

import java.text.DecimalFormat;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.FileInputStream; 
import java.io.FileNotFoundException; 


public class UltimateMazeDriver extends Application{
   Stage pStage = new Stage();
   
   int privVariable = 10;
   
   Label headerLevelLabel;
   Label headerMovesLabel;
   Label headerWallMovesLabel;
   Label timerLabel;
   Label footerLabel;
   HBox headerBox;
   HBox footerBox;
      
   DecimalFormat fmt = new DecimalFormat("0.00");
   Random randNum = new Random();
   LevelOne nextLevelOne;
   
   homePageScene sceneGetter = new homePageScene();
   Scene scene = sceneGetter.getScene();
   
   
   public void start(Stage primaryStage){
      
      headerLevelLabel = new Label("xx");
      headerMovesLabel = new Label("xx");
      timerLabel = new Label("xx");
      headerWallMovesLabel = new Label("xx");
      headerBox = new HBox(headerLevelLabel, headerMovesLabel,headerWallMovesLabel, timerLabel);
      headerBox.setSpacing(25);
      headerBox.setAlignment(Pos.CENTER);
      footerBox = new HBox();
      
      //setting style components for the labels
      headerLevelLabel.setStyle("-fx-text-fill: blue;  -fx-font: 40px Georgia; ");
      headerMovesLabel.setStyle("-fx-text-fill: blue;  -fx-font: 40px Georgia; ");
      headerWallMovesLabel.setStyle("-fx-text-fill: blue; -fx-font: 40px Georgia; ");
      timerLabel.setStyle("-fx-text-fill: blue;  -fx-font: 40px Georgia; ");
      
      sceneGetter.getStartButton().setOnAction(new ButtonHandler());
      pStage.setTitle("The Ultimate Maze");
      pStage.setFullScreenExitHint("------Welcome to the Ultimate Maze------");
      pStage.setScene(scene);
      pStage.show();
      
      Button resetGameBtn = new Button("Reset");
        resetGameBtn.setStyle( "-fx-background-color: black; -fx-border-color: red; -fx-border-radius: 5; -fx-text-fill: white; -fx-font-size : 20px;" );        
        resetGameBtn.setOnAction(new EventHandler<ActionEvent>(){
           @Override
           public void handle(ActionEvent e){
            //GameDetails.isTimeOver = true;
            GameDetails.resetValues();
           }
        });                    
      footerBox.getChildren().add(resetGameBtn);  
      
   }
   
   public static void main(String[] args)
    {
        launch(args);
        
    }
    
    public Stage getStage(){
    
      return this.pStage;
    }
    
    /*public void setTimerLabel(String text){
      timerLabel.setText(text);
    }*/
    
   
    private class ButtonHandler implements EventHandler<ActionEvent>
    {
      
       int gameWidth = 5;
       int gameHeight = 5;
       int[] start = new int[]{randNum.nextInt(gameWidth), randNum.nextInt(gameHeight)};
       int[] end = new int[]{randNum.nextInt(gameWidth), randNum.nextInt(gameHeight)};
       
       public void handle(ActionEvent e){
        Thread timerThread = new Thread(new Runnable(){
         @Override
         public void run(){
            //long currentTime = System.currentTimeMillis();
            //GameDetails.secondspassed = 0;
            try{
             while(GameDetails.timeLeft-GameDetails.secondspassed > 0)
             {
              TimeUnit.MILLISECONDS.sleep(100);
              GameDetails.secondspassed+= 0.10;
              //System.out.println("time left: "+(GameDetails.timeLeft-GameDetails.secondspassed));
              //GameDetails.setTimerLabel((10-secondspassed)+"");
              final double secondsTemp = GameDetails.secondspassed;
              Platform.runLater(new Runnable() {
               @Override
               public void run() {
                  
                  headerLevelLabel.setText("Level: "+(GameDetails.levelNumber)+"");
                  timerLabel.setText("Time Left: "+(fmt.format(GameDetails.timeLeft-secondsTemp))+""); //GameDetails.timeLeft-
                  headerMovesLabel.setText("Moves: "+(GameDetails.numOfMoves)+"");
                  headerWallMovesLabel.setText("Wall: "+(GameDetails.numOfWallMoves)+"");
                }
               });
             if(GameDetails.timeLeft-GameDetails.secondspassed<5){
                  headerLevelLabel.setStyle("-fx-text-fill: red;  -fx-font: 50px Georgia; ");
                  headerMovesLabel.setStyle("-fx-text-fill: red;  -fx-font: 50px Georgia; ");
                  headerWallMovesLabel.setStyle("-fx-text-fill: red; -fx-font: 50px Georgia; ");
                  timerLabel.setStyle("-fx-text-fill: red;  -fx-font: 50px Georgia; ");
             }else{
                  headerLevelLabel.setStyle("-fx-text-fill: blue;  -fx-font: 50px Georgia; ");
                  headerMovesLabel.setStyle("-fx-text-fill: blue;  -fx-font: 50px Georgia; ");
                  headerWallMovesLabel.setStyle("-fx-text-fill: blue; -fx-font: 50px Georgia; ");
                  timerLabel.setStyle("-fx-text-fill: blue;  -fx-font: 50px Georgia; ");
             
             }
             
             }
             GameDetails.isTimeOver = true;
             System.out.println("\nSetting istimeover as true");
            }
            catch(InterruptedException e){
               e.printStackTrace();
            }     
            }
        });
         
        if(e.getSource().equals(sceneGetter.getStartButton())){
                
        BorderPane root = new BorderPane();
        root.setTop(headerBox);
        root.setBottom(footerBox);
        
        
        
        Thread gameThread = new Thread(new Runnable(){
         @Override
         public void run(){
            //long currentTime = System.currentTimeMillis();
            //double secondspassed = 0;
            try{
              while(true){
               System.out.print(GameDetails.isTimeOver);
               
               if(GameDetails.isTimeOver){
                  
                  //change scene to game over scene.
                  try{
                  Image image = new Image(new FileInputStream("images/bg.png"));
                  ImageView gameOverView = new ImageView(image);
                   
      
                  //Setting the position of the image 
                  gameOverView.setX(0); 
                  gameOverView.setY(0);
                  gameOverView.setFitHeight(800); 
                  gameOverView.setFitWidth(1100);
                  
                  BorderPane scoreDetail = new BorderPane();
                  Label gameOverLabel = new Label("Game Over");
                  Label levelsCompletedLabel = new Label("Levels Completed: "+GameDetails.levelNumber);
                  Label totalMovesLabel = new Label("Total Moves: "+GameDetails.numOfMoves);
                  double accuracy = GameDetails.getAccuracy();
                  
                  Label accuracyLabel = new Label("Accuracy : "+fmt.format(accuracy)+"%");
                  
                  //levelsCompleted.setStyle("-fx-text-fill: white; -fx-font:50px;");
                  Button restartBtn = new Button("Play Again");
                  
                  //restartBtn.setStyle("-fx-text-fill: white;  -fx-font: 40px Georgia; ");
                  gameOverLabel.setStyle("-fx-text-fill: white;  -fx-font: 60px Georgia; ");
                  levelsCompletedLabel.setStyle("-fx-text-fill: white;  -fx-font: 40px Georgia; ");
                  totalMovesLabel.setStyle("-fx-text-fill: white;  -fx-font: 40px Georgia; ");
                  accuracyLabel.setStyle("-fx-text-fill: white;  -fx-font: 40px Georgia; ");
                  levelsCompletedLabel.setAlignment(Pos.CENTER);
                  restartBtn.setStyle( "-fx-background-color: black; -fx-border-color: red; -fx-border-radius: 5; -fx-text-fill: white; -fx-font-size : 60px;" );
                  restartBtn.setAlignment(Pos.CENTER);
                  restartBtn.setOnAction(new EventHandler<ActionEvent>(){
                     @Override
                     public void handle(ActionEvent e){
                        GameDetails.resetValues();
                        nextLevelOne = null;
                        sceneGetter.getStartButton().setOnAction(new ButtonHandler());
                        pStage.setScene(scene);
                     }
                  });
                  
                  //HBox score = new HBox(levelsCompletedLabel);
                  HBox bottomBox0 = new HBox(gameOverLabel );
                  HBox bottomBox1 = new HBox( levelsCompletedLabel, totalMovesLabel,accuracyLabel );
                  HBox bottomBox2 = new HBox(levelsCompletedLabel );
                  HBox bottomBox3 = new HBox(restartBtn );
                  VBox displayDetails = new VBox(bottomBox0, bottomBox1,bottomBox2,bottomBox3);
                  displayDetails.setAlignment(Pos.CENTER);
                  
                  bottomBox0.setAlignment(Pos.CENTER);
                  bottomBox0.setSpacing(20);
                  displayDetails.setSpacing(20);
                  
                  bottomBox1.setAlignment(Pos.CENTER);
                  bottomBox1.setSpacing(20);
                  
                  bottomBox2.setAlignment(Pos.CENTER);
                  bottomBox2.setSpacing(20);
                  
                  bottomBox3.setAlignment(Pos.CENTER);
                  bottomBox3.setSpacing(20);
                  
                  scoreDetail.setCenter(displayDetails);
                  
                  StackPane temp = new StackPane(gameOverView, scoreDetail);
                  
                  Scene endGame = new Scene(temp,1100,800);
                  
                  
                  Platform.runLater(new Runnable() {
                        @Override
                        public void run() { 
                           pStage.setScene(endGame);
                           nextLevelOne= null;
                           //pStage.setFullScreen(true);
                           System.out.print("game ended lets go");
                           }
                           
                    }); 
                    }catch(Exception e){
                     e.printStackTrace();
                    }
                    break;
                 }
                else if (!GameDetails.isTimeOver && GameDetails.isGameOver){
                    System.out.println("Check 300000");    
                    
                    if(GameDetails.levelNumber ==1){
                     nextLevelOne = new LevelOne(new int[]{0,0}, new int[]{4,4}, GameDetails.gameWidth, GameDetails.gameHeight);
                    }
                   GameDetails.isGameOver = false;
                     
                       
                    Platform.runLater(new Runnable() {
                     @Override
                     public void run() {
                          //creating a button and updating the footerbox
                          
                          BorderPane rootNew = new BorderPane();
                          rootNew.setStyle("-fx-background-color:"+GameDetails.backgroundRoot);
                          rootNew.setTop(headerBox);
                          rootNew.setBottom(footerBox);
                          rootNew.setCenter(nextLevelOne);
                                                    
                          
                          Scene sceneNew = new Scene(rootNew,1100,800);//,1500,1400
                          sceneNew.setOnKeyPressed(nextLevelOne.getKeyBoardHandler());

                          pStage.setScene(sceneNew);
                          pStage.setFullScreenExitHint("");
                          
                        }
                     });
                     
                     GameDetails.gameWidth += 5;
                     GameDetails.gameHeight += 5;
                     start = new int[]{randNum.nextInt(GameDetails.gameWidth), randNum.nextInt(GameDetails.gameHeight)};
                     end = new int[]{randNum.nextInt(GameDetails.gameWidth), randNum.nextInt(GameDetails.gameHeight)};
                     System.out.print("StartX: "+start[0]+" StartY: "+start[1]);
                     nextLevelOne = new LevelOne(start, end, GameDetails.gameWidth, GameDetails.gameHeight); //StackPane    
               
               
               }
               
              }
             }//end of try
              
            catch(Exception e){
               e.printStackTrace();
            }     
            }

        
        });
        
        gameThread.start();
        timerThread.start();
        
        }else{ 
        System.out.print("button has been pressed");
        }
       } 
    }
    
   
}







/*
Platform.runLater(new Runnable() {
                        @Override
                        public void run() { 
                           pStage.setScene(endGame);
                           System.out.print("game ended lets go");
                           Media buzzer = new Media(getClass().getResource("backgroundMusic.mp3").toExternalForm());
                           MediaPlayer mediaPlayer = new MediaPlayer(buzzer);
                           mediaPlayer.play();
                           }
                           
                    });*/