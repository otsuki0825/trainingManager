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

@WebServlet("/EventSearchServlet")
public class EventSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			request.setCharacterEncoding("UTF-8");
			HttpSession session = request.getSession();
			 List<Training> gpList = (List<Training>)session.getAttribute("gpList");
				String gpName = request.getParameter("gpName");
				String id = (String)session.getAttribute("id");

				session.setAttribute("gpName", gpName);

		        TrainingDao dao = new TrainingDao();
		        List<Training> eList = dao.searchEvent(id,gpName);
		        Training tr = null;

		        for(int i = 0; i < eList.size(); i++) {
				    tr = new Training();
				    tr = eList.get(i);
		         	String evName = tr.getEventName();
		         	if(evName.equals("")) {
		         		dao.deleteeGroupByeventName(id, evName);
		         	}
		         }
		        eList = dao.searchEvent(id, gpName);
				session.setAttribute("eList", eList);

			RequestDispatcher rd = request.getRequestDispatcher("/jsp/eventInfo.jsp");
			rd.forward(request, response);
		}


	}