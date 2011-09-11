public class Organism implements Comparable
{
	int[] blanks;
	int fitness;
	
	public int compareTo(Object o)
	{
		Organism other = (Organism)(o);
		return fitness - other.fitness;
	}
}