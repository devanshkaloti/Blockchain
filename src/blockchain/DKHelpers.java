package blockchain;

import java.io.*;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class DKHelpers {

    public static String applySha256(String input){
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            //Applies sha256 to our input,
            byte[] hash = digest.digest(input.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer(); // This will contain hash as hexidecimal
            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        }
        catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean writeRow(String[] row) {
        StringBuilder sb = new StringBuilder();
        PrintWriter pw;

        try {
            pw = new PrintWriter(new FileOutputStream(new File("src/blockchain/database.csv"), true));
        } catch (FileNotFoundException e) {
            System.out.println("File not found...");
            return false;
        }

        // Set Headers
//        for(String head: header) {
//            sb.append(head);
//            sb.append(',');
//        }
//        sb.append("\n");

        for (String data: row) {
            sb.append(data);
            sb.append(",");
        }
        sb.append('\n');

        pw.write(sb.toString());
        pw.close();
        System.out.println("Updated!");

        return true;
    }


    public static ArrayList<String[]> readCSV() {
        ArrayList<String[]> returnData = new ArrayList<>();
        try {
            Scanner inputstream = new Scanner(new File("src/blockchain/database.csv"));
            while (inputstream.hasNext()) {
                returnData.add(inputstream.next().split(","));
            }

            inputstream.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        return returnData;
    }


}
