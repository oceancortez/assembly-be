# assembly-be

- 1- Clone the project
		
	  Java 8	
      https://github.com/oceancortez/assembly-be.git
      
- 2- Install dependency lombok in your IDEA to run in debug mode
	

- 3- Install the database MySQL
      
      Verison: 8.0.12 / PHP 8.0.12s
	  https://www.apachefriends.org/download.html
	  
	  Looking file application-local.properties to get name of database and user and password
	
- 4- Execute the scripts in path bellow

	  /assembly-be/scripts/create.sql
	 
- 5- Run the project using application-local.properties

- 6- Swagger

	  Local: http://localhost:8080/sicredi/assembly/v1/swagger-ui.html#/
      
      prod: https://sicredi-332620.rj.r.appspot.com/sicredi/assembly/v1/swagger-ui.html#/

- 7- There is a SCHEDULED in the project to summarize the votes, It runs each 10 minutes.


