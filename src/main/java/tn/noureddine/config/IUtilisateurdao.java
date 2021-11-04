package tn.noureddine.config;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IUtilisateurdao extends CrudRepository<Utilisateur, Integer> {
	@Query("SELECT u FROM Utilisateur u WHERE u.username = :username")
	Utilisateur findUserByName(@Param("username") String name);
	@Query("select u from Utilisateur u where u.username = :username and u.password=:password")
	public Utilisateur verif(@Param("username") String username,@Param("password") String password);
	@Query(value ="SELECT r.description FROM Role r,Utilisateur u  WHERE u.id_role = r.id and  u.id = :id",nativeQuery=true)
	public String[] findrolebyuserid( @Param("id") Integer id);


}
