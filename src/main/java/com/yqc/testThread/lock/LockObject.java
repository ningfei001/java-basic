package com.yqc.testThread.lock;

/**
 * 1.����ʵ��ͬ������,���ǵ�ǰʵ������.
 * 2.���ھ�̬ͬ������,���ǵ�ǰ�����Class����.
 * 3.����ͬ��������,����Synchonized���������õĶ���.
 * 
 * @author yangqc
 *
 */
public class LockObject {
	private static volatile int a = 0;

	synchronized static int get() throws InterruptedException {
		Thread.sleep(5000);
		return a;
	}

	synchronized static void put(int b) {
		a = b;
	}

	public static void main(String[] args) {
		Runnable task1 = new Runnable() {

			@Override
			public void run() {
				try {
					System.out.println(LockObject.get());
				} catch (InterruptedException e) {
					System.out.println("fuck,interrupted!");
				}
			}
		};

		Runnable task2 = new Runnable() {

			@Override
			public void run() {
				LockObject.put(12);
				System.out.println("completed");
			}
		};

		// new Thread(task1).start();
		// new Thread(task2).start();

		new Thread(new Task(true)).start();
		new Thread(new Task(false)).start();
	}
}

class A {

	public synchronized void printName() throws InterruptedException {
		while (true) {
			Thread.sleep(1000);
			System.out.println("name");
		}
	}

	public synchronized void printAge() throws InterruptedException {
		while (true) {
			Thread.sleep(1000);
			System.out.println("age");
		}
	}
}

class Task implements Runnable {

	private final boolean flag;
	private A a;

	Task(boolean flag) {
		this.flag = flag;
		a = new A();
	}

	@Override
	public void run() {
		try {
			if (flag) {
				a.printAge();
			} else {
				a.printName();
			}
		} catch (Exception e) {
			System.out.println("fuck,interrupted!");
		}
	}

}