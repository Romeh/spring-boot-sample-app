package com.test.sampleapp.sanity;


import com.test.sampleapp.retry.RetryRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import com.test.sampleapp.retry.Retry;

import static org.junit.Assert.assertTrue;


public class ApplicationSanityCheck_ITT {
    @Rule
    public final RetryRule retry = new RetryRule();
    private int port = 8080;
    private RestTemplate template;
    private URL base;

    @Before
    public void setUp() throws Exception {
        this.base = new URL("http://localhost:" + port + "/");
        // disabled proxy config to run locally
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        // just added for showing how to configure the proxy
        // Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("userproxy.glb.ebc.local", 8080));
        //requestFactory.setProxy(proxy);
        template = new RestTemplate(requestFactory);


    }

    // and retry in case of failure 3 times with 20 seconds delay between each try
    @Test
    @Retry(times = 3, timeout = 20000)
    public void test_is_server_up() {
        assertTrue(template.getForEntity(base + "/actuator/health", String.class).getStatusCode().is2xxSuccessful());

    }


}
