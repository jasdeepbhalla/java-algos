
/*
The Longest Increasing Subsequence (LIS) problem is to find the length of the longest subsequence of a given sequence 
such that all elements of the subsequence are sorted in increasing order. 

For example, the length of LIS for {10, 22, 9, 33, 21, 50, 41, 60, 80} is 6 and LIS is {10, 22, 33, 50, 60, 80}

https://www.youtube.com/watch?v=CE2b_-XfVDk
https://github.com/mission-peace/interview/blob/master/src/com/interview/dynamic/LongestIncreasingSubsequence.java
*/


//DP
public int longestSubsequenceWithActualSolution(int arr[]){
    int T[] = new int[arr.length];
    int actualSolution[] = new int[arr.length];
    
    for(int i=0; i < arr.length; i++){
        T[i] = 1;
        actualSolution[i] = i;
    }

    for(int i=1; i < arr.length; i++){
        for(int j=0; j < i; j++){
        
            if(arr[i] > arr[j]){
            
                if(T[j] + 1 > T[i]){
                
                    T[i] = T[j] + 1;
                    //set the actualSolution to point to guy before me
                    actualSolution[i] = j;
                }
            }
        }
    }

    //find the index of max number in T 
    int maxIndex = 0;
    for(int i=0; i < T.length; i++){
        if(T[i] > T[maxIndex]){
            maxIndex = i;
        }
    }

    //lets print the actual solution
    int t = maxIndex;
    int newT = maxIndex;
    
    do{
        t = newT;
        System.out.print(arr[t] + " ");
        newT = actualSolution[t];
    }while(t != newT);
    
    System.out.println();

    return T[maxIndex];
}
    
