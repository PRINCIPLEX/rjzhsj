package org.javaboy.vhr.service;

import org.ggm.vhr.entity.MapEntity;
import org.ggm.vhr.dao.MapMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MapService {

    @Autowired
    MapMapper mapMapper;


    public boolean deleteMap(long id)
    {
        mapMapper.deleteById(id);
        return true;
    }


    public boolean addMap(MapEntity mapEntity)
    {
       mapMapper.save(mapEntity);
       return true;
    }

    public List<MapEntity> getMap()
    {
        return mapMapper.findAll();
    }
}
