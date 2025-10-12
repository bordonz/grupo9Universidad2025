package persistencia;

import entidades.Alumno;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.naming.spi.DirStateFactory;

public class AlumnoData {

    private Connection conec = null;

    public AlumnoData(MiConexion conexion) {
        this.conec = conexion.buscarConexion();
    }

    public boolean alumnoNuevo(Alumno a) {
        String query = "INSERT INTO alumno (dni, apellido, nombre, fechaNacimiento, estado) VALUES(?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = conec.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, a.getDni());
            ps.setString(2, a.getApellido());
            ps.setString(3, a.getNombre());
            ps.setDate(4, Date.valueOf(a.getFechaNacimiento()));
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
                a.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());
                a.setEstado(rs.getBoolean("estado"));
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("No se pudo encontrar al alumno");
        }
        return a;
    }
    
    public ArrayList<Alumno> listarAlumnos(){
        Alumno a = null;
        ArrayList<Alumno> alumnos = new ArrayList<>();
        try {
            String query = "SELECT * FROM alumno";
            PreparedStatement ps = conec.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                a = new Alumno();
                a.setIdAlumno(rs.getInt("idAlumno"));
                a.setDni(rs.getInt("dni"));
                a.setApellido(rs.getString("apellido"));
                a.setNombre(rs.getString("nombre"));
                a.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());
                a.setEstado(rs.getBoolean("estado"));
                alumnos.add(a);
            }
            ps.close();
        }catch(SQLException e){
            e.getMessage();
        }
        return alumnos;
    }
    
    public void actualizarAlumno(Alumno a, int n){
        String query = "UPDATE alumno SET dni = ?, apellido = ?, nombre = ?, fecha = ?, estado = ? where idAlumno = ?";
        try {
            //El prepare es quien enviara con la conec la query
            PreparedStatement ps = conec.prepareStatement(query);
            //Remplaso los comodines y ejecuto y actualizo
            ps.setInt(1, a.getDni());
            ps.setString(2, a.getApellido());
            ps.setString(3, a.getNombre());
            ps.setDate(4, Date.valueOf(a.getFechaNacimiento()));
            ps.setBoolean(5, a.isEstado());
            ps.setInt(6, n);
            ps.executeUpdate();
            ps.close();
            System.out.println("Actualizado");
       }catch(SQLException e){
           System.out.println("No se pudo actualizar");
       }
    }
        
    public void bajaFisicaAlumno(int id){
        String query = "DELETE FROM alumno WHERE idAlumno = ?";
        try {
            //El prepare es quien enviara con la conec la query
            PreparedStatement ps = conec.prepareStatement(query);
            //Remplaso los comodines y ejecuto y actualizo
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Alumno borrado");
       }catch(SQLException e){
           System.out.println("No se pudo borrar al alumno");
       }
    }
    
    public void bajaAltaLogica(int id, boolean boleano) {
        String query = "UPDATE alumno SET estado = ? where idAlumno = ?";
        try {
            //El prepare es quien enviara con la conec la query
            PreparedStatement ps = conec.prepareStatement(query);
            //Remplazo los comodines y ejecuto y actualizo
            ps.setBoolean(1, boleano);
            ps.setInt(2, id);
            ps.executeUpdate();
            ps.close();
            System.out.println("Actualizado");
        } catch (SQLException e) {
            System.out.println("No se pudo actualizar");
        }
    }
}
