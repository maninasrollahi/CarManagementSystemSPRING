package com.example.carmanagementsystemspring.DBConnection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CarsRepo extends JpaRepository<Cars,Integer> {
    List<Cars> findByCarid(Integer carid);
    @Modifying
    @Query(value = "update Cars c set c.carname=?1, c.carmry=?2, c.price=?3 where c.carid=?4")
    int UpdateCars(String carname,Integer carmry,Integer price,Integer carid);
}
