package org.dream.www.exam.util;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class MyUtil {
    /*
     *
     * @Author dengye
     * @Description //TODO
     * @Date
     * @Param total 知识点所属试题总数
     * @Param need  考试需要对应知识点的试题数量
     * @return List<Integer> 不重复的集合 对应试题集合的下标
     **/
    public static Set<Integer> RandomQuestion(Integer total, Integer need){

        Set<Integer> set = new HashSet() ;

       while (set.size()<need){

           set.add(new Random().nextInt(total));

       }

        return  set;

    }

    public static void main(String[] args) {

         final  Logger LOG = LogManager.getLogger(MyUtil.class);
        for (Integer integer : MyUtil.RandomQuestion(10, 7)) {
           LOG.info(integer);
        }
        ;

    }
}

