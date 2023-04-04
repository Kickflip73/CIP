package com.uchain.cip.service;

import com.uchain.cip.pojo.Resource;
import com.uchain.cip.pojo.User;
import com.uchain.cip.tools.ResourceCondition;
import com.uchain.cip.vo.ResultVO;

/**
* @author 30652
* @description 针对表【resource】的数据库操作Service
* @createDate 2023-03-21 18:37:40
*/
public interface ResourceService {
    ResultVO getResourceById(long id);

    public ResultVO getResourcePage(int pageIndex, int pageSize, ResourceCondition condition);

    ResultVO saveResource(Resource resource);

    ResultVO deleteResourceById(long id);

    ResultVO updateResourceById(Resource resource);

    ResultVO getMyResources(long userId);

    ResultVO recommend(long userId);
}
