package htl.insy;

import htl.insy.commands.create.*;
import htl.insy.commands.delete.*;
import htl.insy.commands.get.*;
import htl.insy.commands.getAll.*;
import htl.insy.commands.update.*;

import java.util.*;

public class ChooseActionCommand implements ICommand {

    private Map<String, ICommand> commandSelection = new HashMap<>();

    @Override
    public void execute(){
        initializeMap();
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Choose an action: ");
            System.out.println("-------------------------------------");
            System.out.println("1.1: Login");
            System.out.println("1.2: Get User");
            System.out.println("1.3: Get All Users");
            System.out.println("1.4: Create User");
            System.out.println("1.5: Update User");
            System.out.println("1.6: Delete User");
            System.out.println();
            System.out.println("2.1: Get Customer");
            System.out.println("2.2: Get All Customers");
            System.out.println("2.3: Create Customer");
            System.out.println("2.4: Update Customer");
            System.out.println("2.5: Delete Customer");
            System.out.println();
            System.out.println("3.1: Get Supplier");
            System.out.println("3.2: Get All Suppliers");
            System.out.println("3.3: Create Supplier");
            System.out.println("3.4: Update Supplier");
            System.out.println("3.5: Delete Supplier");
            System.out.println();
            System.out.println("4.1: Get Category");
            System.out.println("4.2: Get All Categories");
            System.out.println("4.3: Create Category");
            System.out.println("4.4: Update Category");
            System.out.println("4.5: Delete Category");
            System.out.println();
            System.out.println("5.1: Get Item");
            System.out.println("5.2: Get All Items");
            System.out.println("5.3: Create Item");
            System.out.println("5.4: Update Item");
            System.out.println("5.5: Delete Item");
            System.out.println();
            System.out.println("6.1 Get All Orders");
            System.out.println("6.2 Get All Reservations");
            System.out.println("6.3 Get All Stocktakings");
            System.out.println("6.4 Get All ManualEntries");
            System.out.println();
            System.out.println("7.1: Create Order");
            System.out.println("7.2: Create Reservation");
            System.out.println("7.3: Create Stocktaking");
            System.out.println("7.4: Create Manual Entry");
            System.out.println();
            System.out.println("8.1 Get All Warehouse Entries");
            System.out.println("8.2 Get All Warehouse Exits");
            System.out.println();
            System.out.println("9.1: Create Warehouse Entry");
            System.out.println("9.2: Create Warehouse Exit");
            System.out.println();
            System.out.println("Q: Exit");
            System.out.println("-------------------------------------");
            System.out.println("Input: ");

            final String input = scanner.nextLine();

            if (input.equals("Q")) {
                break;
            }

            ICommand command = commandSelection.get(input);

            if (command == null) {
                System.out.println("Invalid selection");
            } else {
                if (input.equals("1.1") || input.equals("1.4")) {
                    command.execute();
                } else if (LoginCommand.userName != null) {
                    command.execute();
                } else {
                    System.out.println("Please log in");
                }
            }
        }
    }

    public void initializeMap(){
        commandSelection.put("1.1", new LoginCommand());
        commandSelection.put("1.2", new GetUserCommand());
        commandSelection.put("1.3", new GetAllUsersCommand());
        commandSelection.put("1.4", new CreateUserCommand());
        commandSelection.put("1.5", new UpdateUserCommand());
        commandSelection.put("1.6", new DeleteUserCommand());
        commandSelection.put("2.1", new GetCustomerCommand());
        commandSelection.put("2.2", new GetAllCustomersCommand());
        commandSelection.put("2.3", new CreateCustomerCommand());
        commandSelection.put("2.4", new UpdateCustomerCommand());
        commandSelection.put("2.5", new DeleteCustomerCommand());
        commandSelection.put("3.1", new GetSupplierCommand());
        commandSelection.put("3.2", new GetAllSuppliersCommand());
        commandSelection.put("3.3", new CreateSupplierCommand());
        commandSelection.put("3.4", new UpdateSupplierCommand());
        commandSelection.put("3.5", new DeleteSupplierCommand());
        commandSelection.put("4.1", new GetCategoryCommand());
        commandSelection.put("4.2", new GetAllCategoriesCommand());
        commandSelection.put("4.3", new CreateCategoryCommand());
        commandSelection.put("4.4", new UpdateCategoryCommand());
        commandSelection.put("4.5", new DeleteCategoryCommand());
        commandSelection.put("5.1", new GetItemCommand());
        commandSelection.put("5.2", new GetAllItemsCommand());
        commandSelection.put("5.3", new CreateItemCommand());
        commandSelection.put("5.4", new UpdateItemCommand());
        commandSelection.put("5.5", new DeleteItemCommand());
        commandSelection.put("6.1", new GetAllOrdersCommand());
        commandSelection.put("6.2", new GetAllReservationsCommand());
        commandSelection.put("6.3", new GetAllStocktakingsCommand());
        commandSelection.put("6.4", new GetAllManualEntriesCommand());
        commandSelection.put("7.1", new CreateOrderCommand());
        commandSelection.put("7.2", new CreateReservationCommand());
        commandSelection.put("7.3", new CreateStocktakingCommand());
        commandSelection.put("7.4", new CreateManualEntryCommand());
        commandSelection.put("8.1", new GetAllWarehouseEntriesCommand());
        commandSelection.put("8.2", new GetAllWarehouseExitsCommand());
        commandSelection.put("9.1", new CreateWarehouseEntryCommand());
        commandSelection.put("9.2", new CreateWarehouseExitCommand());
    }
}
