
/*
The Longest Increasing Subsequence (LIS) problem is to find the length of the longest subsequence of a given sequence 
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
      

