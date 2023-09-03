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

@WebServlet("/EventSearchServlet2")
public class EventSearchServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

			request.setCharacterEncoding("UTF-8");
			HttpSession session = request.getSession();
			String gpName = request.getParameter("key");
			String id = (String)session.getAttribute("id");

	        TrainingDao dao = new TrainingDao();
	        List<Training> eList2 = dao.searchEvent(id,gpName);
	        Training tr = null;

		        for(int i = 0; i < eList2.size(); i++) {
				    tr = new Training();
				    tr = eList2.get(i);
		         	String evName = tr.getEventName();
		         	if(evName.equals("")) {
		         		dao.deleteeGroupByeventName(id, evName);
		         	}
		         }
	        List<Training> tList = dao.reportAllByIdAndGpName(id,gpName);

	        eList2 = dao.searchEvent(id, gpName);
			session.setAttribute("eList2", eList2);
			session.setAttribute("tList", tList);
			session.setAttribute("gpName", gpName);

			RequestDispatcher rd = request.getRequestDispatcher("/jsp/report2.jsp");
			rd.forward(request, response);
		}
	}