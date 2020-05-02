package com.apitest;

import org.junit.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.http.ContentType.JSON;
import static io.restassured.matcher.ResponseAwareMatcher.*;
// 断言库
import static org.hamcrest.Matchers.*;

public class Demo {
    @Test
    public void testJson(){
        given()
                .log().all()
        .when()
                .get("http://127.0.0.1:8000/testerhome.json")
        .then()
                .statusCode(200)
                .body("store.book.category",hasItems("reference","fiction"))
                .body("store.book[0].author",equalTo("Nigel Rees"))
                .body("store.book[-1].author",equalTo("J. R. R. Tolkien"))
                .body("store.book.find {book -> book.price == 8.99f}.price",equalTo(8.99f));
    }

    @Test
    public void testXml(){
        given()
                .log().all()
        .when()
                .get("http://127.0.0.1:8000/testerhome.xml")
        .then()
                .statusCode(200)
                .body("shopping.category.item[0].name", equalTo("Chocolate"))
                .body("shopping.category.item.size()", equalTo(5))
                .body("shopping.category.findAll {it.@type == 'groceries'}.size()", equalTo(1))
                .body("shopping.category.item.findAll {it.price == 20}.name", equalTo("Coffee"))
                .body("**.findAll {it.price == 20}.name", equalTo("Coffee"));

    }
}
