import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.*;
import java.util.concurrent.TimeUnit;
import javafx.geometry.*;

//import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

//import java.util.concurrent.TimeUnit;

public class LevelOne extends StackPane {//implements Runnable
   //instance variables
   RouteGenerator routeGen = new RouteGenerator();
   Label headerLevelLabel;
   Label headerMovesLabel;
   Label headerWallMovesLabel;
   Label footerLabel;
   Label timerLabel;
   HBox headerBox;
   HBox footerBox;
   boolean isGameOver = false;
   ArrayList<ArrayList<Canvas>> canvasList ;
   ArrayList<int[]> wallList = new ArrayList<>();
   ArrayList<int[]> route;
   int currentX;
   int currentY;
   int gridWidth;
   int gridHeight;
   int[] start;
   int[] end;
   double canvasWidth;
   double canvasHeight;
    //Stage pStage;
    //private Rectangle rect;
    long displayMinutes=0;
    long starttime=System.currentTimeMillis();
    
    //TO DO: connect RouteGenerator to this now.
   //Constructor
   public LevelOne( int[] startPos, int[] endPos, int gW, int gH){
     //initializinf variables
     //System.out.print("Constructor of levelOne has been called");
     this.gridWidth = gW;
     this.gridHeight = gH;
     this.start = startPos;
     this.end = endPos;
     this.currentX = start[0];
     this.currentY = start[1];
     //createWallList();
     this.canvasList = new ArrayList<>();
     createCanvasList(gridHeight);
     GridPane gridPane = new GridPane();
     
     route = routeGen.getGrid(startPos, endPos, gridWidth, gridHeight, 6 );
     
     canvasWidth = 700/gridWidth;
     canvasHeight = 700/gridHeight;
     
     /*headerLevelLabel = new Label("Level: NA");
     headerMovesLabel = new Label("Moves: NA");
     headerWallMovesLabel = new Label("Wall Hits: NA");
     timerLabel = new Label(" **:*** ");
     
     
     footerLabel = new Label("Home \t\t Restart");
     headerBox = new HBox(GameDetails.headerLevelLabel, GameDetails.headerMovesLabel, GameDetails.headerWallMovesLabel, GameDetails.timerLabel);
     footerBox = new HBox(footerLabel);
     */
     
     //Coloring all grid elements
     for(int i = 0; i < gridWidth ; i++){
         for (int j = 0; j < gridHeight ; j++){
              Canvas canvas = new Canvas(canvasWidth, canvasHeight);
              if (i == currentX && j == currentY){
               reset(canvas, Color.GRAY);
              }
              else{
               reset(canvas, (Color)GameDetails.WallColor);
              }
              gridPane.add(canvas,i,j);
              canvasList.get(j).add(canvas);
           }
       }   
              
      //coloring elements according to route generated
      for (int i = 0; i < route.size() ;i++){
      //System.out.println(route.get(i)[0]+" "+route.get(i)[1]+"AL");
      int y = route.get(i)[1];
      int x = route.get(i)[0];
      if (x == currentX && y == currentY){
      //reset( canvasList.get(y).get(x) , Color.BLUE);
      }else if(x == end[0] && y == end[1]){
         reset( canvasList.get(y).get(x) , Color.YELLOW); //these two colors will be stored in variables
      }else{
         reset( canvasList.get(y).get(x) , (Color)GameDetails.NonWallColor);

      }
      }
        
     //this.setTop(headerBox);
     //this.setCenter(gridPane);
     //this.setBottom(footerBox);
     gridPane.setAlignment(Pos.CENTER);
     this.getChildren().addAll(gridPane);
   
   
   }//end of constructor
   
   public boolean getIsGameOver(){
      return isGameOver;
   }
   
   public Label getTimerLabel(){
      return this.timerLabel;
      }
      
   /*public void setTimerLabel(String timer){
      GameDetails.setTimerLabel("\t\thi\t\t");
      //GameDetails.headerLevelLabel.setText(timer);
      }*/
   public boolean isNodeWall(int X, int Y){
      for (int[] node: route){
         if(node[0]==X && node[1]==Y){
         return false;
         }
      }
      return true;
   }
   
   
   public ArrayList<ArrayList<Canvas>> getCanvasList(){
      return canvasList;
   }
   
   /*
   public ArrayList<int[]> getWallList(){
   return wallList;
   }*/
   
      
   public void createCanvasList(int height){
    for (int i = 0; i<height ; i++){
      canvasList.add(new ArrayList<Canvas>());
    }
   }
   
   private void reset(Canvas canvas, Color color) {
     GraphicsContext gc = canvas.getGraphicsContext2D();
     gc.setFill(color);
     gc.fillRect(0,0 , canvas.getWidth(), canvas.getHeight());   
    }

   public KeyBoardHandler getKeyBoardHandler(){
      KeyBoardHandler temp = new KeyBoardHandler();
      return temp;
     
   }
   
   
   
   
   /*public void updateScreen(){ //there is a nullPointer eception error here
      System.out.println("calling the updateScreen method");
      headerLevelLabel.setText("Level: "+GameDetails.levelNumber); //+GameDetails.levelNumber
      headerMovesLabel.setText("Moves: "+GameDetails.numOfMoves); //+GameDetails.numOfMoves
      headerWallMovesLabel.setText("Wall Hits: "+ GameDetails.numOfWallMoves); //+ GameDetails.numOfWallMoves
      //updateTimer();
   }*/
 
   private class KeyBoardHandler implements EventHandler<KeyEvent>{
   
   @Override
   public void handle(KeyEvent event){
     if(!isGameOver){
     
     try{
     
     if(event.getCode().toString().equals("RIGHT")){
      if(!isNodeWall(currentX+1,currentY)){
         Canvas temp = canvasList.get(currentY).get(currentX+1);
         reset(temp, Color.GRAY);
         currentX+=1;
         GameDetails.numOfMoves+=1;
         //updateScreen();
      }else{
         throw new Exception("Wall has been hit");
      }
      
     }
     else if(event.getCode().toString().equals("DOWN")){
      if(!isNodeWall(currentX,currentY+1)){
         Canvas temp = canvasList.get(currentY+1).get(currentX);
         reset(temp, Color.GRAY);
         currentY+=1;
         GameDetails.numOfMoves+=1;
         //updateScreen();
      }else{
      
      throw new Exception("Wall has been hit");
      }
           }
     else if(event.getCode().toString().equals("LEFT")){
      if(!isNodeWall(currentX-1, currentY )){
         Canvas temp = canvasList.get(currentY).get(currentX-1);
         reset(temp, Color.GRAY);
         currentX-=1;
         GameDetails.numOfMoves+=1;
         //updateScreen();
      }else{
         throw new Exception("Wall has been hit");
      }
      
     }
     else if(event.getCode().toString().equals("UP")){
      if(!isNodeWall(currentX, currentY-1)){
      Canvas temp = canvasList.get(currentY-1).get(currentX);
      reset(temp, Color.GRAY);
      currentY-=1;
      GameDetails.numOfMoves+=1;
      //System.out.print("Moved up");
      //updateScreen();
      }else{
      //GameDetails.headerLevelLabel.setText("You are hitting the wall brooooo");
      throw new Exception("Wall has been hit");
      }
      
     }//start of diagonal moves
     else if(event.getCode().toString().equals("Q")){
      if(!isNodeWall(currentX-1, currentY-1)){
      Canvas temp = canvasList.get(currentY-1).get(currentX-1);
      reset(temp, Color.GRAY);
      currentY-=1;
      currentX-=1;
      GameDetails.numOfMoves+=1;
      //System.out.print("Moved top left");
      //updateScreen();
      }else{
      //GameDetails.headerLevelLabel.setText("You are hitting the wall brooooo");
      throw new Exception("Wall has been hit");
      }
      
     }
     else if(event.getCode().toString().equals("W")){
      if(!isNodeWall(currentX+1, currentY-1)){
      Canvas temp = canvasList.get(currentY-1).get(currentX+1);
      reset(temp, Color.GRAY);
      currentY-=1;
      currentX+=1;
      GameDetails.numOfMoves+=1;
      //System.out.print("Moved top left");
      //updateScreen();
      }else{
      //GameDetails.headerLevelLabel.setText("You are hitting the wall brooooo");
      throw new Exception("Wall has been hit");
      }
      
     }
     else if(event.getCode().toString().equals("A")){
      if(!isNodeWall(currentX-1, currentY+1)){
      Canvas temp = canvasList.get(currentY+1).get(currentX-1);
      reset(temp, Color.GRAY);
      currentY+=1;
      currentX-=1;
      GameDetails.numOfMoves+=1;
      //System.out.print("Moved top left");
      //updateScreen();
      }else{
      //GameDetails.headerLevelLabel.setText("You are hitting the wall brooooo");
      throw new Exception("Wall has been hit");
      }
      
     }
     else if(event.getCode().toString().equals("S")){
      if(!isNodeWall(currentX+1, currentY+1)){
      Canvas temp = canvasList.get(currentY+1).get(currentX+1);
      reset(temp, Color.GRAY);
      currentY+=1;
      currentX+=1;
      GameDetails.numOfMoves+=1;
      //System.out.print("Moved top left");
      //updateScreen();
      }else{
      //GameDetails.headerLevelLabel.setText("You are hitting the wall brooooo");
      throw new Exception("Wall has been hit");
      }
      
     }
     
     }catch(IndexOutOfBoundsException e){
           System.out.print(e+" from line 168 levelOne.java");
     }catch(Exception e){
      System.out.print(e);
      GameDetails.numOfWallMoves+=1;
      //updateScreen();
      //GameDetails.headerLevelLabel.setText("You are hitting the walls bro");
     }
      //System.out.print(currentX+" "+currentY+"\n");
     }
     
     if(currentX == end[0] && currentY == end[1]){
      
      reset(canvasList.get(end[1]).get(end[0]),Color.PINK); 
      if(GameDetails.levelNumber<5)
         GameDetails.timeLeft += GameDetails.levelNumber;
      else if(GameDetails.levelNumber<10)
         GameDetails.timeLeft +=5;
      else
         GameDetails.timeLeft +=3;
      
      GameDetails.levelNumber+=1;    
      isGameOver= true;
      GameDetails.isGameOver = true;
              
     }else if(currentX == 2 && currentY ==3){
      //drawBackground(rect,Color.RED);
     }
         
    }
   }
    
    

}


//Multi threading works but figure out how to use that to display the time simultaneuosly with the game.