package com.uchain.cip.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.uchain.cip.enums.ResultEnum;
import com.uchain.cip.mapper.ResourceMapper;
import com.uchain.cip.pojo.Resource;
import com.uchain.cip.pojo.User;
import com.uchain.cip.service.ResourceService;
import com.uchain.cip.tools.ResourceCondition;
import com.uchain.cip.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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
     * 依据id获取单个资源帖子
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
            return new ResultVO(ResultEnum.THING_NOT_EXIST.getCode(), ResultEnum.THING_NOT_EXIST.getMessage(), null);
        }
    }

    /**
     * 依据条件分页查询资源帖子
     * */
    @Override
    public ResultVO getResourcePage(int pageIndex, int pageSize, ResourceCondition condition) {
        //设置分页
        Page<Resource> page = new Page<>(pageIndex, pageSize);
        //设置条件
        LambdaQueryWrapper<Resource> wrapper = new LambdaQueryWrapper<>();

        //匹配帖子类型
        wrapper.eq(condition.getPostType() == 1 || condition.getPostType() == 2, Resource::getPostType, condition.getPostType());

        if (condition.getSearchInfo() != null && condition.getSearchInfo().length() != 0) {
            wrapper.and(   //文本匹配，利用lambda表达式，提高条件优先级
                    i -> i.like(condition.getSearchInfo() != null, Resource::getResourceName, condition.getSearchInfo())
                            .or()
                            .like(condition.getSearchInfo() != null, Resource::getResourceType, condition.getSearchInfo())
                            .or()
                            .like(condition.getSearchInfo() != null, Resource::getDescription, condition.getSearchInfo())
            );
        }

        //设置排序规则
        if (condition.getOrderBy() == 1) {
            if (condition.getAscOrDesc() == 1) {
                //按创建时间升序
                wrapper.orderByAsc(Resource::getCreateDateTime);
            } else {
                //按创建时间降序
                wrapper.orderByDesc(Resource::getCreateDateTime);
            }
        } else  if (condition.getOrderBy() == 2) {
            if (condition.getAscOrDesc() == 1) {
                //按热度升序
                wrapper.orderByAsc(Resource::getHot);
            } else {
                //按热度降序
                wrapper.orderByDesc(Resource::getHot);
            }
        }

        //查询，得到页面数据
        Page<Resource> resultPage = resourceMapper.selectPage(page, wrapper);

        if (resultPage != null) {
            //将查询到的帖子浏览量都+1
            for (Resource resource : resultPage.getRecords()) {
                resourceMapper.addViews(resource.getId());
            }
            return new ResultVO(ResultEnum.RESOURCE_DATA_QUERY_SUCCESS.getCode(), ResultEnum.RESOURCE_DATA_QUERY_SUCCESS.getMessage(), resultPage);
        } else {
            return new ResultVO(ResultEnum.RESOURCE_DATA_QUERY_FAIL.getCode(), ResultEnum.RESOURCE_DATA_QUERY_FAIL.getMessage(), null);
        }
    }

    /**
     * 创建资源帖子
     * */
    @Override
    public ResultVO saveResource(Resource resource) {
        //设置创建时间
        resource.setCreateDateTime(new Date());

        //插入帖子，返回影响行数
        int count = resourceMapper.insert(resource);

        if (count == 1) {
            return new ResultVO(ResultEnum.CREATE_RESOURCE_SUCCESS.getCode(), ResultEnum.CREATE_RESOURCE_SUCCESS.getMessage(), resource);
        } else {
            return new ResultVO(ResultEnum.CREATE_RESOURCE_FAIL.getCode(), ResultEnum.CREATE_RESOURCE_FAIL.getMessage(), resource);
        }
    }

    /**
     * 依据id删除帖子
     * */
    @Override
    public ResultVO deleteResourceById(long id) {
        //条件查询是否存在有这个id的比赛帖子
        LambdaQueryWrapper<Resource> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Resource::getId, id);
        Long selectCount = resourceMapper.selectCount(wrapper);
        if (selectCount != 1) {
            //未查询到此id对应有帖子，返回
            return new ResultVO(ResultEnum.THING_NOT_EXIST.getCode(), ResultEnum.THING_NOT_EXIST.getMessage(), null);
        }

        //删除帖子，返回影响行数
        int count = resourceMapper.deleteById(id);

        if (count == 1) {
            return new ResultVO(ResultEnum.DELETE_RESOURCE_SUCCESS.getCode(), ResultEnum.DELETE_RESOURCE_SUCCESS.getMessage(), null);
        } else {
            return new ResultVO(ResultEnum.DELETE_RESOURCE_FAIL.getCode(), ResultEnum.DELETE_RESOURCE_FAIL.getMessage(), null);
        }
    }

    /**
     * 依据id修改帖子
     * */
    @Override
    public ResultVO updateResourceById(Resource resource) {
        //条件查询是否存在有这个id的比赛帖子
        LambdaQueryWrapper<Resource> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Resource::getId, resource.getId());
        Long selectCount = resourceMapper.selectCount(wrapper);
        if (selectCount != 1) {
            //未查询到此id对应有帖子，返回
            return new ResultVO(ResultEnum.THING_NOT_EXIST.getCode(), ResultEnum.THING_NOT_EXIST.getMessage(), null);
        }

        //修改帖子，返回影响行数
        int count = resourceMapper.updateById(resource);

        if (count == 1) {
            return new ResultVO(ResultEnum.UPDATE_RESOURCE_SUCCESS.getCode(), ResultEnum.UPDATE_RESOURCE_SUCCESS.getMessage(), resource);
        } else {
            return new ResultVO(ResultEnum.UPDATE_RESOURCE_FAIL.getCode(), ResultEnum.UPDATE_RESOURCE_FAIL.getMessage(), null);
        }
    }

    /**
     * 依旧id获取用户的所有帖子
     * */
    @Override
    public ResultVO getMyResources(long userId) {
        List<Resource> resourcesList = resourceMapper.selectResourcesByUserId(userId);

        if (resourcesList != null) {
            //将查询到的帖子浏览量都+1
            for (Resource resource : resourcesList) {
                resourceMapper.addViews(resource.getId());
            }
            return new ResultVO(ResultEnum.RESOURCE_DATA_QUERY_SUCCESS.getCode(), ResultEnum.RESOURCE_DATA_QUERY_SUCCESS.getMessage(), resourcesList);
        } else {
            return new ResultVO(ResultEnum.RESOURCE_DATA_QUERY_FAIL.getCode(), ResultEnum.RESOURCE_DATA_QUERY_FAIL.getMessage(), null);
        }
    }

    /**
     * 举报帖子
     * */
    @Override
    public ResultVO report(long userId, long thingId) {
        return null;
    }

    /**
     * 获取推荐帖子
     * */
    @Override
    public ResultVO recommend(int pageIndex, int pageSize, User user) {
        /**
         * 推荐算法
         * */

        /**
         * 临时顶凑的
         * */
        ResourceCondition resourceCondition = new ResourceCondition();
        resourceCondition.setSearchInfo(null);
        resourceCondition.setPostType(3);
        resourceCondition.setOrderBy(2);
        resourceCondition.setAscOrDesc(2);
        return this.getResourcePage(pageIndex, pageSize, resourceCondition);
    }
}
