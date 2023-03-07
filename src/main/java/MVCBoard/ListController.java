package MVCBoard;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.BoardPage;

@WebServlet("/Project01/products.do")
public class ListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		MVCBoardDAO dao = new MVCBoardDAO();
		Map<String, Object> map = new HashMap<String, Object>();
		

		int totalCount = dao.selectCount(map);
		/* 페이지 처리 start */
		ServletContext application = getServletContext();
		int pageSize = Integer.parseInt(application.getInitParameter("POSTS_PER_PAGE"));
		int blockPage = Integer.parseInt(application.getInitParameter("PAGES_PER_BLOCK"));

		// 현재 페이지 확인
		int pageNum = 1;  // 기본값
		String pageTemp = request.getParameter("pageNum");
		if (pageTemp != null && !pageTemp.equals(""))
			pageNum = Integer.parseInt(pageTemp); // 요청받은 페이지로 수정

		// 목록에 출력할 게시물 범위 계산
		int start = (pageNum - 1) * pageSize + 1;  // 첫 게시물 번호
		int end = pageNum * pageSize; // 마지막 게시물 번호
		map.put("start", start);
		map.put("end", end);
		/* 페이지 처리 end */

		List<ProductDTO> boardLists = dao.selectPList(map);
		dao.close();

		//view Model 값 전달
		String pagingImg = BoardPage.pagingStr(totalCount, pageSize, blockPage, pageNum, "/mvcboard/list2.do");
		map.put("pagingImg", pagingImg);
		map.put("totalCount", totalCount);
		map.put("pageSize", pageSize);
		map.put("pageNum", pageNum);

		request.setAttribute("boardLists", boardLists);
		request.setAttribute("map", map);
		request.getRequestDispatcher("/Project01/products.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
