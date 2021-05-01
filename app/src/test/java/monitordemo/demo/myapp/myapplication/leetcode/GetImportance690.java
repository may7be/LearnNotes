package monitordemo.demo.myapp.myapplication.leetcode;

import java.util.HashMap;
import java.util.List;

import monitordemo.demo.myapp.myapplication.leetcode.model.Employee;

/**
 * @Author zhao on 5/1/21
 */
public class GetImportance690 {
    /**
     * 一个员工最多有一个 直系 领导，但是可以有多个 直系 下属
     * 员工数量不超过 2000 。
     */
    private List<Employee> employees;

    private HashMap<Integer, Employee> map;

    public int getImportance2(List<Employee> employees, int id) {
        if (employees == null) {
            return 0;
        }
        //存入map方便查找
        HashMap<Integer, Employee> map = new HashMap<>();
        for (Employee employee : employees) {
            map.put(employee.id, employee);
        }
        return getCount2(map.get(id));
    }

    public int getImportance(List<Employee> employees, int id) {
        if (employees == null) {
            return 0;
        }
        this.employees = employees;
        //找到该员工
        Employee targetE = findTargetE(id);
        return getCount(targetE);
    }

    private Employee findTargetE(int id) {
        Employee targetE = null;
        for (Employee employee : employees) {
            if (employee.id == id) {
                targetE = employee;
                break;
            }
        }
        return targetE;
    }

    private int getCount(Employee targetE) {
        if (targetE == null) {
            return 0;
        }
        int count = targetE.importance;
        for (Integer subordinate : targetE.subordinates) {
            count += getCount(findTargetE(subordinate));
        }
        return count;
    }

    private int getCount2(Employee targetE) {
        if (targetE == null) {
            return 0;
        }
        int count = targetE.importance;
        for (Integer subordinate : targetE.subordinates) {
            count += getCount(map.get(subordinate));
        }
        return count;
    }
}
