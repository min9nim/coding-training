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

public class HUMSD05addHandler extends Handler {

	public HUMSD05addHandler(DBConnection conn) throws DBOpenException {
		super(conn);
	}

	public static HUMSD05addHandler getInstance(DBConnection conn) throws DBOpenException {
		return new HUMSD05addHandler(conn);
	}

	public Document insertHUMSD05(Document doc) throws SQLException, Exception {
		PreparedDBStatement pstmt = null;

		Document result = null;

		try {

			String query = "INSERT INTO `HUMSD05` ( " 
								+ " `reqNo`, "
								+ " `assignerId`, "
								+ " `assignerNm`, "
								+ " `assignDate`, "
								+ " `assignTeam`, "
								+ " `assigneeId`, "
								+ " `assigneeNm`, "
								+ " `assignDesc`) " 
						+ " VALUES (?, ?, ?, ?, ?, ?, ?, ? ); ";
			pstmt = conn.prepareStatement(query);
			int seq = 1;
			pstmt.setString(seq++, getString(doc, "reqNo"));
			pstmt.setString(seq++, getString(doc, "assignerId"));
			pstmt.setString(seq++, getString(doc, "assignerNm"));
			pstmt.setString(seq++, getString(doc, "assignDate"));
			pstmt.setString(seq++, getString(doc, "assignTeam"));
			pstmt.setString(seq++, getString(doc, "assigneeId"));
			pstmt.setString(seq++, getString(doc, "assigneeNm"));
			pstmt.setString(seq++, getString(doc, "assignDesc"));

			int i = pstmt.executeUpdate();
			result = XMLUtil.getDocument("<HUMSD05 result='" + i + "'/>");
		} catch (SQLException ex) {
			Logger.exception("HUMSD05Handler", "insert", "SQL Exception", ex);
			throw ex;
		} finally {
			try {
				pstmt.close();
			} catch (Exception e) {
			}
		}
		return result;
	}

	
	public Vector selectList(Document doc) throws SQLException, Exception {
		Vector list = new Vector();
		PreparedDBStatement pstmt = null;
		DBResultSet rs = null;

		try {
			String query = "SELECT * FROM HUMSD05 WHERE reqNo = ? ORDER BY seq asc";

			pstmt = conn.prepareStatement(query);
			int seq = 1;

			pstmt.setString(seq++, getString(doc, "reqNo"));

			rs = pstmt.executeQuery();

			while (rs.next()) {
				Document result = XMLUtil.getDocument("HUMSD05");
				setLong(result, "seq", rs.getLong("seq"));
				setString(result, "reqNo", rs.getString("reqNo"));
				setString(result, "assignerId", rs.getString("assignerId"));
				setString(result, "assignerNm", rs.getString("assignerNm"));
				setString(result, "assignDate", rs.getString("assignDate"));
				setString(result, "assignTeam", rs.getString("assignTeam"));
				setString(result, "assigneeId", rs.getString("assigneeId"));
				setString(result, "assigneeNm", rs.getString("assigneeNm"));
				setString(result, "assignDesc", rs.getString("assignDesc"));
				list.addElement(result);
			}
		} catch (SQLException ex) {
			Logger.exception("HUMSD05Handler", "selectList", "SQL Exception", ex);
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
	 * delete HUMSD05 Method
	 * 
	 * @param doc
	 * @return doc
	 * @exception SQLException
	 *                ,Exception
	 * @author mg.song
	 * @version 1.0
	 * @cdate 2018-01-17
	 */
	public Document deleteHUMSD05(Document doc) throws SQLException, Exception {
		PreparedDBStatement pstmt = null;
		Document result = null;

		try {
			String query = " DELETE FROM HUMSD05 WHERE seq = ? ";
			pstmt = conn.prepareStatement(query);
			int seq = 1;
			pstmt.setLong(seq++, getLong(doc, "seq"));
			int i = pstmt.executeUpdate();
			result = XMLUtil.getDocument("<HUMSD05 result='" + i + "' msg='success'/>");
		} catch (SQLException ex) {
			Logger.exception("HUMSD05Handler", "delete", "SQL Exception", ex);
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
	 * update HUMSD05 Method
	 * @param			doc
	 * @return			doc
	 * @exception		SQLException,Exception
	 * @author			mg.song
	 * @version 		1.0
	 * @cdate          	2018-01-17
	 */
	public Document updateHUMSD05(Document doc) throws SQLException, Exception {
		PreparedDBStatement pstmt = null;
		Document result = null;
		
		try{
			String query = "UPDATE HUMSD05 SET	" +
			        "  assignerId = ?,		" +
			        "  assignerNm = ?,		" +
			        "  assignDate = ?,		" +
			        "  assignTeam = ?,		" +
			        "  assigneeId = ?,		" +
			        "  assigneeNm = ?,		" +
			        "  assignDesc = ?		" +
			        "WHERE seq = ?		";			
	   pstmt = conn.prepareStatement(query);
	   int seq = 1;
	   
		pstmt.setString(seq++, getString(doc, "assignerId"));
		pstmt.setString(seq++, getString(doc, "assignerNm"));
		pstmt.setString(seq++, getString(doc, "assignDate"));
		pstmt.setString(seq++, getString(doc, "assignTeam"));
		pstmt.setString(seq++, getString(doc, "assigneeId"));
		pstmt.setString(seq++, getString(doc, "assigneeNm"));
		pstmt.setString(seq++, getString(doc, "assignDesc"));
	   pstmt.setLong(seq++, getLong( doc, "seq") );
	   
	   int i = pstmt.executeUpdate();
	   result = XMLUtil.getDocument( "<HUMSD05 result='" + i + "'/>" );
	  } catch (SQLException ex) {
	   Logger.exception("HUMSD05Handler", "update", "SQL Exception", ex);
	   throw ex;
	  } finally {
	   try{pstmt.close();}catch(Exception e){}
	  }
	  return result;
	}
}
