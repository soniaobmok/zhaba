package application.Seat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/seats")
public class SeatController {

    @Autowired
    private SeatService seatService;

    @GetMapping("/{id}")  // not necessary but works
    public String GetById(@PathVariable int id) throws SQLException {
        Seat s = seatService.GetById(id);
        System.out.println("FIND custoceatmer BY ID (" + s.getId() + "): " + s.getRowNumber() + " " + s.getSeatNumber());
        return s.toString();    }

    @GetMapping("/") // works
    public List<String> getAll() throws SQLException {
        List<Seat> seats = seatService.GetAll();
        List<String> list = new ArrayList<>();
        for (Seat item : seats) {
            list.add(item.toString());
        }
        return list;
    }

    @GetMapping("/free")  // works
    public List<String> GetFreeSeats() throws SQLException {
        List<Seat> seats = seatService.seatRepo.findByIsFree(true);
        List<String> list = new ArrayList<>();
        for (Seat item : seats) {
            list.add(item.toString());
        }
        return list;
    }

    @PostMapping("/freeseat/{id}")
    public void FreeSeat(@PathVariable int id) throws SQLException {
        Seat s = seatService.GetById(id);
        s.setFree(true);
        seatService.Update(s);
    }

    @PostMapping("/occupyseat/{id}")
    public void OccupySeat(@PathVariable int id) throws SQLException {
        Seat s = seatService.GetById(id);
        s.setFree(false);
        seatService.Update(s);
    }

    @PostMapping("/") // not necessary, I won't do this shit
    public Seat Add(@RequestBody Seat seat) throws SQLException {
        return seatService.Add(seat);
    }
}
