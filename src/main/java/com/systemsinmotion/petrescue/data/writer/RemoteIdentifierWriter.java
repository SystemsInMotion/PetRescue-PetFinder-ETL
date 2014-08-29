package com.systemsinmotion.petrescue.data.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import com.systemsinmotion.petrescue.datamanager.RemoteIdentifierManager;
import com.systemsinmotion.petrescue.entity.RemoteIdentifier;

public class RemoteIdentifierWriter implements ItemWriter<RemoteIdentifier> {

	@Autowired
	private RemoteIdentifierManager remoteIdentifierManager;

	public void write(List<? extends RemoteIdentifier> remoteIdentifiersRecords) throws Exception {
		remoteIdentifierManager.storeRemoteIdentifier(remoteIdentifiersRecords);
	}

}
