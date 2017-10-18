/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejerciciosficherosbinarios;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author alberto
 */
class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;

    private int numero;
    private String nombre;
    private double saldo;

    public Cliente() {
    }

    public Cliente(int numero, String nombre, double saldo) {
        this.numero = numero;
        this.nombre = nombre;
        this.saldo = saldo;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public void rellenarCliente() {
        System.out.println("Introduce los datos del cliente.");
        Scanner teclado = new Scanner(System.in);
        System.out.println("Numero del cliente: ");
        this.numero = teclado.nextInt();
        teclado.nextLine();
        System.out.println("Nombre del cliente: ");
        
        this.nombre=teclado.nextLine();
        System.out.println("Saldo del cliente: ");
        this.saldo = teclado.nextDouble();
        System.out.println("Cliente creado..");
    }

    @Override
    public String toString() {
        return "numero= " + numero + ", nombre= " + nombre + ", saldo= " + saldo;
    }
    
    

}

public class Ejercicioficherosbinariosdos {

    private static final long serialVersionUID = 1L;

    /*
    2.- Escribir en un fichero secuencial de datos de un cierto número de clientes. 
Los datos serán número, nombre y saldo del cliente. 
El nombre del fichero, el número de clientes y los datos de cada cliente 
se leerán por teclado. Los datos se leerán posteriormente del fichero 
y se imprimirán en pantalla.
 (EscribirTecladoClientes.java y  LeerContenidoClientes.java)
     */
    
    //METODO PARA INSERTAR DATOS EN EL FICHERO
    public static void rellenarFichero(File fichero) throws FileNotFoundException, IOException {
        Scanner teclado = new Scanner(System.in);

        System.out.println("introduce el numero de clientes.");
        int num = teclado.nextInt();
        ArrayList<Cliente> clientes = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            
            clientes.add(new Cliente());
            clientes.get(i).rellenarCliente();
            
        }
        ObjectOutputStream aux = new ObjectOutputStream(new FileOutputStream(fichero));
        aux.writeObject(clientes);
        
        aux.close();

    }
    
    
    //METODO PARA MOSTRAR LOS DATOS DEL FICHERO
    public static void mostrarDatos(File fichero) throws FileNotFoundException, IOException, ClassNotFoundException {

        System.out.println("Mostrando datos del fichero " + fichero.getName());
        ObjectInputStream aux = new ObjectInputStream(new FileInputStream(fichero));
        ArrayList<Cliente> cliente = new ArrayList<>();
        cliente = (ArrayList<Cliente>) aux.readObject();
        aux.close();
        for (int i = 0; i < cliente.size(); i++) {
            
            System.out.println("Cliente: "+(i+1)+"\n"+cliente.get(i));
            
        }
    }

    public static void main(String[] args) throws IOException, FileNotFoundException, ClassNotFoundException {
        Scanner teclado = new Scanner(System.in);
        System.out.println("Introduce el nombre del fichero");
        String nombre = teclado.nextLine();
        File fichero = new File("/home/usuario/Escritorio/filedatos/" + nombre + ".dat");
        //SI EL FICHERO EXISTE NOS MUESTRA LOS DATOS
        if (fichero.exists()) {
            System.out.println("El fichero ya existe.");
            mostrarDatos(fichero);
        } else {
            //SI NO EXISTE LO CREAMOS METEMOS LOS DATOS Y LOS MUESTRA
            fichero.createNewFile();
            rellenarFichero(fichero);
            mostrarDatos(fichero);

        }

    }

}
