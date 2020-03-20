package ru.betterstop.headfirst.dotcom;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class GameHelper {

    private static final String alphabet = "abcdefg";
    private int gridLength = 7;
    private int gridSize = 49;
    private int[] grid = new int[gridSize];
    private int comCount = 0;

    public String getUserInput(String prompt){
        String inputLine = null;
        System.out.print(prompt + " ");
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            inputLine = br.readLine();
            if(inputLine.length() == 0) return null;
        }catch (IOException e){
            System.out.println("IOException: " + e);
        }
        return inputLine.toLowerCase();
    }

    public ArrayList<String> placeDotCom(int comSize){
        ArrayList<String> alphaCells = new ArrayList<>();
        String[] alphacoords = new String[comSize];
        String temp = null;
        int[] coords = new int[comSize];
        int attempts = 0;
        boolean success = false;
        int location = 0;

        comCount++;
        int incr = 1;
        if ((comCount % 2) == 1){
            incr = gridLength;
        }

        while (!success & attempts++ < 200){
            location = (int) (Math.random() * gridSize);
            int x = 0;
            success = true;
            while (success && x < comSize){
                if (grid[location] == 0){
                    coords[x++] = location;
                    location += incr;
                    if (location >= gridSize){
                        success = false;
                    }
                    if (x > 0 && (location % gridLength == 0)){
                        success = false;
                    }
                } else {
                    success = false;
                }
            }
        }
        int x = 0;
        int row = 0;
        int column = 0;
        while (x < comSize){
            grid[coords[x]] = 1;
            row = (int) (coords[x] / gridLength);
            column = coords[x] % gridLength;
            temp = String.valueOf(alphabet.charAt(column));

            alphaCells.add(temp.concat(Integer.toString(row)));
            x++;
        }
        return alphaCells;
    }

    public int getGridLength(){
        return gridLength;
    }
    public int[] getGrid(){
        return grid;
    }

    public void takeShot(String userGuid){
        int shot = Integer.parseInt(String.valueOf(userGuid.charAt(1))) * gridLength;
        if (userGuid.charAt(0) == 'a') shot += 0;
        if (userGuid.charAt(0) == 'b') shot += 1;
        if (userGuid.charAt(0) == 'c') shot += 2;
        if (userGuid.charAt(0) == 'd') shot += 3;
        if (userGuid.charAt(0) == 'e') shot += 4;
        if (userGuid.charAt(0) == 'f') shot += 5;
        if (userGuid.charAt(0) == 'g') shot += 6;
        if (grid[shot] == 0) grid[shot] = 3;
        else if (grid[shot] == 1) grid[shot] = 2;
    }
}
