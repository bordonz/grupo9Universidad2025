package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MiConexion {

    private String url;
    private String usuario;
    private String password;

    private static Connection conexion = null;

    public MiConexion(String url, String usuario, String password) {
        this.url = url;
        this.usuario = usuario;
        this.password = password;
    }

    public Connection buscarConexion() {
        if (conexion == null) {
            try {
                Class.forName("org.mariadb.jdbc.Driver");
                conexion = DriverManager.getConnection(url, usuario, password);
            } catch (SQLException | ClassNotFoundException ex) {
                System.out.println("No se puede conectar o no se puede cargar el driver.Error: " + ex.getMessage());
            }
        }
        return conexion;
    }
}
