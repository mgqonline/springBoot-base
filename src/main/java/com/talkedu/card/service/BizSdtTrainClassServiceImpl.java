package com.talkedu.card.service;

import com.talkedu.card.bo.BizSdtTrainClass;
import com.talkedu.card.mapper.BizSdtTrainClassMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BizSdtTrainClassServiceImpl implements BizSdtTrainClassService {


    @Autowired
    BizSdtTrainClassMapper sdtTrainClassMapper;


    @Override
    public List<BizSdtTrainClass> selectSdtTrainClass(Map<String, Object> params) {
        return sdtTrainClassMapper.selectSdtTrainClass(params);
    }
}
