package com.zamecki.Podcastly.CustomContainers;


import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
//TODO: należy zdefiniować samemu konstruktor, walidować format ustawianego datetime
@AllArgsConstructor
public class CustomDate {
    private String dateTime;

    public CustomDate(){
        LocalDateTime currDateObj = LocalDateTime.now();
        DateTimeFormatter formatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        dateTime = currDateObj.format(formatObj);
    }

}
