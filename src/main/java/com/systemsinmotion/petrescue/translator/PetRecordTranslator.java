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
public class PetRecordTranslator {

	@Autowired
	private PetFinderReader petFinderReader;

	@Autowired
	private List<PetRecord> petRecords;

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

	private final PetRecord copyFromPetFindToPetRecord(final PetfinderPetRecord petfinderPetRecord) {

		PetRecord petRecord = new PetRecord();

		petRecord.setName(petfinderPetRecord.getName());
		petRecord.setDescription(petfinderPetRecord.getDescription());
		petRecord.setBreeds(copyPetFinderPetBreedsToPetRecord(petfinderPetRecord));

		return petRecord;
	}

	private final Set<Breed> copyPetFinderPetBreedsToPetRecord(final PetfinderPetRecord petfinderPetRecord) {

		return null;
	}

}
