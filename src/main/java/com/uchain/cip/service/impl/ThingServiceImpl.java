package com.uchain.cip.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.uchain.cip.enums.ResultEnum;
import com.uchain.cip.pojo.Thing;
import com.uchain.cip.service.ThingService;
import com.uchain.cip.dao.ThingMapper;
import com.uchain.cip.util.ThingCondition;
import com.uchain.cip.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author 30652
* @description 针对表【thing】的数据库操作Service实现
* @createDate 2023-03-16 10:24:20
*/
@Service
public class ThingServiceImpl extends ServiceImpl<ThingMapper, Thing>
    implements ThingService{
    @Autowired
    ThingMapper thingMapper;

    @Override
    public ResultVO getPageThingByCondition(ThingCondition condition) {
        Page<Thing> page = new Page<>(condition.getPageIndex(), condition.getPageSize());
        LambdaQueryWrapper<Thing> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(condition.getTitle() != null, Thing::getTitle, condition.getTitle())
                .eq(condition.getType() != 0,Thing::getType, condition.getType());

        if (condition.getOrderBy() == 0 && condition.getAscOrDesc() == 0) {
            wrapper.orderByAsc(Thing::getCreateDateTime);
        } else if (condition.getOrderBy() == 0 && condition.getAscOrDesc() == 1) {
            wrapper.orderByDesc(Thing::getCreateDateTime);
        } else if (condition.getOrderBy() == 1 && condition.getAscOrDesc() == 0) {
            wrapper.orderByAsc(Thing::getViews);
        } else if (condition.getOrderBy() == 1 && condition.getAscOrDesc() == 1) {
            wrapper.orderByDesc(Thing::getViews);
        } else if (condition.getOrderBy() == 2 && condition.getAscOrDesc() == 0) {
            wrapper.orderByAsc(Thing::getCommentsNum);
        } else if (condition.getOrderBy() == 2 && condition.getAscOrDesc() == 1) {
            wrapper.orderByDesc(Thing::getCommentsNum);
        }

        Page<Thing> thingPage = thingMapper.selectPage(page, wrapper);
        if (thingPage == null) {
            return new ResultVO(ResultEnum.FAIL.getCode(), "帖子查询失败", null);
        } else {
            return new ResultVO(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), thingPage.getRecords());
        }
    }
}
