package in.javahome.mvc.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import in.javahome.mvc.pojo.Employee;

public interface IEmployeeDao extends MongoRepository<Employee, Integer>{
}
