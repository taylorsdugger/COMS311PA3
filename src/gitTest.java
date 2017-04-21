import java.util.ArrayList;

public class gitTest {

	public static void main(String[] args) {
		int[][] s = { {7, 5 ,3 ,1 , 9, 4}, {7, 5 ,3 ,1 , 9, 4}, {7, 5 ,3 ,1 , 9, 4}, {7, 5 ,3 ,1 , 9, 4}, {7, 5 ,3 ,1 , 9, 4}, {7, 5 ,3 ,1 , 9, 4} };
		int[][] s1 = { {7, 5 ,3, 0 },
					   {2, 1, 4, 12 },
					   {5, 0, 10, 4},
					   {2, 9, 10, 4}
		};
		ArrayList<Integer> ret = dynamicProgramming.minCostVC(s1);
        for(int i = 0; i < ret.size();i=i+2)
        {
        	System.out.println(ret.get(i) + ", "+ ret.get(i+1));
        	//ret.get(i);
        }

	}

}
