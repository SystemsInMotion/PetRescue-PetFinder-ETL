package com.systemsinmotion.petrescue.translator;

import java.util.List;

import org.petfinder.entity.PetOptionType;
import org.petfinder.entity.PetfinderPetRecord;
import org.petfinder.entity.PetfinderPetRecord.Options;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.systemsinmotion.petrescue.data.reader.PetFinderReader;
import com.systemsinmotion.petrescue.data.writer.PetRecordWriter;
import com.systemsinmotion.petrescue.datamanager.RemoteIdentifierManager;
import com.systemsinmotion.petrescue.entity.PetRecord;

@Service("petRecordTranslator")
public class PetRecordTranslator implements Translator<PetRecord, PetfinderPetRecord> {

	@Autowired
	private PetFinderReader petFinderReader;

	@Autowired
	private LocationTranslator locationTranslator;

	@Autowired
	private BreedTranslator breedTranslator;

	@Autowired
	private AnimalTypeTranslator animalTypeTranslator;

	@Autowired
	private PetRecordWriter petRecordWriter;

	@Autowired
	private RemoteIdentifierManager remoteIdentifierManager;

	public PetRecord translate(PetfinderPetRecord petFinderPetRecord) {
		return copyFromPetFindToPetRecord(petFinderPetRecord);
	}

	private PetRecord copyFromPetFindToPetRecord(final PetfinderPetRecord petFinderPetRecord) {

		PetRecord petRecord = new PetRecord();

		petRecord.setName(petFinderPetRecord.getName());
		petRecord.setDescription(petFinderPetRecord.getDescription());
		copyPetFinderOptionsToPetRecord(petRecord, petFinderPetRecord);

		petRecord.setLocation(locationTranslator.translate(petFinderPetRecord));
		petRecord.setBreeds(breedTranslator.translate(petFinderPetRecord));
		petRecord.setAnimal(animalTypeTranslator.translate(petFinderPetRecord));

		return petRecord;
	}

	private void copyPetFinderOptionsToPetRecord(PetRecord pet, final PetfinderPetRecord externalPet) {

		Options options = externalPet.getOptions();
		List<PetOptionType> optionsList = options.getOption();

		for (PetOptionType petOptionType : optionsList) {
			switch (petOptionType) {
				case ALTERED:
					pet.setFixed(true);
					break;
				case HAS_SHOTS:
					pet.setVaccinated(true);
					break;
				case HOUSEBROKEN:
					pet.setHousebroken(true);
					break;
				case NO_CATS:
					pet.setNoCats(true);
					break;
				case NO_DOGS:
					pet.setNoDogs(true);
					break;
				case NO_KIDS:
					pet.setNoKids(true);
					break;
				case NO_CLAWS:
					pet.setDeclawed(true);
					break;
				default:
					break;
			}
		}
	}
}
