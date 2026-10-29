import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {
    public static void main(String[] args) {
        // Spring reads the XML and creates/wires the configured objects.
        try (ClassPathXmlApplicationContext context =
                     new ClassPathXmlApplicationContext("applicationContext.xml")) {

            // Get the already configured Student object from Spring.
            Student student = context.getBean("student", Student.class);
            student.displayDetails();
        }
    }
}
