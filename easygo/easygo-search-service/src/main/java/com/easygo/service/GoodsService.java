package com.easygo.service;

import com.easygo.pojo.Goods;

import java.util.List;

/**
 * @BelongsProject: easygo
 * @BelongsPackage: com.easygo.service
 * @Author: bruceliu
 * @QQ:1241488705
 * @CreateTime: 2020-04-11 09:54
 * @Description: TODO
 */
public interface GoodsService {

    //新增文档
    public Goods saveDocument(Goods goods);

    //批量新增
    public Iterable<Goods> saveDocuments(List<Goods> list);

    //根据ID查询文档
    public Goods getDocumentById(Long id);

    //根据ID更新
    public void updateDocumentById(Goods goods);

    //根据ID删除
    public void deleteDocumentById(Long id);

    //全部删除
    public void deleteAllDocument();

}
