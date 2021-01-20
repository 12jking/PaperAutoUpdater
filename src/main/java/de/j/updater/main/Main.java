package de.j.updater.main;

import de.j.updater.util.Paper;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        try {
            Paper paper = new Paper();
            System.out.println("The newest version is " + paper.getNewestVersion());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
