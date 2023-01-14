package com.sjz.cloudcourse.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sjz.cloudcourse.common.R;
import com.sjz.cloudcourse.entity.*;
import com.sjz.cloudcourse.service.DocumentService;
import com.sjz.cloudcourse.service.UploadService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 */
@RestController
@RequestMapping("/document")
@CrossOrigin
public class DocumentController {

    @Resource
    private DocumentService documentService;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private UploadService uploadService;

    /**
     * 查询所有文档
     *
     * @return R
     */


    @GetMapping("/list")
    public R getDocumentList(@RequestParam(defaultValue = "1") Integer page,
                             @RequestParam(defaultValue = "10") Integer limit,
                             @RequestParam(defaultValue = "") String name) {

        Page<Document> pageInfo = new Page<>(page, limit);
        documentService.page(pageInfo, new QueryWrapper<Document>().like("title", name));
        List<Document> records = pageInfo.getRecords();
        System.out.println("records = " + records);
        return R.ok().put("data", records).put("total", pageInfo.getTotal());
    }


    /**
     * 修改文档的信息
     *
     * @param document return R
     */
    @PostMapping("/update")
    public R updateDocument(@RequestBody Document document) {
        System.out.println("document = " + document);
        boolean b = documentService.updateById(document);
        if (b) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    /**
     * 删除文档
     *
     * @param id
     * @return R
     */
    @GetMapping("/delete")
    public R deleteDocument(@RequestParam Integer id) {
        boolean b = documentService.removeById(id);
        if (b) {
            User user = (User) redisTemplate.opsForValue().get("user");
            if (user != null) {
                Upload upload = new Upload();
                upload.setUId(user.getId());
                upload.setDId(id);
                uploadService.remove(new QueryWrapper<>(upload));
            }

            return R.ok();
        } else {
            return R.error();
        }
    }


    /**
     * 添加文档
     *
     * @param document
     * @return R
     */
    @PostMapping("/add")
    public R addDocument(@RequestBody Document document) {


        boolean b = documentService.save(document);
        if (b) {
            //        从redis中获取用户信息
            User user = (User) redisTemplate.opsForValue().get("user");
//            查询用户上传的文档
            if (user != null) {
                Upload upload = new Upload();
                upload.setUId(user.getId());
                upload.setDId(document.getId());
            }
            return R.ok();
        } else {
            return R.error();
        }
    }

    /**
     * 根据id查询文档
     *
     * @param id
     * @return R
     */
    @GetMapping("/get")
    public R getDocumentById(@RequestParam Integer id) {
        Document document = documentService.getById(id);
        return R.ok().put("data", document);
    }


    @GetMapping("/getDocumentByUserId")
    public R getDocumentByUserId() {
        User user = (User) redisTemplate.opsForValue().get("user");
//      根据用户id查询该用户上传的图书
        if (user != null) {
//      根据用户id
            int i = uploadService.count(new QueryWrapper<Upload>().eq("u_id", user.getId()));
            List<Upload> uploadList = uploadService.list(new QueryWrapper<Upload>().eq("u_id", user.getId()));
            List<Document> documents = new ArrayList<Document>();
            for (Upload upload : uploadList) {
//                根据id 查询图书
                Document document = documentService.getById(upload.getDId());
                documents.add(document);
            }
            return R.ok().put("data", documents).put("total", i);
        } else {
            return R.error();
        }
    }
}



