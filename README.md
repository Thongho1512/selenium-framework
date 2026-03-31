# Selenium Automation Framework

## Giới thiệu
Đây là một framework kiểm thử tự động (Automation Testing Framework) nâng cao được xây dựng bằng ngôn ngữ **Java**, sử dụng **Selenium WebDriver**, **TestNG** và mô hình **Page Object Model (POM)**. Framework giúp tối ưu hóa việc viết script, dễ dàng bảo trì và tích hợp vào quy trình CI/CD.

## Các tính năng chính
- **Mô hình Page Object Model (POM)**: Tách biệt logic của trang web và logic của các bài kiểm thử.
- **Quản lý Driver nâng cao**: Sử dụng `WebDriverManager` để tự động tải và quản lý các file thực thi của trình duyệt (Chrome, Firefox, v.v.).
- **Đọc dữ liệu từ nhiều nguồn (DDT)**: Hỗ trợ đọc dữ liệu kiểm thử từ các file Excel (.xlsx) thông qua Apache POI và file JSON (.json) thông qua Jackson.
- **Cấu hình đa môi trường**: Dễ dàng chuyển đổi giữa các môi trường (Dev, Staging, Production) thông qua các file `.properties`.
- **Cơ chế Retry**: Tự động chạy lại các test case bị lỗi (Flaky tests) với Custom Retry Analyzer.
- **Chạy song song (Parallel execution)**: Tích hợp khả năng chạy kiểm thử song song để giảm thời gian thực hiện.
- **Tùy biến Wait**: Sử dụng kết hợp Implicit và Explicit Wait để xử lý các phần tử web linh hoạt.

## Công nghệ sử dụng
- **Ngôn ngữ**: Java 17
- **Quản lý dự án**: Maven
- **Thư viện chính**:
  - Selenium WebDriver 4.x
  - TestNG 7.9.x
  - WebDriverManager
  - Apache POI (Excel handling)
  - Jackson Databind (JSON handling)
  - Java Faker (TestData generation)

## Cấu trúc thư mục
```text
SeleniumFramework/
├── src/
│   ├── main/java/framework/
│   │   ├── base/           # Lớp cơ sở (BaseTest, BasePage, DriverFactory)
│   │   ├── config/         # Đọc cấu hình từ file properties
│   │   ├── pages/          # Các lớp Page Object (LoginPage, ProductsPage,...)
│   │   └── utils/          # Các công cụ hỗ trợ (Excel, JSON, Screenshots, Retry)
│   ├── test/java/tests/    # Các lớp Test Case kế thừa từ BaseTest
│   └── test/resources/     # Chứa config-env.properties và dữ liệu kiểm thử
├── target/                 # Chứa các file build và báo cáo (sau khi chạy test)
├── pom.xml                 # Cấu hình Maven dependencies
└── testng.xml              # File điều phối chạy test của TestNG
```

## Hướng dẫn cài đặt và chạy máy local

### 1. Yêu cầu hệ thống
- **Java JDK**: Phiên bản 17 trở lên.
- **Maven**: Phiên bản 3.6 trở lên.
- **IDE**: IntelliJ IDEA, Eclipse hoặc VS Code (có cài Java extension).
- **Trình duyệt**: Google Chrome (mặc định) hoặc Firefox/Edge.

### 2. Cài đặt Project
Mở Terminal/Command Prompt và chạy lệnh:
```bash
mvn clean install
```

### 3. Chạy kiểm thử (Execution)

#### Chạy tất cả các test case (Mặc định môi trường Dev):
```bash
mvn test
```

#### Chạy với môi trường cụ thể (Ví dụ: Staging):
```bash
mvn test -Denv=staging
```

#### Chạy thông qua file `testng.xml`:
Bạn có thể chuột phải vào file `testng.xml` trong IDE và chọn **Run '...testng.xml'**.

## Liên hệ
Nếu có bất kỳ câu hỏi nào, vui lòng liên hệ với đội ngũ phát triển dự án.
