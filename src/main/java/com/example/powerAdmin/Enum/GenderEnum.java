package com.example.powerAdmin.Enum;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public enum GenderEnum {
    WOMAN(0,"女"),
    MAN(1,"男");
    private Integer code;

    private String message;

    public static Integer convert(String value){
        for (GenderEnum genderEnum : GenderEnum.values()) {
            if (genderEnum.getMessage().equals(value)) {
                return genderEnum.getCode();
            }
        }
        return null;
    }
}
