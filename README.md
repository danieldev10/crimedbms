# Criminal Record Management System

##Overview
The Crime Records Management System is an automated solution designed to efficiently manage and store records related to criminals within a specific area. This system streamlines the process of recording, organizing, and accessing crucial information about criminal activities, cases, and associated details.

## Key Features
1. Criminal Records: The system maintains individual criminal records containing essential details such as crime type, date of the crime, and punishment period. This information provides a clear overview of each criminal's history and activities.

2. Efficient Data Entry: The system offers an intuitive user interface for inputting and updating criminal records, ensuring that relevant information is accurately recorded.

3. Search and Retrieval: Users can quickly search and retrieve specific criminal records based on various criteria such as crime type, date, location, and more. This enhances efficiency in information retrieval and analysis.

4. Auditing and Tracking: The system incorporates auditing capabilities, tracking user interactions and modifications made to criminal records. This feature enhances accountability and data integrity.

5. Role-based Access: Access to the system is controlled through role-based authentication, ensuring that only authorized personnel have access to sensitive criminal records.

6. Integration with Auditing: The system seamlessly integrates with an auditing component, capturing metadata such as creation and modification timestamps, as well as the identities of users who made these changes.

## Endpoints

#### CrimeController
- GET /crimes (Get list of crimes)
- GET /createnewcrime (Create new crime form)
- POST /crimes (Save crime)
- GET /crimes/update/{id} (Edit crime form)
- POST /crimes/update/{id} (Update crime)
- DELETE /crimes/delete/{id} (Delete crime)

#### CriminalRecordController
- GET /createnewrecord (Create new criminal record form)
- POST / (Save criminal record)
- GET /records/{id} (Edit criminal record form)
- POST /records/{id} (Update criminal record)
- DELETE /delete/{id} (Delete criminal record)
- GET /view/{id} (View criminal record details)

#### PrisonController
- GET /prisons (Get list of prisons)
- GET /createnewprison (Create new prison form)
- POST /prisons (Add new prison)
- GET /prisons/update/{id} (Edit prison form)
- POST /prisons/update/{id} (Update prison)
- DELETE /prisons/delete/{id} (Delete prison)

#### StateController

- GET /states (Get list of states)
- GET /createnewstate (Create new state form)
- POST /states (Save state)
- GET /states/update/{id} (Edit state form)
- POST /states/update/{id} (Update state)
- DELETE /states/delete/{id} (Delete state)

#### UserController

- POST /users/addNew (Register new user)
- GET /users (Get list of users)
- POST /users (Save user)
- GET /users/{id} (Edit user form)
- POST /users/{id} (Update user)
- DELETE /users/delete/{id} (Delete user)

#### ApplicationController

- GET / (Index page with list of criminal records)
- GET /accessDenied (Access denied page)
- GET /login (Login page)
- GET /logout (Logout page)
- GET /register (Register page)
- GET /index (Home page link)


## Technologies
- Java
- Spring Boot
- Spring Security
- MySQL
- Thymeleaf
- HTML
- CSS
- Javascript
- Bootstrap

## Entity Relationship Diagram (ERD) <img width="1440" alt="Screenshot 2023-08-09 at 22 01 32" src="https://github.com/danieldev10/crimedbms/assets/64502843/1c88cf8c-8c31-46eb-a799-1a7f965f1a4a">

#### Description
1. Crime - Criminal Record Relationship:
  - The criminal_record table has a foreign key crime_id that references the id field in the crime table.
  - This represents a one-to-many relationship, where one crime can have multiple criminal records (cases).
2. Criminal Record - Prison Relationship:
  - The criminal_record table has a foreign key prison_id that references the id field in the prison table.
  - This represents a many-to-one relationship, where many criminal records (cases) can be associated with one prison.
3. Criminal Record - State Relationship:
  - The criminal_record table has a foreign key state_id that references the id field in the state table.
  - This represents a many-to-one relationship, where many criminal records (cases) can be associated with one state.
4. Prison - State Relationship:
  - The prison table has a foreign key state_id that references the id field in the state table.
  - This represents a many-to-one relationship, where many prisons can be located in one state.
5. User - User Role Relationship:
  - The user_role table contains composite primary keys (user_id, role_id) that serve as foreign keys referencing the id fields in the user and role tables.
  - This represents a many-to-many relationship, where a user can have multiple roles and a role can be associated with multiple users.
6. User Role - Role Relationship:
  - The user_role table has a foreign key role_id that references the id field in the role table.
  - This represents a many-to-one relationship, where many user roles are associated with one role.
7. Criminal Record - User Relationship:
  - The criminal_record table has fields created_by and last_modified_by that are related to users. These fields could be foreign keys referencing the id field in the user table.
  - This represents a many-to-one relationship, where many criminal records are associated with one user for creation and modification.

#### Demo
- Login
  <img width="1440" alt="login" src="https://github.com/danieldev10/crimedbms/assets/64502843/85437cfe-2c0f-4ff1-8e8e-14328ce07aac">

- Register
  <img width="1440" alt="register" src="https://github.com/danieldev10/crimedbms/assets/64502843/122cf118-6ce4-442d-84c9-030b7cf2d21b">

- Criminal Records
  <img width="1440" alt="criminal records" src="https://github.com/danieldev10/crimedbms/assets/64502843/2b70d11d-b769-42da-b228-12c2da83cc98">

- Criminal Records - Create New Record
  <img width="1440" alt="criminal records create record" src="https://github.com/danieldev10/crimedbms/assets/64502843/8e7e3f9f-29d0-4727-8cc3-4e1fd3b469de">
  <img width="1440" alt="criminal records create record 2" src="https://github.com/danieldev10/crimedbms/assets/64502843/59cec920-bb67-4d9b-8344-475d48ac4ba5">

- Criminal Records - Update Record
  <img width="1440" alt="criminal records update" src="https://github.com/danieldev10/crimedbms/assets/64502843/c4abd635-4441-419d-90ed-ceda7256cb31">
  <img width="1440" alt="criminal records update 2" src="https://github.com/danieldev10/crimedbms/assets/64502843/c9d01567-5122-4ebf-b5dd-d3bdf77cc68a">

- Criminal Records - View Record Details
  <img width="1440" alt="criminal records view details" src="https://github.com/danieldev10/crimedbms/assets/64502843/bcc1462a-f541-4685-87a6-d929123876db">
  <img width="1440" alt="criminal records view details 2" src="https://github.com/danieldev10/crimedbms/assets/64502843/0c09e26d-55f8-47ae-bd2d-ec0f75d18a89">

- Prisons
  <img width="1440" alt="prisons" src="https://github.com/danieldev10/crimedbms/assets/64502843/b4c9e704-9d90-420e-9949-1e3e222fb467">

- States
  <img width="1440" alt="states" src="https://github.com/danieldev10/crimedbms/assets/64502843/853b5ca4-10d7-4def-97d9-b538d26c633c">

- Crime
  <img width="1440" alt="crime" src="https://github.com/danieldev10/crimedbms/assets/64502843/22292f7d-261a-42f3-ae67-c7446b3e2726">

- Manage Roles
  <img width="1440" alt="manage roles" src="https://github.com/danieldev10/crimedbms/assets/64502843/13b96a6b-2e3c-4093-8cd7-ebae98909537">
  <img width="1440" alt="manage user role" src="https://github.com/danieldev10/crimedbms/assets/64502843/40b03ad0-32e8-403d-84d5-044acbfea3b2">
  
- Error Page
  <img width="1440" alt="error page" src="https://github.com/danieldev10/crimedbms/assets/64502843/3ada74b4-92d6-4a2b-9373-208e0eff0917">

## Contact
- Email : achonma.tanko@aun.edu.ng
- Phone & Whatsapp : 09030634372
- Twitter : <a href="https://www.x.com/_tank0">_tank0</a>






