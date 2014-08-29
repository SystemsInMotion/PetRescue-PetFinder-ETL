package com.systemsinmotion.petrescue.data.reader;

import java.util.List;

import org.petfinder.entity.PetfinderPetRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.systemsinmotion.petrescue.datamanager.BreedManager;
import com.systemsinmotion.petrescue.entity.Breed;

@Service("breedReader")
public class BreedReader {

	@Autowired
	private BreedManager breedManager;

	public Breed read(String typeOfBreed) {

		Breed breed = breedManager.findBreedByName(typeOfBreed);

		if (breed == null) {
			breed = new Breed();
		}

		return breed;
	}

	public List<Breed> read(PetfinderPetRecord animalTypeName) {

		List<Breed> breeds = breedManager.findAllBreedsByAnimalTypeName(animalTypeName.getBreeds().getAnimal());

		return breeds;
	}
}
