package org.cinema.services;

import java.io.IOException;

public interface IChairService {
    int chairOccuped(String[][] chairsCinema, int numbersChairs);

    int chairReserved(String[][] chairsCinema, int numbersChairs);

    String[][] chairInit();

    int menuOption() throws IOException;

    int chooseNumbers() throws IOException;
}
