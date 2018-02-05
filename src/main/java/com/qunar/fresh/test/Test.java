package com.qunar.fresh.test;

import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * create by lijiajia on 2018/1/12
 */
public class Test {
    public Long getOne(List<Long> iLists, Integer a, List<TestModel> testModels, EnumTest enumTest) {
        int length = iLists.size();
        for (int i = 0; i < length; i++) {
            if (iLists.get(i).getClass().equals(Long.class)) {
                System.out.println("shi long");
            }
        }

        for (TestModel model : testModels) {
//            Class<?> aClass = model.getCode().getClass();
//            if (aClass.equals(Long.class) || aClass.equals(Long.TYPE)) {
//                System.out.println("hahahahhahahha");
//            }
            System.out.println(model.getAge());
            System.out.println(model.getCode());
            System.out.println(model.getName());
        }
        return iLists.get(0);
    }
}
