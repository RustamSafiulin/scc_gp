package me.dehasi.contracts.demo.contract;

import io.restassured.RestAssured;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import me.dehasi.contracts.demo.SccDemoApplication;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = SccDemoApplication.class)
@AutoConfigureStubRunner(ids = {"me.dehasi.contracts.demo:sub-service-stubs:+:stubs:8090"}, stubsMode = StubRunnerProperties.StubsMode.LOCAL)
public abstract class ContractBase {
    @LocalServerPort
    int port;
    @Autowired
    protected WebApplicationContext webApplicationContext;

    @Before
    public void configureRestAssured() {
        RestAssured.port = port;
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .build();
        RestAssuredMockMvc.mockMvc(mockMvc);
    }
}
