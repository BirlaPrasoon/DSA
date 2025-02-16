package multithreading_practicals.forkjoin;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ExchangerDemo {
    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<>();

        new UseString(exchanger);
        new MakeString(exchanger);
    }
}

// A thread that makes a string
class MakeString implements Runnable {
    Exchanger<String> ex;
    String str;

    MakeString(Exchanger<String> ex) {
        this.ex = ex;
        str = new String();

        new Thread(this).start();
    }

    public void run() {
        char ch = 'A';
        try {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 5; j++) {
                    str += ch++;
                }
                if (i == 2) {
                    // Exchange the buffer and
                    // only wait for 250 milliseconds
                    str = ex.exchange(str, 250, TimeUnit.MILLISECONDS);
                    continue;
                }

                // Exchange a full buffer for an empty one
                str = ex.exchange(str);
            }
        } catch (InterruptedException e) {
            System.out.println(e);
        } catch (TimeoutException t) {
            System.out.println("Timeout Occurred");
        }
    }
}

// A thread that uses a string
class UseString implements Runnable {

    Exchanger<String> ex;
    String str;

    UseString(Exchanger<String> ex) {
        this.ex = ex;

        new Thread(this).start();
    }

    public void run() {
        try {
            for (int i = 0; i < 3; i++) {
                if (i == 2) {
                    // Thread sleeps for 500 milliseconds
                    // causing timeout
                    Thread.sleep(500);
                    continue;
                }

                // Exchange an empty buffer for a full one
                str = ex.exchange(new String());
                System.out.println("Got: " + str);
            }
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }
}

