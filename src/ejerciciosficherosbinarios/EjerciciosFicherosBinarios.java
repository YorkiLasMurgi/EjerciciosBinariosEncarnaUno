/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejerciciosficherosbinarios;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alberto
 */
class Alumno implements Serializable {

    private int matricula;
    private String nombre;
    private double calificacion1;
    private double calificacion2;
    private double promedio;
    private static final long serialVersionUID = 1L;

    public Alumno() {
    }

    public Alumno(int matricula, String nombre, double calificacion1, double calificacion2) {
        this.matricula = matricula;
        this.nombre = nombre;
        this.calificacion1 = calificacion1;
        this.calificacion2 = calificacion2;
        this.promedio = calcularPromedio(this.calificacion1, this.calificacion2);
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getCalificacion1() {
        return calificacion1;
    }

    public void setCalificacion1(double calificacion1) {
        this.calificacion1 = calificacion1;
    }

    public double getCalificacion2() {
        return calificacion2;
    }

    public void setCalificacion2(double calificacion2) {
        this.calificacion2 = calificacion2;
    }

    public double getPromedio() {
        return promedio;
    }

    public void setPromedio(double promedio) {
        this.promedio = promedio;
    }

    @Override
    public String toString() {
        return "matricula= " + matricula + ", nombre= " + nombre + ", calificacion1= " + calificacion1 + ", calificacion2= " + calificacion2 + ", promedio= " + promedio;
    }

    public double calcularPromedio(double a, double b) {

        double promedio = (a + b) / 2;

        return promedio;

    }

    public void insertarDatos(int matricula) {
        Scanner teclado = new Scanner(System.in);
        this.matricula=matricula;
        System.out.println("Nombre: ");
        this.nombre = teclado.nextLine();
        System.out.println("calificacion 1: ");
        this.calificacion1 = teclado.nextDouble();
        System.out.println("calificacion 2: ");
        this.calificacion2 = teclado.nextDouble();
        this.promedio = calcularPromedio(calificacion1, calificacion2);
    }

}

public class EjerciciosFicherosBinarios {

    private static final long serialVersionUID = 1L;

    
    public static void rellenarFichero(File fichero) throws FileNotFoundException, IOException, ClassNotFoundException{
        
          ObjectInputStream leyendo_objeto = new ObjectInputStream(new FileInputStream(fichero));
          ArrayList<Alumno> alumnos = (ArrayList<Alumno>) leyendo_objeto.readObject();
          Alumno aux = new Alumno();
          aux.insertarDatos(alumnos.size()+1);
          alumnos.add(aux);
          leyendo_objeto.close();
          ObjectOutputStream escribiendo_objeto = new ObjectOutputStream(new FileOutputStream(fichero));
          escribiendo_objeto.writeObject(alumnos);
          escribiendo_objeto.close();
        
    }
    
    public static void crearFichero(File fichero) throws IOException{
        
       fichero.createNewFile();
       ArrayList<Alumno> arrayalumnos = new ArrayList<>();
        arrayalumnos.add(new Alumno(1, "pepe", 3, 2));
        arrayalumnos.add(new Alumno(2, "juan", 5, 7));
        arrayalumnos.add(new Alumno(3, "alberto", 9, 8));
         ObjectOutputStream escribiendo_objeto = new ObjectOutputStream(new FileOutputStream(fichero));
          escribiendo_objeto.writeObject(arrayalumnos);
          escribiendo_objeto.close();
        
    }
    
    public static boolean mostrardatosElegido(File fichero,int matricula) throws FileNotFoundException, IOException, ClassNotFoundException{
            boolean pasar=true;
          ObjectInputStream leyendo_objeto = new ObjectInputStream(new FileInputStream(fichero));
          ArrayList<Alumno> alumnos = (ArrayList<Alumno>) leyendo_objeto.readObject();
         
          for (Alumno alumno : alumnos) {
               
                if (alumno.getMatricula() == matricula) {
                    System.out.println("Coincidencia encontrada, mostrando alumno");
                    System.out.println(alumno);
                    pasar = false;

                }

            }
          return pasar;
          
    
    
    }
    
    
    public static void main(String[] args) throws IOException, FileNotFoundException, ClassNotFoundException {
        Scanner teclado = new Scanner(System.in);
       
    
          File fichero =  new File("C:\\Users\\Farra\\Desktop\\ficherosborrar\\datos.dat");
          
          if(fichero.exists()){
             
              System.out.println("Introduce una matricula para buscar al alumno: ");
              int matricula = teclado.nextInt();
              
              if(mostrardatosElegido(fichero, matricula)){
                  
              }
             
          
          }else{
             crearFichero(fichero);
              
          
          }

    }

}
