import java.util.*;

public class GeneticAlgorithmSolver
{
	public static void main(String[] args)
	{
		String puzzle = ".94...13..............76..2.8..1.....32.........2...6.....5.4.......8..7..63.4..8";
		int[][] board = new int[9][9];
		int numBlanks = 0;
		for(int i = 0; i < puzzle.length(); i++)
		{
			char c = puzzle.charAt(i);
			if(c == '.')
			{
				c = '0';
				numBlanks++;
			}
			board[i/9][i%9] = Character.digit(c, 10);				
		}
		
		new GeneticAlgorithmSolver(board, numBlanks, 20);
	}
	
	public GeneticAlgorithmSolver(int[][] board, int numBlanks, int population)
	{
		ArrayList<Integer> blanks = new ArrayList<Integer>();
		//intialize blanks
		for(int i = 1; i <= 9; i++)
			for(int j = 1; j <= 9; j++)
				blanks.add(i);
		//take out closed spots
		for(int i = 0; i < 9; i++)
			for(int j = 0; j < 9; j++)
			{
				if(board[i][j] != 0)
					blanks.remove(new Integer(board[i][j]));
			}

		Organism[] organisms = initPopulation(population, blanks);
		
		mate(organisms, board);
	}
	
	
	// 0 is a perfect organism
//	public int fitness(int[] blanks, int[][] board)
//	{
//		//int fitness = blanks.length - numConflicts(blanks, board);
//		return numConflicts;
//	}
	
	//returns number of conflicts horizontally and vertically
	//0 is a perfect organism
	public int fitness(int[] blanks, int[][] board)
	{
		int[][] filledBoard = new int[9][9];
		int blankCounter = 0;
		for(int i = 0; i < 9; i++)
			for(int j = 0; j < 9; j++)
			{
				if(board[i][j] != 0)
					filledBoard[i][j] = board[i][j];
				else
				{
					filledBoard[i][j] = blanks[blankCounter];
					blankCounter++;
				}
			}
		
		int num = 0;
		HashMap<Integer, Integer> numbers = new HashMap<Integer, Integer>();
		
		//count thru rows
		for(int i = 0; i < 9; i++)
		{
			for(int j = 0; j < 9; j++)
			{
				if(numbers.containsValue(filledBoard[i][j]) == false)// .get(filledBoard[i][j]) == null)
					numbers.put(filledBoard[i][j], 1);
				else
					numbers.put(filledBoard[i][j], numbers.get(filledBoard[i][j]) + 1);
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
				if(numbers.get(filledBoard[row][col]) == null)
					numbers.put(filledBoard[row][col], 1);
				else
					numbers.put(filledBoard[row][col], numbers.get(filledBoard[row][col]) + 1);
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
	
	public void mate(Organism[] pop, int[][] board)
	{
		//calculate FF for all sets of blanks
		//order the blanks
		//match up 2 in decreasing order
		//select spot for cut
		//random mutation (make sure to randomly select the variable you mutated so numbers = consistent
		
		//Organism[] organisms = new Organism[population.length];
		
		while(true)
		{
			//calculate fitness score and put into pqueue
			PriorityQueue<Organism> pqueue = new PriorityQueue<Organism>();
			for(int i = 0; i < pop.length; i++)
			{
				//organisms[i] = new Organism();
				//System.arraycopy(population[i], 0, organisms[i].blanks, 0, population[i].length);
				pop[i].fitness = fitness(pop[i].blanks, board);
				pqueue.add(pop[i]);
			}
			
			Organism[] newOrganisms = new Organism[pop.length];
			for(int i = 0; i < pop.length; i+= 2)
			{
				newOrganisms[i] = pqueue.poll();
				newOrganisms[i+1] = pqueue.poll();
				
				//on first iteration, print out best board
				if(i == 0)
				{
					int blankIndex = 0;
					for(int j = 0; j < 9; j++) //print out the new board
					{
						for(int k = 0; k < 9; k++)
						{
							if(board[j][k] != 0)
								System.out.print(board[j][k] + " ");
							else
							{
								System.out.print(newOrganisms[i].blanks[blankIndex] + " ");
								blankIndex++;
							}
						}
						System.out.println();
					}
					System.out.println(newOrganisms[i].fitness);
					System.out.println();
				}
				
				
				int splitIndex = (int)(Math.random()*pop[i].blanks.length - 1); //all indexes of blanks except the last
				int[] swap = new int[splitIndex+1];
				//perform crossover
				System.arraycopy(newOrganisms[i].blanks, 0, swap, 0, splitIndex+1);
				System.arraycopy(newOrganisms[i+1].blanks, 0, newOrganisms[i].blanks, 0, splitIndex+1);
				System.arraycopy(swap, 0, newOrganisms[i+1].blanks, 0, splitIndex+1);
				//random mutation (1% for each organism) - will randomly SWITCH 2 integers so that quantity of numbers is preserved
	//			double rand1 = Math.random();
	//			if(rand < .01)
	//			{
	//				int spot = (int)(Math.random()*newOrganisms[i].blanks.length);
	//				int originalNumber = newOrganisms[i].blanks[spot];
	//				int numToSwitchTo = Math.random()*9+1; //1-9
	//				newOrganisms[i].blanks[spot] = numToSwitchTo;
	//				for(int j = 0; j < 9; j++)
	//					//look for num to switch
	//			}			
			}
			
			//search for an organism with fitness = 0
			for(int i = 0; i < newOrganisms.length; i++)
			{
				int blankIndex = 0;
				if(fitness(newOrganisms[i].blanks, board) == 0)
				{
					for(int j = 0; j < 9; j++) //print out the new board
					{
						for(int k = 0; k < 9; k++)
						{
							if(board[j][k] != 0)
								System.out.print(board[j][k] + " ");
							else
							{
								System.out.print(newOrganisms[i].blanks[blankIndex] + " ");
								blankIndex++;
							}
						}
						System.out.println();
					}
					break;
				}
			}
		}
		
		//mate(newOrganisms, board);		
	}
	
	//public int[][] initPopulation(int population, List<Integer> blanks)
	public Organism[] initPopulation(int population, List<Integer> blanks)
	{
		Organism[] organisms = new Organism[population];
		
		//int[][] pop = new int[population][blanks.size()];
		for(int i = 0; i < population; i++)
		{
			organisms[i] = new Organism();
			Collections.shuffle(blanks);
			organisms[i].blanks = new int[blanks.size()];
			for(int j = 0; j < blanks.size(); j++)
				organisms[i].blanks[j] = (int)(blanks.get(j));
				//pop[i][j] = (int)(blanks.get(j));
		}
		return organisms;
	}
}