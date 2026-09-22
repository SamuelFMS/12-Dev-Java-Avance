package fr.ldnr.thread;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ThreadTime {

    public static void main(String[] args) {
        DateFormat df = new SimpleDateFormat("HH:mm:ss");
        ArrayList<Thread> threads= new ArrayList<>();
        for(int i = 1; i <= 5; i++){
            Thread thread = new Thread(new MonRunnable(i));
            threads.add(thread);
        }

        System.out.println(df.format(new Date()));
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