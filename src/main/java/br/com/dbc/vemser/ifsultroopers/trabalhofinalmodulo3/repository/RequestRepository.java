package br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.repository;

import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.dto.request.RequestUpdateDTO;
import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.entity.Category;
import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.entity.Request;
import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.exception.BusinessRuleException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class RequestRepository {

    public static List<Request> list = new ArrayList<>();
    public static List<Request> closedList = new ArrayList<>();
    public static AtomicInteger COUNTER = new AtomicInteger();

    public RequestRepository() {
        list.add(new Request(COUNTER.incrementAndGet(), "Minha casa foi alagada pela enchente", "Minha casa foi alagada pela enchente que teve aqui na cidada, perdi todas as minhas coisas, não tenho nem comida para alimentar meus filhos, preciso urgentemente de ajuda para poder comprar nossas coisas e podermos recomeçar", 5000.0, 300.00, 1, 1));
        closedList.add(new Request(COUNTER.incrementAndGet(), "Estou sem dinheiro para comprar remédios para minha filha", "Este mês ainda não recebi por conta do atraso dos pagamentos na minha empresa, e tenho em casa uma filha de 3 anos que precisa de um certo remédio que é caro por conta de uma doença que ela tem, estou desesperada e preciso de ajuda, pois tenho medo que algo aconteça com ela", 369.50, 400.00, 1, 2));
        list.add(new Request(COUNTER.incrementAndGet(), "Preciso de ajuda para realizar um sonho", "Com muito esforço consegui uma vaga em uma faculdade na Suiça, mas minha familía é muito cara, e não tenho dinheiro", 30000.0, 50.00, 2, 3));
        list.add(new Request(COUNTER.incrementAndGet(), "Cai e quebrei minha clavicula", "Estava andando de bicicleta, quando cai numa vala, e com isso quebrei minha clavicula, a cirurgia é cara e atualmente não tenho dinheiro, preciso de ajuda para poder voltar a pedalar e me quebrar mais.", 12000.0, 10.00, 2, 4));
    }

    public RequestRepository(boolean some) {}

    public List<Request> getAll() {
        return list;
    }

    public Request getById(Integer id) throws Exception {
        return list.stream()
                .filter(request -> request.getIdRequest().equals(id))
                .findFirst()
                .orElseThrow(() -> new BusinessRuleException("Vakinha não encontrada."));
    }

    public Request create(Integer id, Request request) {
        request.setIdUser(id);
        request.setIdRequest(COUNTER.incrementAndGet());
        request.setReachedValue(0.0);
        list.add(request);
        return request;
    }

    public Request update(Integer id, RequestUpdateDTO newData) throws Exception {
        Request request = this.getById(id);

        String updatedTitle = newData.getTitle();
        String updatedDescription = newData.getDescription();
        Double updatedGoal = newData.getGoal();

        if (updatedTitle != null) {
            request.setTitle(updatedTitle);
        }

        if (updatedDescription != null) {
            request.setDescription(updatedDescription);
        }

        if (updatedGoal != null) {
            request.setGoal(updatedGoal);

            if (request.getReachedValue() >= request.getGoal()) {
                list.remove(request);
                closedList.add(request);
            }
        }

        return request;
    }

    public Request delete(Integer id) throws Exception {
        Request request = this.getById(id);
        list.remove(request);
        return request;
    }

    public Request incrementReachedValue(Integer id, Double value) throws Exception {
        Request request = this.getById(id);
        request.setReachedValue(request.getReachedValue() + value);

        if (request.getReachedValue() >= request.getGoal()) {
            list.remove(request);
            closedList.add(request);
        }

        return request;
    }

    public List<Request> getClosedList() {
        return closedList;
    }

    public List<Request> getByCategory(Integer id) {
        return list.stream()
                .filter(request -> request.getIdCategory().equals(id))
                .toList();
    }

    public List<Request> deleteAll(Integer id) throws Exception {
        List<Request> removeds = new ArrayList<>();
        list.stream()
                .filter(request -> request.getIdUser().equals(id))
                .forEach(request -> list.add(request));
        list.removeAll(removeds);
        return removeds;
    }
}
