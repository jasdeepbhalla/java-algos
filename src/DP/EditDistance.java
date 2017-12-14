
/*
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
