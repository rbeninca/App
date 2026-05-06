package com.example.app;

import java.util.ArrayList;

public class PlanetaContoller {
    PlanetaDAO planetaDAO;
    public PlanetaContoller() {
            planetaDAO=new PlanetaDAO();
    }

    public ArrayList<Planeta> listaPlanetas(){
        return planetaDAO.listaPlanetas();
    }
}
