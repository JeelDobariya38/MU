import javax.persistence.*;

@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Column(unique = true)
    private String enrollmentNo;

    public Student() {
    }

    public Student(String name, String enrollmentNo) {
        this.name = name;
        this.enrollmentNo = enrollmentNo;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getEnrollmentNo() { return enrollmentNo; }
}
