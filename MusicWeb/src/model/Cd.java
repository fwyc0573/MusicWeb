package model;

public class Cd {
	private int CDId;//ר�����
	private String CDName;//ר������
	private String coverUrl;//ר������ͼƬ����
	private int songCount;//��������
	private String publishDate;//��������
	private String introduce;//ר�����
	private int collectionCount;//�ղش���
	
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
