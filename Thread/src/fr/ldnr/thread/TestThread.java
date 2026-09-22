package fr.ldnr.thread;

import java.util.ArrayList;

public class TestThread extends Thread {
    public TestThread(String name){
        super(name);
    }
    public void run(){
        for(int i = 0; i < 5; i++) {
            System.out.print(this.getName());
        }
        System.out.println();
    }
    public static void main(String[] args) {

        ArrayList<Thread> threads= new ArrayList<>();
        for(int i = 1; i <= 5; i++){
            StringBuilder display = new StringBuilder().append(i);
            for (int x = 0; x<i; x++){
                display.append("-");
            }
            display.append(" ");

            Thread thread = new Thread(new TestThread(display.toString()));
            threads.add(thread);
        }
        for (Thread thread: threads){
            thread.start();
        }
    }
}