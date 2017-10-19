package com.avenuecode.test.avenuecodetest;


import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Import(AvenueCodeTestApplicationTests.class)
@ActiveProfiles("h2")
public abstract class AbstractTest {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

}