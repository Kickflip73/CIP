package com.uchain.cip.service.impl;

import com.uchain.cip.enums.ResultEnum;
import com.uchain.cip.mapper.StarMapper;
import com.uchain.cip.pojo.Star;
import com.uchain.cip.service.StarService;
import com.uchain.cip.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StarServiceImpl implements StarService {
    @Autowired
    StarMapper starmapper;
    @Override
    public ResultVO addThing(Star star) {
        if(starmapper.addThing(star)>0){
            return  new ResultVO(ResultEnum.STAR_SUCCESS.getCode(),ResultEnum.STAR_SUCCESS.getMessage(),null);
        }else{
            return  new ResultVO(ResultEnum.STAR_FAIL.getCode(),ResultEnum.STAR_FAIL.getMessage(),null);
        }
    }
}
