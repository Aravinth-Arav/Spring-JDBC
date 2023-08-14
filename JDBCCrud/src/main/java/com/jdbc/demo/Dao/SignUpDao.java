package com.jdbc.demo.Dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;

import com.jdbc.demo.model.Response;
import com.jdbc.demo.model.SignUpModel;
import com.jdbc.demo.service.SignUpService;

@Component

public class SignUpDao implements SignUpService {

	Response res = new Response();
	String url = "jdbc:mysql://127.0.0.1:3306/kgm";
	String username = "root";
	String password = "av12345678";

	public Response createUser(SignUpModel values) {

		String uuid = UUID.randomUUID().toString();
		values.setsNo(uuid);
		values.setCreatedBy(uuid);
		values.setUpdatedBy(uuid);

		Date date = new Date(Calendar.getInstance().getTime().getTime());
		values.setCreatedDate(date);
		values.setUpdatedDate(date);

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			try (Connection conn = DriverManager.getConnection(url, username, password);
					Statement st = conn.createStatement();) {

				String insertQuery = "INSERT INTO kgm.user_details(sno,first_name,last_name,email,dob,gender,phoneno,password,created_by,updated_by,created_date,updated_date)"
						+ "VALUES('" + values.getsNo() + "','" + values.getFirstName() + "','" + values.getLastName()
						+ "','" + values.getEmail() + "','" + values.getDob() + "','" + values.getGender() + "',"
						+ values.getPhoneNo() + ",'" + values.getPassword() + "','" + values.getCreatedBy() + "','"
						+ values.getUpdatedBy() + "','" + values.getCreatedDate() + "','" + values.getUpdatedDate()
						+ "');";

				st.executeUpdate(insertQuery);

				res.setData("User Created Successfully");
				res.setResponse_code(200);
				res.setResponse_msg("Success");

			} catch (Exception e) {
				e.printStackTrace();

				res.setData("Cannot Create User!");
				res.setResponse_code(500);
				res.setResponse_msg("error");
			}

		} catch (Exception e) {
			e.printStackTrace();

			res.setData("Driver Class!");
			res.setResponse_code(500);
			res.setResponse_msg("error");
		}
		return res;
	}

	@SuppressWarnings("unchecked")
	public Response getUser() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			String selectQuery = "select * from user_details;";

			try (Connection conn = DriverManager.getConnection(url, username, password);
					Statement st = conn.createStatement();
					ResultSet rs = st.executeQuery(selectQuery);) {
				JSONArray jsonArray = new JSONArray();
				while (rs.next()) {
					JSONObject jsonobject=new JSONObject();
					jsonobject.put("sNo",rs.getString("sno"));
					jsonobject.put("firstName",rs.getString("first_name"));
					jsonobject.put("lastName",rs.getString("last_name"));
					jsonobject.put("email",rs.getString("email"));
					jsonobject.put("dob",rs.getDate("dob"));
					jsonobject.put("gender",rs.getString("gender"));
					jsonobject.put("phoneNo",rs.getString("phoneno"));
					jsonobject.put("password",rs.getString("password"));
					jsonobject.put("createdBy",rs.getString("created_by"));
					jsonobject.put("createdDate",rs.getDate("created_date"));
					
					jsonArray.add(jsonobject);
					
					res.setData("User fetced Successfully");
					res.setResponse_code(200);
					res.setResponse_msg("Success");
					res.setjData(jsonArray);
				}

			} catch (Exception e) {
				e.printStackTrace();
				res.setData("Cannot fetc User!");
				res.setResponse_code(500);
				res.setResponse_msg("error");
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setData("Driver Class fetc!");
			res.setResponse_code(500);
			res.setResponse_msg("error");
		}

		return res;
	}
	@SuppressWarnings("unchecked")
	public Response getOneUser(String sno) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			String selectQuery = "select * from user_details where sno='"+ sno +"';";
			
			JSONObject jsonObject=new JSONObject();

			try (Connection conn = DriverManager.getConnection(url, username, password);
					Statement st = conn.createStatement();
					ResultSet rs = st.executeQuery(selectQuery);) {
				while (rs.next()) {
					
					jsonObject.put("sNo",rs.getString("sno"));
					jsonObject.put("firstName",rs.getString("first_name"));
					jsonObject.put("lastName",rs.getString("last_name"));
					jsonObject.put("email",rs.getString("email"));
					jsonObject.put("dob",rs.getDate("dob"));
					jsonObject.put("gender",rs.getString("gender"));
					jsonObject.put("phoneNo",rs.getString("phoneno"));
					jsonObject.put("password",rs.getString("password"));
					jsonObject.put("createdBy",rs.getString("created_by"));
					jsonObject.put("createdDate",rs.getDate("created_date"));
					
					res.setData("Onedata fetched Successfully");
					res.setResponse_code(200);
					res.setResponse_msg("Success");
					res.setjData(jsonObject);
				}

			} catch (Exception e) {
				e.printStackTrace();
				res.setData("Cannot fetch Onedata!");
				res.setResponse_code(500);
				res.setResponse_msg("error");
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setData("Driver Class fetch!");
			res.setResponse_code(500);
			res.setResponse_msg("error");
		}

		return res;
	}@SuppressWarnings("unchecked")
	public Response updateUser(String sno, String email) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

		
			try (Connection conn = DriverManager.getConnection(url, username, password);
					Statement st = conn.createStatement();){
					String selectQuery = "update kgm.user_details set email_id='"+ email +"' where sno='"+ sno +"';";
					st.executeQuery(selectQuery);
				
					res.setData("Email Updated Successfully");
					res.setResponse_code(200);
					res.setResponse_msg("Success");
				}

			} catch (Exception e) {
				res.setData("Cannot Update!");
				res.setResponse_code(500);
				res.setResponse_msg("error");
				e.printStackTrace();
			}
		} catch (Exception e) {
			res.setData("Driver Class Update Err!");
			res.setResponse_code(500);
			res.setResponse_msg("error");
			e.printStackTrace();
		}

		return res;
	}

