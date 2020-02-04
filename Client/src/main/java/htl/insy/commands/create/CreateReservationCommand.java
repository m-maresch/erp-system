package htl.insy.commands.create;

import htl.insy.HttpExtensions;
import htl.insy.ICommand;
import htl.insy.JSONObjectFactory;
import org.json.JSONObject;

public class CreateReservationCommand implements ICommand {

    @Override
    public void execute() {
        JSONObject json = JSONObjectFactory.getReservation();

        HttpExtensions.post("/reservation", json, r -> System.out.println("Reservation created"));
    }
}
