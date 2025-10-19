 package persistencia;

import entidades.Materia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @autor Benegas Gonzalez Cristian Rene
 */
public class MateriaData {

    private Connection conec = null;

    public MateriaData(MiConexion conexion) {
        this.conec = conexion.buscarConexion();
    }

    public MateriaData() {
        
    }

    public boolean materiaNueva(Materia m) {
        String query = "INSERT INTO materia (nombre, año, estado) VALUES(?, ?, ?)";
        try {
            PreparedStatement ps = conec.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, m.getNombre());
            ps.setInt(2, m.getAnio());
            ps.setBoolean(3, m.isEstado());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                m.setIdMateria(rs.getInt(1));
            } else {
                System.out.println("No se pudo obtener el id de la materia.");
            }
            ps.close();
            System.out.println("Se guardo la materia correctamente.");
            return true;
        } catch (SQLException e) {
            System.out.println("No se pudo agregar la materia");
            return false;
        }
    }

    public void modificarMateria(Materia materia) {

        String sql = "UPDATE materia SET nombre=?, año=? WHERE idMateria =?";

        try {
            PreparedStatement ps;
            ps = conec.prepareStatement(sql);
            ps.setString(1, materia.getNombre());
            ps.setInt(2, materia.getAnio());
            ps.setInt(3, materia.getIdMateria());
            int exito = ps.executeUpdate();
            if (exito == 1) {
                JOptionPane.showMessageDialog(null, "Materia modificada");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla materia");
        }

    }

    public void eliminarMateria(int id) {
        String sql =  "DELETE FROM materia WHERE idMateria = ?";
            try {
            PreparedStatement ps = conec.prepareStatement(sql);
            ps.setInt(1, id);
            int exito = ps.executeUpdate();
            if (exito == 1) {
                JOptionPane.showMessageDialog(null, "Materia eliminada");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla materia");
        }
    }

    public Materia buscarMateria(int id) {
        String sql = "SELECT * FROM materia WHERE idMateria = ?";
        Materia materia = null;

        try {
            PreparedStatement ps = conec.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                materia = new Materia();
                materia.setIdMateria(rs.getInt("idMateria"));
                materia.setNombre(rs.getString("nombre"));
                materia.setAnio(rs.getInt("año"));
                materia.setEstado(rs.getBoolean("estado"));
            } else {
                JOptionPane.showMessageDialog(null, "No existe esa materia");
            }
            ps.close();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla materia");
        }

        return materia;

    }

    public List<Materia> listarMaterias() {

        String sql = "SELECT * FROM materia";
        ArrayList<Materia> materias = new ArrayList<>();

        try {
            PreparedStatement ps = conec.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Materia materia = new Materia();
                materia.setIdMateria(rs.getInt("IdMateria"));
                materia.setNombre(rs.getString("nombre"));
                materia.setAnio(rs.getInt("año"));
                materia.setEstado(true);

                materias.add(materia);
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla materia");
        }

        return materias;

    }

    public void bajaAltaLogica(int id, boolean boleano) {
        String query = "UPDATE materia SET estado = ? where idMateria = ?";
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
