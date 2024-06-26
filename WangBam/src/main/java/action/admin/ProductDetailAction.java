package action.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import mybatis.dao.ProductDAO;
import mybatis.vo.ProductVO;

public class ProductDetailAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String pd_idx = request.getParameter("pd_idx");
		
		ProductVO pvo = ProductDAO.findByid(pd_idx);
		
		request.setAttribute("pvo", pvo);
		return "jsp/admin/productDetail.jsp";
	}

}
