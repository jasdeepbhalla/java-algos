
/*
The Longest Increasing Subsequence (LIS) problem is to find the length of the longest subsequence of a given sequence 
such that all elements of the subsequence are sorted in increasing order. 

For example, the length of LIS for {10, 22, 9, 33, 21, 50, 41, 60, 80} is 6 and LIS is {10, 22, 33, 50, 60, 80}

https://www.youtube.com/watch?v=CE2b_-XfVDk
https://github.com/mission-peace/interview/blob/master/src/com/interview/dynamic/LongestIncreasingSubsequence.java
*/


//DP
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

