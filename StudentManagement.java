import java.io.*;
import java.util.*;
import java.util.regex.*;

class Student implements Serializable {
    private String id;
    private String name;
    private String dob;
    private String gender;
    private String department;
    private String course;
    private String program;
    private String address;
    private String email;
    private String phone;
    private String status;

    public Student(String id, String name, String dob, String gender, String department, String course, String program, String address, String email, String phone, String status) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.gender = gender;
        this.department = department;
        this.course = course;
        this.program = program;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.status = status;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDob() { return dob; }
    public void setDob(String dob) { this.dob = dob; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public String getCourse() { return course; }
    public void setCourse(String course) { this.course = course; }

    public String getProgram() { return program; }
    public void setProgram(String program) { this.program = program; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "MSSV: " + id + ", Name: " + name + ", DOB: " + dob + ", Gender: " + gender + ", Department: " + department + ", Course: " + course + ", Program: " + program + ", Address: " + address + ", Email: " + email + ", Phone: " + phone + ", Status: " + status;
    }
}

public class StudentManagement {
    private static final List<Student> students = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<String> GENDERS = Arrays.asList("Male", "Female", "Other");
    private static final List<String> DEPARTMENTS = Arrays.asList("Khoa Luật", "Khoa Tiếng Anh thương mại", "Khoa Tiếng Nhật", "Khoa Tiếng Pháp");
    private static final List<String> STATUSES = Arrays.asList("Đang học", "Đã tốt nghiệp", "Đã thôi học", "Tạm dừng học");

    private static boolean isValidEmail(String email) {
        return email.matches("^[\\w-\\.+]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$");
    }

    private static boolean isValidPhone(String phone) {
        return phone.matches("\\d{10}");
    }

    public static void addStudent() {
        System.out.println("Enter student details:");
        System.out.print("MSSV: ");
        String id = scanner.nextLine();

        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("DOB (yyyy-mm-dd): ");
        String dob = scanner.nextLine();

        System.out.println("Select Gender:");
        for (int i = 0; i < GENDERS.size(); i++) {
            System.out.println((i + 1) + ". " + GENDERS.get(i));
        }
        int genderChoice = Integer.parseInt(scanner.nextLine()) - 1;
        String gender = GENDERS.get(genderChoice);

        System.out.println("Select Department:");
        for (int i = 0; i < DEPARTMENTS.size(); i++) {
            System.out.println((i + 1) + ". " + DEPARTMENTS.get(i));
        }
        int departmentChoice = Integer.parseInt(scanner.nextLine()) - 1;
        String department = DEPARTMENTS.get(departmentChoice);

        System.out.print("Course: ");
        String course = scanner.nextLine();

        System.out.print("Program: ");
        String program = scanner.nextLine();

        System.out.print("Address: ");
        String address = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();
        if (!isValidEmail(email)) {
            System.out.println("Invalid email format.");
            return;
        }

        System.out.print("Phone: ");
        String phone = scanner.nextLine();
        if (!isValidPhone(phone)) {
            System.out.println("Invalid phone number.");
            return;
        }

        System.out.println("Select Status:");
        for (int i = 0; i < STATUSES.size(); i++) {
            System.out.println((i + 1) + ". " + STATUSES.get(i));
        }
        int statusChoice = Integer.parseInt(scanner.nextLine()) - 1;
        String status = STATUSES.get(statusChoice);

        students.add(new Student(id, name, dob, gender, department, course, program, address, email, phone, status));
        System.out.println("Student added successfully.");
    }

    public static void deleteStudent() {
        if (students.isEmpty()) {
            System.out.println("No data.");
            return;
        }
        System.out.print("Enter MSSV of the student to delete: ");
        String id = scanner.nextLine();
        students.removeIf(student -> student.getId().equals(id));
        System.out.println("Student deleted successfully.");
    }

    public static void updateStudent() {
        if (students.isEmpty()) {
            System.out.println("No data.");
            return;
        }
        System.out.print("Enter MSSV of the student to update: ");
        String id = scanner.nextLine();
        for (Student student : students) {
            if (student.getId().equals(id)) {
                System.out.print("New Name (leave blank to keep unchanged): ");
                String name = scanner.nextLine();
                if (!name.isBlank()) student.setName(name);

                System.out.print("New DOB (leave blank to keep unchanged): ");
                String dob = scanner.nextLine();
                if (!dob.isBlank()) student.setDob(dob);

                System.out.println("Select New Gender (leave blank to keep unchanged):");
                for (int i = 0; i < GENDERS.size(); i++) {
                    System.out.println((i + 1) + ". " + GENDERS.get(i));
                }
                String genderInput = scanner.nextLine();
                if (!genderInput.isBlank()) {
                    int genderChoice = Integer.parseInt(genderInput) - 1;
                    student.setGender(GENDERS.get(genderChoice));
                }

                System.out.println("Select New Department (leave blank to keep unchanged):");
                for (int i = 0; i < DEPARTMENTS.size(); i++) {
                    System.out.println((i + 1) + ". " + DEPARTMENTS.get(i));
                }
                String departmentInput = scanner.nextLine();
                if (!departmentInput.isBlank()) {
                    int departmentChoice = Integer.parseInt(departmentInput) - 1;
                    student.setDepartment(DEPARTMENTS.get(departmentChoice));
                }

                System.out.print("New Course (leave blank to keep unchanged): ");
                String course = scanner.nextLine();
                if (!course.isBlank()) student.setCourse(course);

                System.out.print("New Program (leave blank to keep unchanged): ");
                String program = scanner.nextLine();
                if (!program.isBlank()) student.setProgram(program);

                System.out.print("New Address (leave blank to keep unchanged): ");
                String address = scanner.nextLine();
                if (!address.isBlank()) student.setAddress(address);

                System.out.print("New Email (leave blank to keep unchanged): ");
                String email = scanner.nextLine();
                if (!email.isBlank() && isValidEmail(email)) student.setEmail(email);

                System.out.print("New Phone (leave blank to keep unchanged): ");
                String phone = scanner.nextLine();
                if (!phone.isBlank() && isValidPhone(phone)) student.setPhone(phone);

                System.out.println("Select New Status (leave blank to keep unchanged):");
                for (int i = 0; i < STATUSES.size(); i++) {
                    System.out.println((i + 1) + ". " + STATUSES.get(i));
                }
                String statusInput = scanner.nextLine();
                if (!statusInput.isBlank()) {
                    int statusChoice = Integer.parseInt(statusInput) - 1;
                    student.setStatus(STATUSES.get(statusChoice));
                }

                System.out.println("Student updated successfully.");
                return;
            }
        }
        System.out.println("Student not found.");
    }

    public static void searchStudent() {
        if (students.isEmpty()) {
            System.out.println("No data.");
            return;
        }
        System.out.print("Enter Name or MSSV to search: ");
        String query = scanner.nextLine().toLowerCase();
        for (Student student : students) {
            if (student.getId().toLowerCase().contains(query) || student.getName().toLowerCase().contains(query)) {
                System.out.println(student);
            }
        }
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nStudent Management System");
            System.out.println("1. Add Student");
            System.out.println("2. Delete Student");
            System.out.println("3. Update Student");
            System.out.println("4. Search Student");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> deleteStudent();
                case 3 -> updateStudent();
                case 4 -> searchStudent();
                case 5 -> {
                    System.out.println("Exiting program.");
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }
}