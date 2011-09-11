import java.util.*;

public class Slot implements Comparable, Cloneable
{
	boolean closed;
	int score;
	int i;
	int j;
	int[] possibles = {1,2,3,4,5,6,7,8,9};
	int[][] correspondingBoard;
	int chosenPossible;
	
	public int compareTo(Object o)
	{
		Slot other = (Slot)(o);
		return score - other.score;
	}
	
	public Object clone() throws CloneNotSupportedException
	{
		Slot s = (Slot) super.clone();
		s.possibles = this.possibles.clone();
		multiArrayCopy(this.correspondingBoard, s.correspondingBoard);
		return s;
	}
	
	public void multiArrayCopy(int[][] source, int[][] destination)
	{
		for (int a=0;a<source.length;a++)
		{
			System.arraycopy(source[a],0,destination[a],0,source[a].length);
		}
	}
}