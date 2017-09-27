import java.util.ArrayList;
import java.util.List;

public class Book {
	private String title;
	private Author author;
	private Genre genre;
	private List<Patron> holds = new ArrayList<Patron>();
	
	public Book(String t, Author a, Genre g) {
		title = t;
		author = a;
		genre = g;
	}
	public String getTitle() {
		return title.toString();
	}
	public String toString() {
		
		return (author + ", " + title);
		
	}
	public String getAuthor() {
		
		return author.toString();
	}
	public String getGenre() {
		// TODO Auto-generated method stub
		return (genre.toString());
	}
}
