package practical.thread;

public class TestThread extends Thread{
	public static void main(String[] args) {
		TestThread t=new TestThread();
		t.start();
		System.out.println("Done!!!!!");
	}
	@Override
	public void run() {
		System.out.println("Runnn callling");
	}
}

