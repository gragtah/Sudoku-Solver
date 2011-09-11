import java.io.IOException;
import java.util.*;

public class SudokuGen{
	
   int model[][] ; //sudoku grid representation
   public int number = 6; //for setting difficulty

   
   public static void main(String[] args) throws IOException {
	   SudokuGen s = new SudokuGen();
	   
	   s.init();
	   s.run();	   
	   
   }

   protected void createModel()
   {
      model = new int[9][9] ;

      // Clear all cells
      for( int row = 0; row < 9; row++ )
         for( int col = 0; col < 9; col++ )
            model[row][col] = 0 ;

      
      for (int i=0; i<9; i++){
    	  for (int j=0; j<9; j++){
    		  model[i][j] = 0;
    	  }
      }
   }


   /** Gives an updated view of the model */
   
   public void updateView()
   {
      for( int row = 0; row < 9; row++ ){
    	  System.out.println();
    	  if(row==0){System.out.println("\n -----------------------");}
         for( int col = 0; col < 9; col++ )
            if( model[row][col] != 0 ){
            	if(col ==0) {System.out.print("| ");}
            	System.out.print(model[row][col] + " ");
            	if(col==2 | col == 5 | col ==8){System.out.print("| ");}
            }
            else{
            	if(col ==0) {System.out.print("| ");}
            	System.out.print("-" + " ");
            	if(col==2 | col == 5 | col ==8){System.out.print("| ");}
            }
         if(row==2 | row == 5 | row ==8){System.out.print("\n -----------------------");}
      }
      System.out.println();
   }
   
   /** creates representation of Sudoku grid */
   public void init()
   {
      createModel() ;
      //updateView() ;
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
	  
	   //Integer[] ints = new Integer[9];
	   List<Integer> ints = new ArrayList<Integer>();
	   for(int i=1; i<=9; i++){
		   //ints[i-1]=i;
		   ints.add(i);
	   }
	   
      // Throw an exception to stop the process if the puzzle is solved
      if( row > 8 ){
    	//  System.out.println("\nCompleted Grid generated below. Removing cells now to create puzzle!");
    	 // updateView();
    	  int[][] temp1 = new int[9][9];
    	  for(int i=0; i<9; i++){
			  for(int j=0; j<9; j++){
				  temp1[i][j] = model[i][j];
			  }
			  
		  }
    	  //temp = model;
		  /* FOR EVERY BLOCK  , TAKE ONE OUT, REPEAT AS MANY NUMBER OF TIMES!!!!*/
		  Random ran = new Random();
		  
		 // SudokuBacktracking s0; //= new SudokuBacktracking();
		 // SudokuBacktracking s1; //= new SudokuBacktracking();
		  int flag = 0;
		  
		  while(flag ==0){
			  int[][] temp = new int[9][9];
	    	  for(int i=0; i<9; i++){
				  for(int j=0; j<9; j++){
					  temp[i][j] = temp1[i][j];
				  }
				  
			  }
	    	  
	    	  /*Choose 'number' as follows:
	    	   * SET BY DEFAULT TO 6 at top
	    	   * 
				for mid level: number= 5 - 6
				for easy: 3-4 
				for hard: 6 to 8
	    	   */
			  	    	  
			  for (int times = 0; times < number; times++){
			
				  temp[ran.nextInt(3)][ran.nextInt(3)] = 0;
				  temp[ran.nextInt(3)][ran.nextInt(3)+3] = 0;
				  temp[ran.nextInt(3)][ran.nextInt(3)+6] = 0;
				  temp[ran.nextInt(3)+3][ran.nextInt(3)] = 0;
				  temp[ran.nextInt(3)+3][ran.nextInt(3)+3] = 0;
				  temp[ran.nextInt(3)+3][ran.nextInt(3)+6] = 0;
				  temp[ran.nextInt(3)+6][ran.nextInt(3)] = 0;
				  temp[ran.nextInt(3)+6][ran.nextInt(3)+3] = 0;
				  temp[ran.nextInt(3)+6][ran.nextInt(3) + 6] = 0;
			  
		  	}
			  SudokuBacktracking s0= new SudokuBacktracking();
			  SudokuBacktracking s1= new SudokuBacktracking();
			  SudokuBacktracking s2 = new SudokuBacktracking();
			  
			  s0.init(temp); s1.init(temp); s2.init(temp);
			  s0.run(); s1.run(); s2.run(); int c = 0;
			  System.out.println();
			  
			  for (int i=0; i<9; i++){
				  for (int j=0; j<9; j++){
					  if (s0.model[i][j] != s1.model[i][j]){c = 1; break;}
				  }
				  if (c==1){break;}
			  }
			  if (c==1){flag = 0; // means that loop should repeat again since this grid-blank-assignment does not yield unique solution
			  			  }
			  else {
				  
				  flag = 1;
				  
				  for (int i=0; i<9; i++){
					  for (int j=0; j<9; j++){
						  if (s1.model[i][j] != s2.model[i][j]){c = 1; break;}
					  }
					  if (c==1){break;}
				  }
				  if(c==1){flag =0;}
				  else{
				  
				  for(int i=0; i<9; i++){
					  for(int j=0; j<9; j++){
						  //System.out.print(temp[i][j]+ " ");
						  model[i][j] = temp[i][j];
					  }
					  //System.out.println();
				  }
			  }
			  }
			  
			  ctr += 1;
			  
		  }

		  System.out.println("SUDOKU PUZZLE GENERATED: ");
		      	  
		  updateView();
		  //return model;
    	      	      	  
         throw new Exception( "Solution found!" ) ;
         
      }
      
      // If cell is not empty, continue with next cell
      if( model[row][col] != 0 )
      {	 
         next( row, col ) ;
      }
      else
      {
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
         //updateView() ;
         //System.out.println();
         
      }
	//return model;
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