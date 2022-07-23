package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.SellingDao;
import Model.Selling;

@WebServlet("/SellingController")
public class SellingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SellingController() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		String action=request.getParameter("action");
		if(action.equalsIgnoreCase("Upload Details")) {
			Selling w = new Selling();
			w.setSid(Integer.parseInt(request.getParameter("sid")));
			w.setName(request.getParameter("name"));
			w.setCategory(request.getParameter("category"));
			w.setPrice(Integer.parseInt(request.getParameter("price")));
			w.setStatus("unsold");
			System.out.println(w);
			SellingDao.insertSelling(w);
			response.sendRedirect("seller-index.jsp");
		}
	}
}