package com.maryanto.dimas.example.test;

import com.maryanto.dimas.example.model.AlfrescoLoginResponse;
import com.maryanto.dimas.example.service.AlfrescoService;
import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Slf4j
public class TestServiceAlfresco extends TestCase {

    @Autowired
    private AlfrescoService service;

    @Override
    protected void setUp() throws Exception {
//        super.setUp();
    }

    @Test
    public void testLoginAlfresco() {
        ResponseEntity<AlfrescoLoginResponse> entity = service.login("admin", "admin");
        assertTrue(entity.getStatusCode().is2xxSuccessful());

        AlfrescoLoginResponse data = entity.getBody();
//        check not null
        assertNotNull(data.getEntry());
//        check tiket generated
        assertNotNull(data.getEntry().getId());
        log.info("{}", data);
    }
}
