import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class StudentManagementFullTest {
    @BeforeEach
    void setUp() {
        StudentManagement.getStudents().clear();
    }

    @Test
    void testAddUpdateDeleteStudent() {
        StudentManagement.processAddStudent("SV001", "John Doe", "01-01-2000", "Male", "Khoa Luật", "Law", "CLC", "123 Street", "john@student.university.edu.vn", "0987654321", "Đang học");
        assertEquals(1, StudentManagement.getStudents().size());
        
        Student student = StudentManagement.getStudents().get(0);
        
        // Test all update cases (1-11)
        StudentManagement.updateStudentField(student, 1, "SV001_NEW");
        assertEquals("SV001_NEW", student.getId());
        StudentManagement.updateStudentField(student, 2, "Jane Doe");
        assertEquals("Jane Doe", student.getName());
        StudentManagement.updateStudentField(student, 3, "02-02-2000");
        assertEquals("02-02-2000", student.getDob());
        StudentManagement.updateStudentField(student, 4, "Female");
        assertEquals("Female", student.getGender());
        StudentManagement.updateStudentField(student, 5, "Khoa CNTT");
        assertEquals("Khoa CNTT", student.getDepartment());
        StudentManagement.updateStudentField(student, 6, "Software Engineering");
        assertEquals("Software Engineering", student.getCourse());
        StudentManagement.updateStudentField(student, 7, "Advanced Program");
        assertEquals("Advanced Program", student.getProgram());
        StudentManagement.updateStudentField(student, 8, "456 Avenue");
        assertEquals("456 Avenue", student.getAddress());
        StudentManagement.updateStudentField(student, 9, "newemail@student.university.edu.vn");
        assertEquals("newemail@student.university.edu.vn", student.getEmail());
        StudentManagement.updateStudentField(student, 10, "0999888777");
        assertEquals("0999888777", student.getPhone());
        StudentManagement.updateStudentField(student, 11, "Tốt nghiệp");
        assertEquals("Tốt nghiệp", student.getStatus());
        
        // Delete Student
        boolean removed = StudentManagement.processDeleteStudent("SV001_NEW");
        assertTrue(removed);
        assertEquals(0, StudentManagement.getStudents().size());
    }

    @Test
    void testSearchStudent() {
        StudentManagement.processAddStudent("SV002", "Alice", "02-02-2001", "Female", "Khoa CNTT", "IT", "CLC", "456 Avenue", "alice@student.university.edu.vn", "0912345678", "Đang học");
        StudentManagement.processAddStudent("SV003", "Bob", "03-03-2002", "Male", "Khoa Tiếng Nhật", "Japanese", "CLC", "789 Boulevard", "bob@student.university.edu.vn", "0938765432", "Đang học");
        
        // Search by MSSV or Name
        List<Student> results = StudentManagement.processSearchStudentByIdOrName("SV003");
        assertEquals(1, results.size());
        
        // Search by Department
        results = StudentManagement.processSearchStudentByDepartment("Khoa CNTT");
        assertEquals(1, results.size());
        
        // Search by Department + Name
        results = StudentManagement.processSearchStudentByDepartmentAndName("Khoa Tiếng Nhật", "Bob");
        assertEquals(1, results.size());
    }

    @Test
    void testExportImport() {
        StudentManagement.processAddStudent("SV003", "Charlie", "03-03-2002", "Other", "Khoa Tiếng Anh", "English", "CLC", "789 Street", "charlie@student.university.edu.vn", "0965432187", "Đang học");
        StudentManagement.processExportData(1);
        StudentManagement.processExportData(2);
        StudentManagement.processImportData("csv", "students.csv");
        StudentManagement.processImportData("json", "students.json");
        assertFalse(StudentManagement.getStudents().isEmpty());
    }

    @Test
    void testValidation() {
        assertTrue(StudentManagement.isValidEmail("valid@student.university.edu.vn"));
        assertFalse(StudentManagement.isValidEmail("invalid@gmail.com"));
        assertTrue(StudentManagement.isValidPhone("0987654321"));
        assertFalse(StudentManagement.isValidPhone("12345"));
    }
}