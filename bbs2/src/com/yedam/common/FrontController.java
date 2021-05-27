package com.yedam.common;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.bulletin.web.BulletinForm;
import com.yedam.bulletin.web.BulletinInsert;
import com.yedam.bulletin.web.BulletinList;
import com.yedam.bulletin.web.BulletinListPaging;
import com.yedam.bulletin.web.BulletinSelete;
import com.yedam.bulletin.web.BulletinUpdate;
import com.yedam.member.web.MemberJoin;
import com.yedam.member.web.MemberJoinForm;
import com.yedam.member.web.MemberLogOut;
import com.yedam.member.web.MemberLogin;
import com.yedam.member.web.MemberLoginForm;
import com.yedam.notice.web.Notice;
import com.yedam.notice.web.NoticeDelete;
import com.yedam.notice.web.NoticeForm;
import com.yedam.notice.web.NoticeInsert;
import com.yedam.notice.web.NoticeList;
import com.yedam.notice.web.NoticeListPaging;
import com.yedam.notice.web.NoticeUpdate;
import com.yedam.product.web.AddCart;
import com.yedam.product.web.CartList;
import com.yedam.product.web.ProductList;

public class FrontController extends HttpServlet {
	private HashMap<String, DbCommand> map = new HashMap<>();

	@Override
	public void init(ServletConfig config) throws ServletException {
		// 요청페이지 - 실행컨트롤러
		map.put("/index.do", new IndexPage());
		map.put("/main.do", new MainPage());
		map.put("/memberJoinForm.do", new MemberJoinForm());
		map.put("/memberJoin.do", new MemberJoin());
		map.put("/memberLoginForm.do", new MemberLoginForm());
		map.put("/memberLogin.do", new MemberLogin());
		map.put("/memberLogOut.do", new MemberLogOut());

		//공지사항
		map.put("/noticeList.do", new NoticeList());
		map.put("/noticeListPaging.do", new NoticeListPaging());
		map.put("/noticeForm.do", new NoticeForm());
		map.put("/notice.do", new Notice());
		map.put("/noticeUpdate.do", new NoticeUpdate());
		map.put("/noticeInsert.do", new NoticeInsert());
		map.put("/noticeDelete.do", new NoticeDelete());
		
		// 게시글관련
		map.put("/bulletinList.do", new BulletinList());
		map.put("/bulletinForm.do", new BulletinForm());
		map.put("/bulletinInsert.do", new BulletinInsert());
		map.put("/bulletinSelete.do", new BulletinSelete());
		map.put("/bulletinUpdate.do", new BulletinUpdate());
		map.put("/bulletinListPaging.do", new BulletinListPaging());
		
		// 상품관련
		map.put("/productList.do", new ProductList());
		map.put("/addCart.do",new AddCart());
		map.put("/cartList.do", new CartList());
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String uri = req.getRequestURI();
		String cpath = req.getContextPath();
		String path = uri.substring(cpath.length());
		DbCommand command = map.get(path);
		String viewPage = command.execute(req, resp);

		RequestDispatcher rd = req.getRequestDispatcher(viewPage);
		rd.forward(req, resp);
	}
}
