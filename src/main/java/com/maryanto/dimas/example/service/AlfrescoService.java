package com.maryanto.dimas.example.service;

import com.maryanto.dimas.example.model.AlfrescoLoginRequest;
import com.maryanto.dimas.example.model.AlfrescoLoginResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class AlfrescoService {

    @Value("${alfresco.schema}")
    private String schema;
    @Value("${alfresco.host}")
    private String host;
    @Value("${alfresco.port}")
    private String port;
    @Value("${alfresco.admin.username}")
    private String username;
    @Value("${alfresco.admin.password}")
    private String password;

    @Autowired
    private RestTemplate restApi;

    /**
     * curl -X POST --header 'Content-Type: application/json' --header 'Accept: application/json' --header 'Authorization: Basic YWRtaW46YWRtaW4=' -d '{
     * "userId": "admin",
     * "password": "admin"
     * }' 'http://localhost:8090/alfresco/api/-default-/public/authentication/versions/1/tickets'
     *
     * @param username
     * @param password
     */
    public ResponseEntity<AlfrescoLoginResponse> login(String username, String password) throws RestClientException {
        Map<String, String> pathVariables = new HashMap<>();
        pathVariables.put("version", "1");

        UriComponents uri = UriComponentsBuilder
                .newInstance()
                .scheme(this.schema)
                .host(this.host)
                .port(this.port)
                .path("/alfresco/api/-default-/public/authentication/versions/{version}/tickets")
                .buildAndExpand(pathVariables);

        log.info("url request -> {}", uri.toUriString());

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON_UTF8));
        headers.setBasicAuth(username, password);
        HttpEntity<AlfrescoLoginRequest> requestEntity = new HttpEntity<>(
                new AlfrescoLoginRequest(
                        username,
                        password
                ),
                headers);

        log.info("request body -> {}", requestEntity.getBody());
        log.info("request header -> {}", requestEntity.getHeaders());

        return restApi.exchange(
                uri.toUriString(),
                HttpMethod.POST,
                requestEntity,
                AlfrescoLoginResponse.class);
    }
}
