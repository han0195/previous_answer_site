package kbuni_question.service;

import kbuni_question.domain.TcategoryEntity;
import kbuni_question.domain.TcategoryRepository;
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

    public JSONArray getcatlist(){
        List<TcategoryEntity> tcategoryEntities = tcategoryRepository.findAll();

        JSONArray jsonArray = new JSONArray();
        for(TcategoryEntity temp : tcategoryEntities){
            jsonArray.put(temp.getTcname());
        }
        return jsonArray;
    }

}
