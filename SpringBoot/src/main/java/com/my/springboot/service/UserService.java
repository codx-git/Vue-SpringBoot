package com.my.springboot.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.my.springboot.entity.User;
import com.my.springboot.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class UserService extends ServiceImpl<UserMapper,User> {

//    @Autowired
//    private UserMapper userMapper;
//
//    public List<User> getUser(){
//        return userMapper.findAll();
//    }

//    public int save(User user){
//        if(user.getId() == null){ //无用户则新增
//            return userMapper.insert(user);
//        }else {     //有用户则更新
//            return userMapper.update(user);
//        }
//    }
    public boolean saveUser(User user){
//        if(user.getId() == null){
//            return save(user);
//        } else {
//            return updateById(user);
//        }
        return  saveOrUpdate(user);
    }

//    public int detele(Integer id){
//        return userMapper.deleteById(id);
//    }
//
//    public Map<String, Object> getUserPage(Integer pageNum, Integer pageSize) {
//        pageNum = (pageNum -1) * pageSize;
//        List<User> data = userMapper.selectPage(pageNum,pageSize);
//        List<User> userList = userMapper.findAll();
//        Integer total = userList.size();
//        Map<String, Object> res = new HashMap<>();
//        res.put("data",data);
//        res.put("total",total);
//        return res;
//    }
}
