package org.ggm.vhr.dao;

import org.ggm.vhr.entity.MapEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface MapMapper extends JpaRepository<MapEntity,Long> {
}
