import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

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
     *Uses Dijkstra algorithm to calculate all the paths, and places that data
     *in a seperate matrix.  Basically builds that data by adding the best possible
     *path to get to a node in the matrix.  After building that matrix it can easily
     *retrace through the paths to find the shortest possible path.
     *
     * @param M Matrix m
     * @return min-cost vertical cut
     */
    public static ArrayList<Integer> minCostVC(int[][] M){

    	int m = M[0].length;
    	int n = M.length;

        Integer[][] CostMatrix = new Integer[n][m]; //matrix of dynamic data
        for(int i = 0;i<m;i++)
        {
        	CostMatrix[0][i] = M[0][i];
        }

        //creates a matrix where each row builds on the row before it
        for (int x = 1; x < n;x++)
        {
        	for(int y = 0; y < m; y++)
        	{
        		Integer temp = null;
        		if( y!= 0&& y!=m-1  && CostMatrix[x-1][y-1] <= CostMatrix[x-1][y] &&  CostMatrix[x-1][y-1] <= CostMatrix[x-1][y+1] ) //first value is smallest
	        	{
        			temp = CostMatrix[x-1][y-1] + M[x][y];
	        	}
        		else if (y!=m-1 && y!= 0 && CostMatrix[x-1][y+1] <= CostMatrix[x-1][y] && CostMatrix[x-1][y+1] <= CostMatrix[x-1][y-1] ) //last is smallest
        		{
        			temp = CostMatrix[x-1][y+1] + M[x][y];
        		}
	        	else {
	        		if(y == 0)//skips first
	        		{
	        			if(CostMatrix[x-1][y] < CostMatrix[x-1][y+1] ) //middle is smallest
	        			{
	        				temp = CostMatrix[x-1][y] + M[x][y];
	        			}
	        			else{
	        				temp = CostMatrix[x-1][y+1] + M[x][y];//last is smallest
	        			}
	        		}
	        		else if(y == m-1)//skips last
	        		{
	        			if(CostMatrix[x-1][y-1] < CostMatrix[x-1][y] )//first is smallest
	        			{
	        				temp = CostMatrix[x-1][y-1] + M[x][y];
	        			}
	        			else{
	        				temp = CostMatrix[x-1][y] + M[x][y]; //middle is smallest
	        			}
	        		}
	        		else{ //middle is smallest
	        			temp = CostMatrix[x-1][y] + M[x][y];
	        		}
	        		
	        	} 	

        			CostMatrix[x][y] = temp;

        	}
        }
        
        Integer[] minCostVC = new Integer[2 * n];//the path for the minimum cut

        int iteratorY = 0;
        int cost = CostMatrix[n-1][iteratorY];
        
        //traces back through the cost matrix to find the minimum cut
        
        for(int y = 1; y<m;y++)//gets initial smallest path
        {
        	if(CostMatrix[n-1][y] < CostMatrix[n-1][iteratorY])
        	{

        		iteratorY = y;
        		cost  =  CostMatrix[n-1][y];
        	}
        }
		minCostVC[2*n - 2] = n-1 ;
		minCostVC[2*n - 1] = iteratorY ;
        
        
        for(int x = n - 2 ; x >-1; x--)//traces through the rest of the matrix from that initial smallest path
        {
        	cost = cost - M[x+1][iteratorY];
        	
        	if(iteratorY!= 0  &&CostMatrix[x][iteratorY-1] == cost)
        	{
        		minCostVC[ 2*x] =  x ;
        		minCostVC[2*x + 1] =  iteratorY-1 ;
        		iteratorY = iteratorY-1;
        	}
        	else if(CostMatrix[x][iteratorY] == cost)
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
        
        ArrayList<Integer> ret = new ArrayList<Integer>(); //converts to arrayList
        for(int i = 0; i < 2*n;i++)
        {
        	ret.add(minCostVC[i]);
        }

        return ret;
    }

    /**
     * stringAlignment(String x, String y) . Assume that x is a string of length n
     * and y is a string of length m such that n â‰¥ m. This method returns a string z
     * (obtained by inserting $ at n a m indices in y) such that AlignCost(x,z)  AlignCost(x,y)
     * over all possible y. You may assume that length of x is at least the length of y and
     * neither of x or y has the character $. Note that the length of the returned string z
     * must equal the length of x. 
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

        //Temp Strings
        String align;
        StringBuilder curString;

        //Return String
        StringBuilder z = new StringBuilder();
        z.append(y);
        //LowPen
        int lowestPenalty = Integer.MAX_VALUE;
        //Calculates the penalty
        ArrayList<Integer> stringPen = new ArrayList<>();
        //Index where lowest pen is
        int in = 0;

        //how many $'s to add
        for(int i = 0; i < n-m; i++){
            //go through string y
            for(int j = 0; j <= m; j++){
                align = z.toString();
                //add to our curString a $ at each spot j, so every spot in string
                curString = new StringBuilder(align.substring(0,j) + "$" + align.substring(j, align.length()));
                //pen =0
                int pen = 0;
                //go though the string we have now and figure out what the current cost is
                for(int l = 0; l < curString.length(); l++){
                    pen += penalty(x.charAt(l), curString.charAt(l));
                }
                //if string isnt complete add more $ cost to even out
                pen += ((n-curString.length()) * 4);
                //add to our array the current cost of that string
                stringPen.add(pen);
            }

            //now look through the array and see what the best option is
            for (int k = 0; k < stringPen.size(); k++) {
                if(stringPen.get(k) < lowestPenalty){
                    //if its the lowest then update
                    lowestPenalty = stringPen.get(k);
                    in = k;
                }
            }

            stringPen.clear();
            //add to our z return the $ and the correct location
            z.insert(in,"$");
        }

        return z.toString();
    }

    /**
     * Given two characters a and b, we define a function penalty(a,b) as follows:
     * if a equals b, penalty(a,b) = 0. If a or b equals $, then penalty(a,b) = 4;
     * otherwise penalty(a,b) = 2.
     *
     * @param a character a
     * @param b character b
     * @return penalty cost
     */
    private static int penalty(Character a, Character b){

        if(a.equals(b)){
            return 0;
        }else if(a.equals('$') || b.equals('$')){
            return 4;
        }else{
            return 2;
        }

    }
}
