package practical.thread;

import java.util.List;
import java.util.ArrayList;

public class LockTipS extends Thread {
	private List longs = new ArrayList<>();

	public static void main(String args[]) {
		LockTipS lt = new LockTipS();
		lt.start();

		new LongSupplier1(lt).start();
	}

	public void run() {
		while (true) {
			try {
				synchronized (this) {
					System.out.println("main run calling ");
					wait();
					// do something with longs
					System.out.println("doing something: " + this.longs);
				}
			} catch (InterruptedException e) {
				System.err.println(e);
			}
		}
	}

	public void addLong(Long l) {
		synchronized (this) {
			this.longs.add(l);
			notifyAll();
		}
	}
}

class LongSupplier1 extends Thread {
	private LockTipS lt;

	public LongSupplier1(LockTipS lt) {
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
