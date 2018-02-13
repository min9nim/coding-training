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

public class HUMSD01addHandler extends Handler {
	
	public HUMSD01addHandler (DBConnection conn) throws DBOpenException {
		super(conn);
	}

    public static HUMSD01addHandler getInstance(DBConnection conn) throws DBOpenException {
        return new HUMSD01addHandler(conn);
    }

	
	/**
	 * insert HUMSD01 Method
	 * @param			doc
	 * @return			doc
	 * @exception		SQLException,Exception
	 * @author			mg.song
	 * @version 		1.0
	 * @cdate          	2018-01-09
	 */
	public Document insertHUMSD01(Document doc) throws SQLException, Exception {
		PreparedDBStatement pstmt = null;

		Document result = null;

		try{

			 String query = "INSERT INTO `HUMSD01` ( "
			 		+ " `reqNo`, "
			 		+ " `reqTime`, "
			 		+ " `reporterId`, "
			 		+ " `reporterNm`, "
			 		+ " `recepterId`, "
					+ " `recepterNm`, "
					+ " `requesterNm`, "
					+ " `requesterTel`, "
					+ " `requesterEmail`, "
					+ " `projectNm`, "
					+ " `contractNo`, "
					+ " `customerId`, "
					+ " `customerNm`, "
					+ " `officeNm`, "
					+ " `desc`, "
					+ " `majorVer`, "
					+ " `minorVer`, "
					+ " `studioVer`, "
					+ " `browserVer`, "
					+ " `referenceType`, "
					+ " `bizSawonNm`, "
					+ " `status`, "
					+ " `remoteYn`, "
					+ " `autoMail`, "
					+ " `autoTake`, "
					+ " `assignTeam`, "
					+ " `assigneeId`, "
					+ " `assigneeNm`, "
					+ " `jiraNo`) "
					+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?); ";
	   pstmt = conn.prepareStatement(query);
	   int seq = 1;
		
	   pstmt.setString(seq++, getString( doc, "reqNo") );
	   pstmt.setString(seq++, getString( doc, "reqTime") );
	   pstmt.setString(seq++, getString( doc, "reporterId") );
	   pstmt.setString(seq++, getString( doc, "reporterNm") );
	   pstmt.setString(seq++, getString( doc, "recepterId") );
	   pstmt.setString(seq++, getString( doc, "recepterNm") );
	   pstmt.setString(seq++, getString( doc, "requesterNm") );
	   pstmt.setString(seq++, getString( doc, "requesterTel") );
	   pstmt.setString(seq++, getString( doc, "requesterEmail") );
	   pstmt.setString(seq++, getString( doc, "projectNm") );
	   pstmt.setString(seq++, getString( doc, "contractNo") );
	   pstmt.setString(seq++, getString( doc, "customerId") );
	   pstmt.setString(seq++, getString( doc, "customerNm") );
	   pstmt.setString(seq++, getString( doc, "officeNm") );
	   pstmt.setString(seq++, getString( doc, "desc") );
	   pstmt.setString(seq++, getString( doc, "majorVer") );
	   pstmt.setString(seq++, getString( doc, "minorVer") );
	   pstmt.setString(seq++, getString( doc, "studioVer") );
	   pstmt.setString(seq++, getString( doc, "browserVer") );
	   pstmt.setString(seq++, getString( doc, "referenceType") );
	   pstmt.setString(seq++, getString( doc, "bizSawonNm") );
	   pstmt.setString(seq++, getString( doc, "status") );
	   pstmt.setString(seq++, getString( doc, "remoteYn") );
	   pstmt.setString(seq++, getString( doc, "autoMail") );
	   pstmt.setString(seq++, getString( doc, "autoTake") );
	   pstmt.setString(seq++, getString( doc, "assignTeam") );
	   pstmt.setString(seq++, getString( doc, "assigneeId") );
	   pstmt.setString(seq++, getString( doc, "assigneeNm") );
	   pstmt.setString(seq++, getString( doc, "jiraNo") );
	   
	   
	   int i = pstmt.executeUpdate();
	   result = XMLUtil.getDocument( "<HUMSD01 result='" + i + "'/>" );
	  } catch (SQLException ex) {
	   Logger.exception("HUMSD01Handler", "insert", "SQL Exception", ex);
	   throw ex;
	  } finally {
	   try{pstmt.close();}catch(Exception e){}
	  }
	  return result;
	}
	
	

	/**
	 * update HUMSD01 Method
	 * @param			doc
	 * @return			doc
	 * @exception		SQLException,Exception
	 * @author			mg.song
	 * @version 		1.0
	 * @cdate          	2018-01-17
	 */
	public Document updateHUMSD01(Document doc) throws SQLException, Exception {
		PreparedDBStatement pstmt = null;
		Document result = null;

		try{
			String query = "UPDATE HUMSD01 SET	" +
			        "  reqTime = ?,		" +
			        "  reporterId = ?,		" +
			        "  reporterNm = ?,		" +
			        "  recepterId = ?,		" +
			        "  recepterNm = ?,		" +
			        "  requesterNm = ?,		" +
			        "  requesterTel = ?,	" +
			        "  requesterEmail = ?,	" +
			        "  projectNm = ?,		" +
			        "  contractNo = ?,		" +
			        "  customerId = ?,		" +
			        "  customerNm = ?,		" +
			        "  officeNm = ?,		" +
			        "  `desc` = ?,			" +		// desc 가 예약어 이므로 반드시 뱁틱문자로 감싸야 함 
			        "  majorVer = ?,		" +
			        "  minorVer = ?,		" +
			        "  studioVer = ?,		" +
			        "  browserVer = ?,		" +
			        "  referenceType = ?,	" +
			        "  bizSawonNm = ?,	" +
			        "  status = ?,			" +
			        "  remoteYn = ?,		" +
			        "  autoMail = ?,		" +
			        "  autoTake = ?,		" +
			        "  assignTeam = ?,		" +
			        "  assigneeId = ?,		" +
			        "  assigneeNm = ?,		" +
			        "  jiraNo = ?		" +
			        "WHERE reqNo = ?		";			
	   pstmt = conn.prepareStatement(query);
	   int seq = 1;
	   
	   pstmt.setString(seq++, getString( doc, "reqTime") );
	   pstmt.setString(seq++, getString( doc, "reporterId") );
	   pstmt.setString(seq++, getString( doc, "reporterNm") );
	   pstmt.setString(seq++, getString( doc, "recepterId") );
	   pstmt.setString(seq++, getString( doc, "recepterNm") );
	   pstmt.setString(seq++, getString( doc, "requesterNm") );
	   pstmt.setString(seq++, getString( doc, "requesterTel") );
	   pstmt.setString(seq++, getString( doc, "requesterEmail") );
	   pstmt.setString(seq++, getString( doc, "projectNm") );
	   pstmt.setString(seq++, getString( doc, "contractNo") );
	   pstmt.setString(seq++, getString( doc, "customerId") );
	   pstmt.setString(seq++, getString( doc, "customerNm") );
	   pstmt.setString(seq++, getString( doc, "officeNm") );
	   pstmt.setString(seq++, getString( doc, "desc") );
	   pstmt.setString(seq++, getString( doc, "majorVer") );
	   pstmt.setString(seq++, getString( doc, "minorVer") );
	   pstmt.setString(seq++, getString( doc, "studioVer") );
	   pstmt.setString(seq++, getString( doc, "browserVer") );
	   pstmt.setString(seq++, getString( doc, "referenceType") );
	   pstmt.setString(seq++, getString( doc, "bizSawonNm") );
	   pstmt.setString(seq++, getString( doc, "status") );
	   pstmt.setString(seq++, getString( doc, "remoteYn") );
	   pstmt.setString(seq++, getString( doc, "autoMail") );
	   pstmt.setString(seq++, getString( doc, "autoTake") );
	   pstmt.setString(seq++, getString( doc, "assignTeam") );
	   pstmt.setString(seq++, getString( doc, "assigneeId") );
	   pstmt.setString(seq++, getString( doc, "assigneeNm") );
	   pstmt.setString(seq++, getString( doc, "jiraNo") );
	   pstmt.setString(seq++, getString( doc, "reqNo") );
	   
	   
	   
	   int i = pstmt.executeUpdate();
	   result = XMLUtil.getDocument( "<HUMSD01 result='" + i + "'/>" );
	  } catch (SQLException ex) {
	   Logger.exception("HUMSD01Handler", "update", "SQL Exception", ex);
	   throw ex;
	  } finally {
	   try{pstmt.close();}catch(Exception e){}
	  }
	  return result;
	}
	


	public Document updateAssignee(String reqNo) throws SQLException, Exception {
		PreparedDBStatement pstmt = null;
		Document result = null;

		try{
			String query =  " UPDATE HUMSD01 A " +
							" INNER JOIN ( " + 
							"    SELECT reqNo, assignTeam, assigneeId, assigneeNm " + 
							"    FROM HUMSD05 " + 
							"    WHERE seq = (SELECT max(seq) FROM HUMSD05 WHERE reqNo = ?) " +
							"    ) B " +
							" ON A.reqNo = B.reqNo " +
							" SET A.assignTeam = B.assignTeam, " +
							"     A.assigneeId = B.assigneeId, " +
							"     A.assigneeNm = B.assigneeNm ";
		
	   pstmt = conn.prepareStatement(query);
	   int seq = 1;
	   
	   pstmt.setString(seq++, reqNo );
	   
	   int i = pstmt.executeUpdate();
	   result = XMLUtil.getDocument( "<HUMSD01 result='" + i + "'/>" );
	  } catch (SQLException ex) {
	   Logger.exception("HUMSD01Handler", "update", "SQL Exception", ex);
	   throw ex;
	  } finally {
	   try{pstmt.close();}catch(Exception e){}
	  }
	  return result;
	}
		
	

	/**
	 * delete HUMSD01 Method
	 * @param			doc
	 * @return			doc
	 * @exception		SQLException,Exception
	 * @author			mg.song
	 * @version 		1.0
	 * @cdate          	2018-01-17
	 */
	public Document deleteHUMSD01(Document doc) throws SQLException, Exception {
		PreparedDBStatement pstmt = null;
		Document result = null;

		try{
			String query = " DELETE FROM HUMSD01 WHERE reqNo = ? \n";
			pstmt = conn.prepareStatement(query);
			int seq = 1;
			pstmt.setString(seq++, getString(doc, "reqNo"));

			int i = pstmt.executeUpdate();
	   result = XMLUtil.getDocument( "<HUMSD01 result='" + i + "'/>" );
	  } catch (SQLException ex) {
	   Logger.exception("HUMSD01Handler", "delete", "SQL Exception", ex);
	   throw ex;
	  } finally {
	   try{pstmt.close();}catch(Exception e){}
	  }
	  return result;
	}
	

	/**
	 * select HUMSD01 Method
	 * @param			doc
	 * @return			doc
	 * @exception      	SQLException,Exception
	 * @author			mg.song
	 * @version        	1.0
	 * @cdate          	2018-01-15
	 */
	public Vector selectList( Document doc ) throws SQLException, Exception {
		Vector list = new Vector();
		PreparedDBStatement pstmt = null;
		DBResultSet rs = null;

		try{
			String query = "SELECT SQL_CALC_FOUND_ROWS * FROM HUMSD01 "
					+ "		WHERE reqTime between ? and ? "
					+ "			AND projectNm LIKE ?  "
					+ "			AND contractNo LIKE ?  "
					+ "			AND status LIKE ?  "
					+ "			AND requesterNm LIKE ? "
					+ "		ORDER BY reqNo DESC	"  
					+ "		LIMIT	?	"  
					+ "		OFFSET	?	"  ;
			
			pstmt = conn.prepareStatement(query);
			int seq = 1;
			
			pstmt.setString(seq++, getString( doc, "sFmdt") + "0000" );
			pstmt.setString(seq++, getString( doc, "sTodt") + "2359" );
			pstmt.setString(seq++, "%" + getString( doc, "projectNm") + "%");
			pstmt.setString(seq++, "%" + getString( doc, "contractNo") + "%");
			pstmt.setString(seq++, "%" + getString( doc, "status") + "%");
			pstmt.setString(seq++, "%" + getString( doc, "requesterNm") + "%");
			pstmt.setLong(seq++, getLong( doc, "cnt"));
			pstmt.setLong(seq++, getLong( doc, "offset"));
			
			
			rs = pstmt.executeQuery();
			
			while ( rs.next() ) {
				Document result = XMLUtil.getDocument( "HUMSD01" );
				setString( result, "reqNo" , rs.getString("reqNo") );
				setString( result, "reqTime" , rs.getString("reqTime") );
				setString( result, "reporterId" , rs.getString("reporterId") );
				setString( result, "reporterNm" , rs.getString("reporterNm") );
				setString( result, "recepterId" , rs.getString("recepterId") );
				setString( result, "recepterNm" , rs.getString("recepterNm") );
				setString( result, "requesterNm" , rs.getString("requesterNm") );
				setString( result, "requesterTel" , rs.getString("requesterTel") );
				setString( result, "requesterEmail" , rs.getString("requesterEmail") );
				setString( result, "projectNm" , rs.getString("projectNm") );
				setString( result, "contractNo" , rs.getString("contractNo") );
				setString( result, "customerId" , rs.getString("customerId") );
				setString( result, "customerNm" , rs.getString("customerNm") );
				setString( result, "officeNm" , rs.getString("officeNm") );
				setString( result, "desc" , rs.getString("desc") );
				setString( result, "majorVer" , rs.getString("majorVer") );
				setString( result, "minorVer" , rs.getString("minorVer") );
				setString( result, "studioVer" , rs.getString("studioVer") );
				setString( result, "browserVer" , rs.getString("browserVer") );
				setString( result, "referenceType" , rs.getString("referenceType") );
				setString( result, "bizSawonNm" , rs.getString("bizSawonNm") );
				setString( result, "status" , rs.getString("status") );
				setString( result, "remoteYn" , rs.getString("remoteYn") );
				setString( result, "autoMail" , rs.getString("autoMail") );
				setString( result, "autoTake" , rs.getString("autoTake") );
				setString( result, "assignTeam" , rs.getString("assignTeam") );
				setString( result, "assigneeId" , rs.getString("assigneeId") );
				setString( result, "assigneeNm" , rs.getString("assigneeNm") );
				setString( result, "jiraNo" , rs.getString("jiraNo") );
				list.addElement(result);
			}
		} catch (SQLException ex) {
			Logger.exception("HUMSD01Handler", "selectList", "SQL Exception", ex);
			throw ex;
		} finally {
			try{rs.close();}catch(Exception e){}
			try{pstmt.close();}catch(Exception e){}
		}
		return list ;
	} 
	

	public long getTotalRow() throws SQLException, Exception {
		PreparedDBStatement pstmt = null;
		DBResultSet rs = null;
		long totalCnt = 0;

		try{
			String query = " SELECT FOUND_ROWS() as totalCnt ";
			
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			while ( rs.next() ) {
				totalCnt = rs.getLong("totalCnt");
			}
			
		} catch (SQLException ex) {
			Logger.exception("HUMSD01Handler", "getTotalRow", "SQL Exception", ex);
			throw ex;
		} finally {
			try{rs.close();}catch(Exception e){}
			try{pstmt.close();}catch(Exception e){}
		}
		return totalCnt ;
	} 	
}
