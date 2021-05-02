import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import java.time.LocalTime;
<<<<<<< HEAD
        =======
import java.util.List;
>>>>>>> fadf508 (New changelist)

import static org.junit.jupiter.api.Assertions.*;


class RestaurantServiceTest {

    //REFACTOR ALL THE REPEATED LINES OF CODE

    //REFACTOR ALL THE REPEATED LINES OF CODE

    public RestaurantService getRestaurantObject(){
        RestaurantService service = new RestaurantService();
        Restaurant restaurant;
        LocalTime openingTime, closingTime;
        openingTime = LocalTime.parse("08:00:00");
        closingTime = LocalTime.parse("22:00:00");
        restaurant = service.addRestaurant("BillaJi's Cafe","Varanasi",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
        restaurant.addToMenu("Potato fry",130);
        restaurant.addToMenu("Vanilla Cake",350);
        restaurant.addToMenu("Banana Pancakes",120);
        restaurant.addToMenu("Cold Strawberry Smoothie",70);
        restaurant.addToMenu("Punjabi Mint Jaljeera Drink",80);
        restaurant.addToMenu("Mango Yogurt Smoothie",100);
        openingTime = LocalTime.parse("10:30:00");
        closingTime = LocalTime.parse("22:00:00");
        restaurant = service.addRestaurant("Amelie's Cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        restaurant.addToMenu("Potato fry",130);
        restaurant.addToMenu("Vanilla Cake",350);
        restaurant.addToMenu("Banana Pancakes",120);
        restaurant.addToMenu("Cold Strawberry Smoothie",70);
        restaurant.addToMenu("Punjabi Mint Jaljeera Drink",80);
        restaurant.addToMenu("Mango Yogurt Smoothie",100);

        openingTime = LocalTime.parse("10:30:00");
        closingTime = LocalTime.parse("22:00:00");
        restaurant = service.addRestaurant("Bobcat's Cafe","Bangalore",openingTime,closingTime);
        restaurant.addToMenu("Mangalorean Buns",119);
        restaurant.addToMenu("Mysore pak", 269);

        restaurant.addToMenu("Potato fry",130);
        restaurant.addToMenu("Vanilla Cake",350);
        restaurant.addToMenu("Banana Pancakes",120);
        restaurant.addToMenu("Cold Strawberry Smoothie",70);
        restaurant.addToMenu("Punjabi Mint Jaljeera Drink",80);
        restaurant.addToMenu("Mango Yogurt Smoothie",100);
        return service;
    }
    Restaurant restaurant;
    RestaurantService service = getRestaurantObject();
    RestaurantService spyRestaurant = Mockito.spy(service);

    //>>>>>>>>>>>>>>>>>>>>>>SEARCHING<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void searching_for_existing_restaurant_should_return_expected_restaurant_object() throws restaurantNotFoundException {
        //WRITE UNIT TEST CASE HERE
        // Bobcat's cafe is an existing restaurant. we will search it.
        restaurant = service.findRestaurantByName("Bobcat's Cafe");
        assertEquals(true, restaurant.getName().equals("Bobcat's Cafe"));

        restaurant = service.findRestaurantByName("Amelie's Cafe");
        assertEquals(true, restaurant.getName().equals("Amelie's Cafe"));

        restaurant = service.findRestaurantByName("BillaJi's Cafe");
        assertEquals(true, restaurant.getName().equals("BillaJi's Cafe"));



    }

    //You may watch the video by Muthukumaran on how to write exceptions in Course 3: Testing and Version control: Optional content
    @Test
    public void searching_for_non_existing_restaurant_should_throw_exception() throws restaurantNotFoundException {
        //WRITE UNIT TEST CASE HERE

        assertThrows(restaurantNotFoundException.class,()->service.findRestaurantByName("Pantry d'or"));
        assertThrows(restaurantNotFoundException.class,()->service.findRestaurantByName("BillaJi's C"));
        assertThrows(restaurantNotFoundException.class,()->service.findRestaurantByName("Amelies d'or"));

    }
    //<<<<<<<<<<<<<<<<<<<<SEARCHING>>>>>>>>>>>>>>>>>>>>>>>>>>




    //>>>>>>>>>>>>>>>>>>>>>>ADMIN: ADDING & REMOVING RESTAURANTS<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void remove_restaurant_should_reduce_list_of_restaurants_size_by_1() throws restaurantNotFoundException {

        int initialNumberOfRestaurants = service.getRestaurants().size();
        service.removeRestaurant("Amelie's Cafe");
        assertEquals(initialNumberOfRestaurants-1, service.getRestaurants().size());
    }

    @Test
    public void removing_restaurant_that_does_not_exist_should_throw_exception() throws restaurantNotFoundException {

        assertThrows(restaurantNotFoundException.class,()->service.removeRestaurant("Pantry d'or"));
    }

    @Test
    public void add_restaurant_should_increase_list_of_restaurants_size_by_1(){

        int initialNumberOfRestaurants = service.getRestaurants().size();
        service.addRestaurant("Pumpkin Tales","Chennai",LocalTime.parse("12:00:00"),LocalTime.parse("23:00:00"));
        assertEquals(initialNumberOfRestaurants + 1,service.getRestaurants().size());
    }
    //<<<<<<<<<<<<<<<<<<<<ADMIN: ADDING & REMOVING RESTAURANTS>>>>>>>>>>>>>>>>>>>>>>>>>>

    @Test
    public void if_user_selects_an_item_then_userselectedlist_length_should_increease_by_1(){
        int initialNumberOfItems = service.getUserSelectedItems().size();
        Item newItem = new Item("PineApple SHake", 70);
        service.addItemToUserSelectedItemList(newItem);
        assertEquals(initialNumberOfItems + 1,service.getUserSelectedItems().size());
    }

    @Test
    public void if_an_item_is_added_then_the_cost_should_get_increased_by_the_amount_of_the_price_of_the_item_added() throws restaurantNotFoundException {
        Restaurant selectedRestaurant = service.selectRestaurant("BillaJi's Cafe");
        Item newItem = new Item("Mango SHake", 50);
        int initalTotalCost = service.getTotalCost();
        service.addItemToUserSelectedItemList(newItem);
        assertEquals(initalTotalCost+50,service.getTotalCost());
    }

    @Test
    public void if_no_item_is_selected_then_the_total_cost_should_be_zero(){
        service.resetUserSelectedItems();
        assertEquals(0,service.getTotalCost());
    }

    @Test
    public void if_an_item_is_deselected_then_the_cost_should_get_reduced_by_the_amount_of_the_price_of_the_item_added() throws restaurantNotFoundException{
        Restaurant selectedRestaurant = service.selectRestaurant("BillaJi's Cafe");
        Item newItem = new Item("Mango SHake", 50);
        int initalTotalCost = service.getTotalCost();
        service.removeItemfromUserSeletedItemList(newItem);
        assertEquals(initalTotalCost+50,service.getTotalCost());
    }



    //<<<<<<<<<<<<<<<<<<<<<<<<<<User: Getting the Total Order Cost>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

}