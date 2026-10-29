import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class MainApp {
    // One SessionFactory is shared by the application.
    private static final SessionFactory factory =
            new Configuration().configure().buildSessionFactory();

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        addSampleData();
        int choice;

        do {
            System.out.println("\n===== BOOK ISSUE MANAGEMENT =====");
            System.out.println("1. View All Books");
            System.out.println("2. Issue a Book");
            System.out.println("3. Return a Book");
            System.out.println("4. View Overdue Books");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");

            // Handle non-numeric menu input gracefully.
            while (!sc.hasNextInt()) {
                System.out.print("Please enter a number: ");
                sc.next();
            }
            choice = sc.nextInt();

            switch (choice) {
                case 1: viewBooks(); break;
                case 2: issueBook(); break;
                case 3: returnBook(); break;
                case 4: viewOverdueBooks(); break;
                case 0: System.out.println("Application closed."); break;
                default: System.out.println("Invalid choice!");
            }
        } while (choice != 0);

        sc.close();
        factory.close();
    }

    // Add demo records only when no books exist.
    private static void addSampleData() {
        try (Session session = factory.openSession()) {

            Transaction tx = session.beginTransaction();
            session.save(new Student("Amit", "CE001"));
            session.save(new Student("Neha", "CE002"));
            session.save(new Book("Java Programming", "James Gosling", 2));
            session.save(new Book("Database Systems", "Korth", 1));
            session.save(new Book("Web Technology", "Deitel", 1));
            tx.commit();

            System.out.println("Sample data added.");
        } catch (Exception e) {
            System.out.println("Error adding sample data: " + e.getMessage());
        }
    }

    private static void viewBooks() {
        try (Session session = factory.openSession()) {
            List<Book> books = session.createQuery("from Book", Book.class).list();
            System.out.println("\n--- Books ---");
            for (Book book : books) {
                System.out.println(book);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void issueBook() {
        System.out.print("Enter Student ID: ");
        int studentId = sc.nextInt();
        System.out.print("Enter Book ID: ");
        int bookId = sc.nextInt();

        Transaction tx = null;
        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();

            Student student = session.get(Student.class, studentId);
            Book book = session.get(Book.class, bookId);

            if (student == null) {
                System.out.println("Student not found!");
                tx.rollback();
                return;
            }
            if (book == null) {
                System.out.println("Book not found!");
                tx.rollback();
                return;
            }

            // Count only issues that have not been returned.
            Long issuedCount = session.createQuery(
                    "select count(i) from BookIssue i "
                    + "where i.student.id = :sid and i.returnDate is null",
                    Long.class
            ).setParameter("sid", studentId).uniqueResult();

            // Rule 1: A student can hold at most three books.
            if (issuedCount != null && issuedCount >= 3) {
                System.out.println("Cannot issue: Student already has 3 books.");
                tx.rollback();
                return;
            }

            // Rule 2: Do not issue a book when no copies are available.
            if (book.getAvailableCopies() <= 0) {
                System.out.println("Book is not available!");
                tx.rollback();
                return;
            }

            BookIssue issue = new BookIssue(student, book);
            session.save(issue);
            book.setAvailableCopies(book.getAvailableCopies() - 1);

            tx.commit();
            System.out.println("Book issued successfully!");
            System.out.println("Issue ID: " + issue.getId());
            System.out.println("Due Date: " + issue.getDueDate());
        } catch (Exception e) {
            if (tx != null && tx.isActive()) tx.rollback();
            System.out.println("Error issuing book: " + e.getMessage());
        }
    }

    private static void returnBook() {
        System.out.print("Enter Issue ID: ");
        int issueId = sc.nextInt();

        Transaction tx = null;
        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();
            BookIssue issue = session.get(BookIssue.class, issueId);

            if (issue == null) {
                System.out.println("Issue record not found!");
                tx.rollback();
                return;
            }
            if (issue.getReturnDate() != null) {
                System.out.println("This book is already returned.");
                tx.rollback();
                return;
            }

            issue.setReturnDate(LocalDate.now());
            Book book = issue.getBook();
            book.setAvailableCopies(book.getAvailableCopies() + 1);

            tx.commit();
            System.out.println("Book returned successfully!");
        } catch (Exception e) {
            if (tx != null && tx.isActive()) tx.rollback();
            System.out.println("Error returning book: " + e.getMessage());
        }
    }

    private static void viewOverdueBooks() {
        try (Session session = factory.openSession()) {
            List<BookIssue> overdueBooks = session.createQuery(
                    "from BookIssue i where i.dueDate < :today "
                    + "and i.returnDate is null",
                    BookIssue.class
            ).setParameter("today", LocalDate.now()).list();

            System.out.println("\n--- Overdue Books ---");
            if (overdueBooks.isEmpty()) {
                System.out.println("No overdue books.");
                return;
            }

            for (BookIssue issue : overdueBooks) {
                System.out.println("Issue ID: " + issue.getId()
                        + " | Student: " + issue.getStudent().getName()
                        + " | Book: " + issue.getBook().getTitle()
                        + " | Due Date: " + issue.getDueDate());
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
