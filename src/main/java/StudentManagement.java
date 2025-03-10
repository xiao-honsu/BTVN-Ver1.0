import java.io.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.*;
import java.util.stream.Collectors;
import java.util.logging.*;
import java.time.format.DateTimeFormatter;
import java.time.Duration;
import java.time.LocalDate;
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
    private LocalDateTime createdDate;

    public Student(String id, String name, String dob, String gender, String department, String course, String program, String address, String email, String phone, String status, LocalDateTime createdDate) {
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
        this.createdDate = createdDate;
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

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    @Override
    public String toString() {
        return "MSSV: " + id + ", Name: " + name + ", DOB: " + dob + ", Gender: " + gender + ", Department: " + department + ", Course: " + course + ", Program: " + program + ", Address: " + address + ", Email: " + email + ", Phone: " + phone + ", Status: " + status;
    }

    public String toCSV() {
        return String.join(",", id, name, dob, gender, department, course, program, address, email, phone, status, createdDate.toString());
    }

    public static Student fromCSV(String line) {
        String[] data = line.split(",");
        LocalDateTime createdDate;
    
        if (data.length > 11) {
            DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME; 
            createdDate = LocalDateTime.parse(data[11], formatter);
        } else {
            createdDate = LocalDateTime.now();
        }
    
        return new Student(data[0], data[1], data[2], data[3], data[4], data[5], data[6], data[7], data[8], data[9], data[10], createdDate);
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
            obj.getString("Email"), obj.getString("Phone"), obj.getString("Status"), LocalDateTime.now()
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
    private static final LocalDateTime BUILD_DATE = LocalDateTime.now();
    private static final List<String> GENDERS = Arrays.asList("Male", "Female", "Other");
    private static final List<String> DEPARTMENTS = Arrays.asList("Khoa Luật", "Khoa Tiếng Anh thương mại", "Khoa Tiếng Nhật", "Khoa Tiếng Pháp", "None");
    private static final List<String> PROGRAMS = Arrays.asList("Đại trà", "Đề án", "CLC", "None");
    private static final List<String> STATUSES = Arrays.asList("Đang học", "Đã tốt nghiệp", "Đã thôi học","Bảo lưu","Đình chỉ", "None");
    
    public static List<Student> getStudents() {
        return students;
    }


    public static void loadFromCSV() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            boolean isFirstLine = true;
            while ((line = br.readLine()) != null) {
                if (isFirstLine) { 
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
            bw.write("MSSV,Name,DOB,Gender,Department,Course,Program,Address,Email,Phone,Status,createDate");
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
                        values[6], values[7], values[8], values[9], values[10], LocalDateTime.now());
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

    private static final String ALLOWED_DOMAIN = "@student.university.edu.vn";

    public static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+$";
        
        if (!email.matches(emailRegex)) {
            return false;
        }

        return email.endsWith(ALLOWED_DOMAIN);
    }


    public static boolean isValidPhone(String phone) {
        String vietnamPhoneRegex = "^(\\+84|0)(3|5|7|8|9)\\d{8}$";
        return phone.matches(vietnamPhoneRegex);
    }
    

    public static void addStudent() {
        System.out.println("\nUniversity of Social Sciences and Humanities - Adding a New Student");
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
        
        System.out.println("Select Program:");
        for (int i = 0; i < PROGRAMS.size(); i++) {
            System.out.println((i + 1) + ". " + PROGRAMS.get(i));
        }
        int programChoice = Integer.parseInt(scanner.nextLine()) - 1;
        String program = PROGRAMS.get(programChoice);
        
        System.out.print("Address: ");
        String address = scanner.nextLine();
        
        String email;
        do {
            System.out.print("Email: ");
            email = scanner.nextLine();
            if (!isValidEmail(email)) {
                System.out.println("Invalid email format. Please try again.");
            }
        } while (!isValidEmail(email));
        
        String phone;
        do {
            System.out.print("Phone: ");
            phone = scanner.nextLine();
            if (!isValidPhone(phone)) {
                System.out.println("Invalid phone number. Please try again.");
            }
        } while (!isValidPhone(phone));
        
        System.out.println("Select Status:");
        for (int i = 0; i < STATUSES.size(); i++) {
            System.out.println((i + 1) + ". " + STATUSES.get(i));
        }
        int statusChoice = Integer.parseInt(scanner.nextLine()) - 1;
        String status = STATUSES.get(statusChoice);
        
        processAddStudent(id, name, dob, gender, department, course, program, address, email, phone, status);
    }


    public static void processAddStudent(String id, String name, String dob, String gender, String department, 
                                          String course, String program, String address, String email, 
                                          String phone, String status) {
        for (Student s : students) {
            if (s.getId().equals(id)) {
                System.out.println("Lỗi: MSSV " + id + " đã tồn tại. Vui lòng nhập MSSV khác.");
                return;
            }
        }
        
        students.add(new Student(id, name, dob, gender, department, course, program, address, email, phone, status, LocalDateTime.now()));
        System.out.println("Student added successfully.");
    }


    public static void deleteStudent() {
        if (students.isEmpty()) {
            System.out.println("No data.");
            return;
        }
        
        System.out.println("\nUniversity of Social Sciences and Humanities - Deleting a Student");
        System.out.print("Enter MSSV of the student to delete: ");
        String id = scanner.nextLine();
        processDeleteStudent(id);
    }

    public static boolean processDeleteStudent(String id) {
        LocalDateTime now = LocalDateTime.now();
        boolean removed = students.removeIf(student ->
            student.getId().equals(id) && 
            Duration.between(student.getCreatedDate(), now).toMinutes() <= 30
        );
    
        if (removed) {
            System.out.println("Student deleted successfully.");
        } else {
            System.out.println("Cannot delete. Student not found or creation time exceeds 30 minutes.");
        }
        return removed;
    }
    

    public static void updateStudent() {
        if (students.isEmpty()) {
            System.out.println("No data.");
            return;
        }
        System.out.println("\nUniversity of Social Sciences and Humanities - Updating a Student");
        System.out.print("Enter MSSV of the student to update: ");
        String id = scanner.nextLine();
        
        for (Student student : students) {
            if (student.getId().equals(id)) {
                boolean updating = true;
                while (updating) {
                    System.out.println("Select the field to update:");
                    System.out.println("1. MSSV\n2. Name\n3. DOB\n4. Gender\n5. Department\n6. Course\n7. Program\n8. Address\n9. Email\n10. Phone\n11. Status\n12. Exit");
                    System.out.print("Choose an option: ");
                    int choice = Integer.parseInt(scanner.nextLine());
                    
                    if (choice == 12) {
                        updating = false;
                        break;
                    }
                    
                    if (choice == 4) {
                        System.out.println("Select new Gender:");
                        for (int i = 0; i < GENDERS.size(); i++) {
                            System.out.println((i + 1) + ". " + GENDERS.get(i));
                        }
                        System.out.print("Choose an option: ");
                        int genderChoice = Integer.parseInt(scanner.nextLine()) - 1;
                        if (genderChoice >= 0 && genderChoice < GENDERS.size()) {
                            updateStudentField(student, choice, GENDERS.get(genderChoice));
                            System.out.println("Gender updated successfully.");
                        } else {
                            System.out.println("Invalid choice.");
                        }
                    } else if (choice == 5) {
                        System.out.println("Select new Department:");
                        for (int i = 0; i < DEPARTMENTS.size(); i++) {
                            System.out.println((i + 1) + ". " + DEPARTMENTS.get(i));
                        }
                        System.out.print("Choose an option: ");
                        int deptChoice = Integer.parseInt(scanner.nextLine()) - 1;
                        if (deptChoice >= 0 && deptChoice < DEPARTMENTS.size()) {
                            updateStudentField(student, choice, DEPARTMENTS.get(deptChoice));
                            System.out.println("Department updated successfully.");
                        } else {
                            System.out.println("Invalid choice.");
                        }
                    } else if (choice == 7) {
                        System.out.println("Select new Program:");
                        for (int i = 0; i < PROGRAMS.size(); i++) {
                            System.out.println((i + 1) + ". " + PROGRAMS.get(i));
                        }
                        System.out.print("Choose an option: ");
                        int programChoice = Integer.parseInt(scanner.nextLine()) - 1;
                        if (programChoice >= 0 && programChoice < PROGRAMS.size()) {
                            updateStudentField(student, choice, PROGRAMS.get(programChoice));
                            System.out.println("Program updated successfully.");
                        } else {
                            System.out.println("Invalid choice.");
                        }
                    } else if (choice == 11) {
                        List<String> validStatuses = getValidStatusOptions(student.getStatus());
                        if (validStatuses.isEmpty()) {
                            System.out.println("Status cannot be changed from '" + student.getStatus() + "'.");
                            continue;
                        }
                        System.out.println("Select new Status:");
                        for (int i = 0; i < validStatuses.size(); i++) {
                            System.out.println((i + 1) + ". " + validStatuses.get(i));
                        }
                        System.out.print("Choose an option: ");
                        int statusChoice = Integer.parseInt(scanner.nextLine()) - 1;
                        if (statusChoice >= 0 && statusChoice < validStatuses.size()) {
                            updateStudentField(student, choice, validStatuses.get(statusChoice));
                            System.out.println("Status updated successfully.");
                        } else {
                            System.out.println("Invalid choice.");
                        }
                    } else {
                        System.out.print("Enter new value: ");
                        String newValue = scanner.nextLine();
                        updateStudentField(student, choice, newValue);
                    }
                }
                return;
            }
        }
        System.out.println("Student not found.");
    }


    public static void updateStudentField(Student student, int field, String newValue) {
        switch (field) {
            case 1 -> {
                if (!newValue.equals(student.getId()) && students.stream().noneMatch(s -> s.getId().equals(newValue))) {
                    student.setId(newValue);
                    System.out.println("MSSV updated successfully.");
                } else {
                    System.out.println("Lỗi: MSSV đã tồn tại hoặc không thay đổi.");
                }
            }
            case 2 -> student.setName(newValue);
            case 3 -> student.setDob(newValue);
            case 4 -> student.setGender(newValue);
            case 5 -> student.setDepartment(newValue);
            case 6 -> student.setCourse(newValue);
            case 7 -> student.setProgram(newValue);
            case 8 -> student.setAddress(newValue);
            case 9 -> {
                if (isValidEmail(newValue)) {
                    student.setEmail(newValue);
                } else {
                    System.out.println("Invalid email format.");
                }
            }
            case 10 -> {
                if (isValidPhone(newValue)) {
                    student.setPhone(newValue);
                } else {
                    System.out.println("Invalid phone number.");
                }
            }
            case 11 -> student.setStatus(newValue);
            default -> System.out.println("Invalid option.");
        }
    }
    
    
    private static List<String> getValidStatusOptions(String currentStatus) {
        List<String> validOptions = new ArrayList<>();
        
        if (currentStatus.equals("None")) {
            validOptions.add("Đang học");
            validOptions.add("Bảo lưu");
            validOptions.add("Đã tốt nghiệp");
            validOptions.add("Đình chỉ");
            validOptions.add("Đã thôi học");
        } else {
            switch (currentStatus) {
                case "Đang học":
                    validOptions.add("Bảo lưu");
                    validOptions.add("Đã tốt nghiệp");
                    validOptions.add("Đình chỉ");
                    validOptions.add("Đã thôi học");
                    break;
                case "Bảo lưu":
                    validOptions.add("Đang học");
                    break;
                case "Đã tốt nghiệp":
                case "Đình chỉ":
                case "Đã thôi học":
                    validOptions.add("None"); 
                    break;
            }
        }
    
        return validOptions;
    }
    
    
    

    public static void searchStudent() {
        if (students.isEmpty()) {
            System.out.println("No data.");
            return;
        }
        System.out.println("\nUniversity of Social Sciences and Humanities - Searching a Student");

        System.out.println("Search options:");
        System.out.println("1. Search by MSSV or Name");
        System.out.println("2. Search by Department");
        System.out.println("3. Search by Department + Name");
        System.out.print("Choose an option: ");
        int choice = Integer.parseInt(scanner.nextLine());

        List<Student> results = null;

        switch (choice) {
            case 1 -> {
                System.out.print("Enter Name or MSSV to search: ");
                String query = scanner.nextLine().toLowerCase();
                results = processSearchStudentByIdOrName(query);
            }
            case 2 -> {
                String department = selectDepartment();
                if (department != null) {
                    results = processSearchStudentByDepartment(department);
                }
            }
            case 3 -> {
                String department = selectDepartment();
                if (department != null) {
                    System.out.print("Enter Name to search: ");
                    String nameQuery = scanner.nextLine().toLowerCase();
                    results = processSearchStudentByDepartmentAndName(department, nameQuery);
                }
            }
            default -> System.out.println("Invalid option.");
        }

        if (results != null && !results.isEmpty()) {
            System.out.println("\nSearch Results:");
            results.forEach(System.out::println);
        } else {
            System.out.println("No matching students found.");
        }
    }



    public static List<Student> processSearchStudentByIdOrName(String query) {
        return students.stream()
                .filter(s -> s.getId().toLowerCase().contains(query) || s.getName().toLowerCase().contains(query))
                .collect(Collectors.toList());
    }

    public static List<Student> processSearchStudentByDepartment(String department) {
        return students.stream()
                .filter(s -> s.getDepartment().equals(department))
                .collect(Collectors.toList());
    }

    public static List<Student> processSearchStudentByDepartmentAndName(String department, String nameQuery) {
        return students.stream()
                .filter(s -> s.getDepartment().equals(department) && s.getName().toLowerCase().contains(nameQuery))
                .collect(Collectors.toList());
    }



    public static String selectDepartment() {
        System.out.println("Select Department:");
        for (int i = 0; i < DEPARTMENTS.size(); i++) {
            System.out.println((i + 1) + ". " + DEPARTMENTS.get(i));
        }
        System.out.print("Choose an option: ");
        int departmentChoice = Integer.parseInt(scanner.nextLine()) - 1;
        if (departmentChoice >= 0 && departmentChoice < DEPARTMENTS.size()) {
            return DEPARTMENTS.get(departmentChoice);
        } else {
            System.out.println("Invalid choice.");
            return null;
        }
    }
    

    public static void exportData() {
        System.out.println("\nUniversity of Social Sciences and Humanities - Exporting data");
        System.out.println("Choose export format: 1. CSV  2. JSON");
        int choice = Integer.parseInt(scanner.nextLine());
        
        processExportData(choice);
    }

 
    public static void processExportData(int choice) {
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
        System.out.println("\nUniversity of Social Sciences and Humanities - Importing data");

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

        processImportData(fileType, filePath);
    }

    public static void processImportData(String fileType, String filePath) {
        try {
            if (fileType.equals("json")) {
                importFromJSON(filePath);
            } else if (fileType.equals("csv")) {
                importFromCSV(filePath);
            }
            System.out.println("Data imported successfully.");
        } catch (Exception e) {
            logger.severe("Error importing data: " + e.getMessage());
    }
}


    
    
    
    public static void main(String[] args) {
        logger.info("Application started.");
        showVersion();
        loadFromCSV();
        while (true) {
            System.out.println("\n==============================");
            System.out.println("  University of Social Sciences and Humanities Management System  ");
            System.out.println("==============================");
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