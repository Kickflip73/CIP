package com.uchain.cip.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.uchain.cip.pojo.Image;
import com.uchain.cip.service.ImageService;
import com.uchain.cip.dao.ImageMapper;
import org.springframework.stereotype.Service;

/**
* @author 30652
* @description 针对表【image】的数据库操作Service实现
* @createDate 2023-03-16 10:24:52
*/
@Service
public class ImageServiceImpl extends ServiceImpl<ImageMapper, Image>
    implements ImageService{

}




