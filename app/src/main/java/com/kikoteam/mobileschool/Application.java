package com.kikoteam.mobileschool;

public class Application {
    ///singleton class
    ///use it to check if app and user information are INITIALIZED

    private boolean isInitialized;

    private static Application instance = null;

    private Application(){
        isInitialized = false;
    }

    public boolean isInitialized() {
        return isInitialized;
    }

    public void setInitialized(boolean initialized) {
        isInitialized = initialized;
    }

    public static Application getInstance(){
        if (instance == null) {
            instance = new Application();
        }
        return instance;
    }
}
