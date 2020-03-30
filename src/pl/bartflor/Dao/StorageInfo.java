package pl.bartflor.Dao;

public class StorageInfo {
	private int totalStorage, filesSize, spaceLeft;

	public StorageInfo(int totalStorage, int filesSize, int spaceLeft) {
		super();
		this.totalStorage = totalStorage;
		this.filesSize = filesSize;
		this.spaceLeft = spaceLeft;
	}

	public int getTotalStorage() {
		return totalStorage;
	}
	public String getTotalStorageString() {
		return stringFormat(totalStorage);
	}

	public void setTotalStorage(int totalStorage) {
		this.totalStorage = totalStorage;
	}

	public int getFilesSize() {
		return filesSize;
	}
	
	public String getFilesSizeString() {
		return stringFormat(filesSize);
	}

	public void setFilesSize(int filesSize) {
		this.filesSize = filesSize;
	}

	public int getSpaceLeft() {
		return spaceLeft;
	}
	
	public String getSpaceLeftString() {
		return stringFormat(spaceLeft);
	}

	public void setSpaceLeft(int spaceLeft) {
		this.spaceLeft = spaceLeft;
	}
	private String stringFormat(int fileSize) {
		if(fileSize>=1000) {
			return fileSize/1000+" kB";
		}else {
			return fileSize+" B";
		}
	}

}
