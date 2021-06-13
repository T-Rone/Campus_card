package com.dragon.contoller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dragon.mapper.BillMapper;
import com.dragon.model.Account;
import com.dragon.model.Bill;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Author tdragon.
 * @Date 2021/5/31.
 * @Time 12:44
 * @Description:
 */
@RestController
public class BillController {
    @Resource
    BillMapper billMapper;

    QueryWrapper<Bill> billQueryWrapper = new QueryWrapper<>();

    @GetMapping("/getAllBill")
    public List<Bill> getAllBill() {
        QueryWrapper<Bill> wrapper = new QueryWrapper<>();
        wrapper.select("*");
        return billMapper.selectList(wrapper);
    }

    @GetMapping("/getOneBill")
    public Bill getOneBill(String id) {
//        billQueryWrapper.select("*")
//                .eq("id", id);
//        return billMapper.selectOne(billQueryWrapper);
        return billMapper.selectById(Integer.parseInt(id));
    }

    @GetMapping("/getBillByDate")
    public List<Bill> getBillByDate(String start,String end) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date start_time = simpleDateFormat.parse(start);
        Date end_time = simpleDateFormat.parse(end);
        System.out.println("start = " + start);
        billQueryWrapper.select("*")
            .between("create_date",start_time,end_time);
        System.out.println("end = " + end);
        List<Bill> bills = billMapper.selectList(billQueryWrapper);
        bills.forEach(System.out::println);
        return bills;
    }

    @GetMapping("/getBillByPay")
    public List<Bill> getBillByPay(){
        QueryWrapper<Bill> payIncome = billQueryWrapper.select("*")
                .eq("pay_income", 1);
         return   billMapper.selectList(payIncome);
    }

    @GetMapping("/getAllBillByPage")
    public List<Bill>  getAllBillByPage(String current,String size){
        if("undefined".equals(current)&& "undefined".equals(size))
        {
            current="1";
            size="2";
        }
        Page<Bill> page = new Page<>(Integer.parseInt(current), Integer.parseInt(size));
        IPage<Bill> billIPage = billMapper.selectPage(page, new QueryWrapper<>());
        return  billIPage.getRecords();
    }

    @PostMapping("/updateBillById")
    public Bill updateBillById(String id, @RequestBody Bill bill){
        Bill old = billMapper.selectById(Integer.parseInt(id));
        System.out.println("旧账单：");
        System.out.println(old);
        System.out.println("新改的账单：\n"+bill);
        billMapper.updateById(bill);
        return bill;
    }

    @PostMapping("/addBill")
    public List<Bill> addAccount(@RequestBody Bill bill){
        bill.setId(0);
        System.out.println("新添加账单：\n"+bill);
        billMapper.insert(bill);
        return getAllBill();
    }

    @DeleteMapping("/deleteBillById")
    public List<Bill> deleteById(String id) {
        if (id != null) {
            billMapper.deleteById(Integer.parseInt(id));
        }
        return getAllBill();
    }

}
