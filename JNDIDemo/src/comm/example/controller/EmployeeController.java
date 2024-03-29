package comm.example.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import comm.example.model.Employee;
import comm.example.service.EmployeeService;
import comm.example.service.EmployeeServiceImpl;

@SuppressWarnings("serial")
@WebServlet("/controller.do")
public class EmployeeController extends HttpServlet {

	private int eId;
	private String firstName, lastName;
	private int salary;

	private EmployeeServiceImpl eService;

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		eService = new EmployeeServiceImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}

	protected void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
		eId = Integer.parseInt(req.getParameter("e_id"));

		firstName = req.getParameter("e_fname");
		lastName = req.getParameter("e_lname");
		salary = Integer.parseInt(req.getParameter("e_sal"));
		Employee employee = new Employee(eId, firstName, lastName, salary);
		int i=eService.createEmployee(employee);
		 
		if(i==0)
		{
             req.setAttribute("ERROR","unable to add data" );
			
			RequestDispatcher view=req.getRequestDispatcher("/errpage.view");
			view.forward(req,resp);
		}
		else
		{
req.setAttribute("Success",employee );
			
			RequestDispatcher view=req.getRequestDispatcher("/successpage.view");
			view.forward(req,resp);
		}

	}

}
