package com.challengeone.wcs.parser;


import com.challengeone.wcs.constants.URLConstants;
import com.challengeone.wcs.dto.URLParserDTO;
import com.challengeone.wcs.exception.URLParserException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Punit Dhakan
 * This Class is the base class of this librabry. It implements the validate() and parseURL() which take a URL as an input and validate and parsed
 * it and send appropriate URLParserDTO object.
 */
public class URLParserImpl implements URLParserInterface {


    /**
     * Default Constructor
     */
    public URLParserImpl() {

    }

    /**
     * This method will call the parse and validate method.
     * @param url
     * @return
     * @throws URLParserException
     */
    public URLParserDTO parseAndValidate(String url) throws URLParserException {
        URLParserDTO urlDto = parseURL(url);
        validate(urlDto);
        return urlDto;

    }

    /**
     * This method will validate the URLParserDTO and check whether the values given are valid or not.
     * 1 - Protocol and Host should not be null and it should follow the pattern.
     * 2 - UserInfo would be validated based on the pattern
     * 3 - Host and Port would be validated and would thrown error if proper values not given
     * 4 - Validate Path and it should nto contain "#" and "?"
     * 5- Validate query and it should not contain "#" and "space"
     * @param dto
     * @return
     * @throws URLParserException
     */
    public void validate(URLParserDTO dto) throws URLParserException {

        //validate protocol and host- protocol is made mandatory
        if(dto.getProtocol() == null || dto.getProtocol().isEmpty()){
            throw new URLParserException(URLConstants.INVALID_PROTOCOL+dto.getProtocol());
        }

        //validate protocol and host- protocol is made mandatory
        if(dto.getHost() == null || dto.getHost().isEmpty()){
            throw new URLParserException(URLConstants.INVALID_HOST+dto.getHost());
        }

        //validate protocol
        Pattern pattern = Pattern.compile(URLConstants.REGEX_PATTERN_PROTOCOL);
        Matcher matcher = pattern.matcher(dto.getProtocol());
        if(!matcher.find()) {
            throw new URLParserException(URLConstants.INVALID_PROTOCOL+dto.getProtocol());
        }

        //validate userInfo
        if(dto.getUserInfo() != null) {
            Pattern userInfoPattern = Pattern.compile(URLConstants.REGEX_PATTERN_USERINFO);
            Matcher userInfoMatcher = userInfoPattern.matcher(dto.getUserInfo());
            if (!userInfoMatcher.find()) {
                throw new URLParserException(URLConstants.INVALID_USERINFO + dto.getUserInfo());
            }
        }

        //validate hostport
        if(dto.getHostPort() != null && !dto.getHostPort().isEmpty()){
            char first = dto.getHostPort().charAt(0); // just like array, string index is zero based
            char last = dto.getHostPort().charAt(dto.getHostPort().length() - 1);

            if(first=='[' && last== ']' ){
                Pattern hostPortPattern = Pattern.compile(URLConstants.REGEX_PATTERN_IP);
                String concatHostPort = dto.getHostPort().substring(1, dto.getHostPort().length() - 1);
                Matcher hostPortMatcher = hostPortPattern.matcher(concatHostPort);
                if(!hostPortMatcher.find()) {
                    throw new URLParserException(URLConstants.INVALID_HOST_PORT+dto.getHostPort());
                }
            }else{
                Pattern hostPattern = Pattern.compile(URLConstants.REGEX_PATTERN_HOSTNAME);
                Matcher hostMatcher = hostPattern.matcher(dto.getHost());
                if(!hostMatcher.find()) {
                    throw new URLParserException(URLConstants.INVALID_HOST+dto.getHost());
                }
            }

            //validate Path - Path should not contain "?" and "#"
            if(dto.getPath() != null){
                if(dto.getPath().contains("?") || dto.getPath().contains("#")){
                    throw new URLParserException(URLConstants.INVALID_PATH+dto.getPath());
                }
            }

            //validate query - Query should not contain "#" or "space"
            if(dto.getQuery() != null && (dto.getQuery().contains("#")  || dto.getQuery().contains(" "))){
                throw new URLParserException(URLConstants.INVALID_QUERY+dto.getQuery());
            }

        }


    }

    /**
     *This method is the main method which will ensure that the URL is parsed properly. This method will return a URLParserDTO
     * which will be further validated using the validate method.
     * Split URL example using regex
     * @param url
     * @return
     * @throws URLParserException
     */
    public URLParserDTO parseURL(String url) throws URLParserException {
        URLParserDTO urlDto = null;

        try {
            Pattern pattern = Pattern.compile(URLConstants.REGEX_PATTERN);
            Matcher matcher = pattern.matcher(url);
            matcher.find();
            urlDto = getUrlParserDTO(matcher, urlDto, url);

        } catch (Exception exception) {
            throw new URLParserException(exception.getMessage());
        }
        return urlDto;
    }

    /**
     * This method will get the URLParserDTO from the Matcher class
     * This method will basically set all the fields in this URLParserDTO.
     * @param matcher
     * @param urlDto
     * @param url
     * @return
     */
    private URLParserDTO getUrlParserDTO(Matcher matcher, URLParserDTO urlDto, String url) {

        // get protocol
        String protocol = matcher.group(1);
        // get userInfo
        String userInfo = matcher.group(2);
        String userName = "";
        String password = "";
        if (userInfo != null && !userInfo.isEmpty() && !userInfo.isBlank()) {
            String[] splitUser = userInfo.split(":");
            // get userName
            userName = splitUser[0];
            if (splitUser.length == 2) {
                // get password
                password = splitUser[1];
            }
        }

        // get hostPort
        String hostPort = matcher.group(3);
        String host = "";
        String portNumber = "";
        if (hostPort != null && !hostPort.isEmpty() && !hostPort.isBlank()) {
            String[] splitHostPort = hostPort.split(":");
            // get host
            if (splitHostPort[0] != null) {
                host = splitHostPort[0];
            }
            //get port
            if (splitHostPort.length ==2) {
                portNumber = splitHostPort[1];
            }
        }

        // get port
        int port = 0;
        if (portNumber != null && !portNumber.isBlank() && !portNumber.isEmpty()) {
            port = Integer.parseInt(portNumber);
        }
        // get path
        String path = matcher.group(5);
        // get query
        String query = matcher.group(6);
        // get fragment
        String fragment = matcher.group(7);


        urlDto = new URLParserDTO(protocol, userInfo, userName, password,
                host, port, path, query, fragment, url, hostPort);

        return urlDto;

    }


}
