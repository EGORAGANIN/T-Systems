package com.tsystems.javaschool.tasks.duplicates;

import java.io.*;
import java.util.Map;
import java.util.TreeMap;

public class DuplicateFinder {


    public boolean process(File sourceFile, File targetFile) throws IllegalArgumentException {

        if (sourceFile == null || targetFile == null) {
            throw new IllegalArgumentException();
        }

        try {
            BufferedReader bufferedInput = new BufferedReader(new FileReader(sourceFile));
            BufferedWriter bufferedOutput = new BufferedWriter(new FileWriter(targetFile));

            TreeMap<String, Integer> mapInputFile = new TreeMap<String, Integer>();
            String line;
            while ((line = bufferedInput.readLine()) != null) {
                if (mapInputFile.containsKey(line)) {
                    mapInputFile.put(line, mapInputFile.get(line) + 1);
                } else {
                    mapInputFile.put(line, 1);
                }
            }
            for (Map.Entry<String, Integer> entry : mapInputFile.entrySet()) {

                bufferedOutput.write(entry.getKey() + " [" + entry.getValue() + "]");
                bufferedOutput.newLine();
            }

            bufferedInput.close();
            bufferedOutput.close();

            return true;

        } catch (Exception e){
            return false;
        }
    }
}
