package com.challengeone.wcs.constants;

/**
 * @author Punit Dhakan
 */
public class URLConstants {
    //prompt messages
    public static final String ENTER_MSG = "Please enter the URI for the program to parse in the below format";
    public static final String EXPECTED_FORMAT = "protocol://user:password@host:8080/path/document?arg1=val1&arg2=val2#part";
    public static final String VALID_URL ="You entered url ";
    public static final String INPUT_URL = "Need an URL as input ";


    // Regex pattern constants
    /**
     * This pattern can be explained as below
     *
     * (?:([^:/?#]+):)?                # protocol
     *     (?://                       # authority
     *         (?:([^/?\#@]*)@)?       # userinfo
     *         ([^/?\#]*)()            # host:port
     *     )?
     *     ([^?\#]*)                   # path
     *     \??([^\#]*)                 # query
     *     \#?(.*)                     # fragment
     *
     */
    public static final String REGEX_PATTERN ="(?:([^:/?#]+):)?(?://(?:([^/?\\#@]*)@)?([^/?\\#]*)())?([^?\\#]*)\\??([^\\#]*)\\#?(.*)";


    /**
     * To validate the Protocol using this pattern
     */
    public static final String REGEX_PATTERN_PROTOCOL = "^[a-z][a-z0-9+\\-.]*$";
    /**
     * To validate the userInfo using this pattern
     */
    public static final String REGEX_PATTERN_USERINFO = "^[^/?\\#@\\[\\]]+$";
    /**
     * To validate the hostname using this pattern
     */
    public static final String REGEX_PATTERN_HOSTNAME = "^[^/?\\#@\\[\\]:]+$";
    /**
     * To validate the Host and port using this pattern
     */
    public static final String REGEX_PATTERN_IP = "^(?:\n" +
            "            v[a-f0-9]+\\.[a-z0-9\\-._~!$&'()*,;=:]+\n" +
            "            |\n" +
            "            [a-f0-9:\\.]+\n" +
            "        )$";


    //Error messages
    public static final String INVALID_PROTOCOL = "The protocol entered is empty or not valid ";
    public static final String INVALID_USERINFO = "The User Info entered is not valid ";
    public static final String INVALID_HOST_PORT = "The Host and Port entered is not valid ";
    public static final String INVALID_HOST = "The Host entered is empty or not valid ";
    public static final String INVALID_PATH = "The Path entered is not valid ";
    public static final String INVALID_QUERY = "The Query entered is not valid ";

}
