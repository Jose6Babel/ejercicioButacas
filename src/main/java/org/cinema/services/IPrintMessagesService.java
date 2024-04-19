package org.cinema.services;

public interface IPrintMessagesService {
    void initMessage();

    void numMessage();

    void menu();

    void chairStatus(String[][] chairsMovie);

    void numChairsFree(int numChairsFree);
}
