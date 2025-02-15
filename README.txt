Student Management System Guide

1. Source Code Structure

The program consists of two main classes:

Student Class:
-Implements Serializable to allow object persistence.
-Contains private attributes for student details such as id, name, dob, gender, etc.
-Provides a constructor for initialization.
-Includes getter and setter methods for accessing and modifying student data.
-Overrides the toString() method for displaying student information.

StudentManagement Class
-Manages a list of students stored in an ArrayList.
-Uses a Scanner for user input.
-Implements various functionalities:
    addStudent(): Adds a new student with validation for email and phone.
    deleteStudent(): Deletes a student based on MSSV (student ID).
    updateStudent(): Updates student details interactively.
    searchStudent(): Searches for a student by name or MSSV.
-Provides a menu-driven system inside main() to allow user interaction.

2. Compilation Instructions

Pre-condition:
-Ensure that Java Development Kit (JDK) 8 or later is installed on your system.
-Set up the Java environment variables if needed (JAVA_HOME, PATH).

Compile the Code:
-Open a terminal or command prompt, navigate to the directory where the source file is saved, and run:

javac StudentManagement.java

-This will generate StudentManagement.class and Student.class files in the same directory.

3. Running the Program

After successful compilation, run the program using:

java StudentManagement

4. Using the Program

When executed, the program displays the following menu:

Student Management System
1. Add Student
2. Delete Student
3. Update Student
4. Search Student
5. Exit
Choose an option:

User Operations:
-Adding a Student: Enter student details step-by-step.
-Deleting a Student: Provide the MSSV to remove a student.
-Updating a Student: Enter the MSSV and modify necessary fields.
-Searching for a Student: Search by name or MSSV.
-Exiting the Program: Select option 5 to terminate execution.

The current program runs in memory only.
