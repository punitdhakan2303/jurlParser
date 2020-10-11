package com.challengeone.wcs;

import com.challengeone.wcs.dto.URLParserDTO;
import com.challengeone.wcs.exception.URLParserException;
import com.challengeone.wcs.parser.URLParserImpl;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * @author
 * This jUnit will to test the Main class
 */
class MainTest {

    /**
     * This jUnit will parse the URL and return URLParserDTO
     * @throws URLParserException
     */
    @Test
    void main() throws URLParserException {

        URLParserDTO parsedDTO = new URLParserImpl().parseAndValidate("https://tim:abc123@facebook.com:9191/tim/profile/?dob=12Jan&gender=male#coverpage");

        assertEquals("https", parsedDTO.getProtocol());
        assertEquals("tim:abc123", parsedDTO.getUserInfo());
        assertEquals("tim", parsedDTO.getUserName());
        assertEquals("abc123", parsedDTO.getPassword());
        assertEquals("facebook.com", parsedDTO.getHost());
        assertEquals(9191, parsedDTO.getPort());
        assertEquals("/tim/profile/", parsedDTO.getPath());
        assertEquals("dob=12Jan&gender=male", parsedDTO.getQuery());
        assertEquals("coverpage", parsedDTO.getFragment());
    }
}