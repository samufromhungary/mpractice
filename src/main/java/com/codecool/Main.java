package com.codecool;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static boolean isLockerOpen = false;
    public static String gameMenuType = "main";
    public static int menuSelect;

    public static String name;
    public static double money;
    private static Locker locker;
    static Gun[] gunList;
    static Gun[] ownedGuns = new Gun[30];


    public static void main(String[] args) throws NumberFormatException, IOException {
        clearScreen();
        System.out.println("Weapon locker v1");
        System.out.println("\n(1) New game\n(2) Quit");
        Scanner scanner = new Scanner(System.in);
        while (true) {
                menuSelect = Integer.parseInt(scanner.nextLine());
                switch(menuSelect) {
                    case 1: newGame();
                    break;
                    case 2: clearScreen();
                        System.out.println("\nSee ya' around!\n");
                        System.exit(-1);
                        break;
                    default:
                        System.out.println("Not a valid option.");
                        break;
                }
        }
    }

    public static void newGame() throws IOException {

        Scanner scanner = new Scanner(System.in);
        clearScreen();
        System.out.println("\nOh, a new player!");
        System.out.println("\nWhat is your name?\n ");
        String name = scanner.nextLine();
        if(name.equals("")) {
            name = "Unknown Soldier";
        }
        Main.name = name;
        money = Common.random(100, 10000) * Common.random(1, 10);
        Locker.fillGuns();
        letStart();
        System.out.println("You run outta money. See ya next time!");
        System.exit(0);
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void letStart() {
        Player player = new Player(name, money);
        while(money >= 5 ) {
            clearScreen();
            System.out.println("Name: " + name + " | Money: " + money + "\n\n");
            gamemMenu();
        }
    }

    public static void gamemMenu() {
        if(gameMenuType == "main") {
            System.out.println("What do you wanna do now?\n(1) Buy weapons\n(2) Open/Close Locker\n(3) Check Locker status\n(4) Check your status(not implemented)\n(5) Exit");
            Scanner scanner = new Scanner(System.in);
            try {
                menuSelect = Integer.parseInt(scanner.nextLine());
                switch(menuSelect) {
                    case 1: if(isLockerOpen) gameMenuType = "gunBuyer";
                    else System.out.println("Locker is closed.");
                        break;
                    case 2: if(isLockerOpen) {
                        isLockerOpen = false;
                        System.out.println("You closed the locker.");
                    }
                    else {
                        isLockerOpen = true;
                        System.out.println("You opened the locker.");
                    }
                        break;
                    case 3: if(isLockerOpen) {
                        System.out.println("The locker is open.");
                    }
                    else {
                        System.out.println("The locker is closed.");
                    }
                        break;
                    case 4:
                        /*System.out.println("Name: " + name + " | Money: " + money + "$.");
                        if(ownedGuns.length == 0){
                            System.out.println("You don't have any guns right now. Go buy some.");
                        }
                        else {
                            System.out.println("You have these guns:");
                            for(int counter = 0; counter < ownedGuns.length; counter++){
                            }
                        } */
                        System.out.println("Not yet implemented.");
                        gameMenuType = "main";
                    case 5: clearScreen();
                        System.out.println("\nSee ya' around!\n");
                        System.exit(-1);
                        break;
                    default:
                        System.out.println("Not a valid option.");
                        break;
                }
            }
            catch(NumberFormatException e) {
                System.out.println("It's not a valid NUMBER");
            }
        }
        else if(gameMenuType == "gunBuyer") {
            System.out.println("Choose one of the followings:");

            Locker l = new Locker(Locker.totalSlots);
            for (Gun gun : l.getGunList()) {
                System.out.println("Type: " + gun.getType() + " | " + "Name: " + gun.getName().replace("_", " ") + " | " + "Price: " + gun.getPrice() + "$");
            }

            System.out.println("\nWhat do you want to buy?");
            Scanner scanner = new Scanner(System.in);
            String gunSelect = scanner.nextLine();

            if(gunSelect.equals("") || gunSelect.equals(" ")) {
                System.out.println("Blank gun? Are you joking?");

            }
            else {
                boolean gunFound = false;
                for (Gun gun : l.getGunList()) {
                    String searchGun = gunSelect.toLowerCase();
                    String gunInLocker = gun.getName().toLowerCase();
                    if(gunInLocker.replace(" ", "_").contains(searchGun)) gunFound = true;
                }
                if(gunFound == false) {
                    System.out.println('"' +  gunSelect + '"' + "You can't buy that.");
                }

                if(gunFound) {
                    Gun[] tempGunList = new Gun[Locker.gunList.length - 1];
                    int listPosCounter = 0;
                    boolean searchMore = true;
                    for (Gun gun : l.getGunList()) {
                        String searchGun = gunSelect.toLowerCase();
                        String gunInLocker = gun.getName().toLowerCase();
                        if(gunInLocker.contains(searchGun) && searchMore == true) {
                            if (money < gun.getPrice()) {
                                System.out.println("This gun is cost more than you have");
                                listPosCounter--;
                                searchMore = false;
                            } else {
                                System.out.println("You bought one " + gun.getName().replace("_", " "));
                                money = money - gun.getPrice();
                                listPosCounter--;
                                searchMore = false;
                                Locker.totalSlots = tempGunList.length;
                            }
                        }
                        else {
                            tempGunList[listPosCounter] = gun;
                        }
                        listPosCounter++;
                    }
                    Locker.gunList = tempGunList;
                }
            }
            gameMenuType = "main";
        }
    }

}
