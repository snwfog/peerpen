package peerpen.domain;

public class TestObject {
	private String title;
	private String author;
	private String isbn;
	private String status;
	private String availability;
	private String description;
	public TestObject() {
	}

	
	public TestObject(String title, String author, String isbn, String status,
			String avail, String descp) {
		// TODO Auto-generated constructor stub
		this.title = title;
		this.author = author;
		this.isbn = isbn;
		this.status = status;
		this.description = descp;
	}
	
	public void setTitle(String tit) {
		title = tit;
	}

	public String getTitle() {
		return title;
	}

	public void setAuthor(String aut) {
		author = aut;
	}

	public String getAuthor() {
		return author;
	}

	public void setIsbn(String is) {
		isbn = is;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setStatus(String stat) {
		status = stat;
	}

	public String getStatus() {
		return status;
	}

	public void setAvailability(String avail) {
		availability = avail;
	}

	public String getAvailability() {
		return availability;
	}

	public void setDescription(String des) {
		description = des;
	}

	public String getDesciption() {
		return description;
	}
}
