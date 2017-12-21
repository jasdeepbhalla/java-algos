
/*
Like linear search and binary search, ternary search is a searching technique 
that is used to determine the position of a specific value in an array. 

In binary search, the sorted array is divided into two parts while in ternary search, it is divided into 3 parts and 
then you determine in which part the element exists.

Ternary search, like binary search, is a divide-and-conquer algorithm. 
It is mandatory for the array (in which you will search for an element) to be sorted before you begin the search. 
In this search, after each iteration it neglects 1/3 part of the array and repeats the same operations on the remaining 2/3.
*/


public int ternary_search(int l,int r, int x)
{
    if(r>=l)
    {
        int mid1 = l + (r-l)/3;
        int mid2 = r -  (r-l)/3;
        
        if(ar[mid1] == x)
            return mid1;
            
        if(ar[mid2] == x)
            return mid2;
            
        if(x<ar[mid1])
            return ternary_search(l,mid1-1,x);
            
        else if(x>ar[mid2])
            return ternary_search(mid2+1,r,x);
            
        else
            return ternary_search(mid1+1,mid2-1,x);

    }
    return -1;
}

/*
Let us consider the following example to understand the code.

Let the sorted array be arr[] = {2,3,5,6,8,9,12,13,14} with indices from 0 to 8. 
You are required to find the position of x = 13 in this array. 

Divide the sorted array into the following 3 parts by evaluating the values of mid1 and mid2
{2,3,5}
{6,8,9}
{12,13,14}

Here ar[mid1]=5 and ar[mid2]=12. 

As 13 
	-> is not equal to ar[mid1] and ar[mid2] and 
	-> is also not smaller than ar[mid1], 

you can safely assume that it lies in the 3rd part of the array as it is greater than ar[mid2].

So Searching in third array then, run the ternary search again with l = 7 and r = 8.
Now, ar[mid1] = ar[7] = 13 and ar[mid2] = ar[8] = 14.As ar[mid1] = x, mid1 is the required answer.
If the value is not in the array, it returns −1 as the answer.

Complexity: O(logn), where N is the size of the array
*/


/*
Why is Binary Search preferred over Ternary Search?
From the first look, it seems the ternary search does less number of comparisons as it makes Log3n recursive calls, 
but binary search makes Log2n recursive calls. 

Let us take a closer look.
The following is recursive formula for counting comparisons in worst case of Binary Search.
   T(n) = T(n/2) + 2,  T(1) = 1
   
The following is recursive formula for counting comparisons in worst case of Ternary Search.
   T(n) = T(n/3) + 4, T(1) = 1
   
In binary search, there are 2Log2n + 1 comparisons in worst case. 
In ternary search, there are 4Log3n + 1 comparisons in worst case.

Time Complexity for Binary search = 2clog2n + O(1)
Time Complexity for Ternary search = 4clog3n + O(1)

Therefore, the comparison of Ternary and Binary Searches boils down the comparison of expressions 2Log3n and Log2n . 
The value of 2Log3n can be written as (2 / Log23) * Log2n . 
Since the value of (2 / Log23) is more than one, 
Ternary Search does more comparisons than Binary Search in worst case.
*/
