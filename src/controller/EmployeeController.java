/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.EmployeesDAO;
import entities.Departments;
import entities.Employees;
import entities.Jobs;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author TAMU
 */
public class EmployeeController {

    public EmployeeController() {
        this.edao = new EmployeesDAO();
    }

    public EmployeesDAO edao;

    public void BindingAll(JTable table, String[] header) {
        BindingTabel(table, header, edao.getAll());
    }

    private void BindingTabel(JTable table, String[] header, List<Object> datas) {
        DefaultTableModel model = new DefaultTableModel(header, 0);
        for (Object data : datas) {
            Employees emp = (Employees) data;
            Object[] data1 = {
                emp.getEmployeeId(),
                emp.getFirstName(),
                emp.getLastName(),
                emp.getEmail(),
                emp.getPhoneNumber(),
                emp.getHireDate(),
                emp.getJobId(),
                emp.getSalary(),
                emp.getCommissionPct(),
                emp.getManagerId(),
                emp.getDepartmentId()
            };
            model.addRow(data1);
        }
        table.setModel(model);
    }

    public boolean insert(Integer employeeId, String firstName, String lastName, String email, String phoneNumber,
            Date HIRE_DATE, String jobId, Long salary, Long commissionPct, Integer managerId, Short departmentId) {

//            Employees emp = new Employees(employeeId, firstName, lastName, email, phoneNumber, HIRE_DATE,
//            jobId, salary, commissionPct, managerId, departmentId);
        
        Employees emp = new Employees();
        emp.setEmployeeId(employeeId);
        emp.setFirstName(firstName);
        emp.setLastName(lastName);
        emp.setEmail(email);
        emp.setPhoneNumber(phoneNumber);
        emp.setHireDate(HIRE_DATE);
        emp.setJobId(new Jobs(jobId));
        emp.setSalary(BigDecimal.valueOf(salary));
        emp.setCommissionPct(BigDecimal.valueOf(commissionPct));
        emp.setManagerId(new Employees(managerId));
        emp.setDepartmentId(new Departments(departmentId));
        return edao.insert(emp);
//    public boolean update (Integer employeeId, String lastName, String email, Date hireDate){
//    Employees emp = new Employees(employeeId, lastName, email, hireDate);
//    return edao.update(emp);
    }

}
