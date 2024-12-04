package com.zamecki.Podcastly.CustomContainers;


import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
public class CustomDate {
    private final String dateTime;

    public CustomDate(){
        LocalDateTime currDateObj = LocalDateTime.now();
        DateTimeFormatter formatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        dateTime = currDateObj.format(formatObj);
    }

}
