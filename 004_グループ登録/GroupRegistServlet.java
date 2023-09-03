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

@WebServlet("/GroupRegistServlet")
public class GroupRegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

   	protected void doPost(HttpServletRequest request, HttpServletResponse response)
   			throws ServletException, IOException {
   		request.setCharacterEncoding("UTF-8");
		String gpName = request.getParameter("gpName");
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");

		if(gpName == "") {
			request.setAttribute("ng", "グループ名を入力してください");

			RequestDispatcher rd = request.getRequestDispatcher("/jsp/groupRegist.jsp");
			rd.forward(request, response);
		}else {
	        TrainingDao dao = new TrainingDao();
	        dao.newGroupRegist(id, gpName);

	        List<Training> gpList = dao.searchGroup(id);
			session.setAttribute("gpList", gpList);

			RequestDispatcher rd = request.getRequestDispatcher("/jsp/home.jsp");
			rd.forward(request, response);
		}
	}
}
