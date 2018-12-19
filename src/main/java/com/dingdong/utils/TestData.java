package com.dingdong.utils;

import jodd.props.Props;

import java.io.*;

import org.apache.log4j.Logger;

public class TestData {

    private static Logger logger =  Logger.getLogger(HttpClientUtil.class);

    public static String TestIP;
    public static String InsidList;
    public static String TrackCode;
    public static String[] Insid;

    public static int LessTimeOut;
    public static int ShortTimeOut;
    public static int LongTimeOut;

    public static String V1_isDummy;
    public static String V1_bind;
    public static String V1_getOnePhotoUrl;
    public static String V1_updateInsData;


    private static String screenShotURL;
    private static String logURL;
    private static Props Props1;
    private static Props Props2;

    public static final int Talkyou = 1;
    public static final int TalkyouDebug= 2;
    public static final int Doingtone = 3;
    public static final int DoingtoneDebug = 4;

    public static boolean initTestData(int testApp) {

        logger.info("Start get info...");

        try {
            Props1 = buildConfig("/config.properties");
            Props2=buildConfig("/interfacesum.properties");

            if(testApp == Talkyou){
                TestIP = Props1.getValue("PN-Talkyou");
                TrackCode = Props1.getValue("trackCode");

                InsidList = Props1.getValue("Insid");
                Insid= InsidList.split(",");
            }

            else if(testApp == TalkyouDebug){
                TestIP = Props1.getValue("DN-Talkyou");
                TrackCode = Props1.getValue("trackCode");

            }

            else if(testApp == Doingtone){
                TestIP = Props1.getValue("PN-Dingtone");
                TrackCode = Props1.getValue("trackCode");

            }

            else if(testApp == DoingtoneDebug){
                TestIP = Props1.getValue("DN-Dingtone");
                TrackCode = Props1.getValue("trackCode");
            }

            //LessTimeOut = Props.getIntegerValue("lessTimeOut");
            //ShortTimeOut = Props.getIntegerValue("shortTimeOut");
            //LongTimeOut = Props.getIntegerValue("longTimeOut");
            V1_isDummy =  Props2.getValue("v1_isDummy");
            V1_bind = Props2.getValue("v1_bind");
            V1_getOnePhotoUrl = Props2.getValue("v1_getOnePhotoUrl");
            V1_updateInsData = Props2.getValue("v1_updateInsData");

            screenShotURL = "";
            logURL = "";

            logger.info("Success");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static Props buildConfig(String rsc) throws Exception {
        String val = "";
        Props props = new Props();

        // input stream
        InputStream i = TestData.class.getResourceAsStream(rsc);
        BufferedReader r = new BufferedReader(new InputStreamReader(i));

        // reads each line
        String l;
        while((l = r.readLine()) != null) {
            val = val + l + "\n";
        }
        i.close();
        props.load(val);
        return props;
    }

    public static void setScreenShotURL(String screenShotURL) {
        TestData.screenShotURL = screenShotURL;
    }

    public static String getScreenShotURL() {
        return screenShotURL;
    }

    public static void setLogURL(String logURL) { TestData.logURL = logURL; }

    public static String getLogURL() { return logURL; }

    }
