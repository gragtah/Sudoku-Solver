import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SudokuBacktracking{
	
   int model[][] ;
   
   public static void main(String[] args) throws IOException {
	   SudokuBacktracking s = new SudokuBacktracking();
	   s.init();
	   s.run();	   
   }

    void createModel()
   {
      model = new int[9][9] ;

      // Clear all cells
      for( int row = 0; row < 9; row++ )
         for( int col = 0; col < 9; col++ )
            model[row][col] = 0 ;

      // Create the initial situation
      
      model[0][0] = 9 ;
      model[0][4] = 2 ;
      model[0][6] = 7 ;
      model[0][7] = 5 ;

      model[1][0] = 6 ;
      model[1][4] = 5 ;
      model[1][7] = 4 ;

      model[2][1] = 2 ;
      model[2][3] = 4 ;
      model[2][7] = 1 ;

      model[3][0] = 2 ;
      model[3][2] = 8 ;

      model[4][1] = 7 ;
      model[4][3] = 5 ;
      model[4][5] = 9 ;
      model[4][7] = 6 ;

      model[5][6] = 4 ;
      model[5][8] = 1 ;

      model[6][1] = 1 ;
      model[6][5] = 5 ;
      model[6][7] = 8 ;

      model[7][1] = 9 ;
      model[7][4] = 7 ;
      model[7][8] = 4 ;

      model[8][1] = 8 ;
      model[8][2] = 2 ;
      model[8][4] = 4 ;
      model[8][8] = 6 ;
      
   }
    
   int[][] createModel(int[][] temp)
   {
      model = new int[9][9] ;

      // Clear all cells
      for( int row = 0; row < 9; row++ )
         for( int col = 0; col < 9; col++ )
            model[row][col] = temp[row][col]; //0 ;
      
      return model;
   }


   /** Gives an updated view of the model */
   
   public void updateView()
   {
      for( int row = 0; row < 9; row++ ){
    	  System.out.println();
    	  if(row==0){System.out.println("\n -----------------------");}
         for( int col = 0; col < 9; col++ ){
            if( model[row][col] != 0 ){
            	if(col ==0) {System.out.print("| ");}
            	System.out.print(model[row][col] + " ");
            	if(col==2 | col == 5 | col ==8){System.out.print("| ");}
            }
            else{
            	System.out.print("-" + " ");
            }
         }
         if(row==2 | row == 5 | row ==8){System.out.print("\n -----------------------");}
                
      }
      System.out.println();
   }
   
   /** creates representation of Sudoku grid */
   public int[][] init(int temp[][])
   {
      createModel(temp) ;
     // updateView() ;
      return model;
   }

   public void init()
   {
      createModel() ;
    //  updateView() ;
   }
   
   /** Checks if num is an acceptable value for the given row */
   protected boolean checkRow( int row, int num )
   {
      for( int col = 0; col < 9; col++ )
         if( model[row][col] == num )
            return false ;

      return true ;
   }

   /** Checks if num is an acceptable value for the given column */
   protected boolean checkCol( int col, int num )
   {
      for( int row = 0; row < 9; row++ )
         if( model[row][col] == num )
            return false ;

      return true ;
   }

   /** Checks if num is an acceptable value for the box around row and col */
   protected boolean checkBox( int row, int col, int num )
   {
      row = (row / 3) * 3 ;
      col = (col / 3) * 3 ;

      for( int r = 0; r < 3; r++ )
         for( int c = 0; c < 3; c++ )
         if( model[row+r][col+c] == num )
            return false ;

      return true ;
   }

   
   public void run()
   {
      try
      {
    	//  updateView();

         // Start to solve the puzzle in the left upper corner
         solve( 0, 0 ) ;
      }
      catch( Exception e )
      {
      }
   }
   public int ctr = 0;
   /** Recursive function to find a valid number for one single cell */
   public void solve( int row, int col ) throws Exception
   {
	   // Throw an exception to stop the process if the puzzle is solved
	      if( row > 8 ){
	    	  //System.out.println("\nSolution found!");
	    	  //updateView(); //------ FINAL SOLUTION IF YOU WANT TO PRINT
	         throw new Exception( "Solution found!" ) ;
	      }
	      
	   ctr += 1;
	   
	   
      
      // If cell is not empty, continue with next cell
      if( model[row][col] != 0 )
         next( row, col ) ;
      else
      {
    	  List<Integer> ints = new ArrayList<Integer>();
   	   for(int i=1; i<=9; i++){
   		   		ints.add(i);
   	   		}
   	   
    	  Collections.shuffle(ints);
     	 
    	  // Find valid number for the empty cell
         for( int num = 1; num < 10; num++ )
         {
        	 int x = ints.get(num-1);
            if( checkRow(row,x) && checkCol(col,x) && checkBox(row,col,x) ) 
            {
            	model[row][col] = x;
             //  updateView() ;

               // Delegate work on the next cell to a recursive call
               next( row, col ) ;
            }
         }

         // No valid number found, erase and return to caller
         model[row][col] = 0 ;
         	// updateView() ;
         	// System.out.println();
         
      }
   }

   /** Calls solve for the next cell */
   public void next( int row, int col ) throws Exception
   {
      if( col < 8 )
         solve( row, col + 1 ) ;
      else
         solve( row + 1, 0 ) ;
   }
}