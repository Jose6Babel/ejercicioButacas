package org.cinema;

import org.cinema.components.CinemaApp;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class CinemaRunner {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConfigApp.class);

        CinemaApp cinemaApp = context.getBean(CinemaApp.class);

        cinemaApp.run();
    }
}