import java.util.*;

public class Main {
    static ArrayList<String> potion_Names = new ArrayList<>();
    static ArrayList<List<List<String>>> potionRecipes = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //System.out.println("Enter number of recipes:");
        int nb_of_recipes = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < nb_of_recipes; i++) {
            //System.out.println("Enter recipe" + (i + 1) + ":");


            String line = scanner.nextLine().trim();
            String[] seperation = line.split("=");

            String potion = seperation[0].trim();
            String right_side_of_second_input = seperation[1].trim();
            String[] individual_ingredient = right_side_of_second_input.split("\\+");


            ArrayList<String> ingredientList = new ArrayList<>();
            for (int added_ingredients = 0; added_ingredients < individual_ingredient.length; added_ingredients++) {
                String ingredient = individual_ingredient[added_ingredients];
                ingredientList.add(ingredient);

            }

            int index_of_potions = indexOfPotion(potion);
            if (index_of_potions == -1) {
                potion_Names.add(potion);
                ArrayList<List<String>> all_recipes_for_one_specific_potion = new ArrayList<>();
                all_recipes_for_one_specific_potion.add(ingredientList);
                potionRecipes.add(all_recipes_for_one_specific_potion);
            } else {
                potionRecipes.get(index_of_potions).add(ingredientList);
            }
        }

        // THE LAST LINE OF INTPut is the target potion name
        //System.out.println("Enter target potion:");
        String target_potion_to_craft = scanner.nextLine();
        scanner.close();

        int minimum_cost = minimumCost(target_potion_to_craft);
        System.out.print(minimum_cost);

    }

    // -1 means that no potion
    static int indexOfPotion(String name) {
        for (int i = 0; i < potion_Names.size(); i++) {
            if (potion_Names.get(i).equals(name)) return i;
        }

        return -1;
    }


    static int minimumCost(String name) {
        int index_of_potions = indexOfPotion(name);
        if (index_of_potions == -1)
            return 0;  // just ing, not potion so cost = 0

        int best_cost = Integer.MAX_VALUE;

        List<List<String>> allRecipes = potionRecipes.get(index_of_potions);
        for (int a = 0; a < allRecipes.size(); a++) {
            List<String> recipe = allRecipes.get(a);

            int cost = recipe.size() - 1;

            for (int i = 0; i < recipe.size(); i++) {
                String ingredients_of_recursion_potion = recipe.get(i);
                cost += minimumCost(ingredients_of_recursion_potion);
            }

            if (cost < best_cost) {
                best_cost = cost;
            }
        }


        return best_cost;
    }

}
