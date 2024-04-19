package org.cinema.services;

import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class ChairService implements IChairService {

    @Override
    public int chairOccuped(String[][] chairsCinema, int numbersChairs) {
        return fillChairs(chairsCinema, numbersChairs, false);
    }
    @Override
    public int chairReserved(String[][] chairsCinema, int numbersChairs) {
        return fillChairs(chairsCinema, numbersChairs, true);
    }

    public int fillChairs(String[][] chairsCinema, int numbersChairs, boolean isReserved) {
        int indexHalfChair = findFreeChairs(chairsCinema, isReserved);
        int half = chairsCinema.length/2;
        boolean firstChair = isReserved ?
                chairsCinema[indexHalfChair][indexHalfChair] == null :
                chairsCinema[indexHalfChair][indexHalfChair] == null || chairsCinema[indexHalfChair][indexHalfChair].equalsIgnoreCase("R");

        int actualIndex = half;

        // Por cada num de butacas hace un bucle
        for (int x = 0; x < numbersChairs; x++) {
            // Si se reservan más que la longitud, vuelve a 0
            if (x == chairsCinema[indexHalfChair].length) {
                numbersChairs = numbersChairs - x;
                indexHalfChair = findFreeChairs(chairsCinema, isReserved);
                x = 0;
                actualIndex = half;
                firstChair = false;
            }

            if (firstChair) {
                // Si ya está ocupado, le sumo numbersChair para seguir en la fila
                if (isReserved && chairsCinema[indexHalfChair][actualIndex] == "R" ||
                        !isReserved && chairsCinema[indexHalfChair][actualIndex] == String.valueOf(x+1)) {

                    numbersChairs++;
                }

                chairsCinema[indexHalfChair][actualIndex] = isReserved ?
                        "R":
                        String.valueOf(x+1);
                firstChair = false;
            }

            // Comprueba si es par
            else {
                if ((x + 1) % 2 == 0) {
                    actualIndex = actualIndex + x;
                } else {
                    actualIndex = actualIndex - x;
                }

                // Si el index está ocupado, suma la cantidad para ocupar la misma fila
                if (chairsCinema[indexHalfChair][actualIndex] == null ||
                        !isReserved && chairsCinema[indexHalfChair][actualIndex].equalsIgnoreCase("R") ) {

                    chairsCinema[indexHalfChair][actualIndex] = isReserved ?
                            "R":
                            String.valueOf(x+1);
                } else {
                    numbersChairs++;
                }
            }
        }

        return getChairsFreeInRow(chairsCinema,indexHalfChair);
    }

    public int findFreeChairs(String[][] chairsCinema, boolean isReserved) {
        boolean isLess = true;
        int numberRow = 1;

        for (int x = chairsCinema.length/2; x < chairsCinema.length;) {
            // Me falta comprobar el tamaño max y min del array, para que no pete cuando esten todos reservados
            if (!isReserved && chairsCinema[x][chairsCinema.length/2] == "R" ||
                    chairsCinema[x][chairsCinema.length/2] == null ||
                    chairsCinema[x][chairsCinema.length] == null ||
                    !isReserved && chairsCinema[x][chairsCinema.length] == "R") {

                return x;
            }
            else {
                if (isLess) {
                    x = chairsCinema.length/2 - numberRow;
                    isLess = false;
                }
                else {
                    x = chairsCinema.length/2 + numberRow;
                    isLess = true;
                    numberRow++;
                }
            }
        }

        return 0;
    }

    @Override
    public String[][] chairInit() {
        String[][] chairsCinema = new String[9][10];
        getChairsByFile("chairs", chairsCinema);

        return chairsCinema;
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

    public int getChairsFreeInRow(String[][] chairsCinema, int indexHalfChair) {
        int chairsFree = 0;

        for (int x = 0; x < chairsCinema[indexHalfChair].length; x++) {
            if (chairsCinema[indexHalfChair][x] == null) {
                chairsFree++;
            }
        }

        return chairsFree;
    }

    @Override
    public int menuOption() throws IOException {
        int numMenuChoose = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (numMenuChoose < 1 || numMenuChoose > 3) {
            String printMenu = br.readLine();
            try {
                numMenuChoose = Integer.parseInt(printMenu);
                if (numMenuChoose < 1 || numMenuChoose > 3) {
                    System.err.println("Error, introduzca un numero valido");
                }
            } catch (NumberFormatException error) {
                System.err.println("Error, introduzca un numero valido");
            }
        }
        return numMenuChoose;

    }
    @Override
    public int chooseNumbers() throws IOException {
        int numChoose = 0;
        boolean isError = true;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        do {
            String printMenu = br.readLine();
            try {
                numChoose = Integer.parseInt(printMenu);
                if (numChoose <= 0) {
                    System.err.println("Error, introduzca un numero valido");
                } else {
                    isError = false;
                }
            } catch (NumberFormatException error) {
                System.err.println("Error, introduzca un numero valido");
                isError = true;
            }
        } while (isError);
        return numChoose;

    }



}
