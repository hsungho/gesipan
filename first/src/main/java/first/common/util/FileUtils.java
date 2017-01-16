package first.common.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Component("fileUtils")
public class FileUtils 
{
	private static final String filePath = "c:\\dev\\file\\"; //파일 저장위치 선언
	
	public List<Map<String, Object>> parseInsertFileInfo(Map<String, Object> map, HttpServletRequest request) throws Exception
	{
		MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
		Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
		
		MultipartFile multipartFile = null;
		String originalFileName = null;
		String originalFileExtension = null;
		String storedFileName = null;
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> listMap = null;
		
		String boardIdx = (String)map.get("IDX"); //게시글 번호 받아오는 부분
		String requestName = null;
		String idx = null;
		
		/*File file = new File(filePath);
		if(file.exists() == false)
		{
			file.mkdirs();
		}  //경로 없으면 만들어줘라
*/		
		while(iterator.hasNext())
		{
			multipartFile = multipartHttpServletRequest.getFile(iterator.next());
			if(multipartFile.isEmpty() == false)
			{
				originalFileName = multipartFile.getOriginalFilename();
				originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf(".")); //확장자명을 짤라서 저장하고
				storedFileName = CommonUtils.getRandomString() + originalFileExtension; //파일이름 랜덤하고 돌리고 짤랐던거 확장자 붙이고,
				
				/*file = new File(filePath + storedFileName);
				multipartFile.transferTo(file);*/
				
				multipartFile.transferTo(new File(filePath + storedFileName));
				
				listMap = new HashMap<String, Object>();
				/*listMap.put("BOARD_IDX", boardIdx);
				listMap.put("ORIGINAL_FILE_NAME", originalFileName);
				listMap.put("STORED_FILE_NAME", storedFileName);
				listMap.put("FILE_SIZE", multipartFile.getSize());*/
				
				listMap.put("IS_NEW", "Y");
				listMap.put("BOARD_IDX", boardIdx);
				listMap.put("ORIGINAL_FILE_NAME", originalFileName);
				listMap.put("STORED_FILE_NAME", storedFileName);
				listMap.put("FILE_SIZE", multipartFile.getSize());
				list.add(listMap);		
			}else{
				requestName = multipartFile.getName();
				idx = "IDX_" + requestName.substring(requestName.indexOf("_")+1);
				if(map.containsKey(idx)==true&&map.get(idx)!=null)
				{
					listMap = new HashMap<String,Object>();
					listMap.put("IS_NEW", "N");
					listMap.put("FILE_IDX", map.get(idx));
					list.add(listMap);
				}
			}
		}
		
		return list;
	}
}
