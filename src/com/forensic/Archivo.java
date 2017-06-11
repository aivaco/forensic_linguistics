package com.forensic;

import java.io.File;
import java.io.FileWriter;
import java.nio.charset.Charset;


import java.io.IOException;

/**
 * It manages all the functions related to the file that has to be created.
 */
public class Archivo {

    private File file;
    /**
     * Creates the file with a respective name in local path.
     *
     * @param name
     * @param type
     */
    public void createFile(String name, String type) {
        try {
            if(!type.isEmpty())
                file = new File(".//" + name + "." + type);
            else
                file = new File(".//" + name);

            if (file.createNewFile()) {
                System.out.println("Archivo " + name + "." + type + " creado exitosamente");
            } else {
                System.out.println("Error al crear el archivo " + name + "." + type);
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void writeInFile(String data) {
        data += "\n";
        Charset.forName("UTF-8").encode(data);              //Sets the string to UTF-8.

        try {
            FileWriter writer = new FileWriter(file, true);
            writer.write(data);
            writer.flush();
            writer.close();
        }
        catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
