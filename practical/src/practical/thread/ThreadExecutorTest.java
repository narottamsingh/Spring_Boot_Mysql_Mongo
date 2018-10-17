package practical.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadExecutorTest {

	public static void main(String[] args) {
		ExecutorService e = Executors.newFixedThreadPool(10);
		for (int i = 0; i <= 100; i++) {
			Runnable runnable = new ThreadExecutor("" + i);
			e.execute(runnable);
		}
		e.shutdown();
		while (!e.isTerminated()) {
        }
        System.out.println("Finished all threads");
	}
}
