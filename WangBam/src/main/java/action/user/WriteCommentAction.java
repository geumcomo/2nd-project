package action.user;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import mybatis.dao.CommentDAO;

public class WriteCommentAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String us_idx = request.getParameter("us_idx");
		String bo_idx = request.getParameter("bo_idx");
		String co_content = request.getParameter("co_content");
		String cPage = request.getParameter("cPage");
		String bo_type = request.getParameter("bo_type");
		
		Map<String, String> map = new HashMap<>();
		map.put("us_idx", us_idx);
		map.put("bo_idx", bo_idx);
		map.put("co_content", co_content);
		
		CommentDAO.add(map);
		
		//String redirectUrl = "boardsDetail?type=boardsDetail&bo_idx=" + bo_idx + "&cPage=" + cPage + "&bo_type=" + bo_type;
        //response.sendRedirect(redirectUrl);

		request.setAttribute("bo_type", bo_type);
		request.setAttribute("bo_idx", bo_idx);
		request.setAttribute("cPage", cPage);
		
        return "/jsp/user/add_comment_success.jsp";
	}

}
