package pwr.lcec.vendor.controller;

import java.io.InputStream;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

public class ProfilePictureBean {

	private StreamedContent profileImg;
	
	/**
	 * TODO: Complete code for inputstream of BLOB
	 */
	public ProfilePictureBean() {
		InputStream inputStream = null;
		profileImg = new DefaultStreamedContent(inputStream, "image/png");
	}

	public StreamedContent getProfileImg() {
		return profileImg;
	}

	public void setProfileImg(StreamedContent profileImg) {
		this.profileImg = profileImg;
	}
}
