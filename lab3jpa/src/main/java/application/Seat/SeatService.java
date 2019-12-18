package application.Seat;

import application.Default.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service("SeatService")
public class SeatService implements IService<Seat> {
    @Autowired
    public SeatRepository seatRepo;

    public SeatService() {}

    @Override
    public Seat Add(Seat seat) throws SQLException {
        seat.setId(GetAll().size() + 1);
        return seatRepo.save(seat);
    }

    @Override
    public void Delete(Seat seat) throws SQLException {
        seatRepo.delete(seat);
    }

    @Override
    public void DeleteById(int id) throws SQLException {
        seatRepo.deleteById(id);
    }

    @Override
    public void Update(Seat seat) throws SQLException {
        Seat s = seatRepo.findById(seat.getId()).orElse(null);
        s.setFree(seat.getFree());
        seatRepo.save(s);
    }

    @Override
    public List<Seat> GetAll() throws SQLException {
        List<Seat> list = new ArrayList<>();
        seatRepo.findAll().forEach(list::add);
        return list;
    }

    @Override
    public Seat GetById(int id) throws SQLException {
        return seatRepo.findById(id).orElse(null);
    }
}
