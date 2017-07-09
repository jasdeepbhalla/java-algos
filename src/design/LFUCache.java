package design;

// Time: O(1)
// Space: O(n)

// Sol: https://discuss.leetcode.com/topic/69137/java-o-1-accept-solution-using-hashmap-doublelinkedlist-and-linkedhashset
/*
 * Two HashMaps are used, one to store <key, value> pair, another store the <key, node>.
 * 
 * Used double linked list to keep the frequency of each key. 
 * In each double linked list node, keys with the same count are saved using LinkedHashSet (to keep the order)
 * 
 * Every time, one key is referenced, 
 * first find the current node corresponding to the key, 
 * If the following node exist and the frequency is larger than one, 
 * add the key to the keys of the following node, else create a new node and add it following the current node.
 * 
 * 
 * All operations are guaranteed to be O(1).
 * 
 */

import java.util.HashMap;
import java.util.LinkedHashSet;

public class LFUCache {
	private Node head = null;
	private int cap = 0;
	private HashMap<Integer, Integer> valueHash = null;
	private HashMap<Integer, Node> nodeHash = null;

	public LFUCache(int capacity) {
		this.cap = capacity;
		valueHash = new HashMap<Integer, Integer>();
		nodeHash = new HashMap<Integer, Node>();
	}

	// once you get a key, you have to increase its frequency count
	public int get(int key) {
		if (valueHash.containsKey(key)) {
			increaseCount(key);
			return valueHash.get(key);
		}
		return -1;
	}

	public void put(int key, int value) {
		if (cap == 0) {
			return;
		}

		if (valueHash.containsKey(key)) {
			valueHash.put(key, value);
		} else {
			if (valueHash.size() < cap) {
				valueHash.put(key, value);
			} else {
				removeOld();
				valueHash.put(key, value);
			}
			addToHead(key);
		}
		increaseCount(key);

	}

	public void removeOld() {
		if (head == null) {
			return;
		}

		// Finding old value to remove
		int old = 0;
		for (int n : head.keys) {
			old = n;
			break;
		}

		// removing old value
		head.keys.remove(old);
		if (head.keys.size() == 0) {
			remove(head);
		}

		nodeHash.remove(old);
		valueHash.remove(old);

	}

	public void addToHead(int key) {
		if (head == null) {
			head = new Node(0);
			head.keys.add(key);

		} else if (head.count > 0) {
			Node newNode = new Node(0);
			newNode.keys.add(key);
			newNode.next = head;
			head.prev = newNode;
			head = newNode;

		} else {
			head.keys.add(key);

		}
		nodeHash.put(key, head);

	}

	public void increaseCount(int key) {
		Node node = nodeHash.get(key);
		// as now the count would increase by 1 as it is referenced now, thus
		// removing it
		node.keys.remove(key);

		if (node.next == null) {
			node.next = new Node(node.count + 1);
			node.next.prev = node;
			node.next.keys.add(key);

		} else if (node.next.count == node.count + 1) {
			node.next.keys.add(key);

		} else {
			Node tmp = new Node(node.count + 1);
			tmp.keys.add(key);
			tmp.prev = node;
			tmp.next = node.next;
			node.next.prev = tmp;
			node.next = tmp;
		}

		nodeHash.put(key, node.next);
		// if after this increment,
		// the node's keys size becomes 0, remove the node
		if (node.keys.size() == 0) {
			remove(node);
		}

	}

	public void remove(Node node) {
		if (node.prev == null) {
			head = node.next;
		} else {
			node.prev.next = node.next;
		}

		if (node.next != null) {
			node.next.prev = node.prev;
		}

	}

}

class Node {
	public int count = 0;
	public LinkedHashSet<Integer> keys = null;
	public Node prev = null;
	public Node next = null;

	public Node(int count) {
		this.count = count;
		keys = new LinkedHashSet<Integer>();
		prev = next = null;
	}

}