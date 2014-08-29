package com.systemsinmotion.petrescue.translator;


public interface Translator<K, T> {

	public K translate(T type);

}
