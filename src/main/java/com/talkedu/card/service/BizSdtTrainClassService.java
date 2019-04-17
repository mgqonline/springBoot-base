package com.talkedu.card.service;

import com.talkedu.card.bo.BizSdtTrainClass;

import java.util.List;
import java.util.Map;

public interface BizSdtTrainClassService {

    List<BizSdtTrainClass> selectSdtTrainClass(Map<String,Object> params);
}
