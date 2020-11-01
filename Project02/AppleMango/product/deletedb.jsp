<%@page import="Product.ProductDAO"%>
<%@page import="Product.ShoppingListVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!--장바구니 목록의 삭제 ajax를 처리해주는 jsp-->
<%
	String l_id1 = request.getParameter("l_id"); <!--넘겨 받은 리스트이 id값은 string이기 때문에 받아서 int형으로 변환을 해줌-->
	System.out.println(l_id1);
	int l_id = Integer.parseInt(l_id1);
	System.out.println(l_id);
	ProductDAO dao = new ProductDAO();
	boolean result = dao.delete(l_id);
%><%=result%>