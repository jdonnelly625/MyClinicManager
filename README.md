# MyClinicManager
A hands-on, work in progress CRUD application developed out of a self-driven learning initiative. Seamlessly integrating cutting-edge backend engineering technologies with a user-friendly frontend, it serves as a comprehensive tool for managing daily healthcare clinic operations. The application supports the maintenance, editing, and utilization of a database containing patient, staff, and appointment information.

**Link to project video demo:** 

![alt tag]() image to go here of dashboard

## How It's Made:

**Tech used:** Spring Boot, JPA, Hibernate, HTML, CSS, JavaScript, SQL, Postman, MySql Workbench, Maven

* **Spring Boot Foundation**:
    - At the heart of the project is Spring Boot, given that this project was initially a hands-on way to practice the principles and fundamentals of Spring I was teaching myself primarily via the book Spring Start Here by Laurentiu Spilca accompanied by spring.io documentation and a lot of YouTube and google searches. This helped to quickly provide scaffolding for the project and inject dependencies as needed via Maven.
    - Spring Boot app configures a Tomcat servlet container to be accessible on port 8080 which makes deployment and development streamlined.

* **Data Management with JPA & Hibernate**:
    - JPA (Java Persistence API) with Hibernate served as the ORM (Object-Relational Mapping) tool, facilitating the interaction between the application and the underlying H2 database.
    - Entities like `Patient`, `Appointment`, and `Clinician` were modeled as JPA Entities, and I leveraged annotations such as `@Entity`, `@Table`, and relational mappings (`@OneToMany`, `@ManyToOne`) to represent and manage relationships. This was a huge eye-opener and my first foray into ORM, adapting my initial plain java classes with fields stored in structures such as LinkedList<Patient> patients and creating a fully connected database.
  - I was able to transition away from typical SQL queries and make use of JPA’s ability to use method naming conventions for queries such as findAllByOrderByAppointmentTimeAsc()


* **RESTful Services with Spring Web**:
    - The application's functionalities were exposed as REST endpoints. Using the annotations provided by Spring Web (`@RestController`, `@RequestMapping`, etc.), I defined routes for CRUD operations.
    - I preferred this decoupled architecture rather than rendering html templates in the controllers to allow for potential scalability and the ease of integration with other systems or a more elaborate front end in the future.

* **JSON Handling with Jackson**:
    - The app interfaces via JSON for data transmission between the front and back ends. Jackson played a pivotal role in serializing and deserializing the Java objects, ensuring smooth data flow.

* **Front-end Development**:
    - The front end was not a learning focus of this project, so I kept it simple with HTML, CSS, and JavaScript.
    - I employed vanilla JavaScript's `fetch` API for asynchronous communication with the backend, handling operations like patient registration and appointment management.
    - Session management on the client side ensured that only authenticated users could navigate the app, enhancing security.

* **Google Calendar API Integration (Proposed)**:
    - To further enhance the application, I'm looking into the Google Calendar API. This integration would allow the app to schedule, modify, and notify of appointments, making the system more interactive and user-centric.

* **Project Management with Maven**:
    - Maven was invaluable in managing project dependencies. Its conventions simplified the process of adding new libraries, ensuring version compatibility, and building the project.


## Lessons Learned:

* **Background and Origins**:
  - **Motivation**: Post my graduation from NC State, I aimed to bridge the gap between my academic knowledge and practical application development. My objective? Build my own CRUD app from scratch.
  - **The Spring Attraction**: My research led me to the Spring ecosystem. With books like "Spring Start Here", I delved into core concepts such as inversion of control, dependency injection, and Object Relational Mapping.
  - **The Learning Curve**: As I ventured deeper, the vastness of application development became evident. I selectively focused on areas like REST applications and how Spring Web exposes REST endpoints, ensuring I understood the architecture and flow of a web application.

* **One Step at a Time**:
  - **Spring Start Here**: Initially following small examples from this book, I found them educational but fragmented. My new approach became to try and adapt a concept from the book into my own project as it arose, and flesh it out with extra research as needed. This eventually became this project.
  - **Trade Offs**: While simple HTML, CSS, and JavaScript may not offer the advanced features of modern front-end frameworks, they aptly served my purpose by allowing the creation of a user-friendly client side. In the future, I'm keen on exploring frameworks like React or Vue to enhance the UI/UX aspects further.
  - **RESTful web development**: Learning RESTful web development became a game-changer for me and the pieces finally started clicking together when I could start sending fetch requests that interacted with my app and actually gave real time feedback on the client side.
  - **Connecting the database**: The final piece of the puzzle came with implementing a persistence layer with JPA and Hibernate. With the power to annotate Java classes and create repositories with custom queries, the overall flow of the application became clearer.

## Considerations:
* **At the time of writing this is still very much work in progress. I plan to add new features that include:**
  - Comprehensive testing and unit tests
  - Update features and more custom database queries
  - Implementing Google's Calendar API to create events and set reminders
  - Ability for clinicians to edit more patient information such as notes, medications, and allergies
  - More security features and access limitations
  - An updated and more intuitive front end 




