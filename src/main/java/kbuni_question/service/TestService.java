package kbuni_question.service;

import kbuni_question.domain.TcategoryEntity;
import kbuni_question.domain.TcategoryRepository;
import kbuni_question.domain.TestEntity;
import kbuni_question.domain.TestRopository;
import org.aspectj.weaver.ast.Test;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TestService {

    @Autowired
    TcategoryRepository tcategoryRepository;

    @Autowired
    TestRopository testRopository;

    // 카테고리 전부 가져오기
    public JSONArray getcatlist(){
        List<TcategoryEntity> tcategoryEntities = tcategoryRepository.findAll();

        JSONArray jsonArray = new JSONArray();
        for(TcategoryEntity temp : tcategoryEntities){
            JSONObject object = new JSONObject();
            object.put("tcno", temp.getTcno());
            object.put("tcname", temp.getTcname());
            jsonArray.put(object);
        }
        return jsonArray;
    }

    // 해당카테고리와 일치하는 시험리스트가져오기
    public JSONArray tgettestlist(int tcno, String tgrade){
        List<TestEntity> testEntities = testRopository.findBytcno(tcno, tgrade);
        System.out.println(tcno);
        System.out.println(tgrade);
        JSONArray jsonArray = new JSONArray();
        for(TestEntity temp : testEntities){
            JSONObject object = new JSONObject();
            object.put("tno" ,temp.getTno());
            object.put("tanswer", temp.getTanswer());
            object.put("testof", temp.getTestof());
            object.put("tgrade", temp.getTgrade());
            object.put("tname",temp.getTname());
            object.put("tyear", temp.getTyear());
            object.put("tcno", temp.getTno());
            jsonArray.put(object);
        }
        return jsonArray;
    }


}
