package com.systemsinmotion.petrescue.translator;

import java.util.List;
import java.util.Set;

import org.petfinder.entity.PetfinderPetRecord;

import com.systemsinmotion.petrescue.entity.AnimalType;

public class AnimalTypeTranslator implements Translator<AnimalType, PetfinderPetRecord> {

	public AnimalType translate(PetfinderPetRecord petfinderPetRecord) {

		AnimalType animalType = new AnimalType();
		animalType.setAnimalType(petfinderPetRecord.getAnimal().value());

		return animalType;
	}

	@Deprecated
	public Set<AnimalType> translateToSet(PetfinderPetRecord type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Deprecated
	public List<AnimalType> translateToList(PetfinderPetRecord type) {
		// TODO Auto-generated method stub
		return null;
	}

}
