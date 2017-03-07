package com.sz.interfaces.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.sz.interfaces.CacheMap;

public class CacheMapImpl<K, V> implements CacheMap<K, V> {

	private Map<K, V> cacheMap = new HashMap<>();
	private Map<K, Long> liveTimes = new HashMap<>();
	private long timeOut;

	public CacheMapImpl(long timeOutInMilis) {
		this.timeOut = timeOutInMilis;
	}

	private void verifyTimeLife() {
		Iterator<Entry<K, Long>> entries = liveTimes.entrySet().iterator();
		while (entries.hasNext()) {
			Map.Entry<K, Long> liveTime = entries.next();
			if (liveTime.getValue() < System.currentTimeMillis()) {
				K key = liveTime.getKey();
				liveTimes.remove(key);
				cacheMap.remove(key);
				entries = liveTimes.entrySet().iterator();
			}
		}
	}

	private long getTimeLive() {
		return System.currentTimeMillis() + timeOut;
	}

	@Override
	public void clear() {
		liveTimes.clear();
		cacheMap.clear();
	}

	@Override
	public boolean containsKey(Object key) {
		verifyTimeLife();
		return cacheMap.containsKey(key);
	}

	@Override
	public boolean containsValue(Object value) {
		verifyTimeLife();
		return cacheMap.containsValue(value);
	}

	@Override
	public Set<java.util.Map.Entry<K, V>> entrySet() {
		verifyTimeLife();
		return cacheMap.entrySet();
	}

	@Override
	public V get(Object key) {
		verifyTimeLife();
		return cacheMap.get(key);
	}

	@Override
	public boolean isEmpty() {
		verifyTimeLife();
		return cacheMap.isEmpty();
	}

	@Override
	public Set<K> keySet() {
		verifyTimeLife();
		return cacheMap.keySet();
	}

	@Override
	public V put(K key, V value) {
		liveTimes.put(key, getTimeLive());
		return cacheMap.put(key, value);
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		for (Map.Entry<? extends K, ? extends V> element : m.entrySet()) {
			liveTimes.put(element.getKey(), getTimeLive());
		}
		cacheMap.putAll(m);
	}

	@Override
	public V remove(Object key) {
		liveTimes.remove(key);
		return cacheMap.remove(key);
	}

	@Override
	public int size() {
		verifyTimeLife();
		return cacheMap.size();
	}

	@Override
	public Collection<V> values() {
		verifyTimeLife();
		return cacheMap.values();
	}
}
