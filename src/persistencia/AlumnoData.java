package persistencia;

import entidades.Alumno;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AlumnoData {

    private Connection conec = null;

    public AlumnoData(MiConexion conexion) {
        this.conec = conexion.buscarConexion();
    }

    public Alumno buscaAlumno(int num) {
        Alumno a = null;
        String query = "Select * FROM alumno WHERE idAlumno = ?";
        try {
            PreparedStatement ps = conec.prepareStatement(query);
            ps.setInt(1, num);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                a = new Alumno();
                a.setIdAlumno(rs.getInt("idAlumno"));
                a.setDni(rs.getInt("dni"));
                a.setApellido(rs.getString("apellido"));
                a.setNombre(rs.getString("nombre"));
                a.setFechaDeNacimiento(rs.getDate("fecha").toLocalDate());
                a.setEstado(rs.getBoolean("estado"));
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("No se pudo encontrar al alumno");
        }
        return a;
    }

}
