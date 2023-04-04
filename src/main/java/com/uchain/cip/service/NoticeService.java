package com.uchain.cip.service;

import com.uchain.cip.pojo.Notice;
import com.baomidou.mybatisplus.extension.service.IService;
import com.uchain.cip.vo.ResultVO;

/**
* @author 30652
* @description 针对表【notice】的数据库操作Service
* @createDate 2023-04-04 21:05:54
*/
public interface NoticeService {
    public ResultVO publishNotice(Notice notice);

    public ResultVO getPublishedNotices();

    public ResultVO getAllNotices();

    public ResultVO updateById(Notice notice);

    public ResultVO deleteNoticerById(long id);

    public void addViews(long id);
}
