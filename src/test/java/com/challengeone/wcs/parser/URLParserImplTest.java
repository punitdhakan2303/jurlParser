package com.challengeone.wcs.parser;

import static org.junit.jupiter.api.Assertions.*;

import com.challengeone.wcs.dto.URLParserDTO;
import com.challengeone.wcs.exception.URLParserException;
import org.junit.jupiter.api.Test;

/**
 * @author Punit Dhakan
 * This jUnit will to test the URLParserImplTest class in which there are 3 methods.
 */
class URLParserImplTest {

    /**
     * This method will test the parseAndValidate by passing the entire URL.
     * @throws URLParserException
     */
    @Test
    void parseAndValidate() throws URLParserException {
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

    /**
     * This method will test the parseAndValidate by passing the entire URL without Username and Password which are not mandatory.
     * @throws URLParserException
     */
    @Test
    void parseAndValidateNoUserNamePassword() throws URLParserException {
        URLParserDTO parsedDTO = new URLParserImpl().parseAndValidate("https://facebook.com:9191/tim/profile/?dob=12Jan&gender=male#coverpage");

        assertEquals("https", parsedDTO.getProtocol());
        assertEquals("facebook.com", parsedDTO.getHost());
        assertEquals(9191, parsedDTO.getPort());
        assertEquals("/tim/profile/", parsedDTO.getPath());
        assertEquals("dob=12Jan&gender=male", parsedDTO.getQuery());
        assertEquals("coverpage", parsedDTO.getFragment());
    }

    /**
     * This method will test the parseAndValidate by passing the URL with only Host, Protocol and port
     * @throws URLParserException
     */
    @Test
    void parseAndValidateOnlyProtocolHostPort() throws URLParserException {
        URLParserDTO parsedDTO = new URLParserImpl().parseAndValidate("https://facebook.com:9191");

        assertEquals("https", parsedDTO.getProtocol());
        assertEquals("facebook.com", parsedDTO.getHost());
        assertEquals(9191, parsedDTO.getPort());
    }

    /**
     * This jUnit will test the validate method by passing only valid protocol and host.
     * @throws Exception
     */
    @Test
    void validateWithProtocolHost() throws Exception {
        URLParserDTO parsedDTO = new URLParserDTO();
        parsedDTO.setHost("facebook.com");
        parsedDTO.setProtocol("https");

        new URLParserImpl().validate(parsedDTO);

    }

    /**
     * This jUnit will check for the protocol mandatory condition and it will throw an exception
     * @throws URLParserException
     */
    @Test
    void validateWithoutProtocol() throws URLParserException {
        URLParserDTO parsedDTO = new URLParserDTO();
        parsedDTO.setHost("facebook.com");
        parsedDTO.setUserInfo("tim:abc123");
        parsedDTO.setUserName("tim");
        parsedDTO.setPassword("abc123");
        parsedDTO.setPort(9191);
        parsedDTO.setQuery("dob=12Jan&gender=male");
        parsedDTO.setFragment("coverpage");
        assertThrows(URLParserException.class, () -> new URLParserImpl().validate(parsedDTO));
    }

    /**
     * This jUnit will check for the host mandatory condition and it will throw an exception
     * @throws URLParserException
     */
    @Test
    void validateWithoutHost() throws URLParserException {
        URLParserDTO parsedDTO = new URLParserDTO();
        parsedDTO.setProtocol("https");
        parsedDTO.setUserInfo("tim:abc123");
        parsedDTO.setUserName("tim");
        parsedDTO.setPassword("abc123");
        parsedDTO.setPort(9191);
        parsedDTO.setQuery("dob=12Jan&gender=male");
        parsedDTO.setFragment("coverpage");
        assertThrows(URLParserException.class, () -> new URLParserImpl().validate(parsedDTO));
    }

    /**
     * This jUnit will test passing the invalid protocol "123" and throws and exception.
     * @throws URLParserException
     */
    @Test
    void validateInvalidProtocol() throws URLParserException {
        URLParserDTO parsedDTO = new URLParserDTO();
        parsedDTO.setProtocol("123");
        assertThrows(URLParserException.class, () -> new URLParserImpl().validate(parsedDTO));
    }

    /**
     * This jUnit will test passing the invalid userInfo "tim123#" and throws and exception.
     * @throws URLParserException
     */
    @Test
    void validateInvalidUserInfo() throws URLParserException {
        URLParserDTO parsedDTO = new URLParserDTO();
        parsedDTO.setHost("facebook.com");
        parsedDTO.setProtocol("https");
        parsedDTO.setUserInfo("tim123#");
        assertThrows(URLParserException.class, () -> new URLParserImpl().validate(parsedDTO));
    }

    /**
     * This jUnit will test passing the invalid hostport combination "[123.45.65:9191]" and throws and exception.
     * @throws URLParserException
     */
    @Test
    void validateInvalidHostPort() throws URLParserException {
        URLParserDTO parsedDTO = new URLParserDTO();
        parsedDTO.setHost("123.45.65");
        parsedDTO.setHostPort("[123.45.65:9191]");
        parsedDTO.setProtocol("https");
        parsedDTO.setPort(9191);
        assertThrows(URLParserException.class, () -> new URLParserImpl().validate(parsedDTO));
    }


    /**
     * This jUnit will parse the URL and return URLParserDTO
     * @throws URLParserException
     */
    @Test
    void parseURL() throws URLParserException {

        URLParserDTO parsedDTO = new URLParserImpl().parseURL("https://tim:abc123@facebook.com:9191/tim/profile/?dob=12Jan&gender=male#coverpage");

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

    /**
     * This jUnit will parse the URL and return URLParserDTO by setting only protocol, host and port.
     * @throws URLParserException
     */
    @Test
    void parseURLOnlyProtocolHostPort() throws URLParserException {
        URLParserDTO parsedDTO = new URLParserImpl().parseURL("https://facebook.com:9191");

        assertEquals("https", parsedDTO.getProtocol());
        assertEquals("facebook.com", parsedDTO.getHost());
        assertEquals(9191, parsedDTO.getPort());
    }
}