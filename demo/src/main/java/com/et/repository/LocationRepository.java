package com.et.repository;

import com.et.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

    // 1. 查询所有地点
    List<Location> findAll();
    
    // 2. 根据名称模糊查询
    @Query("SELECT l FROM Location l WHERE lower(l.name) LIKE lower(concat('%', :name,'%'))")
    List<Location> findByNameContainingIgnoreCase(@Param("name") String name);
    List<Location> findByNameContaining(String name);
    
    // 3. 查询距离某点一定范围内的地点（单位：米）
    @Query(value = "SELECT * FROM locations WHERE ST_DWithin(geom, ST_SetSRID(ST_MakePoint(:lng, :lat), 4326), :distance)", nativeQuery = true)
    List<Location> findWithinDistance(@Param("lng") double lng, @Param("lat") double lat, @Param("distance") double distance);
    
    // 4. 查询距离某点最近的地点
    @Query(value = "SELECT * FROM locations ORDER BY ST_Distance(geom, ST_SetSRID(ST_MakePoint(:lng, :lat), 4326)) ASC LIMIT 1", nativeQuery = true)
    Location findNearest(@Param("lng") double lng, @Param("lat") double lat);
}