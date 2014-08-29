package com.systemsinmotion.petrescue.data.reader;

import java.util.List;

import javax.annotation.PostConstruct;

import org.petfinder.entity.PetfinderPetRecord;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.systemsinmotion.petrescue.web.PetFinderConsumer;

@Component("petFinderReader")
public class PetFinderReader implements ItemReader<PetfinderPetRecord> {

	@Autowired
	@Qualifier("petFinderService")
	private PetFinderConsumer petFinderService;

	private List<PetfinderPetRecord> petRecords;

	private int index;

	private int totalPetRecords;

	@PostConstruct
	public void init() {

		petRecords = this.petFinderService.shelterPets(null, null, null, null, null);
		totalPetRecords = (petRecords == null) ? 0 : petRecords.size();
	}

	public PetfinderPetRecord read() throws Exception, UnexpectedInputException, ParseException,
			NonTransientResourceException {
		PetfinderPetRecord record = null;
		if (petRecords != null && !petRecords.isEmpty() && index < totalPetRecords) {
			record = petRecords.get(index++);
		}
		return record;
	}

	public boolean hasMoreRecords() {
		return index > totalPetRecords;
	}
}
