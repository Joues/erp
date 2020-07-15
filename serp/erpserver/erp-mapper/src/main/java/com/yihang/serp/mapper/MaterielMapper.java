package com.yihang.serp.mapper;

import com.yihang.serp.model.MatIn;
import com.yihang.serp.model.Materiel;

import java.util.List;

public interface MaterielMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Materiel record);

    int insertSelective(Materiel record);

    Materiel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Materiel record);

    int updateByPrimaryKey(Materiel record);

    List<MatIn> getAllMateriel();
}