package controlador;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.Articulos;
import modelo.Daousuarios;
import modelo.Usuario;

/**
 * Servlet implementation class Comprueba_registro
 */
public class Comprueba_registro extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Comprueba_registro() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session=request.getSession();
    	Daousuarios aux=new Daousuarios();
    	ArrayList<Usuario> user;
	    
    	String nombre,mail,pass, telefono,codigopostal;
		nombre=request.getParameter("txtNombre");
		mail=request.getParameter("txtCorreo");
		pass=request.getParameter("txtPass");
		telefono=request.getParameter("txtTelefono");
		codigopostal=request.getParameter("txtCodigopostal");
		
    	user=aux.comprobar_correo_usuario();
    	int cont=0;
    	for (int i=0;i<user.size();i++){
    		if(mail.equals(user.get(i).getEmail())){
    			cont=1;//email ya registrado con anterioridad
    		}
    	}
    	if(cont==1){
    		request.getRequestDispatcher("ErrorRegistro.jsp").forward(request,response);
    	}
    	else{
    		Usuario u = new Usuario(mail,pass,nombre,telefono,codigopostal,null);
    		session.setAttribute("Usuario", u);
    		Daousuarios basededatos= new Daousuarios();
    		basededatos.insert(u);
    		String[] nombres;
    		nombres= new String[]{"Intel i7-6700K 4.0Ghz Box","Kingston SSDNow 120GB","Asus H81M-P PLUS","GeForce GTX 1080 XDDR5",
    				"NOX Coolbay SX USB 3.0","Intel i5-6600K 3.5Ghz","MSI Z270 Gaming Pro","EVGA GeForce GTX 1070"};
    		
    		ArrayList<Articulos> carrito = new ArrayList<Articulos>();
    		for (int i=0;i<8;i++){
    			Articulos p=new Articulos("producto"+i,nombres[i],0);
    			carrito.add(p);
    			session.setAttribute("carrito", carrito);
    		}
    		
    		request.getRequestDispatcher("Menu.jsp").forward(request,response);
    		

    	}
    	
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		processRequest(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
