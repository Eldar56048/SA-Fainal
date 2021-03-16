package com.emeka.bookshop_sales;

import com.emeka.bookshop_sales.services.BookService;
import com.emeka.bookshop_sales.services.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class OrdersController  {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private BookService bookService;

    @Autowired
    private StaffService staffService;

    @PostMapping("/orders")
    public String sellBooks(@RequestBody OrderRequest orderRequest) {

        String bookName = bookService.getBookName(orderRequest);
        String staffName = staffService.getStaffName(orderRequest);

        Orders order = new Orders();
        order.setBookName(bookName);
        order.setStaffName(staffName);
        order.setQuantity(orderRequest.getQuantity());

        ordersRepository.save(order);
        return "Order submitted";
    }

    @DeleteMapping("/orders/{id}")
    public String deleteOrder(@PathVariable int id){
        ordersRepository.deleteById(id);
        return "Order "+id+" successfully deleted";
    }




    @GetMapping("/orders")
    public List<Orders> getOrders() {
        return ordersRepository.findAll();
    }
}
