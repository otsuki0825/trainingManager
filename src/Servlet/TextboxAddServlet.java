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

import entity.Training;

@WebServlet("/TextboxAddServlet")
public class TextboxAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

			request.setCharacterEncoding("UTF-8");
			HttpSession session = request.getSession();
			List<Training> eList = (List<Training>)session.getAttribute("eList");
	        String[]  newEList = request.getParameterValues("evName2");

	        if(!(eList.isEmpty())){
				Training tr = null;
				for(int i = 0; i < newEList.length; i++) {
					tr = new Training();
					String newEvName = newEList[i];
					tr.setEventName(newEvName);
					eList.set(i,tr);
				}
				for(int i = 0; i < 3; i++) {
					tr = new Training();
					tr.setEventName("");
					eList.add(tr);
				}
			}
	        session.setAttribute("eList", eList);
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/eventUpdateDelete.jsp");
			rd.forward(request, response);
	}
}