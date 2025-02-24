package action.admin;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import mybatis.dao.ProductDAO;
import mybatis.vo.ProductVO;

public class ProductListDeleteAction implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // TODO Auto-generated method stub
        String[] pdIdxArray = request.getParameterValues("pd_idx_list");
        ServletContext servletContext = request.getServletContext();
        String imgPath = servletContext.getRealPath("/img");
        File imgPathFile = new File(imgPath);
        if (pdIdxArray != null) {
            for (String p_id : pdIdxArray) {
                ProductVO pvo = ProductDAO.findByid(p_id);
                File[] files = imgPathFile.listFiles();
                if (files != null)
                    for (File file : files) {
                        if (file.getName().equals(pvo.getPd_thumbnail_img()))
                            file.delete();
                        if (file.getName().equals(pvo.getPd_detail_img()))
                            file.delete();
                    }
            }
            Map<String, Object> params = new HashMap<>();
            params.put("pd_idx_list", Arrays.asList(pdIdxArray));
            int cnt = ProductDAO.deleteProducts(params);
            response.getWriter().write(cnt + " products deleted.");
        } else {
            response.getWriter().write("No products selected for deletion.");
        }


        return "admin?type=productList";
    }

}
