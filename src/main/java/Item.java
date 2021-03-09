import java.util.ArrayList;
import java.util.List;
import java.util.function.DoubleUnaryOperator;

public class Item {
    private String name;

    public void setPrice(int price) {
        this.price = price;
    }

    private int price;
    private List<Item> menu= new ArrayList<Item>();

    public Item(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }
    public int getPrice() {
        return price;
    }
    @Override
    public String toString(){
        return  name + ":"
                + price
                + "\n"
                ;
    }

}
