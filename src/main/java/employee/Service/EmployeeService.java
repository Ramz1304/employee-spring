package employee.Service;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import employee.dao.EmployeeDao;
import employee.model.EmployeeData;
import employee.model.EmployeeForm;
import employee.pojo.EmployeePojo;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeDao dao;

	@Transactional(rollbackOn = ApiException.class)
	public EmployeePojo addEmployee(EmployeeForm form) {
		EmployeePojo employee = convert(form);
		normalize(employee);
	    dao.insert(employee);
	    return employee;
	}

	@Transactional
	public void delete(int id) {
		dao.delete(id);
	}

	@Transactional(rollbackOn = ApiException.class)
	public EmployeeData get(int id) throws ApiException {
		return convert(getCheck(id));
	}

	@Transactional
	public List<EmployeeData> getAll() {
		List<EmployeePojo> list = dao.selectAll();
		List<EmployeeData> list2 = new ArrayList<EmployeeData>();
		for (EmployeePojo p : list) {
			list2.add(convert(p));
		}
		return list2;
	}
		
	

	@Transactional(rollbackOn  = ApiException.class)
	public EmployeePojo update(int id, EmployeeForm f) throws ApiException {
		
		EmployeePojo employee = convert(f);
		normalize(employee);
		EmployeePojo employee2 = getCheck(id);
		employee2.setAge(employee.getAge());
		employee2.setName(employee.getName());
		dao.update(employee);
		return employee;
	}

	@Transactional
	public EmployeePojo getCheck(int id) throws ApiException {
		EmployeePojo employee = dao.select(id);
		if (employee == null) {
			throw new ApiException("Employee with given ID does not exit, id: " + id);
		}
		return employee;
	}

	protected static void normalize(EmployeePojo employee) {
		employee.setName(employee.getName().toLowerCase().trim());
	}
	
	private static EmployeePojo convert(EmployeeForm form) {
		EmployeePojo employee = new EmployeePojo();
		employee.setAge(form.getAge());
		employee.setName(form.getName());
		return employee;
	}
	
	private static EmployeeData convert(EmployeePojo employee) {
		EmployeeData data = new EmployeeData();
		data.setAge(employee.getAge());
		data.setName(employee.getName());
		data.setId(employee.getId());
		return data;
	}
	
	
}