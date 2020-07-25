package com.kikoteam.mobileschool;

public class User {


    private static User instance = null;

    private String classroomCode;

    public Boolean getHasVerifiedClass() {
        return hasVerifiedClass;
    }

    public void setHasVerifiedClass(Boolean hasVerifiedClass) {
        this.hasVerifiedClass = hasVerifiedClass;
    }

    private Boolean hasVerifiedClass;

    private User (){
        classroomCode = null;
        hasVerifiedClass = false;
    }

    public String getClassroomCode() {
        return classroomCode;
    }

    public void setClassroomCode(String classroomCode) {
        this.classroomCode = classroomCode;
    }

    public static void resetInstance(){
        instance = null;
    }

    public static User getInstance() {
        if (instance == null)
            instance = new User();
        return instance;
    }

}
