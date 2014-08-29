package com.systemsinmotion.petrescue.data.writer;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.systemsinmotion.petrescue.datamanager.PetManager;
import com.systemsinmotion.petrescue.entity.PetRecord;

@Service("petRecordWriter")
public class PetRecordWriter implements ItemWriter<PetRecord> {
	private static final Logger logger = Logger.getLogger(PetRecordWriter.class);

	@Autowired
	PetManager petManager;

	public void write(List<? extends PetRecord> petRecords) throws Exception {
		petManager.storeAllPets(petRecords);

	}

}
