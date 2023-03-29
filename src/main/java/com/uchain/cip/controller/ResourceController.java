package com.uchain.cip.controller;

import com.uchain.cip.pojo.Resource;
import com.uchain.cip.service.ResourceService;
import com.uchain.cip.tools.ResourceCondition;
import com.uchain.cip.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/resources")
@CrossOrigin
@Api(tags = "资源帖子接口")
public class ResourceController {
    @Autowired
    ResourceService resourceService;

    /**
     * 依据id获取单个资源帖子
     * */
    @GetMapping("/{id}")
    @ApiOperation(value = "获取单个资源帖子", notes = "依据id来获取单个资源帖子")
    public ResultVO getResourceById(@PathVariable long id) {
        return resourceService.getResourceById(id);
    }

    /**
     * 分页条件查询资源帖子
     * */
    @GetMapping("/{pageIndex}/{pageSize}")
    @ApiOperation(value = "分页条件查询资源帖子", notes = "依据传入的索引和页面大小以及筛选条件来查询一批资源帖子")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageIndex",
                    value = "当前页下标（从1开始）",
                    required = true,
                    dataType = "int",
                    paramType = "path"
            ),

            @ApiImplicitParam(name = "pageSize",
                    value = "页面大小，即每页展示的条目数",
                    required = true,
                    dataType = "int",
                    paramType = "path"
            )
    })
    public ResultVO getResourcePage(@PathVariable int pageIndex, @PathVariable int pageSize, @RequestBody ResourceCondition condition) {
        return resourceService.getResourcePage(pageIndex, pageSize, condition);
    }

    /**
     * 创建资源帖子
     * */
    @PostMapping
    @ApiOperation(value = "创建资源帖子", notes = "创建资源帖子")
    public ResultVO createResource(@RequestBody Resource resource) {
        return resourceService.saveResource(resource);
    }

    /**
     * 依据id删除帖子
     * */
    @DeleteMapping("/{id}")
    @ApiOperation(value = "依据id删除帖子", notes = "依据id删除帖子")
    public ResultVO deleteResourceById(@PathVariable long id) {
        return resourceService.deleteResourceById(id);
    }

    /**
     * 依据id修改帖子
     * */
    @PutMapping
    @ApiOperation(value = "依据id修改帖子", notes = "依据id修改帖子")
    public ResultVO updateResourceById(@RequestBody Resource resource) {
        return resourceService.updateResourceById(resource);
    }

}
