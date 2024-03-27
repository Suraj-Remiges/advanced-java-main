import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

class ProducerConsumer {
    private final Queue<String> queue;
    private final String filePath;

    public ProducerConsumer(String filePath) {
        this.queue = new LinkedList<>();
        this.filePath = filePath;
    }

    public void produce() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                synchronized (queue) {
                    queue.add(line);
                    System.out.println("Produced: " + line);
                    queue.notify(); // Notify consumer thread
                }
                Thread.sleep(100); // Simulate processing time
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void consume() {
        while (true) {
            synchronized (queue) {
                while (queue.isEmpty()) {
                    try {
                        queue.wait(); // Wait for producer to produce items
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                String line = queue.poll();
                if (line != null) {
                    String[] words = line.split("\\s+");
                    int wordCount = words.length;
                    System.out.println("Consumed: " + line + ", Word Count: " + wordCount);
                }
            }
        }
    }

    // public void consume2() {
    //     while (true) {
    //         synchronized (queue) {
    //             while (queue.isEmpty()) {
    //                 try {
    //                     queue.wait(); // Wait for producer to produce items
    //                 } catch (InterruptedException e) {
    //                     e.printStackTrace();
    //                 }
    //             }
    //             String line = queue.poll();
    //             if (line != null) {
    //                 String[] words = line.split("\\s+");
    //                 int wordCount = words.length;
    //                 System.out.println("Consumed: " + line + ", Word Count: " + wordCount);
    //             }
    //         }
    //     }
    // }
}

public class App {
    public static void main(String[] args) {
        String filePath = "/home/surajg/Assignments/hello_world/Git_Practice/WorkAccess/MultiThreading_ProducerConsumer/src/MultiThreading.txt"; // Specify your text file path here

        ProducerConsumer pc = new ProducerConsumer(filePath);

        Thread producerThread = new Thread(pc::produce);
        Thread consumerThread = new Thread(pc::consume);
        //Thread consumerThread2 = new Thread(pc::consume2);

        producerThread.start();
        consumerThread.start();
        //consumerThread2.start();
    }
}
