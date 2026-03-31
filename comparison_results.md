# Báo cáo so sánh thời gian chạy Test

Dưới đây là bảng so sánh hiệu suất giữa chạy tuần tự (Sequential) và chạy song song (Parallel/Matrix) dựa trên các lần chạy GitHub Actions.

| Hình thức chạy | Trình duyệt thực thi | Tổng thời gian (Total Duration) | Ghi chú |
| :--- | :--- | :--- | :--- |
| **Tuần tự (Sequential)** | Chrome -> Firefox | ... phút ... giây | Chạy trong cùng 1 job, 1 runner |
| **Song song (Matrix)** | Chrome & Firefox | ... phút ... giây | Chia làm 2 job chạy đồng thời |

### 1. Phân tích kết quả:
- **Thời gian tiết kiệm được**: ...
- **Tỉ lệ cải thiện**: ...%

---
*Vui lòng cung cấp thời gian chạy từ GitHub UI để hoàn thiện báo cáo.*
