package org.sunc.talkig;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class TalkigApplication {

    public static void main(String[] args) {
        SpringApplication.run(TalkigApplication.class, args);
        log.info("项目启动成功...");
    }

}
