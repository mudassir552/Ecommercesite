README.txt

===============================================================================
                     eCommerce Spring Boot Application
===============================================================================

Project Description:
---------------------
This project is a full-featured eCommerce web application built with
**Spring Boot** and **Thymeleaf**. It allows users to browse products, 
add items to their shopping cart, and complete the checkout process.
The application supports user authentication, product management, 
and order processing.

Demo Screen Shots
-------------------
![image](https://github.com/user-attachments/assets/4a0493dd-9570-4c72-b38a-4515217ed130)
![image](https://github.com/user-attachments/assets/09c4101d-5b18-440f-9af8-e73e034a51c7)
![image](https://github.com/user-attachments/assets/fb9e8b5e-946a-4938-a7a5-68a52097f72a)




Technologies Used:
------------------
- **Spring Boot**: For building the backend RESTful APIs and managing business logic.
- **Thymeleaf**: For rendering dynamic HTML views.
- **CSS**      :For Styling
- **Spring Security**: For authentication and authorization.
- **Mongo Database && MYSQL Database** (or **MySQL/PostgreSQL**): For data storage and management.
- **Spring Data JPA**: For ORM-based data management.
- **Bootstrap**: For front-end styling.
- **Maven**: For dependency management and building the application.

Key Features:
-------------
- **User Registration and Login**: User authentication and profile management.
- **Product Browsing**: Display products, categories, and filters.
- **Shopping Cart**: Add, remove, and modify items in the cart.
- **Checkout and Orders**: Place orders and view order history.
- **Admin Dashboard**: Manage products, categories, users, and orders.
- **Responsive Design**: Mobile-friendly user interface with Bootstrap.

Getting Started:
----------------
Prerequisites:
--------------
- **Java 11** or newer (Spring Boot 2.5+).
- **Maven** for building the project.
- An IDE (e.g., IntelliJ IDEA, Eclipse) for development.

Clone the Repository:
----------------------
To clone the repository, run:
    
    https://github.com/mudassir552/Ecommercesite.git

Setting up the Database:
-------------------------

For **MySQL/PostgreSQL**, modify the following properties in `src/main/resources/application.properties`:

    spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce_db
    spring.datasource.username=root
    spring.datasource.password=yourpassword
    spring.jpa.hibernate.ddl-auto=update
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

For **MongoDb** , create two data bases named Products_desc and ProductImages

Building and Running the Application:
-------------------------------------
1. Install dependencies by running:
        mvn clean install
2. Run the application with:
        mvn spring-boot:run

Accessing the Application:
---------------------------
Once the application is running, open a web browser and visit:
    http://localhost:8080/shopme


Project Directory Structure:
----------------------------
ecommerce-springboot-thymeleaf/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── ecommerce/
│   │   │           ├── controller/       # Spring MVC controllers
│   │   │           ├── model/            # Entities and data models
│   │   │           ├── repository/       # JPA repositories
│   │   │           └── service/          # Business logic
│   │   ├── resources/
│   │   │   ├── templates/                 # Thymeleaf templates
│   │   │   ├── static/                    # CSS, JS, Images
│   │   │   └── application.properties     # Application configuration
│   │   └── webapp/
│   └── test/
├── pom.xml                                # Maven configuration
└── README.txt                             # Project description

API Endpoints:
--------------
- **GET /shopme** : Landing page
- **GET /products/Shirts : Shirts
- **POST /login**: User login.
- **POST /register**: User registration.
- **GET /products**: List all products.
- **GET /products/{id}**: Get details of a single product.
- **POST /cart/add**: Add product to cart.
- **POST /checkout**: Place an order.

Development Tips:
-----------------
- Use **Spring DevTools** for automatic restarts during development to improve productivity.
- Modify the `application.properties` file to configure various settings like the server port and database credentials.
- Use **Postman** or **cURL** to test the API endpoints during development.

Contributing:
-------------
1. Fork the repository.
2. Create a new branch (`git checkout -b feature/your-feature`).
3. Make your changes.
4. Commit your changes (`git commit -am 'Add feature'`).
5. Push to the branch (`git push origin feature/your-feature`).
6. Create a pull request.

License:
--------
This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details.

Acknowledgments:
----------------
- **Spring Boot** for a powerful and easy-to-use framework.
- **Thymeleaf** for templating and rendering dynamic HTML.
- **Bootstrap** for a responsive and modern design.
