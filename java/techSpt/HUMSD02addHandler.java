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

public class HUMSD02addHandler extends Handler {

	public HUMSD02addHandler(DBConnection conn) throws DBOpenException {
		super(conn);
	}

	public static HUMSD02addHandler getInstance(DBConnection conn) throws DBOpenException {
		return new HUMSD02addHandler(conn);
	}

	/**
	 * insert HUMSD02 Method
	 * 
	 * @param doc
	 * @return doc
	 * @exception SQLException
	 *                ,Exception
	 * @author mg.song
	 * @version 1.0
	 * @cdate 2018-01-29
	 */
	public Document insertHUMSD02(Document doc) throws SQLException, Exception {
		PreparedDBStatement pstmt = null;

		Document result = null;

		try {

			String query = "INSERT INTO `HUMSD02` ( "
						+ " `reqNo`, " 
						+ " `orgFilename`, " 
						+ " `savedFilename`, "
						+ " `savedPath`, "
						+ " `userSabun`, "
						+ " `userNm`) " 
						+ " VALUES (?, ?, ?, ?, ?, ?); ";
			pstmt = conn.prepareStatement(query);
			int seq = 1;
			pstmt.setString(seq++, getString(doc, "reqNo"));
			pstmt.setString(seq++, getString(doc, "orgFilename"));
			pstmt.setString(seq++, getString(doc, "savedFilename"));
			pstmt.setString(seq++, getString(doc, "savedPath"));
			pstmt.setString(seq++, getString(doc, "userSabun"));
			pstmt.setString(seq++, getString(doc, "userNm"));

			int i = pstmt.executeUpdate();
			result = XMLUtil.getDocument("<HUMSD02 result='" + i + "'/>");
		} catch (SQLException ex) {
			Logger.exception("HUMSD02Handler", "insert", "SQL Exception", ex);
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
	 * select HUMSD02 Method
	 * 
	 * @param doc
	 * @return doc
	 * @exception SQLException
	 *                ,Exception
	 * @author mg.song
	 * @version 1.0
	 * @cdate 2018-01-15
	 */
	public Vector selectList(Document doc) throws SQLException, Exception {
		Vector list = new Vector();
		PreparedDBStatement pstmt = null;
		DBResultSet rs = null;

		try {
			String query = "SELECT * FROM HUMSD02 " 
					+ "		WHERE reqNo = ? ";

			pstmt = conn.prepareStatement(query);
			int seq = 1;

			pstmt.setString(seq++, getString(doc, "reqNo"));

			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				Document result = XMLUtil.getDocument("HUMSD02");
				setString(result, "seq", rs.getString("seq"));
				setString(result, "orgFilename", rs.getString("orgFilename"));
				setString(result, "savedFilename", rs.getString("savedFilename"));
				setString(result, "savedPath", rs.getString("savedPath"));
				setString(result, "userSabun", rs.getString("userSabun"));
				setString(result, "userNm", rs.getString("userNm"));
				list.addElement(result);
			}
		} catch (SQLException ex) {
			Logger.exception("HUMSD02Handler", "selectList", "SQL Exception", ex);
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
	 * delete HUMSD02 Method
	 * 
	 * @param doc
	 * @return doc
	 * @exception SQLException
	 *                ,Exception
	 * @author mg.song
	 * @version 1.0
	 * @cdate 2018-01-17
	 */
	public Document deleteHUMSD02(Document doc) throws SQLException, Exception {
		PreparedDBStatement pstmt = null;
		Document result = null;

		try {
			String query = " DELETE FROM HUMSD02 WHERE savedFilename = ? AND savedPath = ? \n";
			pstmt = conn.prepareStatement(query);
			int seq = 1;
			pstmt.setString(seq++, getString(doc, "savedFilename"));
			pstmt.setString(seq++, getString(doc, "savedPath"));
			int i = pstmt.executeUpdate();
			result = XMLUtil.getDocument("<HUMSD02 result='" + i + "' msg='success'/>");
		} catch (SQLException ex) {
			Logger.exception("HUMSD02Handler", "delete", "SQL Exception", ex);
			throw ex;
		} finally {
			try {
				pstmt.close();
			} catch (Exception e) {
			}
		}
		return result;
	}

	
	public Document deletByReqNo(Document doc) throws SQLException, Exception {
		PreparedDBStatement pstmt = null;
		Document result = null;

		try {
			System.out.println("999 " + getString(doc, "reqNo"));
			
			String query = " DELETE FROM HUMSD02 WHERE reqNo = ? \n";
			pstmt = conn.prepareStatement(query);
			int seq = 1;
			pstmt.setString(seq++, getString(doc, "reqNo"));
			int i = pstmt.executeUpdate();
			result = XMLUtil.getDocument("<HUMSD02 result='" + i + "' msg='success'/>");
		} catch (SQLException ex) {
			Logger.exception("HUMSD02Handler", "delete", "SQL Exception", ex);
			throw ex;
		} finally {
			try {
				pstmt.close();
			} catch (Exception e) {
			}
		}
		return result;
	}
}
