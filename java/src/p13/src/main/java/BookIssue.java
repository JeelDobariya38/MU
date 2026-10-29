import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "book_issue")
public class BookIssue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // Many issue records can belong to one student. Therefore, the relationship from BookIssue to Student is many-to-one.
    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)  // @JoinColumn tells Hibernate which database column connects the issue record to the student.
    private Student student;

    // Many issue records can refer to one book over time. Therefore, the relationship from BookIssue to Book is many-to-one.
    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)  // @JoinColumn tells Hibernate which database column connects the issue record to the book.
    private Book book;

    private LocalDate issueDate;
    private LocalDate dueDate;

    // Null means the book has not been returned yet.
    private LocalDate returnDate;

    public BookIssue() {
    }

    public BookIssue(Student student, Book book) {
        this.student = student;
        this.book = book;
        this.issueDate = LocalDate.now();
        this.dueDate = LocalDate.now().plusDays(14);
        this.returnDate = null;
    }

    public int getId() { return id; }
    public Student getStudent() { return student; }
    public Book getBook() { return book; }
    public LocalDate getIssueDate() { return issueDate; }
    public LocalDate getDueDate() { return dueDate; }
    public LocalDate getReturnDate() { return returnDate; }
    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
}
