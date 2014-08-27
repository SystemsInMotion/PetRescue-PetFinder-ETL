package com.systemsinmotion.petrescue.data.migration;

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
	PetFinderConsumer petFinderService;

	private List<PetfinderPetRecord> petRecords;

	private int index;

	@PostConstruct
	public void init() {
		petRecords = this.petFinderService.shelterPets(null, null, null, null, null);
	}

	public PetfinderPetRecord read() throws Exception, UnexpectedInputException, ParseException,
			NonTransientResourceException {
		// TODO Auto-generated method stub
		return null;
	}

}
