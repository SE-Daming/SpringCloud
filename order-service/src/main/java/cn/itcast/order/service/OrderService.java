package cn.itcast.order.service;

import cn.itcast.order.client.UserClient;
import cn.itcast.order.mapper.OrderMapper;
import cn.itcast.order.pojo.Order;
import cn.itcast.order.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    UserClient userClient;

    public Order queryOrderById(Long orderId) {
        // 1.查询订单
        Order order = orderMapper.findById(orderId);
       //2 .feign发送http请求查询用户
        User user = userClient.findById(order.getUserId());
        //3.将用户信息封装到订单中
        order.setUser(user);
        return order;
    }
}
