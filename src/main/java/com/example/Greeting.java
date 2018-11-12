package com.example;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.charset.StandardCharsets;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class Greeting {
    // Set resources bundle
    private static ResourceBundle bundle = ResourceBundle.getBundle("greetings");
    //Logger
    private static final Logger logger = LogManager.getLogger(Greeting.class);
    // Set time intervals
    private static final LocalTime MORNING = LocalTime.of(6, 0, 0);
    private static final LocalTime DAY_TIME = LocalTime.of(9, 0, 0);
    private static final LocalTime EVENING = LocalTime.of(19, 0, 0);
    private static final LocalTime NIGHT = LocalTime.of(23, 0, 0);
    private LocalTime now;

    public Greeting() {
        now = LocalTime.now();
        logger.info("Current locale: " + bundle.getLocale());
        logger.info("Set current time: " + now);
    }

    // method, which print greetings
    public void print() {
        if (between(MORNING, DAY_TIME))
            System.out.println(toUTF8(bundle.getString("good_morning")));
        else if (between(DAY_TIME, EVENING))
            System.out.println(toUTF8(bundle.getString("good_day")));
        else if (between(EVENING, NIGHT))
            System.out.println(toUTF8(bundle.getString("good_evening")));
        else System.out.println(toUTF8(bundle.getString("good_night")));
    }

    // Check if time between range
    protected boolean between(LocalTime from, LocalTime to) {
        if (from.isAfter(to)) {
            // Return true if the time is after (or at) 'from', *or* it's before 'to'
            return now.compareTo(from) >= 0 ||
                    now.compareTo(to) < 0;
        } else {
            return from.compareTo(now) <= 0 &&
                    now.compareTo(to) < 0;
        }
    }

    // Uncomment only for Tests
    public void setNow(LocalTime now) {
        this.now = now;
        logger.info("Set time " + now);
    }

    public LocalTime getNow() {
        return now;
    }

    //Set Charset to UTF-8
    private String toUTF8(String s){
        return new String(s.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
    }
}
