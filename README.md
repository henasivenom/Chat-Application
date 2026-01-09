# Chat Application

A production-ready, feature-rich real-time chat application built with Spring Boot and WebSocket technology. This application demonstrates enterprise-level architecture with modern UI/UX, comprehensive error handling, message persistence, and real-time features.

## ✨ Features

### Real-Time Communication
- 💬 **Instant Messaging** - Real-time message delivery using WebSocket and STOMP
- 👥 **Multi-User Support** - Unlimited concurrent users with session management
- 🔔 **Join/Leave Notifications** - System messages when users connect/disconnect
- ⌨️ **Typing Indicators** - See when other users are typing with animated dots
- 🔄 **Auto-Reconnection** - Automatic reconnection on connection loss

### User Experience
- 🎨 **Color-Coded Users** - Each user gets a unique color for easy identification
- 😀 **Emoji Support** - Built-in emoji picker with popular emojis
- ⏰ **Message Timestamps** - Every message shows when it was sent
- 📜 **Message History** - Loads last 50 messages on join
- 👤 **Username Persistence** - Remembers your username using localStorage
- 🔊 **Sound Notifications** - Audio alert for new messages (respects user preferences)
- 📊 **Character Counter** - Real-time character count with visual warnings (500 char limit)

### UI/UX Design
- 🎨 **Modern Gradient Design** - Beautiful purple gradient theme
- ✨ **Smooth Animations** - Slide-in animations for messages, pulse for connection status
- 📱 **Fully Responsive** - Works perfectly on desktop, tablet, and mobile
- 👁️ **Active Users Sidebar** - See who's online in real-time
- 🎯 **Intuitive Interface** - Clean, distraction-free chat experience
- ⌨️ **Enter Key Support** - Press Enter to send messages
- 🌐 **Connection Status** - Visual indicator showing online/offline state

### Backend Features
- 💾 **Message Persistence** - All messages stored in database
- 🔐 **Input Validation** - Server-side validation for all inputs
- 🛡️ **XSS Protection** - HTML sanitization to prevent attacks
- 📝 **Comprehensive Logging** - Detailed logging with SLF4J
- 🏗️ **Clean Architecture** - Service layer, DTOs, repositories
- ⚠️ **Error Handling** - Global exception handler with user feedback
- 🧪 **Unit Tests** - Full test coverage for services and controllers

### Developer Features
- 🗄️ **H2 Database** - In-memory database for development (easily switchable)
- 📊 **JPA/Hibernate** - ORM for database operations
- 🔍 **H2 Console** - Database browser at `/h2-console`
- 📋 **REST API** - RESTful endpoints for message history and user list
- 🔧 **Hot Reload** - DevTools support for faster development

## Technologies Used

### Backend
- **Spring Boot 4.0.1** - Application framework
- **Spring WebSocket** - Real-time bidirectional communication
- **Spring Data JPA** - Data access layer
- **STOMP** - Simple Text Oriented Messaging Protocol
- **SockJS** - WebSocket fallback support
- **H2 Database** - In-memory database
- **Hibernate** - ORM framework
- **Lombok** - Reduce boilerplate code
- **SLF4J** - Logging framework
- **Maven** - Dependency management
- **Java 17** - Programming language

### Frontend
- **HTML5** - Markup
- **CSS3** - Styling with gradients, animations
- **JavaScript (ES6+)** - Client-side logic
- **Bootstrap 5** - UI framework
- **Font Awesome 6** - Icons
- **SockJS Client** - WebSocket client library
- **STOMP.js** - STOMP over WebSocket

## Project Structure

```
src/
├── main/
│   ├── java/com/chat/app/
│   │   ├── AppApplication.java              # Main application class
│   │   ├── config/
│   │   │   ├── WebSocketConfig.java         # WebSocket & STOMP configuration
│   │   │   └── WebSocketEventListener.java  # Connection lifecycle events
│   │   ├── controller/
│   │   │   └── ChatController.java          # WebSocket & REST endpoints
│   │   ├── dto/
│   │   │   ├── ChatMessageDTO.java          # Message data transfer object
│   │   │   └── UserDTO.java                 # User data transfer object
│   │   ├── entity/
│   │   │   └── MessageEntity.java           # JPA entity for messages
│   │   ├── exception/
│   │   │   └── GlobalExceptionHandler.java  # Global error handling
│   │   ├── model/
│   │   │   ├── ChatMessage.java             # Message model
│   │   │   └── MessageType.java             # Message type enum
│   │   ├── repository/
│   │   │   └── MessageRepository.java       # JPA repository
│   │   └── service/
│   │       └── ChatService.java             # Business logic layer
│   └── resources/
│       ├── application.properties           # Application configuration
│       ├── templates/
│       │   └── chat.html                    # Modern chat interface
│       └── static/                          # Static resources
└── test/
    └── java/com/chat/app/
        ├── AppApplicationTests.java         # Integration tests
        ├── controller/
        │   └── ChatControllerTest.java      # Controller unit tests
        └── service/
            └── ChatServiceTest.java         # Service unit tests
```

## Prerequisites

- Java 17 or higher
- Maven 3.6 or higher
- A modern web browser

## Installation & Setup

1. **Clone the repository**
   ```bash
   git clone https://github.com/henasivenom/Chat-Application.git
   cd Chat-Application
   ```

2. **Build the project**
   ```bash
   mvn clean install
   ```

3. **Run the application**
   ```bash
   mvn spring-boot:run
   ```
   
   Or run the generated JAR file:
   ```bash
   java -jar target/app-0.0.1-SNAPSHOT.jar
   ```

4. **Access the application**
   
   Open your browser and navigate to:
   ```
   http://localhost:8080
   ```

## Usage

### Starting a Chat Session

1. **Open the application** in your web browser at `http://localhost:8080/chat`
2. **Enter your username** (it will be saved for future sessions)
3. Click **"Join Chat"** to enter the chat room
4. Start sending messages!

### Features You Can Try
can be customized via `src/main/resources/application.properties`:

```properties
# Server Configuration
server.port=8080

# Database Configuration
spring.datasource.url=jdbc:h2:mem:chatdb
spring.datasource.username=sa
spring.datasource.password=

# JPA Settings
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# H2 Console (Development)
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
# Run all tests
mvn test

# Run tests with coverage
mvn clean test jacoco:report

# Run specific test
mvn test -Dtest=ChatServiceTest
```Architecture Highlights

### Design Patterns Used
- **MVC Pattern** - Separation of concerns
- **DTO Pattern** - Data transfer between layers
- **Repository Pattern** - Data access abstraction
- **Builder Pattern** - Object construction
- **Event-Driven Architecture** - WebSocket events
- **Dependency Injection** - Loose coupling

### Key Architectural Decisions
- **Service Layer** - Business logic separated from controllers
- **Exception Handling** - Global error handling with `@ControllerAdvice`
- **Validation** - Multi-layer validation (client, server, database)
- **Logging** - Comprehensive logging at all levels
- **Testing** - Unit tests with Mockito for isolation

## Performance Considerations

- **Concurrent Collections** - Thread-safe user and message management
- **Message History Limit** - In-memory cache limited to 100 messages
- **Database Indexing** - Optimized queries for recent messages
- **Efficient DOM Updates** - Minimal client-side DOM manipulation
- **CSS Animations** - Hardware-accelerated transitions

## Security Features

- **XSS Prevention** - HTML entity encoding
- **Input Validation** - Length and content validation
- **CORS Configuration** - Controlled cross-origin access
- **Session Management** - Secure WebSocket sessions
- **Error Handling** - No sensitive data in error messages

## Troubleshooting

### Port Already in Use
```bash
# Find process using port 8080
netstat -ano | findstr :8080

# Kill the process (Windows)
taskkill /PID <process_id> /F
```

### WebSocket Connection Failed
- Check firewall settings
- Verify SockJS fallback is working
- Check browser console for errors

### Messages Not Persisting
- Verify H2 database is running
- Check `application.properties` for database config
- View logs for JPA errors

## Future Enhancements

- [ ] User authentication with Spring Security
- [ ] Private messaging between users
- [ ] Chat rooms/channels
- [ ] File and image sharing
- [ ] Message editing and deletion
- [ ] Read receipts
- [ ] User status (online, away, busy)
- [ ] Message search functionality
- [ ] Markdown support
- [ ] Code syntax highlighting
- [ ] Push notifications
- [ ] Rate limiting
- [ ] Admin panel

## Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

### Code Style
- Follow Java naming conventions
- Add comments for complex logic
- Write unit tests for new features
- Update documentation as needed

## License

This project is open source and available under the [MIT License](LICENSE).

## Author

Created by [henasivenom](https://github.com/henasivenom)

## Acknowledgments

- Spring Framework team for excellent WebSocket support
- SockJS and STOMP.js for reliable real-time communication
- Bootstrap team for the UI framework
- Font Awesome for the icon library
- H2 Database for easy development

## Support

For issues, questions, or contributions:
- Open an issue in the [GitHub repository](https://github.com/henasivenom/Chat-Application/issues)
- Check [IMPLEMENTATION.md](IMPLEMENTATION.md) for detailed implementation notes

## Screenshots

*Open `http://localhost:8080/chat` to see the beautiful, modern chat interface with:*
- Gradient purple theme
- Real-time messaging
- Active users sidebar
- Typing indicators
- Message timestamps
- And much more!

---

⭐ **Star this repository if you find it helpful!**

📚 For detailed implementation notes, see [IMPLEMENTATION.md](IMPLEMENTATION.md)
During development, access the database console at:
- URL: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:chatdb`
- Username: `sa`
- Password: (leave empty)

### Debugging

Enable debug logging:
```properties
logging.level.com.chat.app=DEBUG
logging.level.org.springframework.messaging=DEBUG
```

### Hot Reload

For faster development, add Spring Boot DevTools (already included):
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId>
    <scope>runtime</scope>
</dependency>
## API Endpoints

### WebSocket Endpoints

**Connection:**
- `WS /chat` - WebSocket connection endpoint (with SockJS fallback)

**Messaging:**
- `SEND /app/sendMessage` - Send a chat message
- `SEND /app/addUser` - Register a new user
- `SEND /app/typing` - Notify typing status

**Subscriptions:**
- `SUB /topic/messages` - Receive all chat messages
- `SUB /topic/users` - Receive user list updates
- `SUB /topic/typing` - Receive typing notifications
- `SUB /user/queue/errors` - Receive personal error messages

### REST Endpoints

- `GET /chat` - Chat application UI
- `GET /api/messages/recent` - Get recent message history (JSON)
- `GET /api/users` - Get active users list (JSON)
- `GET /h2-console` - Database console (development only)
3. Send messages and watch them appear in real-time across all windows
4. Notice how each user has a **unique color**
5. Close a window to see the **leave notification**

## Configuration

The application uses default Spring Boot configuration. You can customize settings in `src/main/resources/application.properties`:

```properties
# Server port (default: 8080)
server.port=8080
```

## WebSocket Endpoints

- **Connect**: `/ws` - WebSocket connection endpoint
- **Subscribe**: `/topic/public` - Topic for receiving messages
- **Send**: `/app/chat.sendMessage` - Endpoint for sending messages
- **Add User**: `/app/chat.addUser` - Endpoint for adding new users

## Development

### Running Tests
```bash
mvn test
```

### Building for Production
```bash
mvn clean package
```

## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## License

This project is open source and available under the [MIT License](LICENSE).

## Author

Created by [henasivenom](https://github.com/henasivenom)

## Acknowledgments

- Spring Framework team for excellent WebSocket support
- SockJS and STOMP.js libraries

## Support

For issues, questions, or contributions, please open an issue in the GitHub repository.

---

⭐ Star this repository if you find it helpful!
