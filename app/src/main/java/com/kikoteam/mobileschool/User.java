package com.kikoteam.mobileschool;

public class User {


    private static User instance = null;

    private String classroomCode;

    public int getNumOfLessons() {
        return numOfLessons;
    }

    public void setNumOfLessons(int numOfLessons) {
        this.numOfLessons = numOfLessons;
    }

    public int getNumOfHomeworks() {
        return numOfHomeworks;
    }

    public void setNumOfHomeworks(int numOfHomeworks) {
        this.numOfHomeworks = numOfHomeworks;
    }

    public int getNumOfTests() {
        return numOfTests;
    }

    public void setNumOfTests(int numOfTests) {
        this.numOfTests = numOfTests;
    }

    private int numOfLessons;
    private int numOfHomeworks;
    private int numOfTests;

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
        numOfHomeworks = 0;
        numOfLessons = 0;
        numOfTests = 0;
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
