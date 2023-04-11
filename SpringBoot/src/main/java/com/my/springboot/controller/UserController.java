package com.my.springboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.my.springboot.entity.User;
import com.my.springboot.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "系统用户接口", description = "系统用户接口")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public boolean save(@RequestBody User user){
        return userService.saveUser(user);
    }
    @GetMapping
    public List<User> index(){
        return userService.list();
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Integer id){
        return userService.removeById(id);
    }

    //批量删除
    @PostMapping("/del/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids){
        return userService.removeByIds(ids);
    }
    /*
    function:分页查询
    接口路径：/user/page?pageNum=1&pageSize=5
    limit第一个参数 = (pageNum - 1) * pageSize
    目前代码格式如果添加新的参数，则对应数据库查询也需要更改
    * */
//    @GetMapping("/page")
//    public Map<String, Object> findPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize){
//        return userService.getUserPage(pageNum,pageSize);
//    }

    //使用Mybaits-plus方法实现带多参数的分页查询
    @GetMapping("/page")
    public IPage<User> findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize,
                                @RequestParam(defaultValue = "") String username,
                                @RequestParam(defaultValue = "") String nickname,
                                @RequestParam(defaultValue = "") String email,
                                @RequestParam(defaultValue = "") String address) {
        IPage<User> page = new Page<>(pageNum, pageSize);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        if (!"".equals(username)) {
//            queryWrapper.like("username", username);
//        }
//        if (!"".equals(nickname)) {
//            queryWrapper.like("nickname", nickname);
//        }
//        if (!"".equals(email)) {
//            queryWrapper.like("email", email);
//        }
//        if (!"".equals(address)) {
//            queryWrapper.like("address", address);
//        }
        queryWrapper.like("username",username);
        queryWrapper.like("nickname", nickname);
        queryWrapper.like("email", email);
        queryWrapper.like("address", address);
        queryWrapper.orderByDesc("id");
        return userService.page(page, queryWrapper);
    }


}
