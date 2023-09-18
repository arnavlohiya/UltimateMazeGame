
import java.util.*;
import java.io.*;
import java.util.function.Function;

public class RouteGenerator{
ArrayList<int[]> routeSet = new ArrayList<>();
Set<int[]> wallSet = new HashSet<>();
ArrayList <int[]> grid = new ArrayList<>();
int gridWidth;
int gridHeight;
int[] currentPosition = new int[]{0,0};
int probability = 6;

/*
public static void main(String[] args){
RouteGenerator root = new RouteGenerator();

root.getGrid(new int[]{1,1}, new int[]{50,60}, 100,100,5);
}*/



public ArrayList<int[]> getGrid(int[] startPos, int[] endPos, int gW, int gH, int probability){
   //System.out.println("flag101.00");
   createGrid(gW , gH);
   ArrayList<int[]> route = findRoute(startPos, endPos);
   Random random = new Random();
   //System.out.print("flag101.01");
   //ArrayList<Set<int[]>> toReturn = new ArrayList<>();
   this.probability = Math.abs(probability);
   
   //Adding extra blue spaces arbitrarily
   for(int[] node: grid){
      if(!route.contains(node)){
         //System.out.println("flag101.02");
         int randomNum = random.nextInt(probability);
         if (randomNum == 0 || randomNum == 1 ||  randomNum == 2){
         route.add(node);
         }
      }
   }
   
   //toReturn.add(route);
   //toReturn.add(wallSet);
   return route;
}


public void createGrid(int gridW , int gridH){
   //System.out.println("flag101.10");
   this.gridWidth = gridW;
   this.gridHeight = gridH;
   
   for (int i = 0; i<  gridHeight; i++){
      for (int j= 0; j< gridWidth ; j++){
      this.grid.add(new int[]{j,i});
      }
   }
}

public ArrayList<int[]> findRoute(int[] startPos, int[] endPos){ //, int gridWidthP, int gridHeightP
   //this.gridWidth = gridWidthP;
   //this.gridHeight = gridHeightP;
   //createGrid(gridWidth , gridHeight);
   //System.out.println("flag101.20");
   int[] startPosition = startPos;
   int[] endPosition = endPos;
   routeSet.add(grid.get(startPosition[1]*gridHeight + startPosition[0] )); //add start pos to routeSet
   //System.out.println("added starting Position: "+routeSet.get(0)[0] +" "+ routeSet.get(0)[1]);
   //System.out.print("is start position found? "+routeSet.contains(grid.get( (1)*gridHeight + (1) )));
   //System.out.print(grid.get(startPosition[0]*gridWidth + startPosition[1] )[0]+" mid "+grid.get(startPosition[0]*gridWidth + startPosition[1] )[1]+"checkng");
   while(!(startPosition[0] == endPos[0] && startPosition[1] == endPos[1] )){   
      startPosition = movePosition(startPosition);
      routeSet.add( grid.get(startPosition[1]*gridHeight + startPosition[0]) );
      //System.out.println("flag101.21 Looping to find route "+(startPosition[0] == endPos[0] && startPosition[1] == endPos[1] ));
      }
      //System.out.println("flag101.22 Ending find route Loop ");
      routeSet.add(grid.get(endPosition[1]*gridHeight + endPosition[0] ));
      //System.out.println("flag101.23 ");
   return routeSet;

}

public int[] movePosition(int[] currentPos){
   ArrayList<Integer> moves = new ArrayList<>();
   Random randomNumber = new Random();
   //need to change options available for moves. run four if statements and check if the four 
   //directions have been already added to the routeList or not.
   
   try{
      //System.out.print("inside try of movePOsition");
   if(!(routeSet.contains(grid.get( currentPos[1]*gridHeight + (currentPos[0]+1) ))) && currentPos[0]+1 >= 0 && currentPos[0]+1 < gridWidth ){ //moveRight is available
      moves.add(0);
      //System.out.print("Added right move");
   }
   }catch(Exception e){
      //System.out.println("\ne");
   }
   
   try{
   if(!(routeSet.contains(grid.get( currentPos[1]*gridHeight + (currentPos[0]-1) ))) && currentPos[0]-1 >= 0 && currentPos[0]-1 < gridWidth ){ //moveRight is available
      moves.add(1);
      //System.out.print("Added left move");
   }
   }catch(Exception e){
      //System.out.println("\ne");
   }
   
   try{
   if(!(routeSet.contains(grid.get( (currentPos[1]-1)*gridHeight + (currentPos[0]) ))) && currentPos[1]-1 >= 0 && currentPos[1]-1 < gridHeight ){ //moveRight is available
      moves.add(2);
      //System.out.print("Added up move");
   }
   }catch(Exception e){
      //System.out.println("\ne");
   }
   
   try{
   if(!(routeSet.contains(grid.get( (currentPos[1]+1)*gridHeight + (currentPos[0]) ))) && currentPos[1]+1 >= 0 && currentPos[1]+1 < gridHeight  ){ //moveRight is available
      moves.add(3);
      //System.out.print("Added down move");
   }
   }catch(Exception e){
      //System.out.println("\ne");
   }
   
   try{
   if(!(routeSet.contains(grid.get( (currentPos[1]-1)*gridHeight + (currentPos[0] - 1) ))) && currentPos[1]-1 >= 0 && currentPos[1]-1 < gridHeight && currentPos[0]-1 >= 0 && currentPos[0]-1 < gridWidth ){ //moveTopLeft is available
      moves.add(4);
      //System.out.print("Added top left move");
   }
   }catch(Exception e){
      //System.out.println("\ne");
   }
   try{
   if(!(routeSet.contains(grid.get( (currentPos[1]-1)*gridHeight + (currentPos[0] + 1) ))) && currentPos[1]-1 >= 0 && currentPos[1]-1 < gridHeight && currentPos[0]+1 >= 0 && currentPos[0]+1 < gridWidth ){ //moveTopRight is available
      moves.add(5);
      //System.out.print("Added top right move");
   }
   }catch(Exception e){
      //System.out.println("\ne");
   }
   
   try{
   if(!(routeSet.contains(grid.get( (currentPos[1]+1)*gridHeight + (currentPos[0] - 1) ))) && currentPos[1]+1 >= 0 && currentPos[1]+1 < gridHeight && currentPos[0]-1 >= 0 && currentPos[0]-1 < gridWidth ){ //moveBottomLeft is available
      moves.add(6);
      //System.out.print("Added bottom left move");
   }
   }catch(Exception e){
      //System.out.println("\ne");
   }
   
   try{
   if(!(routeSet.contains(grid.get( (currentPos[1]+1)*gridHeight + (currentPos[0] + 1) ))) && currentPos[1]+1 >= 0 && currentPos[1]+1 < gridHeight && currentPos[0]+1 >= 0 && currentPos[0]+1 < gridWidth ){ //moveBottomRight is available
      moves.add(7);
      //System.out.print("Added bottom right move");
   }
   }catch(Exception e){
      //System.out.println("\ne");
   }
 
   
   
   finally{
      if(!(moves.size()>0)){
         currentPos = new int[]{randomNumber.nextInt(gridWidth), randomNumber.nextInt(gridHeight)};
         //System.out.print("Assigned new postion to currentPos and returning it now");
         return currentPos;
      }
   }
   
   
   while(true){
   //System.out.println("\nflag104.00 Looping through while loop of movePosition, size of moves: "+moves.size());
   int randomMoveInt = (Integer)moves.get(randomNumber.nextInt(moves.size()));
   int[] potentialPos;
   switch (randomMoveInt){
      case 0:
      potentialPos = moveRight(currentPos);
      //System.out.println("flag104.01  "+randomMoveInt+" "+potentialPos[0] + " "+potentialPos[1] );
         break;
      case 1:
      potentialPos = moveLeft(currentPos);
      //System.out.println("flag104.02  "+randomMoveInt+" "+potentialPos[0] + " "+potentialPos[1] );
         break;
      case 2:
      potentialPos = moveUp(currentPos);
      //System.out.println("flag104.03  "+ randomMoveInt+" "+potentialPos[0] + " "+potentialPos[1] );
         break;
      case 3:
      potentialPos = moveDown(currentPos);
      //System.out.println("flag104.04  "+ randomMoveInt+" "+potentialPos[0] + " "+potentialPos[1] );
         break;
      case 4:
      potentialPos = moveTopLeft(currentPos);
      //System.out.println("flag104.05  "+ randomMoveInt+" "+potentialPos[0] + " "+potentialPos[1] );
         break;
      case 5:
      potentialPos = moveTopRight(currentPos);
      //System.out.println("flag104.06  "+ randomMoveInt+" "+potentialPos[0] + " "+potentialPos[1] );
         break;
      case 6:
      potentialPos = moveBottomLeft(currentPos);
      //System.out.println("flag104.07  "+ randomMoveInt+" "+potentialPos[0] + " "+potentialPos[1] );
         break;
      case 7:
      potentialPos = moveBottomRight(currentPos);
      //System.out.println("flag104.08  "+ randomMoveInt+" "+potentialPos[0] + " "+potentialPos[1] );
         break;
      default:
         potentialPos = new int[]{-100,-100};   
         //System.out.println("flag104.0 Default case "+randomMoveInt+" "+potentialPos[0] + " "+potentialPos[1]);
         break;
   
      }
   
   
   if(potentialPos[0] < gridWidth && potentialPos[0] >=0 && potentialPos[1] >=0 && potentialPos[1] < gridHeight && !(routeSet.contains(grid.get(potentialPos[1]*gridHeight + potentialPos[0]))) ){
      //System.out.println("flag104.06 returning value from movePOsition ");
      return potentialPos;
   }else{
    //System.out.println("flag104.07 in the else statement ");
    //System.out.println("Condition1: "+(potentialPos[0] < gridWidth));
    //System.out.println("Condition2: "+(potentialPos[0] >=0));
    //System.out.println("Condition3: "+(potentialPos[1] >=0));
    //System.out.println("Condition4: "+(potentialPos[1] < gridHeight));
    try{
    //System.out.println("Condition5: "+ (!(routeSet.contains(grid.get(potentialPos[1]*gridHeight + potentialPos[0])))));
    }catch(Exception e){
      //
    }
    
   }   
   }
   
}





//All moves possible from a position
public int[] moveRight(int[] currentPos){
   int x = currentPos[0];
   int y = currentPos[1];
   return new int[]{x+1,y};
   }
   
public int[] moveLeft(int[] currentPos){
   int x = currentPos[0];
   int y = currentPos[1];
   return new int[]{x-1,y};
   }

public int[] moveUp(int[] currentPos){
   int x = currentPos[0];
   int y = currentPos[1];
   return new int[]{x,y-1};
   }

public int[] moveDown(int[] currentPos){
   int x = currentPos[0];
   int y = currentPos[1];
   return new int[]{x,y+1};
   }
   
//start of functions for diagonal moves

public int[] moveTopLeft(int[] currentPos){
   int x = currentPos[0];
   int y = currentPos[1];
   return new int[]{x-1,y-1};
   }
   
public int[] moveTopRight(int[] currentPos){
   int x = currentPos[0];
   int y = currentPos[1];
   return new int[]{x+1,y-1};
   }

public int[] moveBottomLeft(int[] currentPos){
   int x = currentPos[0];
   int y = currentPos[1];
   return new int[]{x-1,y+1};
   }

public int[] moveBottomRight(int[] currentPos){
   int x = currentPos[0];
   int y = currentPos[1];
   return new int[]{x+1,y+1};
   }

}


