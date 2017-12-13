/*
Multiply two integers without using multiplication, division and bitwise operators, and no loops
*/

public int multiply(int x, int y)
{
   if(y == 0)
     return 0;

  /* Add x one by one */
   if(y > 0 )
     return (x + multiply(x, y-1));
  
  /* the case where y is negative */
   if(y < 0 )
     return -multiply(x, -y);
}
 
