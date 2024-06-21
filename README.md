# CompuStore Manager 

![image](https://github.com/VadimZubchenko/PC-Store/assets/36922064/d25583a0-a3b6-420f-948b-e55faf8b5029)

## Overview

Developed as a desktop application this an inventory management tool designed for a small brick-and-mortar computer store. The application is intended for internal use to support sales, inventory management, and financial tracking. It features a simple and straightforward graphical interface to facilitate ease of use.

## Technologies and Tools

### Scrum methodology

The project was executed using the Scrum methodology, enhanced by the Agilefant application. User stories and use cases were defined in Agilefant, which allowed us to monitor and manage the progress of the application and the working hours of the team. Out of the 30 user stories in Agilefant, we completed 28. Figure 1 presents the user stories in Agilefant.

![image](https://github.com/VadimZubchenko/PC-Store/assets/36922064/de5880e9-99f8-48ca-b03b-d0904f893109)

### Tools
- **Programming Languages:** Java
- **Frameworks:** Hibernate, MVC
- **Databases:** MariaDB, SQL
- **Development Tools:** Apache NetBeans, Maven, Jenkins, SonarQube, Agilefant, GitHub
- **Other Technologies:** CSS, XML

## Architecture

The application follows the MVC (Model-View-Controller) architectural pattern.

![image](https://github.com/VadimZubchenko/PC-Store/assets/36922064/5d6b30cc-1488-48bb-be3a-0a8285dc73e2)


### Model
The Model component includes classes such as `Asiakas.java`, `Henkilosto.java`, `Osa.java`, `Paketti.java`, `Product.java`, and others which handle the data and business logic.

### View
The View component consists of several Java files like `View.java`, `LoginView.java`, `Tab1.java`, `Tab2.java`, `Tab3.java`, `Tab4.java`, and others which define the user interface.

### Controller
The Controller is managed by `Controller.java` which handles the input, processes the data with the Model, and updates the View.

## Features

### User Interfaces

- **Sales Page:** Allows sales personnel to create orders from available parts and computer packages, fill in customer information, and adjust order quantities.
      ![image](https://github.com/VadimZubchenko/PC-Store/assets/36922064/0ae510af-5d23-4882-b9b2-f8026e7475b9)

- **Inventory Page:** Enables warehouse staff to manage inventory, add or remove products, and create packages from parts.  
      ![image](https://github.com/VadimZubchenko/PC-Store/assets/36922064/cd5f350c-8782-457c-b83a-4142d135dc80)

- **Orders Page:** Lists all current orders with details like order ID, customer information, order date, and total price. Orders can be selected for detailed view or deletion.
     ![image](https://github.com/VadimZubchenko/PC-Store/assets/36922064/a3d71f52-ec2c-4176-addd-92f2f504b3fe)

- **Finance Page:** Displays a graph of sales data, which is updated automatically with each new order.
     ![image](https://github.com/VadimZubchenko/PC-Store/assets/36922064/f8296c7f-fa51-4ebe-ab14-554afa1a7f7b)

### Localization
The application supports localization, allowing users to switch languages.

### Security
Password encryption using Base64 is implemented for user account management.

   ![image](https://github.com/VadimZubchenko/PC-Store/assets/36922064/288187e5-13de-4167-830d-426c1036ec44)



## Testing

The application was tested using JUnit 5 and TestFX. A total of 52 tests were created to validate the application's functionality, though some had to be removed due to issues with Jenkins and SonarQube.
   
   ![image](https://github.com/VadimZubchenko/PC-Store/assets/36922064/686e70ac-d094-4003-bc1d-b06c9ef9abc1)



## Requirements and Implementation

### Initial Requirements (Project 1)
- Project initiation and application framework creation
- Hibernate setup
- Connection to Metropolia's remote server
- Maven project setup
- Jenkins and Agilefant integration

### Additional Requirements (Project 2)
- Support for a new client: a Russian computer company
- New functional requirements including reusable component implementation, search capabilities, inventory value management, and device configuration suggestions
- User interface improvements and localization

### Non-Functional Requirements
- Code refactoring to remove "bad smells"
- Use of design patterns for code maintainability and extensibility

## Summary

The application was completed on time and met most of the functional requirements set out in Project 1. While some non-functional requirements were not fully implemented due to time constraints, the application overall serves as a comprehensive inventory management tool for the intended use case. With additional time, further improvements to the user interface and adherence to design guidelines could be made.

## Usage

1. **Login:** Users log in with a username and password, which are encrypted and stored in the database.
2. **Navigate:** Use the NavBar to switch between different sections (Sales, Inventory, Orders, Finance).
3. **Create Orders:** Sales personnel can create and manage customer orders.
4. **Manage Inventory:** Warehouse staff can add, remove, and manage inventory items.
5. **Track Finances:** Supervisors can view financial data and track sales performance through graphs.

## Contact

For any questions or further information, please contact the project team members.
