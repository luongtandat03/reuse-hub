# ReuseHub - Nền tảng Cộng đồng Trao đổi & Mua bán Đồ cũ

**ReuseHub** là một nền tảng thương mại điện tử và mạng xã hội tập trung vào việc trao đổi, mua bán và đấu giá các sản phẩm đã qua sử dụng. Dự án hướng tới việc xây dựng thói quen tiêu dùng bền vững, giúp người dùng dễ dàng thanh lý đồ cũ và tìm kiếm những món đồ giá trị.

Dự án được xây dựng dựa trên kiến trúc **Microservices** hiện đại, đảm bảo khả năng mở rộng, hiệu năng cao và trải nghiệm người dùng mượt mà.

## 🚀 Tính năng nổi bật

* **Quản lý người dùng & KYC:**
    * Đăng ký, đăng nhập bảo mật (JWT).
    * Xác thực danh tính (KYC) với hình ảnh giấy tờ tùy thân để tăng độ tin cậy.
    * Hệ thống phân quyền (User, Admin, Staff).
* **Đăng tin & AI:**
    * Đăng bán hoặc trao đổi sản phẩm với đầy đủ thông tin, hình ảnh.
    * Tích hợp **Google Gemini AI** để tự động tạo thẻ (tags) và gợi ý mô tả sản phẩm dựa trên hình ảnh/tên.
* **Đấu giá trực tuyến:**
    * Tạo phiên đấu giá cho sản phẩm.
    * Tham gia đấu giá theo thời gian thực (Real-time Bidding) sử dụng WebSocket.
* **Trò chuyện (Chat):**
    * Nhắn tin trực tiếp 1-1 giữa người mua và người bán.
    * Hỗ trợ gửi tin nhắn văn bản, hình ảnh và thương lượng giá cả.
* **Giao dịch & Thanh toán:**
    * Quản lý đơn hàng, trạng thái giao dịch.
    * Tích hợp cổng thanh toán trực tuyến (Stripe) và ví nội bộ.
* **Mạng xã hội:**
    * Theo dõi (Follow) người bán yêu thích.
    * Bảng tin (News Feed) hiển thị hoạt động mới.
    * Tương tác: Like, Comment, Đánh giá (Rating) người dùng.
* **Gợi ý thông minh:**
    * Hệ thống Recommendation System sử dụng **Neo4j** để gợi ý sản phẩm phù hợp dựa trên lịch sử xem và tương tác.
* **Bản đồ & Vị trí:**
    * Tìm kiếm sản phẩm xung quanh vị trí người dùng.
    * Quản lý địa chỉ giao hàng.

## 🛠 Công nghệ sử dụng

### Backend (Microservices)
* **Ngôn ngữ:** Java 17+
* **Framework:** Spring Boot, Spring Cloud (Gateway, Eureka Discovery, OpenFeign, Config).
* **Cơ sở dữ liệu:**
    * **PostgreSQL:** Dữ liệu quan hệ chính (Identity, Profile, Item, Transaction, Payment...).
    * **MongoDB:** Lưu trữ lịch sử tin nhắn Chat.
    * **Neo4j:** Graph Database cho hệ thống gợi ý (Recommendation).
    * **Redis:** Caching dữ liệu và quản lý phiên.
* **Message Broker:** RabbitMQ (Xử lý sự kiện bất đồng bộ giữa các services).
* **AI:** Google Gemini API.
* **Storage:** Supabase (Lưu trữ hình ảnh/file).
* **Build Tool:** Maven.

### Frontend
* **Framework:** React (Vite).
* **Ngôn ngữ:** TypeScript.
* **Styling:** Tailwind CSS, Shadcn UI.
* **State Management:** React Context / Hooks.
* **Real-time:** WebSocket (cho tính năng Đấu giá và Chat).
* **Bản đồ:** Tích hợp bản đồ số.

### DevOps & Hạ tầng
* **Containerization:** Docker, Docker Compose.
* **Web Server:** Nginx (Reverse Proxy).

## 📂 Cấu trúc dự án

```text
reuse-hub/
├── reuse-hub-backend/          # Mã nguồn Backend (Java/Spring Boot)
│   ├── admin-service           # Quản lý hệ thống, thống kê, duyệt bài
│   ├── ai-service              # Xử lý logic liên quan đến AI (Gemini)
│   ├── api-gateway             # Cổng giao tiếp API tập trung (Authentication Filter)
│   ├── auction-service         # Xử lý logic và socket đấu giá
│   ├── chat-service            # Dịch vụ nhắn tin (Lưu trữ MongoDB)
│   ├── discovery-service       # Service Registry (Eureka Server)
│   ├── identity-service        # Xác thực, phân quyền (Auth, JWT)
│   ├── item-service            # Quản lý sản phẩm, danh mục, tìm kiếm
│   ├── notification-service    # Gửi email, thông báo đẩy
│   ├── payment-service         # Xử lý thanh toán, ví điện tử
│   ├── profile-service         # Thông tin cá nhân, địa chỉ, KYC
│   ├── recommendation-service  # Logic gợi ý sản phẩm (Graph DB)
│   ├── transaction-service     # Quản lý đơn hàng, quy trình giao dịch
│   ├── resource/db-init        # Script khởi tạo Database SQL
│   └── nginx/                  # Cấu hình Nginx
├── reuse-hub/FE/reuse-hub-frontend/ # Mã nguồn Frontend (React/Vite)
└── docker-compose.yml          # Cấu hình chạy toàn bộ hệ thống
