package com.systemsinmotion.petrescue.translator;

import org.springframework.stereotype.Service;

@Service("translator")
public interface Translator<K, T> {

	public K translate(T type);

}
