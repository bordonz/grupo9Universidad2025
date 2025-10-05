package persistencia;

import entidades.Materia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
*
* @autor Benegas Gonzalez Cristian Rene
*/

public class MateriaData {
private Connection conec = null;

 public MateriaData(MiConexion conexion){
    this.conec = conexion.buscarConexion();
    }
 
  public void materiaNueva(Materia m){
        String query = "INSERT INTO materia (nombre, año, estado) VALUES(?, ?, ?)";
        try {
            PreparedStatement ps = conec.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, m.getNombre());
            ps.setInt(2,m.getAnio());
            ps.setBoolean(3, m.isEstado());
            ps.executeUpdate();
        
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                m.setIdMateria(rs.getInt(1));
            }else{
                System.out.println("No se pudo obtener el id de la materia.");
            }
            ps.close();
            System.out.println("Se guardo la materia correctamente.");
       }catch(SQLException e){
           System.out.println("No se pudo agregar la materia");
       }
    }
}
