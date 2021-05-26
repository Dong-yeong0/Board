package com.yedam.bulletin.serviceImpl;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yedam.bulletin.service.BulletinService;
import com.yedam.bulletin.vo.BulletinVO;
import com.yedam.common.DAO;
import com.yedam.notice.vo.NoticeVO;

public class BulletinServiceImpl extends DAO implements BulletinService {
	PreparedStatement psmt;
	ResultSet rs;
	String sql;

	// 페이징

	public List<BulletinVO> bulletinListPaging(int page) {
		String sql = "select b.* from(select rownum m, a.*"
				+ "from (select * from bulletin n order by n.id)a) b where b.m between ? and ?";
		List<BulletinVO> list = new ArrayList<>();
		int firstCnt = 0;
		int lastCnt = 0;
		firstCnt = (page - 1) * 10 + 1; // 1
		lastCnt = (page * 10); // 10

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, firstCnt);
			psmt.setInt(2, lastCnt);
			rs = psmt.executeQuery();
			while (rs.next()) {
				BulletinVO vo = new BulletinVO();
				vo.setId(rs.getInt("id"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setWriter(rs.getString("writer"));
				vo.setRegDate(rs.getDate("reg_date"));
				vo.setHit(rs.getInt("hit"));
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}

	@Override
	public List<BulletinVO> BulletinSelectList() {
		sql = "select * from bulletin order by 1";
		List<BulletinVO> list = new ArrayList<>();
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				BulletinVO vo = new BulletinVO();
				vo.setId(rs.getInt("id"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setWriter(rs.getString("writer"));
				vo.setRegDate(rs.getDate("reg_Date"));
				vo.setHit(rs.getInt("hit"));
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}

	@Override
	public BulletinVO BulletinSelect(BulletinVO vo) {
		sql = "select * from bulletin where id = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, vo.getId());
			rs = psmt.executeQuery();
			if (rs.next()) {
				hitCount(vo.getId());
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setWriter(rs.getString("writer"));
				vo.setRegDate(rs.getDate("reg_Date"));
				vo.setHit(rs.getInt("hit"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return vo;
	}

	@Override
	public int insertBulletin(BulletinVO vo) {
		sql = "insert into bulletin(id, writer, title,content,reg_date, hit) values(bulletin_seq.nextval ,?,?,?, sysdate,0)";
		int n = 0;
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getWriter());
			psmt.setString(2, vo.getTitle());
			psmt.setString(3, vo.getContent());

			n = psmt.executeUpdate();
			if (n != 0) {
				System.out.println("입력완료");
			} else {
				System.out.println("입력실패");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return n;
	}

	@Override
	public int updateBulletin(BulletinVO vo) {
		sql = "update bulletin set title = ?, content = ? where id = ? ";
		int n = 0;
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getTitle());
			psmt.setString(2, vo.getContent());
			psmt.setInt(3, vo.getId());
			n = psmt.executeUpdate();
			if (n != 0) {
				System.out.println("업데이트 완료");
			} else {
				System.out.println("업데이트 실패");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return n;
	}

	@Override
	public int deleteBulletin(BulletinVO vo) {
		sql = "delete from bulletin where id = ?";
		int n = 0;
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, vo.getId());
			n = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return n;
	}

	// 조회수
	public void hitCount(int id) {
		String sql = "update bulletin set hit = hit+1 where id=?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, id);
			psmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void close() {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (psmt != null) {
			try {
				psmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
