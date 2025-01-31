package com.aem.geeks.core.servlets;

import com.aem.geeks.core.models.AdvancedSlingModel;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.framework.Constants;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component(
        service = {Servlet.class},
        property = {
                Constants.SERVICE_DESCRIPTION + "=Article List Path-Based Servlet",
                "sling.servlet.methods=GET",
                "sling.servlet.paths=/bin/aemgeeks/articlelist"
        }
)
public class ArticleListServlet extends SlingAllMethodsServlet {

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        Resource resource = request.getResourceResolver().getResource("/content/aemgeeks/us/en/jcr:content/root/container/container/article_53241839");
        if (resource == null) {
            response.getWriter().write("{\"error\": \"Resource not found\"}");
            return;
        }

        AdvancedSlingModel articleModel = resource.adaptTo(AdvancedSlingModel.class);
        if (articleModel == null) {
            response.getWriter().write("{\"error\": \"Unable to adapt to Sling Model\"}");
            return;
        }

        // Construct JSON response
        JsonObject jsonResponse = new JsonObject();
        jsonResponse.addProperty("title", articleModel.getTitle());
        jsonResponse.addProperty("description", articleModel.getDescription());
        jsonResponse.addProperty("author", articleModel.getAuthor().orElse("Unknown"));
        jsonResponse.addProperty("articleCount", articleModel.getCount());

        List<JsonObject> articlesJson = articleModel.getList().stream().map(article -> {
            JsonObject articleJson = new JsonObject();
            articleJson.addProperty("title", article.getTitle());
            articleJson.addProperty("description", article.getDescription());
            articleJson.addProperty("author", article.getAuthor());
            return articleJson;
        }).collect(Collectors.toList());

        jsonResponse.add("articles", new Gson().toJsonTree(articlesJson));

        response.getWriter().write(jsonResponse.toString());
    }
}
