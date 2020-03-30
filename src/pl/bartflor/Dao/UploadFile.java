package pl.bartflor.Dao;

public class UploadFile {
	private String id;
	private String name;
	private String type;
	private String size;
	private String upload_date;
	
	
	
	public UploadFile(String id, String name, String type, String size, String upload_date) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.size = size;
		this.upload_date = upload_date;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSize() {
		Double size = Double.parseDouble(this.size);
		if(size>=1000) {
			return size/1000+" kB";
		}else {
			return size+" B";
		}
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getUpload_date() {
		return upload_date;
	}
	public void setUpload_date(String upload_date) {
		this.upload_date = upload_date;
	}
	
}
