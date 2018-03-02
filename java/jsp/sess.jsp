<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
%><%@ page import="java.io.File
				, java.io.IOException
				, java.io.PrintWriter
				, java.util.List
				, java.text.SimpleDateFormat
				, java.util.Date
				, java.util.Locale
				, javax.servlet.ServletException
				, javax.servlet.http.HttpServlet
				, javax.servlet.http.HttpServletRequest
				, javax.servlet.http.HttpServletResponse
				, org.apache.commons.fileupload.FileItem
				, org.apache.commons.fileupload.disk.DiskFileItemFactory
				, org.apache.commons.fileupload.servlet.ServletFileUpload
				, websquare.WebSquareConfig,java.io.*
				, java.lang.*"
%><%!


%><%
response.setHeader("Access-Control-Allow-Origin", "*");
response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
response.setHeader("Access-Control-Allow-Headers", "content-type,submissionid");
request.setCharacterEncoding("EUC-KR");

System.out.println("sess.jsp start==============");


String sabun = (String)session.getAttribute("sSabun");

System.out.println("@@ session.getMaxInactiveInterval() = " + session.getMaxInactiveInterval());
System.out.println("@@ sSabun = " + sabun);
out.println("@@ sSabun = " + sabun);
out.println("@@ session.getMaxInactiveInterval() = " + session.getMaxInactiveInterval());
%>