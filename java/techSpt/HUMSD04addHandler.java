package com.inswave.msg.hums;

import java.sql.SQLException;
import java.util.Vector;

import org.w3c.dom.Document;

import com.inswave.admin.Logger;
import com.inswave.msg.Handler;
import com.inswave.system.sql.DBConnection;
import com.inswave.system.sql.DBOpenException;
import com.inswave.system.sql.DBResultSet;
import com.inswave.system.sql.PreparedDBStatement;
import com.inswave.util.XMLUtil;

public class HUMSD04addHandler extends Handler {

	public HUMSD04addHandler(DBConnection conn) throws DBOpenException {
		super(conn);
	}

	public static HUMSD04addHandler getInstance(DBConnection conn) throws DBOpenException {
		return new HUMSD04addHandler(conn);
	}

	public Document insertHUMSD04(Document doc) throws SQLException, Exception {
		PreparedDBStatement pstmt = null;

		Document result = null;

		try {

			String query = "INSERT INTO `HUMSD04` ( " 
								+ " `title`, "
								+ " `name`, "
								+ " `location`, " 
								+ " `lastVisited`, " 
								+ " `etc`) " 
						+ " VALUES (?, ?, ?, ?, ? ); ";
			pstmt = conn.prepareStatement(query);
			int seq = 1;
			pstmt.setString(seq++, getString(doc, "title"));
			pstmt.setString(seq++, getString(doc, "name"));
			pstmt.setString(seq++, getString(doc, "location"));
			pstmt.setString(seq++, getString(doc, "lastVisited"));
			pstmt.setString(seq++, getString(doc, "etc"));

			int i = pstmt.executeUpdate();
			result = XMLUtil.getDocument("<HUMSD04 result='" + i + "'/>");
		} catch (SQLException ex) {
			Logger.exception("HUMSD04Handler", "insert", "SQL Exception", ex);
			throw ex;
		} finally {
			try {
				pstmt.close();
			} catch (Exception e) {
			}
		}
		return result;
	}

	/**
	 * select HUMSD04 Method
	 * 
	 * @param doc
	 * @return doc
	 * @exception SQLException
	 *                ,Exception
	 * @author mg.song
	 * @version 1.0
	 * @cdate 2018-02-02
	 */
	public Vector selectList(Document doc) throws SQLException, Exception {
		Vector list = new Vector();
		PreparedDBStatement pstmt = null;
		DBResultSet rs = null;

		try {
			String query = "SELECT * FROM HUMSD04 ORDER BY lastVisited asc ";

			pstmt = conn.prepareStatement(query);
			int seq = 1;

			//pstmt.setString(seq++, getString(doc, "reqNo"));

			rs = pstmt.executeQuery();

			while (rs.next()) {
				Document result = XMLUtil.getDocument("HUMSD04");
				setString(result, "seq", rs.getString("seq"));
				setString(result, "title", rs.getString("title"));
				setString(result, "name", rs.getString("name"));
				setString(result, "location", rs.getString("location"));
				setString(result, "lastVisited", rs.getString("lastVisited"));
				setString(result, "etc", rs.getString("etc"));
				list.addElement(result);
			}
		} catch (SQLException ex) {
			Logger.exception("HUMSD04Handler", "selectList", "SQL Exception", ex);
			throw ex;
		} finally {
			try {
				rs.close();
			} catch (Exception e) {
			}
			try {
				pstmt.close();
			} catch (Exception e) {
			}
		}
		return list;
	}

	/**
	 * delete HUMSD04 Method
	 * 
	 * @param doc
	 * @return doc
	 * @exception SQLException
	 *                ,Exception
	 * @author mg.song
	 * @version 1.0
	 * @cdate 2018-01-17
	 */
	public Document deleteHUMSD04(Document doc) throws SQLException, Exception {
		PreparedDBStatement pstmt = null;
		Document result = null;

		try {
			String query = " DELETE FROM HUMSD04 WHERE seq = ? ";
			pstmt = conn.prepareStatement(query);
			int seq = 1;
			pstmt.setLong(seq++, getLong(doc, "seq"));
			int i = pstmt.executeUpdate();
			result = XMLUtil.getDocument("<HUMSD04 result='" + i + "' msg='success'/>");
		} catch (SQLException ex) {
			Logger.exception("HUMSD04Handler", "delete", "SQL Exception", ex);
			throw ex;
		} finally {
			try {
				pstmt.close();
			} catch (Exception e) {
			}
		}
		return result;
	}


	/**
	 * update HUMSD04 Method
	 * @param			doc
	 * @return			doc
	 * @exception		SQLException,Exception
	 * @author			mg.song
	 * @version 		1.0
	 * @cdate          	2018-01-17
	 */
	public Document updateHUMSD04(Document doc) throws SQLException, Exception {
		PreparedDBStatement pstmt = null;
		Document result = null;
		
		try{
			String query = "UPDATE HUMSD04 SET	" +
			        "  title = ?,		" +
			        "  name = ?,		" +
			        "  location = ?,		" +
			        "  lastVisited = ?,		" +
			        "  etc = ?		" +
			        "WHERE seq = ?		";			
	   pstmt = conn.prepareStatement(query);
	   int seq = 1;
	   
	   pstmt.setString(seq++, getString( doc, "title") );
	   pstmt.setString(seq++, getString( doc, "name") );
	   pstmt.setString(seq++, getString( doc, "location") );
	   pstmt.setString(seq++, getString( doc, "lastVisited") );
	   pstmt.setString(seq++, getString( doc, "etc") );
	   pstmt.setLong(seq++, getLong( doc, "seq") );
	   
	   
	   int i = pstmt.executeUpdate();
	   result = XMLUtil.getDocument( "<HUMSD04 result='" + i + "'/>" );
	  } catch (SQLException ex) {
	   Logger.exception("HUMSD04Handler", "update", "SQL Exception", ex);
	   throw ex;
	  } finally {
	   try{pstmt.close();}catch(Exception e){}
	  }
	  return result;
	}
}
