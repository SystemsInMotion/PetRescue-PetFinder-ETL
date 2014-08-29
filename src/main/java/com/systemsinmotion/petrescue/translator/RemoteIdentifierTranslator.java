package com.systemsinmotion.petrescue.translator;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.xml.datatype.XMLGregorianCalendar;

import org.petfinder.entity.PetfinderPetRecord;
import org.springframework.beans.factory.annotation.Autowired;

import com.systemsinmotion.petrescue.data.reader.PetFinderReader;
import com.systemsinmotion.petrescue.datamanager.RemoteIdentifierManager;
import com.systemsinmotion.petrescue.entity.RemoteIdentifier;
import com.systemsinmotion.petrescue.entity.type.ApiType;
import com.systemsinmotion.petrescue.mail.MailManager;

public class RemoteIdentifierTranslator {

	@Autowired
	private PetRecordTranslator petRecordTranslator;

	@Autowired
	private PetFinderReader petFinderReader;

	@Autowired
	private RemoteIdentifierManager remoteIdentifierManager;

	@Autowired
	private MailManager mailManager;

	@Autowired
	private List<RemoteIdentifier> petRecords;

	public List<RemoteIdentifier> translatePetRecords() {

		while (petFinderReader.hasMoreRecords()) {
			try {

				PetfinderPetRecord petFinderRecord = petFinderReader.read();

				RemoteIdentifier remoteIdentifer = remoteIdentifierManager.findByRemoteId(petFinderRecord.getId()
						.toString());

				if (remoteIdentifer == null) {
					remoteIdentifer = copyPropertiesToRemoteIdentifierRecord(new RemoteIdentifier(), petFinderRecord);
					petRecords.add(remoteIdentifer);

				} else if (isPetRecordOutdated(petFinderRecord.getLastUpdate(), remoteIdentifer.getLastUpdated())) {
					remoteIdentifer = copyPropertiesToRemoteIdentifierRecord(remoteIdentifer, petFinderRecord);
					petRecords.add(remoteIdentifer);
				}

			} catch (Exception e) {
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				Date date = new Date();

				mailManager.send_error(e,
						"Fail to proform back up at " + dateFormat.format(date) + " caused by " + e.getCause());
			}
		}

		return petRecords;
	}

	private RemoteIdentifier copyPropertiesToRemoteIdentifierRecord(RemoteIdentifier remoteIdentifer,
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
