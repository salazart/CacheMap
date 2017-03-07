package com.sz.interfaces.impl;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import com.sz.interfaces.CacheMap;

public class CacheMapImplTest {
	
	private static final int TIME_OUT = 5000;
	private CacheMap<Integer, Integer> cacheMap = new CacheMapImpl<>(TIME_OUT);
	
	@Before
	public void before(){
		cacheMap.put(1, 1);
		cacheMap.put(2, 2);
		cacheMap.put(3, 3);
		cacheMap.put(4, 4);
	}
	
	@Test
	public void test() {
		System.out.println(cacheMap.size());
		fail("Not yet implemented");
	}

}
