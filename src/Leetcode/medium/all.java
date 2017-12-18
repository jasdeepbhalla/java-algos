

/*
You are given two non-empty linked lists representing two non-negative integers. 
The digits are stored in reverse order and each of their nodes contain a single digit. 
Add the two numbers and return it as a linked list.
You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Example

Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8
Explanation: 342 + 465 = 807.
*/

public class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode c1 = l1;
        ListNode c2 = l2;
        ListNode sentinel = new ListNode(0);
        ListNode d = sentinel;
        int sum = 0;
        while (c1 != null || c2 != null) {
            sum = sum / 10;
            if (c1 != null) {
                sum = sum + c1.val;
                c1 = c1.next;
            }
            if (c2 != null) {
                sum = sum + c2.val;
                c2 = c2.next;
            }
            d.next = new ListNode(sum % 10);
            d = d.next;
        }
        if (sum / 10 == 1)
            d.next = new ListNode(1);
            
        return sentinel.next;
    }
}

// ----------------------------------------------------------------------------------------------------------------------
/*
The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: 
(you may want to display this pattern in a fixed font for better legibility)

P   A   H   N
A P L S I I G
Y   I   R

And then read line by line: "PAHNAPLSIIGYIR"
Write the code that will take a string and make this conversion given a number of rows:

string convert(string text, int nRows);
convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".
*/

public String convert(String s, int nRows) {
  char[] c = s.toCharArray();
  int len = c.length;
  StringBuffer[] sb = new StringBuffer[nRows];

  for (int i = 0; i < sb.length; i++) 
    sb[i] = new StringBuffer();

  int i = 0;
  while (i < len) {

      for (int idx = 0; idx < nRows && i < len; idx++) // vertically down
          sb[idx].append(c[i++]);

      for (int idx = nRows-2; idx >= 1 && i < len; idx--) // obliquely up
          sb[idx].append(c[i++]);
  }

  for (int idx = 1; idx < sb.length; idx++)
      sb[0].append(sb[idx]);

  return sb[0].toString();
}


// ----------------------------------------------------------------------------------------------------------------------

/*
Given a string, find the length of the longest substring without repeating characters.
Examples:

Given "abcabcbb", the answer is "abc", which the length is 3.

Given "bbbbb", the answer is "b", with the length of 1.

Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a substring, "pwke" is a subsequence and not a substring.


the basic idea is, 
keep a hashmap which stores the characters in string as keys and their positions as values, 
and keep two pointers which define the max substring. 
move the right pointer to scan through the string, and meanwhile update the hashmap. 
If the character is already in the hashmap, then move the left pointer to the right of the same character last found. 

Note that the two pointers can only move forward.

*/
public int lengthOfLongestSubstring(String s) {
    if (s.length()==0) 
      return 0;

    HashMap<Character, Integer> map = new HashMap<Character, Integer>();
    int max=0;

    for (int i=0, j=0; i<s.length(); ++i){

        if (map.containsKey(s.charAt(i))){
            j = Math.max(j,map.get(s.charAt(i))+1);
        }

        map.put(s.charAt(i),i);
        max = Math.max(max,i-j+1);

    }
    return max;
}

// ----------------------------------------------------------------------------------------------------------------------
/*
Given an integer, convert it to a roman numeral.
Input is guaranteed to be within the range from 1 to 3999.
*/

public static String intToRoman(int num) {
    String M[] = {"", "M", "MM", "MMM"};
    String C[] = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
    String X[] = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
    String I[] = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
    
    return M[num/1000] + C[(num%1000)/100] + X[(num%100)/10] + I[num%10];
}


// ----------------------------------------------------------------------------------------------------------------------
