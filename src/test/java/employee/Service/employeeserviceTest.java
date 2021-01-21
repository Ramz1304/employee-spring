package employee.Service;

import static org.junit.Assert.assertEquals;

import javax.transaction.Transactional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import employee.model.EmployeeData;
import employee.model.EmployeeForm;
import employee.pojo.EmployeePojo;


@Transactional
public class employeeserviceTest extends AbstractUnitTest{
	
	@Autowired
	private EmployeeService service;
	@Test
	public void testAdd() throws ApiException {
		EmployeeForm employee = new EmployeeForm();
		employee.setName("Ram");
		//service.add(p);
		EmployeePojo employee1 = service.addEmployee(employee);
	    EmployeePojo Employee2 = service.getCheck(employee1.getId());
	    assertEquals(employee1.getName(),Employee2.getName());
	    assertEquals(employee1.getName(),"ram");
	}
	
	
	@Test
	public void testNormalize() {
		EmployeePojo employee = new EmployeePojo();
		employee.setName("Romil Jain ");
		EmployeeService.normalize(employee);
		assertEquals("romil jain",employee.getName());
	}
	
	@Test(expected = ApiException.class)
	public void testGetCheck() throws ApiException {
		EmployeeForm employee = new EmployeeForm();
		employee.setName("Ram");
		//service.add(p);
		EmployeePojo employee1 = service.addEmployee(employee);
	    EmployeePojo Employee2 = service.getCheck(0);
	    
	}

}