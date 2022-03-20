package cn.coding.inventorymanagement;

import cn.coding.inventorymanagement.model.Message;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@SpringBootApplication
@RestController
public class InventoryManagementApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(InventoryManagementApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Inventory-Management-Application has Started =============>><<=============");
    }

    @PostMapping("/api/validateLogin")
    public String validateLogin(@RequestBody JSONObject jsonparam, HttpServletRequest request) {
        String username= jsonparam.get("username").toString();
        String password = jsonparam.get("password").toString();
        String str = "";
        Message message = new Message();
        if (username.equals("joseph") && password.equals("Admin@666")) {
            HttpSession session = request.getSession();
            session.setAttribute("username", "joseph");
            message.setCode(1);
            message.setMessage("Login Successfully");
            str = JSON.toJSONString(message);
        } else {
            message.setCode(0);
            message.setMessage("Invalid username or password!");
            str = JSON.toJSONString(message);
        }
        return str;
    }
}
