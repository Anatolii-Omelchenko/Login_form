package academy.prog.login_form_hm2;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {

    static final String LOGIN = "admin";
    static final String PASS = "admin";
//    static final String PASS = "aDmin_$%12345!";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String ageS = request.getParameter("age");
        Integer age = null;

        if (!ageS.equals("")) {
            age = Integer.valueOf(ageS);
        }
        HttpSession session = request.getSession(true);

        if (age != null ) {
            if (LOGIN.equals(login) && PASS.equals(password) && age >= 18) {
                session.setAttribute("user-login", login);
            }

            session.setAttribute("adult", age >= 18);
        } else {
            session.removeAttribute("adult");
        }

        session.setAttribute("secure", isSecurePassword(password));
        response.sendRedirect("index.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String a = request.getParameter("a");
        HttpSession session = request.getSession(false);

        if (a.equals("exit") && session != null) {
            session.removeAttribute("user-login");
            session.removeAttribute("adult");
            response.sendRedirect("index.jsp");
        }
    }

    private boolean isSecurePassword(String password) {
        String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{10,}";
        return password.matches(pattern);
    }
}
