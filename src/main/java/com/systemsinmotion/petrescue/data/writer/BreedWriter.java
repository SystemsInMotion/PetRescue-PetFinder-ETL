package com.systemsinmotion.petrescue.data.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import com.systemsinmotion.petrescue.datamanager.BreedManager;
import com.systemsinmotion.petrescue.entity.Breed;

public class BreedWriter implements ItemWriter<Breed> {

	@Autowired
	BreedManager breedManager;

	public void write(List<? extends Breed> breeds) throws Exception {
		breedManager.storeBreed(breeds);
	}

	public void write(Breed breed) throws Exception {
		breedManager.storeBreed(breed);
	}
}
