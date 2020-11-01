<%@page import="Product.ShoppingListVO"%>
<%@page import="Product.ProductDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<!--ajax로 넘긴 부분을 처리해주는 jsp-->
<jsp:useBean id="vo" class="Product.ShoppingListVO"></jsp:useBean> <!--shoppinglist vo를 사용한다 지정-->
<jsp:setProperty property="*" name="vo" /> <!--넘겨서 받은 값을 전부 사용-->
<%
	ProductDAO dao = new ProductDAO(); 
	boolean result = dao.create(vo); <!--sql create문 진행-->
%><%=result%>
