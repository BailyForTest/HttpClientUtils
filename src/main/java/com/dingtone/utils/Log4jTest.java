package com.dingtone.utils;


import org.apache.log4j.Logger;

public class Log4jTest {

    public static Logger logger = Logger.getLogger(Log4jTest.class);
    private static  Logger log = Logger.getLogger("myLog");
    public static void main(String[] args){
        logger.debug("this is debug message");

        logger.info("this is info message");

        logger.info("this is warn message");

        logger.error("this is error message");

        log.info("hello");

    }

}
