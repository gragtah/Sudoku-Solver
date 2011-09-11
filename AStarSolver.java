import java.util.*;

public class AStarSolver
{
	int[][] board;
	
	public static void main(String[] args) throws CloneNotSupportedException, HZeroException
	{
		String puzzle = ".94...13..............76..2.8..1.....32.........2...6.....5.4.......8..7..63.4..8";
		int[][] board = new int[9][9];
		for(int i = 0; i < puzzle.length(); i++)
		{
			char c = puzzle.charAt(i);
			if(c == '.')
				c = '0';
			board[i/9][i%9] = Character.digit(c, 10);				
		}
		
		new AStarSolver(board);
	}
	
	//for board, empty spots == 0
	public AStarSolver(int[][] board) throws CloneNotSupportedException, HZeroException
	{
		//this.board = board;
		Slot[][] slots = new Slot[9][9];
		
		PriorityQueue<Slot> pqueue = new PriorityQueue<Slot>();
		
		//calculate new values for slots
		for(int i = 0; i < 9; i++)
			for(int j = 0; j < 9; j++)
			{
				slots[i][j] = new Slot();
				if(board[i][j] != 0)
					slots[i][j].closed = true;
				slots[i][j].i = i;
				slots[i][j].j = j;
				slots[i][j].correspondingBoard = board;
				if(!slots[i][j].closed)
				{
					slots[i][j].score = generateScore(slots[i][j], board);
					slots[i][j].possibles = calculatePossibles(slots[i][j], board);
					for(int k = 0; k < 9; k++) // add a slot for each given possible answer to the fringe (the PQueue)
					{
						if(slots[i][j].possibles[k] != 0)
						{
							Slot newSlot = (Slot)(slots[i][j].clone());
							newSlot.chosenPossible = k + 1;
							pqueue.add(newSlot);
						}
					}
				}
			}
		
		aStarSolve(pqueue);		
	}
	
	public void multiArrayCopy(int[][] source, int[][] destination)
	{
		for (int a=0;a<source.length;a++)
		{
			System.arraycopy(source[a],0,destination[a],0,source[a].length);
		}
	}
	
	public void aStarSolve(PriorityQueue<Slot> pqueue) throws CloneNotSupportedException
	{
		Slot candidate = pqueue.poll();
		System.out.println(candidate.score);
		
		boolean flag = false;
		//check if board is finished
		for(int i = 0; i < 9; i++)
			for(int j = 0; j < 9; j++)
			{
				if(candidate.correspondingBoard[i][j] == 0)
					flag = true;
					break;
			}
			
		//print board if solution found
		if(!flag)
		{
			System.out.println("Solution found!");
			System.out.println();
			
			for(int i = 0; i < 9; i++)
				for(int j = 0; j < 9; j++)
				{
					System.out.print(candidate.correspondingBoard[i][j] + " ");
					if(j == 8) System.out.println();
				}
		}
		
		int[][] newBoard = new int[9][9];
		multiArrayCopy(candidate.correspondingBoard, newBoard);
		newBoard[candidate.i][candidate.j] = candidate.chosenPossible;
		
		//reassign i and j for each slot
		//generate new scores given newBoard
		//generate new possibilities values given newBoard
		//set correspondingBoard = newBoard
		//reassign closed values
		//add all open squares to PQueue
		//recurse
		
		Slot[][] newSlots = new Slot[9][9];
		for(int i = 0; i < 9; i++)
			for(int j = 0; j < 9; j++)
			{
				newSlots[i][j] = new Slot();
				newSlots[i][j].i = i;
				newSlots[i][j].j = j;
				try {
					newSlots[i][j].score = generateScore(newSlots[i][j], newBoard);
				} catch(HZeroException e)
				{
					continue;
				}
				newSlots[i][j].possibles = calculatePossibles(newSlots[i][j], newBoard);
				newSlots[i][j].correspondingBoard = newBoard; //OK?
				if(newBoard[i][j] == 0)
				{
					newSlots[i][j].closed = false;
					for(int k = 0; k < 9; k++) //for all possibilities
					{
						if(newSlots[i][j].possibles[k] != 0)
						{
							Slot newSlot = (Slot)(newSlots[i][j].clone()); //OK?
							newSlot.chosenPossible = k + 1;
							pqueue.add(newSlot);
						}
					}
				}
				else
					newSlots[i][j].closed = true;		
			}
			
			aStarSolve(pqueue);		
	}
	
	//2d array: i = row, j = col
	public int generateScore(Slot slot, int[][] board) throws HZeroException
	{
		HashSet<Integer> nonClosedValues = new HashSet<Integer>();
		for(int i = 0; i < 9; i++)
			nonClosedValues.add(i);
		for(int i = 0; i < 9; i++) //iterate thru rows, keep column constant (j)
		{
			nonClosedValues.remove(board[i][slot.j]);
		}
		for(int j = 0; j < 9; j++)
			nonClosedValues.remove(board[slot.i][j]);
		int h = nonClosedValues.size();
		if(h == 0)
			throw new HZeroException(slot.i+","+slot.j);
		
		int empty = 0; //aka g
		int threeByThreeRowStart = slot.i - slot.i%3;
		int threeByThreeColStart = slot.j - slot.j%3;
		int threeByThreeRowUpperBound = slot.i + (3 - slot.i%3);
		int threeByThreeColUpperBound = slot.j + (3 - slot.j%3);
		//don't count empty squares in same box on horizontal and vertical counts, so they are not counted twice
		
		//iterate thru the rows in the column:
		for(int i = 0; i < 9; i++)
		{
			if((i < threeByThreeRowStart || i >= threeByThreeRowUpperBound) && board[i][slot.j] == 0)
				empty++;
		}
		
		//iterate thru the column in the row
		for(int j = 0; j < 9; j++)
		{
			if((j < threeByThreeColStart || j >= threeByThreeColUpperBound) && board[slot.i][j] == 0)
				empty++;
		}
		
		//iterate thru 3x3 region
		for(int i = threeByThreeRowStart; i < threeByThreeRowUpperBound; i++)
			for(int j = threeByThreeColStart; j < threeByThreeColUpperBound; j++)
			{
				if(board[i][j] == 0 && !(i == slot.i && j == slot.j))
					empty++;
			}
		return h + empty;
	}
	
	public int[] calculatePossibles(Slot slot, int[][] board)
	{
		HashSet<Integer> possibs = new HashSet<Integer>();
		for(int i = 0; i < 9; i++)
			possibs.add(i);
		for(int i = 0; i < 9; i++)
			possibs.remove(board[i][slot.j]);
		for(int j = 0; j < 9; j++)
			possibs.remove(board[slot.i][j]);
		int[] possibles = new int[9];
		for(Integer i : possibs)
			possibles[i.intValue()-1] = i.intValue();
		return possibles;
	}
}