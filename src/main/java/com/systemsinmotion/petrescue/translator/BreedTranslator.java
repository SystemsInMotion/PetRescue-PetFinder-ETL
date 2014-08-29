package com.systemsinmotion.petrescue.translator;

import java.util.HashSet;
import java.util.Set;

import org.petfinder.entity.PetfinderPetRecord;
import org.springframework.stereotype.Component;

import com.systemsinmotion.petrescue.entity.Breed;

@Component
public class BreedTranslator implements Translator<Set<Breed>, PetfinderPetRecord> {

	public Set<Breed> translate(final PetfinderPetRecord petFinderPetRecord) {

		Set<Breed> breeds = new HashSet<Breed>();
		String aninal = petFinderPetRecord.getBreeds().getAnimal();
		Breed breed = new Breed();

		for (String breedType : petFinderPetRecord.getBreeds().getBreed()) {
			breed.setName(aninal);
			breed.setAnimalType(breedType);
			breeds.add(breed);
			breed = new Breed();
		}

		return breeds;
	}

}
