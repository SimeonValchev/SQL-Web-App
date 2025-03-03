# SQL Web App

A fullstack web application that allows users to view and manipulate the entries of a database. CRUD operations are supported. Notifications for failure to connect to the database are also implemented.

## Features

- The database is loaded into a table upon entering the website
- New entries can be inserted by filling the text-fields
- Existing entries can be updated by clicking on a row, which fills the text fields with the data from the row, and then lets the user change it
- Data is validated both on the front and backend:
  - First and Last name size must be between 1-50 characters
  - Salary must be a positive decimal number
  - Start date must be in the past


## Technologies Used
### Backend:
- **Java 17**
- **Spring Boot 3.4.1**
- **Maven**
- **PostgreSQL**
- **JDBC**

### Frontend:
- **HTML**
- **CSS**
- **JavaScript**

## Prerequisites

Before running the project, make sure you have the following installed:

- **Java 17** (or higher)
- **Maven**
- **PostgreSQL**
