package com.syn.entities;

public class TVShow {
	private String name;
	private String url;
	private String score;
	private String type;
	private String myScore;
	private String thumbImg;
	
	public void setName(String name) {
		this.name = name;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setMyScore(String myScore) {
		this.myScore = myScore;
	}
	public void setThumbImg(String thumbImg) {
		this.thumbImg = thumbImg;
	}
	
	public String getName() {
		return name;
	}
	public String getUrl() {
		return url;
	}
	public String getScore() {
		return score;
	}
	public String getType() {
		return type;
	}
	public String getMyScore() {
		return myScore;
	}
	public String getThumbImg() {
		return thumbImg;
	}
}
