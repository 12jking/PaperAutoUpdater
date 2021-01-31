package de.j.updater.util;

import java.io.*;
import java.util.NoSuchElementException;

public class Server {

    public static String getCurrentVersion(){
        Process p;
        String s;
        try {
            p = Runtime.getRuntime().exec("ls");
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(p.getInputStream()));
            while ((s = br.readLine()) != null){
                if (s.contains("paper-1.1")){
                    //serverVersion = s;
                    return s;
                }
            }
            p.waitFor();
            p.destroy();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void deleteOldVersion() throws IOException, InterruptedException {
        try {
            System.out.println("[Thread #1] Deleting old version...");
            Process process = Runtime.getRuntime().exec("rm paper.jar");
            process.waitFor();
            process.destroy();
            System.out.println("[Thread #1] Deleted!");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static boolean alreadyNewestVersion(){
        try {
            Log log = new Log();
            if (!log.exists()){
                return false;
            }
            return log.getLatestUpdateVersion() == Paper.newestVersion;
        } catch (IOException | NoSuchElementException e) {
            return false;
        }
    }

    public static void startServer(String mcVersion, int ram) throws IOException, InterruptedException {
        System.out.println("Starting server on Screen...");
        Process p = Runtime.getRuntime().exec("screen -dmS mc java -jar -Xmx" + ram + "G paper.jar nogui");
        p.waitFor();
        p.destroy();
    }

    public static void updateStartScript(String mcVersion) throws IOException, InterruptedException {
        Process p = Runtime.getRuntime().exec("sudo mv paper-" + mcVersion + "-" + Paper.newestVersion + ".jar paper.jar");
        p.waitFor();
        p.destroy();
    }

}