package com.iaminca;


import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

//@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class)
//@TestPropertySource(locations="classpath:application-dev.yml")
public class OpenaiApplicationTests {

}
