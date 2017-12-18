

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
