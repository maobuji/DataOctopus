package com.fan;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.common.Term;

import java.util.List;

/**
 * Created by Administrator on 2017/9/22.
 */
public class SplitTest {
    public static void main(String[] args) {


        String[] testCase = new String[]{
                "二、本项目为中国移动通信集团湖南有限公司郴州分公司自行采购，采购人为中国移动通信集团湖南有限公司郴州分公司，采购代理机构为公诚管理咨询有限公司。项目资金由采购人自筹，并已落实。项目已具备采购条件，现进行公开比选",

        };
        Segment segment = HanLP.newSegment().enableOrganizationRecognize(true);
        for (String sentence : testCase) {
            List<Term> termList = segment.seg(sentence);
            System.out.println(termList);
        }

    }
}
