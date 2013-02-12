package com.systemsinmotion.petrescue.data.migration;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.petfinder.entity.PetfinderPetRecord;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = { "/test-context.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class PetFinderReaderTest {

	@Test
	public void readFirst() throws Exception {
		PetFinderReader reader = new PetFinderReader();
		assertNotNull(reader);
		
		PetfinderPetRecord record = reader.read();
		assertNotNull(record);
	}
}
