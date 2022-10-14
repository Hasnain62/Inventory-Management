# CMPT370_Group14

To install mysql Open the terminal and 
 Follow the steps written in the link provided.

For linux users,

https://www.digitalocean.com/community/tutorials/how-to-install-mysql-on-ubuntu-20-04

On , Step 3 — Creating a Dedicated MySQL User and Granting Privileges

When Creating a user , write 

CREATE USER 'project_370'@'localhost' IDENTIFIED  BY 'Password@14';

GRANT ALL PRIVILEGES ON *.* TO 'project_370'@'localhost';

Complete till step 3 , 

Then , Log into mysql as this newly created user.

by mysql -u project_370 -p

CREATE DATABASE "Inventory_Management_system";

Then you can check by writing SHOW DATABASES; 

if the database was created .

Connect the mysql-connector-java-8.0.28.jar file as a library in Project Structure---->Libraries -----> '+' sign top left corner add the file.

Test the connection in Sqltest.java

if no errors are shown Connection is established





--------------------------------------------------------------





For MacOS users,

Follow the link , 

https://www.youtube.com/watch?v=101u5Ssqbyw

At 4:20 ,

Inside of mysql shell,

create the user 

CREATE USER 'project_370'@'localhost' IDENTIFIED  BY 'Password@14';

GRANT ALL PRIVILEGES ON *.* TO 'project_370'@'localhost';

then exit from root by 

exit;

Then , on the terminal,

/usr/local/mysql/bin/mysql -u project_370 -p

it will prompt for Password, write

Password@14

Then inside mysql shell ,

CREATE DATABASE "Inventory_Management_system";

Then you can check by writing SHOW DATABASES; 

if the database was created .

Connect the mysql-connector-java-8.0.28.jar file as a library in Project Structure---->Libraries -----> '+' sign top left corner add the file.

Test the connection in Sqltest.java

if no errors are shown Connection is established




--------------------------------------------




For windows users 

https://www.youtube.com/results?search_query=install+mysql+for+windows+terminal

Inside of mysql shell,

create the user 

CREATE USER 'project_370'@'localhost' IDENTIFIED  BY 'Password@14';

GRANT ALL PRIVILEGES ON *.* TO 'project_370'@'localhost';

then exit from root by 

exit;

then launch again with the user just created.

CREATE DATABASE "Inventory_Management_system";

Connect the mysql-connector-java-8.0.28.jar file as a library in Project Structure---->Libraries -----> '+' sign top left corner add the file.

Test the connection in Sqltest.java

