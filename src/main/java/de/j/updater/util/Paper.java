package de.j.updater.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Paper {

    String paperUrl;
    private static BufferedReader br;
    private static URL url;
    public static int newestVersion;
    String mcVersion;


    public int getCurrentVersion() throws IOException {
        URL url = new URL("https://papermc.io/api/v2/projects/paper/versions/" + mcVersion);
        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
        String s = reader.readLine().replace("{\"project_id\":\"paper\",\"project_name\":\"Paper\",\"version\":\"1.16.5\",\"builds\":[", "").replace("]}", "");
        System.out.println("Paper builds for your mc version" + s);
        String[] versions = s.split(",");
        List<Integer> v = new ArrayList<>();
        for (String version : versions) {
            v.add(Integer.parseInt(version));
        }

        int newest = 100;
        for (int i : v) {
            if (i > newest) newest = i;
        }
        newestVersion = newest;
        return newest;
    }

    public Paper(String mcVersion){
        this.mcVersion = mcVersion;
    }

    public void downloadNewVersion() throws IOException, InterruptedException {
            System.out.println("Downloading new version...");
            paperUrl = "https://papermc.io/api/v2/projects/paper/versions/" + mcVersion + "/builds/" + newestVersion + "/downloads/paper-" + mcVersion + "-" + newestVersion + ".jar";
            Process p = Runtime.getRuntime().exec("sudo curl -O " + paperUrl);
            p.waitFor();
            p.destroy();
            System.out.println("Downloaded!");
            System.exit(0);


    }

}
