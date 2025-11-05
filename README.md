Java Swing Car Management System
This is a desktop application built with Java Swing that demonstrates a simple car dealership management system. It features user authentication, role-based access control (Admin vs. User), and data persistence using JSON files.

This project serves as a practical example of Java Swing GUI development, object-oriented design, and flat-file data handling (serialization/deserialization) with the Jackson library.

✨ Features
User Authentication: Secure login system for existing users.

User Registration: A simple registration form for new users (accounts default to the USER role).

Role-Based Access Control (RBAC):

Admin Dashboard: Admins have full CRUD (Create, Read, Delete) capabilities. They can view, add, and delete car listings.

User Dashboard: Regular users have a read-only view of the car listings.

Data Persistence: All user accounts and car listings are stored in local users.json and car.json files, managed via the Jackson library.

Clean UI: A functional and clean multi-window interface with clear navigation, including "Logout" and "Back" buttons.

🛠️ Technologies Used
Core: Java

UI: Java Swing (for the desktop GUI)

Data Handling: Jackson (ObjectMapper) for JSON serialization and deserialization.

Data Storage: JSON (flat files)
