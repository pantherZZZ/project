package com.th.system.po;

import java.io.Serializable;

//sys_pathlist表
//图片类
public class SysPathlist implements Serializable  {
	
	private int pictureId;//图片Id
	
	private String pictureName;//图片名称
	
	private String picturePath;//图片地址
	
	public int getPictureId() {
		return pictureId;
	}

	public void setPictureId(int pictureId) {
		this.pictureId = pictureId;
	}

	public String getPictureName() {
		return pictureName;
	}

	public void setPictureName(String pictureName) {
		this.pictureName = pictureName;
	}

	public String getPicturePath() {
		return picturePath;
	}

	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}
}
