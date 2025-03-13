# Student Management System

## Giới thiệu

Student Management System là một ứng dụng Java quản lý thông tin sinh viên, hỗ trợ:

- Thêm, xóa, cập nhật thông tin sinh viên.
- Tìm kiếm sinh viên theo MSSV, tên, khoa.
- Xuất và nhập dữ liệu từ file CSV và JSON.
- Xuất giấy xác nhận tình trạng sinh viên dưới dạng **PDF** và **DOCX**.
- Gửi email thông báo khi trạng thái sinh viên thay đổi.
- Kiểm tra và xử lý dữ liệu đầu vào như email, số điện thoại.
- Lưu trữ dữ liệu cục bộ bằng file CSV và JSON.

## Công nghệ sử dụng

- **Ngôn ngữ**: Java
- **Thư viện**:
  - `org.json` (xử lý JSON)
  - `org.apache.poi.xwpf.usermodel` (xuất file Word)
  - `org.apache.pdfbox.pdmodel` (xuất file PDF)
  - `java.io` (đọc/ghi file)
  - `java.util` (quản lý danh sách sinh viên)
  - `java.time` (xử lý thời gian)
  - `java.util.logging` (ghi log hệ thống)

## Yêu cầu hệ thống

- **JDK 11 trở lên** (khuyến nghị JDK 17).
- **File JAR của chương trình (****`StudentManagementRun.jar`****)**.
- **Thư mục chứa font chữ (****`fonts/arial.otf`****, ****`fonts/arial-bold.otf`****)** cùng cấp với file `.jar`.

## Hướng dẫn chạy chương trình

1. **Đảm bảo bạn có JDK 11 trở lên**.

   - Kiểm tra phiên bản Java bằng lệnh:
     ```sh
     java -version
     ```
   - Nếu chưa có, tải và cài đặt JDK từ [https://www.oracle.com/java/technologies/javase-downloads.html](https://www.oracle.com/java/technologies/javase-downloads.html).

2. **Đặt thư mục font chữ đúng vị trí**.

   - Tạo thư mục `fonts/` cùng cấp với file `.jar`.
   - Đặt các file font `arial.otf` và `arial-bold.otf` vào thư mục `fonts/`.

3. **Chạy file ****`.jar`**.

   - Mở **Command Prompt (Windows) hoặc Terminal (Mac/Linux)**.
   - Di chuyển đến thư mục chứa `StudentManagementRun.jar`:
     ```sh
     cd đường_dẫn_tới_thư_mục
     ```
   - Chạy chương trình:
     ```sh
     java -jar StudentManagementRun.jar
     ```

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
- Khi thay đổi trạng thái sinh viên, chương trình sẽ **tự động gửi email thông báo**.

### 4. Tìm kiếm sinh viên

- Tìm theo MSSV hoặc họ tên.
- Tìm theo khoa.
- Tìm theo khoa và họ tên.

### 5. Xuất dữ liệu

- Lưu danh sách sinh viên vào file CSV hoặc JSON.

### 6. Nhập dữ liệu

- Tải danh sách sinh viên từ file CSV hoặc JSON.
- Kiểm tra tránh trùng lặp MSSV.

### 7. Cấu hình hệ thống

- Bật/tắt kiểm tra định dạng email.
- Bật/tắt kiểm tra định dạng số điện thoại.
- Bật/tắt quy tắc thay đổi trạng thái sinh viên.
- Bật/tắt quy tắc xóa sinh viên sau 30 phút.

### 8. Xuất giấy xác nhận sinh viên

- Chọn MSSV để xuất giấy xác nhận.
- Chọn **tình trạng sinh viên**.
- Chọn **mục đích xác nhận** (vay vốn, hoãn nghĩa vụ quân sự, xin việc... hoặc nhập lý do khác).
- Chọn định dạng xuất file: **PDF hoặc DOCX**.

### 9. Thoát chương trình

- Lưu dữ liệu trước khi thoát.

##

