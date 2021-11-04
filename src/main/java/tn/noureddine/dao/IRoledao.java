package tn.noureddine.dao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.noureddine.config.Role;
@Repository
public interface IRoledao extends CrudRepository<Role, Integer> {
	
}
