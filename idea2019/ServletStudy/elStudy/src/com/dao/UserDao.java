package com.dao;

import com.bean.User;
import com.util.JdbcUtil;
import com.util.PageUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
//import com.util.dBConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 * @author tdragon
 * @Description: 新增dbutils工具类 通过druid连接池获取链接对象 创建queryRunner对象去操作数据
 */
public class UserDao {

    //使用jdbc查询User表的所有数据
    public List<User> showUsers() {
        try {
            //1.加载驱动，创建连接
//            Class.forName("com.mysql.jdbc.Driver");
            //2.构建连接对象
//            Connection con = DriverManager.getConnection("jdbc:mysql:///requiretest","root","root");
            //3.获取执行sql的pstm对象

            QueryRunner qr=new QueryRunner(JdbcUtil.getDataSource());
            return  qr.query("select * from user", new BeanListHandler<>(User.class));
//            PreparedStatement pstm = JdbcUtil.getConnection().prepareStatement("select * from user");
//            //4.执行sql
//            ResultSet rs = pstm.executeQuery();
//            //5.解析rs
//            ArrayList<User> Useres = new ArrayList<>();
//            while (rs.next()){
//                int id = rs.getInt("id");
//                String name = rs.getString("username");
//                String psw = rs.getString("password");
//                //根据表值创建User对象
//                User user = new User(id, name, psw);
//                Useres.add(user);
//                //将每次循环创建的User存进list集合
//            }
//            return Useres;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;//如果出现异常，返回null
    }

    public User selectOneUser(int id){
        User user=new User();
        user.setId(id);
        try {
            String sql="select  * from user where id=?";
            PreparedStatement ps= JdbcUtil.getConnection().prepareStatement(sql);
            ps.setString(1, String.valueOf(id));
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                user.setUsername(ps.getResultSet().getString("username"));
                user.setPassword(ps.getResultSet().getString("password"));
            }
        }catch (Exception e){e.printStackTrace();
        }
        return user;
    }
    public static void insertUser(User user) {
        try {
            String sql="insert into user(username,password)values(?,?)";
            PreparedStatement ps=JdbcUtil.getConnection().prepareStatement(sql);
            ps.setString(1,user.getUsername());
            ps.setString(2,user.getPassword());
            ps.executeUpdate();
        }catch (Exception e)
        {e.printStackTrace();}
    }
    public  static void deleteUser(int id) {
        User user=new User();
        try {
            String sql="delete  from user where id=?";
            PreparedStatement ps=JdbcUtil.getConnection().prepareStatement(sql);
            ps.setString(1, String.valueOf(id));
            ps.execute();
        }catch (Exception e){e.printStackTrace();}
    }

    public static  void  updateUse(User user){
        try {
            String sql="update user set username =?,password=? where id=?";
            PreparedStatement ps=JdbcUtil.getConnection().prepareStatement(sql);
            ps.setString(1,user.getUsername());
            ps.setString(2,user.getPassword());
            ps.setString(3,String.valueOf(user.getId()));
            ps.executeUpdate();
        }catch (Exception e)
        {e.printStackTrace();}
    }


    //分页查询
    public List<User> showPageUsers(PageUtil page) {
        QueryRunner qr=new QueryRunner(JdbcUtil.getDataSource());
        int count=selectCount();
        int totalSize=count%page.getPageSize()==0?count/page.getPageSize():count/page.getPageSize()+1;
        page.setCount(count);
        page.setTotalSize(totalSize);
        try {
            return qr.query("select * from user limit ?,?",
                    new BeanListHandler<>(User.class),page.getStart(),page.getPageSize());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int selectCount()  {
        QueryRunner qr=new QueryRunner(JdbcUtil.getDataSource());
        try {
            return  qr.query("select count(*) from user", new ScalarHandler<Long>()).intValue();
        }
        catch (Exception e){e.printStackTrace();}
        return 0;
    }

}
