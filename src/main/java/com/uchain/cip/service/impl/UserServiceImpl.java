package com.uchain.cip.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.uchain.cip.enums.ResultEnum;
import com.uchain.cip.mapper.UserMapper;
import com.uchain.cip.pojo.User;
import com.uchain.cip.service.UserService;
import com.uchain.cip.tools.EmailUtil;
import com.uchain.cip.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.config.annotation.RedirectViewControllerRegistration;

import java.util.*;

/**
* @author 30652
* @description 针对表【user】的数据库操作Service实现
* @createDate 2023-03-16 10:21:50
*/
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Autowired
    EmailUtil emailUtil;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Override
    public ResultVO  upDatepasswordById(int id, String newPassword,String password) {
        User user = userMapper.selectById(id);
        if(user==null)return new ResultVO(0,"未查询到用户信息",null);
        if(user.getPassword().equals(password)){
            if(userMapper.upDatepasswordById(id,newPassword)>0){
                return new ResultVO(1,"密码修改成功",null);
            }else{
                return  new ResultVO(2,"服务器故障，请重试",null);
            }

        }
        else{
            return  new ResultVO(0,"原密码错误",null);
        }
    }

    /**
     * 依据id查询单个用户
     * */
    @Override
    public ResultVO getUserById(long id) {
        User user = userMapper.selectById(id);

        if (user != null) {
            //查询成功
            return new ResultVO(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), user);
        } else {
            //查询失败
            return new ResultVO(ResultEnum.LOGIN_FAIL.getCode(), ResultEnum.LOGIN_FAIL.getMessage(), null);
        }
    }

    /**
     * 查询所有用户
     * */
    @Override
    public ResultVO getAllUser() {
        List<User> users = userMapper.selectList(null);

        if (users != null) {
            //查询成功
            return new ResultVO(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), users);
        } else {
            //查询失败
            return new ResultVO(ResultEnum.FAIL.getCode(), ResultEnum.FAIL.getMessage(), null);
        }
    }

    /**
     * 邮箱验证
     * */
    @Override
    public ResultVO verifyEmail(String email, String verifyCode) {
        if (verifyCode != null && verifyCode.length() == 4) {
            //有验证码，验证验证码是否正确，从redis里依据邮箱地址取验证码
            if (Objects.equals(verifyCode, stringRedisTemplate.opsForValue().get(email))) {
                //验证码正确
                return new ResultVO(ResultEnum.VERIFY_CODE_SUCCESS.getCode(), ResultEnum.VERIFY_CODE_SUCCESS.getMessage(), null);
            } else {
                //验证码错误
                return new ResultVO(ResultEnum.VERIFY_CODE_ERROR.getCode(), ResultEnum.VERIFY_CODE_ERROR.getMessage(), null);
            }
        } else {
            //无验证码，发送验证邮箱
            //检测邮箱是否已被注册
            LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(User::getEmail, email);
            Long count = userMapper.selectCount(wrapper);
            if (count > 0) {
                //邮箱已被注册
                return new ResultVO(ResultEnum.EMAIL_ALREADY_EXISTS.getCode(), ResultEnum.EMAIL_ALREADY_EXISTS.getMessage(), null);
            }

            //发送验证码邮件
            try {
                //生成四位数随机验证码
                String sendVerifyCode = String.format("%04d", new Random().nextInt(9999 - 1000 + 1) + 1000);
                String text = "您正在注册智慧校园互助平台账户\n\t验证码：" + sendVerifyCode + "\n若非本人操作，请忽略此条信息~";
                emailUtil.sendSimpleMailMessage(email, "智慧校园互助平台", text);
                //将邮箱和验证码放入redis
                stringRedisTemplate.opsForValue().set(email, sendVerifyCode);
            } catch (Exception e) {
                //发送失败
                return new ResultVO(ResultEnum.EMAIL_SEN_FAIL.getCode(), ResultEnum.EMAIL_SEN_FAIL.getMessage(), null);
            }

            //发送成功
            return new ResultVO(ResultEnum.EMAIL_SEN_SUCCESS.getCode(), ResultEnum.EMAIL_SEN_SUCCESS.getMessage(), null);
        }
    }

    /**
     * 注册账户
     * */
    @Override
    public ResultVO register(User user) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getNickName, user.getNickName());
        Long count = userMapper.selectCount(wrapper);
        if (count > 0) {
            //昵称已存在
            return new ResultVO(ResultEnum.NICKNAME_ALREADY_EXISTS.getCode(), ResultEnum.NICKNAME_ALREADY_EXISTS.getMessage(), null);
        }

        //插入数据库
        int insert = 0;
        try {
            insert = userMapper.insert(user);
        } catch (Exception e) {
            //插入出现异常
            return new ResultVO(ResultEnum.UNKNOWN_ERROR.getCode(), ResultEnum.UNKNOWN_ERROR.getMessage(), null);
        }

        if (insert == 1) {
            //插入成功
            return new ResultVO(ResultEnum.REGISTER_SUCCESS.getCode(), ResultEnum.REGISTER_SUCCESS.getMessage(), user);
        } else {
            //插入失败
            return new ResultVO(ResultEnum.REGISTER_FAIL.getCode(), ResultEnum.REGISTER_FAIL.getMessage(), null);
        }
    }

    /**
     * 根据id修改用户信息
     * */
    @Override
    public ResultVO updateById(User user) {
        //id为空
        if (user.getId() == null) {
            //返回用户不存在
            return new ResultVO(ResultEnum.USER_NOT_EXIST.getCode(), ResultEnum.USER_NOT_EXIST.getMessage(), null);
        }

        //判断是否有对应id的用户存在
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getId, user.getId());
        Long count = userMapper.selectCount(wrapper);
        if (count != 1) {
            //返回用户不存在
            return new ResultVO(ResultEnum.USER_NOT_EXIST.getCode(), ResultEnum.USER_NOT_EXIST.getMessage(), null);
        }

        //修改用户，返回影响行数
        int result = userMapper.updateById(user);

        if (result == 1) {
            //修改成功
            return new ResultVO(ResultEnum.UPDATE_USER_SUCCESS.getCode(), ResultEnum.UPDATE_USER_SUCCESS.getMessage(), user);
        } else {
            //修改失败
            return new ResultVO(ResultEnum.UPDATE_USER_FAIL.getCode(), ResultEnum.UPDATE_USER_FAIL.getMessage(), user);
        }
    }

    /**
     * 依据id删除用户
     * */
    @Override
    public ResultVO deleteById(long id) {
        //判断是否有对应id的用户存在
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getId, id);
        Long count = userMapper.selectCount(wrapper);
        if (count != 1) {
            //返回用户不存在
            return new ResultVO(ResultEnum.USER_NOT_EXIST.getCode(), ResultEnum.USER_NOT_EXIST.getMessage(), null);
        }

        //删除用户
        int result = userMapper.deleteById(id);
        if (result == 1) {
            //删除成功
            return new ResultVO(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), null);
        } else {
            //删除失败
            return new ResultVO(ResultEnum.FAIL.getCode(), ResultEnum.FAIL.getMessage(), null);
        }
    }

    /**
     * 用户登录
     * */
    @Override
    public ResultVO login(String nickNameOrEmail, String password) {
        //依据昵称和邮箱查询用户
        User user = userMapper.getUserByNickNameOrEmail(nickNameOrEmail);

        if (user != null && Objects.equals(user.getPassword(), password)) {
            //登陆成功
            return new ResultVO(ResultEnum.LOGIN_SUCCESS.getCode(), ResultEnum.LOGIN_SUCCESS.getMessage(), user);
        } else {
            //登陆失败
            return new ResultVO(ResultEnum.LOGIN_FAIL.getCode(), ResultEnum.LOGIN_FAIL.getMessage(), null);
        }
    }

}
