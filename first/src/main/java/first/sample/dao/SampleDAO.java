package first.sample.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import first.common.dao.AbstractDAO;

@Repository("sampleDAO")  //@Repository DAO선언시 주로 사용
public class SampleDAO extends AbstractDAO  //오 요건 extends로 상속 받았으니 그대로 쓰면됨
{
	
	@SuppressWarnings("unchecked") //에러 경고문 필요없음
	public Map<String, Object> selectBoardList(Map<String, Object> map) throws Exception
	{
		//return (List<Map<String, Object>>) selectList("sample.selectBoardList", map);
		//첫번째 인자값이 쿼리 이름 -> sample.selectBoardList
		return (Map<String, Object>) selectPagingList("sample.selectBoardList", map);
	}
	
	
	
	
	@SuppressWarnings("unchecked") //에러 경고문 필요없음
	public Map<String, Object> selectBoardList1(Map<String, Object> map) throws Exception
	{
		//return (List<Map<String, Object>>) selectList("sample.selectBoardList", map);
		//첫번째 인자값이 쿼리 이름 -> sample.selectBoardList
		return (Map<String, Object>) selectPagingList("sample.selectBoardList", map);
	}

	
	
	
	
	public void insertBoard(Map<String, Object> map) throws Exception
	{
		insert("sample.insertBoard", map);
	}
	
	public void insertFile(Map<String, Object> map) throws Exception
	{
		insert("sample.insertFile", map);
	}
	
	/*public void updateHitCnt(Map<String, Object> map) throws Exception
	{
		update("sample.updateHitCnt", map);
	}*/
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> selectBoardDetail(Map<String, Object> map) throws Exception{
		return (Map<String, Object>) selectOne("sample.selectBoardDetail", map);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectFileList(Map<String, Object> map) throws Exception
	{
		return (List<Map<String, Object>>) selectList("sample.selectFileList", map);
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> boardUpdate(Map<String, Object> map) throws Exception
	{
		return (Map<String, Object>) selectOne("sample.boardUpdate", map);
	}
	
	public void updateBoard(Map<String, Object> map) throws Exception
	{
		update("sample.updateBoard", map);
	}

	public void deleteBoard(Map<String, Object> map) throws Exception 
	{
		delete("sample.deleteBoard", map);
	}

	public void updateHitCnt(Map<String, Object> map) 
	{
		update("sample.updateHitCnt", map);
	}

	public void deleteFileList(Map<String, Object> map) 
	{
		update("sample.deleteFileList", map);
	}

	public void updateFile(Map<String, Object> map) 
	{
		update("sample.updateFile", map);
	}
	
}
