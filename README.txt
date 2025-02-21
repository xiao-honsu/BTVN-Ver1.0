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
    saveToFile() & loadFromFile(): Saves and loads student data from a file for persistence.
-Provides a menu-driven system inside main() to allow user interaction.

2. Compilation Instructions

Pre-condition:
-Ensure that Java Development Kit (JDK) 8 or later is installed on your system.
-Set up the Java environment variables if needed (JAVA_HOME, PATH).
-Download and include json.jar in the same directory as the source files.
Compile the Code:
-Open a terminal or command prompt, navigate to the directory where the source file is saved, and run:

javac -cp ".;json.jar" StudentManagement.java

-This will generate StudentManagement.class and Student.class files in the same directory.

3. Running the Program

After successful compilation, run the program using:

java -cp ".;json.jar" StudentManagement

4. Using the Program

When executed, the program displays the following menu:

Student Management System
1. Add Student
2. Delete Student
3. Update Student
4. Search Student
5. Save Data
6. Load Data
7. Exit
Choose an option:

Option 1: Add Student

The system prompts the user to enter the following details:
    MSSV (Student ID)
    Name
    Date of Birth (Format: YYYY-MM-DD)
    Gender (Male/Female)
    Department
    Course
    Program
    Address
    Email (Validated for proper format)
    Phone Number (Validated for digits only)
    Status
If the input is valid, the student is added to the list.
The system confirms the addition with a success message.

Option 2: Delete Student

The system asks for the MSSV of the student to be deleted.
If the student exists, their details are displayed, and the user is asked for confirmation.
Upon confirmation, the student is removed from the list.
If the MSSV is not found, an error message is shown.

Option 3: Update Student

The system prompts the user to enter the MSSV of the student to update.
If the student exists, their current details are displayed.
The user can choose which field to update (Name, DOB, Gender, Email, Phone,...).
After making changes, the system confirms the update.
If the MSSV is not found, an error message is displayed.

Option 4: Search Student

The user is given three choices:
Search by MSSV or Name
Search by Department
Search by Department + Name
If a match is found, the student’s details are displayed.
If no match is found, the system notifies the user.

Option 5: Save Data

Saves the current list of students to a file for persistence.
The system confirms that the data has been saved successfully.

Option 6: Load Data

Loads student data from a previously saved file.
The system confirms successful loading or notifies if no data is found.

Option 7: Exit

The system terminates the execution.
If data has not been saved before exiting, it will be lost.


The current program supports data persistence through file storage.
