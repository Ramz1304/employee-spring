package employee.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.h2.store.Data;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import employee.model.EmployeeData;
import employee.model.EmployeeForm;
import employee.pojo.EmployeePojo;
import employee.service.ApiException;
import employee.service.EmployeeService;


@Transactional
public class EmployeeServiceTest extends AbstractUnitTest {

    @Autowired
    private EmployeeService service;

    @Test
    public void testAdd() throws ApiException {
        EmployeeForm employee = new EmployeeForm();
        employee.setName("Ram");
        EmployeePojo employee1 = service.addEmployee(employee);
        assertEquals("ram", employee1.getName());

    }

    @Test
    public void testGet() throws ApiException {
        EmployeeForm employee = new EmployeeForm();
        employee.setName("Ram");
        EmployeePojo employee1 = service.addEmployee(employee);
        EmployeePojo employee2 = service.getCheck(employee1.getId());
        assertEquals(employee1.getName(), employee2.getName());
        assertEquals("ram", employee1.getName());


    }

    @Test
    public void testUpdate() throws ApiException {
        EmployeeForm employee = new EmployeeForm();
        employee.setName("Ram");
        EmployeePojo employee1 = service.addEmployee(employee);
        EmployeePojo employee2 = service.getCheck(employee1.getId());
        employee2.setName("ramz");
        assertEquals("ramz", employee1.getName());

    }

    @Test
    public void testDelete() throws ApiException {
        EmployeeForm employee = new EmployeeForm();
        employee.setName("Ram");
        EmployeePojo employee1 = service.addEmployee(employee);
        service.delete(employee1.getId());
        assertNull(service.get(employee1.getId()));
			
			/*EmployeeForm employee = new EmployeeForm();
			employee.setName("Ram");
			EmployeePojo employee1 = service.addEmployee(employee);
			EmployeePojo employee2 = service.getCheck(employee1.getId());
			service.delete(employee2.getId());
	    	assertNull(employee2.getName());*/


    }


    @Test
    public void testGetAll() throws ApiException {
        EmployeeForm employee = new EmployeeForm();
        employee.setName("ram");
        EmployeePojo pojo = service.addEmployee(employee);
        EmployeeForm employee1 = new EmployeeForm();
        employee1.setName("sau");
        EmployeePojo pojo1 = service.addEmployee(employee1);

        EmployeeData data = new EmployeeData();
        data.setName(pojo.getName());
        EmployeeData data1 = new EmployeeData();
        data1.setName(pojo1.getName());

        List<EmployeeData> list = service.getAll();
        List<EmployeeData> list2 = new ArrayList<EmployeeData>();

        for (EmployeeData edata1 : list) {
            list2.add(edata1);

            System.out.println(list2);
        }
        assertEquals(employee.getName(), data.getName());
        assertEquals(employee1.getName(), data1.getName());
    }

    @Test
    public void testNormalize() {
        EmployeePojo employee = new EmployeePojo();
        employee.setName("Romil Jain ");
        EmployeeService.normalize(employee);
        assertEquals("romil jain", employee.getName());
    }

    @Test(expected = ApiException.class)
    public void testGetCheck() throws ApiException {
        EmployeeForm employee = new EmployeeForm();
        employee.setName("Ram");
        EmployeePojo employee1 = service.addEmployee(employee);
        EmployeePojo employee2 = service.getCheck(0);

    }

}