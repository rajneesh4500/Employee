package org.employee.Utils;

import org.employee.ResponseEntities.Employees;
import org.employee.interfaces.ResponseClasses;
import org.employee.interfaces.ResponseUtils;
import org.springframework.stereotype.Component;




@Component
public class EmployeeUtils implements ResponseUtils {

	@Override
	public void successMessage(ResponseClasses obj) {
		obj.setMessage("Success All the employees got");
		obj.setStatusCode(200);
//		return obj;
	}

	@Override
	public void failureMessage(ResponseClasses obj) {
		obj.setMessage("Error Cannot fetch");
		obj.setStatusCode(404);
//		return obj;
	}
}
