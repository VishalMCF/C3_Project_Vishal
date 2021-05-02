import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {

    //REFACTOR ALL THE REPEATED LINES OF CODE
    public Restaurant getRestaurantObject(){
        Restaurant restaurant;
        LocalTime openingTime = LocalTime.parse("08:00:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant = new Restaurant("BillaJi Restaurant","Varanasi",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
        return restaurant;
    }

    Restaurant restaurant = getRestaurantObject();
    Restaurant spyRestaurant = Mockito.spy(restaurant);

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE

        // Calling the methods and checking assertions
        Mockito.when(spyRestaurant.getCurrentTime()).thenReturn(LocalTime.parse("08:00:00"));
        assertEquals(true,restaurant.isRestaurantOpen());
        Mockito.when(spyRestaurant.getCurrentTime()).thenReturn(LocalTime.parse("11:00:00"));
        assertEquals(true,restaurant.isRestaurantOpen());
        Mockito.when(spyRestaurant.getCurrentTime()).thenReturn(LocalTime.parse("15:00:00"));
        assertEquals(true,restaurant.isRestaurantOpen());
        Mockito.when(spyRestaurant.getCurrentTime()).thenReturn(LocalTime.parse("21:59:59"));
        assertEquals(true,restaurant.isRestaurantOpen());


    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE


        // Calling the methods and checking assertions
        Mockito.when(spyRestaurant.getCurrentTime()).thenReturn(LocalTime.parse("07:00:00"));
        assertEquals(false, spyRestaurant.isRestaurantOpen());
        Mockito.when(spyRestaurant.getCurrentTime()).thenReturn(LocalTime.parse("02:30:40"));
        assertEquals(false, spyRestaurant.isRestaurantOpen());
        Mockito.when(spyRestaurant.getCurrentTime()).thenReturn(LocalTime.parse("22:00:00"));
        assertEquals(false, spyRestaurant.isRestaurantOpen());
        Mockito.when(spyRestaurant.getCurrentTime()).thenReturn(LocalTime.parse("23:59:59"));
        assertEquals(false, spyRestaurant.isRestaurantOpen());

    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }


    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }


    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {

        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
}