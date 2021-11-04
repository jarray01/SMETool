package tn.noureddine.dao;
import java.sql.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.noureddine.entity.Dataindex;
import tn.noureddine.entity.Shape;
@Repository
public interface Idataindexdao extends CrudRepository<Dataindex, Integer>{
	//Map <List<String>,List<String>> sm =new LinkedHashMap<>();
	@Query("SELECT p from Dataindex p  where p.index.id = :indexx")
	List<Dataindex> getdatabyindex (@Param(value ="indexx") Integer indexx);
	
	//@Query("SELECT distinct avg(value),p.dateindex from Dataindex p  where p.index.id = :indexx GROUP BY dateindex")
	@Query(value ="SELECT dateindex,avg(value) FROM dataindex where id_index =:index GROUP BY dateindex",nativeQuery=true)
	List<Object[]>  getdatabyindex1 (@Param(value ="index") Integer idindex);
	@Query(value ="SELECT dateindex,avg(value) FROM dataindex where id_index =:index and id_study =:idstudy GROUP BY dateindex",nativeQuery=true)
	List<Object[]>  getdatabyindexbystudy(@Param(value ="index") Integer idindex,@Param(value ="idstudy") Integer idstudy);
	@Query("SELECT avg(value) from Dataindex p  where p.index.id = :indexx")
	List<String> getdatabyindexavg (@Param(value ="indexx") Integer indexx);
	@Query(value ="SELECT avg(value) as smmoy,dateindex FROM dataindex where id_index =:idindex GROUP BY dateindex",nativeQuery=true)
    public List<String> getavgsm(Integer idindex);

}
