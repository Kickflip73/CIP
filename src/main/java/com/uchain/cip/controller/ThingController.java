package com.uchain.cip.controller;

import com.uchain.cip.pojo.Thing;
import com.uchain.cip.service.Thing.ThingService;
import com.uchain.cip.util.ThingCondition;
import com.uchain.cip.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/things")
public class ThingController {

    @Autowired
    ThingService thingService;
    //查询
    @GetMapping("/getPageThing")
    public ResultVO getPageThing(@RequestBody ThingCondition condition) {
        return thingService.getPageThingByCondition(condition);
    }
    //增加
    @GetMapping("/addThing")
    public ResultVO addPageThing(Thing thing ){
        return thingService.addPageThing(thing);
    }
}
