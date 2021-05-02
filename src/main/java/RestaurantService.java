import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class RestaurantService {
    private static List<Restaurant> restaurants = new ArrayList<>();

    private List<Item> userSelectedItems = new ArrayList<>();
    private int totalCost = 0;
    private Restaurant selectedRestaurant;

    public Restaurant findRestaurantByName(String restaurantName) throws restaurantNotFoundException {
        for (Restaurant restaurant:
                restaurants) {
            if(restaurant.getName()==restaurantName)
                return restaurant;
        }
        throw new restaurantNotFoundException("Error: Restaurant could not be found");
        //DELETE ABOVE STATEMENT AND WRITE CODE HERE
    }


    public Restaurant addRestaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        Restaurant newRestaurant = new Restaurant(name, location, openingTime, closingTime);
        restaurants.add(newRestaurant);
        return newRestaurant;
    }

    public Restaurant removeRestaurant(String restaurantName) throws restaurantNotFoundException {
        Restaurant restaurantToBeRemoved = findRestaurantByName(restaurantName);
        restaurants.remove(restaurantToBeRemoved);
        return restaurantToBeRemoved;
    }

    // whenever the user will click on add item this will update the list and total cost
    public Item addItemToUserSelectedItemList(Item itemName){
        return null;
    }

    public Item removeItemfromUserSeletedItemList(Item itemName){
        return null;
    }

    public int updateTotalCostToUser(Item newlyAddedItem){
        return -1;
    }

    public Restaurant selectRestaurant(String restaurantName) throws restaurantNotFoundException {
        this.selectedRestaurant = this.findRestaurantByName(restaurantName);
        return this.selectedRestaurant;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }
    public List<Item> getUserSelectedItems(){return userSelectedItems;}
    public void resetUserSelectedItems(){userSelectedItems = null;totalCost = -1;}
    public int getTotalCost(){return totalCost;}
    public Restaurant getSelectedRestaurant(){return this.selectedRestaurant;}


}
