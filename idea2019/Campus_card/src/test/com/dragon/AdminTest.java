package com.dragon;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dragon.mapper.AccountMapper;
import com.dragon.mapper.BankMapper;
import com.dragon.mapper.BillMapper;
import com.dragon.mapper.UserMapper;
import com.dragon.model.Account;
import com.dragon.model.Admin;
import com.dragon.model.BankCard;
import com.dragon.model.Bill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author tdragon.
 * @Date 2021/5/30.
 * @Time 17:38
 * @Description:
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminTest {

    @Autowired
    private UserMapper userMapper;

    @Resource
    AccountMapper accountMapper;

    @Resource
    BillMapper billMapper;

    @Resource
    BankMapper bankMapper;
    @Test
    public void testSelectOne() {
        Admin admin = userMapper.selectById(1);
        System.out.println("accountMapper.selectById(8) = " + accountMapper.selectById(8));
        System.out.println(admin);
        QueryWrapper<Bill> billQueryWrapper = new QueryWrapper<>();
        billQueryWrapper.select("*").eq("pay_income",1);
        List<Bill> bills = billMapper.selectList(billQueryWrapper);
        bills.forEach(System.out::println);
    }

    @Test
    public void deletebyid(){
//        Map<String,Object> conditionsMap=new HashMap<>();
//        conditionsMap.put("id",3);
//        int i = accountMapper.deleteByMap(conditionsMap);
        int i = accountMapper.deleteById(3);
        System.out.println("删除成功结果  " + i);
    }
    @Test
    public void testallBill(){
        final List<Bill> bills = billMapper.selectList(new QueryWrapper<Bill>().select("*"));
        bills.forEach(System.out::println);
    }


    @Test
    public void testBank(){
        List<BankCard> bills = bankMapper.selectList(new QueryWrapper<BankCard>().select("*"));
        bills.forEach(System.out::println);
    }

    @Test
    public void testByDate() throws ParseException {
        String start="2021-05-10 12:32:31";
        String end="2021-05-32 12:32:31";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date o = simpleDateFormat.parse(start);
        System.out.println("o = " + o);
        Date n = simpleDateFormat.parse(end);
        System.out.println("n = " + n);
        QueryWrapper<Bill> billQueryWrapper = new QueryWrapper<>();
        billQueryWrapper.select("*")
//                左闭右开
                .between("create_date",o,n);
//                .ge("create_date",o);
//               billQueryWrapper.le("create_date",n);
//        .between()
        List<Bill> bills = billMapper.selectList(billQueryWrapper);
        bills.forEach(System.out::println);

        QueryWrapper<Bill> pay_income = billQueryWrapper.select("*")
                .eq("pay_income", 1);
        billMapper.selectList(pay_income)
                .forEach(System.out::println);
    }

    @Test
    public void testBillByPage(){
        Page<Bill> page = new Page<>(2, 2);
        IPage<Bill> billIPage = billMapper.selectPage(page, new QueryWrapper<Bill>());
        System.out.println("总条数 ------> " + billIPage.getTotal());
        System.out.println("当前页数 ------> " + billIPage.getCurrent());
        System.out.println("当前每页显示数 ------> " + billIPage.getSize());
        System.out.println(billIPage.getRecords());
    }
}
