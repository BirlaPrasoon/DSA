package multithreading_practicals;

import leetcode.TreeNode;

public class VolatileNeeds {

    volatile boolean ready = false;
    int ans;
    TreeNode x;


    synchronized void thread1() {
        while(!ready){
            System.out.println("Not ready");
        }
        System.out.println("Ready!!!");
        assert ans == 42;
    }

    native void jni(); /*{

    }*/

    synchronized void thread2() {
        ans = 42;
        System.out.println(x.right);
        ready = true;
    }


    public static void main(String[] args) throws InterruptedException {
        VolatileNeeds vn = new VolatileNeeds();
        Thread t1 = new Thread(vn::thread1);
        Thread t2 = new Thread(vn::thread2);

//        t2.start();
//        t1.start();
//        t2.join();
//        t1.join();
        String t = """
                This will work as we want without anything breaking!!
                This is something with space///
                """;
        System.out.println(t);
    }
}
