package com.uchain.cip.controller;

import com.uchain.cip.pojo.Thing;
import com.uchain.cip.service.Thing.ThingService;
import com.uchain.cip.tools.ThingCondition;
import com.uchain.cip.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/things")
public class ThingController {

    @Autowired
    ThingService thingService;

    /**
     * 查询
     * */
    @GetMapping("/getPageThing")
    public ResultVO getPageThing(@RequestBody ThingCondition condition) {
        return thingService.getPageThingByCondition(condition);
    }

    /**
     * 增加
     * */
    @GetMapping("/addThing")
    public ResultVO addPageThing(Thing thing ){
        return thingService.addPageThing(thing);
    }

    /**
     * 删除 ：根据文章id进行删除
     * */
    @GetMapping("/deletePageThing")
    public ResultVO deletePageThing(Integer id){
        return thingService.deletePageThing(id);
    }
    /*
    * 修改：
    * 1修改文章内容
    * 2修改文章悬赏金额
    * */
    /*
    * 1修改文章内容
    * */
    @GetMapping("/updateContent")
    ResultVO updateContent(Integer id,String content){
        return thingService.updateContent(id,content);
    }
    /*修改悬赏金额*/
    @GetMapping("/updateMoney")
    ResultVO updateMoney(Integer id,Integer money){
        return thingService.updateMoney(id,money);
    }
    /*修改帖子标题*/
    @GetMapping("/updateTitle")
    ResultVO updateTitle(Integer id,String title){
        return thingService.updateTitle(id,title);
    }


}
