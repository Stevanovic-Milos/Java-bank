# Java Bank 💳🏦💰

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white)
![GUI](https://img.shields.io/badge/GUI-Swing-blue?style=for-the-badge)
![Security](https://img.shields.io/badge/Security-Enabled-green?style=for-the-badge)
![Database](https://img.shields.io/badge/Database-SQL-important?style=for-the-badge)
![Transactions](https://img.shields.io/badge/Transactions-Secure-blue?style=for-the-badge)
![Encryption](https://img.shields.io/badge/Encryption-AES256-critical?style=for-the-badge)
![Multi-User](https://img.shields.io/badge/Multi--User-Support-orange?style=for-the-badge)

Java Bank is a personal banking application built in Java with a graphical user interface (GUI). It allows users to create an account, log in, perform deposits and withdrawals, and track their account balance in real-time. The application is designed as a personal project and includes essential banking functionalities.

## Features ✨💵

- 🔐 **User Authentication** - Register and log in securely.
- 💰 **Deposit & Withdrawal** - Perform transactions and update the account balance instantly.
- 📊 **Real-Time Balance Updates** - View the latest balance changes.
- 🖼 **Customizable UI** - Configure background images for different windows.
- 📂 **MySQL Database Support** - Store user data securely.
- ⚠️ **Exception Handling** - Handles common banking operation errors gracefully.
- 🔄 **Transaction Logging** - Track and store all user transactions.

## Technologies Used 🛠💻

- **Java** (Swing for GUI)
- **MySQL** (Database Management)
- **JDBC** (Database Connection)

## Installation & Setup 🏗⚙️

### Prerequisites 📌
- Install **Java JDK (8 or higher)**
- Install **MySQL Server & Workbench**
- Set up **JDBC Driver** for MySQL

### Database Configuration ⚙️
1. Create a MySQL database:
   ```sql
   CREATE DATABASE java_bank;
   ```
2. Create a `users` table:
   ```sql
   CREATE TABLE users (
       id INT AUTO_INCREMENT PRIMARY KEY,
       username VARCHAR(50) UNIQUE NOT NULL,
       password VARCHAR(255) NOT NULL,
       balance DECIMAL(10,2) DEFAULT 0.00
   );
   ```
3. Create a `transactions` table:
   ```sql
   CREATE TABLE transactions (
       id INT AUTO_INCREMENT PRIMARY KEY,
       user_id INT NOT NULL,
       transaction_type VARCHAR(50) NOT NULL,
       transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
       transaction_amount DECIMAL(10,2) NOT NULL,
       FOREIGN KEY (user_id) REFERENCES users(id)
   );
   ```
4. Update the database connection in the Java code:
   ```java
   String url = "jdbc:mysql://localhost:3306/java_bank";
   String user = "your_username";
   String password = "your_password";
   ```

### Running the Application 🚀💡
1. Clone this repository:
   ```bash
   git clone https://github.com/yourusername/java-bank.git
   cd java-bank
   ```
2. Configure the **database connection** in the code.
3. Set your **image file paths** for backgrounds.
4. Compile and run the application:
   ```bash
   cd src
   javac Main.java
   java Main
   ```

## Screenshots 🖼📸

![Login](https://via.placeholder.com/600x300?text=Login+Screen)

![Dashboard](https://via.placeholder.com/600x300?text=Dashboard)

## Future Improvements 🚀📈
- 📱 **Responsive UI** - Improve layout adaptability.
- 📩 **Email Notifications** - Notify users on transactions.
- 📊 **Transaction History** - Track past transactions.
- 🔑 **Two-Factor Authentication** - Enhance security.
- 🔄 **Automated Reports** - Generate financial reports.

## License 📜✅
This project is open-source and free to use under the [MIT License](LICENSE).

---

👨‍💻 **Developed by Stevanovic**

