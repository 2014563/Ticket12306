package cn.edu.guet;


/**
 * @Author liwei
 * @Date 2023/5/7 10:51
 * @Version 1.0
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

public class TicketServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //解决乱码问题
        request.setCharacterEncoding("UTF-8");
        //Tomcat把我们的数据封装到HttpServletRequest对象中的时候,自动把编码转变了,所以我们需要重新转变回来
        //用request.getParameter("返回消息的name")获取网页返回的消息
        String startStation = request.getParameter("startStation");
        String endStation = request.getParameter("endStation");
        String departureDate=request.getParameter("departureDate");
        System.out.println("起始站：" + startStation);
        System.out.println("终点站："+endStation);
        System.out.println("出发日期："+departureDate);
        //把数据传到TicketSearch类中的search方法中
        List<Ticket> ticketList=TicketSearch.search(startStation, endStation, departureDate);
        //美化数据
        String json=JSON.toJSONString(ticketList, SerializerFeature.PrettyFormat);
        //设置相应类型
        //request用于请求，response用于响应
        //把车次的数据返回给网页
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out=response.getWriter();
        out.print(json);
        out.flush();
        out.close();

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
