package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.SellerDao;
import Model.Seller;

@WebServlet("/SellerController")
public class SellerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SellerController() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		String action=request.getParameter("action");
		if(action.equalsIgnoreCase("register")) {
			Seller v = new Seller();
			v.setName(request.getParameter("name"));
			v.setContact(Long.parseLong(request.getParameter("contact")));
			v.setAddress(request.getParameter("address"));
			v.setPincode(Integer.parseInt(request.getParameter("pincode")));
			v.setEmail(request.getParameter("email"));
			v.setPassword(request.getParameter("password"));
			System.out.println(v);
			SellerDao.insertSeller(v);
			response.sendRedirect("seller-login.jsp");
		}
		else if(action.equalsIgnoreCase("login")) {
			Seller v = new Seller();
			v.setEmail(request.getParameter("email"));
			v.setPassword(request.getParameter("password"));
			Seller v1 = SellerDao.checkSellerlogin(v);
			System.out.println(v1);
			if(v1 == null) {
				request.setAttribute("msg", "Email ID or password is incorrect");
				request.getRequestDispatcher("seller-login.jsp").forward(request, response);
			}
			else {
				HttpSession session = request.getSession();
				session.setAttribute("data", v1);
				request.getRequestDispatcher("seller-index.jsp").forward(request, response);
			}
		}
		else if(action.equalsIgnoreCase("Change Password")) {
			Seller v = new Seller();
			v.setEmail(request.getParameter("email"));
			v.setNpassword(request.getParameter("npassword"));
			String np = request.getParameter("npassword");
			String cnp = request.getParameter("cnpassword");
			System.out.println(request.getParameter("email"));
			System.out.println(np+" : "+cnp);
			if(np.equals(cnp)) {
				SellerDao.changePassword(v);
				System.out.println("done");
				response.sendRedirect("seller-index.jsp");
			}
			else {
				request.setAttribute("msg", "New Password and Confirm New Password does not match");
				request.getRequestDispatcher("seller-change-password.jsp").forward(request, response);
			}
		}
		else if(action.equalsIgnoreCase("update")) {
			Seller v = new Seller();
			v.setId(Integer.parseInt(request.getParameter("id")));
			v.setName(request.getParameter("name"));
			v.setContact(Long.parseLong(request.getParameter("contact")));
			v.setAddress(request.getParameter("address"));
			v.setPincode(Integer.parseInt(request.getParameter("pincode")));
			v.setEmail(request.getParameter("email"));
			SellerDao.updateSeller(v);
			System.out.println(v);
			HttpSession session = request.getSession();
			session.setAttribute("data", v);
			request.getRequestDispatcher("seller-index.jsp").forward(request, response);
		}
	}
}