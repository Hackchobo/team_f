package com.green.team_f.list;

import com.green.team_f.list.model.ListSelAllEntity;
import com.green.team_f.list.model.ListSelHelVo;
import com.green.team_f.list.model.ListSelMealVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ListService {

    private ListMapper mapper;

    @Autowired
    public ListService(ListMapper mapper) {
        this.mapper = mapper;
    }

    public List<ListSelMealVo> selMealList(ListSelAllEntity entity){

            return mapper.selMealList(entity);

    }

    public List<ListSelHelVo> selHelList(ListSelAllEntity entity){
        return mapper.selHelList(entity);
    }
}
