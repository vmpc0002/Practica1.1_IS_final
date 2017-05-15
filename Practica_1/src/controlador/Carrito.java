package controlador;

import java.io.IOException;
import java.util.ArrayList;
 
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.org.glassfish.gmbal.Description;

import modelo.Usuario;



public class Carrito extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Carrito() {
       
    }
    
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		Carrito itemsGuardados =(Carrito) session.getAttribute("itemsGuardados");
		if (itemsGuardados != null){
			//hacer_algo_con(�itemsGuardados�); //Por ejemplo, presentarlos al usuario
			request.getRequestDispatcher("Menu.jsp");
		}
			else {
				itemsGuardados = new Carrito();
				//hacer_algo_adicional_con (�itemsGuardados�); //Qu� hacer con el carrito vac�o
		}
	    	request.getRequestDispatcher("Carrito.jsp").forward(request,response);
		
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		/*Llamamos al modulo en funcon de unos credenciales u otros redigira a menu.vista o error.vista*/
		processRequest(request,response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}

