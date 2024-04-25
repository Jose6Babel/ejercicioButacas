package org.cinema.services;

import java.io.IOException;

public interface IChairService {
    void chairOccuped(String[][] chairsCinema, int numbersChairs);

    String[][] chairReserved(String[][] chairsCinema, int numbersChairs);

    String[][] chairInit();

    int getChairsFreeInRow(String[][] chairsCinema);

    int menuOption(int minOption, int maxOption) throws IOException;

    String yesOrNoOption() throws IOException;

    int chooseNumbers() throws IOException;
}
