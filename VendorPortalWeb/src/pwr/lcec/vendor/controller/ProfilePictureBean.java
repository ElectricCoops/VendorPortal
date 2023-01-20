package pwr.lcec.vendor.controller;

import java.io.InputStream;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import pwr.lcec.vendor.controller.ProfilePictureBean;

public class ProfilePictureBean {
	private StreamedContent profileImg;

	public ProfilePictureBean() {
		InputStream inputStream = null;
		this.profileImg = new DefaultStreamedContent(inputStream, "image/png");
	}

	public StreamedContent getProfileImg() {
		return this.profileImg;
	}

	public void setProfileImg(StreamedContent profileImg) {
		this.profileImg = profileImg;
	}
}
