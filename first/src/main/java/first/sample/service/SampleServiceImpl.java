package first.sample.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import first.common.util.FileUtils;
import first.sample.dao.SampleDAO;


@Service("sampleService")
public class SampleServiceImpl implements SampleService //인터페이스 상속시 implements
{	//SampleSeriveImpl 클래스 선언하는데 SampleSerive
	Logger log = Logger.getLogger(this.getClass()); //로그 한번 찍어주고
	
	@Resource(name="fileUtils")
	private FileUtils fileUtils;
	
	@Resource(name="sampleDAO")
	private SampleDAO sampleDAO; //SampleDAO 선언해주고

	@Override  //implements로 상속 받았으니 @Override로 재 정의해줌(SampleService에서 정의한놈)
	public Map<String, Object> selectBoardList(Map<String, Object> map) throws Exception {
		
		return sampleDAO.selectBoardList(map);
	}
	
	
	@Override  //implements로 상속 받았으니 @Override로 재 정의해줌(SampleService에서 정의한놈)
	public Map<String, Object> selectBoardList1(Map<String, Object> map) throws Exception {
		
		return sampleDAO.selectBoardList(map);
	}
	
	
	
	@Override
	public void insertBoard(Map<String, Object> map, HttpServletRequest request) throws Exception 
	{
		sampleDAO.insertBoard(map);
		
		List<Map<String, Object>> list = fileUtils.parseInsertFileInfo(map, request);
		for(int i=0,size=list.size();i<size;i++)
		{
			sampleDAO.insertFile(list.get(i));
		}
		
		MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
		Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
		MultipartFile multipartFile = null;
		
		while(iterator.hasNext())
		{
			multipartFile = multipartHttpServletRequest.getFile(iterator.next());
			
			if(multipartFile.isEmpty() == false)
			{
				log.debug("--------------file start------------");
				log.debug("name :" + multipartFile.getName());
				log.debug("filename :" + multipartFile.getOriginalFilename());
				log.debug("size :" + multipartFile.getSize());
				log.debug("--------------file end--------------\n");
			}
		}
	}
	
	@Override
	public Map<String, Object> selectBoardDetail(Map<String, Object> map) throws Exception 
	{
		sampleDAO.updateHitCnt(map);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> tempMap = sampleDAO.selectBoardDetail(map);
		resultMap.put("map", tempMap);
		
		List<Map<String, Object>> list = sampleDAO.selectFileList(map);
		resultMap.put("list", list);
		
		return resultMap;
	}
	
	@Override
	public Map<String, Object> boardUpdate(Map<String, Object> map) throws Exception
	{
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> tempMap = sampleDAO.boardUpdate(map);
		resultMap.put("map", tempMap);
		
		List<Map<String, Object>> list = sampleDAO.selectFileList(map);
		resultMap.put("list", list);
		
		return resultMap;
	}
	
	@Override
	public void updateBoard(Map<String, Object> map, HttpServletRequest request) throws Exception
	{
		sampleDAO.updateBoard(map);  //기존 글만 수정(업데이트)
		
		sampleDAO.deleteFileList(map);  // (DEL_GB = 'Y') 로 처리하는 부분
		List<Map<String, Object>> list = fileUtils.parseInsertFileInfo(map, request);
		Map<String, Object> tempMap = null;
		for(int i=0,size=list.size();i<size;i++)
		{
			tempMap = list.get(i);
			if(tempMap.get("IS_NEW").equals("Y"))
			{
				sampleDAO.insertFile(tempMap);
			}else{
				sampleDAO.updateFile(tempMap);
			}
		}
	}

	@Override
	public void deleteBoard(Map<String, Object> map) throws Exception {
		
		sampleDAO.deleteBoard(map);
		
	}
}
