package org.javaboy.vhr.service;

import org.ggm.vhr.dao.QuestionsMapper;
import org.ggm.vhr.entity.QuestionsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionsService {
    @Autowired
    QuestionsMapper questionsMapper;

    public List<QuestionsEntity> getQuestions()
    {
        return questionsMapper.findAll();
    }

    public boolean deleteQuestion(long id)
    {
        questionsMapper.deleteById(id);
        return true;
    }

    public boolean addQuestion(QuestionsEntity questionsEntity)
    {
        questionsMapper.save(questionsEntity);
        return true;
    }

}
