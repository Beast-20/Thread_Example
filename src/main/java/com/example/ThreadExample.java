package com.example;
class PrintCharacters implements Runnable {
    @Override
    public void run() {
        for (char c = 'A'; c <= 'Z'; c++) {
            System.out.println("Thread 2: " + c);

            if (ThreadExample.stopThread2) {
                System.out.println("Thread 2 stopped");
                break;
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class PrintNumbers implements Runnable {
    @Override
    public void run() {
        for (int i = 1; i <= 27; i++) {
            System.out.println("Thread 1: " + i);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
public class ThreadExample {

static boolean stopThread2 = false;

    public static void main(String[] args) {
        Thread thread1 = new Thread(new PrintNumbers());

        Thread thread2 = new Thread(new PrintCharacters());

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            stopThread2 = true;
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
