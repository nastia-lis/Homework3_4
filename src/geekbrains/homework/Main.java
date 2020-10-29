package geekbrains.homework;

public class Main {
    static final Object monitor = new Object();
    static volatile String letter = "A";
    static final int count = 5;

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            try {
                synchronized (monitor) {

                    for (int i = 0; i < count; i++) {
                        while (!letter.equals("A")) {
                            monitor.wait();
                        }
                        System.out.println("A");
                        letter = "B";
                        monitor.notifyAll();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t2 = new Thread(() -> {
            try {
                synchronized (monitor) {
                    for (int i = 0; i < count; i++) {
                        while (!letter.equals("B")) {
                            monitor.wait();
                        }
                        System.out.println("B");
                        letter = "C";
                        monitor.notifyAll();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t3 = new Thread(() -> {
            try {
                synchronized (monitor) {
                    for (int i = 0; i < count; i++) {
                        while (!letter.equals("C")) {
                            monitor.wait();
                        }
                        System.out.println("C");
                        letter = "A";
                        monitor.notifyAll();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t1.start();
        t2.start();
        t3.start();
    }
}

