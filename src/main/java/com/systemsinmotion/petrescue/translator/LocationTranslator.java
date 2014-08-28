package com.systemsinmotion.petrescue.translator;

import org.petfinder.entity.PetContactType;
import org.petfinder.entity.PetfinderPetRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.systemsinmotion.petrescue.data.reader.LocationReader;
import com.systemsinmotion.petrescue.entity.Location;

@Service("locationTranslator")
public class LocationTranslator implements Translator<Location, PetfinderPetRecord> {

	@Autowired
	LocationReader locationReader;

	public Location translate(PetfinderPetRecord petFinderPetRecord) {

		Location location = locationReader.readLocation(petFinderPetRecord.getContact().getAddress1());
		if (location == null) {
			location = new Location();
		}

		return copyPetContactTypeToLocation(petFinderPetRecord.getContact(), location);
	}

	private Location copyPetContactTypeToLocation(PetContactType contactType, Location location) {

		location.setAddress1(contactType.getAddress1());
		location.setAddress2(contactType.getAddress2());
		location.setZipCode(contactType.getZip());
		location.setContactName(contactType.getName());
		location.setEmail(contactType.getEmail());
		location.setFax(contactType.getFax());
		location.setPhone(contactType.getPhone());
		location.setStateOrProvince(contactType.getState());

		return location;
	}

}
