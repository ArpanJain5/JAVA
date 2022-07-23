package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.CustomerDao;
import Model.Customer;

@WebServlet("/CustomerController")
public class CustomerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CustomerController() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		String action=request.getParameter("action");
		if(action.equalsIgnoreCase("register")) {
			Customer u = new Customer();
			u.setName(request.getParameter("name"));
			u.setContact(Long.parseLong(request.getParameter("contact")));
			u.setAddress(request.getParameter("address"));
			u.setPincode(Integer.parseInt(request.getParameter("pincode")));
			u.setEmail(request.getParameter("email"));
			u.setPassword(request.getParameter("password"));
			System.out.println(u);
			CustomerDao.insertCustomer(u);
			response.sendRedirect("customer-login.jsp");
		}
		else if(action.equalsIgnoreCase("login")) {
			Customer u = new Customer();
			u.setEmail(request.getParameter("email"));
			u.setPassword(request.getParameter("password"));
			Customer u1 = CustomerDao.checkCustomerlogin(u);
			System.out.println(u1);
			if(u1 == null) {
				request.setAttribute("msg", "Email ID or password is incorrect");
				request.getRequestDispatcher("customer-login.jsp").forward(request, response);
			}
			else {
				HttpSession session = request.getSession();
				session.setAttribute("data", u1);
				request.getRequestDispatcher("customer-index.jsp").forward(request, response);
			}
		}
		else if(action.equalsIgnoreCase("Change Password")) {
			Customer u = new Customer();
			u.setEmail(request.getParameter("email"));
			u.setNpassword(request.getParameter("npassword"));
			String np = request.getParameter("npassword");
			String cnp = request.getParameter("cnpassword");
			System.out.println(request.getParameter("email"));
			System.out.println(np+" : "+cnp);
			if(np.equals(cnp)) {
				CustomerDao.changePassword(u);
				System.out.println("done");
				response.sendRedirect("customer-index.jsp");
			}
			else {
				request.setAttribute("msg", "New Password and Confirm New Password does not match");
				request.getRequestDispatcher("customer-change-password.jsp").forward(request, response);
			}
		}
		else if(action.equalsIgnoreCase("Update")) {
			Customer u = new Customer();
			u.setId(Integer.parseInt(request.getParameter("id")));
			u.setName(request.getParameter("name"));
			u.setContact(Long.parseLong(request.getParameter("contact")));
			u.setAddress(request.getParameter("address"));
			u.setPincode(Integer.parseInt(request.getParameter("pincode")));
			u.setEmail(request.getParameter("email"));
			CustomerDao.updateCustomer(u);
			System.out.println(u);
			HttpSession session = request.getSession();
			session.setAttribute("data", u);
			request.getRequestDispatcher("customer-index.jsp").forward(request, response);
		}
	}
}