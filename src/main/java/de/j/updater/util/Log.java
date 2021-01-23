package de.j.updater.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Log {

    File file;
    FileWriter writer;

    private boolean exists;

    public Log() throws IOException {
        file = new File("updaterLog.txt");
        writer = new FileWriter(file);
        exists = file.exists();
    }

    public boolean exists(){
        return exists;
    }

    public Log setNewVersion(int version) throws IOException {
        writer.write("Updated successful to #" + version);
        return this;
    }

    public int getLatestUpdateVersion() {
        try {
            Scanner scanner = new Scanner(file);
            String data = scanner.nextLine().replace("Updated successful to #", "");
            scanner.close();
            return Integer.parseInt(data);
        } catch (FileNotFoundException e) {
            return 400;
        }
    }

    public void save() throws IOException {
        writer.close();
    }
}
