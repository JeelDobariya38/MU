public class Student {
    private final Address address; // Required dependency: supplied through constructor
    private Course course;         // Optional dependency: supplied through setter

    // Constructor injection
    public Student(Address address) {
        this.address = address;
        System.out.println("Student created using constructor injection");
    }

    // Setter injection
    public void setCourse(Course course) {
        this.course = course;
        System.out.println("Course supplied using setter injection");
    }

    public void displayDetails() {
        System.out.println("Student city: " + address.getCity());
        if (course != null) {
            System.out.println("Student course: " + course.getCourseName());
        }
    }
}
