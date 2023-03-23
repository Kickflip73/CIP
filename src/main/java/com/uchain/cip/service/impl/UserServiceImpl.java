package com.uchain.cip.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.uchain.cip.enums.ResultEnum;
import com.uchain.cip.mapper.UserMapper;
import com.uchain.cip.pojo.User;
import com.uchain.cip.service.UserService;
import com.uchain.cip.tools.EmailUtil;
import com.uchain.cip.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    private final Map<String, String> verifyCodeMap = new HashMap<>();

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
     * 注册账户（检测邮箱是否重合，发送验证码，验证验证码）
     * */
    @Override
    public ResultVO register(User user, String verifyCode) {
        //未输入验证码
        if (verifyCode == null || Objects.equals(verifyCode, "null") || verifyCode.length() != 4) {
            //检测邮箱是否已被注册
            LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(User::getEmail, user.getEmail());
            Long count = userMapper.selectCount(wrapper);
            if (count > 0) {
                //邮箱已被注册
                return new ResultVO(ResultEnum.EMAIL_ALREADY_EXISTS.getCode(), ResultEnum.EMAIL_ALREADY_EXISTS.getMessage(), null);
            }

            //发送验证码邮件
            try {
                //生成四位数随机验证码
                String sendVerifyCode = String.format("%04d", new Random().nextInt(9999 - 1000 + 1) + 1000);
                String text = "您正在注册智慧校园互助平台账户\n昵称：" + user.getNickName() + "\n\t验证码：" + sendVerifyCode + "\n若非本人操作，请忽略此条信息~";
                emailUtil.sendSimpleMailMessage(user.getEmail(), "智慧校园互助平台", text);
                //将验证码和邮箱放入map
                verifyCodeMap.put(sendVerifyCode, user.getEmail());
            } catch (Exception e) {
                //发送失败
                return new ResultVO(ResultEnum.EMAIL_SEN_FAIL.getCode(), ResultEnum.EMAIL_SEN_FAIL.getMessage(), null);
            }

            //发送成功
            return new ResultVO(ResultEnum.EMAIL_SEN_SUCCESS.getCode(), ResultEnum.EMAIL_SEN_SUCCESS.getMessage(), null);
        } else {
            //有验证码
            String email = verifyCodeMap.get(verifyCode);
            if (email != null && Objects.equals(email, user.getEmail())) {
                //注册成功
                verifyCodeMap.remove(verifyCode);
                return saveUser(user);
            } else {
                //验证码错误
                return new ResultVO(ResultEnum.VERIFY_CODE_ERROR.getCode(), ResultEnum.VERIFY_CODE_ERROR.getMessage(), null);
            }
        }
    }

    /**
     * 插入用户到数据库
     * */
    @Override
    public ResultVO saveUser(User user) {
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
