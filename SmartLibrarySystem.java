import java.util.*;
class SmartLibrarySystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Library library = new Library();
        int choice;
        do {
            System.out.println("\n Smart Library Menu !");
            System.out.println("1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Search Book");
            System.out.println("4. Borrow Book");
            System.out.println("5. Return Book");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine(); 

            switch(choice) {
                case 1:
                    System.out.print("Enter ID: "); int id = sc.nextInt(); sc.nextLine();
                    System.out.print("Enter Title: "); String title = sc.nextLine();
                    System.out.print("Enter Author: "); String author = sc.nextLine();
                    System.out.print("Enter Genre: "); String genre = sc.nextLine();
                    library.addBook(new Book(id, title, author, genre));
                    break;
                case 2:
                    library.viewBooks();
                    break;
                case 3:
                    System.out.print("Enter title/author/genre to search: "); 
                    String keyword = sc.nextLine();
                    library.searchBook(keyword);
                    break;
                case 4:
                    System.out.print("Enter Book ID to borrow: "); 
                    int borrowId = sc.nextInt();
                    library.borrowBook(borrowId);
                    break;
                case 5:
                    System.out.print("Enter Book ID to return: "); 
                    int returnId = sc.nextInt();
                    library.returnBook(returnId);
                    break;
                case 6:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while(choice != 6);
        sc.close();
    }
}
class Book {
    private int id;
    private String title;
    private String author;
    private String genre;
    private boolean isAvailable;
    public Book(int id, String title, String author, String genre) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.isAvailable = true;
    }
    public int getId() { 
        return id; 
    }
    public String getTitle() { 
        return title; 
    }
    public String getAuthor() { 
        return author; 
    }
    public String getGenre() { 
        return genre;
    }
    public boolean isAvailable() { 
        return isAvailable; 
    }
    public void setAvailable(boolean available) { 
        this.isAvailable = available;
    }
    public String toString() {
        return "ID: " + id + ", Title: " + title + ", Author: " + author + ", Genre: " + genre + ", Available: " + (isAvailable ? "Yes" : "No");
    }
}
class Library {
    private List<Book> books;
    public Library() {
        books = new ArrayList<>();
    }
    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book added successfully!");
    }
    public void viewBooks() {
        if(books.isEmpty())
            System.out.println("No books in library.");
        else {
            for(Book b : books) {
                System.out.println(b);
            }
        }
    }
    public void searchBook(String keyword) {
        boolean found = false;
        for(Book b : books) {
            if(b.getTitle().equalsIgnoreCase(keyword) ||
               b.getAuthor().equalsIgnoreCase(keyword) ||
               b.getGenre().equalsIgnoreCase(keyword)) {
                System.out.println(b);
                found = true;
            }
        }
        if(!found) System.out.println("No matching book found.");
    }
    public void borrowBook(int id) {
        for(Book b : books) {
            if(b.getId() == id) {
                if(b.isAvailable()) {
                    b.setAvailable(false);
                    System.out.println("You borrowed: " + b.getTitle());
                } else {
                    System.out.println("Book is already borrowed!");
                }
                return;
            }
        }
        System.out.println("Book ID not found.");
    }
    public void returnBook(int id) {
        for(Book b : books) {
            if(b.getId() == id) {
                if(!b.isAvailable()) {
                    b.setAvailable(true);
                    System.out.println("You returned: " + b.getTitle());
                } else {
                    System.out.println("This book was not borrowed!");
                }
                return;
            }
        }
        System.out.println("Book ID not found.");
    }
}
