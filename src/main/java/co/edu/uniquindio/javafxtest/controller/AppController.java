package co.edu.uniquindio.javafxtest.controller;

import co.edu.uniquindio.javafxtest.controller.HospitalController;
import co.edu.uniquindio.javafxtest.model.Usuario;

public class AppController {

    private static final HospitalController hospitalController = new HospitalController();

    private Usuario usuarioLogueado;

    public static HospitalController getHospitalController() {
        return hospitalController;
    }

    public void setUsuarioLogueado(Usuario usuario) {
        this.usuarioLogueado = usuario;
    }

    public Usuario getUsuarioLogueado() {
        return usuarioLogueado;

    }
}

