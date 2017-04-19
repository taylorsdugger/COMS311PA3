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
     * at your code. For this, ﬁrst deﬁne the recurrence relation. Then arrive at an
     * iterative solution. Your code must be iterative, not recursive and should not use
     * use memoization. Otherwise you will receive zero credit.
     *
     * @param M Matrix m
     * @return min-cost vertical cut
     */
    public static ArrayList<Integer> minCostVC(int[][] M){

        ArrayList<Integer> minVC;
        

        return null;
    }

    /**
     * stringAlignment(String x, String y) . Assume that x is a string of length n
     * and y is a string of length m such that n ≥ m. This method returns a string z
     * (obtained by inserting $ at n − m indices in y) such that AlignCost(x,z) ≤ AlignCost(x,y)
     * over all possible y. You may assume that length of x is at least the length of y and
     * neither of x or y has the character $. Note that the length of the returned string z
     * must equal the length of x. You must use dynamic programming paradigm to arrive at
     * your code. For this, ﬁrst deﬁne the recurrence relation. Then arrive at an
     * iterative solution. Your code must be iterative, not recursive and should not use
     * use memoization. Otherwise you will receive zero credit.
     *
     * @param x String x of length n
     * @param y string y of length m
     * @return string z such that AlignCost(x,z) ≤ AlignCost(x,y) over all possible y
     */
    public static String stringAlignment(String x, String y){

        if(x.equals(y)){
            return y;
        }

        int n = x.length(); //length n
        String z = "";

        for(int i = 0; i < n; i++){
            char a = y.charAt(i);

        }

        return null;
    }

    /**
     * Given two characters a and b, we deﬁne a function penalty(a,b) as follows:
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
