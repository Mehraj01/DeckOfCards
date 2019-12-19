
import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.HashSet;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
 
public class Shuffle {

	@Test 
	public static String Shuffle_the_Cards() {
RestAssured.baseURI="https://deckofcardsapi.com/";
		
		Response res=given().
		       when().
		       get("api/deck/new/shuffle/?deck_count=1").
		       then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
		      and().
		       header("Server","cloudflare").log().all().
		       extract().response();
		
		String str = res.asString();
		JsonPath jp = new JsonPath(str);
		String deck_id = jp.get("deck_id");
		
		//System.out.println(deck_id);
return deck_id;
	}
	@Test
	public static void Draw_a_Cards() {
		RestAssured.baseURI="https://deckofcardsapi.com/";
				
				Response res=given().
				       when().
				       get("api/deck/"+Shuffle_the_Cards()+"/draw/?count=5").
				       then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
				      and().
				       header("Server","cloudflare").log().all().
				       extract().response();
				
				String str = res.asString();
				JsonPath jp = new JsonPath(str);
				int cardSize = jp.get("cards.size()");
				
				ArrayList<String>list = new ArrayList<String>();
				
				for(int i = 0; i<cardSize; i++) {
					
					
					 String value = jp.get("cards["+i+"].value");
					String  suit = jp.get("cards["+i+"].suit");
					 list.add(value);
					 list.add(suit);
					 
				}
				
				System.out.println( list.toString());

			}
	

}
