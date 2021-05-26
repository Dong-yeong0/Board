package com.yedam.bulletin.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.bulletin.service.BulletinService;
import com.yedam.bulletin.serviceImpl.BulletinServiceImpl;
import com.yedam.bulletin.vo.BulletinVO;
import com.yedam.common.DbCommand;

public class BulletinUpdate implements DbCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {

		String path = "";
		String id = request.getParameter("id");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		BulletinService service = new BulletinServiceImpl();
		BulletinVO vo = new BulletinVO();

		if (request.getMethod().equals("POST")) {
			vo.setId(Integer.parseInt(id));
			vo.setTitle(title);
			vo.setContent(content);

			int n = service.updateBulletin(vo);
			if (n != 0) {
				path = "/bulletinListPaging.do";
			} else {
				path = "/bulletin.do";
			}

		} else { // Get 방식일때는 Delete
			vo.setId(Integer.parseInt(id));
			int n = service.deleteBulletin(vo);
			if (n != 0) {
				path = "/bulletinListPaging.do";
			} else {
				path = "/bulletin.do";
			}

		}
		return path;
	}

}
