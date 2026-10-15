import java.util.Scanner;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Main {

    private static final Scanner sc = new Scanner(System.in);
    
    static {
        java.util.logging.Logger.getLogger("org.hibernate")
                .setLevel(java.util.logging.Level.SEVERE);

        java.util.logging.Logger.getLogger("org.jboss")
                .setLevel(java.util.logging.Level.SEVERE);
    }

    public static void main(String[] args) {

        try {
            int choice;

            do {
                System.out.println("\n================================");
                System.out.println("     EMPLOYEE MANAGEMENT");
                System.out.println("================================");
                System.out.println("1. Add Employee");
                System.out.println("2. Select Employee by ID");
                System.out.println("3. Delete Employee");
                System.out.println("4. Increase Salary by 30%");
                System.out.println("5. Display All Employees");
                System.out.println("0. Exit");
                System.out.println("================================");
                System.out.print("Enter your choice: ");

                choice = readInt();

                switch (choice) {
                    case 1 -> addEmployee();
                    case 2 -> selectEmployee();
                    case 3 -> deleteEmployee();
                    case 4 -> increaseSalary();
                    case 5 -> displayAllEmployees();
                    case 0 -> System.out.println("Exiting program...");
                    default -> System.out.println(
                                "Invalid choice! Please try again."
                        );
                }
            } while (choice != 0);

        } catch (HibernateException e) {
            System.err.println("Hibernate error: " + e.getMessage());
        } finally {
            HibernateUtil.shutdown();
            sc.close();
        }
    }

    // 1. Add Employee
    private static void addEmployee() {

        System.out.println("\n===== ADD EMPLOYEE =====");

        System.out.print("Enter Employee Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Salary: ");
        double salary = readDouble();

        if (salary < 0) {
            System.out.println("Salary cannot be negative.");
            return;
        }

        Transaction tx = null;

        try (Session session =
                     HibernateUtil.getSessionFactory().openSession()) {

            tx = session.beginTransaction();

            Employee employee = new Employee();
            employee.setName(name);
            employee.setSalary(salary);

            session.persist(employee);
            tx.commit();

            System.out.println("Employee added successfully!");
            System.out.println(employee);

        } catch (HibernateException e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            System.out.println("Unable to add employee: "
                    + e.getMessage());
        }
    }

    // 2. Select Employee by ID
    private static void selectEmployee() {

        System.out.println("\n===== SELECT EMPLOYEE =====");

        System.out.print("Enter Employee ID: ");
        int id = readInt();

        try (Session session =
                     HibernateUtil.getSessionFactory().openSession()) {

            Employee employee = session.get(Employee.class, id);

            if (employee != null) {
                System.out.println("\nEmployee Found:");
                System.out.println("------------------------------");
                System.out.println("ID          : " + employee.getId());
                System.out.println("Name        : " + employee.getName());
                System.out.printf("Salary      : %.2f%n",
                        employee.getSalary());
            } else {
                System.out.println("Employee ID " + id + " not found.");
            }

        } catch (HibernateException e) {
            System.out.println("Unable to find employee: "
                    + e.getMessage());
        }
    }

    // 3. Delete Employee by ID
    private static void deleteEmployee() {

        System.out.println("\n===== DELETE EMPLOYEE =====");

        System.out.print("Enter Employee ID to delete: ");
        int id = readInt();

        Transaction tx = null;

        try (Session session =
                     HibernateUtil.getSessionFactory().openSession()) {

            tx = session.beginTransaction();

            Employee employee = session.get(Employee.class, id);

            if (employee != null) {
                session.remove(employee);
                tx.commit();

                System.out.println(
                        "Employee deleted successfully!"
                );
            } else {
                tx.rollback();
                System.out.println("Employee ID " + id + " not found.");
            }

        } catch (HibernateException e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            System.out.println("Unable to delete employee: "
                    + e.getMessage());
        }
    }

    // 4. Increase Salary by 10%
    private static void increaseSalary() {

        System.out.println("\n===== INCREASE SALARY BY 30% =====");

        System.out.print("Enter Employee ID: ");
        int id = readInt();

        Transaction tx = null;

        try (Session session =
                     HibernateUtil.getSessionFactory().openSession()) {

            tx = session.beginTransaction();

            Employee employee = session.get(Employee.class, id);

            if (employee != null) {

                double oldSalary = employee.getSalary();
                double newSalary =
                        Math.round(oldSalary * 1.10 * 100.0) / 100.0;

                employee.setSalary(newSalary);

                tx.commit();

                System.out.println("Salary updated successfully!");
                System.out.println("Employee Name: "
                        + employee.getName());
                System.out.printf("Old Salary: %.2f%n", oldSalary);
                System.out.printf("New Salary: %.2f%n", newSalary);

            } else {
                tx.rollback();
                System.out.println("Employee ID " + id + " not found.");
            }

        } catch (HibernateException e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            System.out.println("Unable to update salary: "
                    + e.getMessage());
        }
    }

    // 5. Display All Employees
    private static void displayAllEmployees() {

        System.out.println("\n===== ALL EMPLOYEES =====");

        try (Session session =
                     HibernateUtil.getSessionFactory().openSession()) {

            java.util.List<Employee> employees =
                    session.createQuery(
                            "from Employee", Employee.class
                    ).getResultList();

            if (employees.isEmpty()) {
                System.out.println("No employees found.");
            } else {
                employees.forEach(System.out::println);
            }

        } catch (HibernateException e) {
            System.out.println("Unable to display employees: "
                    + e.getMessage());
        }
    }

    // Read integer safely
    private static int readInt() {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Enter a valid integer: ");
            }
        }
    }

    // Read decimal number safely
    private static double readDouble() {
        while (true) {
            try {
                return Double.parseDouble(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Enter a valid number: ");
            }
        }
    }
}
