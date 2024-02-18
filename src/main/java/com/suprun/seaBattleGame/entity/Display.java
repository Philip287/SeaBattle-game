package com.suprun.seaBattleGame.entity;


import com.suprun.seaBattleGame.service.ColorGame;
import com.suprun.seaBattleGame.service.MessageHelper;

import java.io.IOException;
import java.util.HashMap;


public class Display {
    private final String columns = "   A B C D E F G H I J K L M N O P\n";
    public final String row = "%2d %s %s %s %s %s %s %s %s %s %s %s %s %s %s %s %s\n";

    private final HashMap<Integer, String> mapSymbols = new HashMap<>() {{
        put(0, " ");  // empty cell
        put(1, ColorGame.setColor("■", ColorGame.blue));  // ship
        put(2, ColorGame.setColor("□", ColorGame.yellow)); // hit ship
        put(3, ColorGame.setColor("X", ColorGame.red)); // wrecked ship
        put(4, ColorGame.setColor("*", ColorGame.white)); // slip

    }};

    private final HashMap<Integer, String> radarSymbols = new HashMap<>() {{
        put(0, " ");  // empty cell
        put(1, " ");  // ship
        put(2, ColorGame.setColor("□", ColorGame.yellow)); // hit ship
        put(3, ColorGame.setColor("X", ColorGame.red)); // wrecked ship
        put(4, ColorGame.setColor("*", ColorGame.white)); // slip
    }};

    public void clearScreen() throws IOException, InterruptedException {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } else if (os.contains("nix") || os.contains("nux")) {
            new ProcessBuilder("terminal", "/c", "clear").inheritIO().start().waitFor();
        }
        MessageHelper.writeMessage(" ");
    }

    public void displayMap(SeaMap map) {
        StringBuilder mapImage = new StringBuilder();
        mapImage.append(columns);
        for (int i = 0; i < map.size()[0]; i++) {
            mapImage.append(String.format(row, i + 1,
                    mapSymbols.get(map.getCell(i, 0)), mapSymbols.get(map.getCell(i, 1)),
                    mapSymbols.get(map.getCell(i, 2)), mapSymbols.get(map.getCell(i, 3)),
                    mapSymbols.get(map.getCell(i, 4)), mapSymbols.get(map.getCell(i, 5)),
                    mapSymbols.get(map.getCell(i, 6)), mapSymbols.get(map.getCell(i, 7)),
                    mapSymbols.get(map.getCell(i, 8)), mapSymbols.get(map.getCell(i, 9)),
                    mapSymbols.get(map.getCell(i, 10)), mapSymbols.get(map.getCell(i, 11)),
                    mapSymbols.get(map.getCell(i, 12)), mapSymbols.get(map.getCell(i, 13)),
                    mapSymbols.get(map.getCell(i, 14)), mapSymbols.get(map.getCell(i, 15))));
        }
        mapImage.append("\n");
        MessageHelper.writeMessage(String.valueOf(mapImage));
    }

    public void displayRadar(SeaMap radar) {
        StringBuilder radarImage = new StringBuilder();
        radarImage.append(columns);
        for (int i = 0; i < radar.size()[0]; i++) {
            radarImage.append(String.format(row, i + 1,
                    radarSymbols.get(radar.getCell(i, 0)), radarSymbols.get(radar.getCell(i, 1)),
                    radarSymbols.get(radar.getCell(i, 2)), radarSymbols.get(radar.getCell(i, 3)),
                    radarSymbols.get(radar.getCell(i, 4)), radarSymbols.get(radar.getCell(i, 5)),
                    radarSymbols.get(radar.getCell(i, 6)), radarSymbols.get(radar.getCell(i, 7)),
                    radarSymbols.get(radar.getCell(i, 8)), radarSymbols.get(radar.getCell(i, 9)),
                    radarSymbols.get(radar.getCell(i, 10)), radarSymbols.get(radar.getCell(i, 11)),
                    radarSymbols.get(radar.getCell(i, 12)), radarSymbols.get(radar.getCell(i, 13)),
                    radarSymbols.get(radar.getCell(i, 14)), radarSymbols.get(radar.getCell(i, 15))));
        }
        radarImage.append("\n");
        MessageHelper.writeMessage(String.valueOf(radarImage));
    }
}
