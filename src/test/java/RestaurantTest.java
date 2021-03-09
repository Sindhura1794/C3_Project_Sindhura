import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
    //REFACTOR ALL THE REPEATED LINES OF CODE
    LocalTime openingTime = LocalTime.parse("10:30:00");
    LocalTime closingTime = LocalTime.parse("22:00:00");
    Restaurant restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);

    private void setMenuItems() {
        restaurant.addToMenu("Sweet corn soup", 119);
        restaurant.addToMenu("Vegetable lasagne", 269);
    }
    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
        boolean isOpen;
        isOpen = restaurant.isRestaurantOpen();
        assertTrue(isOpen);
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
        boolean isOpen;
        isOpen = restaurant.isRestaurantOpen();
        assertFalse(isOpen);
    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){
        setMenuItems();
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
        setMenuItems();
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
        setMenuItems();
        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    //<<<<<<<<<<<<<<<<<<<<<<<TOTAL ORDER BILL>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    //When itemName is passed, it should return corresponding price for that item
    //It should fail when price of other itemName is returned
    //The sum of no.of item prices should be correct
    //It should fail if sum of no.of items is incorrect

    @Test
    public void getOrderTotal_should_return_order_value_when_name_of_the_item_is_given_in_string_format(){
        setMenuItems();

        List<String> selectedItemName=new ArrayList<String>();
        selectedItemName.add("Sweet corn soup");
        selectedItemName.add("Vegetable lasagne");

        Double expectedRes=0.0;

        for(int i=0;i<restaurant.getMenu().size();i++){
            Item item=(Item) restaurant.getMenu().get(i);
            if(item.getName().equals(selectedItemName.get(i))){
                expectedRes = expectedRes+item.getPrice();
            }
        }
        Double actualRes=restaurant.getOrderTotal(selectedItemName);

        assertEquals(expectedRes,actualRes);
    }
    //<<<<<<<<<<<<<<<<<<<<<<<TOTAL ORDER BILL>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

}