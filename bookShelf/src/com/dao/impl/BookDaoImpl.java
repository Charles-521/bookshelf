package com.dao.impl;

import java.util.Date;
import java.math.BigDecimal;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import com.bean.BookBean;
import com.dao.BookDao;
import com.util.DBconn;

public class BookDaoImpl implements BookDao {
	@Override
	public int checkout(int userID, BigDecimal total,  String bookIDs) throws SQLException {

		Connection conn = null;
		int genKey = 0;
		String[] ids = bookIDs.split(",");
		String sql = "update book set state = 1 where id=?";
		String sqlOrder = "insert into userorder values (null, ?, ?, ?, ?)";
		String sqlOrderDetail = "insert into orderdetail values (null, ?, ?)";
		
		try {
			conn = DBconn.getConnection();
			conn.setAutoCommit(false);

		    Date today = Calendar.getInstance().getTime();
		    SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddhhmmss");
		    Random r = new Random();
		    String orderNo = "ORD" + formatter.format(today)+ String.format("%04d", r.nextInt(10000));;
			PreparedStatement psOrder =  conn.prepareStatement(sqlOrder, Statement.RETURN_GENERATED_KEYS);
			psOrder.setInt(1, userID);
			psOrder.setBigDecimal(2, total);;
			psOrder.setDate(3, java.sql.Date.valueOf(LocalDate.now()));
			psOrder.setString(4, orderNo);
			psOrder.executeUpdate();
			ResultSet generatedKeys = psOrder.getGeneratedKeys();
			generatedKeys.next();
			genKey = generatedKeys.getInt(1);
			ArrayList<PreparedStatement> stms = new ArrayList<PreparedStatement>();
			for (int i=0;  i<ids.length; i++) {
				PreparedStatement ps =  conn.prepareStatement(sql);
				ps.setInt(1, Integer.parseInt(ids[i]));
				stms.add(ps);
			}
			for (int i=0;  i<ids.length; i++) {
				PreparedStatement ps =  conn.prepareStatement(sqlOrderDetail);
				ps.setInt(1, genKey);
				ps.setInt(2, Integer.parseInt(ids[i]));
				stms.add(ps);
			}
			
			for (PreparedStatement s : stms) {
				s.executeUpdate();
			}
			
			conn.commit();
		}catch (SQLException e) {
			conn.rollback();
			System.out.println("checkout failed");
			e.printStackTrace();
		}finally {
			conn.close();
    	}
		return genKey;
	}
	
	
	@Override
	public boolean addBook(BookBean bookBean) {
		// TODO Auto-generated method stub
		boolean flag = false;
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "insert into book(name,price,ISBN,coursecode,picturepath,ownerid,filename,description)values(?,?,?,?,?,?,?,?)";
		try {
			conn = DBconn.getConnection();
			ps =  conn.prepareStatement(sql);
			ps.setString(1, bookBean.getName());
			ps.setBigDecimal(2, bookBean.getPrice());
			ps.setString(3, bookBean.getISBN());
			ps.setString(4, bookBean.getCourseCode());
			ps.setString(5, bookBean.getPicturePath());
			ps.setInt(6, bookBean.getOwnerId());
			ps.setString(7, bookBean.getFilename());
			ps.setString(8, bookBean.getDescription());
			int i =  ps.executeUpdate();
			if(i>0){
				flag = true;
			}
		}catch (SQLException e) {
			System.out.println("add book error");
			e.printStackTrace();
		}finally {
    		DBconn.close(null, ps, conn);
    	}
		return flag;
	}
	
	@Override	
	public boolean addLikeBook(int userID, int bookID) {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "insert into likebook(bookID, userID)values(?,?)";
		try {
			conn = DBconn.getConnection();
			ps =  conn.prepareStatement(sql);
			ps.setInt(1, bookID);
			ps.setInt(2, userID);
			int i =  ps.executeUpdate();
			if(i>0){
				flag = true;
			}
		}catch (SQLException e) {
			System.out.println("add liked book error");
			e.printStackTrace();
		}finally {
    		DBconn.close(null, ps, conn);
    	}
		return flag;
	}
	
	@Override	
	public boolean removeLikeBook(int userID, int bookID) {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "delete from likebook where bookID=? and userID=?";
		try {
			conn = DBconn.getConnection();
			ps =  conn.prepareStatement(sql);
			ps.setInt(1, bookID);
			ps.setInt(2, userID);
			int i =  ps.executeUpdate();
			if(i>0){
				flag = true;
			}
		}catch (SQLException e) {
			System.out.println("remove liked book error");
			e.printStackTrace();
		}finally {
    		DBconn.close(null, ps, conn);
    	}
		return flag;
	}
	
	@Override
	public boolean addCartBook(int userID, int bookID) {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "insert into cart(bookID, userID)values(?,?)";
		try {
			conn = DBconn.getConnection();
			ps =  conn.prepareStatement(sql);
			ps.setInt(1, bookID);
			ps.setInt(2, userID);
			int i =  ps.executeUpdate();
			if(i>0){
				flag = true;
			}
		}catch (SQLException e) {
			System.out.println("add liked book error");
			e.printStackTrace();
		}finally {
    		DBconn.close(null, ps, conn);
    	}
		return flag;
	}
		/*@Override
	public List<BookBean> findBookByName(String name) {
		List<BookBean> list = new ArrayList<BookBean>();
		Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
        	conn = DBconn.getConnection();
        	//ps = conn.prepareStatement("select * from book where name=?");
        	//ʵ�����ݿ�ģ����ѯ
        	ps = conn.prepareStatement("select * from book where name like ? and state = 0");
        	ps.setString(1, "%" + name + "%");
            rs=ps.executeQuery();
            while(rs.next()) {
            	BookBean book = new BookBean();
            	book.setId(rs.getInt("id"));
            	book.setName(rs.getString("name"));
            	book.setPrice(rs.getFloat("price"));
            	book.setState(rs.getInt("state"));
            	book.setISBN(rs.getString("ISBN"));
            	book.setCourseCode(rs.getString("coursecode"));
            	book.setPicturePath(rs.getString("picturepath"));
            	list.add(book);
            }
            return list;
        }catch (SQLException e) {
			e.printStackTrace();
		}finally {
    		DBconn.close(rs, ps, conn);
    	}
		return null;

	}

	@Override
	public List<BookBean> findBookByISBN(String ISPN) {
		List<BookBean> list = new ArrayList<BookBean>();
		Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
        	conn = DBconn.getConnection();
        	//ps = conn.prepareStatement("select * from book where ISBN=?");
        	//ʵ�����ݿ�ģ����ѯ
        	ps = conn.prepareStatement("select * from book where ISBN like ?");
        	ps.setString(1, "%" + ISPN + "%");
            rs=ps.executeQuery();
            while(rs.next()) {
            	BookBean book = new BookBean();
            	book.setId(rs.getInt("id"));
            	book.setName(rs.getString("name"));
            	book.setPrice(rs.getFloat("price"));
            	book.setState(rs.getInt("state"));
            	book.setISBN(rs.getString("ISBN"));
            	book.setCourseCode(rs.getString("coursecode"));
            	book.setPicturePath(rs.getString("picturepath"));
            	list.add(book);
            }
            return list;
        }catch (SQLException e) {
			e.printStackTrace();
		}finally {
    		DBconn.close(rs, ps, conn);
    	}
		return null;
	}

	@Override
	public List<BookBean> findBookByCourseCode(String courseCode) {
		// TODO Auto-generated method stub
		List<BookBean> list = new ArrayList<BookBean>();
		Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
        	conn = DBconn.getConnection();
        	//ps = conn.prepareStatement("select * from book where coursecode=?");
        	//ʵ�����ݿ�ģ����ѯ
        	ps = conn.prepareStatement("select * from book where coursecode like ?");
        	ps.setString(1, "%" + courseCode + "%");
            rs=ps.executeQuery();
            while(rs.next()) {
            	BookBean book = new BookBean();
            	book.setId(rs.getInt("id"));
            	book.setName(rs.getString("name"));
            	book.setPrice(rs.getFloat("price"));
            	book.setState(rs.getInt("state"));
            	book.setISBN(rs.getString("ISBN"));
            	book.setCourseCode(rs.getString("coursecode"));
            	book.setPicturePath(rs.getString("picturepath"));
            	list.add(book);
            }
            return list;
        }catch (SQLException e) {
			e.printStackTrace();
		}finally {
    		DBconn.close(rs, ps, conn);
    	}
		return null;
	}
*/
	@Override
	public List<BookBean> findCartBookByUserID(int userID) {
		List<BookBean> list = new ArrayList<BookBean>();
		Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
        	conn = DBconn.getConnection();
        	ps = conn.prepareStatement("select B.*, u.phonenumber from book B, userinfo U, cart C where C.bookID = B.id and C.userID = U.id and U.id = ? and B.state = 0");
        	ps.setInt(1, userID);
            rs=ps.executeQuery();
            while(rs.next()) {
            	BookBean book = new BookBean();
            	book.setId(rs.getInt("B.id"));
            	book.setName(rs.getString("B.name"));
            	book.setPrice(rs.getBigDecimal("B.price"));
            	//book.setState(rs.getInt("state"));
            	book.setISBN(rs.getString("B.ISBN"));
            	book.setPhoneNumber(rs.getString("u.phonenumber"));
            	book.setCourseCode(rs.getString("B.coursecode"));
            	book.setPicturePath(rs.getString("B.picturepath"));
            	book.setFilename(rs.getString("B.filename"));
            	list.add(book);
            }
            return list;
        }catch (SQLException e) {
			e.printStackTrace();
		}finally {
    		DBconn.close(rs, ps, conn);
    	}
		return null;
	}

	@Override
	public List<BookBean> findLikeBookByUserID(int userID) {
		List<BookBean> list = new ArrayList<BookBean>();
		Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
        	conn = DBconn.getConnection();
        	ps = conn.prepareStatement("select B.* from book B, userinfo U, likebook L where L.bookID = B.id and L.userID = U.id and U.id = ? and B.state = 0");
        	ps.setInt(1, userID);
            rs=ps.executeQuery();
            while(rs.next()) {
            	BookBean book = new BookBean();
            	book.setId(rs.getInt("id"));
            	book.setName(rs.getString("name"));
            	book.setPrice(rs.getBigDecimal("price"));
            	//book.setState(rs.getInt("state"));
            	book.setISBN(rs.getString("ISBN"));
            	book.setCourseCode(rs.getString("coursecode"));
            	book.setPicturePath(rs.getString("picturepath"));
            	book.setFilename(rs.getString("filename"));
            	list.add(book);
            }
            return list;
        }catch (SQLException e) {
			e.printStackTrace();
		}finally {
    		DBconn.close(rs, ps, conn);
    	}
		return null;
	}



	@Override
	public int getTotalRecordsNum() {
		// TODO Auto-generated method stub
		Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int num = 0;
        try {
        	conn = DBconn.getConnection();
        	ps = conn.prepareStatement("select count(*) from book where state = 0");
            rs=ps.executeQuery();
            rs.next();
            num = rs.getInt(1);
        }catch (SQLException e) {
			e.printStackTrace();
		}finally {
    		DBconn.close(rs, ps, conn);
    	}
		return num;
	}
	
	@Override
	public int getTotalLikeRecordsNum(int userID) {
		// TODO Auto-generated method stub
		Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int num = 0;
        try {
        	conn = DBconn.getConnection();
        	ps = conn.prepareStatement("select count(*) from likebook lb, userinfo u, book b where lb.bookID = b.id and u.id = lb.userID and b.state = 0");
            rs=ps.executeQuery();
            rs.next();
            num = rs.getInt(1);
        }catch (SQLException e) {
			e.printStackTrace();
		}finally {
    		DBconn.close(rs, ps, conn);
    	}
		return num;
	}

	@Override
	public List<BookBean> findPageRecords(int startIndex, int pageSize) {
		// TODO Auto-generated method stub
		List<BookBean> list = new ArrayList<BookBean>();
		Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
        	conn = DBconn.getConnection();
        	ps = conn.prepareStatement("select * from book where state = 0 limit ?,?");
        	ps.setInt(1, startIndex);
        	ps.setInt(2, pageSize);
            rs=ps.executeQuery();
            while(rs.next()) {
            	BookBean book = new BookBean();
            	book.setId(rs.getInt("id"));
            	book.setName(rs.getString("name"));
            	book.setPrice(rs.getBigDecimal("price"));
            	//book.setState(rs.getInt("state"));
            	book.setISBN(rs.getString("ISBN"));
            	book.setCourseCode(rs.getString("coursecode"));
            	book.setPicturePath(rs.getString("picturepath"));
            	book.setFilename(rs.getString("filename"));
            	list.add(book);
            }
            return list;
        }catch (SQLException e) {
			e.printStackTrace();
		}finally {
    		DBconn.close(rs, ps, conn);
    	}
		return null;
	}
	@Override
	public List<BookBean> findLikePageRecords(int startIndex, int pageSize, int userID) {
		List<BookBean> list = new ArrayList<BookBean>();
		Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
        	conn = DBconn.getConnection();
        	ps = conn.prepareStatement("select b.* from likebook lb, userinfo u, book b where lb.bookID = b.id and u.id = lb.userID and b.state = 0 limit ?,?");
        	ps.setInt(1, startIndex);
        	ps.setInt(2, pageSize);
            rs=ps.executeQuery();
            while(rs.next()) {
            	BookBean book = new BookBean();
            	book.setId(rs.getInt("id"));
            	book.setName(rs.getString("name"));
            	book.setPrice(rs.getBigDecimal("price"));
            	book.setISBN(rs.getString("ISBN"));
            	book.setCourseCode(rs.getString("coursecode"));
            	book.setPicturePath(rs.getString("picturepath"));
            	book.setFilename(rs.getString("filename"));
            	list.add(book);
            }
            return list;
        }catch (SQLException e) {
			e.printStackTrace();
		}finally {
    		DBconn.close(rs, ps, conn);
    	}
		return null;
	}

/*	@Override
	public List<BookBean> findPageRecordsByKeyWords(int startIndex, int pageSize, String keyWords) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		List<BookBean> list = new ArrayList<BookBean>();
		Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
        	conn = DBconn.getConnection();
        	ps = conn.prepareStatement("select * from book where name like ? or ISBN like ? or coursecode like ? and state =0 limit ?,?");
        	ps.setString(1, "%" + keyWords + "%");
        	ps.setString(2, "%" + keyWords + "%");
        	ps.setString(3, "%" + keyWords + "%");
        	ps.setInt(4, startIndex);
        	ps.setInt(5, pageSize);
            rs=ps.executeQuery();
            while(rs.next()) {
            	BookBean book = new BookBean();
            	book.setId(rs.getInt("id"));
            	book.setName(rs.getString("name"));
            	book.setPrice(rs.getFloat("price"));
            	//book.setState(rs.getInt("state"));
            	book.setISBN(rs.getString("ISBN"));
            	book.setCourseCode(rs.getString("coursecode"));
            	book.setPicturePath(rs.getString("picturepath"));
            	book.setFilename(rs.getString("filename"));
            	list.add(book);
            }
            return list;
        }catch (SQLException e) {
			e.printStackTrace();
		}finally {
    		DBconn.close(rs, ps, conn);
    	}
		return null;
	}*/

/*	@Override
	public int getTotalRecordsNumByKeyWords(String keyWords) {
		Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int num = 0;
        try {
        	conn = DBconn.getConnection();
        	ps = conn.prepareStatement("select count(*) from book where name like ? or ISBN like ? or coursecode like ? and state = 0");
            rs=ps.executeQuery();
            rs.next();
            num = rs.getInt(1);
        }catch (SQLException e) {
			e.printStackTrace();
		}finally {
    		DBconn.close(rs, ps, conn);
    	}
		return num;
	}*/

	@Override
	public List<BookBean> findPageRecordsByName(int startIndex, int pageSize, String keyWords) {
		List<BookBean> list = new ArrayList<BookBean>();
		Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
        	conn = DBconn.getConnection();
        	ps = conn.prepareStatement("select * from book where name like ?  and state =0 limit ?,?");
        	ps.setString(1, "%" + keyWords + "%");
        	ps.setInt(2, startIndex);
        	ps.setInt(3, pageSize);
            rs=ps.executeQuery();
            while(rs.next()) {
            	BookBean book = new BookBean();
            	book.setId(rs.getInt("id"));
            	book.setName(rs.getString("name"));
            	book.setPrice(rs.getBigDecimal("price"));
            	//book.setState(rs.getInt("state"));
            	book.setISBN(rs.getString("ISBN"));
            	book.setCourseCode(rs.getString("coursecode"));
            	book.setPicturePath(rs.getString("picturepath"));
            	book.setFilename(rs.getString("filename"));
            	list.add(book);
            }
            return list;
        }catch (SQLException e) {
			e.printStackTrace();
		}finally {
    		DBconn.close(rs, ps, conn);
    	}
		return null;
	}

	@Override
	public List<BookBean> findPageRecordsByISBN(int startIndex, int pageSize, String keyWords) {
		List<BookBean> list = new ArrayList<BookBean>();
		Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
        	conn = DBconn.getConnection();
        	ps = conn.prepareStatement("select * from book where ISBN like ?  and state =0 limit ?,?");
        	ps.setString(1, "%" + keyWords + "%");
        	ps.setInt(2, startIndex);
        	ps.setInt(3, pageSize);
            rs=ps.executeQuery();
            while(rs.next()) {
            	BookBean book = new BookBean();
            	book.setId(rs.getInt("id"));
            	book.setName(rs.getString("name"));
            	book.setPrice(rs.getBigDecimal("price"));
            	//book.setState(rs.getInt("state"));
            	book.setISBN(rs.getString("ISBN"));
            	book.setCourseCode(rs.getString("coursecode"));
            	book.setPicturePath(rs.getString("picturepath"));
            	book.setFilename(rs.getString("filename"));
            	list.add(book);
            }
            return list;
        }catch (SQLException e) {
			e.printStackTrace();
		}finally {
    		DBconn.close(rs, ps, conn);
    	}
		return null;
	}

	@Override
	public List<BookBean> findPageRecordsByCourseCode(int startIndex, int pageSize, String keyWords) {
		List<BookBean> list = new ArrayList<BookBean>();
		Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
        	conn = DBconn.getConnection();
        	ps = conn.prepareStatement("select * from book where coursecode like ?  and state =0 limit ?,?");
        	ps.setString(1, "%" + keyWords + "%");
        	ps.setInt(2, startIndex);
        	ps.setInt(3, pageSize);
            rs=ps.executeQuery();
            while(rs.next()) {
            	BookBean book = new BookBean();
            	book.setId(rs.getInt("id"));
            	book.setName(rs.getString("name"));
            	book.setPrice(rs.getBigDecimal("price"));
            	//book.setState(rs.getInt("state"));
            	book.setISBN(rs.getString("ISBN"));
            	book.setCourseCode(rs.getString("coursecode"));
            	book.setPicturePath(rs.getString("picturepath"));
            	book.setFilename(rs.getString("filename"));
            	list.add(book);
            }
            return list;
        }catch (SQLException e) {
			e.printStackTrace();
		}finally {
    		DBconn.close(rs, ps, conn);
    	}
		return null;
	}

	@Override
	public int getTotalRecordsNumByName(String keyWords) {
		Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int num = 0;
        try {
        	conn = DBconn.getConnection();
        	ps = conn.prepareStatement("select count(*) from book where name like ? and state = 0");
        	ps.setString(1, "%" + keyWords + "%");
            rs=ps.executeQuery();
            rs.next();
            num = rs.getInt(1);
        }catch (SQLException e) {
			e.printStackTrace();
		}finally {
    		DBconn.close(rs, ps, conn);
    	}
		return num;
	}

	@Override
	public int getTotalRecordsNumByISBN(String keyWords) {
		Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int num = 0;
        try {
        	conn = DBconn.getConnection();
        	ps = conn.prepareStatement("select count(*) from book where ISBN like ? and state = 0");
        	ps.setString(1, "%" + keyWords + "%");
            rs=ps.executeQuery();
            rs.next();
            num = rs.getInt(1);
        }catch (SQLException e) {
			e.printStackTrace();
		}finally {
    		DBconn.close(rs, ps, conn);
    	}
		return num;
	}

	@Override
	public int getTotalRecordsNumByCourseCode(String keyWords) {
		Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int num = 0;
        try {
        	conn = DBconn.getConnection();
        	ps = conn.prepareStatement("select count(*) from book where coursecode like ? and state = 0");
        	ps.setString(1, "%" + keyWords + "%");
            rs=ps.executeQuery();
            rs.next();
            num = rs.getInt(1);
        }catch (SQLException e) {
			e.printStackTrace();
		}finally {
    		DBconn.close(rs, ps, conn);
    	}
		return num;
	}

	@Override
	public BookBean findBookById(int id) {
		BookBean book = new BookBean();
		Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
        	conn = DBconn.getConnection();
        	ps = conn.prepareStatement("select * from book where id=?");
        	ps.setInt(1, id);
            rs=ps.executeQuery();
            while(rs.next()) {
            	book.setId(rs.getInt("id"));
            	book.setName(rs.getString("name"));
            	book.setPrice(rs.getBigDecimal("price"));
            	book.setState(rs.getInt("state"));
            	book.setISBN(rs.getString("ISBN"));
            	book.setCourseCode(rs.getString("coursecode"));
            	book.setPicturePath(rs.getString("picturepath"));
            	book.setFilename(rs.getString("filename"));
            	book.setDescription(rs.getString("description"));
            }
            return book;
        }catch (SQLException e) {
			e.printStackTrace();
		}finally {
    		DBconn.close(rs, ps, conn);
    	}
		return null;
	}


}
