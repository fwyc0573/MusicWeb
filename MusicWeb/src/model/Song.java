package model;

/*
 * 歌曲表实体类
 */
public class Song extends Cd{
	private int songId;//歌曲编号
	private String songName;//歌曲名称
	private String singerName;//歌手编号
	private int singerId;//歌手编号
	private int CDId;//CD专辑编号
	private String language;//歌曲语种
	private int playCount;//歌曲播放次数
	private int downloadCount;//歌曲下载次数
	private int collectionCount;//歌曲收藏次数
	private String publishDate;//歌曲发行时间
	private String songUrl;//歌曲链接地址
	private String cyricUrl;//歌词链接地址
	private String songTime;//歌曲时间长度
	private int typeId;//歌曲类型编号
	
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
