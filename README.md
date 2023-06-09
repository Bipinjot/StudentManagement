# Student Management System

This is a Java Spring Boot project developed for managing student information.

## Table of Contents
- [Introduction](#introduction)
- [Features](#features)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)

## Introduction

The Student Management System is a web application built using Java Spring Boot framework. It provides a convenient way to manage student records, including their personal details, courses, and grades. The system is designed to be user-friendly and easy to navigate, making it suitable for educational institutions, teachers, and administrators.

## Features

- CRUD operations for students, courses, and grades
- Student enrollment in courses
- Calculation of grades
- Search and filtering functionality
- Responsive and intuitive user interface

## Prerequisites

Make sure you have the following prerequisites installed on your system:

- Java Development Kit (JDK) 8 or higher
- Apache Maven
- MySQL Server
- Git

## Installation

1. Clone the repository using the following command:
git clone https://github.com/Bipinjot/StudentManagement.git

2. Navigate to the project directory:
cd StudentManagement

3. Create a MySQL database for the project.

4. Update the database configuration in the `application.properties` file located in the `src/main/resources` directory. Set the appropriate values for the `spring.datasource.url`, `spring.datasource.username`, and `spring.datasource.password` properties.

5. Build the project using Maven:
mvn clean install

## Usage

1. Run the application using the following command:
java -jar target/StudentManagement-1.0.jar

Alternatively, you can use an IDE like IntelliJ or VSCode to run the project directly without the jar.


