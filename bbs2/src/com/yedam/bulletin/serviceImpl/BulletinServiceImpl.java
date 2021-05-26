package com.yedam.bulletin.serviceImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yedam.bulletin.service.BulletinService;
import com.yedam.bulletin.vo.BulletinVO;
import com.yedam.common.DAO;

public class BulletinServiceImpl extends DAO implements BulletinService{
	PreparedStatement psmt;
	ResultSet rs;
	String sql;
	
	@Override
	public List<BulletinVO> BulletinSelectList() {
		sql = "select * from bulletin order by 1";
		List<BulletinVO> list = new ArrayList<>();
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
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
		}finally {
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
			if(rs.next()) {
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

		return 0;
	}

	@Override
	public int updateBulletin(BulletinVO vo) {

		return 0;
	}

	@Override
	public int deleteBulletin(BulletinVO vo) {

		return 0;
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
