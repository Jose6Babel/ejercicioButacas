package org.cinema.components;

import lombok.SneakyThrows;
import org.cinema.services.IChairService;
import org.cinema.services.IPrintMessagesService;
import org.springframework.stereotype.Component;

@Component
public class CinemaApp {

    IPrintMessagesService printMessagesService;

    IChairService chairService;

    public CinemaApp (IPrintMessagesService printMessagesService, IChairService chairService) {
        this.printMessagesService = printMessagesService;
        this.chairService = chairService;
    }


    @SneakyThrows
    public void run() {
        String[][] chairsCinema = chairService.chairInit();
        int optionChoose = 0;

        while (optionChoose != 3) {
            printMessagesService.initMessage();
            printMessagesService.chairStatus(chairsCinema);
            printMessagesService.menu();

            optionChoose = chairService.menuOption();
            if (optionChoose != 3) {
                printMessagesService.numMessage();
                int numChairs = chairService.chooseNumbers();

                switch (optionChoose) {
                    case 1: //Reservar
                        printMessagesService.numChairsFree(chairService.chairReserved(chairsCinema, numChairs));
                        break;
                    case 2: //Ocupar
                        printMessagesService.numChairsFree(chairService.chairOccuped(chairsCinema, numChairs));
                        break;
                    default:
                        break;
                }
            }
        }

    }
}
