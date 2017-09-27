import java.util.ArrayList;
import java.util.List;

public class Patron {
	private int ID;
	private String name;
	private List<Book> currentlyBorrowed = new ArrayList<Book>();
	private List<Book> borrowingHistory = new ArrayList<Book>();
	
	public Patron(int i, String n) {
		ID = i;
		name = n;
	}
	public String toString() {
		
		return (ID + " " + name);
		
	}
	public int getID() {
		return ID;
	}
	
	public List<Book> getCurrentlyBorrowed(){
		return currentlyBorrowed;
	}
	public List<Book> getBorrowingHistory(){
		return borrowingHistory;
	}
	public String getName() {
		return name;
	}
	public void borrow(Book b) {
		
		currentlyBorrowed.add(b);
		borrowingHistory.add(b);	
	}
	
	public void returnBook(Book book) {
		currentlyBorrowed.remove(book);
		
	}
}
