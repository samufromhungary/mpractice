package com.codecool;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Locker {

    static int totalSlots = 30;
    static int freeSlots;
    static Gun[] gunList;

    Locker(int totalSlots) {
        this.totalSlots = totalSlots;
        this.freeSlots = freeSlots;
    }

    public static void fillGuns() throws IOException{
        String fileName = "src/main/data/weapons.csv";
        File sourceFile = new File(fileName);
        int itemCounter = 1;
        if (sourceFile.exists()) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(sourceFile));
                int lineCounter = 0;
                while (reader.readLine() != null) lineCounter++;
                reader.close();

                switch (lineCounter) {
                    case 0:
                        System.out.println("The file that has the list of the weapons is empty. Oof.");
                        System.exit(-1);
                        break;
                    default:    gunList = new Gun[totalSlots];
                        for(int i = 0; i < totalSlots; i++) {
                            int randomItem = Common.random(1, lineCounter);
                            Scanner sc = new Scanner(sourceFile);
                            int findLine = 1;
                            while (sc.hasNext()) {
                                String sourceLine = sc.next();
                                if(findLine == randomItem) {
                                    String gunType = sourceLine.split(",")[0];
                                    String gunName = sourceLine.split(",")[1];
                                    int gunPrice = Integer.parseInt(sourceLine.split(",")[2]);


                                    if (!sourceLine.split(",")[0].equals("")) {
                                        gunList[i] = new Gun(gunType, gunName, gunPrice);
                                        itemCounter++;
                                    }
                                }
                                findLine++;
                            }
                        }
                        break;
                }

            }
            catch(FileNotFoundException e) {
                System.out.println("Exception error: FileNotFound");
            }
        }
    }

    public Gun[] getGunList() {
        return gunList;
    }
}
