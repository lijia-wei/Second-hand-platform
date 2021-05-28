package com.jess.secondarymarket.service;

import com.jess.secondarymarket.forms.InsertTagsForm;
import com.jess.secondarymarket.vo.ResultVO;

public interface TagService {

    /**
     * 添加标签
     */
    ResultVO insertTags(InsertTagsForm form);


    /**
     * 删除标签
     */
    ResultVO deleteTags(Integer tagId);

    /**
     * 查看所有标签
     */
    ResultVO selectTags();
}
