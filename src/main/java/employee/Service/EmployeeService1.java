package employee.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import employee.dao.EmployeeDao;
import employee.pojo.EmployeePojo;

@Service
public class EmployeeService1 {
	
	@Autowired
	private EmployeeDao dao;
	
	public void addList(List<EmployeePojo> list) {
		for(EmployeePojo p: list) {
			dao.insert(p);
		}
	}
	

}
