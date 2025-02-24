package action.admin;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import action.Action;
import mybatis.dao.ProductDAO;

public class ProductUpdateAction implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String viewPath = null;
        String enc_type = request.getContentType();
        if (enc_type == null) {
            viewPath = "/jsp/admin/productDetail.jsp";
        } else if (enc_type.startsWith("multipart")) {
            try {
                ServletContext application = request.getServletContext();
                String uploadDir = "/img";
                String imgPath = application.getRealPath(uploadDir);
                MultipartRequest mr = new MultipartRequest(request, imgPath, 5 * 1024 * 1024, "utf-8",
                        new DefaultFileRenamePolicy());

                // 폼 데이터 가져오기
                String pd_idx = mr.getParameter("pd_idx");
                String pd_name = mr.getParameter("pd_name");
                String ct_idx = mr.getParameter("ct_idx");
                String pd_price = mr.getParameter("pd_price");
                String pd_cnt = mr.getParameter("pd_cnt");
                String pd_sale = mr.getParameter("pd_sale");

                // 기존 파일명 가져오기
                String existingThumbnailImg = mr.getParameter("existingThumbnailImg");
                String existingDetailImg = mr.getParameter("existingDetailImg");

                // 파일 객체 선언 및 초기화
                File f = mr.getFile("updateImg");
                File f2 = mr.getFile("updateDetailImg");

                // 파일명이 다를 경우에만 업데이트
                String pd_thumbnail_img = (f != null) ? f.getName() : existingThumbnailImg;
                String pd_detail_img = (f2 != null) ? f2.getName() : existingDetailImg;

                // 파라미터 값을 맵에 저장
                Map<String, String> map = new HashMap<>();
                map.put("pd_idx", pd_idx);
                map.put("pd_name", pd_name);
                map.put("ct_idx", ct_idx);
                map.put("pd_price", pd_price);
                map.put("pd_cnt", pd_cnt);
                if (pd_sale != null && !pd_sale.isEmpty()) {
                    map.put("pd_sale", pd_sale);
                }
                if (pd_thumbnail_img != null && !pd_thumbnail_img.isEmpty()) {
                    map.put("pd_thumbnail_img", pd_thumbnail_img);
                    map.put("pd_thumbnail_path", uploadDir + "/" + pd_thumbnail_img);
                }
                if (pd_detail_img != null && !pd_detail_img.isEmpty()) {
                    map.put("pd_detail_img", pd_detail_img);
                    map.put("pd_detail_path", uploadDir + "/" + pd_detail_img);
                }

                // 데이터베이스에 상품 정보 업데이트
                int cnt = ProductDAO.updateProduct(map);
                if (cnt > 0) {
                    File imgPathFile = new File(imgPath);
                    File[] files = imgPathFile.listFiles();
                    for (File file : files) {
                        if(!existingThumbnailImg.equals(pd_thumbnail_img)) {
                            if (file.getName().equals(existingThumbnailImg))
                                file.delete();
                        }
                        if(!existingDetailImg.equals(pd_detail_img)){
                            if (file.getName().equals(existingDetailImg))
                                file.delete();
                        }
                    }
                    request.setAttribute("updatemsg", "상품 정보가 성공적으로 업데이트되었습니다.");
                } else {
                    request.setAttribute("updatemsg", "상품 정보 업데이트에 실패했습니다.");
                }

                viewPath = "admin?type=productDetail&pd_idx=" + pd_idx;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return viewPath;
    }
}
