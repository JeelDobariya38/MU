# Simple Spring Dependency Injection Demo

This small Maven project demonstrates only two techniques:

1. **Constructor injection**: Spring passes an `Address` object into `Student(Address address)`.
2. **Setter injection**: Spring calls `setCourse(Course course)` to supply a `Course` object.

## Project flow

1. `MainApp` loads `applicationContext.xml` using `ClassPathXmlApplicationContext`.
2. Spring creates the `Address` and `Course` beans.
3. Spring creates `Student` and injects `Address` through its constructor.
4. Spring calls `setCourse()` to inject the `Course` object.
5. `MainApp` retrieves the configured `Student` bean and calls `displayDetails()`.

## Files

- `pom.xml`: Maven project setup and Spring dependency.
- `Address.java`: a simple dependency used to demonstrate constructor injection.
- `Course.java`: a simple dependency used to demonstrate setter injection.
- `Student.java`: receives both dependencies using the two injection methods.
- `applicationContext.xml`: tells Spring which objects to create and how to connect them.
- `MainApp.java`: starts Spring and uses the configured Student object.

## Run in NetBeans

1. Extract the ZIP file.
2. In NetBeans, choose **File > Open Project** and select the extracted `spring-di-simple` folder.
3. Wait for Maven dependencies to download.
4. Open `MainApp.java` and run the file, or run the project with `com.example.di.MainApp` as the main class.

Java 17 or newer is required for Spring Framework 6.

## Try these experiments

- Comment out `<property name="course" ref="course"/>`. The student still works, but no course is printed.
- Remove `<constructor-arg ref="address"/>`. Spring cannot construct `Student`, because the class requires an `Address` constructor argument.
- Change the city returned by `Address.getCity()` and rerun the program.
