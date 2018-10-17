package practical.thread;

import java.util.List;
import java.util.ArrayList;

public class LockTip extends Thread {
	private List longs = new ArrayList<>();

	public static void main(String args[]) {
		LockTip lt = new LockTip();
		lt.start();

		new LongSupplier(lt).start();
	}

	public void run() {
		while (true) {
			try {
				System.out.println("main run calling ");
				wait();
				// do something with longs
				System.out.println("doing something: " + this.longs);
			} catch (InterruptedException e) {
				System.err.println(e);
			}
		}
	}

	public void addLong(Long l) {
		this.longs.add(l);
		notify();
	}
}

class LongSupplier extends Thread {
	private LockTip lt;

	public LongSupplier(LockTip lt) {
		this.lt = lt;
	}

	public void run() {
		while (true) {
			try {
				Thread.sleep(1000);
				this.lt.addLong(new Long(123));
			} catch (InterruptedException e) {
				System.out.println(e);
			}
		}
	}
}
