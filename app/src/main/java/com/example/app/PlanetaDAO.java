package com.example.app;

import java.util.ArrayList;

public class PlanetaDAO {
     ArrayList<Planeta> listaPlanetas;
    public PlanetaDAO() {
        listaPlanetas=new ArrayList<>();
        listaPlanetas.add(new Planeta("Mercurio",R.drawable.mercury));
        listaPlanetas.add(new Planeta("Venus",R.drawable.venus));
        listaPlanetas.add(new Planeta("Terra",R.drawable.earth));
        listaPlanetas.add(new Planeta("Marte",R.drawable.mars));
        listaPlanetas.add(new Planeta("Jupiter",R.drawable.jupter));
        listaPlanetas.add(new Planeta("Saturno",R.drawable.saturn));
        listaPlanetas.add(new Planeta("Urano",R.drawable.uranus));
        listaPlanetas.add(new Planeta("Netuno",R.drawable.neptune));
    }

    public ArrayList<Planeta> listaPlanetas(){
        return listaPlanetas;
    }

}
