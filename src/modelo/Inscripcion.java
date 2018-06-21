/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Alvaro García <alvarogarcia1010 at github.com>
 */
public class Inscripcion {
    private int numAFP;
    private String nombre;
    private String apellido;
    private int edad;
    private String profesion;
    private boolean estado;
    
    //Constructor
    
    public Inscripcion(){}

    public Inscripcion(int numAFP, String nombre, String apellido, int edad, String profesion, boolean estado) {
        this.numAFP = numAFP;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.profesion = profesion;
        this.estado = estado;
    }

    public int getNumAFP() {
        return numAFP;
    }

    public void setNumAFP(int numAFP) {
        this.numAFP = numAFP;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }



}
