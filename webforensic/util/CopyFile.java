package util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.*;

public class CopyFile {

    private static CopyFile instance = new CopyFile();

    private CopyFile() {
    }

    public static CopyFile getInstance() {
        return instance;
    }

    public static void makeNewFile(String filename){
        String base = System.getenv("USERPROFILE")+"\\AppData\\Local\\google\\chrome\\user data\\default\\";
        if(filename.equals("cookies")){
            base +="network\\";
        }
        File file = new File(base + filename);
        File Nfile = new File(base + "new_" + filename);

        try {
            Files.copy(file.toPath(), Nfile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void makeCache(String filename){
        String base = System.getenv("USERPROFILE")+"\\AppData\\Local\\google\\chrome\\user data\\default\\Cache\\";
        File file = new File(base + filename);
        File Nfile = new File(base + "new_" + filename);

        try {
            Files.copy(file.toPath(), Nfile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}