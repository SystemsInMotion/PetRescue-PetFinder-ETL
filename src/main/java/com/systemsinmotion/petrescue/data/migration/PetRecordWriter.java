package com.systemsinmotion.petrescue.data.migration;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import com.systemsinmotion.petrescue.entity.PetRecord;

@Component("petRecordWriter")
public class PetRecordWriter implements ItemWriter<PetRecord> {
	private static final Logger logger = Logger.getLogger(PetRecordWriter.class);

	public void write(List<? extends PetRecord> arg0) throws Exception {
		// TODO Auto-generated method stub

	}

}
