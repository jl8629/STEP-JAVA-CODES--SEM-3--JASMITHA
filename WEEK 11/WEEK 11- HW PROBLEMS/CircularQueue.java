import java.util.*;

public class CircularQueue {
    int front, rear, size;
    int[] queue;

    public CircularQueue(int size) {
        this.size = size;
        queue = new int[size];
        front = -1;
        rear = -1;
    }

    public void enqueue(int item) {
        if ((rear + 1) % size == front) {
            System.out.println("Queue is full");
            return;
        }
        if (front == -1)
            front = 0;
        rear = (rear + 1) % size;
        queue[rear] = item;
    }

    public int dequeue() {
        if (front == -1) {
            System.out.println("Queue is empty");
            return -1;
        }
        int item = queue[front];
        if (front == rear) {
            front = -1;
            rear = -1;
        } else {
            front = (front + 1) % size;
        }
        return item;
    }

    public void display() {
        if (front == -1) {
            System.out.println("Queue is empty");
            return;
        }
        System.out.print("Queue: ");
        int i = front;
        while (true) {
            System.out.print(queue[i] + " ");
            if (i == rear)
                break;
            i = (i + 1) % size;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        CircularQueue q = new CircularQueue(5);
        q.enqueue(10);
        q.enqueue(20);
        q.enqueue(30);
        q.display();
        System.out.println("Dequeued: " + q.dequeue());
        q.display();
        q.enqueue(40);
        q.enqueue(50);
        q.enqueue(60);
        q.display();
    }
}
