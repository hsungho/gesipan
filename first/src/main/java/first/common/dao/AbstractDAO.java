package first.common.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

public class AbstractDAO {
    protected Log log = LogFactory.getLog(AbstractDAO.class);
     
    @Autowired
    private SqlSessionTemplate sqlSession;  /* SqlSessionTemplate 는 context-mapper.xml에 정의됨*/
     
    protected void printQueryId(String queryId) {
        if(log.isDebugEnabled()){
            log.debug("\t QueryId  \t:  " + queryId);
        }
    }
     
    public Object insert(String queryId, Object params){
        printQueryId(queryId);
        return sqlSession.insert(queryId, params);
    }
     
    public Object update(String queryId, Object params){
        printQueryId(queryId);
        return sqlSession.update(queryId, params);
    }
     
    public Object delete(String queryId, Object params){
        printQueryId(queryId);
        return sqlSession.delete(queryId, params);
    }
     
    public Object selectOne(String queryId){
        printQueryId(queryId);
        return sqlSession.selectOne(queryId);
    }
     
    public Object selectOne(String queryId, Object params){
        printQueryId(queryId);
        return sqlSession.selectOne(queryId, params);
    }
     
    @SuppressWarnings("rawtypes")
    public List selectList(String queryId){
        printQueryId(queryId);
        return sqlSession.selectList(queryId);
    }
     
    @SuppressWarnings("rawtypes")
    public List selectList(String queryId, Object params){
        printQueryId(queryId);
        return sqlSession.selectList(queryId,params);
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public Map selectPagingList(String queryId, Object params)
    {
    	printQueryId(queryId);
    	
    	Map<String, Object> map = (Map<String, Object>) params;
    	PaginationInfo paginationInfo = null;
    	
    	//containsKey 일치여부
    	if(map.containsKey("currentPageNo") == false || StringUtils.isEmpty(map.get("currentPageNo")) == true)
    		map.put("currentPageNo", "1");
    	
    	paginationInfo = new PaginationInfo();
    	paginationInfo.setCurrentPageNo(Integer.parseInt(map.get("currentPageNo").toString()));
    	if(map.containsKey("PAGE_ROW") == false || StringUtils.isEmpty(map.get("PAGE_ROW")) == true)
    	{
    		paginationInfo.setRecordCountPerPage(15); //몇개의 행을 보여줄지 셋
    	}else{
    		paginationInfo.setRecordCountPerPage(Integer.parseInt(map.get("PAGE_ROW").toString()));
    	}
    	paginationInfo.setPageSize(10);  //페이지 크기 지정 1~10
    	
    	int start = paginationInfo.getFirstRecordIndex();
		int end = start + paginationInfo.getRecordCountPerPage();
		map.put("START",start+1);
		map.put("END",end);
		
		params = map;
		
		Map<String,Object> returnMap = new HashMap<String,Object>();
		List<Map<String,Object>> list = sqlSession.selectList(queryId,params);
		
		if(list.size() == 0){
			map = new HashMap<String,Object>();
			map.put("TOTAL_COUNT",0);  
			list.add(map);
			
			if(paginationInfo != null){
				paginationInfo.setTotalRecordCount(0);
				returnMap.put("paginationInfo", paginationInfo);
			}
		}
		else{
			if(paginationInfo != null){
				paginationInfo.setTotalRecordCount(Integer.parseInt(list.get(0).get("TOTAL_COUNT").toString()));
				returnMap.put("paginationInfo", paginationInfo);
			}
		}
		returnMap.put("result", list);
		return returnMap;
    }
    
    /*@SuppressWarnings("unchecked")
    public Object selectPagingList(String queryId, Object params)
    {
    	printQueryId(queryId);
    	Map<String, Object> map = (Map<String, Object>)params;
    	
    	String strPageIndex = (String)map.get("PAGE_INDEX");  //페이지 번호
    	String strPageRow = (String)map.get("PAGE_ROW");   //페이지 행수
    	int nPageIndex = 0;
    	int nPageRow = 20;
    	
    	if(StringUtils.isEmpty(strPageIndex) == false)
    	{
    		nPageIndex = Integer.parseInt(strPageIndex)-1;
    	}
    	if(StringUtils.isEmpty(strPageRow) == false)
    	{
    		nPageRow = Integer.parseInt(strPageRow);
    	}
    	
    	map.put("START", (nPageIndex * nPageRow) + 1);
    	map.put("END", (nPageIndex * nPageRow) + nPageRow);
    	
    	return sqlSession.selectList(queryId, map);
    }*/
}


