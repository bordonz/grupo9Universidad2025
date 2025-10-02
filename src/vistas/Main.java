package vistas;

import entidades.Alumno;
import java.time.LocalDate;
import persistencia.AlumnoData;
import persistencia.MiConexion;

public class Main {

    public static void main(String[] args) {
        Alumno alumno0 = new Alumno(44530873, "bordon", "rodrigo", LocalDate.now(), true);
        
        MiConexion conex = new MiConexion("jdbc:mariadb://localhost:3306/ulp2025gp9", "root", "");
        AlumnoData alumnoD = new AlumnoData(conex);
        
        alumnoD.alumnoNuevo(alumno0);
    }
    
}
