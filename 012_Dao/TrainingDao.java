package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entity.Training;



public class TrainingDao {
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost/trainingManager";
	private static final String USER = "root";
	private static final String PASSWORD = "";

	Date date = new Date();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	String today = sdf.format(date);

	private Connection getConnection() throws
	    ClassNotFoundException, SQLException{
		Class.forName(DRIVER);
		Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
		return con;
	}

	public void newUserRegist(String id, String password) {
		try(
				Connection con = getConnection();
				PreparedStatement pstmt = con.prepareStatement
				("INSERT INTO user VALUES(?,?)");
		     ){
					pstmt.setString(1, id);
					pstmt.setString(2, password);

		        int rows = pstmt.executeUpdate();


			}catch(SQLException | ClassNotFoundException  e){
				e.printStackTrace();

			}
	}

	public Training searchUser(String id, String password) {
		Training tr = null;
		try(
				Connection con = getConnection();
				PreparedStatement pstmt = con.prepareStatement
				("SELECT * FROM user WHERE id = ? AND password = ?");
		     ){
					pstmt.setString(1, id);
					pstmt.setString(2, password);

		        ResultSet rs = pstmt.executeQuery();

		        if(rs.next()) {
		        	tr = new Training();
		        	tr.setId(rs.getString(1));
		        	tr.setPassword(rs.getString(2));
		        }
			}catch(SQLException | ClassNotFoundException  e){
				e.printStackTrace();
			}
	 return tr;
	}
	public List<Training> searchGroup(String id) {
		List<Training> gpList = new ArrayList<Training>();
		Training tr = null;
		try(
				Connection con = getConnection();
				PreparedStatement pstmt = con.prepareStatement
				("SELECT group_name FROM trainingGroup WHERE id = ?");
		     ){
					pstmt.setString(1, id);

		        ResultSet rs = pstmt.executeQuery();

		        while(rs.next()) {
		        	tr = new Training();
		        	tr.setGroupName(rs.getString(1));
		        	gpList.add(tr);
		        }
			}catch(SQLException | ClassNotFoundException  e){
				e.printStackTrace();
			}
	 return gpList;
	}

	public List<Training> searchEvent(String id, String gpName) {
		List<Training> eList = new ArrayList<Training>();
		Training tr = null;
		try(
				Connection con = getConnection();
				PreparedStatement pstmt = con.prepareStatement
				("SELECT event_name FROM eventGroup WHERE id =? AND group_name = ?");
		     ){
					pstmt.setString(1, id);
					pstmt.setString(2, gpName);

		        ResultSet rs = pstmt.executeQuery();

		        while(rs.next()) {
		        	tr = new Training();
		        	tr.setEventName(rs.getString(1));
		        	eList.add(tr);
		        }
			}catch(SQLException | ClassNotFoundException  e){
				e.printStackTrace();
			}
	 return eList;
	}
	public void trainingRegist(String id, String gpName, String eventName, int kg, int reps, int sets, int count) {
		try(
				Connection con = getConnection();
				PreparedStatement pstmt = con.prepareStatement
				("INSERT INTO trainingEvent VALUES(?,?,?,?,?,?,?,?)");
		     ){
					pstmt.setString(1, id);
					pstmt.setString(2, gpName);
					pstmt.setString(3, eventName);
					pstmt.setInt(4, kg);
					pstmt.setInt(5, reps);
					pstmt.setInt(6, sets);
					pstmt.setInt(7, count);
					pstmt.setString(8, today);

		        int rows = pstmt.executeUpdate();


			}catch(SQLException | ClassNotFoundException  e){
				e.printStackTrace();

			}
	}
	public void trainingUpDate(String id, String eventName, int kg, int sets, int count) {
		try(
				Connection con = getConnection();
				PreparedStatement pstmt = con.prepareStatement
				("UPDATE trainingEvent SET sets = ? , count = ? WHERE id = ? AND event_name = ? AND kg = ? AND dt = ?" );
		     ){
					pstmt.setInt(1, sets);
					pstmt.setInt(2, count);
					pstmt.setString(3, id);
					pstmt.setString(4, eventName);
					pstmt.setInt(5, kg);
					pstmt.setString(6, today);

		        int rows = pstmt.executeUpdate();


			}catch(SQLException | ClassNotFoundException  e){
				e.printStackTrace();

			}
	}
	public Training trainingComparison(String id, String eventName,int kg, int reps, int preSets) {
		Training tr = null;
		try(
				Connection con = getConnection();
				PreparedStatement pstmt = con.prepareStatement
				("SELECT * FROM trainingEvent WHERE id = ? AND event_name = ? AND kg = ? AND reps = ? AND sets = ? AND dt = ?");
		     ){
					pstmt.setString(1, id);
					pstmt.setString(2, eventName);
					pstmt.setInt(3, kg);
					pstmt.setInt(4, reps);
					pstmt.setInt(5, preSets);
					pstmt.setString(6, today);

		        ResultSet rs = pstmt.executeQuery();

		        if(rs.next()) {
		        	tr = new Training();
		        	tr.setEventName(rs.getString(3));
		        	tr.setKg(rs.getInt(4));
		        	tr.setReps(rs.getInt(5));
		        	tr.setSets(rs.getInt(6));
		        	tr.setCount(rs.getInt(7));
		        	tr.setDt(rs.getString(8));


		        }


			}catch(SQLException | ClassNotFoundException  e){
				e.printStackTrace();

			}
		return tr;
	}
	public void newGroupRegist(String id, String gpName) {
		try(
				Connection con = getConnection();
				PreparedStatement pstmt = con.prepareStatement
				("INSERT INTO trainingGroup VALUES(?,?)");
		     ){
					pstmt.setString(1, id);
					pstmt.setString(2, gpName);

		        int rows = pstmt.executeUpdate();


			}catch(SQLException | ClassNotFoundException  e){
				e.printStackTrace();

			}
	}
	public void eventNameEUpdate(String id, String evName, String newEvName) {
		try(
				Connection con = getConnection();
				PreparedStatement pstmt = con.prepareStatement
				("UPDATE trainingEvent SET event_name = ? WHERE id = ? AND event_name = ?" );
		     ){
					pstmt.setString(1, newEvName);
					pstmt.setString(2, id);
					pstmt.setString(3, evName);

		        int rows = pstmt.executeUpdate();


			}catch(SQLException | ClassNotFoundException  e){
				e.printStackTrace();

			}
	}
	public void eventNameGUpdate(String id, String evName, String newEvName) {
		try(
				Connection con = getConnection();
				PreparedStatement pstmt = con.prepareStatement
				("UPDATE eventGroup SET event_name = ? WHERE id = ? AND event_name = ?" );
		     ){
					pstmt.setString(1, newEvName);
					pstmt.setString(2, id);
					pstmt.setString(3, evName);

		        int rows = pstmt.executeUpdate();


			}catch(SQLException | ClassNotFoundException  e){
				e.printStackTrace();

			}
	}
	public void eventNameGRegist(String id,String gpName, String newEvName) {
		try(
				Connection con = getConnection();
				PreparedStatement pstmt = con.prepareStatement
				("INSERT INTO eventGroup VALUES(?,?,?)");
		     ){
					pstmt.setString(1, id);
					pstmt.setString(2, gpName);
					pstmt.setString(3, newEvName);

		        int rows = pstmt.executeUpdate();


			}catch(SQLException | ClassNotFoundException  e){
				e.printStackTrace();

			}
	}
	public void deletetGroup(String id, String gpName) {
		try(
				Connection con = getConnection();
				PreparedStatement pstmt = con.prepareStatement
				("DELETE FROM trainingGroup WHERE id =? AND group_name = ?");
		     ){
					pstmt.setString(1, id);
					pstmt.setString(2, gpName);

		        int rows = pstmt.executeUpdate();


			}catch(SQLException | ClassNotFoundException  e){
				e.printStackTrace();

			}
	}
	public void deleteeGroup(String id, String gpName) {
		try(
				Connection con = getConnection();
				PreparedStatement pstmt = con.prepareStatement
				("DELETE FROM eventGroup WHERE id = ? AND group_name = ?");
		     ){
					pstmt.setString(1, id);
					pstmt.setString(1, gpName);

		        int rows = pstmt.executeUpdate();


			}catch(SQLException | ClassNotFoundException  e){
				e.printStackTrace();

			}
	}
	public void deleteeGroupByeventName(String id, String evName) {
		try(
				Connection con = getConnection();
				PreparedStatement pstmt = con.prepareStatement
				("DELETE FROM eventGroup WHERE id = ? AND event_name = ?");
		     ){
			        pstmt.setString(1, id);
					pstmt.setString(1, evName);

		        int rows = pstmt.executeUpdate();


			}catch(SQLException | ClassNotFoundException  e){
				e.printStackTrace();

			}
	}

	public List<Training>  reportAllByeventName(String id, String gpName, String eventName) {
		List<Training> rlist = new ArrayList<>();
		Training tr = null;
		try(
				Connection con = getConnection();
				PreparedStatement pstmt = con.prepareStatement
				("SELECT * FROM trainingEvent WHERE id = ? AND group_name = ? AND event_name = ? ORDER BY dt ASC, event_name, sets" );
		     ){
					pstmt.setString(1, id);
					pstmt.setString(2, gpName);
					pstmt.setString(3, eventName);

		        ResultSet rs = pstmt.executeQuery();

		        while(rs.next()) {
		        	tr = new Training();
		        	tr.setEventName(rs.getString(3));
		        	tr.setKg(rs.getInt(4));
		        	tr.setReps(rs.getInt(5));
		        	tr.setSets(rs.getInt(6));
		        	tr.setCount(rs.getInt(7));
		        	tr.setDt(rs.getString(8));
                    rlist.add(tr);

		        }


			}catch(SQLException | ClassNotFoundException  e){
				e.printStackTrace();

			}
		return rlist;
	}

	public List<Training>  reportAllByIdAndGpName(String id, String gpName) {
		List<Training> tList = new ArrayList<>();
		Training tr = null;
		try(
				Connection con = getConnection();
				PreparedStatement pstmt = con.prepareStatement
				("SELECT * FROM trainingEvent WHERE id = ? AND group_name = ? ORDER BY dt ASC, event_name, sets");
		     ){
					pstmt.setString(1, id);
					pstmt.setString(2, gpName);

		        ResultSet rs = pstmt.executeQuery();

		        while(rs.next()) {
		        	tr = new Training();
		        	tr.setEventName(rs.getString(3));
		        	tr.setKg(rs.getInt(4));
		        	tr.setReps(rs.getInt(5));
		        	tr.setSets(rs.getInt(6));
		        	tr.setCount(rs.getInt(7));
		        	tr.setDt(rs.getString(8));
		        	tList.add(tr);

		        }


			}catch(SQLException | ClassNotFoundException  e){
				e.printStackTrace();

			}
		return tList;
	}

}
