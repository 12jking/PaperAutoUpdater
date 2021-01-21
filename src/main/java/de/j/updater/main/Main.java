package de.j.updater.main;

import de.j.updater.util.Paper;
import de.j.updater.util.Server;

import java.io.IOException;

public class Main {

    public static String mcVersion;

    public static void main(String[] args) {
        try {
            mcVersion = "1.16.5";
            Paper paper = new Paper(mcVersion);
            System.out.println("Checking version...");
            System.out.println("The newest version is #" + paper.getCurrentVersion());
            if (!Server.alreadyNewestVersion(mcVersion, Paper.newestVersion)){
                Server.deleteOldVersion();
                paper.downloadNewVersion();
            }else
                System.out.println("You are already at the newest version!");
                System.exit(0);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }
}
