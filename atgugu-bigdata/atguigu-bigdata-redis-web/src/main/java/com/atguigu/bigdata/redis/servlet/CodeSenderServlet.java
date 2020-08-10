package com.atguigu.bigdata.redis.servlet;

import redis.clients.jedis.Jedis;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;


/**
 * 发送验证码
 */
public class CodeSenderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取电话号码
        String phoneNo = req.getParameter("phone_no");

        //获取当日已发送次数
        Jedis jedis = null;
        try {
            jedis = new Jedis("hadoop101", 6379);
            //key的设计
            //rowkey设计
            String countKey = phoneNo + ":count";
            String codeKey = phoneNo + ":code";
            String lastCount = jedis.get(countKey);
            //判断是否发过验证码
            Integer second = getSecondsNextEarlyMorning().intValue();
            if (lastCount == null) {
                jedis.setex(countKey, second, "1");


                //第一次发送，生成验证码
                StringBuilder code = new StringBuilder();
                for (int i = 0; i < 6; i++) {
                    code.append(Math.random() * 10);
                }
                //将验证码放入redis
                jedis.setex(codeKey, second, code.toString());
                //返回成功消息
                resp.getWriter().print("true");
            } else {
                //发送过验证码
                if (Integer.valueOf(lastCount)<3){
                    //发送验证码

                    //次数加一
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }

        super.doPost(req, resp);
    }

    /**
     * 获取当前时间到第二天零点的时间（单位是秒）
     *
     * @return
     */
    public Long getSecondsNextEarlyMorning() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return (cal.getTimeInMillis() - System.currentTimeMillis()) / 1000;
    }


}
