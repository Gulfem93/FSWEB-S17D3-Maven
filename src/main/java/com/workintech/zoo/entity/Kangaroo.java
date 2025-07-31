package com.workintech.zoo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Kangaroo {

    public Kangaroo(int par, String kenny, double par1, double par2, double par3, String male, boolean par4) {
    }
    private Integer id;
    private String name;
    private Double height;
    private Double weight;
    private Double sleepHour;
    private String gender;
    private Boolean isAggressive;

    


}
