package com.epam.task4.command;

import com.epam.task4.builder.AbstractDeviceBuilder;
import com.epam.task4.dao.DAOException;
import com.epam.task4.dao.UserDAO;
import com.epam.task4.encoder.PasswordEncoder;
import com.epam.task4.entity.User;
import com.epam.task4.factory.DeviceBuilderFactory;
import com.epam.task4.factory.UserFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class RequestHandler implements Handler {
    private static final String XML_FILE_PATH = "someDevices.xml";

    @Override
    public void handle(XMLCommandType commandType, HttpServletRequest request, HttpServletResponse response, ServletContext servletContext) throws ServletException, IOException {

        switch (commandType){
            case PARSE_COMMAND:
                String parserType = request.getParameter("parserType");
                DeviceBuilderFactory.DeviceBuilderType builderType = DeviceBuilderFactory.DeviceBuilderType.valueOf(parserType.toUpperCase());
                AbstractDeviceBuilder deviceBuilder = DeviceBuilderFactory.createDeviceBuilder(builderType);
                Objects.requireNonNull(deviceBuilder).buildDeviceSet(servletContext.getRealPath("/res/")+XML_FILE_PATH);
                request.setAttribute("pcComponentSet",deviceBuilder.getPcComponentSet());
                request.setAttribute("phoneSet",deviceBuilder.getPhoneSet());
                request.setAttribute("parserType",parserType);
                request.getRequestDispatcher("jsp/result.jsp").forward(request,response);
                break;
            case TO_MAIN_PAGE_COMMAND:
                request.getRequestDispatcher("jsp/app.jsp").forward(request,response);
                break;
            case AUTHORIZATION_COMMAND:
                String authorizationLogin = request.getParameter("login");
                String authorizationPassword = request.getParameter("password");

                if(authorize(authorizationLogin,authorizationPassword)){
                    //To application page
                    request.getRequestDispatcher("jsp/app.jsp").forward(request,response);
                }else{
                    //To authorization page + errors
                    request.setAttribute("mainPageMessage","Something was wrong");
                    request.getRequestDispatcher("index.jsp").forward(request,response);
                }
                break;
            case REGISTRATION_COMMAND:
                String registerLogin = request.getParameter("login");
                String registerPassword = request.getParameter("password");

                if(register(registerLogin,registerPassword)){
                    //To authorization page + successful registration
                    request.setAttribute("devicePageMessage","Registration succeeded");
                    request.getRequestDispatcher("index.jsp").forward(request,response);
                }else{
                    //To registration page + error
                    request.setAttribute("errorMessage","Something was wrong");
                    request.getRequestDispatcher("jsp/register.jsp").forward(request,response);
                }
                break;
            case TO_REGISTRATION_PAGE_COMMAND:
                request.getRequestDispatcher("jsp/register.jsp").forward(request,response);
                break;
            case SIGN_OUT_COMMAND:
                request.getRequestDispatcher("index.jsp").forward(request,response);
                break;
        }
    }

    private boolean register(String login, String password){
        UserDAO userDAO = new UserDAO();
        PasswordEncoder passwordEncoder = new PasswordEncoder();

        try {
            User user = UserFactory.createUser(login,passwordEncoder
                    .encryptPassword(password));
            return userDAO.create(user);

        }catch (DAOException e){
            throw new RuntimeException(e);
        }
    }

    private boolean authorize(String login, String password){
        UserDAO userDAO = new UserDAO();
        PasswordEncoder passwordEncoder = new PasswordEncoder();

        try {
            User user = userDAO.findUserByLogin(login);

            if(user != null && passwordEncoder.encryptPassword(password).equals(user.getPass())){

                return true;
            }
        } catch (DAOException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
