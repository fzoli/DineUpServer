package com.dineup.ejb;

import com.dineup.builder.*;
import com.dineup.dom.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.ejb.Singleton;

@Singleton
public class MockRestaurantDataSource implements RestaurantDataSource {

    private final String[] currencies = {"EUR", "USD", "HUN"};
    private final String[] addresses = {"Cím egy", "Cím kettő", "Cím három"};
    private final String[] restaurantTypes = {"RestaurantA", "RestaurantB"};
    private final String[] extraTypes = {"CHOOSE_ONE", "CHOOSE_TWO"};
    private final String[] daysEn = {"Monday", "Tuesday", "Wednesday"};

    private final List<Restaurant> restaurants;

    public MockRestaurantDataSource() {
        restaurants = generateRestaurants();
    }

    @Override
    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    private List<Restaurant> generateRestaurants() {
        List<Restaurant> restaurantList = new ArrayList<>();
        for (int restaurantId = 0; restaurantId < 100; restaurantId++) {
            RestaurantBuilder.Builder restaurantBuilder = generateRestaurant(restaurantId);
            List<Category> categoryList = new ArrayList<>();
            for (int categoryId = 0; categoryId < 10; categoryId++) {
                CategoryBuilder.Builder categoryBuilder = generateCategory(categoryId);
                List<Food> foodList = new ArrayList<>();
                for (int foodId = 0; foodId < 10; foodId++) {
                    FoodBuilder.Builder foodBuilder = generateFood(foodId);
                    List<Extra> extraList = new ArrayList<>();
                    for (int extraId = 0; extraId < 2; extraId++) {
                        ExtraBuilder.Builder extraBuilder = generateExtra(extraId);
                        List<Option> optionList = new ArrayList<>();
                        for (int optionId = 0; optionId < 5; optionId++) {
                            OptionBuilder.Builder optionBuilder = generateOption(optionId);
                            optionList.add(optionBuilder.build());
                        }
                        extraList.add(extraBuilder.options(Collections.unmodifiableList(optionList)).build());
                    }
                    foodList.add(foodBuilder.extras(Collections.unmodifiableList(extraList)).build());
                }
                categoryList.add(categoryBuilder.foods(Collections.unmodifiableList(foodList)).build());
            }
            restaurantList.add(restaurantBuilder.categories(Collections.unmodifiableList(categoryList)).build());
        }
        return Collections.unmodifiableList(restaurantList);
    }

    private RestaurantBuilder.Builder generateRestaurant(int id) {
        return RestaurantBuilder.newBuilder()
                .id(id)
                .address(addresses[id % addresses.length])
                .coordinate(new Coordinate(3.14 * id % 90, 2 * 3.14 * id % 180))
                .defaultCurrency(currencies[id % currencies.length])
                .photoUrl(String.format("http://dummyimage.com/600x400/000/fff.jpg&text=Restaurant%s", id))
                .rating(id / 2)
                .type(restaurantTypes[id % restaurantTypes.length])
                .locales((List) Collections.singletonList(RestaurantLocaleBuilder.newBuilder()
                        .name(String.format("Restaurant name %s", id))
                        .description(String.format("Restaurant description %s", id))
                        .languageCode("en")
                        .openHours(daysEn[id % daysEn.length] + " 24h")
                        .build()));
    }

    private CategoryBuilder.Builder generateCategory(int id) {
        return CategoryBuilder.newBuilder()
                .id(id)
                .photoUrl(String.format("http://dummyimage.com/600x400/000/fff.jpg&text=Category%s", id))
                .locales((List)Collections.singletonList(CategoryLocaleBuilder.newBuilder()
                        .languageCode("en")
                        .name(String.format("Category name %s", id))
                        .build()));
    }

    private FoodBuilder.Builder generateFood(int id) {
        return FoodBuilder.newBuilder()
                .id(id)
                .photoUrl(String.format("http://dummyimage.com/600x400/000/fff.jpg&text=Food%s", id))
                .prices(generatePrices())
                .locales((List)Collections.singletonList(FoodLocaleBuilder.newBuilder()
                        .name(String.format("Food %s", id))
                        .languageCode("en")
                        .description(String.format("Food description %s", id))
                        .build()));
    }

    private OptionBuilder.Builder generateOption(int id) {
        return OptionBuilder.newBuilder()
                .prices(generatePrices())
                .locales((List)Collections.singletonList(OptionLocaleBuilder.newBuilder()
                        .name(String.format("Option %s", id))
                        .languageCode("en")
                        .build()));
    }

    private ExtraBuilder.Builder generateExtra(int id) {
        return ExtraBuilder.newBuilder()
                .type(extraTypes[id % extraTypes.length])
                .locales((List)Collections.singletonList(ExtraLocaleBuilder.newBuilder()
                        .name(String.format("Extra %s", id))
                        .languageCode("en")
                        .build()));
    }

    private List<Price> generatePrices() {
        List<Price> priceList = new ArrayList<>();
        for (int i = 0; i < currencies.length; i++) {
            priceList.add(PriceBuilder.newBuilder()
                    .amount(new BigDecimal((i + 1) * 100.7))
                    .currency(currencies[i % currencies.length])
                    .build());
        }
        return Collections.unmodifiableList(priceList);
    }

}
