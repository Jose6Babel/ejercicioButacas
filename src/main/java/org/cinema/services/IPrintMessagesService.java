package org.cinema.services;

public interface IPrintMessagesService {
    void numMessage();

    void menu();

    void chooseReserved();

    void chairStatus(String[][] chairsMovie);

    void numChairsFree(int numChairsFree);
}
