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

public class HUMSD03addHandler extends Handler {

	public HUMSD03addHandler(DBConnection conn) throws DBOpenException {
		super(conn);
	}

	public static HUMSD03addHandler getInstance(DBConnection conn) throws DBOpenException {
		return new HUMSD03addHandler(conn);
	}

	/**
	 * insert HUMSD03 Method
	 * 
	 * @param doc
	 * @return doc
	 * @exception SQLException
	 *                ,Exception
	 * @author mg.song
	 * @version 1.0
	 * @cdate 2018-01-29
	 */
	public Document insertHUMSD03(Document doc) throws SQLException, Exception {
		PreparedDBStatement pstmt = null;

		Document result = null;

		try {

			String query = "INSERT INTO `HUMSD03` ( " 
								+ " `reqNo`, " 
								+ " `resTime`, " 
								+ " `resNm`, " 
								+ " `resId`, " 
								+ " `resDesc`) " 
						+ " VALUES (?, ?, ?, ?, ?); ";
			pstmt = conn.prepareStatement(query);
			int seq = 1;
			pstmt.setString(seq++, getString(doc, "reqNo"));
			pstmt.setString(seq++, getString(doc, "resTime"));
			pstmt.setString(seq++, getString(doc, "resNm"));
			pstmt.setString(seq++, getString(doc, "resId"));
			pstmt.setString(seq++, getString(doc, "resDesc"));

			int i = pstmt.executeUpdate();
			result = XMLUtil.getDocument("<HUMSD03 result='" + i + "'/>");
		} catch (SQLException ex) {
			Logger.exception("HUMSD03Handler", "insert", "SQL Exception", ex);
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
	 * select HUMSD03 Method
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
			String query = "SELECT * FROM HUMSD03 " + "		WHERE reqNo = ? ";

			pstmt = conn.prepareStatement(query);
			int seq = 1;

			pstmt.setString(seq++, getString(doc, "reqNo"));

			rs = pstmt.executeQuery();

			while (rs.next()) {
				Document result = XMLUtil.getDocument("HUMSD03");
				setString(result, "seq", rs.getString("seq"));
				setString(result, "reqNo", rs.getString("reqNo"));
				setString(result, "resTime", rs.getString("resTime"));
				setString(result, "resNm", rs.getString("resNm"));
				setString(result, "resId", rs.getString("resId"));
				setString(result, "resDesc", rs.getString("resDesc"));
				list.addElement(result);
			}
		} catch (SQLException ex) {
			Logger.exception("HUMSD03Handler", "selectList", "SQL Exception", ex);
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
	 * delete HUMSD03 Method
	 * 
	 * @param doc
	 * @return doc
	 * @exception SQLException
	 *                ,Exception
	 * @author mg.song
	 * @version 1.0
	 * @cdate 2018-01-17
	 */
	public Document deleteHUMSD03(Document doc) throws SQLException, Exception {
		PreparedDBStatement pstmt = null;
		Document result = null;

		try {
			String query = " DELETE FROM HUMSD03 WHERE seq = ? ";
			pstmt = conn.prepareStatement(query);
			int seq = 1;
			pstmt.setLong(seq++, getLong(doc, "seq"));
			int i = pstmt.executeUpdate();
			result = XMLUtil.getDocument("<HUMSD03 result='" + i + "' msg='success'/>");
		} catch (SQLException ex) {
			Logger.exception("HUMSD03Handler", "delete", "SQL Exception", ex);
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
	 * update HUMSD03 Method
	 * @param			doc
	 * @return			doc
	 * @exception		SQLException,Exception
	 * @author			mg.song
	 * @version 		1.0
	 * @cdate          	2018-01-17
	 */
	public Document updateHUMSD03(Document doc) throws SQLException, Exception {
		PreparedDBStatement pstmt = null;
		Document result = null;

		try{
			String query = "UPDATE HUMSD03 SET	" +
			        "  reqNo = ?,		" +
			        "  resTime = ?,		" +
			        "  resNm = ?,		" +
			        "  resId = ?,		" +
			        "  resDesc = ?		" +
			        "WHERE seq = ?		";			
	   pstmt = conn.prepareStatement(query);
	   int seq = 1;
	   
	   pstmt.setString(seq++, getString( doc, "reqNo") );
	   pstmt.setString(seq++, getString( doc, "resTime") );
	   pstmt.setString(seq++, getString( doc, "resNm") );
	   pstmt.setString(seq++, getString( doc, "resId") );
	   pstmt.setString(seq++, getString( doc, "resDesc") );
	   pstmt.setLong(seq++, getLong( doc, "seq") );
	   
	   
	   int i = pstmt.executeUpdate();
	   result = XMLUtil.getDocument( "<HUMSD03 result='" + i + "'/>" );
	  } catch (SQLException ex) {
	   Logger.exception("HUMSD03Handler", "update", "SQL Exception", ex);
	   throw ex;
	  } finally {
	   try{pstmt.close();}catch(Exception e){}
	  }
	  return result;
	}
	

	
}
