import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class GameDetails{
//this is a class this will contain static variables and methods which will be accessed/mutated by the levelOne class
//in order to keep updating the score of the player.

static int numOfMoves = 0 ;
static int numOfWallMoves = 0;
static double timeLeft = 15; //this will probably be a string, or be divided into seconds and miliseconds variables
static double secondspassed = 0;
static int levelNumber = 1;
static boolean isTimeOver=false;
static boolean isGameOver=true;
static int gameWidth= 5;
static int gameHeight= 5;
static String backgroundRoot = "silver";
static Color WallColor= Color.BLACK;
static Color NonWallColor= Color.WHITE ;


//initiializing control components
   /*Label headerLevelLabel = new Label("xx");
    Label headerMovesLabel = new Label("xx");
    Label headerWallMovesLabel = new Label("xx");
    Label timerLabel = new Label("xx");
    Label footerLabel = new Label("xx");
    HBox headerBox = new HBox(GameDetails.headerLevelLabel, GameDetails.headerMovesLabel,GameDetails.headerWallMovesLabel, GameDetails.timerLabel);
    HBox footerBox = new HBox(GameDetails.footerLabel);
 
*/


public static int getNumOfMoves(){
      return numOfMoves;
   }
   
public static int getNumOfWallMoves(){
      return numOfWallMoves;
   }
   
public static double getTimeLeft(){
      return timeLeft;
   }
public static int getLevelNumber(){
      return levelNumber;
   }   

public static void setNumOfMoves(int moves){
   numOfMoves = moves;
}

public static void setNumOfWallMoves(int wallMoves){
   numOfWallMoves = wallMoves;
}

public static void setTimeLeft(double timeLeft){
   timeLeft = timeLeft;
}

public static void setLevelNumber(int levelNumber){
   levelNumber = levelNumber;
}

public static void resetValues(){
    numOfMoves = 0 ;
    numOfWallMoves = 0;
    timeLeft = 15;
    levelNumber = 1;
    isTimeOver=false;
    isGameOver=true;
    gameWidth= 5;
    gameHeight= 5;
    secondspassed = 0;
    //WallColor=null;
    //NonWallColor=null ;
}


public static double getAccuracy(){
   double res = ((double)(numOfMoves-numOfWallMoves))/((double)(numOfMoves+numOfWallMoves));
   return res*100.00;
}
/*
public static void setHeaderLevelLabelText(String text){
      headerLevelLabel = new Label(text);
   }
   
public static void setTimerLabel(String timerLabelText){
   timerLabel.setText(timerLabelText);
   //System.out.print(timerLabelText);
}

public static String getTimerLabel(){
   return timerLabel.getText();
}
*/
}