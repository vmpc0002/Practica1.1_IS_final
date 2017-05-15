package controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.Daousuarios;
import modelo.Usuario;

/**
 * Servlet implementation class Inicio
 */
public class Inicio extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Inicio() {
        super();
        // TODO Auto-generated constructor stub
    }
	
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String url="index.jsp";
    	HttpSession session=request.getSession();
		Usuario usuario = (Usuario) session.getAttribute("login");
		if(usuario!=null){
		Cookie[] cookies=request.getCookies();
		String cookieName="emailCookie";
		String emailAddress="";
			if(cookies!=null){
				for(Cookie cookie: cookies){
					if(cookieName.equals(cookie.getName())){
						emailAddress=cookie.getValue();
						
					}
					if(!emailAddress.equals("")){
					//	Daousuarios bbdd=new Daousuarios();
						//Usuario usuarioreg=bbdd.readUsuario(emailAddress);
						//session.setAttribute("login", usuarioreg);
						System.out.println("entra");
						System.out.println(emailAddress);
						url="Menu.jsp";
					}else System.out.println("no entra");
				}
			}
			System.out.println("pasa de las cookies");
		}
		System.out.println("el usuario no está null");
    	request.getRequestDispatcher(url).forward(request,response);
	
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
