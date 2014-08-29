package com.systemsinmotion.petrescue.data.reader;

import java.util.List;

import org.petfinder.entity.PetPhotoType;
import org.petfinder.entity.PetfinderPetRecord;

public class PhotoReader {

	public List<PetPhotoType> read(PetfinderPetRecord petfinderPetRecord) {

		List<PetPhotoType> petFinferPhotos = petfinderPetRecord.getMedia().getPhotos().getPhoto();
		return petFinferPhotos;
	}
}
