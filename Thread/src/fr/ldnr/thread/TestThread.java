package fr.ldnr.thread;

import java.util.ArrayList;

public class TestThread {

    public static void main(String[] args) {
        ArrayList<Thread> threads= new ArrayList<>();
        for(int i = 1; i <= 5; i++){
            Thread thread = new Thread(new MonRunnable(i));
            threads.add(thread);
        }
        for (Thread thread: threads){
            thread.start();
        }
    }

    private static class MonRunnable implements Runnable {

        private final long number;

        public MonRunnable(int number) {
            this.number = number;
        }

        @Override
        public void run() {
            StringBuilder display = new StringBuilder().append(number);
            for (int i = 0; i<number; i++){
                display.append("-");
            }
            display.append(" ");
            for(int i = 0; i < 5; i++) {
                System.out.print(display.toString());
            }
            System.out.println();
        }
    }
}