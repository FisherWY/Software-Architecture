package Experiment4.impl;

import Experiment4.dao.UserTable;
import Experiment4.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author Fisher
 * @Date 2019/9/24 11:04
 **/

@WebServlet("/getBmi")
public class UserImpl extends HttpServlet {

    private UserTable userTable = new UserTable();

    @Override
    public void init() throws ServletException {
        super.init();
        userTable.init();
    }

    @Override
    public void destroy() {
        super.destroy();
        userTable.release();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String req_method = req.getMethod();
        String req_url = req.getRequestURI();

        resp.setContentType("text/html;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.write("<h1>Method: " + req_method + "</h1>");
        writer.write("<h1>URL: " +req_url + "</h1>");
        writer.write("<h1>访问login接口，来自get请求</h1>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String username = req.getParameter("username");
        String weight = req.getParameter("weight");
        String height = req.getParameter("height");
        String bmi = calcBmi(weight, height);

        User user = new User(username, weight, height);
        user.setBmi(bmi);
        userTable.addUser(user);

        resp.setContentType("text/html;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.write("<h1>" + user.getBmi() + "</h1>");

    }

    private String calcBmi(String weight, String height) {
        int wei = Integer.valueOf(weight);
        int hei = Integer.valueOf(height);

        hei -= 105;
        if (hei+5>=wei && hei-5<=wei) {
            return "你的体重正常，请继续保持！！！！！";
        } else if (hei+5<=wei && hei+20>=wei) {
            return "你的体重偏胖，需要进行跑步锻炼";
        } else if (hei-5>=wei && hei-20<=wei) {
            return "你的体重偏瘦，需要多进行合理膳食";
        } else {
            return "非法的输入";
        }
    }
}
