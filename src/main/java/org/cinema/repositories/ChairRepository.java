package org.cinema.repositories;

import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@Repository
public class ChairRepository implements IChairRepository {
    String[][] chairsCinema;
    int row = 9;
    int columns= 10;

    public ChairRepository() {
        chairsCinema = new String[row][columns];
        getChairsByFile("chairs", chairsCinema);
    }

    public void getChairsByFile(String nameFile, String[][] chairsCinema) {
        try {
            BufferedReader reader = null;

            try {
                File file = new File("src/main/resources/" + nameFile + ".properties");
                reader = new BufferedReader(new FileReader(file));

                String actualLine = null;
                while ((actualLine = reader.readLine()) != null) {
                    String[] firstKeySplited = actualLine.split("-");
                    String[] secondKeyAndValueSplited = firstKeySplited[1].split(":");

                    chairsCinema[Integer.parseInt(firstKeySplited[0])][Integer.parseInt(secondKeyAndValueSplited[0])] =
                            secondKeyAndValueSplited[1];
                }

            } catch (IOException e) {
                System.err.println("El archivo no existe");
            } finally {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        catch(Exception e) {
            e.getStackTrace();
        }

    }


}
