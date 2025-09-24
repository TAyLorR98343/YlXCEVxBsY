// 代码生成时间: 2025-09-24 13:37:20
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

/**
 * RESTful API示例
 *
 * 该类提供简单的RESTful API接口，用于演示如何使用JSF框架创建RESTful服务。
 */
@Path("/api/employees")
public class RestfulApiExample {

    // 模拟数据库存储的员工信息
    private Map<Integer, Employee> employees = new HashMap<>();

    public RestfulApiExample() {
        // 初始化员工数据
        employees.put(1, new Employee(1, "John Doe"));
        employees.put(2, new Employee(2, "Jane Doe"));
    }

    /**
     * 获取所有员工的列表
     *
     * @return 包含所有员工信息的响应对象
     */
    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllEmployees() {
        try {
            // 返回所有员工信息
            return Response.ok(employees.values()).build();
        } catch (Exception e) {
            // 错误处理
            return Response.serverError().entity("Error retrieving employees: " + e.getMessage()).build();
        }
    }

    /**
     * 根据员工ID获取员工信息
     *
     * @param id 员工ID
     * @return 包含单个员工信息的响应对象
     */
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployeeById(@PathParam("id") int id) {
        try {
            Employee employee = employees.get(id);
            if (employee != null) {
                // 返回指定员工信息
                return Response.ok(employee).build();
            } else {
                // 员工不存在
                return Response.status(Response.Status.NOT_FOUND).entity("Employee not found").build();
            }
        } catch (Exception e) {
            // 错误处理
            return Response.serverError().entity("Error retrieving employee: " + e.getMessage()).build();
        }
    }
}

/**
 * 员工实体类
 *
 * 用于表示员工信息的简单类。
 */
class Employee {
    private int id;
    private String name;

    public Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
