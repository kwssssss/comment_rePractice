package org.galapagos.mapper;

import org.galapagos.domain.AuthVO;
import org.galapagos.domain.MemberVO;

public interface MemberMapper {
	public MemberVO read(String username);
	
	public void insert(MemberVO member); // db작업에 특화된 명칭으로 작성해야함, insert delete update 기타등등...
	
	public void insertAuth(AuthVO auth); // 권한 부여
}
