package com.nfit.yaoliusan.myblog.web;


import com.nfit.yaoliusan.myblog.bean.Comment;
import com.nfit.yaoliusan.myblog.bean.Post;
import com.nfit.yaoliusan.myblog.dao.CommentDAO;
import com.nfit.yaoliusan.myblog.dao.PostDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


@WebServlet("/post")
public class PostServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        try {
            PostDAO postDAO = new PostDAO();
            CommentDAO commentDAO = new CommentDAO();

            Post post = postDAO.getPostById(id);
            List<Comment> comments = commentDAO.getCommentsByPostId(id);

            req.setAttribute("post", post);
            req.setAttribute("comments", comments);

            req.getRequestDispatcher("/jsp/post.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", e.getLocalizedMessage());
            req.getRequestDispatcher("/jsp/error.jsp").forward(req, resp);
        }
    }
}
