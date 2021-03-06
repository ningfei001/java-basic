package com.yqc.collection;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * LRU缓存初步实现,超过10个则清除最少使用的
 * 
 * @author yangqc 2016年12月19日
 * @param <K>
 * @param <V>
 */
public class MyLinkedHashMap<K, V> extends LinkedHashMap<K, V> {
	private static final long serialVersionUID = 1L;

	public MyLinkedHashMap() {
		super();
	}

	public MyLinkedHashMap(int initialCapacity, float loadFactor, boolean accessOrder) {
		super(initialCapacity, loadFactor, accessOrder);
	}

	@Override
	protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
		if (keySet().size() > 10) {
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		MyLinkedHashMap<Integer, Integer> myLinkedHashMap = new MyLinkedHashMap<>(10, 0.75f, true);
		for (int i = 0; i < 10; i++) {
			myLinkedHashMap.put(i, i);
		}
		myLinkedHashMap.get(0);
		myLinkedHashMap.get(1);
		myLinkedHashMap.put(11, 11);
		for (Map.Entry<Integer, Integer> entry : myLinkedHashMap.entrySet()) {
			System.out.println(entry.getKey() + " , " + entry.getValue());
		}
	}
}
