<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@include file="/jsp/common/adminHeader.jsp"%>

	<h1>리뷰 상세보기</h1>
	<form action="" method="post" class="form-group">
		<div>
			<label for="title">제목</label> <input type="text" name="title"
				value="${vo.bo_title }" disabled />
		</div>
		<div>
			<label for="writeDate">작성일</label>
			<input type="text" name="writeDate"	value="${vo.bo_write_date }" disabled />
		</div>
		<div>
			<label for="writer">작성자</label> <input type="text" name="writer"
				value="<c:if test='${null eq vo.uvo.us_name}'>탈퇴한 회원</c:if><c:if test='${null ne vo.uvo.us_name}'>${vo.uvo.us_name}</c:if>"disabled />
		</div>
		<div>
			<label for="content">상품</label>
			<input type="text" name="scroe" value="${vo.pvo.pd_name}" disabled />
		</div>
		<div>
			<label for="score">평점</label> <input type="text" name="Score"
				value="${vo.bo_score}" disabled />
		</div>
		<div>
			<label for="content" >내용</label> <%-- <input type="text" name="content"
				value="${vo.bo_content}" disabled /> --%>
				<div style="border: 1px solid #ddd; border-radius:8px; background:#fff; padding: 12px;">${vo.bo_content}</div>
		</div>


		<div class="btn_div">
			<button type="button" class="admin-btn cancel"
				onclick="del('${vo.bo_idx}')">삭제</button>
			<button type="button" class="admin-btn submit"
				onclick="javascript:window.location.href='admin?type=reviewList'">목록</button>
		</div>
	</form>
<%@include file="/jsp/common/footer.jsp"%>
<script>
	function del(idx) {
		if (confirm("정말 삭제하시겠습니까?")) {
			window.location = "admin?type=reviewDelete&bo_idx=" + idx;
		}
	}
</script>
</body>

</html>

