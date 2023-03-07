package MVCBoard;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import util.JDBConnect;

public class MVCBoardDAO extends JDBConnect{
	public MVCBoardDAO() {
		super();
	}
	
	public List<MVCBoardDTO> selectList(Map<String, Object>map) {
		List<MVCBoardDTO> board = new ArrayList<MVCBoardDTO>();

		String sql = "SELECT * FROM board";
		
		try {
			psmt = con.prepareStatement(sql);
			//psmt.setString(1, map.get("start").toString());
			//psmt.setString(2, map.get("end").toString());
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				MVCBoardDTO dto = new MVCBoardDTO();
				dto.setNum(rs.getString("num"));
				dto.setId(rs.getString("id"));
				dto.setName(rs.getString("name"));
				dto.setSubject(rs.getString("subject"));
				dto.setContent(rs.getString("content"));
				dto.setRegist_day(rs.getString("regist_day"));
				dto.setHit(rs.getString("hit"));
				dto.setIp(rs.getString("ip"));				
				board.add(dto);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return board;		
	}
	
	public int selectCount(Map<String, Object> map) {
		int totalCount = 0;
		String query = "SELECT COUNT(*) FROM board";
		if (map.get("searchWord") != null) {
			query += " WHERE " + map.get("searchField") + " "
					+ " LIKE '%" + map.get("searchWord") + "%'";
		}
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			rs.next();
			totalCount = rs.getInt(1);
		}
		catch (Exception e) {
			System.out.println("게시물 카운트 중 예외 발생");
			e.printStackTrace();
		}

		return totalCount;
	}
	
	public List<ProductDTO> selectPList(Map<String, Object>map) {
		List<ProductDTO> board = new ArrayList<ProductDTO>();

		String sql = "SELECT * FROM product";
		
		try {
			psmt = con.prepareStatement(sql);
			//psmt.setString(1, map.get("start").toString());
			//psmt.setString(2, map.get("end").toString());
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				ProductDTO dto = new ProductDTO();
				dto.setProductId(rs.getString("productId"));
				dto.setPname(rs.getString("pname"));
				dto.setUnitPrice(rs.getString("unitPrice"));
				dto.setManufacturer(rs.getString("manufacturer"));
				dto.setCategory(rs.getString("category"));
				dto.setFilename(rs.getString("filename"));				
				dto.setQuantity(rs.getString("quantity"));				
				board.add(dto);
				
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return board;		
	}

}
