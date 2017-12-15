
/*
Edit Distance
Given two strings how many minimum edits(update, remove or insert) is needed to convert one string to another

Time : O(m*n)
Space: O(m*n)
*/

// DP
public int dynamicEditDistance(char[] str1, char[] str2){
    int temp[][] = new int[str1.length+1][str2.length+1];

    for(int i=0; i < temp[0].length; i++){
        temp[0][i] = i;
    }

    for(int i=0; i < temp.length; i++){
        temp[i][0] = i;
    }

    for(int i=1;i <=str1.length; i++){
        for(int j=1; j <= str2.length; j++){
        
            if(str1[i-1] == str2[j-1]){
                temp[i][j] = temp[i-1][j-1];
            }else{
                temp[i][j] = 1 + min(
                                temp[i-1][j-1], // replace
                                temp[i-1][j],   // remove
                                temp[i][j-1]);  // insert
            }
        }
    }
    
    printActualEdits(temp, str1, str2);
    return temp[str1.length][str2.length];

}


public void printActualEdits(int T[][], char[] str1, char[] str2) {
    int i = T.length - 1;
    int j = T[0].length - 1;
    
    while(true) {
        if (i == 0 || j == 0) {
            break;
        }
        if (str1[i-1] == str2[j-1]) {
            i = i-1;
            j = j-1;
        } else if (T[i][j] == T[i-1][j-1] + 1){
            System.out.println("Edit " + str2[j-1] + " in string2 to " + str1[i-1] + " in string1");
            i = i-1;
            j = j-1;
        } else if (T[i][j] == T[i-1][j] + 1) {
            System.out.println("Delete in string1 " + str1[i-1]);
            i = i-1;
        } else if (T[i][j] == T[i][j-1] + 1){
            System.out.println("Delete in string2 " + str2[j-1]);
            j = j -1;
        } else {
            throw new IllegalArgumentException("Some wrong with given data");
        }

    }
}

private int min(int a,int b, int c){
    int l = Math.min(a, b);
    return Math.min(l, c);
}


// ****************************************************************************************************************


// Min Cost Path 
// Time: O(mn)
public int minCost(int cost[][], int m, int n)
    {
        int i, j;
        int tc[][]=new int[m+1][n+1];
 
        tc[0][0] = cost[0][0];
 
        /* Initialize first column of total cost(tc) array */
        for (i = 1; i <= m; i++)
            tc[i][0] = tc[i-1][0] + cost[i][0];
 
        /* Initialize first row of tc array */
        for (j = 1; j <= n; j++)
            tc[0][j] = tc[0][j-1] + cost[0][j];
 
        /* Construct rest of the tc array */
        for (i = 1; i <= m; i++)
            for (j = 1; j <= n; j++)
                tc[i][j] = min(tc[i-1][j-1], tc[i-1][j], tc[i][j-1]) + cost[i][j];
 
        return tc[m][n];
    }



// ****************************************************************************************************************



/*
The Longest Increasing Subsequence (LIS) problem 

is to find the length of the longest subsequence of a given sequence 
such that all elements of the subsequence are sorted in increasing order. 
For example, the length of LIS for {10, 22, 9, 33, 21, 50, 41, 60, 80} is 6 and LIS is {10, 22, 33, 50, 60, 80}
https://www.youtube.com/watch?v=CE2b_-XfVDk
https://github.com/mission-peace/interview/blob/master/src/com/interview/dynamic/LongestIncreasingSubsequence.java
*/


//DP O(n^2)
public int longestSubsequenceWithActualSolution(int arr[]){
{
      int n = arr.length;
      int lis[] = new int[n];
      int i,j,max = 0;

      /* Initialize LIS values for all indexes */
       for ( i = 0; i < n; i++ )
          lis[i] = 1;

       /* Compute optimized LIS values in bottom up manner */
       for ( i = 1; i < n; i++ )
          for ( j = 0; j < i; j++ ) 
             if ( arr[i] > arr[j] && lis[i] < lis[j] + 1)
                lis[i] = lis[j] + 1;

       /* Pick maximum of all LIS values */
       for ( i = 0; i < n; i++ )
          if ( max < lis[i] )
             max = lis[i];

        return max;
}
/*
Note that the time complexity of the above Dynamic Programming (DP) solution is O(n^2) 
and there is a O(nLogn) solution for the LIS problem explained below. 
*/
      

// Binary search based solution O(nLogn)      
public int lengthOfLIS(int[] nums) {
      
    int[] tails = new int[nums.length];
    int size = 0;
      
    for (int x : nums) {
        int i = 0; 
        int j = size;
          
        while (i != j) {
            int m = (i + j) / 2;
              
            if (tails[m] < x)
                i = m + 1;
            else
                j = m;
        }
          
        tails[i] = x;
        if (i == size) 
              ++size;
    }
      
    return size;
}
      
/*
tails is an array storing the smallest tail of all increasing subsequences with length i+1 in tails[i].
For example, say we have nums = [4,5,6,3], then all the available increasing subsequences are:
len = 1   :      [4], [5], [6], [3]   => tails[0] = 3
len = 2   :      [4, 5], [5, 6]       => tails[1] = 5
len = 3   :      [4, 5, 6]            => tails[2] = 6
We can easily prove that tails is a increasing array. 
Therefore it is possible to do a binary search in tails array to find the one needs update.
Each time we only do one of the two:
(1) if x is larger than all tails, append it, increase the size by 1
(2) if tails[i-1] < x <= tails[i], update tails[i]
Doing so will maintain the tails invariant. The the final answer is just the size.
*/

    
    
// ****************************************************************************************************************
// Egg Dropping    
public int calculate(int eggs, int floors){

    int T[][] = new int[eggs+1][floors+1];
    int c =0;
    
    // With 1 egg min num of attempts = no of floor
    for(int i=0; i <= floors; i++){
        T[1][i] = i;
    }

    for(int e = 2; e <= eggs; e++){
        for(int f = 1; f <=floors; f++){
            T[e][f] = Integer.MAX_VALUE;
            
            for(int k = 1; k <=f ; k++){
                c = 1 + Math.max(T[e-1][k-1], T[e][f-k]);
                
                if(c < T[e][f]){
                    T[e][f] = c;
                }
            }
        }
    }
    return T[eggs][floors];
}
      
