package com.jess.secondarymarket.util;

import com.jess.secondarymarket.enums.ResultEnum;
import com.jess.secondarymarket.vo.ResultVO;
import lombok.Data;

/**
 * @author Jess
 * @date 2020/7/31 10:00
 */
@Data
public class ResultVOUtil {

    /**
     * 有参成功结果
     */
    public static ResultVO success(Object object) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        resultVO.setData(object);
        return resultVO;
    }

    /**
     * 无参结果
     */
    public static ResultVO success() {
        return success(null);
    }

    /**
     * 返回错误结果
     */
    public static ResultVO error(ResultEnum resultEnum) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(resultEnum.getCode());
        resultVO.setMsg(resultEnum.getMsg());
        return resultVO;
    }


}
