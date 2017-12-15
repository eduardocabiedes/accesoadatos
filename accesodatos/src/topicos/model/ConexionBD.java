
package topicos.model;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;


public class ConexionBD {
    public static final String url = "jdbc:mysql://localhost:3306/Disqueras";
      public static final String user = "root";
       public static final String password = "";
    public com.mysql.jdbc.Connection conn;
    public com.mysql.jdbc.Statement  stat;
    public ResultSet  res;
 
    
    public ConexionBD(){        
        
    }
    
    public void conectar(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.conn = (Connection) DriverManager.getConnection(url,user,password);
            System.out.println("Conexión exitosa");
            
            this.stat = (Statement) this.conn.createStatement();
            
        } catch (Exception e){
            System.out.println("Error en iniciar conexión: "+e.getMessage());
        }
    }
}
