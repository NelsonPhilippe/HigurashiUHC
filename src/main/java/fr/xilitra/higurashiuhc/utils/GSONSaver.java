package fr.xilitra.higurashiuhc.utils;

import com.google.gson.Gson;

import java.io.*;

public class GSONSaver {

    public static <T> T loadGSON(String string, Class<T> tClass) {

        return new Gson().fromJson(string, tClass);

    }

    public static <T> T loadGSON(File file, Class<T> tClass) {

        if (!file.exists()) return null;

        if (!file.canRead())
            if (!file.setReadable(true)) return null;

        Gson gson = new Gson();
        T gsClass = null;
        FileReader fileReader = null;

        try {
            fileReader = new FileReader(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        if (fileReader != null) {
            gsClass = gson.fromJson(new BufferedReader(fileReader), tClass);
            try {
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return gsClass;
    }

    public static String toGSON(Object object) {
        return new Gson().toJson(object);
    }

    public static boolean writeGSON(File file, Object object) {

        String[] pathSplit = file.getPath().split(File.separator);
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < pathSplit.length - 1; i++) {
            if (sb.length() != 0)
                sb.append(File.separator);
            sb.append(pathSplit[i]);
        }

        System.out.println(sb);

        File folderFile = new File(sb.toString());
        if (!folderFile.exists() && !folderFile.mkdirs())
            return false;

        try {
            if (!file.exists() && !file.createNewFile())
                return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        if (!file.canRead())
            if (!file.setReadable(true)) return false;

        Gson gson = new Gson();
        String json = gson.toJson(object);
        FileWriter fileWriter;

        try {
            fileWriter = new FileWriter(file);
            fileWriter.write(json);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        try {
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }
}
