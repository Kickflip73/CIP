package com.uchain.cip.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.uchain.cip.pojo.Comment;
import com.uchain.cip.service.CommentService;
import com.uchain.cip.dao.CommentMapper;
import org.springframework.stereotype.Service;

/**
* @author 30652
* @description 针对表【comment】的数据库操作Service实现
* @createDate 2023-03-16 10:24:52
*/
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
    implements CommentService{

}




