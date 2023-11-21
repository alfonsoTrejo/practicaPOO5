/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.practica5;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author trejo
 */
public class Juego {
    int intento =0,intentosMax =15;
    private Canvas canvas;
    private ArrayList<Color> codigo;
    private ArrayList<Colores> correctas;
    private ArrayList<Color> codigoIngresado;
    
    
    public Juego(){
        canvas = new Canvas("Mastermind",800,800);
        
    }
    
    public void jugar (){
        codigo = generarCodigo();
        System.out.println(codigo);
        boolean igauales=false;
        do {
            codigoIngresado = capturarCodigo();
            correctas = algoritmo(codigo, codigoIngresado);
            dibujar();
            correctas= null;
            intento++;
            if(intento ==15) break;
            if(codigoIngresado.equals(codigo)){
                System.out.println("ganaste");
                break;
            }
        } while (true);
        
    }
    
    public ArrayList<Colores> algoritmo(ArrayList<Color> codigo, ArrayList<Color> codigoIngresado){
        ArrayList<Colores> correctasThis = new ArrayList<>(6);
        correctasThis.add(new Colores());
        correctasThis.add(new Colores());
        correctasThis.add(new Colores());
        correctasThis.add(new Colores());
        correctasThis.add(new Colores());
        correctasThis.add(new Colores());
        
        System.out.println(correctasThis.toString());
        
        for (int i = 0; i < codigoIngresado.size(); i++) {
            for (int j = 0; j < codigo.size(); j++) {
                if (codigoIngresado.get(i).equals(codigo.get(j))){
                    if (i==j) {
                        correctasThis.get(i).setColor(Color.WHITE);
                        break;
                    }else{
                        correctasThis.get(i).setColor(Color.RED);
                        break;                    
                    }
                }
            }
        }
    return correctasThis;
    }
    
    public ArrayList<Color> generarCodigo(){
        ArrayList<Color> listColores = new ArrayList<>();
        
        listColores.add(Color.RED);
        listColores.add(Color.GREEN);
        listColores.add(Color.BLUE);
        listColores.add(Color.YELLOW);
        listColores.add(Color.ORANGE);
        listColores.add(Color.PINK);
        listColores.add(Color.CYAN);
        listColores.add(Color.MAGENTA);
        Collections.shuffle(listColores);
        
        ArrayList<Color> coloresCodigo = new ArrayList<>(6);
        for (int i = 0; i < 6; i++) {
            coloresCodigo.add(listColores.get(i));
        }
        
        return coloresCodigo;
    }
    
    public void dibujar(){
          int diametro = 30, espacio =10,i=intentosMax-this.intento;
        //for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 6; j++) {
                Circulo b2 = new Circulo(20 +(j*(diametro+espacio)),20+(i*(diametro+espacio)),diametro+4,Color.BLACK,canvas);
                Circulo b1 = new Circulo(22+(j*(diametro+espacio)),22+(i*(diametro+espacio)),diametro,codigoIngresado.get(j),canvas);
                b2.draw();
                b1.draw();                            
            }
            diametro = 15;
            espacio = 25;
            for (int j = 0; j < 6; j++) {
                Circulo b2 = new Circulo(280 +(j*(diametro+espacio)),30+(i*(diametro+espacio)),diametro+4,Color.BLACK,canvas);
                Circulo b1 = new Circulo(282+(j*(diametro+espacio)),32+(i*(diametro+espacio)),diametro,correctas.get(j).getColor(),canvas);
                b2.draw();
                b1.draw();                            
            }
        //}
    }
    
    public ArrayList<Color> capturarCodigo(){
        boolean indice = true; 
        boolean repetidos = true;
        ArrayList<Color> listColor = new ArrayList<>();
        Scanner s1 = new Scanner(System.in);
        String[] colores;
        do {  
            System.out.println("re red \t bl blue \t gr green \t ye yellow ");
            System.out.println("or orange \t pi pink \t cy cyan \t ma magenta");
            System.out.println("Ingrese el codigo:");
            String palabra = s1.nextLine();
            colores =  palabra.split(" ");
            if(colores.length == 6 ) indice =false;
            else System.out.println("ingrese los 6 colores");

            repetidos = esRepetido(colores);
            if (repetidos) System.out.println("los colores estan repetidos");

        } while (!(!indice && !repetidos));
        
        for (String colore : colores) {
            switch (colore) {
                case "re" -> listColor.add(Color.RED);
                case "bl" -> listColor.add(Color.BLUE);
                case "gr" -> listColor.add(Color.GREEN);
                case "ye" -> listColor.add(Color.YELLOW);
                case "or" -> listColor.add(Color.ORANGE);
                case "pi" -> listColor.add(Color.PINK);
                case "cy" -> listColor.add(Color.CYAN);
                case "ma" -> listColor.add(Color.MAGENTA);
            }
        }
        return listColor;
    }
    
    public boolean esRepetido(String [] colores){
        Set<String> elementosUnicos = new HashSet<>();  
        for (String elemento : colores) {
            if (!elementosUnicos.add(elemento)) {
                return true;
            }       
        }
        return false;
    }
}

