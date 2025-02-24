import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class repaso1 {

    private Connection conexion;

    public void abrirConexion(String bd, String servidor, String usuario,
            String password) {
        try {
            String url = String.format("jdbc:mysql://%s:3306/%s", servidor, bd);
            // Establecemos la conexión con la BD
            this.conexion = DriverManager.getConnection(url, usuario, password);
            if (this.conexion != null) {
                System.out.println("Conectado a " + bd + " en " + servidor);
            } else {
                System.out.println("No conectado a " + bd + " en " + servidor);
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getLocalizedMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("Código error: " + e.getErrorCode());
        }
    }

    public void cerrarConexion() {
        try {
            this.conexion.close();
        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexión: " + e.getLocalizedMessage());
        }
    }

    public void ejercicio1(String cadena) throws SQLException {

        try (Statement stmt = this.conexion.createStatement()) {
            String query = "select * from alumnos";

            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                System.out.println(rs.getString("nombre"));
            }
        }

    }

    public static void main(String[] args) throws SQLException {
        repaso1 a = new repaso1();
        a.abrirConexion("add", "localhost", "root", "");
        a.ejercicio1("a");
        a.cerrarConexion();
    }
}
