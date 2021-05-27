package com.yedam.product.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.DbCommand;
import com.yedam.product.service.ProductService;
import com.yedam.product.serviceImpl.ProductServiceImpl;
import com.yedam.product.vo.ProductVO;

public class CartList implements DbCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String uid = request.getParameter("uid");
		ProductServiceImpl service = new ProductServiceImpl();
		
		
		List<ProductVO> list = service.cartList(uid);
		
		request.setAttribute("cartList", list);
		
		return "product/cartList.tiles";
	}

}
