package com.sz.interfaces.impl;

import com.sz.interfaces.CacheMap;

public class CacheMapTest {
	
	private static final int TIME_OUT = 5000;
	private static CacheMap<Integer, Integer> cacheMap = new CacheMapImpl<>(TIME_OUT);
	
	public static void main(String[] args) {
		cacheMap.put(1, 1);
		cacheMap.put(2, 2);
		cacheMap.put(3, 3);
		cacheMap.put(4, 4);
		
		System.out.println(cacheMap.size());

		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("started");
				System.out.println(cacheMap.size());
				try {
					Thread.sleep(6000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(cacheMap.size());
				System.out.println("finished");
			}
		});
		thread.start();
	}
}
