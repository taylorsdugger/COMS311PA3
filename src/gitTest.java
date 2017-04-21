import java.util.ArrayList;

public class gitTest {

	public static void main(String[] args) {
		int[][] s = { {7, 5 ,3 ,1 , 9, 4}, {7, 5 ,3 ,1 , 9, 4}, {7, 5 ,3 ,1 , 9, 4}, {7, 5 ,3 ,1 , 9, 4}, {7, 5 ,3 ,1 , 9, 4}, {7, 5 ,3 ,1 , 9, 4} };
		int[][] s1 = { {7, 5 ,3 },
					   {2, 1, 4 },
					   {0, 5, 10}
		};
		ArrayList<Integer> ret = dynamicProgramming.minCostVC(s1);
        for(int i = 0; i < ret.size();i++)
        {
        	System.out.println(ret.get(i));
        	//ret.get(i);
        }

	}

}
