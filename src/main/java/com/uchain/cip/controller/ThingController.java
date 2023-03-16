package com.uchain.cip.controller;

import com.uchain.cip.service.ThingService;
import com.uchain.cip.util.ThingCondition;
import com.uchain.cip.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/things")
public class ThingController {

    @Autowired
    ThingService thingService;

    @GetMapping
    public ResultVO getPageThing(@RequestBody ThingCondition condition) {
        return thingService.getPageThingByCondition(condition);
    }
}
