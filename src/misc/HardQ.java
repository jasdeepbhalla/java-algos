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

*/
