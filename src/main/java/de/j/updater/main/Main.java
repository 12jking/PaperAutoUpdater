package de.j.updater.main;

import de.j.updater.util.Paper;
import de.j.updater.util.Server;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        try {
            Paper paper = new Paper();
            System.out.println("The newest version is #" + paper.getNewestVersion());
            Server.deleteOldVersion();
            paper.downloadNewVersion();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }
}
