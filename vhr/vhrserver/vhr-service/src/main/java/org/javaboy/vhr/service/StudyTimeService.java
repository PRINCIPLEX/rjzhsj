package org.javaboy.vhr.service;

import org.ggm.vhr.dao.StudyTimeMapper;
import org.ggm.vhr.entity.StudyTimeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudyTimeService {
    @Autowired
    StudyTimeMapper studyTimeMapper;
    public List<StudyTimeEntity> getTime()
    {
        return studyTimeMapper.findAll();
    }
}
