package Servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.TrainingDao;
import entity.Training;

@WebServlet("/trainingSearchServlet")
public class trainingSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String gpName = (String)session.getAttribute("gpName");
		String evName = request.getParameter("key2");
		String id = (String)session.getAttribute("id");

	    TrainingDao dao = new TrainingDao();
	    List<Training> tList = dao.reportAllByeventName(id,gpName,evName);
	    session.setAttribute("tList", tList);

		RequestDispatcher rd = request.getRequestDispatcher("/jsp/report2.jsp");
		rd.forward(request, response);
	}
}