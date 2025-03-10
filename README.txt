# Student Management System

## Giới thiệu

Student Management System là một ứng dụng Java quản lý thông tin sinh viên, hỗ trợ:

- Thêm, xóa, cập nhật thông tin sinh viên.
- Tìm kiếm sinh viên theo MSSV, tên, khoa.
- Xuất và nhập dữ liệu từ file CSV và JSON.
- Kiểm tra và xử lý dữ liệu đầu vào như email, số điện thoại.
- Lưu trữ dữ liệu cục bộ bằng file CSV và JSON.

## Công nghệ sử dụng

- **Ngôn ngữ**: Java
- **Thư viện**:
  - `org.json` (xử lý JSON)
  - `java.io` (đọc/ghi file)
  - `java.util` (quản lý danh sách sinh viên)
  - `java.time` (xử lý thời gian)
  - `java.util.logging` (ghi log hệ thống)

## Yêu cầu hệ thống

- **JDK 11 trở lên** (khuyến nghị JDK 17).
- **Eclipse IDE** hoặc IDE hỗ trợ Java.

## Hướng dẫn cài đặt và chạy project trên Eclipse

### 1. Cài đặt Eclipse và JDK

- Tải Eclipse tại: [https://www.eclipse.org/downloads/](https://www.eclipse.org/downloads/)
- Cài đặt JDK nếu chưa có (có thể tải từ [https://www.oracle.com/java/technologies/javase-jdk11-downloads.html](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)).

### 2. Clone project từ GitHub

1. Mở Terminal hoặc Command Prompt và chạy lệnh:
   ```sh
   git clone https://github.com/xiao-honsu/BTVN-Ver1.0.git
   ```
2. Mở Eclipse và chọn **File** > **Import...**.
3. Chọn **Existing Projects into Workspace** và nhấn **Next**.
4. Chọn thư mục chứa project (`BTVN-Ver1.0`) và nhấn **Finish**.

### 3. Cấu hình thư viện JSON (nếu cần)

1. Trong Eclipse, nhấn chuột phải vào project > **Build Path** > **Configure Build Path**.
2. Chuyển sang tab **Libraries** và chọn **Add External JARs**.
3. Thêm file `json-<version>.jar` (có thể tải từ [https://mvnrepository.com/artifact/org.json/json](https://mvnrepository.com/artifact/org.json/json)).
4. Nhấn **Apply and Close**.

### 4. Chạy chương trình

1. Mở file `StudentManagement.java` trong Eclipse.
2. Nhấn chuột phải và chọn **Run As** > **Java Application**.

## Hướng dẫn sử dụng

Chương trình cung cấp các chức năng sau:

### 1. Thêm sinh viên
- Nhập thông tin sinh viên, bao gồm:
  - MSSV (Mã số sinh viên)
  - Họ tên
  - Ngày sinh (dd-mm-yyyy)
  - Giới tính (Nam, Nữ, Khác)
  - Khoa
  - Khóa học
  - Chương trình đào tạo (Đại trà, Đề án, CLC)
  - Địa chỉ
  - Email (chỉ chấp nhận email trường học hợp lệ)
  - Số điện thoại (định dạng Việt Nam)
  - Trạng thái (Đang học, Bảo lưu, Đã tốt nghiệp, Đã thôi học, Đình chỉ)

### 2. Xóa sinh viên
- Nhập MSSV để xóa sinh viên khỏi danh sách.
- Chỉ có thể xóa sinh viên trong vòng 30 phút sau khi thêm.

### 3. Cập nhật thông tin sinh viên
- Nhập MSSV của sinh viên cần cập nhật.
- Chọn trường thông tin cần chỉnh sửa.
- Cập nhật dữ liệu mới.

### 4. Tìm kiếm sinh viên
- Tìm theo MSSV hoặc họ tên.
- Tìm theo khoa.
- Tìm theo khoa và họ tên.

### 5. Xuất dữ liệu
- Lưu danh sách sinh viên vào file CSV hoặc JSON.

### 6. Nhập dữ liệu
- Tải danh sách sinh viên từ file CSV hoặc JSON.
- Kiểm tra tránh trùng lặp MSSV.

### 7. Thoát chương trình
- Lưu dữ liệu trước khi thoát.

##

