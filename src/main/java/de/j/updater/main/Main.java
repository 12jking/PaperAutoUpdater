package de.j.updater.main;

import de.j.updater.util.Paper;
import de.j.updater.util.Server;

import java.io.IOException;

public class Main {

    public static String mcVersion;

    public static void main(String[] args) {
        if (args[0] != null){
            try {
                mcVersion = "1.16.5";
                Paper paper = new Paper(mcVersion);
                System.out.println("Checking version...");
                System.out.println("The newest version is #" + paper.getCurrentVersion());
                if (!Server.alreadyNewestVersion()){
                    Thread delete = new Thread(() -> {
                        try {
                            Server.deleteOldVersion();
                        } catch (IOException | InterruptedException e) {
                            e.printStackTrace();
                        }
                    });
                    Thread download = new Thread(() -> {
                        try {
                            paper.downloadNewVersion();
                        } catch (IOException | InterruptedException e) {
                            e.printStackTrace();
                        }
                    });
                    delete.start();
                    download.start();
                    try {
                        download.join();
                        Server.startServer(mcVersion, Paper.newestVersion, Integer.parseInt(args[0]));
                    } catch (NumberFormatException e){
                        System.out.println("Please enter a valid RAM");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }else
                    System.out.println("You are already at the newest version!");
                System.exit(0);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else
            System.out.println("Please check your start script");
            System.exit(-1);

    }
}
