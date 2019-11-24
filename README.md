# AGODA DOWNLOADER
Project to download sources with different protocols for the coding challenge problem for AGODA.

## Getting Started

These instructions will get you a guide to test the microservice to download files from differents protocols

```
    - We could test by executing the test and this test will download the list of uri's that we have in the List urls,
      the process will download the files in a temp directory and then will create an new directory in the path of the 
      project if it is not exist yet and move from the temp directory to the new directory created, it will be happen 
      only when the process of download is done.
    - We could test by run the microservice from console (maven command line or java -jar when we get the jar file)
      or by Postman's API development to test our endpoint with the body request, in the directory document we can find 
      the swagger contract to test the donwloader service.
   
```
