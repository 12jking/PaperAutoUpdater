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

    public int getNewestVersion()  {
        int newestVersion = 430;
        for (int i = 430; i < 800; i ++){
            try {
                if (checkIfUrlExists(new URL("https://papermc.io/api/v2/projects/paper/versions/1.16.5/builds/" + i + "/downloads/paper-1.16.5-" + i + ".jar"))){
                    newestVersion ++;
                }else {
                    return newestVersion - 1;
                }
            } catch (IOException e) {
                return newestVersion - 1;
            }
        }
        return 430;
    }

    public Paper () throws IOException {
        paperUrl = "https://papermc.io/api/v2/projects/paper/versions/1.16.5/builds/" + getNewestVersion() + "/downloads/paper-1.16.5-" + getNewestVersion() + ".jar";
        Runtime.getRuntime().exec("wget " + paperUrl);
    }

    private boolean checkIfUrlExists(URL url) throws IOException {
        br = new BufferedReader(new InputStreamReader(url.openStream()));
        return !br.readLine().equalsIgnoreCase("{\"error\":\"no such build\"}");
    }

}
