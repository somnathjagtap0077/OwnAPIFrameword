package Api.endpoints;

public class Routes {
	
	
	public static String base_url="https://petstore.swagger.io/v2";
	
	//user endpoints {username} is path parameters
	public static String post_url=base_url+"/user";
	public static String get_url=base_url+"/user/{username}";
	public static String update_url=base_url+"/user/{username}";
	public static String delete_url=base_url+"/user/{username}";
	
	
}
