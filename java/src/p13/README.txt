BOOK ISSUE MANAGEMENT - NETBEANS PROJECT

Requirements:
- JDK 11 or compatible
- NetBeans with Maven support
- MySQL Server running locally

SETUP:
1. Extract this ZIP file.
2. In NetBeans, choose File > Open Project and select the extracted
   BookIssueManagement folder (the folder containing pom.xml).
3. In MySQL/phpMyAdmin, run:
      CREATE DATABASE book_management;
4. Open src/main/resources/hibernate.cfg.xml.
   Update the MySQL username and password for your computer.
5. Right-click the project and choose Clean and Build, then Run.
6. On the first run, the application inserts sample students and books.

Sample students:
ID 1: Amit (CE001)
ID 2: Neha (CE002)

Sample books (for a fresh database):
ID 1: Java Programming (2 copies)
ID 2: Database Systems (1 copy)
ID 3: Web Technology (1 copy)

Features:
- View books and available copies
- Issue books (maximum 3 active books per student)
- Reject unavailable books
- Return a book using its Issue ID
- Display overdue books

Notes:
- Maven downloads Hibernate and MySQL dependencies on first build,
  so an internet connection may be required initially.
- This is a simple educational console project. It does not include
  login, fines, or a graphical interface.
