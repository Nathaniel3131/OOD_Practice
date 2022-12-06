package com.company;

import com.company.characters.Knight;
import com.company.characters.Warrior;

public class Main {

    public static void main(String[] args) {
        var army = new Army()
                .addUnits(Warrior::new, 1)
                .addUnits(new Knight())
                .addUnits(new Warrior())
                .addUnits(new Knight());
    }
}
