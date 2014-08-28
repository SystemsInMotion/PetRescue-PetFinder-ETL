package com.systemsinmotion.petrescue.translator;

import java.util.List;
import java.util.Set;

import org.petfinder.entity.PetfinderPetRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.systemsinmotion.petrescue.data.reader.PetFinderReader;
import com.systemsinmotion.petrescue.entity.Breed;
import com.systemsinmotion.petrescue.entity.PetRecord;

@Service("petRecordTranslator")
public class PetRecordTranslator implements Translator<PetRecord, PetfinderPetRecord> {

	@Autowired
	private PetFinderReader petFinderReader;

	@Autowired
	private LocationTranslator locationTranslator;

	@Autowired
	private List<PetRecord> petRecords;

	public PetRecord translate(PetfinderPetRecord type) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<PetRecord> translatePetRecords() {

		while (petFinderReader.hasMoreRecords()) {
			try {

				copyFromPetFindToPetRecord(petFinderReader.read());

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return null;
	}

	private final PetRecord copyFromPetFindToPetRecord(final PetfinderPetRecord petFinderPetRecord) {

		PetRecord petRecord = new PetRecord();

		petRecord.setName(petFinderPetRecord.getName());
		petRecord.setDescription(petFinderPetRecord.getDescription());
		petRecord.setBreeds(copyPetFinderPetBreedsToPetRecord(petFinderPetRecord));
		petRecord.setLocation(locationTranslator.translate(petFinderPetRecord));

		return petRecord;
	}

	private final Set<Breed> copyPetFinderPetBreedsToPetRecord(final PetfinderPetRecord petfinderPetRecord) {

		return null;
	}

}
