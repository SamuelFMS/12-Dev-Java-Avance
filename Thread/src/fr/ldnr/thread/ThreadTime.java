package fr.ldnr.thread;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ThreadTime {

    public static void main(String[] args) {
        Thread thread = new Thread(new MonRunnable(1000));
        thread.start();
    }

    private static class MonRunnable implements Runnable {

        private final long delay;

        public MonRunnable(long delay) {
            this.delay = delay;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(delay);
                    DateFormat df = new SimpleDateFormat("HH:mm:ss");
                    System.out.println(df.format(new Date()));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}