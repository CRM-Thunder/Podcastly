package com.zamecki.Podcastly.CustomContainers;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
@Builder
@Getter
@Setter
public class CustomDate {
    final private String dateTime;

    public CustomDate(){
        LocalDateTime currDateObj = LocalDateTime.now();
        DateTimeFormatter formatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        dateTime = currDateObj.format(formatObj);
    }

}
