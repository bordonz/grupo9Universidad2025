package persistencia;

import entidades.Alumno;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.naming.spi.DirStateFactory;

public class AlumnoData {

    private Connection conec = null;

    public AlumnoData(MiConexion conexion) {
        this.conec = conexion.buscarConexion();
    }

    public boolean alumnoNuevo(Alumno a) {
        String query = "INSERT INTO alumno (dni, apellido, nombre, fecha, estado) VALUES(?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = conec.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, a.getDni());
            ps.setString(2, a.getApellido());
            ps.setString(3, a.getNombre());
            ps.setDate(4, Date.valueOf(a.getFechaDeNacimiento()));
            ps.setBoolean(5, a.isEstado());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                a.setIdAlumno(rs.getInt(1));
            } else {
                System.out.println("No se pudo obtener el ID");
            }
            ps.close();
            System.out.println("Guardado");
            return true;
        } catch (SQLException e) {
            System.out.println("No se pudo insertar al alumno");
            return false;
        }

    }

    public Alumno buscarAlumno(int num) {
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
