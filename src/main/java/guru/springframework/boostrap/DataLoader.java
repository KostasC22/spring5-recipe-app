package guru.springframework.boostrap;

import guru.springframework.domain.*;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
public class DataLoader implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public DataLoader(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        loadData();

    }

    private void loadData() {

        Optional<Category> categoryOptional = categoryRepository.findByDescription("Mexican");
        Optional<UnitOfMeasure> teaspoon = unitOfMeasureRepository.findByDescription("Teaspoon");
        Optional<UnitOfMeasure> tablespoon = unitOfMeasureRepository.findByDescription("Tablespoon");
        Optional<UnitOfMeasure> dash = unitOfMeasureRepository.findByDescription("Dash");

        // ------------------- Perfect Guacamole --------------- start
        Recipe perfectGuacamole = new Recipe();
        perfectGuacamole.setDescription("Perfect Guacamole");
        Set<Category> categories = new HashSet<>();
        categories.add(categoryOptional.get());
        perfectGuacamole.setCategories(categories);
        perfectGuacamole.setDifficulty(Difficulty.EASY);
        perfectGuacamole.setCookTime(10);
        perfectGuacamole.setServings(4);
        Set<Ingredient> ingredients = new HashSet<>();
        // --------------- 2 ripe avocados -------------------
        Ingredient ingredient1 = new Ingredient();
        ingredient1.setAmount(new BigDecimal("2.0"));
        ingredient1.setDescription("ripe avocados");
        ingredients.add(ingredient1);
        // --------------- 1/4 teaspoon of salt, more to taste -------------------
        Ingredient ingredient2 = new Ingredient();
        ingredient2.setAmount(new BigDecimal("0.25"));
        ingredient2.setDescription("salt, more to taste");
        ingredient2.setUnitOfMeasure(teaspoon.get());
        ingredients.add(ingredient2);
        // --------------- 1 tablespoon fresh lime juice or lemon juice -------------------
        Ingredient ingredient3 = new Ingredient();
        ingredient3.setAmount(new BigDecimal("1.00"));
        ingredient3.setDescription("fresh lime juice or lemon juice");
        ingredient3.setUnitOfMeasure(tablespoon.get());
        ingredients.add(ingredient3);
        // --------------- 2 tablespoons to 1/4 cup of minced red onion or thinly sliced green onion -------------------
        Ingredient ingredient4 = new Ingredient();
        ingredient4.setAmount(new BigDecimal("2.00"));
        ingredient4.setDescription("to 1/4 cup of minced red onion or thinly sliced green onion");
        ingredient4.setUnitOfMeasure(tablespoon.get());
        ingredients.add(ingredient4);
        perfectGuacamole.setIngredients(ingredients);
        // ------------------- Perfect Guacamole --------------- end
    }
}
