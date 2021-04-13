package com.company;


import com.comsol.model.util.ModelUtil;
import com.comsol.model.*;

import javax.swing.*;
import java.io.*;
import java.time.LocalDateTime;

public class Main {
    public Main(){

    }

    public static void main(String[] args) {
        int port = 2037;
        String path = System.getProperty("user.dir");
        String[] coil1_param = {"", "", "", "", "", ""};
        String[] coil2_param = {"", "", "", "", "", ""};

        try {
            BufferedReader inputReader = new BufferedReader(new FileReader(path + "/coil1_param.txt"));

            for(int i = 0; i <= 5; i += 1) {
                String s = inputReader.readLine();
                coil1_param[i] = s;
            }
        } catch (IOException var45) {
            var45.printStackTrace();
        }

        try {
            BufferedReader inputReader = new BufferedReader(new FileReader(path + "/coil2_param.txt"));

            for(int i = 0; i <= 5; i += 1) {
                String s = inputReader.readLine();
                coil2_param[i] = s;
            }
        } catch (IOException var45) {
            var45.printStackTrace();
        }

        double ba = (double)System.currentTimeMillis();
        ModelUtil.connect("localhost", port);
        double b = (double)System.currentTimeMillis();
        System.out.println("Creating model... ");
        Induction Coil = new Induction();
        double e = (double)System.currentTimeMillis();
        System.out.println("Creating model done for " + (e - b) / 1000.0D + "s");
        Coil.SetCoilParams(coil1_param, coil2_param);
        System.out.println("Run model...");
        double[][] result = Coil.Run();
        System.out.println("Your result: " + result[0][0]);

        /*try {

            BufferedWriter outputWriter = new BufferedWriter(new FileWriter(path + "/FR.txt"));

            outputWriter.write(result[0][0] + "");
            /*for(int i = 0; i < result.length; ++i) {
                outputWriter.write(result[i][0] + " " + result[i][1] + " " + result[i][2]);
                outputWriter.newLine();
            }

            outputWriter.flush();
            outputWriter.close();
        } catch (IOException var43) {
            var43.printStackTrace();
        }*/

        ModelUtil.disconnect();
        double ea = (double)System.currentTimeMillis();
        System.out.println("All jobs done for " + (ea - ba) / 1000.0D + "s");
    }
}
