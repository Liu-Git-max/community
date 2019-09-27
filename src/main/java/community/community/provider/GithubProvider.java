package community.community.provider;

import com.alibaba.fastjson.JSON;
import community.community.dto.AccessTokenDTO;
import community.community.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GithubProvider {

    public String getAccessToken(AccessTokenDTO accessTokenDTO){
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        Request.Builder builder = new Request.Builder();
        builder.url("https://github.com/login/oauth/access_token");
        builder.post(body);
        Request request = builder
                .build();
        try (Response response = client.newCall(request).execute()) {
          String string = response.body().string();
          String token = string.split("&")[0].split("=")[1];
          return token;
        } catch (Exception e) {
              e.printStackTrace();
        }
        return null;
    }

    public GithubUser getUser(String accessToken) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token="+accessToken)
                .build();
        Response response = client.newCall(request).execute();
        String string = response.body().string();
        GithubUser githubUser = JSON.parseObject(string, GithubUser.class);
        return githubUser;

    }
}
