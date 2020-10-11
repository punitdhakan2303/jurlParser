package com.challengeone.wcs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Punit Dhakan
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class URLParserDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private  String protocol;
    private  String userInfo;
    private  String userName;
    private  String password;
    private  String host;
    private  int port;
    private  String path;
    private  String query;
    private  String fragment;
    private  String url;
    private  String hostPort;


    @Override
    public String toString() {
        return "URLParserDTO{" +
                "protocol='" + protocol + '\'' +
                ", userInfo='" + userInfo + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", host='" + host + '\'' +
                ", port=" + port +
                ", path='" + path + '\'' +
                ", query='" + query + '\'' +
                ", fragment='" + fragment + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
