package com.sjz.cloudcourse.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.sjz.cloudcourse.common.R;
import com.sjz.cloudcourse.entity.Book;
import com.sjz.cloudcourse.entity.Buyer;
import com.sjz.cloudcourse.entity.User;
import com.sjz.cloudcourse.service.BookService;
import com.sjz.cloudcourse.service.BuyerService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 */
@RestController
@RequestMapping("/book")
@CrossOrigin
public class BookController {


    @Resource
    RedisTemplate<String, Object> redisTemplate;

    @Resource
    private BookService bookService;

    @Resource
    private BuyerService buyerService;

    /**
     * 查询所有图书
     *
     * @return R
     */
    @GetMapping("/list")
    public R getBookList(@RequestParam(defaultValue = "1") Integer page,
                         @RequestParam(defaultValue = "10") Integer limit,
                         @RequestParam(defaultValue = "") String name) {

        Page<Book> pageInfo = new Page<>(page, limit);
        bookService.page(pageInfo, new QueryWrapper<Book>().like("book_name", name));
        List<Book> records = pageInfo.getRecords();
        System.out.println("records = " + records);
        return R.ok().put("data", records).put("total", pageInfo.getTotal());
    }


    /**
     * 修改图书的信息
     *
     * @param book
     * @return R
     */
    @PostMapping("/update")
    public R updateBook(@RequestBody Book book) {
//        从redis中获取用户信息
        User user = (User) redisTemplate.opsForValue().get("user");

        System.out.println("User = " + user);
        System.out.println("book = " + book);
        boolean b = bookService.updateById(book);
        if (b) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    /**
     * 删除图书
     *
     * @param id
     * @return R
     */
    @GetMapping("/delete")
    public R deleteBook(Integer id) {

        boolean b = bookService.removeById(id);
        if (b) {
            User user = (User) redisTemplate.opsForValue().get("user");
//        增加关联表的id
            if (user != null) {
                Buyer buyer = new Buyer();
                buyer.setuId(user.getId());
                buyer.setbId(id);
                buyerService.remove(new QueryWrapper<Buyer>().eq("u_id", buyer.getuId()).eq("bid", buyer.getbId()));
            }
            return R.ok();
        } else {
            return R.error();
        }
    }

    /**
     * 添加图书
     *
     * @param book
     * @return R
     */
    @PostMapping("/add")
    public R addBook(@RequestBody Book book) {
        //        从redis中获取用户信息
        System.out.println("book = " + book);

        boolean b = bookService.save(book);
        if (b) {
            User user = (User) redisTemplate.opsForValue().get("user");
//        增加关联表的id
            if (user != null) {
                Buyer buyer = new Buyer();
                buyer.setbId(book.getId());
                buyer.setuId(user.getId());
                buyerService.save(buyer);
            }
            return R.ok();
        } else {
            return R.error();
        }
    }

    /**
     * 根据id查询图书
     *
     * @param id
     * @return R
     */
    @GetMapping("/get")
    public R getBook(Integer id) {
        Book book = bookService.getById(id);
        return R.ok().put("data", book);
    }


    @GetMapping("/getBookByUserId")
    public R getBookByUserId() {
        User user = (User) redisTemplate.opsForValue().get("user");
//      根据用户id查询该用户上传的图书
        if (user != null) {
//            List<Buyer> buyers = buyerService.list(new QueryWrapper<Buyer>().eq("u_id", user.getId()));
//            List<Book> books = bookService.list(new QueryWrapper<Book>().eq("id", user.getId()));
            int i = buyerService.count(new QueryWrapper<Buyer>().eq("u_id", user.getId()));
            List<Buyer> buyers = buyerService.list(new QueryWrapper<Buyer>().eq("u_id", user.getId()));
            List<Book> books = new ArrayList<>();
            for (Buyer buyer : buyers) {
                Book book = bookService.getById(buyer.getbId());
                books.add(book);
            }
            return R.ok().put("data", books).put("total", i);
        } else {
            return R.error();
        }
    }


}

