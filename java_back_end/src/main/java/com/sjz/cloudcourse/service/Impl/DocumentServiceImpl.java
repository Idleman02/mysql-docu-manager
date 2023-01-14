package com.sjz.cloudcourse.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sjz.cloudcourse.entity.Document;
import com.sjz.cloudcourse.mapper.DocumentMapper;
import com.sjz.cloudcourse.service.DocumentService;
import org.springframework.stereotype.Service;

@Service
public class DocumentServiceImpl extends ServiceImpl<DocumentMapper, Document> implements DocumentService {
}

