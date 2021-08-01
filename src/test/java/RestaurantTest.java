import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;

class RestaurantTest {
    Restaurant restaurant;
    //REFACTOR ALL THE REPEATED LINES OF CODE

    @BeforeEach
    private void createMockedRestaurant() {
      LocalTime openingTime = LocalTime.parse("10:30:00");
      LocalTime closingTime = LocalTime.parse("22:00:00");
      restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
      restaurant.addToMenu("Sweet corn soup",119);
      restaurant.addToMenu("Vegetable lasagne", 269);
    }

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
    //WRITE UNIT TEST CASE HERE
      restaurant = Mockito.spy(restaurant);
      LocalTime openTime = LocalTime.parse("11:00:00");
      Mockito.when(restaurant.getCurrentTime()).thenReturn(openTime);
      assertEquals(true, restaurant.isRestaurantOpen());
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
      //WRITE UNIT TEST CASE HERE
      restaurant = Mockito.spy(restaurant);
      LocalTime closedTime = LocalTime.parse("8:00:00");
      Mockito.when(restaurant.getCurrentTime()).thenReturn(closedTime);
      assertEquals(false, restaurant.isRestaurantOpen());
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

    @Test
    public void calculate_total_cost_should_return_zero_for_empty_menu(){
      int emptyList = restaurant.calculateTotalOrderValue(new ArrayList<String>());
      assertEquals(0, emptyList);
    }

    @Test
    public void calculate_total_cost_should_return_zero_correct_value_of_selected_items(){
        List<String> orderList = new ArrayList<String>();
        orderList.add("Sweet corn soup");
        orderList.add("Vegetable lasagne");
        assertEquals(388, restaurant.calculateTotalCost(orderList));
    }
}