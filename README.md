# ReuseHub - Nền tảng Cộng đồng Trao đổi & Mua bán Đồ cũ

**ReuseHub** là một nền tảng thương mại điện tử kết hợp mạng xã hội, tập trung vào việc trao đổi, mua bán và đấu giá các sản phẩm đã qua sử dụng. Dự án hướng tới việc xây dựng thói quen tiêu dùng bền vững, giúp người dùng dễ dàng thanh lý đồ cũ và tìm kiếm những món đồ giá trị với chi phí hợp lý.

Hệ thống được xây dựng trên kiến trúc **Microservices** hiện đại, đảm bảo khả năng mở rộng, hiệu năng cao và dễ dàng bảo trì.

---

## 📑 Mục lục

- [Tính năng nổi bật](#-tính-năng-nổi-bật)
- [Kiến trúc hệ thống](#-kiến-trúc-hệ-thống)
- [Công nghệ sử dụng](#-công-nghệ-sử-dụng)
- [Cài đặt và chạy dự án](#%EF%B8%8F-cài-đặt-và-chạy-dự-án)
  - [Yêu cầu](#yêu-cầu)
  - [Chạy bằng Docker (Khuyên dùng)](#cách-1-chạy-bằng-docker-khuyên-dùng)
  - [Chạy môi trường phát triển (Local)](#cách-2-chạy-môi-trường-phát-triển-local)
- [Cấu hình biến môi trường](#-cấu-hình-biến-môi-trường)
- [API Documentation](#-api-documentation)
- [Đóng góp](#-đóng-góp)

---

## 🚀 Tính năng nổi bật

### 1. Quản lý người dùng & Bảo mật
* **Đăng ký/Đăng nhập:** Hỗ trợ xác thực qua Email, Google (OAuth2).
* **KYC (Know Your Customer):** Xác minh danh tính người dùng qua CMND/CCCD để tăng độ uy tín cho tài khoản bán hàng.
* **Phân quyền:** Hệ thống Role-based (User, Staff, Admin).

### 2. Thương mại điện tử
* **Đăng tin:** Đăng bán hoặc trao đổi sản phẩm với hình ảnh, mô tả chi tiết.
* **Gợi ý thông minh (AI):** Tích hợp **Google Gemini AI** để tự động gắn thẻ (tags) và gợi ý danh mục dựa trên hình ảnh/tiêu đề sản phẩm.
* **Tìm kiếm & Lọc:** Tìm kiếm sản phẩm theo từ khóa, danh mục, khoảng giá, vị trí địa lý.
* **Giao dịch:** Quy trình mua bán an toàn, tích hợp thanh toán trực tuyến (Stripe) và ví nội bộ.

### 3. Đấu giá (Auction)
* **Tạo phiên đấu giá:** Người bán có thể tạo các phiên đấu giá với giá khởi điểm và thời gian cụ thể.
* **Real-time Bidding:** Người mua tham gia đặt giá thầu theo thời gian thực (sử dụng WebSocket).

### 4. Mạng xã hội & Tương tác
* **Chat:** Nhắn tin trực tiếp (1-1) giữa người mua và bán, hỗ trợ gửi ảnh, thương lượng giá.
* **News Feed:** Bảng tin hiển thị các hoạt động mới từ người dùng bạn theo dõi.
* **Tương tác:** Like, Comment, Follow người dùng khác.
* **Đánh giá (Rating):** Hệ thống đánh giá tín nhiệm sau mỗi giao dịch.

### 5. Hệ thống gợi ý (Recommendation)
* Sử dụng **Neo4j (Graph Database)** để phân tích hành vi người dùng và gợi ý các sản phẩm phù hợp nhất.

---

## 📂 Kiến trúc hệ thống

Dự án bao gồm các Microservices chính sau:

| Service | Chức năng | Port (Mặc định) |
| :--- | :--- | :--- |
| **Discovery Service** | Eureka Server - Quản lý đăng ký dịch vụ | `8761` |
| **API Gateway** | Cổng giao tiếp tập trung, Routing, Auth Filter | `8888` |
| **Identity Service** | Quản lý User, Auth, Token, Phân quyền | `8080` |
| **Profile Service** | Thông tin cá nhân, KYC, Địa chỉ, Follow | `8081` |
| **Item Service** | Quản lý sản phẩm, Danh mục, Tìm kiếm | `8082` |
| **Auction Service** | Quản lý đấu giá, Socket đấu giá | `8083` |
| **Chat Service** | Nhắn tin Real-time (lưu trữ MongoDB) | `8084` |
| **Notification Service** | Gửi Email, Thông báo đẩy | `8085` |
| **Transaction Service** | Quản lý đơn hàng, Quy trình giao dịch | `8086` |
| **Payment Service** | Cổng thanh toán, Ví điện tử | `8087` |
| **Recommendation** | Gợi ý sản phẩm (Neo4j) | `8088` |
| **Admin Service** | Dashboard, Thống kê, Quản lý chung | `8089` ||

---

## 🛠 Công nghệ sử dụng

### Backend
* **Ngôn ngữ:** Java 17
* **Framework:** Spring Boot 3.x, Spring Cloud (Gateway, Eureka, OpenFeign).
* **Message Broker:** RabbitMQ (Xử lý sự kiện bất đồng bộ).
* **AI Integration:** Google Gemini API.
* **Storage:** Supabase (Lưu trữ hình ảnh/file).

### Database
* **PostgreSQL:** Dữ liệu quan hệ chính (Identity, Profile, Item, Transaction...).
* **MongoDB:** Lưu trữ lịch sử tin nhắn Chat.
* **Neo4j:** Graph Database cho hệ thống Recommendation.
* **Redis:** Caching dữ liệu và quản lý phiên làm việc.

### Frontend
* **Framework:** React (Vite) + TypeScript.
* **Styling:** Tailwind CSS, Shadcn UI.
* **State Management:** React Context / Hooks.
* **Real-time:** WebSocket.

### DevOps
* **Docker & Docker Compose:** Đóng gói và triển khai ứng dụng.
* **Nginx:** Reverse Proxy & Load Balancer.

---

## ⚙️ Cài đặt và Chạy dự án

### Yêu cầu
* **Docker Desktop** (Đã cài đặt và đang chạy).
* **Java JDK 17+** (Nếu chạy source code backend).
* **Node.js 18+** (Nếu chạy source code frontend).
* **Git**.

### Cách 1: Chạy bằng Docker (Khuyên dùng)

Đây là cách nhanh nhất để dựng toàn bộ hệ thống bao gồm cả Backend, Frontend và Database.

1.  **Clone dự án:**
    ```bash
    git clone [https://github.com/luongtandat03/reuse-hub.git](https://github.com/luongtandat03/reuse-hub.git)
    cd reuse-hub
    ```

2.  **Cấu hình môi trường (Quan trọng):**
    * Bạn cần cập nhật các API Key (Google, Stripe, Supabase, Mail Server) trong file `docker-compose.yml` hoặc các file `application.yml` của từng service nếu chưa có file `.env`.

3.  **Khởi chạy hệ thống:**
    Tại thư mục gốc (nơi có file `docker-compose.yml`):
    ```bash
    docker-compose up -d --build
    ```
    *Lưu ý: Lần chạy đầu tiên sẽ mất khoảng 10-15 phút để tải Docker Images và Build source code.*

4.  **Kiểm tra trạng thái:**
    * Eureka Dashboard: `http://localhost:8761` (Kiểm tra các service đã online chưa).
    * Frontend: `http://localhost:3000`.
    * API Gateway: `http://localhost:8888`.

### Cách 2: Chạy môi trường phát triển (Local)

Dùng khi bạn muốn phát triển/chỉnh sửa code.

1.  **Chạy hạ tầng (Databases & Broker):**
    Chỉ chạy các container database bằng Docker:
    ```bash
    docker-compose up -d postgres mongo redis neo4j rabbitmq discovery-service
    ```

2.  **Chạy Backend Services:**
    Mở project `reuse-hub-backend` trong IntelliJ/Eclipse.
    * Chạy **DiscoveryServiceApplication** (Bắt buộc chạy đầu tiên).
    * Chạy **ApiGatewayApplication**.
    * Chạy các Service khác tùy theo chức năng bạn đang phát triển.

3.  **Chạy Frontend:**
    ```bash
    cd reuse-hub/FE/reuse-hub-frontend
    npm install
    npm run dev
    ```
    Frontend sẽ chạy tại `http://localhost:5173` (hoặc port được hiển thị).

---

## 🔑 Cấu hình biến môi trường

Để hệ thống hoạt động đầy đủ tính năng, bạn cần cung cấp các biến môi trường sau (thường nằm trong `application.yml` hoặc biến môi trường Docker):

* `GEMINI_API_KEY`: Key API của Google Gemini (cho AI Service).
* `SUPABASE_URL` & `SUPABASE_KEY`: Để upload ảnh.
* `STRIPE_SECRET_KEY`: Để thanh toán Online.
* `MAIL_USERNAME` & `MAIL_PASSWORD`: Để gửi email xác thực/thông báo.

---

## 📖 API Documentation

Hệ thống sử dụng **OpenAPI (Swagger)** để tài liệu hóa API. Sau khi chạy backend, bạn có thể truy cập Swagger UI của từng service (thông qua Gateway hoặc trực tiếp):

* URL mẫu (qua Gateway): `http://localhost:8888/swagger-ui.html` (cần cấu hình gộp Swagger)
* URL trực tiếp từng service: `http://localhost:<PORT>/swagger-ui/index.html` (Ví dụ: Identity Service là `http://localhost:8080/swagger-ui/index.html`).

---

## 🤝 Đóng góp

Chúng tôi rất hoan nghênh mọi đóng góp từ cộng đồng!

1.  Fork dự án.
2.  Tạo branch tính năng mới (`git checkout -b feature/TenTinhNang`).
3.  Commit thay đổi (`git commit -m 'Thêm tính năng XYZ'`).
4.  Push lên branch (`git push origin feature/TenTinhNang`).
5.  Tạo Pull Request trên GitHub.

---

## 📞 Liên hệ

Nếu bạn gặp vấn đề trong quá trình cài đặt hoặc sử dụng, vui lòng liên hệ đội ngũ phát triển:

* **Email:** luongtandat512@gmail.com
* **GitHub Issues:** [Tạo Issue mới](https://github.com/luongtandat03/reuse-hub/issues)

**Cảm ơn bạn đã quan tâm đến ReuseHub!** 🌍♻️
