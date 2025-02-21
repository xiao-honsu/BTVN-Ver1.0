import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.util.logging.*;
import org.json.*;

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

    public String toCSV() {
        return String.join(",", id, name, dob, gender, department, course, program, address, email, phone, status);
    }
    public static Student fromCSV(String line) {
        String[] data = line.split(",");
        return new Student(data[0], data[1], data[2], data[3], data[4], data[5], data[6], data[7], data[8], data[9], data[10]);
    }

    public JSONObject toJSONObject() {
        JSONObject obj = new JSONObject();
        obj.put("MSSV", id);
        obj.put("Name", name);
        obj.put("DOB", dob);
        obj.put("Gender", gender);
        obj.put("Department", department);
        obj.put("Course", course);
        obj.put("Program", program);
        obj.put("Address", address);
        obj.put("Email", email);
        obj.put("Phone", phone);
        obj.put("Status", status);
        return obj;
    }

    public static Student fromJSONObject(JSONObject obj) {
        return new Student(
            obj.getString("MSSV"), obj.getString("Name"), obj.getString("DOB"), obj.getString("Gender"),
            obj.getString("Department"), obj.getString("Course"), obj.getString("Program"), obj.getString("Address"),
            obj.getString("Email"), obj.getString("Phone"), obj.getString("Status")
        );
    }

    public String toJSON() {
        return new JSONObject(this).toString();
    }
}

public class StudentManagement {
    private static final Logger logger = Logger.getLogger(StudentManagement.class.getName());
    private static final List<Student> students = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);
    private static final String FILE_PATH = "students.csv";
    private static final String JSON_FILE_PATH = "students.json";
    private static final String VERSION = "2.0";
    private static final String BUILD_DATE = "2025-02-20";
    private static final List<String> GENDERS = Arrays.asList("Male", "Female", "Other");
    private static final List<String> DEPARTMENTS = Arrays.asList("Khoa Luật", "Khoa Tiếng Anh thương mại", "Khoa Tiếng Nhật", "Khoa Tiếng Pháp");
    private static final List<String> STATUSES = Arrays.asList("Đang học", "Đã tốt nghiệp", "Đã thôi học", "Tạm dừng học");

    public static void loadFromCSV() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            boolean isFirstLine = true;
            while ((line = br.readLine()) != null) {
                if (isFirstLine) { // Skip header line
                    isFirstLine = false;
                    continue;
                }
                students.add(Student.fromCSV(line));
            }
            logger.info("Data loaded successfully from " + FILE_PATH);
        } catch (IOException e) {
            logger.warning("No existing CSV data found or error reading file.");
        }
    }

    public static void saveToCSV() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            bw.write("MSSV,Name,DOB,Gender,Department,Course,Program,Address,Email,Phone,Status");
            bw.newLine();
            for (Student student : students) {
                bw.write(student.toCSV());
                bw.newLine();
            }
            logger.info("Data saved successfully to " + FILE_PATH);
        } catch (IOException e) {
            logger.severe("Error saving data: " + e.getMessage());
        }
    }

    public static void loadFromJSON() {
        try (BufferedReader br = new BufferedReader(new FileReader(JSON_FILE_PATH))) {
            StringBuilder jsonBuilder = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                jsonBuilder.append(line);
            }
            JSONArray jsonArray = new JSONArray(jsonBuilder.toString());
            for (int i = 0; i < jsonArray.length(); i++) {
                students.add(Student.fromJSONObject(jsonArray.getJSONObject(i)));
            }
            logger.info("Data loaded successfully from " + JSON_FILE_PATH);
        } catch (IOException | JSONException e) {
            logger.warning("No existing JSON data found or error reading file.");
        }
    }

    public static void saveToJSON() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(JSON_FILE_PATH))) {
            JSONArray jsonArray = new JSONArray();
            for (Student student : students) {
                jsonArray.put(student.toJSONObject());
            }
            bw.write(jsonArray.toString(4));
            logger.info("Data saved successfully to " + JSON_FILE_PATH);
        } catch (IOException e) {
            logger.severe("Error saving JSON data: " + e.getMessage());
        }
    }

    private static void importFromJSON(String filePath) throws IOException, JSONException {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            StringBuilder jsonBuilder = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                jsonBuilder.append(line);
            }
            JSONArray jsonArray = new JSONArray(jsonBuilder.toString());
            for (int i = 0; i < jsonArray.length(); i++) {
                Student newStudent = Student.fromJSONObject(jsonArray.getJSONObject(i));
                if (!isStudentExists(newStudent.getId())) {
                    students.add(newStudent);
                }
            }
            logger.info("Data imported successfully from JSON file: " + filePath);
        }
    }

    private static void importFromCSV(String filePath) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine(); // Skip header
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length == 11) {
                    Student newStudent = new Student(values[0], values[1], values[2], values[3], values[4], values[5],
                        values[6], values[7], values[8], values[9], values[10]);
                    if (!isStudentExists(newStudent.getId())) {
                        students.add(newStudent);
                    }
                }
            }
            logger.info("Data imported successfully from CSV file: " + filePath);
        }
    }

    private static boolean isStudentExists(String id) {
        for (Student student : students) {
            if (student.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public static void showVersion() {
        System.out.println("Student Management System - Version: " + VERSION + " - Build Date: " + BUILD_DATE);
    }

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

        System.out.print("DOB (dd-mm-yy): ");
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
                boolean updating = true;
                while (updating) {
                    System.out.println("Select the field to update:");
                    System.out.println("1. Name\n2. DOB\n3. Gender\n4. Department\n5. Course\n6. Program\n7. Address\n8. Email\n9. Phone\n10. Status\n11. Exit");
                    System.out.print("Choose an option: ");
                    int choice = Integer.parseInt(scanner.nextLine());
                    
                    if (choice == 11) {
                        updating = false;
                        break;
                    }
                    
                    System.out.print("Enter new value: ");
                    String newValue = scanner.nextLine();
                    
                    switch (choice) {
                        case 1 -> student.setName(newValue);
                        case 2 -> student.setDob(newValue);
                        case 3 -> student.setGender(GENDERS.contains(newValue) ? newValue : student.getGender());
                        case 4 -> student.setDepartment(DEPARTMENTS.contains(newValue) ? newValue : student.getDepartment());
                        case 5 -> student.setCourse(newValue);
                        case 6 -> student.setProgram(newValue);
                        case 7 -> student.setAddress(newValue);
                        case 8 -> { if (isValidEmail(newValue)) student.setEmail(newValue); }
                        case 9 -> { if (isValidPhone(newValue)) student.setPhone(newValue); }
                        case 10 -> student.setStatus(STATUSES.contains(newValue) ? newValue : student.getStatus());
                        default -> System.out.println("Invalid option.");
                    }
                    System.out.println("Field updated successfully.");
                }
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
        System.out.println("Search options:");
        System.out.println("1. Search by MSSV or Name");
        System.out.println("2. Search by Department");
        System.out.println("3. Search by Department + Name");
        System.out.print("Choose an option: ");
        int choice = Integer.parseInt(scanner.nextLine());
        
        switch (choice) {
            case 1 -> {
                System.out.print("Enter Name or MSSV to search: ");
                String query = scanner.nextLine().toLowerCase();
                students.stream()
                        .filter(s -> s.getId().toLowerCase().contains(query) || s.getName().toLowerCase().contains(query))
                        .forEach(System.out::println);
            }
            case 2 -> {
                System.out.println("Select Department:");
                for (int i = 0; i < DEPARTMENTS.size(); i++) {
                    System.out.println((i + 1) + ". " + DEPARTMENTS.get(i));
                }
                int departmentChoice = Integer.parseInt(scanner.nextLine()) - 1;
                if (departmentChoice >= 0 && departmentChoice < DEPARTMENTS.size()) {
                    String department = DEPARTMENTS.get(departmentChoice);
                    students.stream()
                            .filter(s -> s.getDepartment().equals(department))
                            .forEach(System.out::println);
                }
            }
            case 3 -> {
                System.out.println("Select Department:");
                for (int i = 0; i < DEPARTMENTS.size(); i++) {
                    System.out.println((i + 1) + ". " + DEPARTMENTS.get(i));
                }
                int departmentChoice = Integer.parseInt(scanner.nextLine()) - 1;
                if (departmentChoice >= 0 && departmentChoice < DEPARTMENTS.size()) {
                    String department = DEPARTMENTS.get(departmentChoice);
                    System.out.print("Enter Name to search: ");
                    String nameQuery = scanner.nextLine().toLowerCase();
                    students.stream()
                            .filter(s -> s.getDepartment().equals(department) && s.getName().toLowerCase().contains(nameQuery))
                            .forEach(System.out::println);
                }
            }
            default -> System.out.println("Invalid option.");
        }
    }

    public static void exportData() {
        System.out.println("Choose export format: 1. CSV  2. JSON");
        int choice = scanner.nextInt();
        scanner.nextLine(); 
        if (choice == 1) {
            saveToCSV();
            System.out.println("Data exported to CSV successfully.");
        } else if (choice == 2) {
            saveToJSON();
            System.out.println("Data exported to JSON successfully.");
        } else {
            System.out.println("Invalid choice. No data exported.");
        }
    }

    public static void importData() {
        System.out.println("Choose file type to import:");
        System.out.println("1. CSV");
        System.out.println("2. JSON");
        System.out.print("Enter your choice: ");
        String choice = scanner.nextLine();
    
        String fileType = "";
        if (choice.equals("1")) {
            fileType = "csv";
        } else if (choice.equals("2")) {
            fileType = "json";
        } else {
            System.out.println("Invalid choice.");
            return;
        }
    
        System.out.print("Enter file path: ");
        String filePath = scanner.nextLine();
    
        try {
            if (fileType.equals("json")) {
                importFromJSON(filePath);
            } else if (fileType.equals("csv")) {
                importFromCSV(filePath);
            }
        } catch (Exception e) {
            logger.severe("Error importing data: " + e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        logger.info("Application started.");
        showVersion();
        loadFromCSV();
        while (true) {
            System.out.println("\nStudent Management System");
            System.out.println("1. Add Student");
            System.out.println("2. Delete Student");
            System.out.println("3. Update Student");
            System.out.println("4. Search Student");
            System.out.println("5. Export file");
            System.out.println("6. Import file");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> deleteStudent();
                case 3 -> updateStudent();
                case 4 -> searchStudent();
                case 5 -> exportData();
                case 6 -> importData();
                case 7 -> {
                    saveToCSV();
                    logger.info("Application exited.");
                    System.out.println("Exiting program.");
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }
}