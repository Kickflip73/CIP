package com.uchain.cip.service.impl;

import com.uchain.cip.enums.ResultEnum;
import com.uchain.cip.mapper.ResourceMapper;
import com.uchain.cip.pojo.Resource;
import com.uchain.cip.service.ResourceService;
import com.uchain.cip.tools.ResourceCondition;
import com.uchain.cip.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author 30652
* @description 针对表【resource】的数据库操作Service实现
* @createDate 2023-03-21 18:37:40
*/
@Service
public class ResourceServiceImpl implements ResourceService {
    @Autowired
    ResourceMapper resourceMapper;

    /**
     * 依据id获取单个资源帖子sbsbsbsbsbsbbsbsbsbnsbsbsbbsssbbbssssbbbbsbsbsbsbssbbsbssbssbbsbsb
     * */
    @Override
    public ResultVO getResourceById(long id) {
        //依据id查询数据
        Resource resource = resourceMapper.selectById(id);

        if (resource != null) {
            //浏览量+1
            resourceMapper.addViews(id);
            return new ResultVO(ResultEnum.RESOURCE_DATA_QUERY_SUCCESS.getCode(), ResultEnum.RESOURCE_DATA_QUERY_SUCCESS.getMessage(), resource);
        } else {
            return new ResultVO(ResultEnum.RESOURCE_NOT_EXIST.getCode(), ResultEnum.RESOURCE_NOT_EXIST.getMessage(), null);
        }
    }

    @Override
    public ResultVO getResourcePage(int pageIndex, int pageSize, ResourceCondition condition) {
        return null;
    }

    @Override
    public ResultVO saveResource(Resource resource) {
        return null;
    }

    @Override
    public ResultVO deleteResourceById(long id) {
        return null;
    }

    @Override
    public ResultVO updateResourceById(Resource resource) {
        return null;
    }
}
