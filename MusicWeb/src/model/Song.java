package model;

/*
 * ������ʵ����
 */
public class Song extends Cd{
	private int songId;//�������
	private String songName;//��������
	private String singerName;//���ֱ��
	private int singerId;//���ֱ��
	private int CDId;//CDר�����
	private String language;//��������
	private int playCount;//�������Ŵ���
	private int downloadCount;//�������ش���
	private int collectionCount;//�����ղش���
	private String publishDate;//��������ʱ��
	private String songUrl;//�������ӵ�ַ
	private String cyricUrl;//������ӵ�ַ
	private String songTime;//����ʱ�䳤��
	private int typeId;//�������ͱ��
	
	public int getSongId() {
		return songId;
	}
	public void setSongId(int songId) {
		this.songId = songId;
	}
	public String getSongName() {
		return songName;
	}
	public void setSongName(String songName) {
		this.songName = songName;
	}
	public String getSingerName() {
		return singerName;
	}
	public void setSingerName(String singerName) {
		this.singerName = singerName;
	}
	public int getSingerId() {
		return singerId;
	}
	public void setSingerId(int singerId) {
		this.singerId = singerId;
	}
	public int getCDId() {
		return CDId;
	}
	public void setCDId(int cDId) {
		CDId = cDId;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public int getPlayCount() {
		return playCount;
	}
	public void setPlayCount(int playCount) {
		this.playCount = playCount;
	}
	public int getDownloadCount() {
		return downloadCount;
	}
	public void setDownloadCount(int downloadCount) {
		this.downloadCount = downloadCount;
	}
	public int getCollectionCount() {
		return collectionCount;
	}
	public void setCollectionCount(int collectionCount) {
		this.collectionCount = collectionCount;
	}
	public String getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}
	public String getSongUrl() {
		return songUrl;
	}
	public void setSongUrl(String songUrl) {
		this.songUrl = songUrl;
	}
	public String getCyricUrl() {
		return cyricUrl;
	}
	public void setCyricUrl(String cyricUrl) {
		this.cyricUrl = cyricUrl;
	}
	public String getSongTime() {
		return songTime;
	}
	public void setSongTime(String songTime) {
		this.songTime = songTime;
	}
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	
}
