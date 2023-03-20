package com.wang.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author feige
 * @version v1.0
 * @date 2023-03-20-20:22
 * @description
 */
public class ServletUtil {

    /**
     * 将字符串渲染到客户端
     *
     * @param response 渲染对象
     * @param string 待渲染的字符串
     */
    public static void renderString(HttpServletResponse response, String string)
    {
        try
        {
            response.setStatus(200);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(string);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}
