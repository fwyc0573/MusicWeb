package model;

public class Cd {
	private int CDId;//专辑编号
	private String CDName;//专辑名称
	private String coverUrl;//专辑封面图片链接
	private int songCount;//歌曲数量
	private String publishDate;//发行日期
	private String introduce;//专辑简介
	private int collectionCount;//收藏次数
	
	public int getCDId() {
		return CDId;
	}
	public void setCDId(int cDId) {
		CDId = cDId;
	}
	public String getCDName() {
		return CDName;
	}
	public void setCDName(String cDName) {
		CDName = cDName;
	}
	public String getCoverUrl() {
		return coverUrl;
	}
	public void setCoverUrl(String coverUrl) {
		this.coverUrl = coverUrl;
	}
	public int getSongCount() {
		return songCount;
	}
	public void setSongCount(int songCount) {
		this.songCount = songCount;
	}
	public String getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public int getCollectionCount() {
		return collectionCount;
	}
	public void setCollectionCount(int collectionCount) {
		this.collectionCount = collectionCount;
	}
}
