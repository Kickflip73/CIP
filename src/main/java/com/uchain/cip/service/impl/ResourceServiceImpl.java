package com.uchain.cip.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.uchain.cip.pojo.Resource;
import com.uchain.cip.service.ResourceService;
import com.uchain.cip.mapper.ResourceMapper;
import org.springframework.stereotype.Service;

/**
* @author 30652
* @description 针对表【resource】的数据库操作Service实现
* @createDate 2023-03-21 18:37:40
*/
@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource>
    implements ResourceService{

}




