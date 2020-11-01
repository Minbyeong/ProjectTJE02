<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="Product.ProductDAO"%>
<%@page import="Product.ProductMainVO"%>
<%@page import="Product.ProductSubVO"%>
<%@page import="Product.ProductAddVO"%>
<%@page import="Product.ShoppingListVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%

	String user_id = (String) session.getAttribute("id"); <!--새션의 id값을 가져온다-->
	String p_id = request.getParameter("p_id"); <!--이전 페이지에서 넘겨온 값들을 받아주고 있다-->
	String ps_con = request.getParameter("ps_content");
	String ps_price = request.getParameter("ps_price");

	ProductDAO dao = new ProductDAO(); <!--사용할 dao를 선언해주고-->
	JSONObject jo = dao.add(p_id); <!--해당 메서드가 jsonobject로 받아주고 있기 때문에 json객체에 담아주고 있다.-->

	ProductMainVO bag1 = new ProductMainVO();
	bag1 = dao.one(p_id); <!--장바구니에 저장할 내용중에 부족한 부분을 보충해주기 위해서 해당 상품에 대한 정보를 담아와서 추가로 사용을 해주었다-->
	String p_name = bag1.getP_name();
	String p_content = bag1.getP_content();
	String p_pic = bag1.getP_pic();

	String ps_content = ps_con + p_content;

	ShoppingListVO bag2 = new ShoppingListVO();
	bag2.setP_id(p_id);
	bag2.setP_name(p_name);
	bag2.setPs_content(ps_content);
	bag2.setPs_price(Integer.parseInt(ps_price));
	bag2.setP_pic(p_pic);
	bag2.setUser_id(user_id);
	dao.create(bag2); <!--db에 선택한 상품을 담는 과정-->
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>AppleMango</title>
<!-- Favicon-->
<link rel="icon" type="image/x-icon" href="assets/img/favicon.ico" />
<!-- Font Awesome icons (free version)-->
<script src="https://use.fontawesome.com/releases/v5.13.0/js/all.js"
	crossorigin="anonymous"></script>
<!-- Google fonts-->
<link href="https://fonts.googleapis.com/css?family=Montserrat:400,700"
	rel="stylesheet" type="text/css" />
<link
	href="https://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic"
	rel="stylesheet" type="text/css" />
<!-- Core theme CSS (includes Bootstrap)-->
<link href="css/styles.css" rel="stylesheet" />
<script type="text/javascript" src="js/jquery-3.5.1.js"></script>
<script type="text/javascript">
	var json = <%=jo%>; //위에서 가져온 json객체를 사용하려고 선언
	var user_id = "<%= user_id %>"
		$(document).ready(function() {
			if (user_id != "null") {
				$('#login').text('Logout')
			} else {
			$('#login').text('Login')
		}
		})
	$(function() { //json으로 저장된 내용을 꺼내주고 사용자에게 있는 모든 장바구니 목록을 호출해서 화면에 아래 방향으로 찍어주었다.
		for (var i = 1; i < 4; i++) {
			$('#add-product1_'+i).prepend(
				'<img src='+json.add[(i-1)].pa_pic+' widdth=324px height=240px>',
				'<div>'+json.add[(i-1)].pa_content+'</div>',
				'<div>'+json.add[(i-1)].pa_price+'원</div>'
			) //prepend
		} //for
		
		
		$('.ap-add').click(function() {
			idValue = $(this).attr('id')
			console.log(idValue)
			$.ajax({ //추가로 주문하는 상품의 경우 다음페이지에서 장바구니에 넣는 것이 아닌, 현 페이지에서 db저장을 진행해야하기 때문에 ajax를 사용하여 db저장 과정을 진행
					url: "godb.jsp",
					data: {
						p_id: json.add[idValue].p_id,
						p_name: json.add[idValue].pa_name,
						ps_content: json.add[idValue].pa_content,
						ps_price: json.add[idValue].pa_price,
						p_pic: json.add[idValue].pa_pic,
						user_id: user_id
					}, //data
					success: function(result) { // 성공적으로 db의 저장이 진행이 되면 알림을 띄워준다.
						alert('상품이 장바구니에 추가되었습니다.')
					} //success
				}) //ajax
			}) //click
		
		$('#go-list').click(function() { 
			location.href = 'call_list.jsp'
		})
		
		$('#cart').click(function() {
					if (user_id == "null") {
						location.href = "../login/memlogin.html";
					} else {
						location.href = "../product/call_list.jsp";
					}
				})
				
				$('#login').click(function() {
					if (user_id == "null") {
						location.href = "../login/memlogin.html";
					} else {
						$.ajax({
							url: 'logout.jsp',
							success: function() {
								location.href = "product_add.jsp";
							}
						})
					}
				})
	})
</script>

<style>
div#main-body {
	padding-top: calc(6rem + 74px);
	padding-bottom: 6rem;
	text-align: center;
	line-height: 1.57143;
}

div#main-title {
	padding: 64px 0 34px;
}

div#main-title h2 {
	font-size: 40px;
}

div#add-product {
	display: grid;
	grid-template-columns: 1fr 1fr 1fr 1fr 1fr;
	padding-top: 50px;
	font-size: 17px;
}
</style>
</head>
<body id="page-top">
	<!-- Navigation-->
	<nav
		class="navbar navbar-expand-lg bg-secondary text-uppercase fixed-top"
		id="mainNav" style="background : #000 !important;">
		<div class="container">
			<a class="navbar-brand js-scroll-trigger" href="index.html"><img
				style="width: 50px" src="img/full_color.png" alt="" />AppleMANGO</a>
			<button
				class="navbar-toggler navbar-toggler-right text-uppercase font-weight-bold bg-secondary text-white rounded"
				type="button" data-toggle="collapse" data-target="#navbarResponsive"
				aria-controls="navbarResponsive" aria-expanded="false"
				aria-label="Toggle navigation">
				Menu <i class="fas fa-bars"></i>
			</button>
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item mx-0 mx-lg-1"><a
						class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger"
						href="../product/product_all.jsp">Products</a></li>
					<li class="nav-item mx-0 mx-lg-1"><a
						class="nav-link py-3 px-0 px-lg-3 rounded"
						href="../music/New.jsp">Music</a></li>
					<li class="nav-item mx-0 mx-lg-1"><a
						class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger"
						href="../login/customer.jsp">고객지원</a></li>
					<li class="nav-item mx-0 mx-lg-1"><a style="padding: 5px"
						class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger"
						id="cart"
						href="../product/call_list.jsp"><img style="width: 30px" src="img/cart.png"
							alt="" /></a></li>
					<li class="nav-item mx-0 mx-lg-1"><a
						class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger"
						id="login"
						href="">Login</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<!-- Masthead-->
	<div id="main-body">
		<div id="main-title">
			<h2>꼭 필요한 것부터 알아두면 좋은 것까지</h2>
			<button id="go-list">장바구니 확인</button>
		</div>
		<div id="add-product">
			<div></div>
			<div id="add-product1_1">
				<button class="ap-add" id="0">장바구니에 담기</button>
			</div>
			<div id="add-product1_2">
				<button class="ap-add" id="1">장바구니에 담기</button>
			</div>
			<div id="add-product1_3">
				<button class="ap-add" id="2">장바구니에 담기</button>
			</div>
			<div></div>
		</div>
	</div>
</body>
</html>

