/*
Given a contiguous sequence of numbers in which each number repeats thrice, there is exactly one missing number. 
Find the missing number.

eg: 11122333 : Missing number 2
11122233344455666 Missing number 5
*/






/*
Given a compressed string in which a number followed by [] indicate how many times those characters occur, 
decompress the string.

Eg. : a3[b2[c1[d]]]e will be decompressed as abcdcdbcdcdbcdcde.
Assume the string is well formed and number will always be followed by a [].
*/






/*
Given a tree representation of a html parsed output, wherein every block is a node in the tree, 
find if two html docs contain the same text.

struct Node {
       string value;
       bool isMetadata;
       vector<Node*> children;
};

Doc1:
    <html>
      <head>sample</head>
      <body>
      Hello world
      </body>
    </html> 

will be represented as 
Node1: 
   value: sample, 
   children: <body> 
   isMetadata: true 
 
Node2: 
  value: <body> 
  children:
  isMetadata: true 
  
Node3: 
  value:
  children: Hello world 
  isMetadata: true 
  
Node4: 
  value: Hello world 
  isMetadata: false


Doc2:
    <html>
      <body>Hello world</body>
    </html>

and both documents are equivalent since they contain the same data.

Note: the case of both documents fitting in memory is trivial, since it is just walking this tree list, 
consolidating data and comparing. 

As a follow up, solve the case where the whole documents may not be able to fit in memory.
*/







/*

Given a 2D matrix M X N, support two operations:
Query(row1, col1, row2, col2) such that I get the sum of all numbers 
in the rectangle ((row1, col1), (row1, col2), (row2, col1), (row2, col2)) 
and
Update(row, col) to a new number

And query is a very frequent operation and update is a rare operation, 
so query should be really fast, but update can be slower.

Follow up: How would you solve this in a distributed fashion
*/







/*

Verify if a given matrix is a Toeplitz matrix:
Follow up:
assume that the whole matrix cannot be fit in memory and should be read from a file, 
assume that a few rows and all columns can be read in, how to verify?

In linear algebra, a Toeplitz matrix or diagonal-constant matrix, 
is a matrix in which each descending diagonal from left to right is constant. 
For instance, the following matrix is a Toeplitz matrix:
9     1     3     2
2     9     1     3
3     2     9     1
1     3     2     9

In general, any n×n matrix mat[][] is a Toeplitz matrix 
if every cell mat[i][j] is same as mat[i-1][j-1], mat[i+1][j+1], mat[i-2][j-2], mat[i+2][j+2], .. 
for every cell mat[i][j] and all the valid cells mat[i+k][j+k] or mat[i-k][j-k]

The idea is very simple. For each element of first row and first column(or last row and last column) in the matrix, 
we check if descending diagonal starting from that element have same values or not. 
If we found any diagonal having different values, we return false.

*/

// Java program to check whether given matrix
// is a Toeplitz matrix or not
import java.io.*;
 
class GFG 
{
    public static int N = 5;
    public static int M = 4;
     
    // Function to check if all elements present in
    // descending diagonal starting from position
    // (i, j) in the matrix are all same or not
    static boolean checkDiagonal(int mat[][], int i, int j)
    {
        int res = mat[i][j];
        while (++i < N && ++j < M)
        {
            // mismatch found
            if (mat[i][j] != res)
                return false;
        }
  
        // we only reach here when all elements
        // in given diagonal are same
        return true;
    }
     
    // Function to check whether given matrix is a
    // Toeplitz matrix or not
    static boolean isToepliz(int mat[][])
    {
        // do for each element in first row
        for (int i = 0; i < M; i++)
        {
            // check descending diagonal starting from
            // position (0, j) in the matrix
            if (!checkDiagonal(mat, 0, i))
                return false;
        }
  
        // do for each element in first column
        for (int i = 1; i < N; i++)
        {
            // check descending diagonal starting from
            // position (i, 0) in the matrix
            if (!checkDiagonal(mat, i, 0))
                return false;
        }
  
        // we only reach here when each descending
        // diagonal from left to right is same
        return true;
    }
     
    // driver program
    public static void main (String[] args) 
    {
        int mat[][] = { { 6, 7, 8, 9 },
                        { 4, 6, 7, 8 },
                        { 1, 4, 6, 7 },
                        { 0, 1, 4, 6 },
                        { 2, 0, 1, 4 }
                      };
  
        if (isToepliz(mat))
            System.out.println("Matrix is a Toepliz ");
        else
            System.out.println("Matrix is not a Toepliz ");
    }
}

//The time complexity of this solution would be O(n2) as we are traversing each element in the matrix once only.
