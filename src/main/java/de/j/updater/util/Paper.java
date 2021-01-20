package de.j.updater.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class Paper {

    String paperUrl;
    private static BufferedReader br;
    private static URL url;
    int newestVersion = 430;

    public int getNewestVersion()  {
        for (int i = 430; i < 800; i ++){
            try {
                if (checkIfUrlExists(new URL("https://papermc.io/api/v2/projects/paper/versions/1.16.5/builds/" + i + "/downloads/paper-1.16.5-" + i + ".jar"))){
                    newestVersion ++;
                }else {
                    newestVersion = i-1;
                    return newestVersion-1;
                }
            } catch (IOException e) {
                newestVersion = i -1;
                return newestVersion-1;
            }
        }
        return 430;
    }

    private boolean checkIfUrlExists(URL url) throws IOException {
        br = new BufferedReader(new InputStreamReader(url.openStream()));
        return !br.readLine().equalsIgnoreCase("{\"error\":\"no such build\"}");
    }

    public void downloadNewVersion() throws IOException, InterruptedException {
        System.out.println("Downloading new version...");
        paperUrl = "https://papermc.io/api/v2/projects/paper/versions/1.16.5/builds/" + newestVersion + "/downloads/paper-1.16.5-" + newestVersion + ".jar";
        Process p = Runtime.getRuntime().exec("sudo curl -O " + paperUrl);
        p.waitFor();
        p.destroy();
        System.out.println("Downloaded!");
        System.exit(0);
    }

}
