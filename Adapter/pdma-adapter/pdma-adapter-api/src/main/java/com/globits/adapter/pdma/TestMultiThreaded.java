package com.globits.adapter.pdma;

import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class TestMultiThreaded {
	static int num = 0;
	public static AtomicInteger atomicInteger = new AtomicInteger(num);

	@SuppressWarnings("resource")
	public static void main(String args[]) {
		Scanner userInput = new Scanner(System.in);
		System.out.println("Enter Number of Threads: ");
		int getThreadNumber = userInput.nextInt();
		for (int i = 0; i < getThreadNumber; i++) {
			PrintThread p = new PrintThread();
			p.start();
		}

	}
}

class PrintThread extends Thread {
	public void run() {
		System.out.println("Thread Started: ");
		System.out.println(TestMultiThreaded.atomicInteger.incrementAndGet());

	}
}
