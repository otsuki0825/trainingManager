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

@WebServlet("/EventUpdateDeleteServlet")
public class EventUpdateDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		List<Training> eList = (List<Training>)session.getAttribute("eList");
		String gpName = (String)session.getAttribute("gpName");
		String addS = (String)session.getAttribute("add");
		String id = (String)session.getAttribute("id");
		int add = 5;
		if(addS != null ) {
			add = Integer.parseInt(addS);
		}
        String[]  newEList = request.getParameterValues("evName1");
        TrainingDao dao = new TrainingDao();
		Training tr = null;
		//種目登録がされていた場合
		if(!(eList.isEmpty())){
			//テキストボックスの値を更新リストに格納

			//新しい種目の数、更新前の種目の数、その差分を格納
			int num1 = newEList.length;
			int num2 = eList.size();
			int num3 = 0;
			if(num1 > num2) {
				num3 = num1 - num2;
			}
			//差分の分だけ、更新前リストに要素追加して、要素数そろえる
			for(int i = 0; i < num3; i++) {
				tr = new Training();
				tr.setEventName("");
				eList.add(tr);
			}
			//更新リストと更新前リストを比較して差があれば、更新
			for(int i = 0; i < num1; i++) {
				tr = new Training();
				tr = eList.get(i);
				String newEvName = newEList[i];
				String evName = tr.getEventName();
				//更新リストに値がある場合
				if(newEvName != "") {
					//更新リストに値があるが、更新前リストに値がない場合
					if(evName == "") {
						dao.eventNameGRegist(id,gpName, newEvName);
					//更新リストと更新前リストがちがう場合
					}else if (!(newEvName.equals(evName))){
						dao.eventNameEUpdate(id, evName,newEvName);
						dao.eventNameGUpdate(id, evName,newEvName);
				    }
				//更新前リストに値があるが、更新リストに値がない場合
				}else if(evName != "") {
					dao.eventNameEUpdate(id,evName,newEvName);
					dao.eventNameGUpdate(id,evName,newEvName);
				}
			}
		//種目が登録されていない場合、
		}else{
			//更新リストの種目を登録していく
			for(int i = 0; i < add; i++) {
				String newEvName = newEList[i];
				if(!(newEvName.equals(""))) {
					dao.eventNameGRegist(id, gpName, newEvName);
				}
			}
		}
		session.setAttribute("eList", eList);
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/home.jsp");
		rd.forward(request, response);
	}
}
