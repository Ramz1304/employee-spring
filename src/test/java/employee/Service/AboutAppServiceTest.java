package employee.Service;

import static org.junit.Assert.assertEquals;

import javax.transaction.Transactional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import employee.model.EmployeeForm;
import employee.pojo.EmployeePojo;


@Transactional
public class AboutAppServiceTest extends AbstractUnitTest{
	
	@Autowired
	private AboutAppService service;
	
	
	
	@Test
	public void testServiceApis() {
		assertEquals("1.0",service.getVersion());
		assertEquals("employee-application",service.getName());
	}

}