package vistas;

import entidades.Alumno;
import java.time.LocalDate;
import persistencia.AlumnoData;
import persistencia.MiConexion;

public class Main {

    public static void main(String[] args) {
        Alumno alumno0 = new Alumno(44530873, "bordon", "rodrigo", LocalDate.now(), true);
        Alumno alumno1 = new Alumno(48345987, "Esquiaga", "Julian", LocalDate.now(), true);
        Alumno alumno2 = new Alumno(43987135, "Cristian", "Benegas", LocalDate.now(), true);
        Alumno alumno3 = new Alumno(36784652, "Mariano", "Mathot", LocalDate.now(), true);
        Alumno alumno4 = new Alumno(41987145, "Ayelen", "Aguero", LocalDate.now(), true);
        
        MiConexion conex = new MiConexion("jdbc:mariadb://localhost:3306/ulp2025gp9", "root", "");
        AlumnoData alumnoD = new AlumnoData(conex);
        
        //alumnoD.alumnoNuevo(alumno0);
        //alumnoD.alumnoNuevo(alumno1);
        // alumnoD.alumnoNuevo(alumno2);
        //alumnoD.alumnoNuevo(alumno3);
        //alumnoD.alumnoNuevo(alumno4);

//        System.out.println("Alumno: " + alumnoD.buscarAlumno(1));
//        System.out.println("Alumno: " + alumnoD.buscarAlumno(3));
//        System.out.println("Alumno: " + alumnoD.buscarAlumno(6));
//        System.out.println("Alumno: " + alumnoD.buscarAlumno(7));
//        System.out.println("Alumno: " + alumnoD.buscarAlumno(8));
//        
//        
    alumnoD.bajaFisicaAlumno(9);
    }
    
}
