package modelo;
import java.util.ArrayList;

public interface Daousuarios_interfaz {
	public void insert(Usuario user);
	public void del(Usuario user);
	public void update(Usuario user);
	public ArrayList<Usuario> selec();
	public ArrayList<Usuario> comprobar_admin();
	public ArrayList<Usuario> comprobar_user();
	public ArrayList<Usuario> comprobar_correo_usuario();
	public Usuario readUsuario(String email);
}
