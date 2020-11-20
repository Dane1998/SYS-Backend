[![Build Status](https://travis-ci.com/kasarama/CA3_back_start.svg?branch=main)](https://travis-ci.com/kasarama/CA3_back_start)


This document explains how to use this code (build, test and deploy), locally with maven, and remotely with maven controlled by Travis
 - [How to use](https://docs.google.com/document/d/1K6s6Tt65bzB8bCSE_NUE8alJrLRNTKCwax3GEm4OjOE/edit?usp=sharing)
 
 To be able to use this startcode it is necessary to setup Docker enviroment localy and on the Droplet according to this instructions:
  local setup: https://docs.google.com/document/d/1RlAR3Ax-c3cpBrW2ez7NYAc8mrCJiRjAC3xdjjdi0Uc/edit
  droplet: https://docs.google.com/document/d/1PCHrEnD_9G_clweMn01kQhB9n1VGpbhSg8ef2UhaLMA/edit
 
 Initial Setup:
  Clone the code
  Start your local docker enviroment
  Setup database to be used with project and edit the persistance.xml file to point at that database
  Edit CONNECTION_STR in docker-compose file on your droplet or create new one yto pint at your database 
  If you decidet to create a new  CONNECTION_STR, edit a value of connection_str in  EMF_Creator file
  Run clean and build in the project
  Run mvn clean test in gitbash in root of the project
  Create a new repository for the project push the code up and locate that repository on travis. 
  Add Environment Variables 
  Copy the Build Markdown to README file 
 
 
 
 
 
