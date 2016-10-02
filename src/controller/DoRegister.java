package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Customer;
import service.CustomerService;

/**
 * Servlet implementation class DoRegister
 */
@WebServlet("/doRegister")
public class DoRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DoRegister() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String email = request.getParameter("email");

		CustomerService service = (CustomerService) CustomerService.getInstance();
		Customer customer = new Customer(id, password, name, gender, email);
		
		String page;
		
		//모든 값이 입력 되지 않았으면 registerFail
		if(id==null || password==null || name==null || gender==null || email==null){
			page = "/view/registerFail.jsp";
			request.setAttribute("id", id);
		}
		
		//로그인 아이디가 중복이면 registerFail
		else if(service.findCustomer(id) != null){
			page = "/view/registerFail.jsp";
			request.setAttribute("id", id);
		}
		
		//모든 값이 입력 되었으면 해당하는 값들로 회원가입 registerSuccess 
		else{
			service.addCustomer(customer);
			page = "/view/registerSuccess.jsp";
			request.setAttribute("customer", customer);
		}

		// 해당하는 page로 forward 시킴
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}

}
