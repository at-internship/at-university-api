package com.agilethought.internship.university.service.common;

public enum CategoryConstants {
    JAVA(1),
    PEGA(2),
    JS(3);

    private int ord;

    CategoryConstants(int ord) {
        this.ord = ord;
    }
    public int getOrd() {
        return this.ord;
    }

    public static String getCategory(Integer ord){
        for(CategoryConstants c:CategoryConstants.values()){
            if(c.getOrd()==ord){
                return c.name();
            }
        }
        return("UNKNOWN");
    }
}

