package com.systemsinmotion.petrescue.translator;

import org.petfinder.entity.PetContactType;
import org.petfinder.entity.PetfinderPetRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.systemsinmotion.petrescue.data.reader.LocationReader;
import com.systemsinmotion.petrescue.entity.Location;

@Component("locationTranslator")
public class LocationTranslator implements Translator<Location, PetfinderPetRecord> {

	@Autowired
	LocationReader locationReader;

	public Location translate(final PetfinderPetRecord petFinderPetRecord) {

		PetContactType contactType = petFinderPetRecord.getContact();

		Location location = locationReader.readLocation(contactType.getAddress1());

		if (!isEqual(location, contactType)) {

			location.setAddress1(contactType.getAddress1());
			location.setAddress2(contactType.getAddress2());
			location.setZipCode(contactType.getZip());
			location.setContactName(contactType.getName());
			location.setEmail(contactType.getEmail());
			location.setFax(contactType.getFax());
			location.setPhone(contactType.getPhone());
			location.setStateOrProvince(contactType.getState());

		}
		return location;
	}

	private boolean isEqual(Location location, PetContactType contactType) {
		return (location.getAddress1().equals(contactType.getAddress1())
				&& location.getAddress2().equals(contactType.getAddress2())
				&& location.getEmail().equals(contactType.getEmail()) && location.getFax().equals(contactType.getFax()) && location
				.getPhone().equals(contactType.getPhone()));
	}

}
