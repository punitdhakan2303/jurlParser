# jurlParser

jurlParser is a URL parsing and validation library written in Java.

### Design objectives

- Parse URL through console and the URL will be read, parsed
and validated based on rfc3986.
- Minimal dependencies.
- This can be used as a library for parsing any URL.


Getting started
---------------

### Pass the URL through console based on the below format given
protocol://user:password@host:8080/path/document?arg1=val1&arg2=val2#part

###After reading the URL we will parse and validate the URL calling below code. We are using Regex pattern to parse and validate.

###The return type is URLParserDTO, which you can use to get Protocol, Host, port, username, password, query, path and fragment.

// Validate and Parse the URL

    try {
    
        URLParserImpl parsedUrl = new URLParserImpl();
        URLParserDTO urlDto = parsedUrl.parseAndValidate(url);
        printResult(urlDto);
    
    }
    catch ( URLParserException e) {
        printException(e);
    }
    
Executing the code
---------------
### Clone this project and load into your favourite IDE.
### This is a maven project.
### Just do a mvn clean and install and run the Main.java class    

