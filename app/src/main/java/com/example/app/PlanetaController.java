package com.example.app;

import java.util.ArrayList;

public class PlanetaController {
    PlanetaDao planetaDao;

    public PlanetaController(){
        planetaDao = new PlanetaDao();
    }

    public void addPlaneta(Planeta planeta) {//verficaria a lofica de negocio aqui
    }
    public ArrayList<Planeta> getPlaneta() {
        //verica se algo se algo ....
        return planetaDao.getPlanetas();
    }
    public ArrayList<String>  getNomePLanetas(){
        ArrayList<String> nomes = new ArrayList<String>();
        for (Planeta planeta : planetaDao.getPlanetas()) {
            nomes.add(planeta.nome);
        }
        return nomes;
    }


}
