package com.sjz.cloudcourse.service.impl;

import com.sjz.cloudcourse.entity.Book;
import com.sjz.cloudcourse.mapper.BookMapper;
import com.sjz.cloudcourse.service.BookService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 */
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements BookService {

}
