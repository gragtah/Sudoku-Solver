/*********************************************************/
/*                                                       */
/* An applet to demonstrate recursion and backtracking   */
/* ===================================================   */
/*                                                       */
/* V0.3   18-MAR-2007  P. Tellenbach   www.heimetli.ch   */
/*                                                       */
/*********************************************************/
import java.applet.* ;
import java.awt.* ;

/**
 * Solves a sudoku puzzle by recursion and backtracking
 */
public class SimplifiedSudoku extends Applet implements Runnable
{
   /** The model */
   protected int model[][] ;

   /** The view */
   protected Button view[][] ;

   /** Creates the model and sets up the initial situation */
   protected void createModel()
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

   /** Creates an empty view */
   protected void createView()
   {
      setLayout( new GridLayout(9,9) ) ;

      view = new Button[9][9] ;

      // Create an empty view
      for( int row = 0; row < 9; row++ )
         for( int col = 0; col < 9; col++ )
         {
            view[row][col]  = new Button() ;
            add( view[row][col] ) ;
         }
   }

   /** Updates the view from the model */
   protected void updateView()
   {
      for( int row = 0; row < 9; row++ )
         for( int col = 0; col < 9; col++ )
            if( model[row][col] != 0 )
               view[row][col].setLabel( String.valueOf(model[row][col]) ) ;
            else
               view[row][col].setLabel( "" ) ;
   }

   /** This method is called by the browser when the applet is loaded */
   public void init()
   {
      createModel() ;
      createView() ;
      updateView() ;
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

   /** This method is called by the browser to start the applet */
   public void start()
   {
      // This statement will start the method 'run' to in a new thread
      (new Thread(this)).start() ;
   }

   /** The active part begins here */
   public void run()
   {
      try
      {
         // Let the observers see the initial position
         Thread.sleep( 1000 ) ;

         // Start to solve the puzzle in the left upper corner
         solve( 0, 0 ) ;
      }
      catch( Exception e )
      {
      }
   }

   /** Recursive function to find a valid number for one single cell */
   public void solve( int row, int col ) throws Exception
   {
      // Throw an exception to stop the process if the puzzle is solved
      if( row > 8 )
         throw new Exception( "Solution found" ) ;

      // If the cell is not empty, continue with the next cell
      if( model[row][col] != 0 )
         next( row, col ) ;
      else
      {
         // Find a valid number for the empty cell
         for( int num = 1; num < 10; num++ )
         {
            if( checkRow(row,num) && checkCol(col,num) && checkBox(row,col,num) )
            {
               model[row][col] = num ;
               updateView() ;

               // Let the observer see it
               //Thread.sleep( 1000 ) ;

               // Delegate work on the next cell to a recursive call
               next( row, col ) ;
            }
         }

         // No valid number was found, clean up and return to caller
         model[row][col] = 0 ;
         updateView() ;
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