package com.dragon.contoller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dragon.mapper.AccountMapper;
import com.dragon.mapper.UserMapper;
import com.dragon.model.Account;
import com.dragon.model.Admin;
import com.dragon.model.Bill;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Author tdragon.
 * @Date 2021/5/30.
 * @Time 17:37
 * @Description:
 */
@RestController
public class UserController {
    @Resource
    UserMapper userMapper;

    @Resource
    AccountMapper accountMapper;
//    @CrossOrigin
    @GetMapping("/login")
    public Object login(String username, String password, @RequestParam String radio, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        Account account = new Account();
        Admin admin = new Admin();
        account.setStuId(username);
        account.setStuPwd(password);
        Account account1 = accountMapper.selectOne(new QueryWrapper<Account>()
                .eq("stu_id",username)
                .eq("stu_pwd",password)
        );
        admin.setName(username);
        admin.setPassword(password);
        QueryWrapper<Admin> adminQueryWrapper = new QueryWrapper<>();
        adminQueryWrapper.eq("username",username)
                .eq("password",password);
        Admin admin1 = userMapper.selectOne(adminQueryWrapper);
        if(Integer.parseInt(radio)==1) {
            System.out.println("学生用户！");
            if (account1!=null)
            {
                System.out.println("数据库存在该学生用户，登录成功");
                System.out.println(account1);
            }
            else {
                System.out.println("该用户不存在");
            }
        } else {
            System.out.println("管理员用户登录! ");
            if(admin1!=null)
            {
                System.out.println("存在该管理员用户!登录成功");
                System.out.println(admin1);
            } else {
                System.out.println("该管理员用户不存在");
            }
        }
        return Integer.parseInt(radio)==1?account1:admin1;
    }

    /**
     *
     * @param new_pwd
     * @param stuId
     * @return  根据学号修改密码
     */
    @PostMapping("/edit_pwd")
    public Object edit_pwd(String new_pwd ,String stuId){
        QueryWrapper<Account> queryWrapper = new QueryWrapper<>();
        Account account = accountMapper.selectOne(queryWrapper.eq("stu_id", stuId));
        UpdateWrapper<Account> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("stu_pwd",new_pwd)
                     .eq("stu_id",stuId);
        int update = accountMapper.update(account, updateWrapper);
        System.out.println((update==1)?"修改成功":"修改失败");
        Account new_account = accountMapper.selectOne(queryWrapper.eq("stu_id", stuId));
        return  new_account;
    }

    @GetMapping("/main")
    public Object main(){
        Account account =new Account();
        return account;
    }

    @GetMapping("/getAllAccountByPage")
    public List<Account> getAllAccountByPage(String current,String size){
        Page<Account> page = new Page<>(Integer.parseInt(current), Integer.parseInt(size));
        IPage<Account> accountIPage = accountMapper.selectPage(page, new QueryWrapper<>());
        return  accountIPage.getRecords();
    }

    @GetMapping("/getAllAccount")
    public List<Account> getAllAccount(){
        QueryWrapper<Account> accountQueryWrapper = new QueryWrapper<>();
        accountQueryWrapper.select("*");
        return accountMapper.selectList(accountQueryWrapper);
    }

//    @DeleteMapping("/deleteAccountById")
    @DeleteMapping("/deleteAccountById")
    public List<Account> deleteById(String id) {
        if (id != null) {
            accountMapper.deleteById(Integer.parseInt(id));
        }
        return getAllAccount();
    }

    @GetMapping("/getAllAccountById")
    public Account getAllAccountById(String id){
        return   accountMapper.selectById(Integer.parseInt(id));
    }

    @PostMapping("/updateAccountById")
    public Account updateAccountById(String id, @RequestBody Account account){
         Account old = accountMapper.selectById(Integer.parseInt(id));
        System.out.println("旧用户：");
        System.out.println(old);
        System.out.println("新改的用户：\n"+account);
        accountMapper.updateById(account);
        return account;
    }

    @PostMapping("/addAccount")
    public List<Account> addAccount(@RequestBody Account account){
        account.setId(0);
        System.out.println("新添加用户：\n"+account);
        accountMapper.insert(account);
        return getAllAccount();
    }
}
