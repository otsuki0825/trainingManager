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

@WebServlet("/GpListServlet")
public class GpListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		TrainingDao dao = new TrainingDao();
		List<Training> gpList = dao.searchGroup(id);
		Training tr = null;

		 for(int i = 0; i < gpList.size(); i++) {
			    tr = new Training();
	         	String gpName = tr.getGroupName();
	         	if(gpName == "") {
	         		dao.deleteeGroup(id, gpName);
	         	}
	     }
		 gpList = dao.searchGroup(id);
		 session.setAttribute("gpList", gpList);

		 RequestDispatcher rd = request.getRequestDispatcher("/jsp/home.jsp");
			rd.forward(request, response);
	}
}
