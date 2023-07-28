# Music-Streaming-App
**[Deployment Link](http://52.66.243.103:8080/swagger-ui/index.html#/ "Swagger Link")**
> The music streaming app project built with Spring Boot can serve as a versatile and feature-rich platform for users to access and enjoy music in various ways. We have leveraged the capabilities of Spring Boot, along with other powerful dependencies, to ensure a smooth and feature-rich experience for our users
---
## Frameworks And Languages:
The Music Streaming Service project is developed using the following frameworks and languages:

* Spring Boot: A Java-based framework for building web applications.
* Spring MVC: A module of the Spring Framework that supports building web applications.
* Java: The programming language used for backend development.
* Hibernate: An Object-Relational Mapping (ORM) framework used for database interactions.
* MySQL: The chosen database management system.
---

## Dependencies Used :
The dependencies used in the pom.xml file for this project are :

* Spring Starter Web: Provides essential web-related features and configurations.
* Spring JPA: Simplifies working with relational databases using Java Persistence API (JPA).
* Lombok: Reduces boilerplate code with annotations for getter, setter, and other common methods.
* Validation: Enables data validation using annotations.
* MySQL Driver: The chosen database management system for data storage.
* javax.mail: For integrating email functionality.
* Swagger: To generate interactive API (documentation).
---
## Data Flow:
  * **<ins>Model:</ins>**
    
      Represents the database entities, such as User, Admin, Song, Playlist, and AuthenticationToken for user and admin.

    * User: Represents a registered user of the music streaming app.
    * Admin: Represents an administrator with additional privileges.
    * Song: Stores information about individual songs in the app's library.
    * Playlist: Represents a collection of songs curated by users.
    * AdminAuthentication: Handles authentication for administrators.
    * UserAuthentication: Handles authentication for regular users.
      
  * **<ins>Service:</ins>**
    
      Implements the business logic and interacts with the repositories.
    
    * UserService: Service for user-related operations.
    * AdminService: Service for admin-related operations.
    * SongService: Service for song-related operations
    * PlaylistService: Service for playlist-related operations.
    * AdminAuthenticationService: Service for admin authentication-related operations.
    * UserAuthenticationService: Service for user authentication-related operations.

  * **<ins>Repository:</ins>**
    
       Handles database operations,  manages the CRUD operations and provides an interface for data access.
    
    * UserRepository: Repository for managing user data.
    * AdminRepository: Repository for managing admin data.
    * SongRepository: Repository for managing song data.
    * PlaylistRepository: Repository for managing playlist data.
    * AdminAuthenticationRepository: Repository for managing admin authentication-related data.
    * UserAuthenticationRepository: Repository for managing user authentication-related data.

  * **<ins>Controller:</ins>**

       Handles incoming HTTP requests, manages the data flow, and sends responses back to the client.

     * UserController: Manages user-specific endpoints such as sign-in, sign-up, sign-out and other crud endpoints for the playlist.
     * AdminController: Handles admin-specific endpoints like sign-in, sign-up, sign-out and other crud endpoints for the song.

   * **<ins>DTOs</ins>**

       DTOs (Data Transfer Objects) are used for data exchange between the front-end and back-end. The following DTOs are used:

     * SignInInput: Contains fields required for user/admin sign-in.
     * SignUpOutput: Holds necessary fields for user/admin sign-up.
       
   * **<ins>Password Encrypter and Email Utility</ins>**
     
     * PasswordEncrypter: Utilizes encryption techniques to secure user and admin passwords.
     * EmailUtility: Facilitates the integration of email functionality for password reset or verification.
---
## Database Design:

The project uses MySQL as the database management system. To design the database for the music streaming app, we need to consider the entities (tables) and their relationships. Based on the features and models mentioned in this file.
    
   * **<ins>Relationships:</ins>**

     * Each User can have multiple Playlists.
     * Each Playlist can have multiple Songs.
     * Each User can have multiple Songs in their playlists, and each Song can be part of multiple Playlists.
---
## Data Structure:

The project utilizes several data structures, including:

* Strings: Used for storing text-based data, such as user names, email addresses, phone numbers, artist names, album names, song names and authentication tokens.
* Integers: Used for storing numerical data, such as IDs.
* LocalDate: Used for storing date information, including token creation dates.
* ArrayList: To organize and manage data efficiently, such as lists of songs, and playlists.
---
## Deployment using AWS

Once we developed and tested our music streaming app locally, next we have to deploy it to a cloud platform like AWS (Amazon Web Services) to make it accessible to users worldwide. AWS provides a robust and scalable infrastructure to host web applications, making it a popular choice for deploying Spring Boot applications.

**Swagger Link** : `http://52.66.243.103:8080/swagger-ui/index.html#/`

---
## Project Summary:

The music streaming app aims to provide users with a seamless experience to explore, discover, and enjoy their favourite music. It leverages the Spring Boot framework and related dependencies for efficient web development and data management. Users can register, log in, and create personalized accounts, allowing them to access a vast library of songs, and create playlists. And deployed the project using AWS(Amazon Web Service). 

---
## Conclusion:

We are excited to present our Music Streaming App built with Spring Boot in IntelliJ. Our app brings together the power of Spring Boot and various dependencies to create a seamless and enjoyable music streaming experience. From exploring a vast library of songs to creating personalized playlists and sharing musical moments with friends, our app offers a wide range of features for music enthusiasts.

---


    
