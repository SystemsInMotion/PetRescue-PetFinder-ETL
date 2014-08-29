package com.systemsinmotion.petrescue.translator;

import java.util.List;

import org.petfinder.entity.PetPhotoType;
import org.petfinder.entity.PetfinderPetRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.systemsinmotion.petrescue.data.reader.PhotoReader;
import com.systemsinmotion.petrescue.datamanager.PhotoManager;
import com.systemsinmotion.petrescue.entity.PetRecord;
import com.systemsinmotion.petrescue.entity.Photo;

@Component
public class PhotoTranslator {

	@Autowired
	private PhotoReader photoReader;

	@Autowired
	private PhotoManager photoManager;

	public List<Photo> translate(PetRecord pet, PetfinderPetRecord petfinderPetRecord) {

		List<PetPhotoType> petFinferPhotos = photoReader.read(petfinderPetRecord);
		Integer id = pet.getId();
		List<Photo> photos = photoManager.findPhotosByPetId(id);

		if (!isEqual(petFinferPhotos, photos)) {
			for (PetPhotoType photo : petFinferPhotos) {
				Photo petRecordPhoto = new Photo();
				petRecordPhoto.setSize(photo.getSize());
				petRecordPhoto.setUrl(photo.getValue());
				petRecordPhoto.setPet(id);
				photos.add(petRecordPhoto);
			}
		}
		return photos;
	}

	private boolean isEqual(List<PetPhotoType> petFinferPhotos, List<Photo> photos) {

		boolean equalFlag = false;

		if (photos == null) {
			equalFlag = false;
		} else if (photos.size() < petFinferPhotos.size()) {
			equalFlag = false;
		} else {
			Integer maxPhotos = Math.min(petFinferPhotos.size(), photos.size());
			for (int i = 0; i < maxPhotos; i++) {
				if (!petFinferPhotos.get(i).getValue().equals(photos.get(i).getUrl())) {
					equalFlag = false;
					break;
				} else {
					equalFlag = true;
				}
			}
		}
		return equalFlag;
	}

}
