package com.systemsinmotion.petrescue.data.reader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.systemsinmotion.petrescue.datamanager.LocationManager;
import com.systemsinmotion.petrescue.entity.Location;

@Service("locationReader")
public class LocationReader {

	@Autowired
	private LocationManager locationManager;

	public Location readLocation(String address) {

		Location locatin = locationManager.findByAddress(address);

		if (locatin == null) {
			locatin = new Location();
		}

		return locatin;

	}
}
