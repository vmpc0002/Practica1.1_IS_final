package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
 
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.annotation.WebServlet;
import com.sun.org.glassfish.gmbal.Description;

import modelo.Daousuarios;
import modelo.Usuario;

// TODO BORRAR CLASE

public class ValidarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ValidarServlet() {
       
    }
    
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	
		String email, pass;
		HttpSession session=request.getSession();
		//Se leen los parametros
		email= request.getParameter("txtMail");
		pass=request.getParameter("txtPass");

		
		//Se crea el objeto Usuario
		Usuario aux= new Usuario(email,pass);
		ArrayList<Usuario> listado_admin = new ArrayList<Usuario>();
		ArrayList<Usuario> listado_user = new ArrayList<Usuario>();
		Daousuarios admin= new Daousuarios();
		Daousuarios user=new Daousuarios();
		listado_admin=admin.comprobar_admin();
		listado_user=user.comprobar_user();
		Daousuarios basededatos = new Daousuarios();
		String url="/ErrorJSP.jsp";
		
		System.out.println("Comprobamos usuario");
		
		for(int i=0;i<listado_admin.size();i++){
			if(aux.getEmail().equals(listado_admin.get(i).getEmail())){
				if(aux.getPass().equals(listado_admin.get(i).getPass())){
					url = "/Administrador.jsp";
					ArrayList<Usuario> usuario = basededatos.selec();
					session.setAttribute("usuario",usuario);
					request.getRequestDispatcher(url).forward(request, response);
				}
				
			}
		}
		
		for(int i=0;i<listado_user.size();i++){
			if(aux.getEmail().equals(listado_user.get(i).getEmail())){
				if(aux.getPass().equals(listado_user.get(i).getPass())){
					url="/Menu.jsp";
					session.setAttribute("login", aux);
					Cookie c=new Cookie("emailCookie", email);
					c.setMaxAge(60*60);
					response.addCookie(c);
					
				}
				
			}
			
		}
		
		request.getRequestDispatcher(url).forward(request, response);
		
	}

	
	
	/*Metodo doGet donde se hace una llamada a todas las funciones*/
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		/*Llamamos al modulo en funcon de unos credenciales u otros redigira a menu.vista o error.vista*/
		processRequest(request,response);
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
