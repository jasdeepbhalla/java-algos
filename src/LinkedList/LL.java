
// Find middle element in LL
ListNode middleNode(ListNode head) {
    ListNode slow = head, fast = head;
    while (fast != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;
    }
    return slow;
}

// ------------------------------------------------------------------------------------------------------------
// Add two numbers
//Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
//Output: 7 -> 0 -> 8
//Explanation: 342 + 465 = 807.
public class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode c1 = l1;
        ListNode c2 = l2;
        ListNode sentinel = new ListNode(0);
        ListNode d = sentinel;
        int sum = 0;
        while (c1 != null || c2 != null) {
            sum /= 10;
            if (c1 != null) {
                sum += c1.val;
                c1 = c1.next;
            }
            if (c2 != null) {
                sum += c2.val;
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

// ------------------------------------------------------------------------------------------------------------
// Add two numbers 2 - O(n)
You are given two non-empty linked lists representing two non-negative integers. 
The most significant digit comes first and each of their nodes contain a single digit. 
Add the two numbers and return it as a linked list.
You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Follow up:
What if you cannot modify the input lists? In other words, reversing the lists is not allowed.

Example:

Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 8 -> 0 -> 7
	
public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
	Stack<Integer> s1 = new Stack<Integer>();
	Stack<Integer> s2 = new Stack<Integer>();

	while(l1 != null) {
	    s1.push(l1.val);
	    l1 = l1.next;
	};
	while(l2 != null) {
	    s2.push(l2.val);
	    l2 = l2.next;
	}

	int sum = 0;
	ListNode list = new ListNode(0);
	while (!s1.empty() || !s2.empty()) {
	    if (!s1.empty()) sum += s1.pop();
	    if (!s2.empty()) sum += s2.pop();
	    list.val = sum % 10;
	    ListNode head = new ListNode(sum / 10);
	    head.next = list;
	    list = head;
	    sum /= 10;
	}
	return list.val == 0 ? list.next : list;
}

// ------------------------------------------------------------------------------------------------------------
// Merge two sorted LLs
Input: 1->2->4, 1->3->4
Output: 1->1->2->3->4->4
public class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null){
            return l2;
        }
        if(l2 == null){
            return l1;
        }
        
        ListNode mergeHead;
        if(l1.val < l2.val){
            mergeHead = l1;
            mergeHead.next = mergeTwoLists(l1.next, l2);
        }
        else{
            mergeHead = l2;
            mergeHead.next = mergeTwoLists(l1, l2.next);
        }
        return mergeHead;
    }
}

// ------------------------------------------------------------------------------------------------------------
// Copy list with random pointer (deep copy of the list)
public RandomListNode copyRandomList(RandomListNode head) {
  RandomListNode iter = head, next;

  // First round: make copy of each node,
  // and link them together side-by-side in a single list.
  while (iter != null) {
    next = iter.next;

    RandomListNode copy = new RandomListNode(iter.label);
    iter.next = copy;
    copy.next = next;

    iter = next;
  }

  // Second round: assign random pointers for the copy nodes.
  iter = head;
  while (iter != null) {
    if (iter.random != null) {
      iter.next.random = iter.random.next;
    }
    iter = iter.next.next;
  }

  // Third round: restore the original list, and extract the copy list.
  iter = head;
  RandomListNode pseudoHead = new RandomListNode(0);
  RandomListNode copy, copyIter = pseudoHead;

  while (iter != null) {
    next = iter.next.next;

    // extract the copy
    copy = iter.next;
    copyIter.next = copy;
    copyIter = copy;

    // restore the original list
    iter.next = next;

    iter = next;
  }

  return pseudoHead.next;
}

// ------------------------------------------------------------------------------------------------------------
// reverse LL
Input: 1->2->3->4->5->NULL
Output: 5->4->3->2->1->NULL

public ListNode reverseList(ListNode head) {
    /* iterative solution */
    ListNode newHead = null;
    while (head != null) {
        ListNode next = head.next;
        head.next = newHead;
        newHead = head;
        head = next;
    }
    return newHead;
}

// recursive
public ListNode reverseList(ListNode head) {
    /* recursive solution */
    return reverseListInt(head, null);
}

private ListNode reverseListInt(ListNode head, ListNode newHead) {
    if (head == null)
        return newHead;
    ListNode next = head.next;
    head.next = newHead;
    return reverseListInt(next, head);
}

// ------------------------------------------------------------------------------------------------------------
// LL 
public boolean hasCycle(ListNode head) {
    if(head==null) 
        return false;
    ListNode walker = head;
    ListNode runner = head;
    
    while(runner.next!=null && runner.next.next!=null) {
        walker = walker.next;
        runner = runner.next.next;
        
        if(walker==runner) 
            return true;
    }
    return false;
}

// Cycle 2 O(1) space
Given a linked list, return the node where the cycle begins. If there is no cycle, return null.

public ListNode detectCycle(ListNode head) {
    ListNode slow = head;
    ListNode fast = head;

    while (fast!=null && fast.next!=null){
        fast = fast.next.next;
        slow = slow.next;

        if (fast == slow){
            ListNode slow2 = head; 
            while (slow2 != slow){
                slow = slow.next;
                slow2 = slow2.next;
            }
            return slow;
        }
    }
    return null;
}    
    
// ------------------------------------------------------------------------------------------------------------
// Merge K sorted LLs
Input:
[
  1->4->5,
  1->3->4,
  2->6
]
Output: 1->1->2->3->4->4->5->6
    
public class Solution {
    public ListNode mergeKLists(List<ListNode> lists) {
        if (lists==null||lists.size()==0) return null;
        
        PriorityQueue<ListNode> queue= new PriorityQueue<ListNode>(lists.size(),new Comparator<ListNode>(){
            @Override
            public int compare(ListNode o1,ListNode o2){
                if (o1.val<o2.val)
                    return -1;
                else if (o1.val==o2.val)
                    return 0;
                else 
                    return 1;
            }
        });
        
        ListNode dummy = new ListNode(0);
        ListNode tail=dummy;
        
        for (ListNode node:lists)
            if (node!=null)
                queue.add(node);
            
        while (!queue.isEmpty()){
            tail.next=queue.poll();
            tail=tail.next;
            
            if (tail.next!=null)
                queue.add(tail.next);
        }
        return dummy.next;
    }
}

// ------------------------------------------------------------------------------------------------------------
// Sort Llist O(nlogn) - merge sort on LL
Example 1:

Input: 4->2->1->3
Output: 1->2->3->4
Example 2:

Input: -1->5->3->4->0
Output: -1->0->3->4->5
    
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
    
// ------------------------------------------------------------------------------------------------------------
Insertion Sort LL - O(1) space
Algorithm of Insertion Sort:

Insertion sort iterates, consuming one input element each repetition, and growing a sorted output list.
At each iteration, insertion sort removes one element from the input data, finds the location it belongs within the sorted list, and inserts it there.
It repeats until no input elements remain.

Example 1:

Input: 4->2->1->3
Output: 1->2->3->4
    
public ListNode insertionSortList(ListNode head) {
		if( head == null ){
			return head;
		}
		
		ListNode helper = new ListNode(0); //new starter of the sorted list
		ListNode cur = head; //the node will be inserted
		ListNode pre = helper; //insert node between pre and pre.next
		ListNode next = null; //the next node will be inserted
		//not the end of input list
		while( cur != null ){
			next = cur.next;
			//find the right place to insert
			while( pre.next != null && pre.next.val < cur.val ){
				pre = pre.next;
			}
			//insert between pre and pre.next
			cur.next = pre.next;
			pre.next = cur;
			pre = helper;
			cur = next;
		}
		
		return helper.next;
	}

// ------------------------------------------------------------------------------------------------------------
// Odd Even LL

Example 1:
Given a singly linked list, group all odd nodes together followed by the even nodes
Input: 1->2->3->4->5->NULL
Output: 1->3->5->2->4->NULL
Example 2:

Input: 2->1->3->5->6->4->7->NULL
Output: 2->3->6->7->1->5->4->NULL
O(n) O(1)

public ListNode oddEvenList(ListNode head) {
    if (head != null) {
    
        ListNode odd = head; 
        ListNode even = head.next; 
        ListNode evenHead = even; 
    
        while (even != null && even.next != null) {
            odd.next = odd.next.next; 
            even.next = even.next.next; 
            odd = odd.next;
            even = even.next;
        }
        odd.next = evenHead; 
    }
    return head;
}


// ------------------------------------------------------------------------------------------------------------
Palindrome LL
Given a singly linked list, determine if it is a palindrome.

Example 1:

Input: 1->2
Output: false
Example 2:

Input: 1->2->2->1
Output: true
    
public boolean isPalindrome(ListNode head) {
    ListNode fast = head, slow = head;
    while (fast != null && fast.next != null) {
        fast = fast.next.next;
        slow = slow.next;
    }
    if (fast != null) { // odd nodes: let right half smaller
        slow = slow.next;
    }
    slow = reverse(slow);
    fast = head;
    
    while (slow != null) {
        if (fast.val != slow.val) {
            return false;
        }
        fast = fast.next;
        slow = slow.next;
    }
    return true;
}

public ListNode reverse(ListNode head) {
    ListNode prev = null;
    while (head != null) {
        ListNode next = head.next;
        head.next = prev;
        prev = head;
        head = next;
    }
    return prev;
}

// ------------------------------------------------------------------------------------------------------------
Intersection of two LL
A:          a1 → a2
                   ↘
                     c1 → c2 → c3
                   ↗            
B:     b1 → b2 → b3
begin to intersect at node c1.

1, Get the length of the two lists.

2, Align them to the same start point.

3, Move them together until finding the intersection point, or the end null

public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    int lenA = length(headA), lenB = length(headB);
    // move headA and headB to the same start point
    while (lenA > lenB) {
        headA = headA.next;
        lenA--;
    }
    while (lenA < lenB) {
        headB = headB.next;
        lenB--;
    }
    // find the intersection until end
    while (headA != headB) {
        headA = headA.next;
        headB = headB.next;
    }
    return headA;
}

private int length(ListNode node) {
    int length = 0;
    while (node != null) {
        node = node.next;
        length++;
    }
    return length;
}

// ------------------------------------------------------------------------------------------------------------
Swap nodes in pairs
Given a linked list, swap every two adjacent nodes and return its head.
Given 1->2->3->4, you should return the list as 2->1->4->3.

public ListNode swapPairs(ListNode head) {
    ListNode dummy = new ListNode(0);
    dummy.next = head;
    ListNode current = dummy;
    
    while (current.next != null && current.next.next != null) {
        ListNode first = current.next;
        ListNode second = current.next.next;
        first.next = second.next;
        current.next = second;
        current.next.next = first;
        current = current.next.next;
    }
    return dummy.next;
}

// ------------------------------------------------------------------------------------------------------------
// Reorder List
Given a singly linked list L: L0→L1→…→Ln-1→Ln,
reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…
You may not modify the values in the list's nodes, only nodes itself may be changed.
Given 1->2->3->4, reorder it to 1->4->2->3.
Given 1->2->3->4->5, reorder it to 1->5->2->4->3.

public void reorderList(ListNode head) {
    if(head==null||head.next==null) return;

    //Find the middle of the list
    ListNode p1=head;
    ListNode p2=head;
    while(p2.next!=null&&p2.next.next!=null){ 
	p1=p1.next;
	p2=p2.next.next;
    }

    //Reverse the half after middle  1->2->3->4->5->6 to 1->2->3->6->5->4
    ListNode preMiddle=p1;
    ListNode preCurrent=p1.next;
    while(preCurrent.next!=null){
	ListNode current=preCurrent.next;
	preCurrent.next=current.next;
	current.next=preMiddle.next;
	preMiddle.next=current;
    }

    //Start reorder one by one  1->2->3->6->5->4 to 1->6->2->5->3->4
    p1=head;
    p2=preMiddle.next;
    while(p1!=preMiddle){
	preMiddle.next=p2.next;
	p2.next=p1.next;
	p1.next=p2;
	p1=p2.next;
	p2=preMiddle.next;
    }
}

// ------------------------------------------------------------------------------------------------------------
// Remove Nth node from end of list
Given linked list: 1->2->3->4->5, and n = 2.
After removing the second node from the end, the linked list becomes 1->2->3->5.

public ListNode removeNthFromEnd(ListNode head, int n) {
    
    ListNode start = new ListNode(0);
    ListNode slow = start, fast = start;
    slow.next = head;
    
    //Move fast in front so that the gap between slow and fast becomes n
    for(int i=1; i<=n+1; i++)   {
        fast = fast.next;
    }
    //Move fast to the end, maintaining the gap
    while(fast != null) {
        slow = slow.next;
        fast = fast.next;
    }
    //Skip the desired node
    slow.next = slow.next.next;
    return start.next;
}

// ------------------------------------------------------------------------------------------------------------
// Remove dups from sorted list
Given a sorted linked list, delete all duplicates such that each element appear only once.
Input: 1->1->2
Output: 1->2
	
public ListNode deleteDuplicates(ListNode head) {
	ListNode node = head;
	while (node != null && node.next != null) {
	    if (node.val == node.next.val) 
		node.next = node.next.next;
	    else 
		node = node.next;
	}
	return head;
}

// ------------------------------------------------------------------------------------------------------------
// Remove LL elements
Remove all elements from a linked list of integers that have value val.
Input:  1->2->6->3->4->5->6, val = 6
Output: 1->2->3->4->5
	
public ListNode removeElements(ListNode head, int val) {
	ListNode fakeHead = new ListNode(-1);
	fakeHead.next = head;
	ListNode curr = head, prev = fakeHead;
	
	while (curr != null) {
	    if (curr.val == val) {
		prev.next = curr.next;
	    } else {
		prev = prev.next;
	    }
	    curr = curr.next;
	}
	return fakeHead.next;
}

// ------------------------------------------------------------------------------------------------------------
Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
Given this linked list: 1->2->3->4->5
For k = 2, you should return: 2->1->4->3->5
For k = 3, you should return: 3->2->1->4->5
	
