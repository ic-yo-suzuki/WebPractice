package chapter5.dao;

public class Book {
	private int id, autherId;
	private String title;

	public int getId(){
		return id;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getAutherId(){
		return autherId;
	}

	public void setAutherId(int autherId){
		this.autherId = autherId;
	}

	public String getTitle(){
		return title;
	}

	public void setTitle(String title){
		this.title = title;
	}

	@Override
	public String toString(){
		return "Book [id = " + id + ", autherId = " + autherId + ", " + " title = " + title + "]";
	}
}
