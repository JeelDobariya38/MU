COMPLAINT MANAGEMENT SYSTEM - APACHE NETBEANS IDE 25

Requirements:
- JDK 17 or newer
- Apache NetBeans IDE 25
- Internet connection for Maven to download dependencies

OPEN AND RUN:
1. Extract ComplaintManagement.zip.
2. Open Apache NetBeans IDE 25.
3. Select File > Open Project.
4. Select the extracted ComplaintManagement folder (the folder containing pom.xml).
5. Wait for Maven dependencies to load.
6. Right-click the project and choose Clean and Build.
7. Right-click the project and choose Run, or open ComplaintManagementApplication.java and run it.
8. Open a browser at http://localhost:8080

FEATURES:
- Register complaints
- Change complaint status: Pending, In Progress, Resolved
- Change priority: Low, Medium, High
- View all complaints
- View pending complaints only

NOTES:
- This starter project stores complaints in memory. Data resets when the app stops.
- The first build needs an internet connection so Maven can download Spring dependencies.
- Use JDK 17. In NetBeans, check Tools > Java Platforms if the project cannot find Java 17.
- If port 8080 is busy, change server.port=8081 in src/main/resources/application.properties and open http://localhost:8081.
