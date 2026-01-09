# Chat Application

A real-time chat application built with Spring Boot and WebSocket technology, enabling instant messaging between multiple users.

## Features

- 💬 Real-time messaging using WebSocket
- 👥 Multi-user support
- 🚀 Simple and intuitive user interface
- ⚡ Instant message delivery
- 📱 Responsive web design

## Technologies Used

- **Spring Boot** - Backend framework
- **Spring WebSocket** - Real-time bidirectional communication
- **STOMP** - Simple Text Oriented Messaging Protocol
- **SockJS** - WebSocket fallback support
- **Maven** - Dependency management
- **HTML/CSS/JavaScript** - Frontend

## Project Structure

```
src/
├── main/
│   ├── java/com/chat/app/
│   │   ├── AppApplication.java          # Main application class
│   │   ├── config/
│   │   │   └── WebSocketConfig.java     # WebSocket configuration
│   │   ├── controller/
│   │   │   └── ChatController.java      # Message handling controller
│   │   └── model/
│   │       └── ChatMessage.java         # Chat message model
│   └── resources/
│       ├── application.properties       # Application configuration
│       └── templates/
│           └── chat.html                # Chat interface
└── test/
    └── java/com/chat/app/
        └── AppApplicationTests.java     # Unit tests
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

1. Open the application in your web browser
2. Enter your username to join the chat
3. Start sending messages
4. Open multiple browser tabs/windows to simulate multiple users
5. Messages will be delivered in real-time to all connected users

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
