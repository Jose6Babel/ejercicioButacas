package org.cinema.services;

import org.springframework.stereotype.Service;

@Service
public class PrintMessagesService implements IPrintMessagesService {
    @Override
    public void initMessage() {
        System.out.println("Estas son las butacas disponibles:");
    }

    @Override
    public void numMessage() {
        System.out.println("Para cuantas personas?");
    }

    @Override
    public void menu() {
        System.out.println("Que desea hacer?");
        System.out.println("1.-Reservar butaca\n" +
                "2.- Ocupar butaca.\n" +
                "3.- Salir.");
    }

    @Override
    public void chairStatus(String[][] chairsMovie) {
        for (int x = 0; x < chairsMovie.length; x++) {
            for (int y = 0; y < chairsMovie[x].length; y++) {

                System.out.print("[" + (chairsMovie[x][y] == null ? " " : chairsMovie[x][y]) + "]");
            }
            System.out.println();
        }
        System.out.println();
    }

    @Override
    public void numChairsFree(int numChairsFree) {
        String message = numChairsFree == 0 ?
                        "No hay asientos contiguos libres en esta fila" :
                        "Quedan " + numChairsFree + " asientos libres contiguos.";

        System.out.println(message);
    }
}
