import java.util.ArrayList;
import java.util.List;

public class Catalogue {
	private Library library;
	private List<Book> booksOnShelf = new ArrayList<Book>();
	private List<Book> booksOnLoan = new ArrayList<Book>();
	private List<Genre> genres  = new ArrayList<Genre>();
	private List<Author> authors  = new ArrayList<Author>();


		public Catalogue(Library l) {

			library = l;
			

		}


	public void use() {
		
		 char choice;
		 do {
		 System.out.println("Welcome to the Catalogue! Please make a selection from the menu:");
		 System.out.println("1. Display all books.");
		 System.out.println("2. Display all available books.");
		 System.out.println("3. Display all genres.");
		 System.out.println("4. Display books in a genre.");
		 System.out.println("5. Display all authors.");
		 System.out.println("6. Display all books by an author.");
		 System.out.println("7. Borrow a book.");
		 System.out.println("8. Return a book.");
		 System.out.println("9. Place a hold.");
		 System.out.println("R. Return to previous menu.");
		 choice = readChoice();

			 switch (choice) {
	    	 case '1':  booksAll();break;
	    	 case '2':  booksIn();break;
	    	 case '3':  genresAll();break;
	    	 case '4':  showGenre();break;
	    	 case '5':  authorAll();break;
	    	 case '6':  showAuthor();break;
	    	 case '7':  borrow(); break;
	    	 case '8':  returnBook(); break;
	    	 case '9':  hold();break;
	    	 case 'R':  break;
	    	 default : System.out.println("Please enter a number between 1 and 9 or press R to return to the previous menu.");
	    	 }
		 }while (choice != 'R') ;
	 }







	private void hold() {
		Book borrowedBook = null;
		System.out.println(" ");
		System.out.print("Enter a patron ID: ");
		int id = In.nextInt();
		System.out.print("Enter book title: ");
		String t = In.nextLine();
		System.out.println("Book held.");
		
		System.out.println(" ");
		for (Book book : booksOnShelf) {
			if (book.getTitle().equals(t)) {
				 borrowedBook = book;
				
			}
		}

		if (borrowedBook != null) {
		booksOnShelf.remove(borrowedBook);
		booksOnLoan.add(borrowedBook);
		}
	}


	private void showAuthor() {
		System.out.println(" ");
		System.out.print("Enter the name of an author: ");
		String a = In.nextLine();
		System.out.println("The library has the following books by that author: ");
		for(Book book : booksOnShelf) {
			if(book.getAuthor().equals(a)) {
				System.out.println(book.toString());
			}
		}
		System.out.println(" ");
	}


	private void showGenre() {
		System.out.println(" ");
		System.out.print("Enter a genre: ");
		String g = In.nextLine();
		System.out.println("The library has the following books in that genre: ");
		for(Book book : booksOnShelf) {
			if(book.getGenre().equals(g)) {
				System.out.println(book.toString());
			}
		}
		System.out.println(" ");
	}


	private void returnBook() {
		System.out.println(" ");
		System.out.print("Enter a valid patron ID: ");
		int id = In.nextInt();
		
		Book returnedBook = library.returnBook(id);
		booksOnShelf.add(returnedBook);
		booksOnLoan.remove(returnedBook);
		
		
	}


	private void borrow() {
		
		System.out.println(" ");
		System.out.print("Enter a valid patron ID: ");
		int id = In.nextInt();
		Book borrowedBook = library.borrow(id, booksOnShelf);
		
		if (borrowedBook != null) {
		booksOnShelf.remove(borrowedBook);
		booksOnLoan.add(borrowedBook);
		}
	}


	private void booksAll() {
		System.out.println(" ");
		System.out.println("The Library has the following books: ");
		if(booksOnShelf.isEmpty() == true && booksOnLoan.isEmpty() == true) {
			
			}
		else {
			if(booksOnShelf != null) {
				for(Book book : booksOnShelf) {
					System.out.println(book.toString());
					
					}
				}
			if(booksOnLoan != null) {
				for(Book book : booksOnLoan) {
					System.out.println(book.toString());
					
					}
				}
		}
		System.out.println(" ");
	}

	private void booksIn() {
		System.out.println(" ");
		System.out.println("The following books are on the shelf:");
		if(booksOnShelf.isEmpty() == true) {
			System.out.println(" ");
			}
		else {
			for(Book book : booksOnShelf) {
				System.out.println(book.toString());
				System.out.println(" ");
				}
			}
		}

	
	private void genresAll() {
		System.out.println(" ");
		System.out.println("The Library has books in the following genres:");
		if(genres.isEmpty() == true) {
			
			}else {
				for(Genre genre: genres) {
					System.out.println(genre.toString());
					}
				}
		System.out.println(" ");
	}
	private void authorAll() {
		System.out.println(" ");
		System.out.println("The following authors have books in this Library:");
		if(authors.isEmpty() == true) {
			
			}else {
				for(Author author: authors) {
					System.out.println(author.toString());
					
					}
				}
		System.out.println(" ");
	}
	private char readChoice() {
		System.out.print("Enter a choice: ");

		 return In.nextChar();
	}
	public void bookAdd(String t, Author a, Genre g) {
		boolean addg = true;
		boolean addt = true;
		booksOnShelf.add(new Book(t, a,g));
		for(Genre genre : genres) {
			if(genre.toString().equals(g.toString())) {
				addg = false;
			}
		}
		if(addg == true) {
			genres.add(g);
		}
		
		for(Author author : authors) {
			if(author.toString().equals(a.toString())) {
				addt = false;
			}
		}
		if(addt == true) {
			authors.add(a);
		}
		System.out.println("Added " + t+" to catalogue.");
		System.out.println(" ");
	}
	

	public void bookRemove(String t, Author a) {
		Book removeBook = null;
		for (Book book: booksOnShelf) {
			if(book.getTitle().equals(t) ) {
				removeBook = book;
				
			}
		}
		if(removeBook != null) {
		System.out.println(removeBook + " removed from catalogue.");
		booksOnShelf.remove(removeBook);
		System.out.println(" ");
		}else {
			System.out.println("No such book found.");
			System.out.println(" ");
		}
	}
}
