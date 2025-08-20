# Bookshop Web Application

A web-based bookshop management system built with Java and MySQL.

## Prerequisites

Before setting up this project, ensure you have the following installed:

- **IDE**: IntelliJ IDEA or Visual Studio Code
- **XAMPP Server** (for MySQL database)
- **Apache Tomcat Server**
- **Java JDK** (version 8 or higher)
- **Maven** (for dependency management)

## Setup Instructions

### 1. Database Setup

1. Start XAMPP and ensure MySQL service is running
2. Open phpMyAdmin in your browser (usually `http://localhost/phpmyadmin`)
3. Create a new database for the project
4. Import the database schema:
   - Locate the `database.sql` file in the project codebase
   - Import this file into your newly created database in phpMyAdmin

### 2. Project Setup

1. **Clone the Repository**
   ```bash
   git clone <repository-url>
   cd bookshop
   ```

2. **Open in IDE**
   - Open IntelliJ IDEA or VS Code
   - Open the cloned project folder

3. **Build the Project**
   - Open the terminal in your IDE
   - Run the following Maven command:
   ```bash
   mvn clean package
   ```
   - This will create a `.war` file in the `target` folder

### 3. Deploy to Tomcat

1. **Locate the WAR file**
   - After successful build, find the `.war` file in the `target` directory

2. **Deploy to Tomcat**
   - Copy the `.war` file
   - Navigate to your Tomcat installation directory
   - Paste the `.war` file into the `webapps` folder

3. **Start Tomcat Service**
   - Ensure Tomcat service is running
   - If not running, start the Tomcat service

### 4. Access the Application

Once everything is set up:

1. Open your web browser
2. Navigate to: `http://localhost:8080/bookshop`
3. The application should now be accessible

## Project Structure

```
bookshop/
├── src/
│   ├── main/
│   │   ├── java/
│   │   ├── resources/
│   │   └── webapp/
├── target/
├── database.sql
├── pom.xml
└── README.md
```

## Troubleshooting

### Common Issues

1. **Port Conflicts**
   - Ensure ports 8080 (Tomcat) and 3306 (MySQL) are not being used by other applications

2. **Database Connection Issues**
   - Verify XAMPP MySQL service is running
   - Check database credentials in the application configuration

3. **Build Failures**
   - Ensure Maven is properly installed and configured
   - Check Java version compatibility

4. **Tomcat Deployment Issues**
   - Verify Tomcat is running on port 8080
   - Check Tomcat logs for any error messages
   - Ensure the WAR file is properly deployed in the webapps folder

### Quick Checks

- **XAMPP Status**: Verify MySQL is running in XAMPP control panel
- **Tomcat Status**: Check if Tomcat service is active
- **Database**: Confirm the database and tables are created correctly
- **WAR File**: Ensure the WAR file is generated successfully in the target folder

## Support

If you encounter any issues during setup, please check the troubleshooting section above or refer to the project documentation.

## Technologies Used

- Java
- Maven
- MySQL
- Apache Tomcat
- HTML/CSS/JavaScript (assumed for web interface)

---

**Note**: Make sure all services (XAMPP MySQL and Tomcat) are running before accessing the application.
