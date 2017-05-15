package modelo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Daousuarios implements Daousuarios_interfaz {
	String driver = "com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/acceso_usuarios";
	Connection conn;

	public Daousuarios() {
		cargarDriver();
	}

	private void cargarDriver() {
		try {
			Class.forName(driver).newInstance();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private void getConnection() {
		try {
			conn = DriverManager.getConnection(url, "root", "");

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	public void insert(Usuario user){
		String sql = "INSERT INTO usuarios (Nombre, usuario, contraseña, direccion, telefono) "
				+ " VALUES(?, ?, ?, ?, ?);";
		getConnection();
		try{
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, user.getNombre());
			stmt.setString(2, user.getMail());
			stmt.setString(3, user.getPass());
			stmt.setString(4, user.getCodigopostal());
			stmt.setString(5, user.getTelefono());
		
			stmt.execute();
			stmt.close();
			conn.close();
		}catch(SQLException e){
			System.out.println(e.getStackTrace());
		}
	}
	public void del(Usuario user){
		String sql = "Delete From usuarios where usuario = ?;";
		getConnection();
		try{
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, user.getMail());
			stmt.execute();
			stmt.close();
			conn.close();
		}catch(SQLException e){
			System.out.println(e.getStackTrace());
		}
		
	}
	public void update(Usuario user){
		//String sql = "update usuario set contraseña = ?, Nombre=?, usuario=?, telefono=? where usuario = ?;";
		String sql= "UPDATE usuarios set contraseña = ?, Nombre=?, telefono=?, direccion=? WHERE usuario = ?;";
		getConnection();
		try{
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, user.getPass());
			stmt.setString(2, user.getNombre());
			stmt.setString(3, user.getTelefono());
			stmt.setString(4, user.getCodigopostal());
			stmt.setString(5, user.getMail());
			
			stmt.execute();
			stmt.close();
			conn.close();
		}catch(SQLException e){
			System.out.println(e.getStackTrace());
		}
	}
	public ArrayList<Usuario> selec(){
		getConnection(); 
		String sql = "Select * from usuarios;";
		ResultSet rs = null;
		ArrayList<Usuario> listado = new ArrayList<Usuario>();
		Usuario aux;
		getConnection();
		try{
			PreparedStatement stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()){
				aux = new Usuario(rs.getString(2), rs.getString(6), rs.getString(4), rs.getString(5),rs.getString(3), null);
				listado.add(aux);
			}
			rs.close();
			stmt.close();
			conn.close();
		}catch(SQLException e){
			System.out.println(e.getStackTrace());
		}
		return listado;
	}
	public ArrayList<Usuario> comprobar_admin(){
		getConnection(); 
		String sql = "Select user, pass from admin;";
		ResultSet rs = null;
		ArrayList<Usuario> listado = new ArrayList<Usuario>();
		Usuario aux;
		getConnection();
		try{
			PreparedStatement stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()){
				aux = new Usuario(rs.getString(1), rs.getString(2));
				listado.add(aux);
			}
			rs.close();
			stmt.close();
			conn.close();
		}catch(SQLException e){
			System.out.println(e.getStackTrace());
		}
		return listado;
	}
	public ArrayList<Usuario> comprobar_user(){
		getConnection(); 
		String sql = "Select usuario, contraseña from usuarios;";
		ResultSet rs = null;
		ArrayList<Usuario> listado = new ArrayList<Usuario>();
		Usuario aux;
		getConnection();
		try{
			PreparedStatement stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()){
				aux = new Usuario(rs.getString(1), rs.getString(2));
				listado.add(aux);
			}
			rs.close();
			stmt.close();
			conn.close();
		}catch(SQLException e){
			System.out.println(e.getStackTrace());
		}
		return listado;
	}
	public ArrayList<Usuario> comprobar_correo_usuario(){
		getConnection(); 
		String sql = "Select usuario from usuarios;";
		ResultSet rs = null;
		ArrayList<Usuario> listado = new ArrayList<Usuario>();
		Usuario aux;
		getConnection();
		try{
			PreparedStatement stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()){
				aux = new Usuario(rs.getString(1), null);
				listado.add(aux);
			}
			rs.close();
			stmt.close();
			conn.close();
		}catch(SQLException e){
			System.out.println(e.getStackTrace());
		}
		return listado;
	}
	public Usuario readUsuario(String email){
		
		String pass=null;
		Usuario user;
		getConnection(); 
		String sql = "Select contraseña from usuarios where usuario = ?;";
		ResultSet rs = null;
		ArrayList<Usuario> listado = new ArrayList<Usuario>();
		Usuario aux;
		getConnection();
		try{
			PreparedStatement stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			stmt.setString(1, email);
			rs.getString(1);
			rs.close();
			stmt.close();
			conn.close();
		}catch(SQLException e){
			System.out.println(e.getStackTrace());
		}
		user= new Usuario(email,pass);
		return user;
		
	}
}


