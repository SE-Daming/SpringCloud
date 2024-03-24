package cn.itcast.order.service;

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

    public Order queryOrderById(Long orderId) {
        // 1.查询订单
        Order order = orderMapper.findById(orderId);
        // 2.查询用户
//      String url="http://127.0.0.1:8081/user/"+order.getUserId();
        String url="http://userservice/user/"+order.getUserId();
        User user  = restTemplate.getForObject(url, User.class);
        order.setUser(user);
        // 4.返回
        return order;
    }
}
