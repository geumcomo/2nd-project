package action.admin;

import java.io.File;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import action.Action;
import mybatis.dao.ProductDAO;

public class ProductSaveAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewPath = null;
		String enc_type = request.getContentType();

		if (enc_type == null) {
			viewPath = "/jsp/admin/productAdd.jsp";
		} else if (enc_type.startsWith("multipart")) {
			try {
				// 1. 파일 저장 경로 설정
				ServletContext application = request.getServletContext();
				String uploadDir = "/img"; // 웹에서 접근할 상대 경로
				String imgPath = application.getRealPath(uploadDir); // 실제 서버 저장 경로

				// 업로드 디렉토리가 없으면 생성
				File dir = new File(imgPath);
				if (!dir.exists()) dir.mkdirs();

				// 2. MultipartRequest 생성
				MultipartRequest mr = new MultipartRequest(request, imgPath, 5 * 1024 * 1024, "utf-8",
						new DefaultFileRenamePolicy());

				// 3. 폼에서 입력받은 데이터 가져오기
				String pd_name = mr.getParameter("pd_name");
				String ct_idx = mr.getParameter("ct_idx");
				String pd_cnt = mr.getParameter("pd_cnt");
				String pd_sale = mr.getParameter("pd_sale");
				if (pd_sale.trim().length() < 1) {
					pd_sale = "0";
				}
				String pd_price = mr.getParameter("pd_price");

				// 4. 업로드된 파일 처리
				File f = mr.getFile("pd_thumbnail_img");
				File f2 = mr.getFile("pd_detail_img");

				// 5. 파일 경로를 DB에 저장할 값으로 변환
				String pd_thumbnail_path = (f != null) ? (uploadDir + "/" + f.getName()) : "";
				String pd_detail_path = (f2 != null) ? (uploadDir + "/" + f2.getName()) : "";

				// 6. HashMap에 데이터 저장
				HashMap<String, String> map = new HashMap<>();
				map.put("pd_name", pd_name);
				map.put("ct_idx", ct_idx);
				map.put("pd_cnt", pd_cnt);
				map.put("pd_sale", pd_sale);
				map.put("pd_price", pd_price);
				map.put("pd_thumbnail_path", pd_thumbnail_path);
				map.put("pd_thumbnail_img", f.getName());
				map.put("pd_detail_path", pd_detail_path);
				map.put("pd_detail_img", f2.getName());

				// 7. DB 저장
				ProductDAO.addProduct(map);

				// 8. 다음 페이지로 이동
				viewPath = "/jsp/admin/productSave.jsp";
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return viewPath;
	}
}
