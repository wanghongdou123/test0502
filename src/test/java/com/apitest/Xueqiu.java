package com.apitest;

import io.restassured.http.ContentType;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static io.restassured.http.ContentType.JSON;
import static io.restassured.matcher.ResponseAwareMatcher.*;
// 断言库
import static org.hamcrest.Matchers.*;

public class Xueqiu {
    @Test
    public void testSearch(){
        //        设置证书
        useRelaxedHTTPSValidation();
        given()
                .log().all().proxy("127.0.0.1",7778)
                .queryParam("code","sogo")
                .header("Cookie ","xq_a_token=2ee68b782d6ac072e2a24d81406dd950aacaebe3; xqat=2ee68b782d6ac072e2a24d81406dd950aacaebe3; xq_r_token=f9a2c4e43ce1340d624c8b28e3634941c48f1052; xq_id_token=eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJ1aWQiOi0xLCJpc3MiOiJ1YyIsImV4cCI6MTU4NzUyMjY2MSwiY3RtIjoxNTg1MzExNDAyNDM4LCJjaWQiOiJkOWQwbjRBWnVwIn0.auV8uEIz96wKpEK3CfHVE3SFPHylxfk1Ct9_h2qEExgUztqyD57kDiD0rDQ2jgi3f6MUxFRrLxs13cH3p7ioE9Wtwd7GHdUad5sQFG1RCi_SReU7W-sY6dZvVAEWr7pU2rXo754MSTOuhaIcYFSawvP6kLnKRyAgoa-ctsUFn2x6Nv5rR4fjp7Fg-5UoeXBYJZRsSjHi33EcSWwGyMRoCUfMtAkqwN8AJ56GKFu6BoWq60ldkeMkZOvqR0YlX5mWbXidxCfhM5voyZXhOuH9sWSPUCKMe2KZIyJFnFcOUnMYqXB9UtbmzII1Et2ZqErE3mmbPnmdf-BbdIcUuNfHOA; u=911585311438980; device_id=24700f9f1986800ab4fcc880530dd0ed; aliyungf_tc=AQAAAPLQ610ANQwAHkASbyfZ07OFTYw3; acw_tc=2760822715853220008128095e5fc987995fabd3b0b0a028298e6247532617")
        .when()
                .get("https://xueqiu.com/stock/search.json")
        .then()
                .log().all()
                .statusCode(200)
                .body("stocks.name",hasItem("搜狗"))
                .body("stocks.code",hasItem("SOGO"))
                .body("stocks.find {it.code == 'SOGO' }.name", equalTo("搜狗"));
    }


//    @Test
//    public void testLogin(){
//        useRelaxedHTTPSValidation();
//        String code = given()
//                .log().all().proxy("127.0.0.1",7778)
//                .header("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36")
//                .formParam("username","18401504837")
//                .formParam("password","hd123456")
//                .cookie("u","731585377387070")
//        .when()
//                .post("https://xueqiu.com/snb/provider/login")
//        .then()
//                .log().all()
//                .statusCode(200);
//    }




    @Test
    public void testPostJson(){
        useRelaxedHTTPSValidation();
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("a",1);
        map.put("b","testerhome");
        map.put("array",new String[] {"111", "2222"});
        given()
                .log().all().proxy("127.0.0.1",7778)
                .contentType(JSON)
                .body(map)
        .when()
                .post("http://www.baidu.com")
        .then()
                .log().all()
                .statusCode(200);
//                .body(hasXPath("//*[@id='twxt']"));

    }

}
