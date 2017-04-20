import java.util.ArrayList;

/**
 * Created by taylo on 4/18/2017.
 */
public class dynamicProgramming {

    public dynamicProgramming(){

    }

    /**
     * minCostVC(int[][] M): Returns a min-cost vertical cut. Type of this method must be
     * array list of integers. Note that if M has n rows, the the returned array list
     * has exactly 2n integers. You must use dynamic programming paradigm to arrive
     * at your code. 
     *
     * @param M Matrix m
     * @return min-cost vertical cut
     */
    public static ArrayList<Integer> minCostVC(int[][] M){

    	int m = M[0].length;
    	int n = M.length;
    	
    	
      
        
        Integer[][] CostMatrix = new Integer[n][m];
        for(int i = 0;i<m;i++)
        {
        	CostMatrix[0][i] = M[0][i];
        }

        for (int x = 1; x < n;x++)
        {
        	for(int y = 0; y < m; y++)
        	{
        		Integer temp = null;
        		if( y!=0 && M[x-1][y-1] <= M[x-1][y] &&  M[x-1][y-1] <= M[x-1][y+1] ) //first value is smallest
	        	{
        			temp = M[x-1][y-1] + M[x][y];
	        	}
	        	else if(M[x-1][y] <= M[x-1][y+1]) //the middle is the smallest
	        	{
	        		temp = M[x-1][y] + M[x][y];
	        		
	        	}
	        	else if(y!=m-1) {// M[x-1][y+1] is the smallest then

	        		temp = M[x-1][y+1] + M[x][y];
	        	} 	
//        	else{
//        	}
        		if(temp==null)
        		{
        			System.out.println("Error");
        		}
        	
        		else if(CostMatrix[x][y] == null || CostMatrix[x][y] < temp)
        		{
        			CostMatrix[x][y] = temp;
        		}
        	}
        }
        ArrayList<Integer> minCostVC = new ArrayList<Integer>(2 * n);
        
        for (int x = n-1; x > -1;x--)
        {

        	}

        return minCostVC;
    }

    /**
     * stringAlignment(String x, String y) . Assume that x is a string of length n
     * and y is a string of length m such that n â‰¥ m. This method returns a string z
     * (obtained by inserting $ at n âˆ’ m indices in y) such that AlignCost(x,z) â‰¤ AlignCost(x,y)
     * over all possible y. You may assume that length of x is at least the length of y and
     * neither of x or y has the character $. Note that the length of the returned string z
     * must equal the length of x. You must use dynamic programming paradigm to arrive at
     * your code. For this, ï¬�rst deï¬�ne the recurrence relation. Then arrive at an
     * iterative solution. Your code must be iterative, not recursive and should not use
     * use memoization. Otherwise you will receive zero credit.
     *
     * @param x String x of length n
     * @param y string y of length m
     * @return string z such that AlignCost(x,z) â‰¤ AlignCost(x,y) over all possible y
     */
    public static String stringAlignment(String x, String y){

        if(x.equals(y)){
            return y;
        }

        int n = x.length(); //length n
        int m = y.length();//length m
        
        String z = "";

        for(int i = 0; i < n; i++){
            char a = y.charAt(i);
            char b = x.charAt(i);
        }

        return null;
    }

    /**
     * Given two characters a and b, we deï¬�ne a function penalty(a,b) as follows:
     * if a equals b, penalty(a,b) = 0. If a or b equals $, then penalty(a,b) = 4;
     * otherwise penalty(a,b) = 2.
     *
     * @param a character a
     * @param b character b
     * @return penalty cost
     */
    private int penalty(Character a, Character b){

        if(a.equals(b)){
            return 0;
        }else if(a.equals('$') || b.equals('$')){
            return 4;
        }else{
            return 2;
        }

    }
}
