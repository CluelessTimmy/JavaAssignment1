import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Library {

	public static void main(String[] args) {

		new Library().use();


	}
	private Catalogue catalogue;


	private List<Patron> patrons = new ArrayList<Patron>();
	public Library() {

		catalogue = new Catalogue(this);
	}

	 public void use() {
		 char choice;
		 do {
		 System.out.println("Welcome to the Library! Please make a selection from the menu:");
		 System.out.println("1. Explore the catalogue.");
		 System.out.println("2. View your patron record.");
		 System.out.println("3. Show you favourite books.");
		 System.out.println("4. Enter Admin Mode.");
		 System.out.println("X. Exit the system.");
		 choice = readChoice();
			 switch (choice) {
	    	 case '1':  explore(); break;
	    	 case '2':  view(); break;
	    	 case '3':  showFavourite();break;
	    	 case '4':  admin();break;
	    	 case 'X': break;
	    	 default : System.out.println("Please enter a number between 1 and 4, or press X to exit.");
	    	 }
		 } while (choice != 'X');
	 }

	private void showFavourite() {
		System.out.println(" ");
		System.out.print("Enter a patron ID: ");
		int i = In.nextInt();
		
		for(Patron patron : patrons) {
			System.out.println(patron.getName() + "'s favourite books are:");
			if(patron.getID() == i ) {
				calculateFavourite(patron.getBorrowingHistory());
				
			}
		}System.out.println(" ");
	}

	private void calculateFavourite(List<Book> borrowingHistory) {
		int max = 0;
		int loc = 0;
		
		List<String> titles = new ArrayList<String>();
		List<Integer> num = new ArrayList<Integer>();
		
		for(Book book : borrowingHistory) {
		int count = Collections.frequency(borrowingHistory, book);
			if(!titles.contains(book.toString())) {
				num.add(count);
				titles.add(book.toString());
				
			}
		}
		
			for(int i = 0; i < titles.size(); i++) {
				if (num.get(i) > max) {
					max = num.get(i);
					loc = i;
					
				}	
			
		}System.out.println(titles.get(loc));
		titles.remove(titles.get(loc));
		num.remove(num.get(loc));
		if(num.size() == 1) {
			System.out.println(titles.get(0));
			titles.remove(titles.get(0));
			num.remove(num.get(0));
		}else {
			max = 0;
			for(int i3 = 0; i3 < titles.size(); i3++) {
				
				if (num.get(i3) > max) {
					max = num.get(i3);
					loc = i3;
					
				}	
			
		}System.out.println(titles.get(loc));
		titles.remove(titles.get(loc));
		num.remove(num.get(loc));
		System.out.println(titles.get(0));
		titles.remove(titles.get(0));
		num.remove(num.get(0));
		}
	}

	private char readChoice() {
		System.out.print("Enter a choice: ");

		 return In.nextChar();
	}

	private void explore() {
		catalogue.use();
	}
	private void view() {
		System.out.println(" ");

		System.out.print("Enter a patron ID: ");
		int i = In.nextInt();
		if(patrons.isEmpty() == true) {
			System.out.println("That patron does not exist.");
			System.out.println(" ");
		}else {
			for(Patron patron : patrons) {
				if(patron.getID() == i ) {
				System.out.println(patron.toString());
				System.out.println("Books currently borrowed by " + patron.getName() + ": ");
					if(patron.getCurrentlyBorrowed().isEmpty() == true) {
					
						}else {
							for (Book book : patron.getCurrentlyBorrowed())
							{
								System.out.println(book.toString());
							}
							
						}
					System.out.println(patron.getName() + "'s borrowing history: ");
						if(patron.getBorrowingHistory().isEmpty() == true) {
							
						}else {
							for (Book book : patron.getBorrowingHistory())
							{
								System.out.println(book.toString());
							}
						}System.out.println(" ");
					}
					
			}
		}

	}

	private void admin() {
		char choice;
		 do {
		 System.out.println("Welcome to the administration menu:");
		 System.out.println("1. Add a patron.");
		 System.out.println("2. Remove a patron.");
		 System.out.println("3. Add a book to the catalogue.");
		 System.out.println("4. Remove a book from the catalogue.");
		 System.out.println("R. Return to the previous menu.");
		 choice = readChoice();
			 switch (choice) {
	    	 case '1':  patronAdd(); break;
	    	 case '2':  patronRemove();break;
	    	 case '3':  bookAdd();break;
	    	 case '4':  bookRemove();break;
	    	 case 'R': break;
	    	 default : System.out.println("Please enter a number between 1 and 4 or press R to return to the previous menu.");

	    	 }
		 } while (choice != 'R');
	}

	private void bookRemove() {
		System.out.println(" ");
		System.out.println("Removing a book.");
		System.out.print("Enter the title of the book: ");
		String t = In.nextLine();
		System.out.print("Enter the author's name: ");
		Author a = new Author(In.nextLine());
		catalogue.bookRemove(t, a);
	}

	private void patronRemove() {
		Patron removePatron = null;
		System.out.println(" ");
		System.out.println("Removing a patron.");
		System.out.print("Enter a patron ID: ");
		int i = In.nextInt();
		for (Patron patron : patrons) {
			if(patron.getID() == i) {
				removePatron = patron;
			}		
		}
		if (removePatron != null) {
		patrons.remove(removePatron);
		System.out.println("Patron removed.");
		System.out.println(" ");
		}else {
			System.out.println("That patron does not exist.");
			System.out.println(" ");
		}
	}
		

	private void patronAdd() {
		System.out.println(" ");
		System.out.println("Adding a new patron.");
		System.out.print("Enter a new ID: ");
		int i = In.nextInt();
		System.out.print("Enter the patron's name: ");
		String n = In.nextLine();
		System.out.println("Patron added.  ");
		System.out.println(" ");
		patrons.add(new Patron(i, n));
		
		
		
	}

	private void bookAdd() {
		System.out.println(" ");
		System.out.println("Adding a new book.");
		System.out.print("Enter the title of the book: ");
		String t = In.nextLine();
		System.out.print("Enter the author's name: ");
		Author a = new Author(In.nextLine());
		System.out.print("Enter the genre: ");
		Genre g = new Genre(In.nextLine().toLowerCase());
		
		catalogue.bookAdd(t,a,g);
	}

	
	
	

	public Book returnBook(int id) {
		for(Patron patron : patrons) {
			if(patron.getID() == id ) {
				System.out.println(patron.getName() + " has the following books: ");
				System.out.println("Books currently borrowed by " + patron.getName() + ":");
				for(Book book : patron.getCurrentlyBorrowed()) {
					System.out.println(book);
				}
				System.out.print("Enter the title of the book you wish to return: ");
				String title = In.nextLine();
				for(Book book : patron.getCurrentlyBorrowed()) {
					if(title.equals(book.getTitle())) {
						patron.returnBook(book);
						System.out.println(book.getTitle() + " has been returned.");
						System.out.println(" ");
						return book;
					}
				}
			}
		}
	return null;	
	}

	

	public Book borrow(int id, List<Book> booksOnShelf) {
		for(Patron patron : patrons) {
			if(patron.getID() == id ) {
				System.out.print("Enter the title of the book you wish to borrow: ");
				String title = In.nextLine();
				for(Book book : booksOnShelf) {
					
					if(book.getTitle().equals(title)) {
						patron.borrow(book);
						System.out.println("Book loaned.");
						System.out.println(" ");
						return book;
						
					}
					
				}
			
			}
		}
		System.out.println("That book is not available or doesn't exist. ");
		System.out.println(" ");
		return null;
	}

	
	
}


