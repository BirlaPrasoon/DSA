import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

record Process(int id, String name) {
    public void executeSync() {
        System.out.println("Executing process with id: " + id);
    }

    public String executeASync() throws InterruptedException {
        System.out.println("Executing process with id: " + id);
        return "process with name: "+ name + " completed execution";
    }
}

public class ProcessExecutor {


    public static void main(String[] args) throws InterruptedException {
        List<Process> processes = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            processes.add(new Process(i, "p"+ i));
        }


        System.out.println("Process execution start");
        //        executeWay1(processes);
//        automaticallyWaitsWithoutJoinCall(processes);

        executeParallalyToGetResponse(processes);


        System.out.println("Process execution end");

        System.out.println("hello");

    }

    private static void automaticallyWaitsWithoutJoinCall(List<Process> processes) {
        processes.stream().parallel().forEach(Process::executeSync);
    }

    private static List<String> executeParallalyToGetResponse(List<Process> processes) throws InterruptedException {
        List<Callable<String>> list = new ArrayList<>();
        List<Future<String>> listOfFuture = new ArrayList<>();
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(100);


        processes.forEach(p -> {

            Callable<String> future =new Callable<>() {
                @Override
                public String call() throws Exception {
                    return p.executeASync();
                }
            };
            // 2nd way to do the same thing, this requires list of Futures not callable.
            //Future<String> fut = executorService.submit(future);
            // listOfFuture.add(fut);
            list.add(future);
        });

        List<Future<String>> futures = executorService.invokeAll(list);

        executorService.

        List<String> anss = new ArrayList<>();
        futures.forEach(f -> {
            try {
                anss.add(f.get());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        });

        System.out.println(anss);

        return anss;
    }

    public static void executeWay1(List<Process> processes) throws InterruptedException {
        Thread t1 = new Thread(()-> {
            processes.get(0).executeSync();
        });

        Thread t2 = new Thread(()-> {
            processes.get(1).executeSync();
        });

        Thread t3 = new Thread(()-> {
            processes.get(2).executeSync();
        });

        Thread t4 = new Thread(()-> {
            processes.get(3).executeSync();
        });

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        t1.join();
        t2.join();
        t3.join();
        t4.join();
    }
}
