package org.javaboy.vhr.controller;

import org.ggm.vhr.entity.MapEntity;
import org.ggm.vhr.entity.QuestionsEntity;
import org.javaboy.vhr.service.QuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@Controller

public class QuestionsController {
    @Autowired
    QuestionsService questionsService;


    @RequestMapping(value = "/questions/manage")
    @ResponseBody
    public List<QuestionsEntity> getQuestions(){
        return questionsService.getQuestions();
    }

    @RequestMapping(value = "/questions/manage/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public boolean deleteQuestion(@PathVariable Long id)
    {
        return questionsService.deleteQuestion(id);

    }



    @RequestMapping(value = "/questions/manage/",method = RequestMethod.POST)
    @ResponseBody
    public boolean addQuestion(@RequestBody QuestionsEntity questionsEntity)
    {
        questionsService.addQuestion(questionsEntity);
        return true;
    }



}
