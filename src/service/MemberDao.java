package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import model.Member;
import util.JdbcConection;

public class MemberDao {

	public int insertMember(HttpServletRequest request) {
	
		Connection con = JdbcConection.getConnection();
		PreparedStatement pstmt = null;
		String sql ="insert into member " + " values(?,?,?,?,?,?,?)";
		
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("id"));
			pstmt.setString(2, request.getParameter("pass"));
			pstmt.setString(3, request.getParameter("name"));
			pstmt.setInt(4,Integer.parseInt(request.getParameter("gender")));
			pstmt.setString(5, request.getParameter("tel"));
			pstmt.setString(6, request.getParameter("email"));
			pstmt.setString(7, request.getParameter("picture"));
			
			return pstmt.executeUpdate();
			
		
		} catch (SQLException e) {
				e.printStackTrace();
		}finally {
			
			JdbcConection.close(con, pstmt, null);
		}
		
		return 0;}
	
	public Member selectOne(String id) {
		Connection con = JdbcConection.getConnection();
		PreparedStatement pstmt = null;
		String sql = "select*from member where id =?";
		ResultSet rs=null;
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();		
			
			if(rs.next()) {
				Member m = new Member(
					rs.getString("id"),	
					rs.getString("pass"),	
					rs.getString("name"),	
					rs.getInt("gender"),	
					rs.getString("tel"),	
					rs.getString("email"),	
					rs.getString("picture")
						);
				return m;}
			} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcConection.close(con, pstmt, null);
		}
		return null;}
	
	public int memberUpdate(Member mem) {
		
		Connection con = JdbcConection.getConnection();
		PreparedStatement pstmt = null;
		String sql ="update member set tel = ?, email = ? where id = ? ";
		
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, mem.getTel());
			pstmt.setString(2, mem.getEmail());
			pstmt.setString(3, mem.getId());
			return pstmt.executeUpdate();
			
		
		} catch (SQLException e) {
				e.printStackTrace();
		}finally {
			
			JdbcConection.close(con, pstmt, null);
		}
		
		return 0;
	}
	
	
	public int deleteMember(String id) {
		
		Connection con = JdbcConection.getConnection();
		PreparedStatement pstmt = null;
		String sql = "delete from member where id=?";
		
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,id);
			return pstmt.executeUpdate();
			
		
		} catch (SQLException e) {
				e.printStackTrace();
		}finally {
			
			JdbcConection.close(con, pstmt, null);
		}
		
		return 0;
	}
	
	
	
	
	
}
/*Member mem = new Member(
request.getParameter("id"),
request.getParameter("pass"),
request.getParameter("name"),
Integer.parseInt(request.getParameter("gender")),
request.getParameter("tel"),
request.getParameter("email"),
request.getParameter("picture")

);*/
