package org.galapagos.service;

import java.util.List;

import org.galapagos.domain.BoardVO;
import org.galapagos.domain.Criteria;

public interface BoardService {
	
	public void register(BoardVO board);
	
	public BoardVO get(Long bno); // 읽기 기능
	
	public boolean modify(BoardVO board); // 수정 기능
	
	public boolean remove(Long bno); // 삭제 기능

	public List<BoardVO> getList(Criteria cri);
	
	public int getTotal(Criteria cri);
}
