package com.service.MainService.entity;

import lombok.Getter;

@Getter
public enum TrancheSalariale {
    T1("[0 ; 30.000[", 10),
    T2("[30.000 ; 100.000[", 20),
    T3("[100.000 ; 200.000[", 30),
    T4("[200.000 ; 700.000[", 40),
    T5("[700.000 ; 1.000.000[", 45),
    T6("[1.000.000 ; plus [", 55);

    private final String label;
    private final int points;

    TrancheSalariale(String label, int points) {
        this.label = label;
        this.points = points;
    }

}