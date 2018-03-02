package com.inswave.techspt.task;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Vector;

import org.w3c.dom.*;

import com.inswave.admin.Logger;
import com.inswave.msg.hums.HUMSD01addHandler;
import com.inswave.msg.hums.HUMSD02addHandler;
import com.inswave.msg.hums.HUMSD03addHandler;
import com.inswave.msg.hums.HUMSD04addHandler;
import com.inswave.msg.hums.HUMSD05addHandler;
import com.inswave.msg.warn.WARNINGMsg;
import com.inswave.system.Task;
import com.inswave.system.exception.Warning;
import com.inswave.system.sql.DBConnection;
import com.inswave.system.sql.DBResultSet;
import com.inswave.system.sql.DefaultDBConn;
import com.inswave.system.sql.PreparedDBStatement;
import com.inswave.util.DateUtil;
import com.inswave.util.KeyUtil;
import com.inswave.util.StringUtil;
import com.inswave.util.XMLUtil;

public class ServiceDesk extends Task {
	
	private String updateDate = "";

	public ServiceDesk() throws Exception {
		DBConnection dbConn = null;
		PreparedDBStatement pstmt = null;
		DBResultSet rs = null;
		try {
			// getSeqNo 에서 사용할 updateDate 값을 미리 초기화 함
			dbConn = new DefaultDBConn().getConnection();
			Document res;

			String query = "SELECT * FROM TOLXX01 WHERE sKey = 'HUMSDB01'	";
			pstmt = dbConn.prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				updateDate = rs.getString("dUpdateDate").substring(8, 10);
			}

		} catch (Exception e) {
			throw e;
		} finally {
			try {
				rs.close();
			} catch (Exception e) {
			}
			try {
				pstmt.close();
			} catch (Exception e) {
			}
			try {
				dbConn.close();
			} catch (Exception e) {
			}
		}
	}

	public Document saveReq(Document doc) throws Exception {
		DBConnection dbConn = null;
		Document result = null;
		try {
			dbConn = new DefaultDBConn().getConnection();
			dbConn.setAutoCommit(false);
			HUMSD01addHandler humsd01Handler = HUMSD01addHandler.getInstance(dbConn);
			HUMSD02addHandler humsd02Handler = HUMSD02addHandler.getInstance(dbConn);

			Node node = doc.getDocumentElement();
			NodeList dataList = node.getChildNodes();
			Node vectorNode = dataList.item(0);

			Vector vt = XMLUtil.toVector(XMLUtil.getDocument((Element) vectorNode));

			int cnt = vt.size();
			for (int i = 0; i < cnt; i++) {
				Document row = (Document) vt.elementAt(i);

				String rowStatus = XMLUtil.getAttribute(row, "rowStatus", "value");
				if (rowStatus.equals("C")) {
					humsd01Handler.insertHUMSD01(row);
				} else if (rowStatus.equals("U")) {
					humsd01Handler.updateHUMSD01(row);
				} else if (rowStatus.equals("E")) {
					humsd01Handler.deleteHUMSD01(row);
					
					// 물리적인 파일삭제
					deleteFiles(row);
					
					// HUMSD02(첨부파일) 테이블 정리
					//humsd02Handler.deletByReqNo(row);

					/*
					 HUMSD02, HUMSD03, HUMSD05 테이블의 ON DELETE CASCADE 설정이 되어있기 때문에
					 HUMSD01 에서 삭제되는 reqNo를 참조하는 해당 테이블의 모든 row는 DB상에서 자동으로 삭제처리 됨
					 */ 
				}
			}
			
			result = XMLUtil.getDocument("<HUMSD01 result='" + cnt + "' msg='success'/>");

			dbConn.commit();

		} catch (Exception e) {
			throw e;
		} finally {

		}

		return result;

	}

	public Document saveFileinfo(Document doc) throws Exception {
		DBConnection dbConn = null;
		Document result = null;
		try {
			dbConn = new DefaultDBConn().getConnection();

			HUMSD02addHandler humsd02Handler = HUMSD02addHandler.getInstance(dbConn);

			Node node = doc.getDocumentElement();
			NodeList dataList = node.getChildNodes();
			Node vectorNode = dataList.item(0);

			Vector vt = XMLUtil.toVector(XMLUtil.getDocument((Element) vectorNode));

			int cnt = vt.size();
			for (int i = 0; i < cnt; i++) {
				Document row = (Document) vt.elementAt(i);

				humsd02Handler.insertHUMSD02(row);
			}

			result = XMLUtil.getDocument("<HUMSD02 result='" + cnt + "' msg='success'/>");

		} catch (Exception e) {
			throw e;
		} finally {

		}

		return result;

	}
	
	public Document saveLunch(Document doc) throws Exception {
		DBConnection dbConn = null;
		Document result = null;
		try {
			dbConn = new DefaultDBConn().getConnection();
			dbConn.setAutoCommit(false);
			HUMSD04addHandler humsd04Handler = HUMSD04addHandler.getInstance(dbConn);

			Node node = doc.getDocumentElement();
			NodeList dataList = node.getChildNodes();
			Node vectorNode = dataList.item(0);

			Vector vt = XMLUtil.toVector(XMLUtil.getDocument((Element) vectorNode));

			int cnt = vt.size();
			for (int i = 0; i < cnt; i++) {
				Document row = (Document) vt.elementAt(i);

				String rowStatus = XMLUtil.getAttribute(row, "rowStatus", "value");
				if (rowStatus.equals("C")) {
					humsd04Handler.insertHUMSD04(row);
				} else if (rowStatus.equals("U")) {
					humsd04Handler.updateHUMSD04(row);
				} else if (rowStatus.equals("E")) {
					humsd04Handler.deleteHUMSD04(row);
				}
			}
			
			result = XMLUtil.getDocument("<HUMSD04 result='" + cnt + "' msg='success'/>");

			dbConn.commit();

		} catch (Exception e) {
			throw e;
		} finally {

		}

		return result;

	}	

	public Document deleteFiles(Document doc) throws Exception {
		DBConnection dbConn = null;
		Document result = null;
		try {
			//System.out.println("222 " + XMLUtil.indent(doc));
			
			dbConn = new DefaultDBConn().getConnection();
			
			
			HUMSD02addHandler humsd02Handler = HUMSD02addHandler.getInstance(dbConn);
			Vector vt = humsd02Handler.selectList(doc);

			int cnt = vt.size();
			for (int i = 0; i < cnt; i++) {
				Document row = (Document) vt.elementAt(i);
				String savedFilename = XMLUtil.getString(row, "savedFilename");
				String savedPath = XMLUtil.getString(row, "savedPath");
				
				String fullPath = savedPath + File.separator + savedFilename;
				
				File file = new File(fullPath);
				if(file.exists()){
					file.delete();
					System.out.println("@@ " + fullPath + " : 파일삭제 성공");
				}else{
					System.out.println("@@ " + fullPath + " : 파일없음");
				}
			}			
			
			// 디비정보 삭제
			result = XMLUtil.getDocument("<HUMSD02 result='" + cnt + "' msg='success'/>");

		} catch (Exception e) {
			System.out.println("humsd02Handler.deleteFiles 실패");
			throw e;
		} finally {

		}

		return result;

	}	

	public Document deleteFile(Document doc) throws Exception {
		DBConnection dbConn = null;
		Document result = null;
		try {
			System.out.println("## deleteFile called..");

			//System.out.println(XMLUtil.indent(doc));

			dbConn = new DefaultDBConn().getConnection();

			// 디비정보 삭제
			HUMSD02addHandler humsd02Handler = HUMSD02addHandler.getInstance(dbConn);

			// 업로드된 파일삭제 
			String savedPath = XMLUtil.getString(doc, "savedPath");
			String savedFilename = XMLUtil.getString(doc, "savedFilename");
			
			System.out.println("@@ fullpath = " + savedPath + File.separator + savedFilename);
			
			File file = new File(savedPath + File.separator +  savedFilename);
			file.delete();

			result = humsd02Handler.deleteHUMSD02(doc);

		} catch (Exception e) {
			System.out.println("humsd02Handler.deleteHUMSD02 실패");
			throw e;
		} finally {

		}

		return result;

	}

	public Document getReqList(Document doc) throws Exception {
		DBConnection dbConn = null;
		Vector result = null;
		Document res;
		try {
			dbConn = new DefaultDBConn().getConnection();
			HUMSD01addHandler humsd01Handler = HUMSD01addHandler.getInstance(dbConn);

			result = humsd01Handler.selectList(doc);
			long totalRow = humsd01Handler.getTotalRow();

			res = XMLUtil.toXML(result);
			XMLUtil.setAttribute(res, "totalRow", String.valueOf(totalRow));

		} catch (Exception e) {
			throw e;
		} finally {
			try {
				dbConn.close();
			} catch (Exception e) {
			}
		}

		return res;
	}


	public Document getLunchList(Document doc) throws Exception {
		DBConnection dbConn = null;
		Vector result = null;
		Document res;
		try {
			dbConn = new DefaultDBConn().getConnection();
			HUMSD04addHandler humsd04Handler = HUMSD04addHandler.getInstance(dbConn);

			result = humsd04Handler.selectList(doc);
			res = XMLUtil.toXML(result);

		} catch (Exception e) {
			throw e;
		} finally {
			try {
				dbConn.close();
			} catch (Exception e) {
			}
		}

		return res;
	}

	
	public Document getFileList(Document doc) throws Exception {
		DBConnection dbConn = null;
		Vector result = null;
		Document res;
		try {
			dbConn = new DefaultDBConn().getConnection();
			HUMSD02addHandler humsd02Handler = HUMSD02addHandler.getInstance(dbConn);
			result = humsd02Handler.selectList(doc);

			res = XMLUtil.toXML(result);

		} catch (Exception e) {
			throw e;
		} finally {
			try {
				dbConn.close();
			} catch (Exception e) {
			}
		}

		return res;
	}

	public Document getSeqNo(Document doc) throws Exception {
		DBConnection dbConn = null;
		PreparedDBStatement pstmt = null;
		Document res;

		try {
			dbConn = new DefaultDBConn().getConnection();
			int result;
			String currDate = DateUtil.getCurrentDate("yyMMdd");
			String dd = DateUtil.getCurrentDate("dd"); // 오늘일자

			// System.out.println("#오늘일자 = " + dd);
			// System.out.println("#updateDate = " + updateDate);

			if (updateDate.compareTo(dd) < 0) {
				// 날짜가 넘어가면 nSerial을 0으로 초기
				updateDate = dd;
				String query = "UPDATE TOLXX01 SET nSerial = 0 WHERE sKey = 'HUMSDB01'	";
				pstmt = dbConn.prepareStatement(query);
				result = pstmt.executeUpdate();
			}
			System.out.println("#updateDate = " + updateDate);

			String seqNo = currDate + "-" + KeyUtil.getStringKey("HUMSDB01", 0, 4);
			res = XMLUtil.getDocument("<HUMSD01 result='" + seqNo + "'/>");
			return res;
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				pstmt.close();
			} catch (Exception e) {
			}
			try {
				dbConn.close();
			} catch (Exception e) {
			}
		}
	}

	
	public Document getSeqNo() throws Exception {
		return getSeqNo(null);
	}
	

	public Document saveRes(Document doc) throws Exception {
		DBConnection dbConn = null;
		Document result = null;
		try {
			dbConn = new DefaultDBConn().getConnection();
			dbConn.setAutoCommit(false);
			HUMSD03addHandler humsd03Handler = HUMSD03addHandler.getInstance(dbConn);

			Node node = doc.getDocumentElement();
			NodeList dataList = node.getChildNodes();
			Node vectorNode = dataList.item(0);

			Vector vt = XMLUtil.toVector(XMLUtil.getDocument((Element) vectorNode));

			int cnt = vt.size();
			for (int i = 0; i < cnt; i++) {
				Document row = (Document) vt.elementAt(i);

				String rowStatus = XMLUtil.getAttribute(row, "rowStatus", "value");
				if (rowStatus.equals("C")) {
					humsd03Handler.insertHUMSD03(row);
				} else if (rowStatus.equals("U")) {
					humsd03Handler.updateHUMSD03(row);
				} else if (rowStatus.equals("E")) {
					humsd03Handler.deleteHUMSD03(row);
				}
			}

			result = XMLUtil.getDocument("<HUMSD03 result='" + cnt + "' msg='success'/>");

			dbConn.commit();

		} catch (Exception e) {
			throw e;
		} finally {

		}

		return result;

	}
	
	public Document getResList(Document doc) throws Exception {
		DBConnection dbConn = null;
		Vector result = null;
		Document res;
		try {
			dbConn = new DefaultDBConn().getConnection();
			HUMSD03addHandler humsd03Handler = HUMSD03addHandler.getInstance(dbConn);
			result = humsd03Handler.selectList(doc);

			res = XMLUtil.toXML(result);

		} catch (Exception e) {
			throw e;
		} finally {
			try {
				dbConn.close();
			} catch (Exception e) {
			}
		}

		return res;
	}
	

	public Document saveAssign(Document doc) throws Exception {
		DBConnection dbConn = null;
		Document result = null;
		try {
			dbConn = new DefaultDBConn().getConnection();
			dbConn.setAutoCommit(false);
			HUMSD05addHandler humsd05Handler = HUMSD05addHandler.getInstance(dbConn);
			HUMSD01addHandler humsd01Handler = HUMSD01addHandler.getInstance(dbConn);

			Node node = doc.getDocumentElement();
			NodeList dataList = node.getChildNodes();
			Node vectorNode = dataList.item(0);
			

			Vector vt = XMLUtil.toVector(XMLUtil.getDocument((Element) vectorNode));
			

			int cnt = vt.size();
			for (int i = 0; i < cnt; i++) {
				Document row = (Document) vt.elementAt(i);

				String rowStatus = XMLUtil.getAttribute(row, "rowStatus", "value");
				if (rowStatus.equals("C")) {
					humsd05Handler.insertHUMSD05(row);
				} else if (rowStatus.equals("U")) {
					humsd05Handler.updateHUMSD05(row);
				} else if (rowStatus.equals("E")) {
					humsd05Handler.deleteHUMSD05(row);
				}
			}
			
			String reqNo = XMLUtil.getAttribute(doc, "vector", "reqNo");
			//System.out.println("@@@222 reqNo = " + reqNo);
			humsd01Handler.updateAssignee(reqNo);
			
			

			result = XMLUtil.getDocument("<HUMSD05 result='" + cnt + "' msg='success'/>");

			dbConn.commit();

		} catch (Exception e) {
			throw e;
		} finally {

		}
		return result;
	}
	

	public Document getAssignList(Document doc) throws Exception {
		DBConnection dbConn = null;
		Vector result = null;
		Document res;
		try {
			dbConn = new DefaultDBConn().getConnection();
			HUMSD05addHandler humsd05Handler = HUMSD05addHandler.getInstance(dbConn);
			result = humsd05Handler.selectList(doc);
			res = XMLUtil.toXML(result);

		} catch (Exception e) {
			throw e;
		} finally {
			try {
				dbConn.close();
			} catch (Exception e) {
			}
		}

		return res;
	}
	

	public Document getReqDetail(Document doc) throws Exception {
		DBConnection dbConn = null;
		Document res;
		try {
			dbConn = new DefaultDBConn().getConnection();
			
			// 첨부파일 리스트
			HUMSD02addHandler humsd02Handler = HUMSD02addHandler.getInstance(dbConn);
			Document result1 = XMLUtil.toXML(humsd02Handler.selectList(doc));
			XMLUtil.setAttribute(result1, "id", "HUMSD02");
			String str1 = XMLUtil.indent(result1);
			
			// res 리스트
			HUMSD03addHandler humsd03Handler = HUMSD03addHandler.getInstance(dbConn);
			Document result2 = XMLUtil.toXML(humsd03Handler.selectList(doc));
			XMLUtil.setAttribute(result2, "id", "HUMSD03");
			String str2 = XMLUtil.indent(result2);
			
			// assign 리스트
			HUMSD05addHandler humsd05Handler = HUMSD05addHandler.getInstance(dbConn);
			Document result3 = XMLUtil.toXML(humsd05Handler.selectList(doc));
			XMLUtil.setAttribute(result3, "id", "HUMSD05");
			String str3 = XMLUtil.indent(result3);
			
			res = XMLUtil.getDocument("<HUMSD>" + str1 + str2 + str3 + "</HUMSD>");

			//System.out.println("@@33 = " + XMLUtil.indent(res));
			

		} catch (Exception e) {
			throw e;
		} finally {
			try {
				dbConn.close();
			} catch (Exception e) {
			}
		}

		return res;
	}		
}