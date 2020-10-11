package com.challengeone.wcs;

import com.challengeone.wcs.constants.URLConstants;
import com.challengeone.wcs.dto.URLParserDTO;
import com.challengeone.wcs.exception.URLParserException;
import com.challengeone.wcs.parser.URLParserImpl;
import java.util.Scanner;



/**
 * @author Punit Dhakan
 * This class will accept a URL from Command Prompt and it will validate and parse the URL and print the result.
 */
public class Main {

    public static void main(String[] args) {

        // Using Scanner for Getting Input from User
        Scanner in = new Scanner(System.in);

        //Asking to user to enter the URL, so that we can parse it
        System.out.println(URLConstants.ENTER_MSG);
        System.out.println(URLConstants.EXPECTED_FORMAT);
        String url = in.nextLine();
        System.out.println(URLConstants.VALID_URL+url);

        //Null check, if the URL is null or blank ask the User to enter the correct URL.
        if(url.isEmpty() || url.isBlank()){
            System.err.println(URLConstants.INPUT_URL);
            System.exit(1);
        }

        // Validate and Parse the URL
        try {

            URLParserImpl parsedUrl = new URLParserImpl();
            URLParserDTO urlDto = parsedUrl.parseAndValidate(url);
            printResult(urlDto);

        }
        catch ( URLParserException e) {
            printException(e);
        }


    }

    /**
     * @author Punit Dhakan
     * This method will print the end result.
     * @param urlDto
     */
    private static void printResult(URLParserDTO urlDto) {
        System.out.println("\tResult:");
        System.out.println("\t\turlDto: " + urlDto.toString());

        if (urlDto.getUrl() != null) {
            System.out.println("\t\tUrl: " + urlDto.getUrl());
        }

        if (urlDto.getProtocol() != null) {
            System.out.println("\t\tProtocol: " + urlDto.getProtocol());
        }
        if (urlDto.getUserInfo() != null) {
            System.out.println("\t\tUserInfo: " + urlDto.getUserInfo());
        }
        if (urlDto.getUserName() != null) {
            System.out.println("\t\tUsername: " + urlDto.getUserName());
        }
        if (urlDto.getPassword() != null) {
            System.out.println("\t\tPassword: " + urlDto.getPassword());
        }
        if (urlDto.getHost() != null) {
            System.out.println("\t\tHost: " + urlDto.getHost());
        }
        if (urlDto.getPort() != 0) {
            System.out.println("\t\tPort: " + urlDto.getPort());
        }else{
            System.out.println("\t\tPort: " + 0);
        }
        if (urlDto.getPath() != null) {
            System.out.println("\t\tPath: " + urlDto.getPath());
        }
        if (urlDto.getQuery() != null) {
            System.out.println("\t\tQuery: " + urlDto.getQuery());
        }
        if (urlDto.getFragment() != null) {
            System.out.println("\t\tFragment: " + urlDto.getFragment());
        }
    }

    /**
     *This method is to print the exception in the console.
     * @param exp
     */
    private static void printException(URLParserException exp) {
        System.out.println("\t\tURLParserException---->>: " + exp.getMessage());
    }
}
