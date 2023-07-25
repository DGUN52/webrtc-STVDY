package com.ssafy.ssap.dto;

import lombok.Getter;

@Getter
public class RoomDto {

    private Long userNo;
    private String title;
    private long endHour;
    private long endMinute;
    private int quota;
    private boolean isPrivacy;
    private String password;
    private String imagePath;
    private String rule;

}
