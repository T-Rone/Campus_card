package com.dragon.contoller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.dragon.mapper.AccountMapper;
import com.dragon.mapper.BankMapper;
import com.dragon.model.Account;
import com.dragon.model.BankCard;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author tdragon.
 * @Date 2021/5/31.
 * @Time 12:25
 * @Description:
 */
@RestController
public class BankCardController {
    @Resource
    AccountMapper accountMapper;
    @Resource
    BankMapper bankMapper;

    QueryWrapper<BankCard> bankCardQueryWrapper = new QueryWrapper<>();
    QueryWrapper<Account> accountQueryWrapper = new QueryWrapper<>();

    @GetMapping("/getOneBankCard")
    public BankCard getOne(){
        bankCardQueryWrapper.select("*");
        BankCard bankCard = bankMapper.selectOne(bankCardQueryWrapper);
        System.out.println(bankCard);
        return bankCard;
    }

    @PostMapping("/addBalance")
    public Account addBalance(String recharge){
        UpdateWrapper<Account> accountUpdateWrapper = new UpdateWrapper<>();
        UpdateWrapper<BankCard> bankCardUpdateWrapper = new UpdateWrapper<>();
        BankCard one = getOne();
        String old=one.getBalance();
        double new_balance=Double.parseDouble(old)-Double.parseDouble(recharge);
        one.setBalance(String.valueOf(new_balance));
        bankMapper.update(one,bankCardUpdateWrapper);
        System.out.println(one);
       //不搞那么复杂 没人就一张校园卡 不管挂失之类
        final Account account = accountMapper.selectOne(accountQueryWrapper.select("*")
                .eq("stu_id","2005059042"));
        final String old_balance = account.getBalance();
        double nbalance=Double.parseDouble(old_balance)+Double.parseDouble(recharge);
        account.setBalance(String.valueOf(nbalance));
        accountMapper.update(account,accountUpdateWrapper);
        return  account;
    }


}
