#Use the OpenJDK 17 image as the base image
FROM openjdk:17

#Create a new app directory for my application files
RUN mkdir /application

#Set the direcotry for excution future commands
WORKDIR /application

#Copy the jar from host machine to image filesystem
COPY target/*.jar assessment.jar

#Api port
EXPOSE 8080

#To run java application:
ENTRYPOINT ["java","-jar","assessment.jar"]