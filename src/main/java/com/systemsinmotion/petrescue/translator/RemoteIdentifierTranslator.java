package com.systemsinmotion.petrescue.translator;

import java.util.Date;
import java.util.List;

import javax.xml.datatype.XMLGregorianCalendar;

import org.petfinder.entity.PetfinderPetRecord;
import org.springframework.beans.factory.annotation.Autowired;

import com.systemsinmotion.petrescue.data.reader.PetFinderReader;
import com.systemsinmotion.petrescue.datamanager.RemoteIdentifierManager;
import com.systemsinmotion.petrescue.entity.RemoteIdentifier;
import com.systemsinmotion.petrescue.entity.type.ApiType;

public class RemoteIdentifierTranslator {

	@Autowired
	private PetRecordTranslator petRecordTranslator;

	@Autowired
	private PetFinderReader petFinderReader;

	@Autowired
	private RemoteIdentifierManager remoteIdentifierManager;

	@Autowired
	private List<RemoteIdentifier> petRecords;

	public List<RemoteIdentifier> translatePetRecords() {

		while (petFinderReader.hasMoreRecords()) {
			try {

				PetfinderPetRecord petFinderRecord = petFinderReader.read();

				RemoteIdentifier remoteIdentifer = remoteIdentifierManager.findByRemoteId(petFinderRecord.getId()
						.toString());

				if (remoteIdentifer == null) {
					remoteIdentifer = createNewRemoteIdentifierRecord(new RemoteIdentifier(), petFinderRecord);
					petRecords.add(remoteIdentifer);

				} else if (isPetRecordOutdated(petFinderRecord.getLastUpdate(), remoteIdentifer.getLastUpdated())) {
					remoteIdentifer = createNewRemoteIdentifierRecord(remoteIdentifer, petFinderRecord);
					petRecords.add(remoteIdentifer);
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return petRecords;
	}

	private RemoteIdentifier createNewRemoteIdentifierRecord(RemoteIdentifier remoteIdentifer,
			PetfinderPetRecord petFinderRecord) {
		remoteIdentifer.setApi(ApiType.PF);
		remoteIdentifer.setLastUpdated(petFinderRecord.getLastUpdate().toGregorianCalendar().getTime());
		remoteIdentifer.setPet(petRecordTranslator.translate(petFinderRecord));
		remoteIdentifer.setRemoteId(petFinderRecord.getId().toString());
		return remoteIdentifer;
	}

	private boolean isPetRecordOutdated(XMLGregorianCalendar petFinderLastUpdated, Date remoteIdentifierLastUpdated) {
		return petFinderLastUpdated.toGregorianCalendar().after(remoteIdentifierLastUpdated);
	}
}
