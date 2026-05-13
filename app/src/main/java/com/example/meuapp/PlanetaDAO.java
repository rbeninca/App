package com.example.meuapp;

import java.util.ArrayList;

public class PlanetaDAO {
    ArrayList<Planeta> listaPlanetas;

    public PlanetaDAO() {
        listaPlanetas = new ArrayList<>();
        listaPlanetas.add(new Planeta("Mercurio", R.drawable.mercury,"3.285x10^23"));
        listaPlanetas.add(new Planeta("Venus", R.drawable.venus,"4.86x10^23"));
        listaPlanetas.add(new Planeta("Terra", R.drawable.earth,"5.97x10^24"));
        listaPlanetas.add(new Planeta("Marte", R.drawable.mars,"6.97x10^23"));
        listaPlanetas.add(new Planeta("Jupiter", R.drawable.jupter,"1,873x10^27"));
        listaPlanetas.add(new Planeta("Saturno", R.drawable.saturn,"5.97x10^24"));
        listaPlanetas.add(new Planeta("Urano", R.drawable.uranus,"8.961x10^24"));
        listaPlanetas.add(new Planeta("Netuno", R.drawable.neptune,"1.024x10^26"));
    }

    public ArrayList<Planeta> listaPlanetas(){
        return listaPlanetas;
    }

}
