package com.challengeone.wcs.parser;

import com.challengeone.wcs.dto.URLParserDTO;
import com.challengeone.wcs.exception.URLParserException;

/**
 * @author Punit Dhakan
 */
public interface URLParserInterface {

    /**
     * @return URLParserDTO
     * @param url
     * @throws URLParserException
     */
    public URLParserDTO parseAndValidate(String url) throws URLParserException;

}
