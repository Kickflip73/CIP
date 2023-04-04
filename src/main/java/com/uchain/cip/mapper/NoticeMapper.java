package com.uchain.cip.mapper;

import com.uchain.cip.pojo.Notice;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

/**
* @author 30652
* @description 针对表【notice】的数据库操作Mapper
* @createDate 2023-04-04 21:05:54
* @Entity com.uchain.cip.pojo.Notice
*/

@Mapper
public interface NoticeMapper extends BaseMapper<Notice> {
    void addViews(long id);
}
