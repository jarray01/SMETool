package tn.noureddine.dao;



import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.noureddine.entity.Index;
@Repository
public interface Iindexdao extends CrudRepository<Index, Integer> {
	@Query("SELECT i from Index i  where i.id = :ind")
	Index getindexbyid (@Param(value ="ind") Integer ind);

}
