package com.suprun.seaBattleGame.entity;

import java.util.Random;

public class RandomIntelligence {
    private SeaMap map;
    private SeaMap radar;

    public RandomIntelligence(SeaMap map, SeaMap radar) {
        this.map = map;
        this.radar = radar;
    }

    public boolean shoot() {
        Random random = new Random();
        int x = random.nextInt(16);
        int y = random.nextInt(16);

        if (radar.isFreeEnterCell(x, y)) {
            return radar.registerShot(x, y);
        }

        shoot();
        return false;
    }
}
