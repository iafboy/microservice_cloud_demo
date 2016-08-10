package io.pivotal.microservices.accounts.db.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import io.pivotal.microservices.accounts.db.model.AccountModel;

@Mapper
public interface AccountDao {
	@Select ("select * from T_ACCOUNT where NUMBER = #{NUMBER}")
	public AccountModel findByNumber(@Param("NUMBER")String number);
	@Select("select * from T_ACCOUNT where NAME like #{NAME}")
	public List<AccountModel> findByOwnerContainingIgnoreCase(@Param("NAME")String names);
	@Select("SELECT count(*) from T_ACCOUNT")
	public int countAccounts();
	@Update("Update T_ACCOUNT set NAME=#{NAME} where NUMBER=#{NUMBER}")
	public boolean updateName(@Param("NUMBER")String no,@Param("NAME")String name);
	@Delete("delete from T_ACCOUNT where NUMBER=#{NUMBER}")
	public boolean deleteAccount(@Param("NUMBER")String no);
}
