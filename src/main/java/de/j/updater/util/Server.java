package de.j.updater.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

public class Server {

    //private static String serverVersion;

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
            System.out.println("Deleting old version...");
            Process process = Runtime.getRuntime().exec("rm " + getCurrentVersion());
            process.waitFor();
            process.destroy();
            System.out.println("Deleted!");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static boolean alreadyNewestVersion(String mcVersion, int build){
        return Objects.requireNonNull(getCurrentVersion()).equalsIgnoreCase("paper-" + mcVersion + "-" + Paper.newestVersion + ".jar");
    }

}
