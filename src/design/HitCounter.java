package design;

/*
 * https://discuss.leetcode.com/topic/48752/simple-java-solution-with-explanation
 * 
 * Each time we call the function getHits( ), 
 * we have to delete the elements which hits beyond 5 mins (300). 
 * The result would be the length of the queue
 * 
 */

import java.util.LinkedList;
import java.util.Queue;

public class HitCounter {

	Queue<Integer> q = null;

	public HitCounter() {
		q = new LinkedList<Integer>();
	}

	public void hit(int timestamp) {
		q.offer(timestamp);
	}

	public int getHits(int timestamp) {
		while (!q.isEmpty() && timestamp - q.peek() >= 300) {
			q.poll();
		}

		return q.size();
	}
}
