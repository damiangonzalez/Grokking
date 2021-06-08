package Primitives;
import java.util.LinkedList;
import java.util.Queue;


public class QueueClass {
    public static void main(String[] args) {
        Queue<Integer> q = new LinkedList<>();
        q.add(6); // or .offer()
        q.add(1);
        q.add(8);
        q.add(4);
        q.add(7);
        System.out.println("The queue is: " + q);
        int num1 = q.remove();
        System.out.println("The element deleted from the head is: " + num1);
        System.out.println("The queue after deletion is: " + q);
        int head = q.peek(); // poll() method returns and removes the element at the front the container
        System.out.println("The head of the queue is: " + head);
        int size = q.size();
        System.out.println("The size of the queue is: " + size);
    }
}