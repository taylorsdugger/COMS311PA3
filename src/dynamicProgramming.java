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
        		if( y!= 0&& y!=m-1  && CostMatrix[x-1][y-1] <= CostMatrix[x-1][y] &&  CostMatrix[x-1][y-1] <= CostMatrix[x-1][y+1] ) //first value is smallest
	        	{
        			temp = CostMatrix[x-1][y-1] + M[x][y];
	        	}
        		else if (y!=m-1 && y!= 0 && CostMatrix[x-1][y+1] <= CostMatrix[x-1][y] && CostMatrix[x-1][y+1] <= CostMatrix[x-1][y-1] )
        		{
        			temp = CostMatrix[x-1][y+1] + M[x][y];
        		}
	        	else {
	        		if(y == 0)
	        		{
	        			if(CostMatrix[x-1][y] < CostMatrix[x-1][y+1] )
	        			{
	        				temp = CostMatrix[x-1][y] + M[x][y];
	        			}
	        			else{
	        				temp = CostMatrix[x-1][y+1] + M[x][y];
	        			}
	        		}
	        		else if(y == m-1)
	        		{
	        			if(CostMatrix[x-1][y-1] < CostMatrix[x-1][y] )
	        			{
	        				temp = CostMatrix[x-1][y-1] + M[x][y];
	        			}
	        			else{
	        				temp = CostMatrix[x-1][y] + M[x][y];
	        			}
	        		}
	        		else{
	        			temp = CostMatrix[x-1][y] + M[x][y];
	        		}
	        		
	        	} 	

        			CostMatrix[x][y] = temp;

        	}
        }
        Integer[] minCostVC = new Integer[2 * n];

        int iteratorY = 0;
        int cost = CostMatrix[n-1][iteratorY];
        
        //finds the lowest costing value in the cost matrix to work back from
        for(int y = 1; y<m;y++)
        {
        	if(CostMatrix[n-1][y] < CostMatrix[n-1][iteratorY])
        	{

        		iteratorY = y;
        		cost  =  CostMatrix[n-1][y];
        	}
        }
		minCostVC[2*n - 2] = n-1 ;
		minCostVC[2*n - 1] = iteratorY ;
        
        
        for(int x = n - 2 ; x >-1; x--)
        {
        	cost = cost - M[x+1][iteratorY];
        	
        	if(iteratorY!= 0  &&CostMatrix[x][iteratorY-1] == cost)
        	{
        		minCostVC[ 2*x] =  x ;
        		minCostVC[2*x + 1] =  iteratorY-1 ;
        		iteratorY = iteratorY-1;
        	}
        	else if(CostMatrix[x][iteratorY]== cost)
        	{
        		minCostVC[ 2*x] =  x ;
        		minCostVC[2*x + 1] =  iteratorY ;
        		
        	}
        	else if(iteratorY!= m-1 && CostMatrix[x][iteratorY+1]== cost)
        	{
        		minCostVC[ 2*x] =  x ;
        		minCostVC[2*x + 1] =  iteratorY+1 ;
        		iteratorY = iteratorY+1;
        	}

        	
        }
        
        
        ArrayList<Integer> ret = new ArrayList<Integer>();
        for(int i = 0; i < 2*n;i++)
        {
        	ret.add(minCostVC[i]);
        }

        return ret;
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
