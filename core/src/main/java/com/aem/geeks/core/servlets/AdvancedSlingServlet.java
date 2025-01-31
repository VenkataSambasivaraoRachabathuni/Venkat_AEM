package com.aem.geeks.core.servlets;

import com.aem.geeks.core.models.AdvancedSlingModel;
import com.aem.geeks.core.models.AdvancedSlingModel.Article;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.models.factory.ModelFactory;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Component(service = Servlet.class)
@SlingServletResourceTypes(
        resourceTypes = "aemgeeks/components/content/article", // Same as Sling Model
        methods = "GET",
        extensions = "json"
)
public class AdvancedSlingServlet extends SlingAllMethodsServlet {

    @Reference
    private ModelFactory modelFactory;

    private static final Gson GSON = new Gson(); // Using GSON for JSON conversion

    public AdvancedSlingServlet(ModelFactory modelFactory) {
        this.modelFactory = modelFactory;
    }

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Adapt request to the Sling Model
        AdvancedSlingModel model = request.adaptTo(AdvancedSlingModel.class);

        if (model != null) {
            // Create JSON response
            JsonObject jsonResponse = new JsonObject();
            jsonResponse.addProperty("title", model.getTitle());
            jsonResponse.addProperty("description", model.getDescription());

            // Optional value handling
            Optional<String> authorOpt = model.getAuthor();
            jsonResponse.addProperty("author", authorOpt.orElse("Unknown"));

            jsonResponse.addProperty("articleCount", model.getCount());

            // Articles array
            JsonArray articlesArray = new JsonArray();
            List<Article> articles = model.getList();
            for (Article article : articles) {
                JsonObject articleJson = new JsonObject();
                articleJson.addProperty("title", article.getTitle());
                articleJson.addProperty("description", article.getDescription());
                articleJson.addProperty("author", article.getAuthor());
                articlesArray.add(articleJson);
            }

            jsonResponse.add("articles", articlesArray);

            // Write response
            response.getWriter().write(GSON.toJson(jsonResponse));
        } else {
            response.getWriter().write("{\"error\": \"Resource cannot be adapted to AdvancedSlingModel\"}");
        }
    }
}
