package utils;

public class AliExpressConstants {
    public static final String BASE_URL = "https://www.aliexpress.com";
    public static final String LOGIN_URL = BASE_URL + "/login.html";
    public static final String CART_URL = BASE_URL + "/shopcart/shopcart.htm";
    public static final String ORDERS_URL = BASE_URL + "/p/order/list.html";
    public static final String WISHLIST_URL = BASE_URL + "/wish-list/wish_list.htm";
    
    // API endpoints
    public static final String API_BASE_URL = "https://api.aliexpress.com/v2";
    public static final String SEARCH_API = API_BASE_URL + "/search";
    public static final String PRODUCT_API = API_BASE_URL + "/product";
    public static final String ORDER_API = API_BASE_URL + "/order";
    public static final String LOGISTICS_API = API_BASE_URL + "/logistics";
    
    // Timeouts
    public static final int DEFAULT_TIMEOUT = 10;
    public static final int PAGE_LOAD_TIMEOUT = 30;
    public static final int SCRIPT_TIMEOUT = 30;
    
    // Messages
    public static final String PRODUCT_NOT_FOUND = "Ürün bulunamadı";
    public static final String CART_EMPTY = "Sepetiniz boş";
    public static final String LOGIN_REQUIRED = "Lütfen giriş yapın";
} 