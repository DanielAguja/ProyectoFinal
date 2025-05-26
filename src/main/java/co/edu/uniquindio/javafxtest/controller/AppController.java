package co.edu.uniquindio.javafxtest.controller;

import co.edu.uniquindio.javafxtest.controller.HospitalController;

public class AppController {

    private static final HospitalController hospitalController = new HospitalController();

    public static HospitalController getHospitalController() {
        return hospitalController;
    }
}
