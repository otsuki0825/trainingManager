package Servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.TrainingDao;
import entity.Training;

@WebServlet("/TrainingServlet")
public class TrainingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Date date = new Date();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	String today = sdf.format(date);

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

			request.setCharacterEncoding("UTF-8");
			String kgS = (String)request.getParameter("kg");
			String repsS = (String)request.getParameter("reps");

			HttpSession session = request.getSession();
			String gpName = (String)session.getAttribute("gpName");
			String eventName = (String)session.getAttribute("eventName");
			String setsS = (String)session.getAttribute("sets");
			String id = (String)session.getAttribute("id");

			if(kgS == "" || repsS == "") {
				request.setAttribute("ng", "㎏またはrepsを入力してください");

				RequestDispatcher rd = request.getRequestDispatcher("/jsp/training.jsp");
				rd.forward(request, response);
			}else {
				int kg = Integer.parseInt(kgS);
				int reps = Integer.parseInt(repsS);
				int sets = Integer.parseInt(setsS);

				TrainingDao dao = new TrainingDao();
				Training tr = new Training();

	            int count = 1;
				int preSets = sets - 1;

				String preEventName = null;
	            int preKg = 0;
	            int preReps = 0;
	            int preCount = 0;
	            String preDt = null;

				if(sets > 1) {
					tr = dao.trainingComparison(id,eventName, kg, reps, preSets);
	                if(tr != null) {
						preEventName = tr.getEventName();
			            preKg = tr.getKg();
			            preReps = tr.getReps();
			            preSets = tr.getSets();
			            preCount = tr.getCount();
			            preDt = tr.getDt();
					}else {
						preEventName = "hoge";
			            preKg = 0;
			            preReps = 0;
			            preCount = 0;
			            preDt = "hoge";
					}
				}
				if(sets <= 1) {
					dao.trainingRegist(id, gpName, eventName, kg, reps, sets, count);
					sets++;
					setsS = String.valueOf(sets);
					session.setAttribute("sets", setsS);
				}else if (preEventName.equals(eventName) && preKg == kg && preReps == reps  && preDt.equals(today)){
					count = preCount + 1;
					dao.trainingUpDate(id,eventName, kg, sets,count);
					sets++;
					setsS = String.valueOf(sets);
					session.setAttribute("sets", setsS);
				}else {
					count = 1;
					dao.trainingRegist(id, gpName, eventName, kg, reps, sets, count);
					sets++;
					setsS = String.valueOf(sets);
					session.setAttribute("sets", setsS);
				}
				RequestDispatcher rd = request.getRequestDispatcher("/jsp/training.jsp");
				rd.forward(request, response);
			}
        }
}