package com.uchain.cip.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.uchain.cip.enums.ResultEnum;
import com.uchain.cip.pojo.Notice;
import com.uchain.cip.service.NoticeService;
import com.uchain.cip.mapper.NoticeMapper;
import com.uchain.cip.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
* @author 30652
* @description 针对表【notice】的数据库操作Service实现
* @createDate 2023-04-04 21:05:54
*/
@Service
public class NoticeServiceImpl implements NoticeService {
    @Autowired
    NoticeMapper noticeMapper;

    /**
     * 发布公告
     * */
    @Override
    public ResultVO publishNotice(Notice notice) {
        //若无发布日期，则设置发布日期为当前
        if ((notice.getPublishDateTime() == null)) {
            notice.setPublishDateTime(new Date());
        }

        int insert = noticeMapper.insert(notice);
        if (insert != 0) {
            return new ResultVO(ResultEnum.PUBLISH_NOTICE_SUCCESS.getCode(), ResultEnum.PUBLISH_NOTICE_SUCCESS.getMessage(), notice);
        } else {
            return new ResultVO(ResultEnum.PUBLISH_NOTICE_FAIL.getCode(), ResultEnum.PUBLISH_NOTICE_FAIL.getMessage(), null);
        }
    }

    /**
     * 获取所有已发布的公告
     * */
    @Override
    public ResultVO getPublishedNotices() {
        //获取当前日期后的公告信息
        LambdaQueryWrapper<Notice> wrapper = new LambdaQueryWrapper<>();
        wrapper.ge(Notice::getPublishDateTime, LocalDate.now());
        List<Notice> noticeList = noticeMapper.selectList(wrapper);

        if (noticeList != null) {
            //将所有查到的公告浏览量+1
            for (Notice notice : noticeList) {
                this.addViews(notice.getId());
                notice.setViews(notice.getViews() + 1);
            }
            return new ResultVO(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), noticeList);
        } else {
            return new ResultVO(ResultEnum.FAIL.getCode(), ResultEnum.FAIL.getMessage(), null);
        }
    }

    /**
     * 获取所有的公告（含未发布的）
     * */
    @Override
    public ResultVO getAllNotices() {
        List<Notice> noticeList = noticeMapper.selectList(null);

        if (noticeList != null) {
            return new ResultVO(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), noticeList);
        } else {
            return new ResultVO(ResultEnum.FAIL.getCode(), ResultEnum.FAIL.getMessage(), null);
        }
    }

    /**
     * 修改公告
     * */
    @Override
    public ResultVO updateById(Notice notice) {
        LambdaQueryWrapper<Notice> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Notice::getId, notice.getId());
        Long count = noticeMapper.selectCount(wrapper);
        if (count == 0) {
            //公告信息不存在
            return new ResultVO(ResultEnum.NOTICE_NOT_EXIT.getCode(), ResultEnum.NOTICE_NOT_EXIT.getMessage(), null);
        }

        int update = noticeMapper.updateById(notice);
        if (update == 1) {
            //修改成功
            return new ResultVO(ResultEnum.UPDATE_NOTICE_SUCCESS.getCode(), ResultEnum.UPDATE_NOTICE_SUCCESS.getMessage(), notice);
        } else {
            //修改失败
            return new ResultVO(ResultEnum.UPDATE_NOTICE_FAIL.getCode(), ResultEnum.UPDATE_NOTICE_FAIL.getMessage(), null);
        }
    }

    /**
     * 删除公告
     * */
    @Override
    public ResultVO deleteNoticerById(long id) {
        LambdaQueryWrapper<Notice> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Notice::getId, id);
        Long count = noticeMapper.selectCount(wrapper);
        if (count == 0) {
            //公告信息不存在
            return new ResultVO(ResultEnum.NOTICE_NOT_EXIT.getCode(), ResultEnum.NOTICE_NOT_EXIT.getMessage(), null);
        }

        int delete = noticeMapper.deleteById(id);
        if (delete == 1) {
            //删除成功
            return new ResultVO(ResultEnum.DELETE_NOTICE_SUCCESS.getCode(), ResultEnum.DELETE_NOTICE_SUCCESS.getMessage(), null);
        } else {
            //删除失败
            return new ResultVO(ResultEnum.DELETE_NOTICE_FAIL.getCode(), ResultEnum.DELETE_NOTICE_FAIL.getMessage(), null);
        }
    }

    /**
     * 浏览量+1
     * */
    @Override
    public void addViews(long id) {
        noticeMapper.addViews(id);
    }
}
