package com.systemsinmotion.petrescue.translator;

import org.petfinder.entity.PetContactType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.systemsinmotion.petrescue.data.reader.LocationReader;
import com.systemsinmotion.petrescue.entity.Location;

@Service("locationTranslator")
public class LocationTranslator implements Translator<Location, PetContactType> {

	@Autowired
	LocationReader locationReader;

	public Location translate(PetContactType contactType) {

		Location location = new Location();
		copyPetContactTypeToLocation(contactType, location);

		return location;
	}

	public Location updateLocaleObject(Location locale, PetContactType contactType) {

		Location location = locationReader.readLocation(locale.getId());

		if (location == null) {
			location = new Location();
		}

		return copyPetContactTypeToLocation(contactType, location);
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
