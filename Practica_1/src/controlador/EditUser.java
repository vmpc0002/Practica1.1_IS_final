package controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.Daousuarios;
import modelo.Usuario;

/**
 * Servlet implementation class EditUser
 */
public class EditUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditUser() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void EnviarDatos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nombre,mail,pass, telefono,codigopostal;
		
		nombre=request.getParameter("txtNombre");
		mail=request.getParameter("txtCorreo");
		pass=request.getParameter("txtPass");
		telefono=request.getParameter("txtTelefono");
		codigopostal=request.getParameter("txtCodigopostal");
		Usuario u = new Usuario(mail,pass,nombre,telefono,codigopostal,null);
		HttpSession session= request.getSession(true);
		session.setAttribute("Usuario", u);
		Daousuarios basededatos= new Daousuarios();
		basededatos.update(u);
		
		
		
	
    	request.getRequestDispatcher("Menu.jsp").forward(request,response);
	}
 
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	EnviarDatos(request,response);
}

/**
 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
 */
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// TODO Auto-generated method stub
	doGet(request, response);
}
	

}
