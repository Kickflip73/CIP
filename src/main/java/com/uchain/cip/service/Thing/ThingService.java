package com.uchain.cip.service.Thing;

import com.uchain.cip.pojo.Thing;
import com.baomidou.mybatisplus.extension.service.IService;
import com.uchain.cip.util.ThingCondition;
import com.uchain.cip.vo.ResultVO;
import org.springframework.web.bind.annotation.RequestBody;

/**
* @author 30652
* @description 针对表【thing】的数据库操作Service
* @createDate 2023-03-16 10:24:20
*/
public interface ThingService extends IService<Thing> {
    ResultVO getPageThingByCondition(ThingCondition condition);
    ResultVO addPageThing(@RequestBody Thing thing );
}
