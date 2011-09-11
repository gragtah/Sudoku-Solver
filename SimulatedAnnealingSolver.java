import java.util.*;

public class SimulatedAnnealingSolver
{
	boolean[] squaresUnchangable;
	
	public ArrayList<Integer> initNumbers(ArrayList<Integer> numbers)
	{
		numbers.clear();
		for(int i = 1; i <= 9; i++)
			numbers.add(i);
		
		return numbers;
	}
	
	//for input board, empty squares are = 0
	public void SimulatedAnnealingSolve(int[][] board)
	{
		squaresUnchangable = new boolean[81];
		
		//determine which squares' values cannot be changed
		for(int i = 0; i < 9; i++)
		{
			for(int j = 0; j < 9; j++)
			{
				if(board[i][j] != 0)
					squaresUnchangable[i*9 + j] = true;
			}
		}
		
		//for every 3x3 square, calculate numbers remaining and fill them in randomly
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		numbers = initNumbers(numbers);
		
		for(int row = 0; row < 3; row++)
			for(int col = 0; col < 3; col++)
			{
				if(board[row][col] != 0)
					numbers.remove(new Integer(board[row][col]));
			}
		Collections.shuffle(numbers);
		for(int row = 0; row < 3; row++)
			for(int col = 0; col < 3; col++)
			{
				if(board[row][col] == 0)
					board[row][col] = numbers.remove(0);
			}
		
		numbers = initNumbers(numbers);
		
		for(int row = 0; row < 3; row++)
			for(int col = 3; col < 6; col++)
			{
				if(board[row][col] != 0)
					numbers.remove(new Integer(board[row][col]));
			}
		Collections.shuffle(numbers);
		for(int row = 0; row < 3; row++)
			for(int col = 3; col < 6; col++)
			{
				if(board[row][col] == 0)
					board[row][col] = numbers.remove(0);
			}
		
		numbers = initNumbers(numbers);
		
		for(int row = 0; row < 3; row++)
			for(int col = 6; col < 9; col++)
			{
				if(board[row][col] != 0)
					numbers.remove(new Integer(board[row][col]));
			}
		Collections.shuffle(numbers);
		for(int row = 0; row < 3; row++)
			for(int col = 6; col < 9; col++)
			{
				if(board[row][col] == 0)
					board[row][col] = numbers.remove(0);
			}
		
		numbers = initNumbers(numbers);
		
		for(int row = 3; row < 6; row++)
			for(int col = 0; col < 3; col++)
			{
				if(board[row][col] != 0)
					numbers.remove(new Integer(board[row][col]));
			}
		Collections.shuffle(numbers);
		for(int row = 3; row < 6; row++)
			for(int col = 0; col < 3; col++)
			{
				if(board[row][col] == 0)
					board[row][col] = numbers.remove(0);
			}
		
		numbers = initNumbers(numbers);
		
		for(int row = 3; row < 6; row++)
			for(int col = 3; col < 6; col++)
			{
				if(board[row][col] != 0)
					numbers.remove(new Integer(board[row][col]));
			}
		Collections.shuffle(numbers);
		for(int row = 3; row < 6; row++)
			for(int col = 3; col < 6; col++)
			{
				if(board[row][col] == 0)
					board[row][col] = numbers.remove(0);
			}
		
		numbers = initNumbers(numbers);
		
		for(int row = 3; row < 6; row++)
			for(int col = 6; col < 9; col++)
			{
				if(board[row][col] != 0)
					numbers.remove(new Integer(board[row][col]));
			}
		Collections.shuffle(numbers);
		for(int row = 3; row < 6; row++)
			for(int col = 6; col < 9; col++)
			{
				if(board[row][col] == 0)
					board[row][col] = numbers.remove(0);
			}
		
		numbers = initNumbers(numbers);
		
		for(int row = 6; row < 9; row++)
			for(int col = 0; col < 3; col++)
			{
				if(board[row][col] != 0)
					numbers.remove(new Integer(board[row][col]));
			}
		Collections.shuffle(numbers);
		for(int row = 6; row < 9; row++)
			for(int col = 0; col < 3; col++)
			{
				if(board[row][col] == 0)
					board[row][col] = numbers.remove(0);
			}
		
		numbers = initNumbers(numbers);
		
		for(int row = 6; row < 9; row++)
			for(int col = 3; col < 6; col++)
			{
				if(board[row][col] != 0)
					numbers.remove(new Integer(board[row][col]));
			}
		Collections.shuffle(numbers);
		for(int row = 6; row < 9; row++)
			for(int col = 3; col < 6; col++)
			{
				if(board[row][col] == 0)
					board[row][col] = numbers.remove(0);
			}
		
		numbers = initNumbers(numbers);
		
		for(int row = 6; row < 9; row++)
			for(int col = 6; col < 9; col++)
			{
				if(board[row][col] != 0)
					numbers.remove(new Integer(board[row][col]));
			}
		Collections.shuffle(numbers);
		for(int row = 6; row < 9; row++)
			for(int col = 6; col < 9; col++)
			{
				if(board[row][col] == 0)
					board[row][col] = numbers.remove(0);
			}
		/*
		for(int i = 0; i < 9; i++)
		{
			for(int j = 0; j < 9; j++)
			{
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
		*/
		for( int row = 0; row < 9; row++ ){
	    	  System.out.println();
	    	  if(row==0){System.out.println("\n -----------------------");}
	         for( int col = 0; col < 9; col++ )
	            if( board[row][col] != 0 ){
	            	if(col ==0) {System.out.print("| ");}
	            	System.out.print(board[row][col] + " ");
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
	      
		
		recurseSolve(board, .8, 0);
	}
	
	public int numConflicts(int[][] board)
	{
		int num = 0;
		HashMap<Integer, Integer> numbers = new HashMap<Integer, Integer>();
		
		//count thru rows
		for(int i = 0; i < 9; i++)
		{
			for(int j = 0; j < 9; j++)
			{
				if(numbers.get(board[i][j]) == null)
					numbers.put(board[i][j], 1);
				else
					numbers.put(board[i][j], numbers.get(board[i][j]) + 1);
			}

			for(int j = 1; j <= 9; j++)
			{
				if(numbers.get(j) != null && numbers.get(j) > 1)
				{
					num += numbers.get(j) - 1;
				}
				numbers.put(j, null); //reset map for next row
			}
		}
		//count thru columns
		for(int col = 0; col < 9; col++)
		{
			for(int row = 0; row < 9; row++)
			{
				if(numbers.get(board[row][col]) == null)
					numbers.put(board[row][col], 1);
				else
					numbers.put(board[row][col], numbers.get(board[row][col]) + 1);
			}
			
			for(int j = 1; j <= 9; j++)
			{
				if(numbers.get(j) != null && numbers.get(j) > 1)
				{
					num += numbers.get(j) - 1;
				}
				numbers.put(j, null); //reset map for next column
			}
		}
		return num;
	}
	
	// cooling constant = .7
	public int[][] recurseSolve(int[][] board, double temperature, int iteration)
	{
		int initConflicts = numConflicts(board);
		int square = (int)(Math.random()*9);
		int xOffset = 0;
		int yOffset = 0;
		
		if(initConflicts == 0)
		{
			System.out.println("Solution found!");
			return board;
		}			
		
		switch(square)
		{
			case 0:
				xOffset = 0;
				yOffset = 0;
				break;
			case 1:
				xOffset = 0;
				yOffset = 3;
				break;
			case 2:
				xOffset = 0;
				yOffset = 6;
				break;
			case 3:
				xOffset = 3;
				yOffset = 0;
				break;
			case 4:
				xOffset = 3;
				yOffset = 3;
				break;
			case 5:
				xOffset = 3;
				yOffset = 6;
				break;
			case 6:
				xOffset = 6;
				yOffset = 0;
				break;
			case 7:
				xOffset = 6;
				yOffset = 3;
				break;
			case 8:
				xOffset = 6;
				yOffset = 6;
				break;
		}
		
		int x1, y1, x2, y2;		
		do {
			x1 = (int)(Math.random()*3);
			y1 = (int)(Math.random()*3);
			x2 = (int)(Math.random()*3);
			y2 = (int)(Math.random()*3);
		} while(squaresUnchangable[(xOffset+x1)*9+(yOffset+y1)] || squaresUnchangable[(xOffset+x2)*9+(yOffset+y2)]);
		
		System.out.println("x1: "+(xOffset+x1)+" y1: "+(yOffset+y1));
		System.out.println("x2: "+(xOffset+x2)+" y2: "+(yOffset+y2));
		System.out.println("iteration number: "+ iteration);
		iteration++;
		
		int[][] boardCandidate = new int[9][9];
		multiArrayCopy(board, boardCandidate);
		boardCandidate[xOffset+x1][yOffset+y1] = board[xOffset+x2][yOffset+y2];
		boardCandidate[xOffset+x2][yOffset+y2] = board[xOffset+x1][yOffset+y1];
		
		int newConflicts = numConflicts(boardCandidate);
		
		if(newConflicts < initConflicts)
			multiArrayCopy(boardCandidate, board);
		else
		{
			double probability = Math.exp((initConflicts - newConflicts)/temperature);
			double random = Math.random();
			if(random <= probability)
				multiArrayCopy(boardCandidate, board);
		}
		/*
		for(int i = 0; i < 9; i++) //print out the new board
		{
			for(int j = 0; j < 9; j++)
			{
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
		*/
		for( int row = 0; row < 9; row++ ){
	    	  System.out.println();
	    	  if(row==0){System.out.println("\n -----------------------");}
	         for( int col = 0; col < 9; col++ )
	            if( board[row][col] != 0 ){
	            	if(col ==0) {System.out.print("| ");}
	            	System.out.print(board[row][col] + " ");
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
	      
	      
		
		if(iteration > 4450) //added. 20000 before, 4450
			return board;
		
		double nextTemperature = updateTemp(temperature);
		return recurseSolve(board, nextTemperature, iteration);
	}
	
	public void multiArrayCopy(int[][] source, int[][] destination)
	{
		for (int a=0;a<source.length;a++)
		{
			System.arraycopy(source[a],0,destination[a],0,source[a].length);
		}
	}
	
	public double updateTemp(double temperature)
	{
		temperature *= .8; //or .7 or .3
		return temperature;
	}
	
	public static void main(String[] args)
	{
		//String puzzle = ".94...13..............76..2.8..1.....32.........2...6.....5.4.......8..7..63.4..8";
		String puzzle = "6.2.41..51..2.584.8546.72913..46257.4287531.95.6.1.32...3126.577453...122615.49.3";
		int[][] board = new int[9][9];
		for(int i = 0; i < puzzle.length(); i++)
		{
			char c = puzzle.charAt(i);
			if(c == '.')
				c = '0';
			board[i/9][i%9] = Character.digit(c, 10);				
		}
		/*
		for(int i = 0; i < 9; i++) //print out the initial board
		{
			for(int j = 0; j < 9; j++)
			{
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
		*/
		
		for( int row = 0; row < 9; row++ ){
	    	  System.out.println();
	    	  if(row==0){System.out.println("\n -----------------------");}
	         for( int col = 0; col < 9; col++ )
	            if( board[row][col] != 0 ){
	            	if(col ==0) {System.out.print("| ");}
	            	System.out.print(board[row][col] + " ");
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
	      
		
		SimulatedAnnealingSolver s = new SimulatedAnnealingSolver();
		s.SimulatedAnnealingSolve(board);
	}
}