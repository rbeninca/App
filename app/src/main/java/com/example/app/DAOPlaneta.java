package com.example.app;

import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class DAOPlaneta {
    private ArrayList<Planeta> planetas;

    public  DAOPlaneta(){
        planetas =new ArrayList<Planeta>();
    }
    //inserir Planetas
    public void add( Planeta p ){
        this.planetas.add(p);
    }
    public ArrayList<Planeta> getPlanetas() {
        return this.planetas;
    }
    public Planeta get(int posicao){
        return this.planetas.get(posicao);
    }
}
