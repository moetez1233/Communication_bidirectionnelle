package com.hasee.websocket.servicesImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class TestComponent implements Runnable {
    private final Logger logger= LoggerFactory.getLogger(TestComponent.class);
    @Override
    public void run() {
        logger.info("i'm runing always");
    }
}
