package com.codecool;
import java.util.Random;

public class Common {

    boolean isLockerOpen;
    String gamemMenuType;

    public static int random(int from, int to) {
        Random r = new Random();
        int result = r.nextInt((to+1)-from) + from;
        return result;
    }

    public void isLockerOpen() {
        if(isLockerOpen) {
            System.out.println("Locker is open.");
        }
        else {
            System.out.println("Locker is closed.");
        }
    }

    public void useLockerDoor() {
        if(isLockerOpen) {
            isLockerOpen = false;
            System.out.println("You closed the locker.");
        }
        else {
            isLockerOpen = true;
            System.out.println("You opened the locker");
        }
    }
}
