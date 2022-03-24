package br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.repository;

import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.entity.Users;
import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.exception.BusinessRuleException;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class UsersRepository {
    private static List<Users> usersList = new ArrayList<>();
    private AtomicInteger COUNTER = new AtomicInteger();

    public UsersRepository () {
        usersList.add(new Users(COUNTER.incrementAndGet(), "ana", "ana@ana.com", "12345678", false, "81841173029"));
        usersList.add(new Users(COUNTER.incrementAndGet(), "maicon", "maicon@maicon.com", "12345678", false, "01459367081"));
        usersList.add(new Users(COUNTER.incrementAndGet(), "augusto", "augusto@augusto.com", "12345678", true, "54809381000183"));
        usersList.add(new Users(COUNTER.incrementAndGet(), "nicolas", "nicoals@nicolas.com", "12345678", true, "76161136000145"));
    }

    public List<Users> list () {
        return usersList;
    }

    public Users getById (Integer id) throws BusinessRuleException {
        return list().stream()
                .filter(users -> users.getIdUser().equals(id))
                .findFirst()
                .orElseThrow(() -> new BusinessRuleException("Pessoa nao encontrada"));
    }

    public Users create (Users users) {
        users.setIdUser(COUNTER.incrementAndGet());
        usersList.add(users);
        return users;
    }

    public Users update (Integer id, Users users) throws BusinessRuleException {
        Users u = getById(id);
        users.setIdUser(id);
        int index = list().indexOf(u);
        list().set(index, users);
        return u;
    }

    public Users delete (Integer id) throws BusinessRuleException {
        Users u = getById(id);
        if (usersList.remove(u)) {
            return u;
        }
        return null;
    }
}
