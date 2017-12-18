

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

/*
TinyURL is a URL shortening service where you enter a URL such as https://leetcode.com/problems/design-tinyurl 
and it returns a short URL such as http://tinyurl.com/4e9iAk.

Design the encode and decode methods for the TinyURL service. 
There is no restriction on how your encode/decode algorithm should work. 
You just need to ensure that a URL can be encoded to a tiny URL and the tiny URL can be decoded to the original URL.

*/

public class Codec {
    Map<String, String> index = new HashMap<String, String>();
    Map<String, String> revIndex = new HashMap<String, String>();
    static String BASE_HOST = "http://tinyurl.com/";
    
    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        if (revIndex.containsKey(longUrl)) 
            return BASE_HOST + revIndex.get(longUrl);
        
        String charSet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        String key = null;
        
        do {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 6; i++) {
                int r = (int) (Math.random() * charSet.length());
                sb.append(charSet.charAt(r));
            }
            key = sb.toString();
            
        } while (index.containsKey(key));
        
        index.put(key, longUrl);
        revIndex.put(longUrl, key);
        return BASE_HOST + key;
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        return index.get(shortUrl.replace(BASE_HOST, ""));
    }
}


// ----------------------------------------------------------------------------------------------------------------------

// How would you design a URL shortening service that is similar to TinyURL?
// Complete Sol: https://discuss.leetcode.com/topic/95853/a-complete-solution-for-tinyurl-leetcode-system-design/2

public class URLService {
    HashMap<String, Integer> ltos;
    HashMap<Integer, String> stol;
    static int COUNTER;
    String elements;
    
    URLService() {
        ltos = new HashMap<String, Integer>();
        stol = new HashMap<Integer, String>();
        COUNTER = 1;
        elements = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    }
    
    public String longToShort(String url) {
        String shorturl = base10ToBase62(COUNTER);
        ltos.put(url, COUNTER);
        stol.put(COUNTER, url);
        COUNTER++;
        
        return "http://tiny.url/" + shorturl;
    }
    
    public String shortToLong(String url) {
        url = url.substring("http://tiny.url/".length());
        int n = base62ToBase10(url);
        return stol.get(n);
    }
    
    public int base62ToBase10(String s) {
        int n = 0;
        
        for (int i = 0; i < s.length(); i++) {
            n = n * 62 + convert(s.charAt(i));
        }
        
        return n;
        
    }
    
    public int convert(char c) {
        if (c >= '0' && c <= '9')
            return c - '0';
        
        if (c >= 'a' && c <= 'z') {
            return c - 'a' + 10;
        }
        
        if (c >= 'A' && c <= 'Z') {
            return c - 'A' + 36;
        }
        
        return -1;
    }
    
    public String base10ToBase62(int n) {
        StringBuilder sb = new StringBuilder();
        
        while (n != 0) {
            sb.insert(0, elements.charAt(n % 62));
            n /= 62;
        }
        
        while (sb.length() != 6) {
            sb.insert(0, '0');
        }
        
        return sb.toString();
    }
}

// ----------------------------------------------------------------------------------------------------------------------

/*
Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.

Example:
Input: "babad"
Output: "bab"
Note: "aba" is also a valid answer.

Example:
Input: "cbbd"
Output: "bb"
*/

public class Solution {
    
    private int lo, maxLen;

    public String longestPalindrome(String s) {
        int len = s.length();
        if (len < 2)
            return s;

        for (int i = 0; i < len-1; i++) {
            extendPalindrome(s, i, i);  //assume odd length, try to extend Palindrome as possible
            extendPalindrome(s, i, i+1); //assume even length.
        }
        return s.substring(lo, lo + maxLen);
    }

    private void extendPalindrome(String s, int j, int k) {
        while (j >= 0 && k < s.length() && s.charAt(j) == s.charAt(k)) {
            j--;
            k++;
        }
        if (maxLen < k - j - 1) {
            lo = j + 1;
            maxLen = k - j - 1;
        }
    }
}


// ----------------------------------------------------------------------------------------------------------------------
/*
Given an input string, reverse the string word by word.

For example,
Given s = "the sky is blue",
return "blue is sky the".
//First, reverse the whole string, then reverse each word.
*/

public class Solution {
  
  public String reverseWords(String s) {
    if (s == null) return null;
    
    char[] a = s.toCharArray();
    int n = a.length;
    
    // step 1. reverse the whole string
    reverse(a, 0, n - 1);
    // step 2. reverse each word
    reverseWords(a, n);
    // step 3. clean up spaces
    return cleanSpaces(a, n);
  }
  
  void reverseWords(char[] a, int n) {
    int i = 0, j = 0;
      
    while (i < n) {
      while (i < j || i < n && a[i] == ' ') i++; // skip spaces
      while (j < i || j < n && a[j] != ' ') j++; // skip non spaces
      reverse(a, i, j - 1);                      // reverse the word
    }
  }
  
  // trim leading, trailing and multiple spaces
  String cleanSpaces(char[] a, int n) {
    int i = 0, j = 0;
      
    while (j < n) {
      while (j < n && a[j] == ' ') j++;             // skip spaces
      while (j < n && a[j] != ' ') a[i++] = a[j++]; // keep non spaces
      while (j < n && a[j] == ' ') j++;             // skip spaces
      if (j < n) a[i++] = ' ';                      // keep only one space
    }
  
    return new String(a).substring(0, i);
  }
  
  // reverse a[] from a[i] to a[j]
  private void reverse(char[] a, int i, int j) {
    while (i < j) {
      char t = a[i];
      a[i++] = a[j];
      a[j--] = t;
    }
  }
  
}

// ----------------------------------------------------------------------------------------------------------------------
//Sort a linked list in O(n log n) time using constant space complexity.

public class Solution {
  
  public ListNode sortList(ListNode head) {
    if (head == null || head.next == null)
      return head;
        
    // step 1. cut the list to two halves
    ListNode prev = null, slow = head, fast = head;
    
    while (fast != null && fast.next != null) {
      prev = slow;
      slow = slow.next;
      fast = fast.next.next;
    }
    
    prev.next = null;
    
    // step 2. sort each half
    ListNode l1 = sortList(head);
    ListNode l2 = sortList(slow);
    
    // step 3. merge l1 and l2
    return merge(l1, l2);
  }
  
  ListNode merge(ListNode l1, ListNode l2) {
    ListNode l = new ListNode(0), p = l;
    
    while (l1 != null && l2 != null) {
      if (l1.val < l2.val) {
        p.next = l1;
        l1 = l1.next;
      } else {
        p.next = l2;
        l2 = l2.next;
      }
      p = p.next;
    }
    
    if (l1 != null)
      p.next = l1;
    
    if (l2 != null)
      p.next = l2;
    
    return l.next;
  }

}

// ----------------------------------------------------------------------------------------------------------------------
/*
Suppose we abstract our file system by a string in the following manner:

The string "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext" represents:

dir
    subdir1
    subdir2
        file.ext
The directory dir contains an empty sub-directory subdir1 and a sub-directory subdir2 containing a file file.ext.

The string "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext" represents:

dir
    subdir1
        file1.ext
        subsubdir1
    subdir2
        subsubdir2
            file2.ext
            
The directory dir contains two sub-directories subdir1 and subdir2. 
subdir1 contains a file file1.ext and an empty second-level sub-directory subsubdir1. 
subdir2 contains a second-level sub-directory subsubdir2 containing a file file2.ext.

We are interested in finding the longest (number of characters) absolute path to a file within our file system. 
For example, in the second example above, 
the longest absolute path is "dir/subdir2/subsubdir2/file2.ext", 
and its length is 32 (not including the double quotes).

Given a string representing the file system in the above format, 
return the length of the longest absolute path to file in the abstracted file system. 
If there is no file in the system, return 0.

Note:
The name of a file contains at least a . and an extension.
The name of a directory or sub-directory will not contain a ..
Time complexity required: O(n) where n is the size of the input string.

Notice that a/aa/aaa/file1.txt is not the longest file path, if there is another path aaaaaaaaaaaaaaaaaaaaa/sth.png.
*/

public int lengthLongestPath(String input) {
    String[] paths = input.split("\n");
    int[] stack = new int[paths.length+1];
    int maxLen = 0;
    
    for(String s:paths){
        int lev = s.lastIndexOf("\t") + 1; 
        int curLen = stack[lev+1] = stack[lev] + s.length() - lev +1 ;
        
        if(s.contains(".")) 
            maxLen = Math.max(maxLen, curLen-1);
    }
    
    return maxLen;
}

// ----------------------------------------------------------------------------------------------------------------------
/*
Given a text file file.txt, transpose its content.
You may assume that each row has the same number of columns and each field is separated by the ' ' character.

For example, 
if file.txt has the following content:
name age
alice 21
ryan 30

Output the following:
name alice ryan
age 21 30
*/

awk '
{
    for (i = 1; i <= NF; i++) {
        if(NR == 1) {
            s[i] = $i;
        } else {
            s[i] = s[i] " " $i;
        }
    }
}
END {
    for (i = 1; s[i] != ""; i++) {
        print s[i];
    }
}' file.txt
    

// ----------------------------------------------------------------------------------------------------------------------
/*
Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? 
Find all unique triplets in the array which gives the sum of zero.

Note: The solution set must not contain duplicate triplets.
For example, given array S = [-1, 0, 1, 2, -1, -4],
A solution set is:
[
  [-1, 0, 1],
  [-1, -1, 2]
]


The idea is to sort an input array and then run through all indices of a possible first element of a triplet. 
For each possible first element we make a standard bi-directional 2Sum sweep of the remaining part of the array. 
Also we want to skip equal elements to avoid duplicates in the answer without making a set or smth like that.
*/    

public List<List<Integer>> threeSum(int[] num) {
    Arrays.sort(num);
    
    List<List<Integer>> res = new LinkedList<>(); 
    
    for (int i = 0; i < num.length-2; i++) {
        if (i == 0 || (i > 0 && num[i] != num[i-1])) {
            int lo = i+1, hi = num.length-1; 
            int sum = 0 - num[i];
            
            while (lo < hi) {
                if (num[lo] + num[hi] == sum) {
                    res.add(Arrays.asList(num[i], num[lo], num[hi]));
                    
                    while (lo < hi && num[lo] == num[lo+1]) 
                        lo++;
                    
                    while (lo < hi && num[hi] == num[hi-1]) 
                        hi--;
                    
                    lo++; 
                    hi--;
                } else if (num[lo] + num[hi] < sum) 
                    lo++;
                
                else hi--;
           }
        }
    }
    return res;
}
