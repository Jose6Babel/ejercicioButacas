package org.cinema.components;

import lombok.SneakyThrows;
import org.cinema.services.IChairService;
import org.cinema.services.IPrintMessagesService;
import org.springframework.stereotype.Component;

import java.io.IOException;

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
            printMessagesService.menu();
            try {
                optionChoose = chairService.menuOption(1, 3);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (optionChoose != 3) {
                switch (optionChoose) {
                    case 1: //Reservar
                        printMessagesService.numMessage();
                        int numChairs = chairService.chooseNumbers();

                        String[][] chairsReserved = chairService.chairReserved(chairsCinema, numChairs);
                        printMessagesService.chairStatus(chairsReserved);
                        confirmOcupped(chairsCinema, numChairs);
                        break;
                    case 2: //Estado cine
                        printMessagesService.chairStatus(chairsCinema);
                        break;
                    default:
                        break;
                }
            }
        }

    }

    private void confirmOcupped(String[][] chairsCinema, int numChairs) throws IOException {
        printMessagesService.chooseReserved();
        String yesOrNo = chairService.yesOrNoOption();
        if (yesOrNo.equalsIgnoreCase("s")) {
            chairService.chairOccuped(chairsCinema, numChairs);
            int freeChairs = chairService.getChairsFreeInRow(chairsCinema);
            printMessagesService.numChairsFree(freeChairs);
        }
    }
}
