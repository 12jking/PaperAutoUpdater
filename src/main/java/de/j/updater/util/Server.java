package de.j.updater.util;

import de.j.updater.main.Main;

import java.io.*;
import java.util.Objects;

public class Server {

    public static String getCurrentVersion(){
        Process p;
        String s;
        try {
            p = Runtime.getRuntime().exec("ls");
            p.waitFor();
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(p.getInputStream()));
            while ((s = br.readLine()) != null){
                if (s.contains("paper-1.1")){
                    p.destroy();
                    System.out.println("You are currently on version " + s);
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
            Process process = Runtime.getRuntime().exec("rm " + getCurrentVersion());
            process.waitFor();
            process.destroy();
            System.out.println("[Thread #1] Deleted!");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static boolean alreadyNewestVersion() throws IOException {
        /*try {
            Log log = new Log();
            if (!log.exists()){
                return false;
            }
            return log.getLatestUpdateVersion() == Paper.newestVersion;
        } catch (IOException | NoSuchElementException e) {
            return false;
        }*/
        //paper-1.16.5-457.jar
        return Objects.requireNonNull(getCurrentVersion()).replace("paper-" + Main.mcVersion + "-", "").replace(".jar", "").equals(String.valueOf(Paper.newestVersion));
    }

    public static void startServer(String mcVersion, int buildVersion, int ram) throws IOException, InterruptedException {
        System.out.println("Starting server on Screen...");
        Process p = Runtime.getRuntime().exec("screen -dmS mc java -jar -Xmx" + ram + "G paper-" + mcVersion + "-" + buildVersion + ".jar nogui");
        p.waitFor();
        p.destroy();
    }

    public static void updateStartScript(String mcVersion) throws IOException, InterruptedException {
        Process p = Runtime.getRuntime().exec("sudo mv paper-" + mcVersion + "-" + Paper.newestVersion + ".jar paper.jar");
        p.waitFor();
        p.destroy();
    }

}