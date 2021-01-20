package de.j.updater.main;

import de.j.updater.util.Paper;
import de.j.updater.util.Server;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        try {
            Paper paper = new Paper("1.16.5");
            System.out.println("Checking version...");
            System.out.println("The newest version is #" + paper.getCurrentVersion());
            Server.deleteOldVersion();
            paper.downloadNewVersion();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }
}
